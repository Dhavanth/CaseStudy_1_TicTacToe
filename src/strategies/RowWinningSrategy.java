package strategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningSrategy implements WinningStrategies{

    Map<Integer, Map<Symbol,Integer>> rowCount = new HashMap<>();

    public boolean checkWinner(Move move, Board board){
        int row = move.getCell().getRowNum();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!rowCount.containsKey(row)){
            rowCount.put(row, new HashMap<>());
        }

        Map<Symbol, Integer> rowMap = rowCount.get(row);
        if(!rowMap.containsKey(symbol)){
            rowMap.put(symbol, 0);
        }
        rowMap.put(symbol, rowMap.get(symbol) + 1);
        if(rowMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }

    public void undo(Board board, Move move){
        int row = move.getCell().getRowNum();
        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> rowMap = rowCount.get(row);
        rowMap.put(symbol, rowMap.get(symbol) - 1);
    }

    public String getStrategyName(){
        return "Row Winning Strategy";
    }
}
