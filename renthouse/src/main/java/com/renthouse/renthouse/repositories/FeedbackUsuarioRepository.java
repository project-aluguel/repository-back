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
public interface FeedbackUsuarioRepository extends JpaRepository<FeedbackItemModel, Integer> {

    @Query("SELECT avg(fum.nota) FROM FeedbackUsuarioModel fum WHERE fum.usuarioModel.id = ?1")
    Optional<Double> getMediaFeedbackItem(int idUsuarioModel);

    @Query("SELECT avg(fum.nota) FROM FeedbackUsuarioModel fum WHERE " +
            " fum.usuarioModel.id = ?1 AND fum.dataHoraAvaliacao >= ?2 ")
    Optional<Double> getMediaAvaliacoes(int idUsuarioModel, LocalDateTime aPartirDe);

    @Query("select new " +
            " com.renthouse.renthouse.models.FeedbackUsuarioModel(fum.usuarioModel.nome, avg(fum.nota), count(fum.id)) " +
            " from FeedbackItemModel fum where fum.usuarioModel.id = ?1 " +
            " group by fum.usuarioModel.id")
    Optional<FeedbackUsuarioModel> getResumoFeedbackItem(int idUsuarioModel);

    @Query("select new " +
            " com.renthouse.renthouse.models.FeedbackUsuarioModel(fum.usuarioModel.nome, avg(fum.nota), count(fum.id)) " +
            " from FeedbackUsuarioModel fum where fum.usuarioModel.id = ?1 " +
            " group by fum.usuarioModel.id")
    Optional<FeedbackUsuarioModel> getResumoFeedbackItem(int idUsuarioModel, LocalDateTime aPartirDe);

    List<FeedbackUsuarioModel> findByUsuarioModelId(int usuarioModelId);

    List<FeedbackUsuarioModel> findByItemModelId(int itemModelId);
}
