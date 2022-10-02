package com.renthouse.renthouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedbackUsuarioRepository extends JpaRepository<FeedbackUsuarioRepository, UUID> {
}
