package com.renthouse.renthouse.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "O item já está alugado")
public class ItemAlugado extends RuntimeException {
}
