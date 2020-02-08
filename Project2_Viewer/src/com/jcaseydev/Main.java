package com.jcaseydev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Main extends JFrame {

  Object[][] tableData = new Object[10][5];

  private void processData(String data) {
    Scanner scanner = new Scanner(data);

    for (int i = 0; i < tableData.length; i++) {
      int size = scanner.nextInt();
      double[] timeData = new double[50];
      double[] countData = new double[50];
      for (int j = 0; j < timeData.length; j++) {
        countData[j] = scanner.nextDouble();
        timeData[j] = scanner.nextDouble();
      }
      // set values
      tableData[i][0] = size;
      tableData[i][1] = getMean(countData);
      tableData[i][2] = getCoef(countData);
      tableData[i][3] = getMean(timeData);
      tableData[i][4] = getCoef(timeData);
    }

    scanner.close();
  }

  private double getMean(double[] list) {
    double sum = 0;
    for (double i : list) {
      sum += i;
    }
    return sum / list.length;
  }

  private double getStdDev(double[] list) {
    double sum = 0;
    for (double i : list) {
      sum += (i - getMean(list)) * (i - getMean(list));
    }
    return Math.sqrt(sum / (list.length - 1));
  }

  private double getCoef(double[] list) {
    return ((getStdDev(list)) / getMean(list)) * 100;
  }

  public Main() {

    JFileChooser fileChooser = new JFileChooser();

    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      try {
        File file = fileChooser.getSelectedFile();
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder data = new StringBuilder();
        String line = "";
        while((line = br.readLine()) != null) {
          data.append(line);
        }
        processData(data.toString());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // headers for the table
    String[] columns = new String[]{"Size", "Avg Count", "Coef Count", "Avg Time", "Coef Time"};

    // create table with data
    JTable table = new JTable(tableData, columns);
    this.add(new JScrollPane(table));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    this.setTitle("CMSC 451 PROJ. 2 VIEWER");
  }

  public static void main(String[] args) {
    // write your code here
    new Main();
  }
}
