/**
 * @author Niclas Fölster nfolster@kth.se
 * @version 0.2
 * @since 2020-03-19
 */
package com.game.RockPaperScissors.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You can't do your move until another player has joined!")
public class noOpponentException extends RuntimeException {
}
