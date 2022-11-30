package com.renthouse.renthouse.dtos.respostas;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public class ItensUsuario {

    private UUID idItem;
    private String nome;
    private String manualUso;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;
    private String descricao;
    private String imagemUrl;
    private String categoria;
    private Double valorItem;
    private Double valorGarantia;
    private Boolean alugado;
    private Boolean entregaFrete;
    private Boolean entregaPessoal;

    public ItensUsuario() {
    }
    public ItensUsuario(
            UUID idItem,
            String nome,
            String manualUso,
            LocalDate dataInicio,
            LocalDate dataFim,
            String descricao,
            String imagemUrl,
            String categoria,
            Double valorItem,
            Double valorGarantia,
            Boolean alugado,
            Boolean entregaFrete,
            Boolean entregaPessoal
    ) {
        this.idItem = idItem;
        this.nome = nome;
        this.manualUso = manualUso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.categoria = categoria;
        this.valorItem = valorItem;
        this.valorGarantia = valorGarantia;
        this.alugado = alugado;
        this.entregaFrete = entregaFrete;
        this.entregaPessoal = entregaPessoal;
    }

    public UUID getIdItem() {
        return idItem;
    }

    public String getNome() {
        return nome;
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

    public String getDescricao() {
        return descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public String getCategoria() {
        return categoria;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public Double getValorGarantia() {
        return valorGarantia;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public Boolean getEntregaFrete() {
        return entregaFrete;
    }

    public Boolean getEntregaPessoal() {
        return entregaPessoal;
    }
}
