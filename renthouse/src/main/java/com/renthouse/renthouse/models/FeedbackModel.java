package com.renthouse.renthouse.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_feedbacks")
public class FeedbackModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 16)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private Double notaProprietario;

    @Column
    private Double notaProduto;

    @OneToOne
    private NegociacaoModel idNegociacao;

    @Column(length = 16)
    private UUID idProprietario;

    @Column(length = 16)
    private UUID idAvaliador;

    @Column(length = 16)
    private UUID idItem;

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

    public Double getNotaProprietario() {
        return notaProprietario;
    }

    public void setNotaProprietario(Double notaProprietario) {
        this.notaProprietario = notaProprietario;
    }

    public Double getNotaProduto() {
        return notaProduto;
    }

    public void setNotaProduto(Double notaProduto) {
        this.notaProduto = notaProduto;
    }

    public NegociacaoModel getIdNegociacao() {
        return idNegociacao;
    }

    public void setIdNegociacao(NegociacaoModel idNegociacao) {
        this.idNegociacao = idNegociacao;
    }

    public UUID getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(UUID idProprietario) {
        this.idProprietario = idProprietario;
    }

    public UUID getIdAvaliador() {
        return idAvaliador;
    }

    public void setIdAvaliador(UUID idAvaliador) {
        this.idAvaliador = idAvaliador;
    }

    public UUID getIdItem() {
        return idItem;
    }

    public void setIdItem(UUID idItem) {
        this.idItem = idItem;
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
