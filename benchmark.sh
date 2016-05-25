#!/usr/bin/env bash

echo "Benchmark script compares various serialization methods"
echo "(Please be patient as the whole process takes about 10 minutes on a mobile Core i7 machine)"

mvn clean compile -q

# warm up
for i in {1..10}
    do
        echo "Warming up... [run $i of 10]"
        mvn exec:java -Dexec.args="10000 false" -q
    done

# actual testing
###########################

objects=1
echo "Executing benchmark for 1 object per mode:"
mvn exec:java -Dexec.args="$objects true" -q

objects=10
echo "Executing benchmark for 10 objects per mode:"
mvn exec:java -Dexec.args="$objects true" -q

objects=10000
echo "Executing benchmark for 10000 objects per mode:"
mvn exec:java -Dexec.args="$objects true" -q

echo "Benchmark completed."