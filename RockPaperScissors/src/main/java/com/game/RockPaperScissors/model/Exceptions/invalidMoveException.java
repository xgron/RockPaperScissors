package com.game.RockPaperScissors.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="That move is invalid! Choose between Rock, Paper or Scissors!")
public class invalidMoveException extends RuntimeException {
}
