
import java.util.concurrent.ThreadLocalRandom;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;

public class DataGenerator {

	/**
	* Constructor for DataGenerator Class. Takes in number of random values 
	* to generate, as well as the output filename (context: pwd). 
	* The constructor will automatically execute the random number generation 
	* and then write the data to the file.
	**/
	public DataGenerator(String outputFileName, int numOfValues) {
		int randomValues[] = new int [numOfValues];
		for(int i=0; i < numOfValues; i++) {
				randomValues[i] = getRandomInt();
		}
		writeValues(randomValues, outputFileName);
	}

	public static void main(String [] args) {
		try {
			DataGenerator gen = new DataGenerator(args[0],
			 Integer.parseInt(args[1]));
		} catch (Exception e) {
			System.out.println("Please enter args: 'filename' 'no. of ints'");
			System.exit(-1);
		}
	}

	private int getRandomInt() {
		// http://stackoverflow.com/questions/363681/generating-random-integers-in-a-specific-range
		return ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, 
			Integer.MAX_VALUE);
	}

	private void writeValues(int[] writeValues, String outputFilename) {
		System.out.println("Random Values Produced: ");
		for(int r: writeValues) {
			System.out.print(r);
			System.out.print(" ");
		}
		System.out.println(" ");
		// based on:
		// http://stackoverflow.com/questions/12350248/java-difference-between-filewriter-and-bufferedwriter
		try {
		    BufferedWriter bw = new BufferedWriter(
		    	new FileWriter(outputFilename));
		    String output = "";
		    for(int number: writeValues) {
		    	bw.write(Integer.toString(number) + " ");
		    	bw.flush();
		    }
		 } catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println(
	    	"Random vals should be available in " + outputFilename);
	}
}
