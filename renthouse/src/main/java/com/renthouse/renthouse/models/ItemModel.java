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

    @Column
    private String nome;

    @Column
    private String categoria;

    @Column
    private String descricao;

    @Column
    private String manualUso;

    @Column
    private String imagem;

    @Column
    private Double valorGarantia;

    @Column
    private Double valorItem;

    @Column
    private Boolean alugado;

    @Column
    private Boolean entregaFrete;

    @Column
    private Boolean entregaPessoal;

    @Column
    private LocalDateTime criadoEm;

    @Column
    private LocalDateTime atualizadoEm;

    @ManyToOne
    private UsuarioModel usuarioModel;

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public Boolean getEntregaFrete() {
        return entregaFrete;
    }

    public void setEntregaFrete(Boolean entregaFrete) {
        this.entregaFrete = entregaFrete;
    }

    public Boolean getEntregaPessoal() {
        return entregaPessoal;
    }

    public void setEntregaPessoal(Boolean entregaPessoal) {
        this.entregaPessoal = entregaPessoal;
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


    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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
