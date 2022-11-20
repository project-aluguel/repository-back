package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.requisicoes.FeedbackDto;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NegociacaoService negociacaoService;

    @PostMapping
    public ResponseEntity<UUID> registraFeedback(@RequestBody @Valid FeedbackDto requisicao) {
        if (!negociacaoService.existsById(requisicao.getIdNegociacao())) {
            throw new NegociacaoNaoExiste();
        }
        if (!usuarioService.existsById(requisicao.getIdAvaliador())
                || !usuarioService.existsById(requisicao.getIdProprietario())) {
            throw new UsuarioNaoExiste();
        }
        if (!itemService.existsById(requisicao.getIdItem())) {
            throw new ItemNaoExiste();
        }
        if (!negociacaoService
                .negociacaoEhVeridica(
                        requisicao.getIdNegociacao(),
                        requisicao.getIdProprietario(),
                        requisicao.getIdAvaliador(),
                        requisicao.getIdItem()
                )
        ) {
            throw new NegociacaoIncoerente();
        }
        if (requisicao.getNotaProduto() < 1.0 || requisicao.getNotaProprietario() > 5.0) {
            throw new NotaFeedbackInvalida();
        }
        FeedbackModel novoFeedback = new FeedbackModel();
        BeanUtils.copyProperties(requisicao, novoFeedback);
        novoFeedback.setIdNegociacao(negociacaoService.buscaPorId(requisicao.getIdNegociacao()).get());
        novoFeedback.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        feedbackService.save(novoFeedback);
        return ResponseEntity.status(201).body(novoFeedback.getId());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<FeedbacksUsuario> buscarFeedbacksUsuario(@PathVariable UUID idUsuario) {
        if (!usuarioService.existsById(idUsuario)) {
            throw new UsuarioNaoExiste();
        }
        return ResponseEntity.status(200).body(feedbackService.buscarFeedbacksUsuario(idUsuario));
    }

    @GetMapping("/item/{idItem}")
    public ResponseEntity<FeedbacksItem> buscarFeedbacksItem(@PathVariable UUID idItem) {
        if (!itemService.existsById(idItem)) {
            throw new ItemNaoExiste();
        }
        return ResponseEntity.status(200).body(feedbackService.buscarFeedbacksItem(idItem));
    }

    @GetMapping
    public ResponseEntity<List<FeedbackModel>> getAll() {
        return ResponseEntity.status(200).body(feedbackService.findAll());
    }

}
