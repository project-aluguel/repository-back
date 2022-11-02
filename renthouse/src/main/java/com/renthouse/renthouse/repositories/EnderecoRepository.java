package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.models.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, UUID> {

    List<EnderecoModel> findEnderecoModelByUsuarioModelId(UUID idUsuario);

    void deleteEnderecoModelById(UUID idEndereco);

    EnderecoModel findEnderecoModelById(UUID idEndereco);

    boolean existsById(UUID idEndereco);

}
