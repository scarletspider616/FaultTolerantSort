#!/bin/bash

echo "Welcome to FaultTolerantHeapSort Installer " 
echo "By Joey-Michael Fallone"

echo "Setting up classpath..." 
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.

echo "Building Native Methods and threads..."
make clean -C Sort
make all -C Sort


echo "Building Random Number Generator..."
javac DataGenerator/DataGenerator.java

"Installation finished. Please check for any errors in compilation."