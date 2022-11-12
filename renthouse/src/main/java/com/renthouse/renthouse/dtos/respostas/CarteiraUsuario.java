package com.renthouse.renthouse.dtos.respostas;

import java.util.UUID;

public class CarteiraUsuario {

    private UUID idCarteira;
    private Double saldoCarteira;
    private UUID idUsuario;

    public CarteiraUsuario(UUID idCarteira, Double saldoCarteira, UUID idUsuario) {
        this.idCarteira = idCarteira;
        this.saldoCarteira = saldoCarteira;
        this.idUsuario = idUsuario;
    }

    public UUID getIdCarteira() {
        return idCarteira;
    }

    public Double getSaldoCarteira() {
        return saldoCarteira;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }
}
