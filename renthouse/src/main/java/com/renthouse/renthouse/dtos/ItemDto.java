package com.renthouse.renthouse.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ItemDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String manualUso;



    private Double valorGarantia;


    private Double valorItem;

    private Boolean alugado;

//    @NotBlank
//    private LocalDateTime dataCriacao;

//    @NotBlank
//    private LocalDateTime dataAtualizacao;

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

//    public LocalDateTime getDataCriacao() {
//        return dataCriacao;
//    }
//
//    public void setDataCriacao(LocalDateTime dataCriacao) {
//        this.dataCriacao = dataCriacao;
//    }
//
//    public LocalDateTime getDataAtualizacao() {
//        return dataAtualizacao;
//    }
//
//    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
//        this.dataAtualizacao = dataAtualizacao;
//    }
}
