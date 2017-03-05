import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class Driver {
	private static String inputFilename;
	private static String outputFilename;
	private static int primaryFailureRate;
	private static int secondaryFailureRate;
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
			if(Driver.adjudicate(primary.getResult())) {
				System.out.println(primary.getResult());
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
			t.cancel();
			// check results
			if(Driver.adjudicate(secondary.getResult())) {
				Driver.writeResult(secondary.getResult());
			}
			else {
				Driver.writeFailedResult();
			}
		} catch (InterruptedException e) {

		}
	}

	private static Boolean adjudicate(int [] result, int count, int prob) {
		if(result == null) return false;
		if(sum(Driver.readInputData()) != sum(result)) return false;
		for(int i = 1; i < result.length; i++) {
			if(result[i] < result[i-1]) return false;
		}
		if(hardwarePassed(count, prob)) return true;
		return false;		
	}

	private static int sum(int [] numbers) {
		int sum = 0;
		for(int number:numbers) {
			sum = sum + number;
		}
		return sum;
	}

	private static Boolean hardwarePassed(int count, int prob) {
		// algorithm to simlute hardware failures based on inputted probs

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
		for(int r: result) {
			System.out.print(r);
			System.out.print(" ");
		}
		System.out.println(" ");
		// based on:
		// http://stackoverflow.com/questions/12350248/java-difference-between-filewriter-and-bufferedwriter
		try {
		    BufferedWriter bw = new BufferedWriter(
		    	new FileWriter(outputFilename));
		    System.out.println(outputFilename);
		    String output = "";
		    for(int number: result) {
		    	output = output + Integer.toString(number) + " ";
		    }
			bw.write(output);
			bw.flush();
			System.out.println("Done");
		 } catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println(
	    	"Results should be available in " + outputFilename);
	}

	private static void writeFailedResult() {
		try {
		    PrintWriter writer = new PrintWriter(outputFilename, "UTF-8");
		    writer.println("Unfortunately the sort process has failed.");
		    System.out.println("Failure.");
		} catch (IOException e) {
		   // do something
		}
	}

	private static String getInputFilename() {
		return commandLineStringResponse("Please enter name of input data "+
					"file: ");
	}

	private static String getOutputFilename() {
		return commandLineStringResponse("Please choose name for new file " +
						"to countain output data: ");
	}

	private static int getSecondaryFailureRate() {
		try {
			return commandLineIntResponse("Please enter Second Variant " +
					"failure prob: ");
		} catch (InputMismatchException e) {
			System.out.println("ERROR: Input was not valid int");
			System.exit(-1);
		}
		return -1;
	}

	private static int getPrimaryFailureRate() {
		try {
			return commandLineIntResponse("Please enter First Variant " +
					"failure prob: ");
		} catch (InputMismatchException e) {
			System.out.println("ERROR: Input was not valid int");
			System.exit(-1);
		}
		return -1;
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
}
