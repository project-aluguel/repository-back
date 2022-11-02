package com.renthouse.renthouse.dtos;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class EnderecoDto {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numeroResidencia;
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String localidade;
    @NotBlank
    private String uf;
    @NotBlank
    private String ibge;
    @NotBlank
    private String gia;
    @NotBlank
    private String ddd;
    @NotBlank
    private String siafi;
    private UUID idUsuario;

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumeroResidencia() {
        return numeroResidencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public String getIbge() {
        return ibge;
    }

    public String getBairro() {
        return bairro;
    }

    public String getGia() {
        return gia;
    }

    public String getDdd() {
        return ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}