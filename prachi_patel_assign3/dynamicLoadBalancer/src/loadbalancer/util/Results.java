package loadbalancer.util;
import loadbalancer.util.FileProcessor;
import loadbalancer.util.FileDisplayInterface;
import loadbalancer.util.StdoutDisplayInterface;

import java.util.ArrayList;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
  
  private String resultFileName;
  private String message;
  public Results (FileProcessor fp) {
    resultFileName = fp.GetResultFileName();
    //message = fp.getmessage();
  }
  public void displayOutput() {
  
  }
  public void displayMessage() {
    System.out.println(message);
  }
}