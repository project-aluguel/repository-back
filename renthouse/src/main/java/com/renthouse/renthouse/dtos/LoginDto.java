package com.renthouse.renthouse.dtos;

import com.renthouse.renthouse.models.UsuarioModel;

import javax.validation.constraints.NotBlank;
//{
//    "email" : "rodrigo.faro@bandtec.com.br",
//     "senha" : "vaidarnamoro123"
//        }
public class LoginDto {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    private boolean isLogado;

    public void setIsLogado(boolean logado) {
        isLogado = logado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
