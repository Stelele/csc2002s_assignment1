import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class HelperMethods {
 
    static long tick(){
		return System.currentTimeMillis();
    }
    
	static double tock(long startTime){
		return (System.currentTimeMillis() - startTime) / 1000.0; 
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

    static boolean checkMatchingResults(String[] expectedBasins, ArrayList<Basin> foundBasins){
        boolean match = true;

        for(Basin foundBasin : foundBasins){
            if(!Arrays.asList(expectedBasins).contains(foundBasin.toString())){
                match = false;
                break;
            }
        }

        return match;
    }

    static void writeToCSVFile(HashMap<String, ArrayList<Double>> testingResults, String newFileName) throws IOException{
        FileWriter writer = new FileWriter(newFileName);

        for(String sequentialCutoff : testingResults.keySet()){
            double sum = 0;

            for(double val : testingResults.get(sequentialCutoff))
                sum += val;

            double averageTime = sum/testingResults.get(sequentialCutoff).size();

            writer.append(sequentialCutoff + "," + Double.toString(averageTime) + "\n");
        }

        writer.close();
    }
}