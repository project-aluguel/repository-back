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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;
    private String ReferenciaProprietario;
    private String emailFrom;
    private String emailTo;
    private String subject;
    // column definition text consegue receber no banco string maior que 255.
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime enviarDataEmail;
    private StatusEmail statusEmail;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
