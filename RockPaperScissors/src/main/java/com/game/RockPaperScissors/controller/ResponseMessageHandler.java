/**
 * @author Niclas Fölster nfolster@kth.se
 * @version 0.2
 * @since 2020-03-19
 */
package com.game.RockPaperScissors.controller;

import com.game.RockPaperScissors.model.Game;
import com.game.RockPaperScissors.model.Player;
import com.game.RockPaperScissors.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class handles the responses returned to the user and makes sure they get the right message.
 * each function corresponds to one Endpoint in the {@code GameController}, except the last two, which both correlate
 * to the last Endpoint.
 */
public class ResponseMessageHandler {

    public static ResponseEntity<String> startNewGameMessage(Game game) {
        return new ResponseEntity<>("Welcome to Rock, Paper and Scissors, " + game.getPlayer1().getName() + "! " +
                "\nYour Game ID is: " + game.getId(), HttpStatus.CREATED);
    }

    public static ResponseEntity<String> joinMessage(Game game) {
        return new ResponseEntity<>("Welcome to Rock, Paper and Scissors, " + game.getPlayer2().getName() +
                "\nYou are playing against " + game.getPlayer1().getName() + "." +
                "\nGame status: " + game.getStatus() +
                "\nGame ID: " + game.getId(), HttpStatus.OK);
    }

    public static ResponseEntity<String> moveMessage(Game game, Player player) {
        return new ResponseEntity<>("Welcome to Rock, Paper and Scissors, " + player.getName() +
                "\nYou chose " + player.getMove() +
                "\nGame status: " + game.getStatus() +
                "\nGame ID: " + game.getId(), HttpStatus.OK);
    }

    public static ResponseEntity<String> ongoingGameMessage(Game game) {
        String players = "";
        players = game.getPlayer1().getName();
        if (game.getPlayer2() != null)
            players += ", " + game.getPlayer2().getName();


        return new ResponseEntity<>("Welcome to Rock, Paper and Scissors!" +
                "\nThis game is still ongoing!" +
                "\nPlayers: " + players +
                "\n--------------------------------------------------------------" +
                "\nGame status: " + game.getStatus() +
                "\nGame ID: " + game.getId(), HttpStatus.OK);
    }

    public static ResponseEntity<String> resolvedGameMessage(Game game, Result result) {


        String sResult;
        switch (result) {
            case draw:
                sResult = "It is a draw!";
                break;
            case player1Wins:
                sResult = "The winner is: " + game.getPlayer1().getName() + "!";
                break;
            case player2Wins:
                sResult = "The winner is: " + game.getPlayer2().getName() + "!";
                break;
            default:
                sResult = "";
        }


        return new ResponseEntity<>("Welcome to Rock, Paper and Scissors!" +
                "\nThis game is over! " + sResult +
                "\n" + game.getPlayer1().getName() + " chose " + game.getPlayer1().getMove() + " and " +
                game.getPlayer2().getName() + " chose " + game.getPlayer2().getMove() + "." +
                "\n--------------------------------------------------------------" +
                "\nGame status: " + game.getStatus() +
                "\nGame ID: " + game.getId(), HttpStatus.OK);
    }
}
