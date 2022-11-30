package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.adt.FilaObj;
import com.renthouse.renthouse.adt.PilhaItem;
import com.renthouse.renthouse.dtos.requisicoes.ImagemUrlDto;
import com.renthouse.renthouse.dtos.requisicoes.ItemDto;
import com.renthouse.renthouse.adt.ListaObjDto;
import com.renthouse.renthouse.dtos.respostas.DetalheItemCatalogo;
import com.renthouse.renthouse.dtos.respostas.ItensCatalogo;
import com.renthouse.renthouse.dtos.respostas.ItensUsuario;
import com.renthouse.renthouse.excecao.*;
import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.services.ItemService;
import com.renthouse.renthouse.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UsuarioService usuarioService;

    private PilhaItem pilhaItem = new PilhaItem(10);

    private FilaObj fila = new FilaObj(10);

    private ListaObjDto<ItemModel> itensVetor = new ListaObjDto<>(50);

    @PostMapping
    public ResponseEntity<UUID> criarItem(@RequestBody ItemDto itemDto) {
        if (!usuarioService.existsById(itemDto.getIdUsuario())) {
            throw new UsuarioNaoExiste();
        }
        if (itensVetor.getTamanho() >= 50) {
            throw new LimiteItensAtingido();
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDto, itemModel);
        itemModel.setNome(itemDto.getNome().toUpperCase());
        itemModel.setCategoria(itemDto.getCategoria().toUpperCase());
        itemModel.setUsuarioModel(usuarioService.findById(itemDto.getIdUsuario()).get());
        itemModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        itensVetor.adiciona(itemModel);
        return ResponseEntity.status(201).body(itemService.save(itemModel).getId());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ItensUsuario>> buscarItensUsuario(@PathVariable UUID idUsuario) {
        if (!usuarioService.existsById(idUsuario)) {
            throw new UsuarioNaoExiste();
        }
        if (itemService.getItensDeUsuario(idUsuario).isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(itemService.getItensDeUsuario(idUsuario));
    }

    @GetMapping("/ordem")
    public ResponseEntity<ItemModel[]> ordenaPorPreco() {
        if (itensVetor.getTamanho() == 0) {
            return ResponseEntity.status(204).build();
        }
        ItemModel[] itens = itemService.findAll().toArray(new ItemModel[0]);
        itensVetor.ordenaArray(itens);

        return ResponseEntity.status(200).body(itens);
    }

    @GetMapping("/csv")
    public ResponseEntity gerarArquivoCsv() {
        FileWriter arq = null; // objeto que representa o arquivo de gravacao
        Formatter saida = null; // objeto usado para gravar o arquivo
        Boolean deuRuim = false;

        try {
            arq = new FileWriter("produtos");
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.exit(1);
            deuRuim = true;
            return ResponseEntity.status(500).body(erro);
        }

        try {
//            saida.format("ID;NOME;VALOR;QUANTIDADE");
            for (int i = 0; i < itensVetor.getTamanho(); i++) {
                ItemDto item = new ItemDto();
                BeanUtils.copyProperties(itensVetor.getElemento(i), item);
                saida.format(
                        "%s;%s;%s;%s;%.2f;%.2f;%b;%b;%b\n",
                        item.getNome(),
                        item.getCategoria(),
                        item.getDescricao(),
                        item.getManualUso(),
                        item.getValorItem(),
                        item.getValorGarantia(),
                        item.getAlugado(),
                        item.getEntregaFrete(),
                        item.getEntregaPessoal()
                );
            }
        } catch (FormatterClosedException erro) {
            System.exit(1);
            deuRuim = true;
            return ResponseEntity.status(500).body(erro);
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                deuRuim = true;
                return ResponseEntity.status(500).body("Erro ao fechar o arquivo");
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/catalogo/{idUsuario}")
    public ResponseEntity<List<ItensCatalogo>> buscaItensCatalogo(@PathVariable UUID idUsuario) {
        List<ItensCatalogo> listaItens = itemService.buscaItensCatalogo(idUsuario);
        if (listaItens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaItens);
    }

    @GetMapping("/catalogo/{idUsuario}/categoria/{categoria}")
    public ResponseEntity<List<ItensCatalogo>> buscaItensCatalogoPorCategoria(
            @PathVariable UUID idUsuario,
            @PathVariable String categoria
    ) {
        categoria = categoria.toUpperCase();
        List<ItensCatalogo> listaItens = itemService.buscaItensCatalogoPorCategoria(categoria, idUsuario);
        if (listaItens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaItens);
    }

    @GetMapping("/catalogo/{idUsuario}/nome/{nome}")
    public ResponseEntity<List<ItensCatalogo>> buscaItensCatalogoPorNome(
            @PathVariable UUID idUsuario,
            @PathVariable String nome
    ) {
        if (!nome.isBlank() && !fila.isFull()) {
            fila.insert(nome);
        }
        nome = nome.toUpperCase();
        List<ItensCatalogo> listaItens = itemService.buscaItensCatalogoPorNome(nome, idUsuario);
        if (listaItens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaItens);
    }

    @GetMapping("/catalogo/item/{idItem}")
    public ResponseEntity<DetalheItemCatalogo> buscaDetalhesDoItem(
            @PathVariable
            UUID idItem
    ) {
        if (!itemService.existsById(idItem)) {
            throw new ItemNaoExiste();
        }
        return ResponseEntity.status(200).body(itemService.buscaDetalheItem(idItem).get());
    }

    @GetMapping("/catalogo/recente/{idUsuario}")
    public ResponseEntity<List<ItensCatalogo>> buscaItensRecentesCatalogo(@PathVariable UUID idUsuario) {
        if (!usuarioService.existsById(idUsuario)) {
            throw new UsuarioNaoExiste();
        }
        List<ItensCatalogo> itensRecentes = itemService.buscaItensRecenteCatalogo(idUsuario);
        if (itensRecentes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(itensRecentes);
    }

    @PutMapping("/{idItem}")
    public ResponseEntity<ItemModel> atualizaItem(@PathVariable UUID idItem, @RequestBody ItemDto itemAtualizado) {
        if (!itemService.findById(idItem).isEmpty()) {
            ItemModel itemModel = itemService.findById(idItem).get();
            BeanUtils.copyProperties(itemAtualizado, itemModel);
            itemModel.setAtualizadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
            itemService.save(itemModel);
            atualizaVetor(itemModel.getUsuarioModel().getId());
            return ResponseEntity.status(200).body(itemModel);
        }
        throw new ItemNaoExiste();
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<UUID> deletaItem(@PathVariable UUID idItem) {
        if (!itemService.findById(idItem).isEmpty()) {
            if (!pilhaItem.isFull()) {
                pilhaItem.push(itemService.findById(idItem).get());
            }
            itemService.delete(itemService.findById(idItem).get());
            return ResponseEntity.status(200).body(idItem);
        }
        throw new ItemPilhaNaoExiste();
    }

    @PostMapping("/desfazer-exclusao")
    public void desfazerExclusao() {
        if (pilhaItem.isEmpty()) {
            throw new ItemPilhaNaoExiste();
        }
        itemService.save(pilhaItem.pop());
    }

    @GetMapping("/catalogo/ultimas-buscas")
    public ResponseEntity<String[]> listaUltimasBuscas() {
        if (!fila.isEmpty()) {
            return ResponseEntity.status(200).body(fila.getFila());
        }
        return ResponseEntity.status(204).build();
    }


    @GetMapping("/verifica-deletados")
    public boolean verificaPilha() {
        return !pilhaItem.isEmpty();
    }

    @GetMapping("/verifica-buscas")
    public boolean verificaFila() {
        return !fila.isEmpty();
    }

    public void atualizaVetor(UUID idUsuario) {
        itensVetor.limpa();
        List<ItemModel> itens = itemService.getItensDeUsuarioVetor(idUsuario);
        for (ItemModel itemDaVez :
                itens) {
            itensVetor.adiciona(itemDaVez);
        }
    }

    @PostMapping(value = "/arquivo-txt/{idItem}", consumes = "text/*")
    public ResponseEntity<UUID> salvaTxt(
            @RequestBody byte[] fileTxt,
            @PathVariable UUID idItem
    ) throws UnsupportedEncodingException {


        String itemString = new String(fileTxt, "UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (itemService.findById(idItem).isEmpty()) {
            throw new ItemNaoExiste();
        }

        ItemModel item = itemService.findById(idItem).get();

        if (!usuarioService.existsById(UUID.fromString(itemString.substring(145, 181)))) {
            throw new UsuarioNaoExiste();
        }

        String infosArquivo = itemString.substring(1, 144);
        UUID idUsuario = UUID.fromString(itemString.substring(145, 181));
        String nome = itemString.substring(181, 213);
        String descricao = itemString.substring(214, 514);
        String manualUso = itemString.substring(515, 815);
        String categoria = itemString.substring(816, 846);
        Double valorItem = Double.valueOf(itemString.substring(847, 853));
        Double valorGarantia = Double.valueOf(itemString.substring(854, 860));
        LocalDate dataInicio = LocalDate.parse(itemString.substring(861, 871), formatter);
        LocalDate dataFim = LocalDate.parse(itemString.substring(874, 884), formatter);
        String entregaPessoal = itemString.substring(887, 890);
        String entregaFrete = itemString.substring(890, 893);

        item.setNome(nome);
        item.setDescricao(descricao);
        item.setManualUso(manualUso);
        item.setCategoria(categoria);
        item.setValorItem(valorItem);
        item.setValorGarantia(valorGarantia);
        item.setDataInicio(dataInicio);
        item.setDataFim(dataFim);
        item.setEntregaPessoal(entregaPessoal.equals("SIM"));
        item.setEntregaFrete(entregaFrete.equals("SIM"));
        item.setUsuarioModel(usuarioService.findById(idUsuario).get());
        item.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        itemService.save(item);
        return ResponseEntity.status(201).body(item.getId());

    }

    @PostMapping("/arquivo-txt/salva-imagem")
    public ResponseEntity<UUID> salvaImagem(@RequestBody ImagemUrlDto imagem) {
        ItemModel itemNovo = new ItemModel();
        itemNovo.setImagemUrl(imagem.getImagemUrl());
        itemService.save(itemNovo);
        return ResponseEntity.status(201).body(itemNovo.getId());
    }

    @GetMapping(value = "/arquivo-txt/{idItemParam}", produces = "text/plain")
    public ResponseEntity<byte[]> buscaArquivoTxt(@PathVariable UUID idItemParam) {
        if (!itemService.existsById(idItemParam)) {
            throw new ItemNaoExiste();
        }

        ItemModel item = itemService.findById(idItemParam).get();
        UUID idUsuario = usuarioService.findById(itemService.findById(idItemParam).get().getUsuarioModel().getId()).get().getId();

        String
                idProprietario = String.valueOf(idUsuario),
                nomeProprietario = usuarioService.findById(idUsuario).get().getNomeCompleto(),
                cpf = usuarioService.findById(idUsuario).get().getCpf(),
                idItem = String.valueOf(idItemParam),
                nomeItem = item.getNome(),
                valorDiario = String.valueOf(item.getValorItem()),
                valorGarantia = String.valueOf(item.getValorGarantia()),
                dataInicio = String.valueOf(item.getDataInicio()),
                dataFim = String.valueOf(item.getDataFim()),
                entregaPessoal = String.valueOf(item.getEntregaPessoal()),
                entregaFrete = String.valueOf(item.getEntregaFrete()),
                arquivoFormatado = "";

        arquivoFormatado += "01";
        arquivoFormatado += "PRODUTO";
        arquivoFormatado += LocalDateTime.now() + " ".repeat(5);
        arquivoFormatado += "1.0";
        arquivoFormatado += idProprietario;
        arquivoFormatado += nomeProprietario + " ".repeat(30 - nomeProprietario.length());
        arquivoFormatado += cpf + " ".repeat(20 - cpf.length());
        arquivoFormatado += idItem;
        arquivoFormatado += nomeItem + " ".repeat(30 - nomeItem.length());
        arquivoFormatado += valorDiario + " ".repeat(6 - valorDiario.length());
        arquivoFormatado += valorGarantia + " ".repeat(6 - valorGarantia.length());
        arquivoFormatado += dataInicio + " ".repeat(12 - dataInicio.length());
        arquivoFormatado += dataFim + " ".repeat(12 - dataFim.length());
        arquivoFormatado += entregaPessoal + " ".repeat(5 - entregaPessoal.length());
        arquivoFormatado += entregaFrete + " ".repeat(5 - entregaFrete.length());
        
        byte[] arquivoTxt = arquivoFormatado.getBytes(StandardCharsets.UTF_8);

        return ResponseEntity
                .status(200)
                .header("content-disposition", "attachment; filename=\"item.txt\"")
                .body(arquivoTxt);
    }

    public void limpaSessaoUsuario() {
        while (pilhaItem.getTopo() != -1) {
            pilhaItem.pop();
        }
        while (fila.getTamanho() != 0) {
            fila.poll();
        }
    }
}
