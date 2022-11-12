package com.renthouse.renthouse.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Este usuário não possui carteira")
public class CarteiraUsuarioNaoExiste extends RuntimeException {
}
