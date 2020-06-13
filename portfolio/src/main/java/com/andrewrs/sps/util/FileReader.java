
package com.andrewrs.sps;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReader {
    private StringBuilder data;
  public FileReader(String filePath) {
    //loads file into data string builer object
    loadFile(filePath);
  }
  //loads file into data string builer object
  private void loadFile(String filePath)
  {
    data = new StringBuilder(1024);
    try {
      File file = new File(filePath);
      Scanner reader = new Scanner(file);
      while (reader.hasNextLine()) {
          data.append('\n');
        data.append(reader.nextLine());
      }
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  public String readFile(String filePath) 
  {
    //loads file into data string builer object
    loadFile(filePath);
    return data.toString();
  }
  public String getData()
  {
    return data.toString();
  }
}