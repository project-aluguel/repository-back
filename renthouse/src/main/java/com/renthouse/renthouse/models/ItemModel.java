package com.renthouse.renthouse.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_itens")
public class ItemModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 16)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String manualUso;

    @Column(nullable = false)
    private Double valorGarantia;

    @Column(nullable = false)
    private Double valorItem;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }


    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
