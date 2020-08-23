import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class ParallelBasinRunner {
    static long startTime = 0;
    static final ForkJoinPool fjPool = new ForkJoinPool();

    private static void tick(){
		startTime = System.currentTimeMillis();
    }
    
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}

    static ArrayList<Basin> findBasins(float[][] mountain){
        return fjPool.invoke(new ParallelBasinClassify(mountain, 1, mountain.length - 1, 1, mountain[0].length - 1));
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        int row = reader.nextInt();
        int col = reader.nextInt();

        float[][] mountain = new float[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                mountain[i][j] = reader.nextFloat();
            }
        }

        ArrayList<Basin> basins = findBasins(mountain);

        System.out.println(basins.size());
        for(Basin foundBasin : basins){
            System.out.println(foundBasin.toString());
        }
    }

}