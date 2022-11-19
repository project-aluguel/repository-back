package com.renthouse.renthouse.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_feedback")
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
    private UUID negociacaoId;

    private UsuarioModel avaliadorId;

    private UsuarioModel proprietarioId;

    private ItemModel itemId;

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

    public UUID getNegociacaoId() {
        return negociacaoId;
    }

    public void setNegociacaoId(UUID negociacaoId) {
        this.negociacaoId = negociacaoId;
    }

    public UsuarioModel getAvaliadorId() {
        return avaliadorId;
    }

    public void setAvaliadorId(UsuarioModel avaliadorId) {
        this.avaliadorId = avaliadorId;
    }

    public UsuarioModel getProprietarioId() {
        return proprietarioId;
    }

    public void setProprietarioId(UsuarioModel proprietarioId) {
        this.proprietarioId = proprietarioId;
    }

    public ItemModel getItemId() {
        return itemId;
    }

    public void setItemId(ItemModel itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "FeedbackModel{" +
                "id=" + id +
                ", notaProprietario=" + notaProprietario +
                ", notaProduto=" + notaProduto +
                ", negociacaoId=" + negociacaoId +
                ", avaliadorId=" + avaliadorId +
                ", proprietarioId=" + proprietarioId +
                ", itemId=" + itemId +
                '}';
    }
}
