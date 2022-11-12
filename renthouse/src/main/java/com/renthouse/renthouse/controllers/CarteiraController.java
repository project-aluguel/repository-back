package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.requisicoes.RecargaCarteira;
import com.renthouse.renthouse.dtos.respostas.CarteiraUsuario;
import com.renthouse.renthouse.excecao.CarteiraUsuarioNaoExiste;
import com.renthouse.renthouse.excecao.UsuarioNaoExiste;
import com.renthouse.renthouse.excecao.ValorNegativo;
import com.renthouse.renthouse.models.CarteiraModel;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.services.CarteiraService;
import com.renthouse.renthouse.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RestController
@RequestMapping("/carteiras")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<UUID> criaCarteira(@PathVariable UUID idUsuario) {
        if (usuarioService.findById(idUsuario).isEmpty()) {
            throw new UsuarioNaoExiste();
        }
        CarteiraModel carteiraModel = new CarteiraModel();
        carteiraModel.setUsuarioModel(usuarioService.findById(idUsuario).get());
        carteiraModel.setSaldo(0.0);
        carteiraModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        carteiraService.save(carteiraModel);
        return ResponseEntity.status(201).body(carteiraModel.getCarteiraId());
    }

    @GetMapping("{idUsuario}")
    public ResponseEntity<CarteiraUsuario> buscaCarteira(@PathVariable UUID idUsuario) {
        if (!carteiraService.existsByIdUsuario(idUsuario)) {
            throw new CarteiraUsuarioNaoExiste();
        }
        return ResponseEntity.status(200).body(carteiraService.buscaCarteira(idUsuario));
    }

    @PutMapping("{idCarteira}")
    public ResponseEntity<CarteiraUsuario> atualizaCarteira(
            @PathVariable UUID idCarteira,
            @RequestBody RecargaCarteira recargaCarteira
    ) {
        if (!carteiraService.existsByIdCarteira(idCarteira)) {
            throw new CarteiraUsuarioNaoExiste();
        }
        if (recargaCarteira.getRecarga() < 0.0) {
            throw new ValorNegativo();
        }
        CarteiraModel carteiraModel = carteiraService.buscaCarteiraPorIdCarteira(idCarteira).get();
        carteiraModel.setSaldo(carteiraModel.getSaldo() + recargaCarteira.getRecarga());
        carteiraModel.setAtualizadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        carteiraService.save(carteiraModel);
        return ResponseEntity.status(200)
                .body(carteiraService.buscaCarteira(carteiraModel.getUsuarioModel().getId()));
    }

}
