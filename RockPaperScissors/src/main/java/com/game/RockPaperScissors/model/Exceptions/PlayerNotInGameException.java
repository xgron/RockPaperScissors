package com.game.RockPaperScissors.model.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="No player with this name is in this game!")
public class PlayerNotInGameException extends RuntimeException {
}
