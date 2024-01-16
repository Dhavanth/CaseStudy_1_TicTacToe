package strategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategies{

    Map<Integer, Map<Symbol, Integer>> colCounts = new HashMap<>();

    public boolean checkWinner(Move move, Board board){
        int col = move.getCell().getColNum();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!colCounts.containsKey(col)){
            colCounts.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> colMap = colCounts.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol, 0);
        }

        colMap.put(symbol, colMap.get(symbol) + 1);

        if(colMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }

    public void undo(Board board, Move move){
        int col = move.getCell().getColNum();
        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> colMap = colCounts.get(col);
        colMap.put(symbol, colMap.get(symbol) - 1);
    }

    public String getStrategyName(){
        return "Column Winning Strategy";
    }
}
