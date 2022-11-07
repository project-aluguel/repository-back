package com.renthouse.renthouse.controllers;

// solicita para que a service execute a regra de negócio do endpoint
// recebe os dados de dto e retorna para o usuario

import com.renthouse.renthouse.dtos.requisicoes.AtualizaUsuarioDto;
import com.renthouse.renthouse.dtos.requisicoes.LoginDto;
import com.renthouse.renthouse.dtos.requisicoes.UsuarioDto;
import com.renthouse.renthouse.excecao.*;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UUID> criaUsuario(@RequestBody UsuarioDto usuarioDto) {
        if (usuarioService.existsByEmail(usuarioDto.getEmail())) {
            throw new ConflitoEmail();
        }
        if (usuarioService.existsByCpf(usuarioDto.getCpf())) {
            throw new ConflitoCpf();
        }
        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        usuarioModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        usuarioService.save(usuarioModel);
        UUID idUsuario = usuarioService.findByEmail(usuarioDto.getEmail()).getId();
        return ResponseEntity.status(201).body(idUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
        List<UsuarioModel> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioModel> getUsuario(@PathVariable UUID idUsuario) {
        if (usuarioService.existsById(idUsuario)) {
            return ResponseEntity.status(200).body(usuarioService.findById(idUsuario).get());
        }
        throw new UsuarioNaoExiste();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<UUID> deletaUsuario(@PathVariable UUID idUsuario) {
        if (usuarioService.existsById(idUsuario)) {
            usuarioService.delete(usuarioService.findById(idUsuario).get());
            return ResponseEntity.status(200).body(idUsuario);
        }
        throw new UsuarioNaoExiste();
    }

    @PutMapping("/login")
    public ResponseEntity<UsuarioModel> efetuaLogin(@RequestBody LoginDto loginDto) {
        Optional<UsuarioModel> usuario = usuarioService.findByEmailAndPassword(loginDto);
        if (!usuario.isEmpty()) {
            usuario.get().setAutenticado(true);
            usuarioService.save(usuario.get());
            return ResponseEntity.status(200).body(usuario.get());
        }
        throw new LoginInvalido();
    }

    @PutMapping("/logoff/{id}")
    public ResponseEntity<UUID> efetuaLogoff(@PathVariable UUID id) {
        Optional<UsuarioModel> usuario = usuarioService.findById(id);
        if (!usuario.isEmpty()) {
            usuario.get().setAutenticado(false);
            usuarioService.save(usuario.get());
            return ResponseEntity.status(200).body(id);
        }
        throw new LoginInvalido();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> atualizaUser(@PathVariable UUID id, @RequestBody AtualizaUsuarioDto usuarioAtualizado) {
        if (!usuarioService.findById(id).isEmpty()) {
            UsuarioModel usuarioModel = usuarioService.findById(id).get();
            BeanUtils.copyProperties(usuarioAtualizado, usuarioModel);
            usuarioModel.setAtualizadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
            usuarioService.save(usuarioModel);
            return ResponseEntity.status(200).body(usuarioModel);
        }
        throw new UsuarioNaoExiste();
    }

}
