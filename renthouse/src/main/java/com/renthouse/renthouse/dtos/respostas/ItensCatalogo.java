package com.renthouse.renthouse.dtos.respostas;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public class ItensCatalogo {

    private UUID id;
    private String nome;
    private Double valorItem;
    private String imagemUrl;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    public ItensCatalogo(UUID id, String nome, Double valorItem, String imagemUrl, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.nome = nome;
        this.valorItem = valorItem;
        this.imagemUrl = imagemUrl;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
}
