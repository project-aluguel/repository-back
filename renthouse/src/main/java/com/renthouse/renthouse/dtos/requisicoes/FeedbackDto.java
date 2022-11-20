package com.renthouse.renthouse.dtos.requisicoes;

import java.util.UUID;

public class FeedbackDto {

    private UUID idNegociacao;
    private UUID idProprietario;
    private UUID idAvaliador;
    private UUID idItem;
    private Double notaProprietario;
    private Double notaItem;

    public UUID getIdNegociacao() {
        return idNegociacao;
    }

    public UUID getIdProprietario() {
        return idProprietario;
    }

    public UUID getIdAvaliador() {
        return idAvaliador;
    }

    public UUID getIdItem() {
        return idItem;
    }

    public Double getNotaProprietario() {
        return notaProprietario;
    }

    public Double getNotaItem() {
        return notaItem;
    }
}
