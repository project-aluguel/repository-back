package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.models.FeedbackItemModel;
import com.renthouse.renthouse.models.FeedbackUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackItemRepository extends JpaRepository<FeedbackItemModel, Integer> {

    @Query("SELECT avg(fim.nota) FROM FeedbackItemModel fim WHERE fim.itemModel.id = ?1")
    Optional<Double> getMediaFeedbackItem(int idItemModel);

    @Query("SELECT avg(fim.nota) FROM AvaliacaoMotorista fim WHERE " +
            " fim.itemModel.id = ?1 AND fim.dataHoraAvaliacao >= ?2 ")
    Optional<Double> getMediaAvaliacoes(int iditemModel, LocalDateTime aPartirDe);

    @Query("select new " +
            " com.renthouse.renthouse.models.FeedbackItemModel(fim.itemModel.nome, avg(fim.nota), count(fim.id)) " +
            " from FeedbackItemModel fim where fim.itemModel.id = ?1 " +
            " group by fim.itemModel.id")
    Optional<FeedbackItemModel> getResumoFeedbackItem(int idItemModel);

    @Query("select new " +
            " com.renthouse.renthouse.models.FeedbackItemModel(fim.itemModel.nome, avg(fim.nota), count(fim.id)) " +
            " from FeedbackItemModel fim where fim.itemModel.id = ?1 " +
            " group by fim.itemModel.id")
    Optional<FeedbackItemModel> getResumoFeedbackItem(int idItemModel, LocalDateTime aPartirDe);

    List<FeedbackItemModel> findByItemModelId(int itemModelId);

    List<FeedbackItemModel> findByUsuarioModelId(int usuarioModelId);
}
