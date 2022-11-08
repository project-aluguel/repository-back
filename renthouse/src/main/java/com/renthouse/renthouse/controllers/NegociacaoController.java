package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.requisicoes.RegistroNegociacao;
import com.renthouse.renthouse.excecao.ItemAlugado;
import com.renthouse.renthouse.excecao.ItemNaoExiste;
import com.renthouse.renthouse.excecao.UsuarioNaoExiste;
import com.renthouse.renthouse.models.NegociacaoModel;
import com.renthouse.renthouse.services.ItemService;
import com.renthouse.renthouse.services.NegociacaoService;
import com.renthouse.renthouse.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/negociacoes")
public class NegociacaoController {

    @Autowired
    private NegociacaoService negociacaoService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UUID> registraNegociacao(@RequestBody RegistroNegociacao registroNegociacao) {
        if (!usuarioService.existsById(registroNegociacao.getIdAlugador())
                && !usuarioService.existsById(registroNegociacao.getIdProprietario())) {
            throw new UsuarioNaoExiste();
        }
        if (!itemService.existsById(registroNegociacao.getIdItem())) {
            throw new ItemNaoExiste();
        }
        if (!itemService.isAlugado(registroNegociacao.getIdItem())) {
            throw new ItemAlugado();
        }
        NegociacaoModel negociacaoModel = new NegociacaoModel();
        BeanUtils.copyProperties(registroNegociacao, negociacaoModel);
        negociacaoModel.setIdItem(itemService.findById(registroNegociacao.getIdItem()).get());
        negociacaoModel.setIdProprietario(usuarioService.findById(registroNegociacao.getIdProprietario()).get());
        negociacaoModel.setIdAlugador(usuarioService.findById(registroNegociacao.getIdAlugador()).get());
        negociacaoModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        return ResponseEntity.status(201).body(negociacaoService.save(negociacaoModel).getId());
    }

}
