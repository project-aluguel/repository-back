package com.renthouse.renthouse.dtos.respostas;

public class FeedbacksUsuario {

    private Double notaMediaUsuario;
    private Long totalAvaliacoes;

    public FeedbacksUsuario(Double notaMediaUsuario, Long totalAvaliacoes) {
        this.notaMediaUsuario = notaMediaUsuario;
        this.totalAvaliacoes = totalAvaliacoes;
    }

    public Double getNotaMediaUsuario() {
        return notaMediaUsuario;
    }

    public Long getTotalAvaliacoes() {
        return totalAvaliacoes;
    }
}
