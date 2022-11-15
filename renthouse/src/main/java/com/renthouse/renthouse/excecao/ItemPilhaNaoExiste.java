package com.renthouse.renthouse.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item não existe na pilha")
public class ItemPilhaNaoExiste extends RuntimeException {
}
