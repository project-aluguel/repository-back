package com.renthouse.renthouse.dtos.requisicoes;

import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.UUID;

public class ItemDto {

    private String nome;

    private String categoria;

    private String descricao;

    private String manualUso;
    private String imagem;

    private Double valorGarantia;

    private Double valorItem;

    private Boolean alugado;

    private UUID idUsuario;

    private Boolean entregaFrete;

    private Boolean entregaPessoal;

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getManualUso() {
        return manualUso;
    }

    public Double getValorGarantia() {
        return valorGarantia;
    }

    public String getImagem() {
        return imagem;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public Boolean getEntregaFrete() {
        return entregaFrete;
    }

    public Boolean getEntregaPessoal() {
        return entregaPessoal;
    }
}