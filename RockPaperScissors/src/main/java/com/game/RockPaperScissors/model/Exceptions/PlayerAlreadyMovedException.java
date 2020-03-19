package com.game.RockPaperScissors.model.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="This player has already chosen!")
public class PlayerAlreadyMovedException extends RuntimeException {

}
