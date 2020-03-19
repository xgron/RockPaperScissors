package com.game.RockPaperScissors.controller;


import com.game.RockPaperScissors.model.Exceptions.*;
import com.game.RockPaperScissors.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
 * TODO:
 * */

@RestController
@RequestMapping(value = "api/games")
public class GameController {


    private GameServer gameServer;

    public GameController() {
        this.gameServer = new GameServer();
    }

    @RequestMapping(method = RequestMethod.POST, value = "", produces = "application/json")
    @ResponseBody
    ResponseEntity<String> startNewGame(@RequestBody Player player) throws gameDoesNotExistException {
        Game game = new Game(player);
        gameServer.add(game);

        return new ResponseEntity<>("Welcome to Rock, Paper and Scissors, " + game.getPlayer1().getName() + "! " +
                "\nYour Game ID is: " + gameServer.findById(game.getId()).getId(), HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{id}/join", produces = "application/json")
    @ResponseBody
    ResponseEntity<String> joinGame(@PathVariable String id, @RequestBody Player player) {
        Game game = gameServer.findById(UUID.fromString(id));


        if (game == null)
            throw new gameDoesNotExistException();
        if (!game.getStatus().equals(Status.Ongoing))
            throw new invalidGameException();
        if (game.getPlayer2() != null)
            throw new gameIsFullException();
        if (game.getPlayer1().getName().equals(player.getName()))
            throw new samePlayernameException();


        gameServer.findById(UUID.fromString(id)).setPlayer2(player);
        return new ResponseEntity<>("Welcome to Rock, Paper and Scissors, " + player.getName() +
                "\nYou are playing against " + game.getPlayer1().getName() + "." +
                "\nGame status: " + game.getStatus() +
                "\nGame ID: " + game.getId(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{id}/move", produces = "application/json")
    @ResponseBody
    ResponseEntity<String> playMove(@PathVariable String id, @RequestBody Player player) {
        Game game = gameServer.findById(UUID.fromString(id));


        if (game == null)
            throw new gameDoesNotExistException();
        if (!game.getStatus().equals(Status.Ongoing))
            throw new invalidGameException();
        if (player.getMove() == null || (!player.getMove().equals("Rock") && !player.getMove().equals("Paper") && !player.getMove().equals("Scissors")))
            throw new invalidMoveException();
        if (game.getPlayer2() == null)
            throw new noOpponentException();
        if (!(player.getName().equals(game.getPlayer1().getName()) || player.getName().equals(game.getPlayer2().getName())))
            throw new PlayerNotInGameException();
        if (player.getName().equals(game.getPlayer1().getName()))
            if (game.getPlayer1().getMove() == null)
                game.setPlayer1(player);
            else
                throw new PlayerAlreadyMovedException();
        if (player.getName().equals(game.getPlayer2().getName()))
            if (game.getPlayer2().getMove() == null)
                game.setPlayer2(player);
            else
                throw new PlayerAlreadyMovedException();


        return new ResponseEntity<>("Welcome to Rock, Paper and Scissors, " + player.getName() +
                "\nYou chose " + player.getMove() +
                "\nGame status: " + game.getStatus() +
                "\nGame ID: " + game.getId(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    @ResponseBody
    ResponseEntity<String> viewGame(@PathVariable String id) {
        Game game = gameServer.findById(UUID.fromString(id));


        if (game == null)
            throw new gameDoesNotExistException();


        resolve(game);
        if (game.getStatus().equals(Status.Ongoing)) {
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

        } else if (game.getStatus().equals(Status.Resolved) || game.getStatus().equals(Status.Draw)) {
            String result = "It is a draw!";
            if (game.getStatus().equals(Status.Resolved))
                result = "The winner is: " + game.getWinner().getName() + "!";
            return new ResponseEntity<>("Welcome to Rock, Paper and Scissors!" +
                    "\nThis game is over! " + result +
                    "\n" + game.getPlayer1().getName() + " chose " + game.getPlayer1().getMove() + " and " +
                    game.getPlayer2().getName() + " chose " + game.getPlayer2().getMove() +
                    "\n--------------------------------------------------------------" +
                    "\nGame status: " + game.getStatus() +
                    "\nGame ID: " + game.getId(), HttpStatus.OK);
        } else
            throw new invalidGameException();
    }

    private void resolve(Game game) {
        if (game.getPlayer1().getMove() != null && game.getPlayer2().getMove() != null) {
            game.setStatus(Status.Resolved);
            if (game.winsOver(game.getPlayer1().getMove(), game.getPlayer2().getMove()))
                game.setWinner(game.getPlayer1());
            else if (game.winsOver(game.getPlayer2().getMove(), game.getPlayer1().getMove()))
                game.setWinner(game.getPlayer2());
            else
                game.setStatus(Status.Draw);
        }

    }


}
