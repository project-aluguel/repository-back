package com.renthouse.renthouse.services;

import com.renthouse.renthouse.dtos.respostas.DetalheItemCatalogo;
import com.renthouse.renthouse.dtos.respostas.ItensCatalogo;
import com.renthouse.renthouse.dtos.respostas.ItensUsuario;
import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<ItemModel> getItensDeUsuarioVetor(UUID idUsuario) {
        return itemRepository.findItemModelByUsuarioModelId(idUsuario);
    }

    public boolean existsById(UUID idItem) {
        return itemRepository.existsById(idItem);
    }

    public boolean isAlugado(UUID idItem) {
        return findById(idItem).get().getAlugado();
    }

    public List<ItensCatalogo> buscaItensCatalogo(UUID idUsuario) {
        return itemRepository.getItensCatalogo(idUsuario);
    }

    public List<ItensCatalogo> buscaItensCatalogoPorCategoria(String categoria, UUID idUsuario) {
        return itemRepository.getItensCatalogoPorCategoria(categoria, idUsuario);
    }

    public List<ItensCatalogo> buscaItensCatalogoPorNome(String nome, UUID idUsuario) {
        return itemRepository.getItensCatalogoPorNome(nome, idUsuario);
    }

    public void atualizaItemParaEmprestado(UUID idItem) {
        ItemModel item = findById(idItem).get();
        item.setAlugado(true);
        itemRepository.save(item);
    }

    public Optional<DetalheItemCatalogo> buscaDetalheItem(UUID idItem) {
        return itemRepository.getDetalheItem(idItem);
    }

    public boolean existsByUsuarioId(UUID idUsuario) {
        return itemRepository.existsByUsuarioModelId(idUsuario);
    }

    public List<ItensCatalogo> buscaItensRecenteCatalogo(UUID idUsuario) {
        Pageable pageable = PageRequest.of(0,10);
        return itemRepository.getItensRecentesCatalogo(idUsuario, pageable);
    }
}
