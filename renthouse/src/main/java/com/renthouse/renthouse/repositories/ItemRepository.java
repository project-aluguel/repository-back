package com.renthouse.renthouse.repositories;

import com.renthouse.renthouse.dtos.ItemDto;
import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ItemRepository extends JpaRepository<ItemModel, UUID> {

//    public void adiciona(ItemDto elemento);

}
