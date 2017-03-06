CC=gcc

CFLAGS= 
MACSTEP=
CARGS=

MACFLAGS=-c -fPIC -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers/ 

LINUXFLAGS=-shared -fpic -o libInsertionSort.so -I$(JAVA_HOME)/include/ -I$(JAVA_HOME)/include/linux/ 

LINUXARGS=insertion_sort.c

MACARGS=insertion_sort.c -o

UNAME_S:=$(shell uname -s)
ifeq ($(UNAME_S),Linux)
	CFLAGS+=$(LINUXFLAGS)
	CARGS+=$(LINUXARGS)
endif
ifeq ($(UNAME_S),Darwin)
	CFLAGS+=$(MACFLAGS)
	MACSTEP+=libtool -dynamic -lSystem libInsertionSort.o -o libInsertionSort.dylib libInsertionSort.o
	CARGS+=$(MACARGS)
endif

run: all
	java InsertionSort

all: compilejava createJNIheader makenative linklibrary setjavapath

compilejava: 
	javac InsertionSort.java

createJNIheader: InsertionSort.class
	javah InsertionSort

makenative: insertion_sort.c InsertionSort.h
	$(CC) $(CFLAGS) $(CARGS)

linklibrary:  
	$(MACSTEP)


setjavapath: 
	export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.

clean:
	rm -f *.class libInsertionSort.* InsertionSort.h *.log
