public class InsertionSortThread extends Thread implements RcBVariantThread {
	private int[] inputData;
	private int[] result;


	public void run() {
		try {
			result = InsertionSort.sort(inputData);

		} catch(ThreadDeath td) {
			// timeout
			result = null;
			System.out.println(toString() + " timed out");
		}
	}

	public InsertionSortThread(int[] inputData) {
		super();
		this.inputData = inputData;
		result = null;
	}

	public int[] getResult() {
		return result;
	}

	public String toString() {
		return "Variant 1 (HeapSort)";
	}
	public int getMemCount() {
		return InsertionSort.getMemCount();
	}


}