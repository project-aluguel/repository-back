package com.renthouse.renthouse.controllers;

// solicita para que a service execute a regra de negócio do endpoint
// recebe os dados de dto e retorna para o usuario

import com.renthouse.renthouse.dtos.AtualizaUsuarioDto;
import com.renthouse.renthouse.dtos.ListaObjDto;
import com.renthouse.renthouse.dtos.LoginDto;
import com.renthouse.renthouse.dtos.UsuarioDto;
import com.renthouse.renthouse.models.EnderecoModel;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.services.EnderecoService;
import com.renthouse.renthouse.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ListaObjDto<UsuarioModel> usuariosVetor = new ListaObjDto(100);

    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody UsuarioDto usuarioDto) {

        if (usuarioService.existsByEmail(usuarioDto.getEmail())) {
            return ResponseEntity.status(409).body("Conflito: este email já está sendo usado!");
        }

        if (usuarioService.existsByCpf(usuarioDto.getCpf())) {
            return ResponseEntity.status(409).body("Conflito: este cpf já está sendo usado!");
        }

        EnderecoModel enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(usuarioDto.getEndereco(), enderecoModel);
        enderecoModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        enderecoService.save(enderecoModel);

        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        usuarioModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
        usuarioService.save(usuarioModel);

        usuariosVetor.adiciona(usuarioModel);

        return ResponseEntity.status(201).body(usuarioDto);
    }

    @GetMapping
    public ResponseEntity getAllUsuarios() {

        if (usuariosVetor.getTamanho() == 0) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuariosVetor.exibe());

    }

    @GetMapping("/{id}")
    public ResponseEntity getAllUsuario(@PathVariable UUID id) {

        Optional<UsuarioModel> usuarioBuscado = usuarioService.findById(id);

        if (!usuarioBuscado.isPresent()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuarioBuscado.get());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable UUID id) {

        Optional<UsuarioModel> usuarioBuscado = usuarioService.findById(id);

        if (!usuarioBuscado.isPresent()) {
            return ResponseEntity.status(204).build();
        }

        usuarioService.delete(usuarioBuscado.get());


        return ResponseEntity.status(200).build();

    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody LoginDto credenciaisUser) {

        for (UsuarioModel usuarioModel : usuarioService.findAll()) {
            if (usuarioModel.getEmail().equals(credenciaisUser.getEmail())
                    && usuarioModel.getSenha().equals(credenciaisUser.getSenha())) {

                UsuarioModel usuario = new UsuarioModel();
                BeanUtils.copyProperties(usuarioModel, usuario);

                int indice = usuariosVetor.buscaUsuario(usuario);
                usuario.setAutenticado(true);
                usuariosVetor.atualiza(indice, usuario);

                return ResponseEntity.status(200).body(usuario);
            }
        }

        return ResponseEntity.status(400).build();
    }

    @PutMapping("/logoff/{id}")
    public ResponseEntity efetuarLogoff(@PathVariable UUID id) {

        for (UsuarioModel usuarioModel : usuarioService.findAll()) {
            if (usuarioModel.getId().equals(id)) {

                UsuarioModel usuario = new UsuarioModel();
                BeanUtils.copyProperties(usuarioModel, usuario);

                int indice = usuariosVetor.buscaUsuario(usuario);
                usuario.setAutenticado(false);
                usuariosVetor.atualiza(indice, usuario);

                return ResponseEntity.status(200).build();
            }
        }

        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizaUser(@PathVariable UUID id, @RequestBody AtualizaUsuarioDto usuarioAtualizado) {

        for (UsuarioModel usuarioModel : usuarioService.findAll()) {
            if (usuarioModel.getId().equals(id)) {

                UsuarioModel usuario = new UsuarioModel();
                BeanUtils.copyProperties(usuarioModel, usuario);

                int indice = usuariosVetor.buscaUsuario(usuario);

                usuario.setNomeCompleto(usuarioAtualizado.getNomeCompleto());
                usuario.setEmail(usuarioAtualizado.getEmail());
                usuario.setSenha(usuarioAtualizado.getSenha());
                usuario.setTelefone(usuarioAtualizado.getTelefone());
                usuario.setAtualizadoEm(LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS));
                usuario.setAutenticado(true);

                usuariosVetor.atualiza(indice, usuario);

                return ResponseEntity.status(200).body(usuario);
            }
        }

        return ResponseEntity.status(400).build();

    }

}
