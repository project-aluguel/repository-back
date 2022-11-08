package com.renthouse.renthouse.dtos.respostas;

import java.util.UUID;

public class ItensCatalogo {

    private UUID id;
    private String nome;
    private Double valorItem;

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getValorItem() {
        return valorItem;
    }
}
