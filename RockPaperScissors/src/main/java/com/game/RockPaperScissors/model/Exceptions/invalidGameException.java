package com.game.RockPaperScissors.model.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="This game has expired and is no longer valid!")
public class invalidGameException extends RuntimeException {
}
