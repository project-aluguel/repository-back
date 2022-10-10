package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.ItemDto;
import com.renthouse.renthouse.dtos.ListaObjDto;
import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.services.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private ListaObjDto<ItemDto> itensVetor = new ListaObjDto<>(10);

    @PostMapping
    public ResponseEntity<ItemDto> criarItem(
            @RequestBody @Valid ItemDto itemDto) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDto, itemModel);
        itemModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        itensVetor.adiciona(itemDto);
        itemService.save(itemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(itensVetor.getElemento(itensVetor.getTamanho()-1));
    }

    @GetMapping
    public ResponseEntity buscarItens(){
        if (itensVetor.getTamanho() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(itensVetor.exibe());
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity removerPorIndice(@PathVariable int indice) {
        if (itensVetor.removePeloIndice(indice)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Indice invalido");
    }

    @DeleteMapping
    public ResponseEntity removerElemento(
            @RequestBody @Valid ItemDto itemDto) {
        if (itensVetor.removeElemento(itemDto)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Indice invalido");
    }

    @GetMapping("/{indice}")
    public ResponseEntity<ItemDto> buscarElemento(@PathVariable int indice) {
        if (itensVetor.getTamanho() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(itensVetor.getElemento(indice));
    }

    @GetMapping("/ordena")
    public ResponseEntity ordenaItensPorPreco(){
        ItemModel[] itens = itemService.findAll().toArray(new ItemModel[0]);
        itensVetor.ordenaArray(itens);
        return ResponseEntity.status(200).body(itens);
    }














}
