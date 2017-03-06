package project1.datagen;

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
		int randomValues[numOfValues];
		for(int i=0; i < numOfValues; i++) {
				randomValues[i] = getRandomInt();
		}
	}
}
