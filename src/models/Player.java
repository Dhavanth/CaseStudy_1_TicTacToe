package models;

import java.net.StandardSocketOptions;
import java.util.Scanner;

public class Player {
    private String name;
    private Long playerId;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(String name, Long playerId, Symbol symbol, PlayerType playerType){
        this.name = name;
        this.playerId = playerId;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Move makeMove(Board board){
        //How will a player give input?
        //using scanner obj
        System.out.println("Please enter the row number: ");
        int rowNumber = scanner.nextInt();

        System.out.println("Please enter the column number: ");
        int colNumber = scanner.nextInt();

        return new Move(new Cell(rowNumber, colNumber), this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
