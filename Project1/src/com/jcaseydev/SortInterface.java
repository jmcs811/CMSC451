package com.jcaseydev;

////////////////////////
// Author: Justin Casey
// Date: 02/07/2020
// Desc: interface for different
//       sorting algorithms. This
//       interface defines the
//       required methods.

public interface SortInterface {

  void recursiveSort(int[] list) throws UnsortedException;

  void iterativeSort(int[] list) throws UnsortedException;

  int getCount();

  long getTime();
}
