package com.renthouse.renthouse.dtos;

import javax.validation.constraints.NotBlank;

// Recebe dados do usuario
// faz a validações iniciais como se o campo pode ser enviado em branco.
// dados de entrada (input/post)
// não deixar o que o usuário não deve utilizar (id,dataRegistro,etc)
// para criar essas validações necessário a dependencia start validation.
// caso vc não coloque notblank (campo não pode ser em branco)...
// ...E coloque em model em @column que nullable = false, pode dar erro de dados se o usuário digitar em branco.

// json teste abaixo:
//{
//    "email" : "diego.lsilva@bandtec.com.br",
//     "senha" : "arrozfeijao123",
//        "nomeCompleto" : "Diego silva",
//        "cpf" : "040.860.070-53",
//        "telefone" : "11967197958",
//        "endereco" : "Rua dos portos largos - jardim peri - são paulo 1105",
//        "complemento" : "casa 13 (ultima casa da viela)"
//        }
public class UsuarioDto {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    private String nomeCompleto;
    @NotBlank
    private String cpf;
    @NotBlank
    private String telefone;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;

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

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
