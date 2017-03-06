#!/bin/bash

echo "Welcome to FaultTolerantHeapSort Installer " 
echo "By Joey-Michael Fallone"

echo "Buidling project..."
cd sort 
echo "Setting up classpath..." 
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.
echo "Building Native Methods..."
make clean
make all
javac Driver.java

cd ../DataGenerator
echo "Building Random Number Generator"
