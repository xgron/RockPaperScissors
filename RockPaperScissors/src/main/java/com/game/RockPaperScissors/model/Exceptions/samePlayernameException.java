package com.game.RockPaperScissors.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Two players can not have the same name! Please choose a different name!")
public class samePlayernameException extends RuntimeException {
}
