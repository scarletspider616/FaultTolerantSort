
#include <jni.h>
#include <stdio.h>
#include "InsertionSort.h"

void insertion_sort(int *, int, int *);
void swap(int *, int, int, int, int *);
void save_count(int);
// JNI tutorial used as reference: 
// https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html

// JNI func
JNIEXPORT jintArray JNICALL Java_InsertionSort_runInsertionSort( 
	JNIEnv * env, jobject thisObj, jintArray inJNIArray) {
	// convert from java data types to C data types
	jint * input = (*env)->GetIntArrayElements(env, inJNIArray, NULL);
	if (input == NULL) {
		return NULL;
	}
	jsize length = (*env)->GetArrayLength(env, inJNIArray);

	int mem_count = 0;
	int * mem_count_p = &mem_count;
	insertion_sort(input, (int) length, mem_count_p);
	save_count(mem_count);

	// convert back to java data types and output 
	jintArray output = (*env)->NewIntArray(env, length);

	int i;
	jint temp[length];

	for (i = 0; i < (int) length; i++) {
		 temp[i] = input[i];
	}
	if (output == NULL) {
		return NULL;
	}
	(*env)->SetIntArrayRegion(env, output, 0, length, temp);
	return output;
}




// insertion sort implementation in C based on pseudo code found at: 
// https://en.wikipedia.org/wiki/Insertion_sort

// operating under assumption that a checkpoint has been saved elsewhere
// so insertion sort will in fact rewrite the input data in the memory 
// provided to it.

// How this will relate into java calling it as a thread and then a native
// C method could possibly change things moving forward. At this point unclear
void insertion_sort(int * sort_this_list, int length_of_list, 
		int * mem_counter) {
	int i;
	for(i = 1; i < length_of_list; i++) {
		// start at leftmost, make our way all the way to the right
		int j = i;
		// if prev element is greater than curr element swap these two
		// continue left until end of list or the prev element is less than or 
		// equal to the next element.
		// int a;
		// for(a = 0; a < length_of_list; a++) {
		// 	printf("%d ", sort_this_list[a]);
		// }
		// printf("\n");
		(*mem_counter)++;
		(*mem_counter)++;
		while(j > 0 && sort_this_list[j-1] > sort_this_list[j]) {
			swap(sort_this_list, length_of_list, j, j-1, mem_counter);
			j--;
		}
	}
}

// takes in the pointer to the int array as well as the indexes of the two
// elements that need to be swapped in this list
// Also requires the length of the list
void swap(int * entire_list, int length_of_list, int i1, int i2, 
		int * mem_count) {
	// make sure indexs are in range
	// since list is 0 indexed, cannot be >= len of list
	if(i1 >= length_of_list | i2 >= length_of_list) {
		// TODO: THROW EXCEPTION!!!
	}
	// printf("%d: %d %d: %d\n",i1, entire_list[i1], i2, entire_list[i2]);
	(*mem_count)++;
	(*mem_count)++;
	int temp = entire_list[i1];
	entire_list[i1] = entire_list[i2];
	entire_list[i2] = temp;
}

void save_count(int count) {
	FILE *file;
	file = fopen("~mem_count_insertion.data", "w+");
	fprintf(file, "%d", count);
	fclose(file);

}

