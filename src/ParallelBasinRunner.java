import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class ParallelBasinRunner {
    static final ForkJoinPool fjPool = new ForkJoinPool();
    
    static ArrayList<Basin> findBasins(float[][] mountain){
        return fjPool.invoke(new ParallelBasinClassify(mountain, 1, mountain.length - 1, 1, mountain[0].length - 1));
    }

    public static void main(String[] args) {
        try{
            float[][] mountain = HelperMethods.loadMountainInputData(args[0]);
            String[] expectedBasins = HelperMethods.loadExpectedBasins(args[1]);

            ArrayList<Basin> basins = findBasins(mountain);

            System.out.println(basins.size());
            for(Basin foundBasin : basins){
                System.out.println(foundBasin.toString());
            }
        } catch(FileNotFoundException e){
            System.err.println(e);
        }
    }

}