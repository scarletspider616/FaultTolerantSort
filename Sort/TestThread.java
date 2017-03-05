public class TestThread {
	public static void main(String [] args) {
		int [] data = {1, 7, 3, 3};
		InsertionSortThread thread = new InsertionSortThread(data);
		thread.run();
	}
}