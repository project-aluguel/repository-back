package com.renthouse.renthouse.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "O usuário ja atingiu o limite (50 itens) de cadastro permitido")
public class LimiteItensAtingido extends RuntimeException{
}