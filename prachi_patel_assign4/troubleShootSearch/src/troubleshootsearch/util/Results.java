package troubleshootsearch.util;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.FileDisplayInterface;
import troubleshootsearch.util.StdoutDisplayInterface;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
  private String resultFileName;
  private ArrayList<String> output = new ArrayList();
	public Results(FileProcessor fp) {
    resultFileName = fp.getResultFileName();
    output.addAll(fp.getOutput());
  }
  
  public void printOnScreen(){
    try {
      FileWriter fw = new FileWriter(resultFileName,false);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      
      for(String out : output) {
        pw.write(out + "\n");
      }
    
      pw.close();    
    } catch(Exception e) {
      System.out.println("Output File not found" + e);
    }
  }


}
