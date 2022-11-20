package com.renthouse.renthouse.dtos.respostas;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public class DetalheItemCatalogo {

    private UUID id;

    private String nome;

    private String categoria;

    private String descricao;

    private String imagemUrl;

    private String manualUso;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    private Double valorGarantia;

    private Double valorItem;

    private Boolean alugado;

    private UUID idProprietario;

    private Boolean entregaFrete;

    private Boolean entregaPessoal;

    public DetalheItemCatalogo(
            UUID id,
            String nome,
            String categoria,
            String descricao,
            String imagemUrl,
            String manualUso,
            LocalDate dataInicio,
            LocalDate dataFim,
            Double valorGarantia,
            Double valorItem,
            Boolean alugado,
            UUID idProprietario,
            Boolean entregaFrete,
            Boolean entregaPessoal
    ) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.manualUso = manualUso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorGarantia = valorGarantia;
        this.valorItem = valorItem;
        this.alugado = alugado;
        this.idProprietario = idProprietario;
        this.entregaFrete = entregaFrete;
        this.entregaPessoal = entregaPessoal;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public String getManualUso() {
        return manualUso;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Double getValorGarantia() {
        return valorGarantia;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public UUID getIdProprietario() {
        return idProprietario;
    }

    public Boolean getEntregaFrete() {
        return entregaFrete;
    }

    public Boolean getEntregaPessoal() {
        return entregaPessoal;
    }

}

