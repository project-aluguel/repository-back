package com.renthouse.renthouse.dtos;

import com.renthouse.renthouse.enums.StatusEmail;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
// @Email verifica se é um email valido.

//    {
//        "referenciaProprietario" : "Diego",
//        "emailDe" : "lepoldino2@gmail.com",
//        "emailPara" : "lepoldino2@gmail.com",
//        "titulo" : "Titulo nesse email",
//        "text" : "Titulo nesse email Titulo nesse email Titulo nesse email Titulo nesse email Titulo nesse email",
//    }
public class EmailDto {

    @NotBlank
    private String referenciaProprietario;
    @NotBlank
    @Email
    private String emailDe;
    @NotBlank
    @Email
    private String emailPara;
    @NotBlank
    private String titulo;
    @NotBlank
    private String text;

    public String getReferenciaProprietario() {
        return referenciaProprietario;
    }

    public void setReferenciaProprietario(String referenciaProprietario) {
        this.referenciaProprietario = referenciaProprietario;
    }

    public String getEmailDe() {
        return emailDe;
    }

    public void setEmailDe(String emailDe) {
        this.emailDe = emailDe;
    }

    public String getEmailPara() {
        return emailPara;
    }

    public void setEmailPara(String emailPara) {
        this.emailPara = emailPara;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
