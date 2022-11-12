package com.renthouse.renthouse.models;

import org.springframework.beans.BeanUtils;

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

    @Column
    private Boolean autenticado = false;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private String nomeCompleto;

    @Column
    private LocalDate dataNascimento;

    @Column
    private String cpf;

    @Column
    private String telefone;

    @Column
    private LocalDateTime criadoEm;

    @Column
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

    @Override
    public boolean equals(Object obj) {
        //se nao forem objetos da mesma classe sao objetos diferentes
        if (!(obj instanceof UsuarioModel)) return false;

        //se nao tiverem o mesmo id, sao diferentes
        return ((UsuarioModel) obj).id == this.id;

    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id=" + id +
                ", autenticado=" + autenticado +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", criadoEm=" + criadoEm +
                ", atualizadoEm=" + atualizadoEm +
                '}';
    }
}
