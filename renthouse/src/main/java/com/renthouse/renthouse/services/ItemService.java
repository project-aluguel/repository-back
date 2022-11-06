package com.renthouse.renthouse.services;

import com.renthouse.renthouse.dtos.respostas.ItensUsuario;
import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<ItemModel> findAll() {
        return itemRepository.findAll();
    }

    @Transactional
    public ItemModel save(ItemModel itemModel) {
        return itemRepository.save(itemModel);
    }

    public Optional<ItemModel> findById(UUID id) {
        return itemRepository.findById(id);
    }

    @Transactional
    public void delete(ItemModel itemModel) {
        itemRepository.delete(itemModel);
    }

    public List<ItensUsuario> getItensDeUsuario(UUID idUsuario) {
        return itemRepository.getItensUsuario(idUsuario);
    }
}
