package com.renthouse.renthouse.dtos;

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

    private Double valorGarantia;

    private Double valorItem;

    private Boolean alugado;

    private UUID idUsuario;

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

    public Double getValorItem() {
        return valorItem;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

}
