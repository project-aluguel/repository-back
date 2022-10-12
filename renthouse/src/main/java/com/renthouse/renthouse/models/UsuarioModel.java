package com.renthouse.renthouse.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

// model deve se colocar entity, dizendo é que é uma entidade do banco de dados.
@Entity
// nome da tabela que ira ser gerada no banco
// serializable são converções que ocorrem por baixo dos panos da jvm
@Table(name = "tb_usuarios")
public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 16)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Boolean autenticado = false;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 45)
    private String senha;

    @Column(nullable = false, length = 45)
    private String nomeCompleto;

    @Column(nullable = false, length = 10)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(nullable = false, length = 70)
    private LocalDateTime criadoEm;

    @Column(nullable = true, length = 70)
    private LocalDateTime atualizadoEm;

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

}
