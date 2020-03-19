/**
 * @author Niclas Fölster
 * @version 0.2
 * @since 2020-03-19
 */
package com.game.RockPaperScissors.model;

import com.game.RockPaperScissors.model.Exceptions.samePlayernameException;
import java.util.UUID;

/**
 * Representation of a game
 * The id uses UUID, which makes sure that no games will have same ID, or at least the chance is astronomically small.
 */
public class Game {

    private UUID id;

    private Player player1;
    private Player player2;
    private Status status;

    public Game(Player player1) {
        this.player1 = player1;
        this.id = UUID.randomUUID();
        this.status = Status.Ongoing;

    }


    public UUID getId() {
        return id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    //Throws exception if both players have the same name.
    public void setPlayer2(Player player2) {
        //Player1 does not need to check for samePlayernameException, since they are always the creator of the game
        if(player2.getName().equals(player1.getName()))
            throw new samePlayernameException();

        this.player2 = player2;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}

