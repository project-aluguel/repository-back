package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.dtos.respostas.FeedbacksItem;
import com.renthouse.renthouse.dtos.respostas.FeedbacksUsuario;
import com.renthouse.renthouse.models.FeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackModel, UUID> {

    @Query("select new " +
            "com.renthouse.renthouse.dtos.respostas.FeedbacksUsuario" +
            "(avg(fm.notaProprietario), count(fm.notaProprietario))" +
            "from FeedbackModel fm where fm.idProprietario = ?1")
    FeedbacksUsuario getFeedbacksUsuario(UUID idUsuario);

    @Query("select new " +
            "com.renthouse.renthouse.dtos.respostas.FeedbacksItem" +
            "(avg(fm.notaProduto), count(fm.notaProduto))" +
            "from FeedbackModel fm where fm.idItem = ?1")
    FeedbacksItem getFeedbacksItem(UUID idItem);

}
