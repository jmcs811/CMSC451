package com.jcaseydev;

////////////////////////
// Author: Justin Casey
// Date: 02/07/2020
// Desc: Defines the recursive
//       and iterative versions
//       of the heapsort algorithm

public class HeapSort implements SortInterface {

  private int count;
  private long time;

  @Override
  public void recursiveSort(int[] list) throws UnsortedException {
    count = 0;
    time = 0;

    long startTime = System.nanoTime();

    int n = list.length;

    // build heap
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(list, n, i);
    }

    // get element from heap 1 by 1
    for (int i = n - 1; i >= 0; i--) {
      int temp = list[0];
      list[0] = list[i];
      list[i] = temp;

      // call heapify to reduce heap
      heapify(list, i, 0);
      count++;
    }
    long endTime = System.nanoTime();
    time = (endTime - startTime);
    if (!isSorted(list)) {
      throw new UnsortedException();
    }
  }

  private void heapify(int[] list, int n, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    // left larger than root
    if (left < n && list[left] > list[largest]) {
      largest = left;
    }

    // right larger than root
    if (right < n && list[right] > list[largest]) {
      largest = right;
    }

    // recursively heapify the subtree
    if (largest != i) {
      int swap = list[i];
      list[i] = list[largest];
      list[largest] = swap;

      heapify(list, n, largest);
    }
  }

  @Override
  public void iterativeSort(int[] list) throws UnsortedException {
    count = 0;
    time = 0;

    long startTime = System.nanoTime();

    int n = list.length;
    buildMaxHeap(list, n);

    for (int i = n - 1; i > 0; i--) {
      swap(list, 0, i);
      int j = 0, index;

      do {
        index = (2 * j + 1);

        // if left child is smaller than
        // right child point index variable
        // to right child
        if (index < (i - 1) && list[index] < list[index + 1]) {
          index++;
        }

        // if parent is smaller than child
        // then swapping parent with child
        // having higher value
        if (index < i && list[j] < list[index]) {
          swap(list, j, index);
        }

        j = index;

      } while (index < i);
    }
    long endTime = System.nanoTime();
    time = (endTime - startTime);
    System.out.println(
        "\n\n***** TIME *****\n" + startTime + "\n" + endTime + "\n" + time + "\n**************\n");
    if (!isSorted(list)) {
      throw new UnsortedException();
    }
  }

  private void buildMaxHeap(int list[], int n) {
    for (int i = 1; i < n; i++) {
      // if child is bigger than parent
      if (list[i] > list[(i - 1) / 2]) {
        int j = i;

        // swap child and parent until
        // parent is smaller
        while (list[j] > list[(j - 1) / 2]) {
          swap(list, j, (j - 1) / 2);
          j = (j - 1) / 2;
        }
      }
    }
  }


  private void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  @Override
  public int getCount() {
    return count;
  }

  @Override
  public long getTime() {
    return time;
  }

  private boolean isSorted(int[] list) {
    for (int i = 0; i < list.length - 1; i++) {
      if (list[i] > list[i + 1]) {
        return false;
      }
    }
    return true;
  }
}
