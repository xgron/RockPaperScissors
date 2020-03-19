package com.game.RockPaperScissors.model;


import java.util.UUID;

public class Game {


    private UUID id;

    private Player player1;
    private Player player2;
    private Status status;
    private Player winner;

    public Game(Player player1) {
        this.player1 = player1;
        this.id = UUID.randomUUID();
        this.status = Status.Ongoing;

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean winsOver(String move, String move2){
        if (move.equals("Rock"))
            return ("Scissors".equals(move2));
        if (move.equals("Paper"))
            return ("Rock".equals(move2));
        if (move.equals("Scissors"))
            return ("Paper".equals(move2));
        else
            return false;
    }
}

