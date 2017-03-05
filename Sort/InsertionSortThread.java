public class InsertionSortThread extends Thread implements RcBVariantThread {
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
		System.out.println(this.result);
		return this.result;
	}

	public String toString() {
		return "Variant 2 (InsertionSort)";
	}
	public int getMemCount() {
		return InsertionSort.getMemCount();
	}


}