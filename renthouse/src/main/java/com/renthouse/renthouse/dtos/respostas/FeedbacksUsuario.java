package com.renthouse.renthouse.dtos.respostas;

import java.util.UUID;

public class FeedbacksUsuario {

    private UUID idFeedbackUsuario;
    private Double notaMediaUsuario;
    private int totalAvaliacoes;

    public UUID getIdFeedbackUsuario() {
        return idFeedbackUsuario;
    }

    public void setIdFeedbackUsuario(UUID idFeedbackUsuario) {
        this.idFeedbackUsuario = idFeedbackUsuario;
    }

    public Double getNotaMediaUsuario() {
        return notaMediaUsuario;
    }

    public void setNotaMediaUsuario(Double notaMediaUsuario) {
        this.notaMediaUsuario = notaMediaUsuario;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(int totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }
}
