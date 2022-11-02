package com.renthouse.renthouse.controllers;

// solicita para que a service execute a regra de negócio do endpoint
// recebe os dados de dto e retorna para o usuario

import com.renthouse.renthouse.dtos.AtualizaUsuarioDto;
import com.renthouse.renthouse.dtos.LoginDto;
import com.renthouse.renthouse.dtos.UsuarioDto;
import com.renthouse.renthouse.excecao.ConflitoCpf;
import com.renthouse.renthouse.excecao.ConflitoEmail;
import com.renthouse.renthouse.models.EnderecoModel;
import com.renthouse.renthouse.models.UsuarioModel;
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
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EnderecoService enderecoService;
    private EnderecoController enderecoController = new EnderecoController();

    @PostMapping
    public ResponseEntity<UUID> criarUsuario(@RequestBody UsuarioDto usuarioDto) {
        try {
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
        } catch (Exception erro) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity getAllUsuarios() {
        try {
            List<UsuarioModel> usuarios = usuarioService.findAll();
            if (usuarios.isEmpty()) {
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(200).body(usuarios);
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getAllUsuario(@PathVariable UUID id) {
        try {
            Optional<UsuarioModel> usuarioBuscado = usuarioService.findById(id);
            if (!usuarioBuscado.isPresent()) {
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(200).body(usuarioBuscado.get());
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable UUID id) {
        try {
            Optional<UsuarioModel> usuarioBuscado = usuarioService.findById(id);
            if (!usuarioBuscado.isPresent()) {
                return ResponseEntity.status(204).build();
            }
            usuarioService.delete(usuarioBuscado.get());
            return ResponseEntity.status(200).build();
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody LoginDto credenciaisUser) {
        try {
            for (UsuarioModel usuarioModel : usuarioService.findAll()) {
                if (usuarioModel.getEmail().equals(credenciaisUser.getEmail())
                        && usuarioModel.getSenha().equals(credenciaisUser.getSenha())) {
                    usuarioModel.setAutenticado(true);
                    return ResponseEntity.status(200).body(usuarioModel);
                }
            }
            return ResponseEntity.status(400).build();
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @PutMapping("/logoff/{id}")
    public ResponseEntity efetuarLogoff(@PathVariable UUID id) {
        try {
            for (UsuarioModel usuarioModel : usuarioService.findAll()) {
                if (usuarioModel.getId().equals(id)) {
                    usuarioModel.setAutenticado(false);
                    return ResponseEntity.status(200).build();
                }
            }
            return ResponseEntity.status(400).build();
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizaUser(@PathVariable UUID id, @RequestBody AtualizaUsuarioDto usuarioAtualizado) {
        try {
            Optional<UsuarioModel> usuarioModel = usuarioService.findById(id);
            if (!usuarioModel.isEmpty()) {
//                código de update (aguardando prof yoshi passar o conteudo)
                return ResponseEntity.status(200).body(usuarioAtualizado);
            }
            return ResponseEntity.status(400).build();
        } catch (Exception erro) {
            return ResponseEntity.status(500).body(erro);
        }
    }

}
