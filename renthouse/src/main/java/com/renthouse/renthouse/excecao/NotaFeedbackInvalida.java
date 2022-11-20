package com.renthouse.renthouse.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "A nota tem que ser entre 1 e 5")
public class NotaFeedbackInvalida extends RuntimeException {
}
