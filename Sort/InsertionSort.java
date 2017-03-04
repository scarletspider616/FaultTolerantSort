// JNI tutorial used as reference: 
// https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html

public class InsertionSort {
	static {
		System.loadLibrary("InsertionSort");
	}

	// native method
	private native int[] runInsertionSort(int[] sortThis);

	// Driver (as main for initial testing only)
	public static void main(String [] args) {
		int[] sortThis = {13, -15, 223, 18, -2, 0}; // 4 initial testing only
		int[] result = new InsertionSort().runInsertionSort(sortThis);
		for (int r:result) {
			System.out.println(r);
		}
	}
}