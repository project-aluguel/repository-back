package com.renthouse.renthouse.dtos.respostas;

import java.util.UUID;

public class FeedbacksItem {

    private UUID idFeedbackItem;
    private Double notaMediaItem;
    private int totalAvaliacoes;

    public UUID getIdFeedbackItem() {
        return idFeedbackItem;
    }

    public void setIdFeedbackItem(UUID idFeedbackUsuario) {
        this.idFeedbackItem = idFeedbackUsuario;
    }

    public Double getNotaMediaItem() {
        return notaMediaItem;
    }

    public void setNotaMediaItem(Double notaMediaUsuario) {
        this.notaMediaItem = notaMediaUsuario;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(int totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }
}
