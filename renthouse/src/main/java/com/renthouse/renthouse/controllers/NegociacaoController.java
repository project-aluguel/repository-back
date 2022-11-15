package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.requisicoes.RegistroNegociacao;
import com.renthouse.renthouse.excecao.*;
import com.renthouse.renthouse.models.CarteiraModel;
import com.renthouse.renthouse.models.NegociacaoModel;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.services.CarteiraService;
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

    @Autowired
    private CarteiraService carteiraService;

    @PostMapping
    public ResponseEntity<UUID> registraNegociacao(@RequestBody RegistroNegociacao registroNegociacao) {
        if (!usuarioService.existsById(registroNegociacao.getIdAlugador())
                || !usuarioService.existsById(registroNegociacao.getIdProprietario())) {
            throw new UsuarioNaoExiste();
        }
        if (!itemService.existsByUsuarioId(registroNegociacao.getIdProprietario())) {
            throw new ItemNaoPertenceUsuario();
        }
        if (!itemService.existsById(registroNegociacao.getIdItem())) {
            throw new ItemNaoExiste();
        }
        if (itemService.isAlugado(registroNegociacao.getIdItem())) {
            throw new ItemAlugado();
        }
        if (!verificaSaldo(registroNegociacao.getIdAlugador(), registroNegociacao.getValorEmprestimo())) {
            throw new SaldoInsuficiente();
        }
        realizaTransacaoNegociacao(
                registroNegociacao.getIdProprietario(),
                registroNegociacao.getIdAlugador(),
                registroNegociacao.getValorEmprestimo()
        );
        NegociacaoModel negociacaoModel = new NegociacaoModel();
        BeanUtils.copyProperties(registroNegociacao, negociacaoModel);
        negociacaoModel.setIdItem(itemService.findById(registroNegociacao.getIdItem()).get());
        itemService.atualizaItemParaEmprestado(registroNegociacao.getIdItem());
        negociacaoModel.setIdProprietario(usuarioService.findById(registroNegociacao.getIdProprietario()).get());
        negociacaoModel.setIdAlugador(usuarioService.findById(registroNegociacao.getIdAlugador()).get());
        negociacaoModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        return ResponseEntity.status(201).body(negociacaoService.save(negociacaoModel).getId());
    }

    public boolean verificaSaldo(UUID idAlugador, Double valorNegociacao) {
        Double saldoUsuario = carteiraService.buscaCarteira(idAlugador).getSaldoCarteira();
        return saldoUsuario > valorNegociacao;
    }

    public void realizaTransacaoNegociacao(UUID idProprietario, UUID idAlugador, Double valorNegociacao) {
        CarteiraModel alugador = carteiraService.buscaCarteiraPorIdUsuario(idAlugador).get();
        CarteiraModel proprietario = carteiraService.buscaCarteiraPorIdUsuario(idProprietario).get();

        alugador.setSaldo(alugador.getSaldo() - valorNegociacao);
        proprietario.setSaldo(proprietario.getSaldo() + valorNegociacao);

        carteiraService.save(alugador);
        carteiraService.save(proprietario);
    }

}
