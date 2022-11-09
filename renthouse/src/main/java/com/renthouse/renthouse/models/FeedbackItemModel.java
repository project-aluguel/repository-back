package com.renthouse.renthouse.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
public class FeedbackItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime dataHoraAvaliacao;

    @Min(1)
    @Max(5)
    private Integer nota;

    @ManyToOne
    private ItemModel itemModel;

    @ManyToOne
    private UsuarioModel usuarioModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraAvaliacao() {
        return dataHoraAvaliacao;
    }

    public void setDataHoraAvaliacao(LocalDateTime dataHoraAvaliacao) {
        this.dataHoraAvaliacao = dataHoraAvaliacao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public ItemModel getItemModel() {
        return itemModel;
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }
}
