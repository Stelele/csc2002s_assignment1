import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is a Helper class that contains useful methods used by
 * both Sequential and Parallel solution
 */
public class HelperMethods {
 
    /**
     * @return currentTime time in milliseconds
     */
    static long tick(){
		return System.currentTimeMillis();
    }
    
    /**
     * 
     * @param startTime time at which tick method was called
     * @return elapsedTime the difference between current time and startTime
     */
	static double tock(long startTime){
		return (System.currentTimeMillis() - startTime) / 1000.0; 
	}

    /**
     * Function that reads in input terrain data and loads it into a 2d array
     * @param inputFileLocation location of file containing input terrain data
     * @return mountainTerain[][] 2d array with equivalent height information
     * @throws FileNotFoundException
     */
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

    /**
     * Method that loads the expected answer file for testing and loads value into an array
     * @param expectedBasinsLocation location of file containing expected basins data
     * @return expectedBasins[]
     * @throws FileNotFoundException
     */
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

    /**
     * Method that check if the the found and expected basins are the same
     * @param expectedBasins array with expected basins data
     * @param foundBasins arrayList containing basins found by program
     * @return match 
     */
    static boolean checkMatchingResults(String[] expectedBasins, ArrayList<Basin> foundBasins){
        boolean match = true;

        if(expectedBasins.length != foundBasins.size())
            return false;

        for(Basin foundBasin : foundBasins){
            if(!Arrays.asList(expectedBasins).contains(foundBasin.toString())){
                match = false;
                break;
            }
        }

        return match;
    }

    /**
     * Method that outputs timing results from testing to a csv file
     * @param testingResults HashMap with all the timining information from benchmarking tests
     * @param newFileName Name of file to be written to
     * @throws IOException
     */
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