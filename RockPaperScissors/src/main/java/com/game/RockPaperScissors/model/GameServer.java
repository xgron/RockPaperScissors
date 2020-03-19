package com.game.RockPaperScissors.model;


import com.game.RockPaperScissors.model.Exceptions.gameDoesNotExistException;

import java.util.ArrayList;
import java.util.UUID;

public class GameServer {
    ArrayList<Game> games = new ArrayList<>();


    public void add(Game game) {
        games.add(game);
    }


    public Game findById(UUID id) {
        for(Game game : games)
            if (game.getId().equals(id))
                return game;
            throw new gameDoesNotExistException();
    }
}
