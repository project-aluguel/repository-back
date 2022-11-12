package com.renthouse.renthouse.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "não existe carteira registrada com o ID do usuário informado")
public class CarteiraUsuarioNaoExiste extends RuntimeException {
}
