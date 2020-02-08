package com.jcaseydev;

////////////////////////
// Author: Justin Casey
// Date: 02/07/2020
// Desc: Performs JVM warm up and
//       then runs the benchmark

public class Main {

  public static void main(String[] args) throws UnsortedException {
    int[] sizes = new int[]{100, 200, 300, 400, 500, 1000, 2000, 3000, 4000, 5000};

    BenchmarkSorts warmUp = new BenchmarkSorts(sizes);
    warmUp.runBenchmark();
  }
}
