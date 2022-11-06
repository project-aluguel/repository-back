package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.dtos.respostas.ItensUsuario;
import com.renthouse.renthouse.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, UUID> {

    @Query("select new " +
            " com.renthouse.renthouse.dtos.respostas.ItensUsuario" +
            "(im.id, im.nome, im.manualUso, im.descricao, im.categoria," +
            " im.valorItem, im.valorGarantia, im.alugado, im.entregaFrete, im.entregaPessoal ) " +
            " from ItemModel im where im.usuarioModel.id = ?1 ")
    List<ItensUsuario> getItensUsuario(UUID idUsuario);

    List<ItemModel> findItemModelByUsuarioModelId(UUID idUsuario);

}
