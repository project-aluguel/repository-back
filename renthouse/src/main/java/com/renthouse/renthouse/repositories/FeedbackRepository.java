package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.dtos.respostas.FeedbacksUsuario;
import com.renthouse.renthouse.dtos.respostas.ItensUsuario;
import com.renthouse.renthouse.models.FeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackModel, UUID> {

    @Query("select avg(fm.notaProprietario) from FeedbackModel fm where fm.proprietarioId.id = ?1")
        // @Query("SELECT AVG(am.nota) FROM AvaliacaoMotorista am WHERE am.motorista.id = ?1")
    Double getMediaAvaliacoesUsuario(UUID id);

    @Query("select avg(fm.notaProduto) from FeedbackModel fm where fm.itemId.id = ?1")
        // @Query("SELECT AVG(am.nota) FROM AvaliacaoMotorista am WHERE am.motorista.id = ?1")
    Double getMediaAvaliacoesItem(UUID id);

    @Query("select count(fm.notaProprietario) from FeedbackModel fm where fm.proprietarioId.id = ?1")
        // @Query("SELECT AVG(am.nota) FROM AvaliacaoMotorista am WHERE am.motorista.id = ?1")
    int getTotalAvaliacoesUsuario(UUID id);

    @Query("select count(fm.notaProduto) from FeedbackModel fm where fm.itemId.id = ?1")
        // @Query("SELECT AVG(am.nota) FROM AvaliacaoMotorista am WHERE am.motorista.id = ?1")
    int getTotalAvaliacoesItem(UUID id);

}
