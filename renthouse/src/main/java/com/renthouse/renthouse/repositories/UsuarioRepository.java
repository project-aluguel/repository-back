package com.renthouse.renthouse.repositories;

// deve passar a model (onde fica o mapeamento de todas as colunas)
// e o identificador da tabela
import com.renthouse.renthouse.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

}
