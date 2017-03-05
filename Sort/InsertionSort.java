// JNI tutorial used as reference: 
// https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html
import java.util.Scanner;
import java.io.File;

public class InsertionSort {
	// private int[] result;
	private static int memCount;
	static {
		System.loadLibrary("InsertionSort");
	}

	// public InsertionSort(int[] inputData) {
	// 	result = new InsertionSort().runInsertionSort(inputData);
	// } 

	// public int[] getSortedResult() {
	// 	return this.result;
	// }

	// native method
	private native int[] runInsertionSort(int[] sortThis);

	// Driver (as main for initial testing only)
	public static void main(String [] args) {
		int[] sortThis = {-15, 4, 99, 0, 87, 32, 1, -1}; // 4 initial testing only
		int[] result = new InsertionSort().runInsertionSort(sortThis);
		for (int r:result) {
			System.out.println(r);
		}
		System.out.println(getMemCount());
	}

	public static int[] sort(int[] inputData) {
		// mem count is stored in the last element of the array
		int [] result = new InsertionSort().runInsertionSort(inputData);
		return result;
	}

	public static int getMemCount() {
		try {
			File f = new File("~mem_count_insertion.data");
			Scanner scanner = new Scanner(f);
			memCount = scanner.nextInt();
			f.delete();
			return memCount;
		} catch (Exception e) {}
		return 0;
	}
}