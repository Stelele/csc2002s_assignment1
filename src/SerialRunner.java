import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * SerialRunner 
 * Implements running in serial
 */
public class SerialRunner {

    public static void main(String[] args) {
        try{
            float[][] mountain = HelperMethods.loadMountainInputData(args[0]);
            int row = mountain.length;
            int col = mountain[0].length;

            String[] expectedBasins = HelperMethods.loadExpectedBasins(args[1]);

            ArrayList<Basin> basins = new ArrayList<Basin>();
            float offset = (float)0.01;

            for(int i = 1; i < row - 1; i++){
                for(int j = 1; j < col - 1; j++){
                    float offsetedValue = mountain[i][j] + offset;

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

            System.out.println(basins.size());
            for(Basin entry : basins){
                System.out.println(entry.toString());
            }
        } catch(FileNotFoundException e){
            System.err.println(e);
        }
    }
}