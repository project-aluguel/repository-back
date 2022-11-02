package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.EnderecoDto;
import com.renthouse.renthouse.excecao.EnderecoNaoExiste;
import com.renthouse.renthouse.excecao.UsuarioNaoExiste;
import com.renthouse.renthouse.models.EnderecoModel;
import com.renthouse.renthouse.services.EnderecoService;
import com.renthouse.renthouse.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<EnderecoDto> registraEndereco(@RequestBody EnderecoDto enderecoDto) {
        try {
            EnderecoModel enderecoModel = new EnderecoModel();
            BeanUtils.copyProperties(enderecoDto, enderecoModel);
            enderecoModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
            enderecoModel.setUsuarioModel(usuarioService.findById(enderecoDto.getIdUsuario()).get());
            enderecoService.save(enderecoModel);
            System.out.println(enderecoModel);
            return ResponseEntity.status(201).body(enderecoDto);
        } catch (Exception erro) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<EnderecoModel>> buscaEnderecosPorUsuario(@PathVariable UUID idUsuario) {
        try {
            if (usuarioService.findById(idUsuario).isEmpty()) {
                throw new UsuarioNaoExiste();
            }
            List<EnderecoModel> enderecos = enderecoService.findEnderecoByUsuario(idUsuario);
            if (enderecos.isEmpty()) {
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(200).body(enderecos);
        } catch (Exception erro) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/busca/{idEndereco}")
    public ResponseEntity<EnderecoModel> buscaEnderecoPorId(@PathVariable UUID idEndereco) {
        try {
            if (enderecoService.existePorId(idEndereco)) {
                return ResponseEntity.status(200).body(enderecoService.buscaEnderecoPorId(idEndereco));
            }
            throw new EnderecoNaoExiste();
        } catch (Exception erro) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<UUID> deletaEndereco(@PathVariable UUID idEndereco) {
        try {
            if (enderecoService.existePorId(idEndereco)) {
                UUID idDeletado = enderecoService.buscaEnderecoPorId(idEndereco).getId();
                enderecoService.deletaEndereco(idEndereco);
                return ResponseEntity.status(200).body(idDeletado);
            }
            throw new EnderecoNaoExiste();
        } catch (Exception erro) {
            return ResponseEntity.status(500).build();
        }
    }

}
