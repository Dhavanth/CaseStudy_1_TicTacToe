import controllers.GameController;
import exceptions.BotCountException;
import exceptions.PlayerCountException;
import exceptions.SymbolCountException;
import models.*;
import strategies.ColumnWinningStrategy;
import strategies.DiagonalWinningSrategy;
import strategies.RowWinningSrategy;
import strategies.WinningStrategies;

import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        try{
            int dimensions = 3;

            List<Player> players = new ArrayList<>();
            players.add(new Player("Fuji", 1L, new Symbol('O'), PlayerType.HUMAN));
            players.add(new Bot("Chitti The Robot", 2L, new Symbol('X'), PlayerType.BOT, BotDifficultyLevel.EASY));

            List<WinningStrategies> winningStrategies = new ArrayList<>();
            winningStrategies.add(new RowWinningSrategy());
            winningStrategies.add(new ColumnWinningStrategy());
            winningStrategies.add(new DiagonalWinningSrategy());

            Game game = gameController.startGame(dimensions, players, winningStrategies);
            System.out.println("Game has been created");

            while(gameController.checkState(game).equals(GameStatus.INPROGRESS)){
                gameController.display(game);
                //undo
                System.out.println("Do you want to undo: (Y/N)");
                String checkUndo = scanner.next();
                if(checkUndo.equalsIgnoreCase("Y")){
                    gameController.undo(game);
                    continue;
                }

                //make a move
                gameController.makeMove(game);
            }

            if(gameController.checkState(game).equals(GameStatus.WON)){
                gameController.display(game);
                System.out.println(gameController.getWinner(game).getName() + " has won the game");
            }
            else if(gameController.checkState(game).equals(GameStatus.DRAW)){
                gameController.display(game);
                System.out.println("Game has been drawn!");
            }

        }catch(BotCountException | PlayerCountException | SymbolCountException e){
            System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}