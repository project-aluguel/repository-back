package com.renthouse.renthouse.controllers;

// solicita para que a service execute a regra de negócio do endpoint
// recebe os dados de dto e retorna para o usuario

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

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/renthouse")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    private EnderecoService enderecoService;
    EnderecoController enderecoController = new EnderecoController();


    // @valid é o responsavel por fazer a leitura das anotações de dto, como de notblank por exemplo
    // sem ele o spring ignora as anotações em dto.

    @PostMapping("/cadastro")
    public ResponseEntity<Object> criarUsuario(@RequestBody UsuarioDto usuarioDto) {

        if (usuarioService.existsByEmail(usuarioDto.getEmail())) {
            return ResponseEntity.status(409).body("Conflito: este email já está sendo usado!");
        }

        if (usuarioService.existsByCpf(usuarioDto.getCpf())) {
            return ResponseEntity.status(409).body("Conflito: este cpf já está sendo usado!");
        }

        EnderecoModel enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(usuarioDto.getEndereco(), enderecoModel);
        enderecoModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")));
        enderecoService.save(enderecoModel);

        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        usuarioModel.setCriadoEm(LocalDateTime.now(ZoneId.of("UTC")));
        usuarioService.save(usuarioModel);

        return ResponseEntity.status(201).body(usuarioDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if (!usuarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if (!usuarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
        usuarioService.delete(usuarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
    }

    @PatchMapping("/autenticar/{id}")
    public ResponseEntity<Object> patchAutenticarUsuario(@PathVariable(value = "id") UUID id,
                                                         @RequestBody @Valid LoginDto loginDto) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if (!usuarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        var usuarioModel = usuarioModelOptional.get();

        usuarioModel.setAutenticado(true);
        // caso seja necessário alterar todos.
        //usuarioModel.setComplemento(usuarioDto.getComplemento());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioModel));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> efetuarLogin(@RequestBody LoginDto credenciaisUser) {
        for (UsuarioModel usuarioModel : usuarioService.findAll()) {
            if (usuarioModel.getEmail().equals(credenciaisUser.getEmail())
                    && usuarioModel.getSenha().equals(credenciaisUser.getSenha())) {
                BeanUtils.copyProperties(credenciaisUser, usuarioModel);
                usuarioModel.setAutenticado(true);
                return ResponseEntity.status(200).body(credenciaisUser);
            }
        }
        return ResponseEntity.status(400).build();
    }

    @PatchMapping("/logoff/{id}")
    public ResponseEntity<Object> efetuarLogoff(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if (!usuarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        var usuarioModel = usuarioModelOptional.get();

        usuarioModel.setAutenticado(false);
        usuarioModel.setAutenticado(false);
        // caso seja necessário alterar todos.
        //usuarioModel.setComplemento(usuarioDto.getComplemento());
        usuarioService.save(usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModel.getNomeCompleto() + ", Logoff feito com sucesso!");
    }

}
