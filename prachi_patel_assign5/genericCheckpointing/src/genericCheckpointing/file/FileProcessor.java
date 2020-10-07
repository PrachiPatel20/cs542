package genericCheckpointing.file;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileProcessor {
  ArrayList<String> dmline = new ArrayList<>();
  private BufferedReader b = null;
  public FileProcessor(String filenameIn) {
    String line;
    NewFile(filenameIn);
    while((line = readLine())!=null) {
      try {
        if(line.equals("<DPSerialization>")) {
          line = readLine();
          while(!(line.equals("</DPSerialization>"))) {
            dmline.add(line.trim());
            line = readLine();
          }
        }
        //sortLines(line);
      } catch(Exception e) {
        System.out.println("Input file not found");
        continue;
      }
    }
    CloseFile();
  }
  public ArrayList<String> getList() {
    return this.dmline;
  }
  
  private void NewFile(String file){
    try{
	    b = new BufferedReader(new FileReader(file));
	  } catch(IOException e){
   	  System.out.println("File for new line not found:" + e);
	    e.printStackTrace();	
	  }
  }
  
  private void CloseFile(){
    try{
      b.close();    
   	} catch(IOException e){
      System.out.println("file to close not found:" + e);
      e.printStackTrace();	
   	}
  }

  private String readLine() {
	  String inp = "";
	  String eachline = null;
	  try{
 	    if((inp = b.readLine()) != null) {
		    eachline = inp;	
	    }
	  } catch (IOException e) {
	    System.out.println("New line not assigned:" + e);
	    e.printStackTrace();
	  }
	  return eachline;
  }
  
  private ArrayList<String> sortLines(String line) {
    return null;
  }

	
}
