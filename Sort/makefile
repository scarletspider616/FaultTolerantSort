CC=gcc

CFLAGS=-c -fPIC -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers/ 

run: all
	java InsertionSort

all: compilejava createJNIheader makenative linklibrary setjavapath

compilejava: InsertionSort.Java
	javac InsertionSort.java

createJNIheader: InsertionSort.class
	javah InsertionSort

makenative: insertion_sort.c InsertionSort.h
	$(CC) $(CFLAGS) insertion_sort.c -o libInsertionSort.o

linklibrary: libInsertionSort.o 
	libtool -dynamic -lSystem libInsertionSort.o -o libInsertionSort.dylib

setjavapath: 
	export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.

clean:
	rm InsertionSort.class libInsertionSort.* InsertionSort.h