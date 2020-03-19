/**
 * @author Niclas Fölster
 * @version 0.2
 * @since 2020-03-19
 */
package com.game.RockPaperScissors.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.game.RockPaperScissors.model.Exceptions.*;
import com.game.RockPaperScissors.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The main controller for the application.
 *
 * All the endpoints are here in the following order:
 *  <p><ul>
 *  <li>POST /api/games
 *  <li>POST /api/games/{id}/join
 *  <li>POST /api/games/{id}/move
 *  <li>GET /api/games/{id}
 *  </ul></p>
 */
@RestController
@RequestMapping(value = "api/games")
public class GameController {

    /**
     * ExceptionHandler for handling invalid JSON entries
     * @throws invalidJsonException
     */
    @ExceptionHandler(JsonParseException.class)
    public void handleException() throws invalidJsonException {
        throw new invalidJsonException();
    }


    /**
     * the gameServer stores all the games in a list.
     */
    private GameServer gameServer;

    /**
     * constructor to initialize {@code gameServer}
     */
    public GameController() {
        this.gameServer = new GameServer();
    }

    /**
     * <h1>api/games Endpoint</h1>
     * <p>
     * This starts a new game. it requires that a player name is sent in the request-body.
     * the player name is directly made into a Player.
     * @param player
     * <p>
     * returns a message with all necessary information, including the game ID.
     * @return startNewGameMessage
     */
    @RequestMapping(method = RequestMethod.POST, value = "", produces = "application/json")
    @ResponseBody
    ResponseEntity<String> startNewGame(@RequestBody Player player) {

        //Create new game and add to gameServer
        Game game = new Game(player);
        gameServer.add(game);

        return ResponseMessageHandler.startNewGameMessage(game);
    }


    /**
     * <h1>api/games/{id}/join Endpoint</h1>
     * <p>
     * This is used for a player to join an existing game. It requires the games ID,
     * and a player name in the request-body.
     * <p>
     *
     * The Games ID you want to join.
     * @param id
     *
     * The second players name.
     * @param player
     * @return joinMessage
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/join", produces = "application/json")
    @ResponseBody
    ResponseEntity<String> joinGame(@PathVariable String id, @RequestBody Player player) {

        //Find the game in the gameServer
        Game game = gameServer.findById(UUID.fromString(id));

        //Some exception handling
        if (!game.getStatus().equals(Status.Ongoing))
            throw new invalidGameException();
        if (game.getPlayer2() != null)
            throw new gameIsFullException();

        //Add player2
        gameServer.findById(UUID.fromString(id)).setPlayer2(player);

        return ResponseMessageHandler.joinMessage(game);
    }

    /**
     * <h1>api/games/{id}/move Endpoint</h1>
     * <p>
     * This is used when a player wants to make a move(Rock, Paper or Scissors).
     * <p>
     * The Games ID you want to make your move in.
     * @param id
     *
     * The players name.
     * @param player
     *
     * @return moveMessage
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/move", produces = "application/json")
    @ResponseBody
    ResponseEntity<String> playMove(@PathVariable String id, @RequestBody Player player) {

        //Find the game in the gameServer
        Game game = gameServer.findById(UUID.fromString(id));

        //Exception handling
        if (!game.getStatus().equals(Status.Ongoing))
            throw new invalidGameException();
        if (player.getMove() == null)
            throw new invalidMoveException();
        else
            player.setMove(Resolver.checkMove(player.getMove()) + ""); //check if move is ok

        //more Exceptions
        if (game.getPlayer2() == null)
            throw new noOpponentException();
        if (!(player.getName().equals(game.getPlayer1().getName()) || player.getName().equals(game.getPlayer2().getName())))
            throw new PlayerNotInGameException();

        //Final checks and adding moves
        if (player.getName().equals(game.getPlayer1().getName()))
            if (game.getPlayer1().getMove() == null)
                game.setPlayer1(player); //Sets the player, replacing the name, but also adding their new move
            else
                throw new PlayerAlreadyMovedException();
        if (player.getName().equals(game.getPlayer2().getName()))
            if (game.getPlayer2().getMove() == null)
                game.setPlayer2(player); //Sets the player, replacing the name, but also adding their new move
            else
                throw new PlayerAlreadyMovedException();


        return ResponseMessageHandler.moveMessage(game, player);
    }

    /**
     * <h1>api/games/{id} Endpoint</h1>
     * <p>
     * This is used when a player wants see the games current state, including the result of the game.
     * <p>
     * The Games ID you want to join.
     * @param id
     *
     * @return ongoingGameMessage or resolvedGameMessage
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    @ResponseBody
    ResponseEntity<String> viewGame(@PathVariable String id) {

        //Find the game in the gameServer
        Game game = gameServer.findById(UUID.fromString(id));

        //Check if both players made a move. If so, resolve the game.
        Result result = null;
        if (game.getPlayer1().getMove() != null && game.getPlayer2().getMove() != null) {
            game.setStatus(Status.Resolved);
            result = Resolver.resolve(game.getPlayer1().getMove(), game.getPlayer2().getMove());
        }


        //check if the game is ongoing or resolved, and return messages accordingly.
        if (game.getStatus().equals(Status.Ongoing)) {
            return ResponseMessageHandler.ongoingGameMessage(game);

        } else if (game.getStatus().equals(Status.Resolved)) {
            return ResponseMessageHandler.resolvedGameMessage(game, result);
        } else
            throw new invalidGameException();
    }


}
