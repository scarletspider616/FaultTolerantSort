public class ExecutiveThread extends Thread {
	private int[] inputData;
	private int[] result;

	public void run() {
		try {
			result = null;
			// start primary 
			HeapSortThread
			while(!isGoodResult(result)) {

			}
			
		} catch(ThreadDeath td) {
			// timeout
			result = null;
			// System.out.println(toString() + " timed out");
		}
	}

	public ExecutiveThread(int[] inputData) {
		super();
		inputData = inputData;
		result = null;
	}

	public String toString() {
		return "ExecutiveThread";
	}

	private Boolean isGoodResult(int[] input) {
		//TODO: actual logic
		if(result == null) return false;
		return false;
	}
}