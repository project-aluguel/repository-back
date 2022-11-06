package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.requisicoes.ItemDto;
import com.renthouse.renthouse.dtos.requisicoes.ListaObjDto;
import com.renthouse.renthouse.dtos.respostas.ItensUsuario;
import com.renthouse.renthouse.excecao.ItemNaoExiste;
import com.renthouse.renthouse.excecao.LimiteItensAtingido;
import com.renthouse.renthouse.excecao.UsuarioNaoExiste;
import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.services.ItemService;
import com.renthouse.renthouse.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
            itemService.delete(itemService.findById(idItem).get());
            return ResponseEntity.status(200).body(idItem);
        }
        throw new ItemNaoExiste();
    }

    public void atualizaVetor(UUID idUsuario) {
        itensVetor.limpa();
        List<ItemModel> itens = itemService.getItensDeUsuarioVetor(idUsuario);
        for (ItemModel itemDaVez :
                itens) {
            itensVetor.adiciona(itemDaVez);
        }
    }
}
