package com.renthouse.renthouse.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "tb_feedback_item")
public class FeedbackItemModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 16)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFeedbackItem;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private int avaliacao;

    @Column(nullable = false)
    private Integer fk_consumidor;

    @Column(nullable = false)
    private Integer fk_item;

    @Column(nullable = false)
    private Integer fk_proprietario;

    public UUID getIdFeedbackItem() {
        return idFeedbackItem;
    }

    public void setIdFeedbackItem(UUID idFeedbackItem) {
        this.idFeedbackItem = idFeedbackItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
}
