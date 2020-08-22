import java.util.ArrayList;
import java.util.Scanner;
/**
 * SerialRunner
 */
public class SerialRunner {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        
        int row = reader.nextInt();
        int col = reader.nextInt();

        float [][] mountain = new float[row][col];

        for(int i = 0; i < row ; i++){
            for(int j = 0; j < col; j++){
                mountain[i][j] = reader.nextFloat();
            }
        }

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

    }
}