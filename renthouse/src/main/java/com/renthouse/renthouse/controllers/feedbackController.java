package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.models.FeedbackUsuarioModel;
import com.renthouse.renthouse.repositories.FeedbackItemRepository;
import com.renthouse.renthouse.repositories.FeedbackUsuarioRepository;
import com.renthouse.renthouse.repositories.ItemRepository;
import com.renthouse.renthouse.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/feedback/usuarios")
public class feedbackController {

    @Autowired
    FeedbackItemRepository feedbackItemRepository;

    @Autowired
    FeedbackUsuarioRepository feedbackUsuarioRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping("media/{idUsuario}")
    public ResponseEntity<Double> getMediaAvaliacoes(@PathVariable int idUsuario) {
        return ResponseEntity.of(feedbackUsuarioRepository.getMediaAvaliacoes(idUsuario));
    }

    @PostMapping
    public ResponseEntity<FeedbackUsuarioModel> postFeedbackUsuario(
            @RequestBody @Valid
            FeedbackUsuarioModel novoFeedbackUsuario
    ) {

        if (!itemRepository.existsById(novoFeedbackUsuario.getItemModel().getId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Item não encontrado");
        }
        if (!usuarioRepository.existsById(novoFeedbackUsuario.getUsuarioModel().getId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuario não encontrado");
        }

        feedbackUsuarioRepository.save(novoFeedbackUsuario);

        return ResponseEntity.status(201).body(novaAvaliacao);
    }

}
