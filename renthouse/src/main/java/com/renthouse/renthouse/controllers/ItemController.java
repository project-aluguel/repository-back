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
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    private ListaObjDto<ItemModel> itensVetor = new ListaObjDto<>(10);

    @PostMapping
    public ResponseEntity criarItem(@RequestBody @Valid ItemDto itemDto) {
        if (itensVetor.getTamanho() > 10) {
            return ResponseEntity.status(400).body("O usuário ja atingiu o limite (10 itens) de cadastro permitido");
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDto, itemModel);
        itemModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        itemService.save(itemModel);
        itensVetor.adiciona(itemModel);

        return ResponseEntity.status(201).body(itensVetor.getElemento(itensVetor.getTamanho() - 1));
    }

    @GetMapping
    public ResponseEntity buscarItens() {
        if (itensVetor.getTamanho() == 0) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(itensVetor.exibe());
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity removerPorIndice(@PathVariable int indice) {
        if (itensVetor.removePeloIndice(indice)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).body("Índice inexistente");
    }

    @DeleteMapping
    public ResponseEntity removerElemento(@RequestBody @Valid ItemModel itemModel) {
        if (itensVetor.removeElemento(itemModel)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).body("Elemento inexistente");
    }

    @GetMapping("/{indice}")
    public ResponseEntity buscarElemento(@PathVariable int indice) {
        if (itensVetor.getElemento(indice) == null) {
            return ResponseEntity.status(400).body("Índice inexistente");
        }
        return ResponseEntity.status(200).body(itensVetor.getElemento(indice));
    }

    @GetMapping("/ordem")
    public ResponseEntity ordenaPorPreco() {
        if (itensVetor.getTamanho() == 0) {
            return ResponseEntity.status(204).build();
        }
        ItemModel[] itens = itemService.findAll().toArray(new ItemModel[0]);
        itensVetor.ordenaArray(itens);

        return ResponseEntity.status(200).body(itens);
    }


}
