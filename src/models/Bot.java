package models;

import strategies.BotPlayingStrategy;
import strategies.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    public BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Long playerId, Symbol symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(name, playerId, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingSrategy(this.botDifficultyLevel);
    }

    public Move makeMove(Board board){
        Move move =  this.botPlayingStrategy.makeMove(board);//returns move obj(move, null)
        move.setPlayer(this);
        return move;
    }
}
