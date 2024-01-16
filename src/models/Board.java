package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;
    private int size;

    public Board(int dimension){
        this.size = dimension;
        this.board = new ArrayList<>();

        for(int i = 0; i < size; i++){
            board.add(new ArrayList<>());
            for(int j = 0; j < size; j++){
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void displayBoard(){
        for(List<Cell> eachRow: board){
            for(Cell cell: eachRow){
                if(cell.getPlayer() == null){
                    System.out.print("| - |");
                }else{
                    System.out.print("| " + cell.getPlayer().getSymbol().getaSymbol() + " |");
                }
            }
            System.out.println();
        }
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
