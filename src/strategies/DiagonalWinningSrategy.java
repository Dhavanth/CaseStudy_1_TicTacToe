package strategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningSrategy implements WinningStrategies{

    Map<Symbol, Integer> mainDiagonal = new HashMap<>();
    Map<Symbol, Integer> antiDiagonal = new HashMap<>();

    public boolean checkWinner(Move move, Board board){
        int row = move.getCell().getRowNum();
        int col = move.getCell().getColNum();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col){
            if(!mainDiagonal.containsKey(symbol))
                mainDiagonal.put(symbol, 0);

            mainDiagonal.put(symbol, mainDiagonal.get(symbol) + 1);
            if(mainDiagonal.get(symbol).equals(board.getSize()))
                return true;
        }else if(row + col == board.getSize() - 1){
            if(!antiDiagonal.containsKey(symbol))
                antiDiagonal.put(symbol, 0);

            antiDiagonal.put(symbol, antiDiagonal.get(symbol) + 1);
            if(antiDiagonal.get(symbol).equals(board.getSize()))
                return true;
        }

        return false;
    }

    public void undo(Board board, Move move){
        int row = move.getCell().getRowNum();
        int col = move.getCell().getColNum();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col){
            mainDiagonal.put(symbol, mainDiagonal.get(symbol) - 1);
        }else if(row + col == board.getSize() - 1){
            antiDiagonal.put(symbol, antiDiagonal.get(symbol) - 1);
        }

    }

    public String getStrategyName(){
        return "Diagonal Winning Strategy";
    }
}
