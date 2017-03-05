public class TestThread {
	public static void main(String [] args) {
		int [] data = {1, 7, 3, 3};
		InsertionSortThread thread = new InsertionSortThread(data);
		thread.run();
		try {
			thread.join();
			for(int r: thread.getResult()) {
				System.out.print(r);
				System.out.print(" ");
			}
			System.out.println();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}