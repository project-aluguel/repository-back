package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.respostas.FeedbacksItem;
import com.renthouse.renthouse.dtos.respostas.FeedbacksUsuario;
import com.renthouse.renthouse.excecao.*;
import com.renthouse.renthouse.models.FeedbackModel;
import com.renthouse.renthouse.services.FeedbackService;
import com.renthouse.renthouse.services.ItemService;
import com.renthouse.renthouse.services.NegociacaoService;
import com.renthouse.renthouse.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    ItemService itemService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    NegociacaoService negociacaoService;

    @PostMapping
    public ResponseEntity<FeedbackModel> registraFeedback(@RequestBody @Valid FeedbackModel feedbackModel){
        if (!usuarioService.existsById(feedbackModel.getAvaliadorId().getId())
                || !usuarioService.existsById(feedbackModel.getProprietarioId().getId())) {
            throw new UsuarioNaoExiste();
        }
        if (!itemService.existsByUsuarioId(feedbackModel.getProprietarioId().getId())) {
            throw new ItemNaoPertenceUsuario();
        }
        if (!itemService.existsById(feedbackModel.getItemId().getId())) {
            throw new ItemNaoExiste();
        }
        if (itemService.isAlugado(feedbackModel.getItemId().getId())) {
            throw new ItemAlugado();
        }
        FeedbackModel novoFeedback = new FeedbackModel();
        BeanUtils.copyProperties(feedbackModel, novoFeedback);
        novoFeedback.setItemId(itemService.findById(feedbackModel.getItemId().getId()).get());
        novoFeedback.setProprietarioId(usuarioService.findById(feedbackModel.getProprietarioId().getId()).get());
        novoFeedback.setAvaliadorId(usuarioService.findById(feedbackModel.getAvaliadorId().getId()).get());

        return ResponseEntity.status(201).body(feedbackService.save(novoFeedback));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<FeedbacksUsuario> buscarMediaFeedbackUsuario(@PathVariable UUID idUsuario) {
        if (!usuarioService.existsById(idUsuario)) {
            throw new UsuarioNaoExiste();
        }
        FeedbacksUsuario novoFeedback = new FeedbacksUsuario();
        novoFeedback.setIdFeedbackUsuario(idUsuario);
        novoFeedback.setNotaMediaUsuario(feedbackService.getMediaAvaliacoesUsuario(idUsuario));
        novoFeedback.setTotalAvaliacoes(feedbackService.getTotalAvaliacoesUsuario(idUsuario));
        return ResponseEntity.status(200).body(novoFeedback);
    }

    @GetMapping("/item/{idItem}")
    public ResponseEntity<FeedbacksItem> buscarMediaFeedbackItem(@PathVariable UUID idItem) {
        if (!itemService.existsById(idItem)) {
            throw new ItemNaoExiste();
        }
        FeedbacksItem novoFeedback = new FeedbacksItem();
        novoFeedback.setIdFeedbackItem(idItem);
        novoFeedback.setNotaMediaItem(feedbackService.getMediaAvaliacoesItem(idItem));
        novoFeedback.setTotalAvaliacoes(feedbackService.getTotalAvaliacoesItem(idItem));
        return ResponseEntity.status(200).body(novoFeedback);
    }

}
