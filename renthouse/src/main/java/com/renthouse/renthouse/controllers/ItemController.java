package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.ItemDto;
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
public class ItemController <T> {

    @Autowired
    private ItemService itemService;

    private int nroElem;

    @PostMapping
    public ResponseEntity<ItemModel> adiciona(
            @RequestBody @Valid ItemDto itemDto) {
        ItemModel itemModel = new ItemModel();
        // faz a conversão de dto para model
        BeanUtils.copyProperties(itemDto, itemModel);
        itemModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        itemModel.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemModel));
    }





}
