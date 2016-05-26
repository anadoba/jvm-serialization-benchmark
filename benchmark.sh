#!/usr/bin/env bash

echo "Benchmark script compares various serialization methods"
echo -e "(Please be patient as the whole process takes about 3 minutes on a mobile Core i7 machine)\n"

mvn clean compile -q

# warm up
for i in {1..50}
    do
        echo "Warming up... [run $i of 50]"
        mvn exec:java -Dexec.args="100000 false" -q
    done

# actual testing
###########################

objects=1
echo -e "\nExecuting benchmark for 1 object per mode:"
mvn exec:java -Dexec.args="$objects true" -q

objects=10
echo -e "\nExecuting benchmark for 10 objects per mode:"
mvn exec:java -Dexec.args="$objects true" -q

objects=10000
echo -e "\nExecuting benchmark for 10000 objects per mode:"
mvn exec:java -Dexec.args="$objects true" -q

echo -e "\nBenchmark completed."