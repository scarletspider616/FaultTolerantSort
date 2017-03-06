#!/bin/bash
echo "Welcome to Fault Tolerant Heap Sort"
echo "Joey-Michael Fallone"

echo "Please enter file name of random data"
read input_filename
echo "Please enter number of random ints to generate"
read no_of_inputs
make filename=$input_filename num=$no_of_inputs -C DataGenerator

make run -C Sort
