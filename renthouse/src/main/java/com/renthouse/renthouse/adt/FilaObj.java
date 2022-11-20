package com.renthouse.renthouse.adt;

import java.util.ArrayList;
import java.util.List;

public class FilaObj {

    private int tamanho;
    private String[] fila;

    public FilaObj(int capacidade) {
        tamanho = 0;
        fila = (String[]) new String[capacidade];
    }

    public Boolean isEmpty() {
        return tamanho == 0;
    }

    public Boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(String info) {
        if (isFull()) {
            throw new IllegalStateException();
        } else {
            fila[tamanho++] = info;
        }
    }

    public String peek() {
        return fila[0];
    }


    public String poll() {
        String primeiro = peek(); // salva o primeiro elemento da fila

        if (!isEmpty()) { // se a fila não está vazia
            // faz a fila andar
            for (int i = 0; i < tamanho - 1; i++) {
                fila[i] = fila[i + 1];
            }
            fila[tamanho - 1] = null;    // limpa o último cara da fila
            tamanho--;                 // decrementa tamanho
        }

        return primeiro;
    }

    public List<String> listaBuscas() {
        List<String> buscas = new ArrayList<>();
        if (isEmpty()) {
            System.out.println("A fila está vazia");
            return buscas;
        } else {
            for (int i = 0; i < getTamanho(); i++) {
                buscas.set(i, fila[i]);
            }
        }
        return buscas;
    }

    // Getters (não retirar)
    public String[] getFila() {
        return fila;
    }

    public int getTamanho() {
        return tamanho;
    }
}
