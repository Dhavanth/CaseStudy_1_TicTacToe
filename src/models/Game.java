package models;

import exceptions.BotCountException;
import exceptions.PlayerCountException;
import exceptions.SymbolCountException;
import jdk.swing.interop.SwingInterOpUtils;
import strategies.WinningStrategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private List<Move> moves;
    private Board board;
    private Player winner;
    private List<WinningStrategies> winningStrategies;
    private GameStatus gameStatus;
    private int nextPlayerIndex;

    private Game(int dimension, List<Player> players, List<WinningStrategies> winningStrategies){
        board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;

        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.INPROGRESS;
        this.nextPlayerIndex = 0;
    }

    public void displayBoard(){
        this.board.displayBoard();
    }

    public void makeMove(){
        //who is the player that is going to make the move?
        //get the current player who has to make the move
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("It is "+ currentPlayer.getName() + "'s move");
        Move move = currentPlayer.makeMove(board);

        System.out.println(currentPlayer.getName() + " has made a move at "+ move.getCell().getRowNum() + ", "+ move.getCell().getColNum());

        if(!validateMove(move)){
            System.out.println("It is an invalid move");
            return;
        }
        int row = move.getCell().getRowNum();
        int col = move.getCell().getColNum();

        Cell actualCell = board.getBoard().get(row).get(col);
        actualCell.setCellStatus(CellStatus.FILLED);
        actualCell.setPlayer(currentPlayer);

        Move actualMove = new Move(actualCell, currentPlayer);
        moves.add(actualMove);
        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();
        if(checkWinner(actualMove)){
            setWinner(currentPlayer);
            setGameStatus(GameStatus.WON);
            return;
        }

        if(moves.size() == board.getSize() * board.getSize()){
            setGameStatus(GameStatus.DRAW);

        }
    }

    public boolean checkWinner(Move move){
        for(WinningStrategies winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(move, board)){
                System.out.println(move.getPlayer().getName() + " has won using " + winningStrategy.getStrategyName());
                return true;
            }
        }

        return false;
    }

    public void undo(){
        //check if there are 0 moves
        if(moves.size() == 0){
            return;
        }

        // fetch the last move and remove that move
        Move lastMove = moves.get(moves.size() - 1);
        moves.remove(moves.size() - 1);

        // update the cell status
        Cell cell = lastMove.getCell();
        cell.setCellStatus(CellStatus.EMPTY);
        cell.setPlayer(null);

        // update all the corresponding maps
        for(WinningStrategies winningStrategy: winningStrategies){
            winningStrategy.undo(board, lastMove);
        }

        // update the player index to previous value
        nextPlayerIndex -= 1;
        nextPlayerIndex = (nextPlayerIndex + players.size()) % players.size();

    }

    public boolean validateMove(Move move){
        int row = move.getCell().getRowNum();
        int col = move.getCell().getColNum();

        if(row >= board.getSize() || col >= board.getSize() || row < 0 || col < 0){
            return false;
        }

        if(board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.FILLED)){
            //why are we checking board here. why dont we check using move itself.
            //because board is more of fixed but move is temporary or varies everytime
            return false;
        }
        return true;
    }



    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int dimensions;
        private List<Player> players;
        private List<WinningStrategies> winningStrategies;

        public Game build() throws PlayerCountException, BotCountException, SymbolCountException {
            validate();
            return new Game(dimensions, players, winningStrategies);
        }

        public void validate() throws PlayerCountException, BotCountException, SymbolCountException {

            validatePlayerCount();//less then or equal to dimension - 1
            validateSymbolCount();//must be equal to number of players
            validateBotCount();// there must be only one bot

        }

        public void validatePlayerCount() throws PlayerCountException {
            if(players.size() != dimensions - 1){
                throw new PlayerCountException("Invalid number of players");
            }

        }

        public void validateSymbolCount() throws SymbolCountException {
            Map<Character, Integer> symbolCount = new HashMap<>();
            for(Player player: players){
                Character currSymbol = player.getSymbol().getaSymbol();
                if(!symbolCount.containsKey(currSymbol)){
                    symbolCount.put(currSymbol, 0);
                }
                symbolCount.put(currSymbol, symbolCount.get(currSymbol) + 1);
                if(symbolCount.get(currSymbol) > 1){
                    throw new SymbolCountException("Invalid number of symbols");
                }
            }
        }

        public void validateBotCount() throws BotCountException {
            int botCount = 0;
            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new BotCountException("Invalid number of bots");
            }

        }

        public int getDimensions() {
            return dimensions;
        }

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategies> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategies> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<WinningStrategies> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategies> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
}
