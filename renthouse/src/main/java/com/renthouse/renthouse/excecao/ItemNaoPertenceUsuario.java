package com.renthouse.renthouse.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "O item não pertence ao usuário referênciado")
public class ItemNaoPertenceUsuario extends RuntimeException {
}
