package com.jcaseydev;

public class Main {

  public static void main(String[] args) {
    // write your code here
    int[] arr = {5, 4, 3, 2, 1};
    printArray(arr);
    SortedPriorityQueue<Integer> queue = new SortedPriorityQueue<>();

    for (int i = 0; i < arr.length; i++) {
      queue.add(arr[i]);
    }

    for (int i = 0; i < arr.length; i++) {
      arr[i] = queue.dequeue();
    }
    printArray(arr);
  }

  static void printArray(int[] array) {
    for (int i = 0; i < array.length; i++)
      System.out.print(array[i]);

    System.out.println();
  }
}
