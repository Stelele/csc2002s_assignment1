import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

            ArrayList<Double> storedTimes = new ArrayList<Double>();
            int averageRuns = 3;

            boolean checkGetExpectedOutput = false;

            for(int run = 0; run < averageRuns; run++){
                System.gc();
                basins.clear();
                long startTime = HelperMethods.tick();
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
                double runTime = HelperMethods.tock(startTime);
                storedTimes.add(runTime);

                checkGetExpectedOutput = HelperMethods.checkMatchingResults(expectedBasins, basins);

                if(!checkGetExpectedOutput)
                    break;
                
            }

            if(!checkGetExpectedOutput)
                System.out.println("Results don't match expected");
            else{ 
                String[] filePath = args[1].split("/");
                String newFileName = filePath[filePath.length -1].split("\\.")[0];
                
                double sum = 0;
                
                for(double val : storedTimes){
                    sum += val;
                }

                double averageRunTime = sum / storedTimes.size();

                FileWriter writer = new FileWriter(String.format("../output/sequential_%s.csv", newFileName));
                writer.append(Double.toString(averageRunTime));
                writer.close();

                System.out.println(basins.size());
                for(Basin basin : basins){
                    System.out.println(basin.toString());
                }
            }    
        } catch(FileNotFoundException e){
            System.err.println(e);
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}