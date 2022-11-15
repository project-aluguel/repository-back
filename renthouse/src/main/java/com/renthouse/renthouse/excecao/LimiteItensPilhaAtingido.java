package com.renthouse.renthouse.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "O espaço ocupante da pilha esta completo")
public class LimiteItensPilhaAtingido extends RuntimeException{
}
