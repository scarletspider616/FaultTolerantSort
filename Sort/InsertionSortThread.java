public class InsertionSortThread extends Thread {
	private int[] inputData;
	private int[] result;


	public void run() {
		try {
			this.result = InsertionSort.sort(inputData);

		} catch(ThreadDeath td) {
			// timeout
			result = null;
			System.out.println(toString() + " timed out");
		}
	}

	public InsertionSortThread(int[] inputData) {
		super();
		this.inputData = inputData;
	}

	public int[] getResult() {
		return this.result;
	}

	public String toString() {
		return "Variant 2 (InsertionSort)";
	}
	public int getMemCount() {
		return InsertionSort.getMemCount();
	}


}