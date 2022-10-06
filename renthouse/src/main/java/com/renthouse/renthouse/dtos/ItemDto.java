package com.renthouse.renthouse.dtos;

import javax.validation.constraints.NotBlank;

public class ProdutosDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String manualUso;

    @NotBlank
    private Double valorGarantia;

    @NotBlank
    private Double valorItem;

    private Boolean alugado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getManualUso() {
        return manualUso;
    }

    public void setManualUso(String manualUso) {
        this.manualUso = manualUso;
    }

    public Double getValorGarantia() {
        return valorGarantia;
    }

    public void setValorGarantia(Double valorGarantia) {
        this.valorGarantia = valorGarantia;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
    }
}
