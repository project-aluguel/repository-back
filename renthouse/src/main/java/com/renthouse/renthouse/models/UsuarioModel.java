package com.renthouse.renthouse.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

// model deve se colocar entity, dizendo é que é uma entidade do banco de dados.
@Entity
// nome da tabela que ira ser gerada no banco
// serializable são converções que ocorrem por baixo dos panos da jvm
@Table(name = "usuario")
public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 16)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Boolean autenticado = false;

    @Column(nullable = false)
    private boolean isLogado = false;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, unique = false, length = 45)
    private String senha;

    @Column(nullable = false, unique = false, length = 45)
    private String nomeCompleto;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = false, length = 11)
    private String telefone;

    @Column(nullable = false, unique = false, length = 100)
    private String endereco;

    @Column(nullable = false, unique = false, length = 70)
    private String complemento;

    @Column(nullable = false, unique = false, length = 70)
    private LocalDateTime dataCriacaoConta;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getDataCriacaoConta() {
        return dataCriacaoConta;
    }

    public void setDataCriacaoConta(LocalDateTime dataCriacaoConta) {
        this.dataCriacaoConta = dataCriacaoConta;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public void setIsLogado(Boolean logado) {this.isLogado = logado;}
    public boolean ngetLogado() {
        return isLogado;
    }
}
