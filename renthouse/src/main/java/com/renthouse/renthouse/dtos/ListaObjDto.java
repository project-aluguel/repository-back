package com.renthouse.renthouse.dtos;

import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.models.UsuarioModel;

public class ListaObjDto<T> {

    private T[] vetor;

    private int nroElem;

    public ListaObjDto(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            throw new IllegalStateException();
        } else {
            vetor[nroElem++] = elemento;
        }
    }

    public int busca(T elementoBuscado) {

        ItemModel comparador = new ItemModel();

        for (int i = 0; i < nroElem; i++) {

            if (comparador.comparar(vetor[i], elementoBuscado)) {
                return i;
            }
        }
        return -1;

    }

    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return false;
        }

        // Loop para "deslocar para a esquerda" os elementos do vetor
        // sobrescrevendo o elemento removido
        for (int i = indice; i < nroElem - 1; i++) {
            vetor[i] = vetor[i + 1];
        }

        nroElem--;
        return true;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        } else {
            return vetor[indice];
        }
    }

    public void limpa() {
        for (int i = 0; i < nroElem; i++) {
            vetor[i] = null;
        }
        nroElem = 0;
    }

    public Object[] exibe() {
        if (nroElem == 0) {
            return null;
        }

        T[] vetorNovo = (T[]) new Object[getTamanho()];
        for (int i = 0; i < nroElem; i++) {
            vetorNovo[i] = vetor[i];
        }
        return vetorNovo;

    }

    public void ordenaArray(ItemModel[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                ItemModel var1 = array[j - 1];
                ItemModel var2 = array[j];
                if (var1.getValorItem() > var2.getValorItem()) {
                    array[j] = var1;
                    array[j - 1] = var2;
                }
            }
        }
    }

    public void atualiza(int indice, T itemAtualizado) {
        vetor[indice] = itemAtualizado;
    }

    public T[] getVetor() {
        return vetor;
    }
}


