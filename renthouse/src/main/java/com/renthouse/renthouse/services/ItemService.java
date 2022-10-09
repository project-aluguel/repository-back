package com.renthouse.renthouse.services;

import com.renthouse.renthouse.dtos.ItemDto;
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

    // garante que em adição/deleção em cascata nada seja perdido
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

//    public void adiciona(ItemModel elemento, int nroElem, ItemModel[] vetor) {
//        if (nroElem >= vetor.length) {
//            throw new IllegalStateException();
//        }
//        else {
//            vetor[nroElem++] = elemento;
//        }
//    }
//
//    public int busca(ItemModel elemento, int nroElem, ItemModel[] vetor) {
//        for (int i = 0; i < nroElem; i++) {
//            if (vetor[i].equals(elemento)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public boolean removePeloIndice (int indice, int nroElem, ItemModel[] vetor) {
//        if (indice < 0 || indice >= nroElem) {
//            System.out.println("\nÍndice inválido!");
//            return false;
//        }
//
//        for (int i = indice; i < nroElem-1; i++) {
//            vetor[i] = vetor[i+1];
//        }
//
//        nroElem--;
//        return true;
//    }
//
//    public boolean removeElemento(ItemModel elemento, int nroElem, ItemModel[] vetor) {
//        return removePeloIndice(busca(elemento, nroElem, vetor), nroElem, vetor);
//    }
//
//    public int getTamanho(int nroElem) {
//        return nroElem;
//    }
//
//    public ItemModel getElemento(int indice, int nroElem, ItemModel[] vetor) {
//        if (indice < 0 || indice >= nroElem) {
//            return null;
//        }
//        else {
//            return vetor[indice];
//        }
//    }
//



}
