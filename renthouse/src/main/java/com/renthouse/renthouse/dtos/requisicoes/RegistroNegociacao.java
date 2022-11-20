package com.renthouse.renthouse.dtos.requisicoes;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public class RegistroNegociacao {

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dataFim;

    private Double valorEmprestimo;

    private Double valorFrete;

    private UUID idItem;

    private UUID idProprietario;

    private UUID idAlugador;

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public UUID getIdItem() {
        return idItem;
    }

    public UUID getIdProprietario() {
        return idProprietario;
    }

    public UUID getIdAlugador() {
        return idAlugador;
    }
}
