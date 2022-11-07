package com.renthouse.renthouse.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_negociacoes")
public class NegociacaoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 16)
    private UUID id;

    @Column
    private LocalDate dataInicio;

    @Column
    private LocalDate dataFim;

    @Column
    private Double valorEmprestimo;

    @Column
    private Double valorFrete;

    @ManyToOne
    private ItemModel idItem;

    @ManyToOne
    private UsuarioModel idProprietario;

    @ManyToOne
    private UsuarioModel idAlugador;

    @Column
    private LocalDateTime criadoEm;

    @Column
    private LocalDateTime atualizadoEm;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(Double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public ItemModel getIdItem() {
        return idItem;
    }

    public void setIdItem(ItemModel idItem) {
        this.idItem = idItem;
    }

    public UsuarioModel getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(UsuarioModel idProprietario) {
        this.idProprietario = idProprietario;
    }

    public UsuarioModel getIdAlugador() {
        return idAlugador;
    }

    public void setIdAlugador(UsuarioModel idAlugador) {
        this.idAlugador = idAlugador;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
