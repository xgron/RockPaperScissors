package com.game.RockPaperScissors.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="A game with this ID does not exist!")
public class gameDoesNotExistException extends RuntimeException {
}
