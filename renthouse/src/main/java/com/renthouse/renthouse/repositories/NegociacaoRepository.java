package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.models.NegociacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NegociacaoRepository extends JpaRepository<NegociacaoModel, UUID> {

    boolean existsById(UUID idNegociacao);

}
