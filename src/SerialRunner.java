import java.util.List;
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

        List<Basin> basins = new List<Basin>();
        float offset = 0.01;

        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){

            }
        }

    }
}