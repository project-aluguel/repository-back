package com.renthouse.renthouse.dtos.requisicoes;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class UsuarioDto {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    private String nomeCompleto;
    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotBlank
    private String cpf;
    @NotBlank
    private String telefone;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

}
