package models;

public class Cell {
    private int rowNum;
    private int colNum;
    private CellStatus cellStatus;
    private Player player;

    public Cell(int rowNum, int colNum){
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.cellStatus = CellStatus.EMPTY;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
