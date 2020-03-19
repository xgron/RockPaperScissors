/**
 * @author Niclas Fölster nfolster@kth.se
 * @version 0.2
 * @since 2020-03-19
 */
package com.game.RockPaperScissors.controller;

import com.game.RockPaperScissors.model.Exceptions.invalidMoveException;
import com.game.RockPaperScissors.model.Result;

/**
 * This class contains functions to handle conversion of the moves, and resolving two moves
 * against each other using a truth table approach for fun.
 */
public class Resolver {
    //Converts moves to ints for the resolve function
    public static int stringToInt(String s) {

        switch (s.toLowerCase()) {
            case "r":
            case "rock":
                return 0;
            case "p":
            case "paper":
                return 1;
            case "s":
            case "scissors":
                return 2;
            default:
                throw new invalidMoveException();
        }
    }

    /**
     * handles the move string and makes sure its not case-sensitive
     * and that you can only use r, p or s if you want to.
     * <p>
     * @param move
     * @return move
     */
    public static String checkMove(String move) {

            switch (move.toLowerCase()) {
                case "r":
                case "rock":
                    return "Rock";
                case "p":
                case "paper":
                    return "Paper";
                case "s":
                case "scissors":
                    return "Scissors";
                default:
                    throw new invalidMoveException();
            }

    }

    /**
     * Resolves the result of two moves against each other using a truth table approach.
     * The moves are one of the following in comparison to the matrix:
     * <p><ul>
     * <li> rock = 0
     * <li> paper = 1
     * <li> scissors = 2
     * </ul></p>
     *
     * @param move1
     * @param move2
     * @return
     */
    public static Result resolve(String move1, String move2) {
        Result[][] resolver = {
                {Result.draw, Result.player2Wins, Result.player1Wins},

                {Result.player1Wins, Result.draw, Result.player2Wins},

                {Result.player2Wins, Result.player1Wins, Result.draw}};

        return resolver[stringToInt(move1)][stringToInt(move2)];
    }
}
