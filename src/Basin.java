/**
 * Storage class used to store basin location
 */
public class Basin {
    int row;
    int col;

    /**
     * 
     * @param row row location on grid
     * @param col column location on grid
     */
    public Basin(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * Output String representation of values
     */
    public String toString(){
        return Integer.toString(row) + " " + Integer.toString(col);
    }
}