package strategies;

import models.Board;
import models.Move;

public interface WinningStrategies {
    public boolean checkWinner(Move move, Board board);
    public void undo(Board board, Move move);
    public String getStrategyName();
}
