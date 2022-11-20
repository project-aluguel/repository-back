package com.renthouse.renthouse.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Os ids informados não pertencem a negociacao em referencia")
public class NegociacaoIncoerente extends RuntimeException{
}
