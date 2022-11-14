package com.renthouse.renthouse.adt;

import com.renthouse.renthouse.excecao.ItemPilhaNaoExiste;
import com.renthouse.renthouse.excecao.LimiteItensPilhaAtingido;
import com.renthouse.renthouse.models.ItemModel;

public class PilhaItem {

    private ItemModel[] pilha;
    private int topo;

    public int getTopo() {
        return topo;
    }

    public PilhaItem(int capacidade) {
        this.pilha = new ItemModel[capacidade];
        this.topo = -1;
    }

    public ItemModel get() {
        if (!isEmpty()) {
            throw new ItemPilhaNaoExiste();
        }
        return pilha[topo];

    }

    public Boolean isEmpty() {
        return  this.topo == -1;
    }

    public Boolean isFull() {
        return this.topo +1 == pilha.length;
    }

    public void push(ItemModel info) {
        if(isFull()){
            throw new LimiteItensPilhaAtingido();
        } else {
            this.pilha[++topo] = info;
        }
    }

    public ItemModel pop() {
        if (!isEmpty()) {
            throw new ItemPilhaNaoExiste();
        }
        return pilha[topo--];

    }
}
