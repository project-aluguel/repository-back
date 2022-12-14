package com.renthouse.renthouse.services;

import com.renthouse.renthouse.dtos.LoginDto;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// onde fica a regra de negócio
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<UsuarioModel> findAll() {
        return usuarioRepository.findAll();
    }

    // garante que em adição/deleção em cascata nada seja perdido
    @Transactional
    public UsuarioModel save(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public boolean existsByCpf(String cpf) {
        return usuarioRepository.existsByCpf(cpf);
    }

    public Optional<UsuarioModel> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

  
    @Transactional
    public void delete(UsuarioModel usuarioModel) {
         usuarioRepository.delete(usuarioModel);
    }


}
