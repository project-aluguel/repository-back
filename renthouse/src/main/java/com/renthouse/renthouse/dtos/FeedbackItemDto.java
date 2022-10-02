package com.renthouse.renthouse.dtos;

import javax.validation.constraints.NotBlank;

public class FeedbackItemDto {

    @NotBlank
    private String descricao;

    @NotBlank
    private int avaliacao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
}
