package com.renthouse.renthouse.dtos.respostas;

import java.util.UUID;

public class ItensCatalogo {

    private UUID id;
    private String nome;
    private Double valorItem;

    private String imagemUrl;

    public ItensCatalogo(UUID id, String nome, Double valorItem, String imagemUrl) {
        this.id = id;
        this.nome = nome;
        this.valorItem = valorItem;
        this.imagemUrl = imagemUrl;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }
}
