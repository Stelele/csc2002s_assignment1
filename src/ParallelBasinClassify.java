import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import java.util.concurrent.RecursiveTask;

public class ParallelBasinClassify extends RecursiveTask<ArrayList<Basin>> {
    int colStart;
    int colEnd;
    int rowStart;
    int rowEnd;
    float[][] mountain;
    static  int SEQUENTIAL_CUTOFF = 1;
    static final float OFFSET = 0.01f;

    ArrayList<Basin> basins;

    public ParallelBasinClassify(float[][] mountain, int rowStart, int rowEnd, int colStart, int colEnd){
        this.mountain = mountain;
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.colStart = colStart;
        this.colEnd = colEnd;

        this.basins = new ArrayList<Basin>();
    }

    protected ArrayList<Basin> compute(){
        if(((rowEnd - rowStart) <= SEQUENTIAL_CUTOFF) &&  ((colEnd - colStart) <= SEQUENTIAL_CUTOFF)){
            for(int i = rowStart; i < rowEnd; i++){
                for(int j = colStart; j < colEnd; j++){
                    float offsetedValue = mountain[i][j] + OFFSET;
    
                    if(offsetedValue > mountain[i-1][j-1])
                        continue;
                    if(offsetedValue > mountain[i-1][j])
                        continue;
                    if(offsetedValue > mountain[i-1][j+1])
                        continue;
                    if(offsetedValue > mountain[i][j-1])
                        continue;
                    if(offsetedValue > mountain[i][j+1])
                        continue;
                    if(offsetedValue > mountain[i+1][j-1])
                        continue;
                    if(offsetedValue > mountain[i+1][j])
                        continue;
                    if(offsetedValue > mountain[i+1][j+1]) 
                        continue;
                    
                    basins.add(new Basin(i, j));
                }
            }
            return basins;
        } else {
            int midRow = (rowStart + rowEnd)/2;
            int midCol = (colStart + colEnd)/2;

            ParallelBasinClassify topLeft = new ParallelBasinClassify(mountain, rowStart, midRow, colStart, midCol);
            ParallelBasinClassify topRight = new ParallelBasinClassify(mountain, rowStart , midRow, midCol, colEnd);
            ParallelBasinClassify bottomLeft = new ParallelBasinClassify(mountain, midRow, rowEnd, colStart, midCol);
            ParallelBasinClassify bottomRight = new ParallelBasinClassify(mountain, midRow, rowEnd, midCol, colEnd);

            topLeft.fork();
            topRight.fork();
            bottomLeft.fork();
        
            basins.addAll(bottomRight.compute());
            basins.addAll(topLeft.join());
            basins.addAll(topRight.join());
            basins.addAll(bottomLeft.join());

            return basins;
        }

        
    }
}