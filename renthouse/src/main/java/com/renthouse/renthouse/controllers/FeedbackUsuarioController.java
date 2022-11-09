package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.models.FeedbackUsuarioModel;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.repositories.FeedbackItemRepository;
import com.renthouse.renthouse.repositories.ItemRepository;
import com.renthouse.renthouse.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feedback/usuarios")
public class FeedbackUsuarioController {

    @Autowired
    FeedbackItemRepository feedbackItemRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<List<FeedbackUsuarioModel>> buscar

}
