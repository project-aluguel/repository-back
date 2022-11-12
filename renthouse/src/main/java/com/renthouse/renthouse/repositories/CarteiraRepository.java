package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.models.CarteiraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarteiraRepository extends JpaRepository<CarteiraModel, UUID> {
}
