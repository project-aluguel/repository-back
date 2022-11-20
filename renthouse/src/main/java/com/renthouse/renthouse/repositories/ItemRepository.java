package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.dtos.respostas.DetalheItemCatalogo;
import com.renthouse.renthouse.dtos.respostas.ItensCatalogo;
import com.renthouse.renthouse.dtos.respostas.ItensUsuario;
import com.renthouse.renthouse.models.ItemModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
            "(im.id, im.nome, im.manualUso, im.dataInicio, im.dataFim, im.descricao, im.imagemUrl, im.categoria, im.valorItem, im.valorGarantia, im.alugado, im.entregaFrete, im.entregaPessoal ) " +
            " from ItemModel im where im.usuarioModel.id = ?1 ")
    List<ItensUsuario> getItensUsuario(UUID idUsuario);

    List<ItemModel> findItemModelByUsuarioModelId(UUID idUsuario);

    @Query("select new " +
            " com.renthouse.renthouse.dtos.respostas.ItensCatalogo" +
            "(im.id, im.nome, im.valorItem, im.imagemUrl,im.dataInicio, im.dataFim) " +
            " from ItemModel im where im.alugado = false and not im.usuarioModel.id = ?1")
    List<ItensCatalogo> getItensCatalogo(UUID idUsuario);

    @Query("select new " +
            " com.renthouse.renthouse.dtos.respostas.ItensCatalogo" +
            "(im.id, im.nome, im.valorItem, im.imagemUrl, im.dataInicio, im.dataFim) " +
            " from ItemModel im where im.alugado = false and im.categoria = ?1 and not im.usuarioModel.id = ?2")
    List<ItensCatalogo> getItensCatalogoPorCategoria(String categoria, UUID idUsuario);

    @Query("select new " +
            " com.renthouse.renthouse.dtos.respostas.ItensCatalogo" +
            "(im.id, im.nome, im.valorItem, im.imagemUrl, im.dataInicio, im.dataFim) " +
            " from ItemModel im where im.alugado = false and im.nome = ?1 and not im.usuarioModel.id = ?2")
    List<ItensCatalogo> getItensCatalogoPorNome(String nome, UUID idUsuario);

    @Query("select new " +
            " com.renthouse.renthouse.dtos.respostas.DetalheItemCatalogo" +
            "(im.id, im.nome, im.categoria, im.descricao, im.imagemUrl, im.manualUso, im.dataInicio, im.dataFim," +
            " im.valorGarantia, im.valorItem, im.alugado, im.usuarioModel.id, im.entregaFrete, im.entregaPessoal) " +
            " from ItemModel im where im.id = ?1")
    Optional<DetalheItemCatalogo> getDetalheItem(UUID idItem);

    boolean existsByUsuarioModelId(UUID idUsuario);

    @Query("select new " +
            " com.renthouse.renthouse.dtos.respostas.ItensCatalogo" +
            "(im.id, im.nome, im.valorItem, im.imagemUrl, im.dataInicio, im.dataFim) " +
            " from ItemModel im where im.alugado = false and not im.usuarioModel.id = ?1 order by im.criadoEm")
    List<ItensCatalogo> getItensRecentesCatalogo(UUID idUsuario, Pageable pageable);
}
