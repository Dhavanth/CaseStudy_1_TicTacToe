package strategies;

import models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingSrategy(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        }else if(botDifficultyLevel.equals(BotDifficultyLevel.HARD)){
            return new HardBotPlayingStrategy();
        }
        return new EasyBotPlayingStrategy();
    }
}
