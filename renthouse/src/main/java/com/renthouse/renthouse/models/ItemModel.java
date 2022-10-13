package com.renthouse.renthouse.models;

import org.springframework.beans.BeanUtils;

import javax.persistence.*;
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
    private Boolean alugado;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = true)
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

    public Boolean getAlugado() {
        return alugado;
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
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

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", manualUso='" + manualUso + '\'' +
                ", valorGarantia=" + valorGarantia +
                ", valorItem=" + valorItem +
                ", alugado=" + alugado +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }

    public boolean comparar(Object objeto1, Object objeto2) {

        ItemModel item1 = new ItemModel();
        ItemModel item2 = new ItemModel();
        BeanUtils.copyProperties(objeto1, item1);
        BeanUtils.copyProperties(objeto2, item2);

        if (item1.getId().equals(item2.getId())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        //se nao forem objetos da mesma classe sao objetos diferentes
        if (!(obj instanceof ItemModel)) return false;

        //se nao tiverem o mesmo id, sao diferentes
        return ((ItemModel) obj).id == this.id;

    }
}
