// Heapsort for java based on pseudo code found at 
// http://www.codecodex.com/wiki/Heapsort#Pseudocode
// Written for lists of integers rather than generically as 
// this module should only be used with the Fault Tolerant 
// heapsort program it is packaged with

public class HeapSort {

	// for initial testing only!
	public static void main(String[] args) {
		int[] initialTest = {-10, 0, -22, 33, 44, 98, 97};
		int [] result = HeapSort.sort(initialTest, 7);
		for(int r: result) {
			System.out.print(r);
			System.out.print(" ");
		}
		System.out.println(" ");
	}

	public static int[] sort(int[] sortThis, int length) {
		heapSort(sortThis, length);
		return sortThis;
	}

	private static void heapSort(int[] list, int count) {
		// something 
		int start = count/2 - 1;
		int end   = count - 1;

		while(start >= 0) {
			sift(list, start, count);
			start = start - 1;
		}

		while (end > 0) {
			swap(list, end, 0);
			sift(list, 0, end);
			end = end - 1;
		}
	}

	private static void sift(int[] list, int start, int count) {
		int root = start;
		int child;

		while(root*2 + 1 < count) {
			child = root * 2 + 1;

			if(child < count - 1 && list[child] < list[child + 1]) {
				child = child + 1;
			}
			if(list[root] < list[child]) {
				swap(list, root, child);
				root = child;
			}
			else
				return;
		}
	}

	private static void swap(int[] array, int i1, int i2) {
		int temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
}