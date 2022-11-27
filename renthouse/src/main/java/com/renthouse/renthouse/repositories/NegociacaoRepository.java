package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.dtos.respostas.NegociacoesUsuario;
import com.renthouse.renthouse.models.NegociacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NegociacaoRepository extends JpaRepository<NegociacaoModel, UUID> {

    boolean existsById(UUID idNegociacao);

    @Query("select new " +
            " com.renthouse.renthouse.dtos.respostas.NegociacoesUsuario" +
            "(nm.id, nm.idItem.nome, nm.idItem.valorItem, nm.idItem.imagemUrl, nm.idItem.id, nm.idItem.usuarioModel.id) " +
            " from NegociacaoModel nm where nm.idAlugador.id = ?1")
    Optional<NegociacoesUsuario> getNegociacoesUsuario(UUID idUsuario);

}
