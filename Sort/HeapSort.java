// Heapsort for java based on pseudo code found at 
// http://www.cc.gatech.edu/classes/cs3158_98_fall/heapsort.html
// Written for lists of integers rather than generically as 
// this module should only be used with the Fault Tolerant 
// heapsort program it is packaged with

package java.FaultTolerant.HeapSort;

public class HeapSort {
	private ArrayList<Integer> values;
	private int lenOfList;
	private int heapSize;

	public HeapSort(ArrayList<Integer> toBeSorted, int lenOfList) {
		this.values = toBeSorted;
		this.lenOfList = lenOfList;
		sort();
	}

	public ArrayList<Integer> getSortedList() {
		return this.values;
	}

	private void sort() {
		buildHeap(values);
		for (int i = this.lenOfList; i > 1; i--) {
			swap(1, i);
			this.heapSize--;
			heapify(1);
		}
	}

	private void buildHeap() {
		this.heapSize = this.lenOfList;
		for (int i = floor(this.lenOfList/2); i > 2; i--) {
			heapify(i);
		}
	}

	private void heapify(int index) {
		int leftIndex = 2*index;
		int rightIndex = leftIndex + 1;
		int max = index;

		if (leftIndex <= this.lenOfList && 
			this.values.get(leftIndex) > this.values.get(index)) {
			max = leftIndex;
		}
		else {
			max = index;
		}

		if (rightIndex <= this.lenOfList && 
			this.values.get(rightIndex) > this.values.get(index)) {
			max = rightIndex;
		}

		if (max != index) {
			swap(index, max);
			heapify(max);
		}
	}

	private void swap(int index1, int index2) {
		int temp = this.values.get(index1);
		this.values.set(index1, this.values.get(index2));
		this.values.set(index2, temp);
	}
}