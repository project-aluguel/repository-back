package com.renthouse.renthouse.dtos.respostas;

import java.util.UUID;

public class ItensUsuario {

    private UUID idItem;
    private String nome;
    private String imagem;
    private String manualUso;
    private String descricao;
    private String categoria;
    private Double valorItem;
    private Double valorGarantia;
    private Boolean alugado;
    private Boolean entregaFrete;
    private Boolean entregaPessoal;

    public ItensUsuario(
            UUID idItem,
            String nome,
            String imagem,
            String manualUso,
            String descricao,
            String categoria,
            Double valorItem,
            Double valorGarantia,
            Boolean alugado,
            Boolean entregaFrete,
            Boolean entregaPessoal
    ) {
        this.idItem = idItem;
        this.nome = nome;
        this.imagem = imagem;
        this.manualUso = manualUso;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getImagem() {
        return imagem;
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
