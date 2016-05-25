# jvm-serialization-benchmark

Benchmark project for measuring the various serialization methods performance. 
There are several serialization methods:
- Serializable
- Externalizable
- JSON – Jackson2
- GSON
- JAX-B

Each one will be ran for various objects count:
- 1
- 10
- 10000

My results are available in `results.pdf`


## running the project

`sh benchmark.sh`  
 
The whole process takes about 10 minutes on a mobile Core i7 machine!