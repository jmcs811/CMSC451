package com.jcaseydev;

import java.util.ArrayList;

public class SortedPriorityQueue<T extends Comparable> {

  private ArrayList<T> list = new ArrayList<>();

  // add elements (sort as we add)
  public void add(T object) {
    if (list.isEmpty()) {
      list.add(object);
    } else {
      int i = 0;
      while (i < list.size() && object.compareTo(list.get(i)) > 0) {
        ++i;
      }
      if (i >= list.size()) {
        list.add(object);
      } else {
        list.add(i, object);
      }
    }
  }

  // dequeue
  public T dequeue() {
    return list.remove(0);
  }

  // peek
  public T peek() {
    return list.get(0);
  }
}
