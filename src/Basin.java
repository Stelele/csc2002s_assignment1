public class Basin {
    int row;
    int col;

    public Basin(int row, int col){
        this.row = row;
        this.col = col;
    }


    public String toString(){
        return Integer.toString(row) + " " + Integer.toString(col);
    }
}