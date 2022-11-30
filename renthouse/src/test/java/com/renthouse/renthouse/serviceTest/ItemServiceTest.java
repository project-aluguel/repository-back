package com.renthouse.renthouse.serviceTest;

import com.renthouse.renthouse.dtos.respostas.ItensUsuario;
import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.repositories.ItemRepository;
import com.renthouse.renthouse.repositories.UsuarioRepository;
import com.renthouse.renthouse.services.ItemService;
import com.renthouse.renthouse.services.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;


    @Test
    @DisplayName("Deve retornar um item model quando passado um uuid")
    void retornaItemModelComFindById(){
        UUID uuid = UUID.fromString("92db6929-3705-4dbf-9471-f1aed152b492");
        Mockito.when(itemRepository.findById(uuid)).thenReturn(Optional.of(new ItemModel()));
        assertEquals(new ItemModel().getClass(), itemService.findById(uuid).get().getClass());
    }

    @Test
    @DisplayName("Recebe uuid e retorna uma lista vazia de ItensUsuario")
    void retornaListaDeItensDoUsuario(){
        UUID uuid = UUID.fromString("92db6929-3705-4dbf-9471-f1aed152b492");
        Mockito.when(itemRepository.getItensUsuario(uuid)).thenReturn(List.of(
        ));
        List<ItensUsuario> teste = new ArrayList<>();
        assertEquals(teste, itemService.getItensDeUsuario(uuid));
    }

    @Test
    @DisplayName("Retorna todos os itens cadastrados")
    void retornaListaComTodosOsItens(){
        Mockito.when(itemRepository.findAll()).thenReturn(List.of(
            new ItemModel()
                ));
        List<ItemModel> teste = new ArrayList<>();
        ItemModel newItem = new ItemModel();
        teste.add(newItem);
        assertEquals(teste, itemService.findAll());
    }

    @Test
    @DisplayName("Não retorna nenhum item")
    void retornaListaZerada(){
        Mockito.when(itemRepository.findAll()).thenReturn(List.of(

        ));
        List<ItemModel> teste = new ArrayList<>();
        assertEquals(teste, itemService.findAll());
    }

//    @Test
//    @DisplayName("retorna true para item alugado")
//    void retornaIsAlugado() {
//        UUID uuid = UUID.fromString("92db6929-3705-4dbf-9471-f1aed152b492");
//        Mockito.when(itemRepository.findById(uuid).get().getAlugado()).thenReturn(true);
//        assertEquals(true, itemService.isAlugado(uuid));
//    }







}
