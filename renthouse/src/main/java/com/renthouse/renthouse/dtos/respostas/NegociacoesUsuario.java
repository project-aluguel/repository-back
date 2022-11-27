package com.renthouse.renthouse.dtos.respostas;

import java.util.UUID;

public class NegociacoesUsuario {

    private UUID idNegociacao;

    private String nomeItem;

    private Double valorEmprestimo;

    private String imagemUrl;

    private UUID idItem;

    private UUID idProprietario;

    public NegociacoesUsuario(
            UUID idNegociacao,
            String nomeItem,
            Double valorEmprestimo,
            String imagemUrl,
            UUID idItem,
            UUID idProprietario
    ) {
        this.idNegociacao = idNegociacao;
        this.nomeItem = nomeItem;
        this.valorEmprestimo = valorEmprestimo;
        this.imagemUrl = imagemUrl;
        this.idItem = idItem;
        this.idProprietario = idProprietario;
    }

    public UUID getIdNegociacao() {
        return idNegociacao;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public Double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public UUID getIdItem() {
        return idItem;
    }

    public UUID getIdProprietario() {
        return idProprietario;
    }
}
