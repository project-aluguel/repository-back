package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.requisicoes.EnderecoDto;
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
@CrossOrigin(origins = "*", maxAge = 3600)
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
        if (!usuarioService.existsById(idUsuario)) {
            throw new UsuarioNaoExiste();
        }
        List<EnderecoModel> enderecos = enderecoService.findEnderecoByUsuario(idUsuario);
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(enderecos);
    }

    @GetMapping("/busca/{idEndereco}")
    public ResponseEntity<EnderecoModel> buscaEnderecoPorId(@PathVariable UUID idEndereco) {
        if (enderecoService.existePorId(idEndereco)) {
            return ResponseEntity.status(200).body(enderecoService.buscaEnderecoPorId(idEndereco));
        }
        throw new EnderecoNaoExiste();
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<UUID> deletaEndereco(@PathVariable UUID idEndereco) {
        if (enderecoService.existePorId(idEndereco)) {
            UUID idDeletado = enderecoService.buscaEnderecoPorId(idEndereco).getId();
            enderecoService.deletaEndereco(idEndereco);
            return ResponseEntity.status(200).body(idDeletado);
        }
        throw new EnderecoNaoExiste();
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoModel> atualizaEndereco(
            @PathVariable UUID idEndereco,
            @RequestBody EnderecoDto enderecoAtualizado
    ) {
        if (enderecoService.existePorId(idEndereco)) {
            EnderecoModel enderecoModel = enderecoService.buscaEnderecoPorId(idEndereco);
            BeanUtils.copyProperties(enderecoAtualizado, enderecoModel);
            enderecoModel.setAtualizadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
            enderecoService.save(enderecoModel);
            return ResponseEntity.status(200).body(enderecoModel);
        }
        throw new EnderecoNaoExiste();
    }

}
