import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

/**
 * Runner class for parallel implementation that stores main method
 */
public class ParallelBasinRunner {
    static final ForkJoinPool fjPool = new ForkJoinPool();
    
    /**
     * Entry point for parallel solution that runs
     * @param mountain 2d array contain input terrain data
     * @param sequentialCutoff the value you want to set your sequential cutoff to for the run
     * @return basins list of all found basins
     */
    static ArrayList<Basin> findBasins(float[][] mountain, int sequentialCutoff ){
        ParallelBasinClassify.SEQUENTIAL_CUTOFF = sequentialCutoff;
        ParallelBasinClassify.threadNumbers = 0;
        return fjPool.invoke(new ParallelBasinClassify(mountain, 1, mountain.length - 1, 1, mountain[0].length - 1));
    }

    /**
     * 
     * @param args[0] location of input terrain data
     * @param args[1] location of expected basins data
     */
    public static void main(String[] args) {
        try{
            float[][] mountain = HelperMethods.loadMountainInputData(args[0]);
            String[] expectedBasins = HelperMethods.loadExpectedBasins(args[1]);

            HashMap<String,ArrayList<Double>> storedTimesForDifferentCutoffs = new HashMap<String, ArrayList<Double>>();
            int maxSequentialCutoff = 100;
            int averageRuns = 3; 

            boolean checkGetExpectedOutput = false;

            ArrayList<Basin> basins = new ArrayList<Basin>();

            for(int sequentialCutoff = 1; sequentialCutoff <= maxSequentialCutoff; sequentialCutoff++){
                String cutoffValStr = Integer.toString(sequentialCutoff);
                storedTimesForDifferentCutoffs.put(cutoffValStr, new ArrayList<Double>());

                for(int i = 0; i < averageRuns; i++){

                    System.gc();

                    //timed module
                    long startTime = HelperMethods.tick();
                    basins = findBasins(mountain, sequentialCutoff);
                    double runTime = HelperMethods.tock(startTime);

                    checkGetExpectedOutput = HelperMethods.checkMatchingResults(expectedBasins, basins);

                    if(!checkGetExpectedOutput)
                        break;

                    storedTimesForDifferentCutoffs.get(cutoffValStr).add(runTime);
                }

                //comment back in line if you want to know number of threads used for each sequentialCutoff value
                //System.out.println(String.format("For sequentialCutoff : %d, Threads used : %d", sequentialCutoff, ParallelBasinClassify.threadNumbers));

                if(!checkGetExpectedOutput)
                    break;
            }

            if(!checkGetExpectedOutput)
                System.out.println("Results don't match expected");
            else{ 
                String[] filePath = args[1].split("/");
                String newFileName = filePath[filePath.length -1].split("\\.")[0];
                
                HelperMethods.writeToCSVFile(storedTimesForDifferentCutoffs, String.format("../output/%s.csv",newFileName));   
                
                System.out.println(basins.size());

                for(Basin basin : basins){
                    System.out.println(basin.toString());
                }

            }
            
        } catch(FileNotFoundException e){
            System.err.println(e);
        } catch(IOException e){
            System.err.println(e);
        }
    }

}