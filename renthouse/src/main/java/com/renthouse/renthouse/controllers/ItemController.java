package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.ItemDto;
import com.renthouse.renthouse.dtos.ListaObjDto;
import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.services.ItemService;
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
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    private ListaObjDto<ItemModel> itensVetor = new ListaObjDto<>(50);

    @PostMapping
    public ResponseEntity criarItem(@RequestBody @Valid ItemDto itemDto) {
        try {
            if (itensVetor.getTamanho() >= 50) {
                return ResponseEntity.status(400).body("O usuário ja atingiu o limite (50 itens) de cadastro permitido");
            }
            System.out.println(itensVetor.getTamanho());
            ItemModel itemModel = new ItemModel();
            BeanUtils.copyProperties(itemDto, itemModel);
            itemModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
            itemService.save(itemModel);
            itensVetor.adiciona(itemModel);

            return ResponseEntity.status(201).body(itemDto);
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @GetMapping
    public ResponseEntity buscarItens() {
        try {
            if (itensVetor.getTamanho() == 0) {
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(200).body(itensVetor.exibe());
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity removerPorIndice(@PathVariable int indice) {
        try {
            if (itensVetor.removePeloIndice(indice)) {
                return ResponseEntity.status(200).build();
            }

            return ResponseEntity.status(400).body("Índice inexistente");
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @DeleteMapping
    public ResponseEntity removerElemento(@RequestBody @Valid ItemModel itemModel) {
        try {
            if (itensVetor.removeElemento(itemModel)) {
                return ResponseEntity.status(200).build();
            }
            return ResponseEntity.status(400).body("Elemento inexistente");
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @GetMapping("/{indice}")
    public ResponseEntity buscarElemento(@PathVariable int indice) {
        try {
            if (itensVetor.getElemento(indice) == null) {
                return ResponseEntity.status(400).body("Índice inexistente");
            }
            return ResponseEntity.status(200).body(itensVetor.getElemento(indice));
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @GetMapping("/ordem")
    public ResponseEntity ordenaPorPreco() {
        try {
            if (itensVetor.getTamanho() == 0) {
                return ResponseEntity.status(204).build();
            }
            ItemModel[] itens = itemService.findAll().toArray(new ItemModel[0]);
            itensVetor.ordenaArray(itens);

            return ResponseEntity.status(200).body(itens);
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
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
                        "%s;%s;%s;%.2f;%.2f;%b\n",
                        item.getNome(),
                        item.getDescricao(),
                        item.getManualUso(),
                        item.getValorItem(),
                        item.getValorGarantia(),
                        item.getAlugado()
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

    @PutMapping("{id}")
    public ResponseEntity atualizaItem(@PathVariable UUID id, @RequestBody ItemDto itemAtualizado) {
        try {
            Optional<ItemModel> itemBuscado = itemService.findById(id);

            if (itemBuscado.isEmpty()) {
                return ResponseEntity.status(400).body("ID inexistente na base de dados");
            }

            int indice = itensVetor.busca(itemBuscado.get());

            if (indice == -1) {
                return ResponseEntity.status(400).body("Item inexistente no vetor");
            }

            ItemModel item = itensVetor.getElemento(indice);
            BeanUtils.copyProperties(itemAtualizado, item);
            item.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
            itensVetor.atualiza(indice, item);

            return ResponseEntity.status(200).body(itemAtualizado);

        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }
}
