package controllers;

import exceptions.BotCountException;
import exceptions.PlayerCountException;
import exceptions.SymbolCountException;
import models.Game;
import models.GameStatus;
import models.Player;
import strategies.WinningStrategies;

import java.util.List;

public class GameController {
    private int dimensions;
    private List<Player> players;
    private List<WinningStrategies> winningStrategies;

    public Game startGame(int dimensions, List<Player> players, List<WinningStrategies> winningStrategies) throws PlayerCountException, BotCountException, SymbolCountException {
        Game game = Game.getBuilder().setDimensions(dimensions)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
        return game;
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public void display(Game game){
        game.displayBoard();
    }

    public GameStatus checkState(Game game){
        return game.getGameStatus();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void undo(Game game){
        game.undo();

    }
}
