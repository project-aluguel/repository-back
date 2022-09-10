package com.renthouse.renthouse.controllers;

// solicita para que a service execute a regra de negócio do endpoint
// recebe os dados de dto e retorna para o usuario

import com.renthouse.renthouse.dtos.LoginDto;
import com.renthouse.renthouse.dtos.UsuarioDto;
import com.renthouse.renthouse.models.UsuarioModel;
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

    // @valid é o responsavel por fazer a leitura das anotações de dto, como de notblank por exemplo
    // sem ele o spring ignora as anotações em dto.


    @PostMapping("/criar-usuario")
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){

        if(usuarioService.existsByEmail(usuarioDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: conta com email já criado!");
        }

        if(usuarioService.existsByCpf(usuarioDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: conta com esse cpf já criada!");
        }

        UsuarioModel usuarioModel = new UsuarioModel();
        // faz a conversão de dto para model
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        // setando data que n foi definida pelo dto (id n pq é gerado automaticamente)
        usuarioModel.setDataCriacaoConta(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllUsuario(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
        usuarioService.delete(usuarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
    }

    @PatchMapping("/autenticar/{id}")
    public ResponseEntity<Object> patchAutenticarUsuario(@PathVariable(value = "id") UUID id,
                                                              @RequestBody @Valid LoginDto loginDto){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        var usuarioModel = usuarioModelOptional.get();

        usuarioModel.setAutenticado(true);
        // caso seja necessário alterar todos.
        //usuarioModel.setComplemento(usuarioDto.getComplemento());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioModel));
    }

    @GetMapping("/login")
    public String efetuarLogin( @RequestBody LoginDto loginDto) {
        for (UsuarioModel usuarioModel : usuarioService.findAll()) {
            if(usuarioModel.getEmail().equals(loginDto.getEmail()) && usuarioModel.getSenha().equals(loginDto.getSenha())){
                BeanUtils.copyProperties(loginDto, usuarioModel);
                usuarioModel.setIsLogado(true);
                return "Login efetuado com sucesso";
            }
        }
        return "Email ou senha inválidos";
    }

    @PatchMapping("/logoff/{id}")
    public ResponseEntity<Object> efetuarLogoff(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        var usuarioModel = usuarioModelOptional.get();
//        if (!usuarioModel.ngetLogado()){
//            return ResponseEntity.status(HttpStatus.OK).body(usuarioModel.getNomeCompleto() + " não esta logado");
//        }


        usuarioModel.setIsLogado(false);
        // caso seja necessário alterar todos.
        //usuarioModel.setComplemento(usuarioDto.getComplemento());
        usuarioService.save(usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModel.getNomeCompleto() + ", Logoff feito com sucesso!");
    }



}
