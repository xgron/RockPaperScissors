package com.game.RockPaperScissors.model;

public enum Move {
    Rock {
        @Override
        public boolean winsOver(String move) { return ("Scissors".equals(move)); }
    },

    Paper {
        @Override
        public boolean winsOver(String move) { return ("Rock".equals(move)); }
    },

    Scissors {
        @Override
        public boolean winsOver(String move) { return ("Paper".equals(move)); }
    };

    public abstract boolean winsOver(String move);

}

