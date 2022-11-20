package com.renthouse.renthouse.dtos.respostas;

public class FeedbacksItem {

    private Double notaMediaItem;
    private Long totalAvaliacoes;

    public FeedbacksItem(Double notaMediaItem, Long totalAvaliacoes) {
        this.notaMediaItem = notaMediaItem;
        this.totalAvaliacoes = totalAvaliacoes;
    }

    public Double getNotaMediaItem() {
        return notaMediaItem;
    }

    public Long getTotalAvaliacoes() {
        return totalAvaliacoes;
    }
}