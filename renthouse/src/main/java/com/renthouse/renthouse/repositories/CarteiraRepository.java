package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.dtos.respostas.CarteiraUsuario;
import com.renthouse.renthouse.dtos.respostas.ItensCatalogo;
import com.renthouse.renthouse.models.CarteiraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarteiraRepository extends JpaRepository<CarteiraModel, UUID> {

    @Query("select new " +
            " com.renthouse.renthouse.dtos.respostas.CarteiraUsuario" +
            "(cm.id, cm.saldo, cm.usuarioModel.id) " +
            " from CarteiraModel cm where cm.usuarioModel.id = ?1")
    CarteiraUsuario getCarteria(UUID idUsuario);

    Boolean existsByUsuarioModelId(UUID idUsuario);
}
