import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;


public class Driver {
	private static String inputFilename;
	private static String outputFilename;
	private static Double primaryFailureRate;
	private static Double secondaryFailureRate;
	private static int[] inputData;
	private static int timeout;

	public static void main(String[] args) {
		// grab user inputs
		inputFilename = Driver.getInputFilename();
		outputFilename = Driver.getOutputFilename();
		primaryFailureRate = Driver.getPrimaryFailureRate();
		secondaryFailureRate = Driver.getSecondaryFailureRate();
		inputData = Driver.readInputData();
		timeout = Driver.getTimeout();

		// run primary and start watchdog timer
		runPrimary();
	}

	private static void runPrimary() {
		// run primary and start watchdog timer
		System.out.println("Running Primary variant");
		HeapSortThread primary = 
		new HeapSortThread(inputData);
		Timer t = new Timer();
		Watchdog w = new Watchdog(primary);

		// start primary & WD timer
		t.schedule(w, timeout);
		primary.start();
		try {
			primary.join();
			t.cancel();
			if(Driver.adjudicate(primary.getResult(), primary.getMemCount(),
					primaryFailureRate)) {
				Driver.writeResult(primary.getResult());
			}
			else {
				System.out.println("ERROR: Primary failed AT!\n" + 
					"Running secondary....");
				Driver.runSecondary();
			}
		}
		catch (InterruptedException e) {

		}
	}

	private static void runSecondary() {
		InsertionSortThread secondary = 
		new InsertionSortThread(Driver.readInputData());
		Timer t = new Timer();
		Watchdog w = new Watchdog(secondary);


		// start secondary & WD timer
		t.schedule(w, timeout);
		secondary.start();
		try {
			secondary.join();
			// for(int r: secondary.getResult()) {
			// 	System.out.print(r);
			// }
			// System.out.println();
			t.cancel();
			// check results
			if(Driver.adjudicate(secondary.getResult(), secondary.getMemCount(),
					secondaryFailureRate)) {
				Driver.writeResult(secondary.getResult());
			}
			else {
				// for(int r:secondary.getResult()) {
				// 	System.out.print(r);
				// 	System.out.print(" ");
				// }
				// System.out.println();
				Driver.writeFailedResult();
			}
		} catch (InterruptedException e) {

		}
	}

	private static Boolean adjudicate(int [] result, int count, double prob) {
			// System.out.println(result);
		if(result == null) {
			System.out.println("ADJUDICATOR: was null");
			return false;
		}
		if(sum(Driver.readInputData()) != sum(result)) {
			System.out.println("ADJUDICATOR: sum mismatch");
			return false;
		}
		for(int i = 1; i < result.length; i++) {
			if(result[i] < result[i-1]) {
				System.out.println("ADJUDICATOR: elements not ascending");
				return false;
			}
		}
		if(hardwarePassed(count, prob)) {
			System.out.println("ADJUDICATOR: passed");
			return true;
		}
		else {
			System.out.println("ADJUDICATOR: hardware failure (mem)");
			return false;
		}
	}

	private static int sum(int [] numbers) {
		int sum = 0;
		for(int number:numbers) {
			sum = sum + number;
		}
		return sum;
	}

	private static Boolean hardwarePassed(int count, double prob) {
		// algorithm to simlute hardware failures based on inputted probs
		// hazard = count * prob
		// if random number is in range [0.5, 0.5+HAZARD] harware fails
		double hazard = count * prob; 
		double random = Math.random();
		System.out.println("Hazard: " + hazard + " Random: " + random);
		if(random < 0.5) return true;
		if(random - (0.5 + hazard) <= 0) {
			return false;
		}
		return true;
	}

	private static int[] readInputData() {
		// based on 
		// https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
		try {
			Scanner scanner = new Scanner(new File(inputFilename));
			ArrayList<Integer> inputData = new ArrayList<Integer>();
			while(scanner.hasNextInt()) {
				inputData.add(scanner.nextInt());
			}
			int [] result = new int[inputData.size()];
			for(int i = 0; i < inputData.size(); i++) {
				result[i] = inputData.get(i);
			}
			return result;
		} catch (Exception e) {
			System.out.println("ERROR: Could not read input file");
			System.out.println(e.toString());
			System.exit(-1);
		}
		return null;
	}

	private static void writeResult(int [] result) {
		// for(int r: result) {
		// 	System.out.print(r);
		// 	System.out.print(" ");
		// }
		// System.out.println(" ");
		// based on:
		// http://stackoverflow.com/questions/12350248/java-difference-between-filewriter-and-bufferedwriter
		try {
		    BufferedWriter bw = new BufferedWriter(
		    	new FileWriter(outputFilename));
		    String output = "";
		    for(int number: result) {
		    	output = output + Integer.toString(number) + " ";
		    }
			bw.write(output);
			bw.flush();
		 } catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println(
	    	"Results should be available in " + outputFilename);
	}

	private static void writeFailedResult() {

		// delete output.txt if it exists. 
		try {
			File f = new File(outputFilename);
			f.delete();
		} catch (Exception e) {}
	}

	private static String getInputFilename() {
		return "../" + commandLineStringResponse("Please enter name of input data "+
					"file: ");
	}

	private static String getOutputFilename() {
		return "../" + commandLineStringResponse("Please choose name for new file " +
						"to countain output data: ");
	}

	private static Double getSecondaryFailureRate() {
		try {
			return commandLineDoubleResponse("Please enter Second Variant " +
					"failure prob: ");
		} catch (InputMismatchException e) {
			System.out.println("ERROR: Input was not valid double");
			System.exit(-1);
		}
		return null;
	}

	private static Double getPrimaryFailureRate() {
		try {
			return commandLineDoubleResponse("Please enter First Variant " +
					"failure prob: ");
		} catch (InputMismatchException e) {
			System.out.println("ERROR: Input was not valid double");
			System.exit(-1);
		}
		return null;
	}

	private static int getTimeout() {
		try {
			return commandLineIntResponse("Please enter timeout (ms, int): ");
		} catch (InputMismatchException e) {
			System.out.println("ERROR: Input was not valid int");
			System.exit(-1);
		}
		return -1;
	}

	private static String commandLineStringResponse(String prompt) {
		System.out.print(prompt);
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

	private static int commandLineIntResponse(String prompt) {
		System.out.print(prompt);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

	private static Double commandLineDoubleResponse(String prompt) {
		System.out.print(prompt);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextDouble();
	}
}
