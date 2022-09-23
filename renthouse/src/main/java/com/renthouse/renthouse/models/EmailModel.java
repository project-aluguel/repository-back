package com.renthouse.renthouse.models;

import com.renthouse.renthouse.enums.StatusEmail;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_email")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 16)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;

    private String referenciaProprietario;
    private String emailDe;
    private String emailPara;
    private String titulo;
    // column definition text consegue receber no banco string maior que 255.
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime enviarDataEmail;
    private StatusEmail statusEmail;

    public String getEmailDe() {
        return emailDe;
    }

    public String getReferenciaProprietario() {
        return referenciaProprietario;
    }

    public void setReferenciaProprietario(String referenciaProprietario) {
        this.referenciaProprietario = referenciaProprietario;
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

    public void setSubject(String titulo) {
        this.titulo = titulo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getEnviarDataEmail() {
        return enviarDataEmail;
    }

    public void setEnviarDataEmail(LocalDateTime enviarDataEmail) {
        this.enviarDataEmail = enviarDataEmail;
    }

    public StatusEmail getStatusEmail() {
        return statusEmail;
    }

    public void setStatusEmail(StatusEmail statusEmail) {
        this.statusEmail = statusEmail;
    }
}
