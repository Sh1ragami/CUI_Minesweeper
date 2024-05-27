public class Cell {
    
    private boolean dug;
    private boolean mine;
    private boolean flagged;
    private int totalMines;

    public Cell() {
        this.dug = false;
        this.mine = false;
        this.flagged = false;
        this.totalMines = 0;
    }

    public void setDug() {
        this.dug = true;
    }

    public boolean hasDug() {
        return this.dug;
    }

    public void setMine() {
        this.mine = true;
    }

    public boolean hasMine() {
        return this.mine;
    }

    public void setFlagged(boolean f) {
        this.flagged = f;
    }

    public boolean isFlagged() {
        return this.flagged;
    }

    public void setTotalMines(int n) {
        this.totalMines = n;
    }

    public int getTotalMines() {
        return this.totalMines;
    }
}
