public class HeapSortThread extends Thread implements RcBVariantThread {
	private int[] inputData;
	private int[] result;

	public void run(){
		try {
			this.result = HeapSort.sort(inputData, inputData.length);

		} catch(ThreadDeath td) {
			// timeout
			this.result = null;
			System.out.println(toString() + "Timed out");
		}
	}

	public HeapSortThread(int[] inputData) {
		super();
		this.inputData = inputData;
		this.result = null;
	}

	public int[] getResult() {
		return null;
		// return this.result;
	}

	public String toString() {
		return "Variant 1 (HeapSort) ";
	}
}