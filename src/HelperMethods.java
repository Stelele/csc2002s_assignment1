import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HelperMethods {
 
    static long tick(){
		return System.currentTimeMillis();
    }
    
	static float tock(long startTime){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}

    static float[][] loadMountainInputData(String inputFileLocation) throws FileNotFoundException{
        File file = new File(inputFileLocation);
        Scanner reader = new Scanner(file);

        int row = reader.nextInt();
        int col = reader.nextInt();

        float [][] mountain = new float[row][col];

        for(int i = 0; i < row ; i++){
            for(int j = 0; j < col; j++){
                mountain[i][j] = reader.nextFloat();
            }
        }

        reader.close();

        return mountain;
    }

    static String[] loadExpectedBasins(String expectedBasinsLocation) throws FileNotFoundException{
        File file = new File(expectedBasinsLocation);
        Scanner reader = new Scanner(file);

        int foundBasins = Integer.parseInt(reader.nextLine());
        String[] basins = new String[foundBasins];
        for(int i = 0; i < foundBasins; i++){
            basins[i] = reader.nextLine();
        }

        reader.close();

        return basins;
    }
}