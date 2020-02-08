package com.jcaseydev;

////////////////////////
// Author: Justin Casey
// Date: 02/07/2020
// Desc: Performs sorts on 10
//       different input sizes. Each
//       input size is ran 50 times.
//       The results are written to files
//       called iterative.txt and
//       recursive.txt

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkSorts {

  private HeapSort heapSort = new HeapSort();
  private Random random = new Random();

  private int[] dataSize;
  private int numOfDataSets = 50;

  private double[] iterativeCountData = new double[numOfDataSets];
  private double[] iterativeTimeData = new double[numOfDataSets];
  private double[] recursiveCountData = new double[numOfDataSets];
  private double[] recursiveTimeData = new double[numOfDataSets];

  BenchmarkSorts(int[] sizes) {
    dataSize = sizes;
  }

  void runBenchmark() throws UnsortedException {
    for (int value : dataSize) {
      int[] iterativeDataset;
      int[] recursiveDataset;

      for (int j = 0; j < numOfDataSets; j++) {

        iterativeDataset = buildArray(value);
        recursiveDataset = buildArray(value);

        // run each sort 50 times on each dataset size
        heapSort.iterativeSort(iterativeDataset);
        iterativeCountData[j] = heapSort.getCount();
        iterativeTimeData[j] = heapSort.getTime();

        heapSort.recursiveSort(recursiveDataset);
        recursiveCountData[j] = heapSort.getCount();
        recursiveTimeData[j] = heapSort.getTime();
      }
      writeToFile("iterative.txt", value, iterativeCountData, iterativeTimeData);
      writeToFile("recursive.txt", value, recursiveCountData, recursiveTimeData);
    }
  }

  int[] buildArray(int n) {
    int[] temp = new int[n];
    for (int i = 0; i < n; i++) {
      int r = random.nextInt(n + 1);
      temp[i] = r;
    }
    return temp;
  }

  void writeToFile(String fileName, int size, double[] countList, double[] timeList) {
    try {
      FileWriter writer = new FileWriter(fileName, true);
      writer.append(String.valueOf(size)).append(" ");
      for (int i = 0; i < countList.length; i++) {
        writer.append(String.valueOf(countList[i])).append(" ").append(String.valueOf(timeList[i]))
            .append(" ");
      }
      writer.append("\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
