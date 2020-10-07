package courseplanner.util;
import courseplanner.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap; 

public class FileProcessor {
  private ArrayList<Student> student_list = new ArrayList();
  private String resultFileName;
  private Map< String,Integer> hm = new HashMap< String,Integer>();
  
	private BufferedReader b = null;
  
  public FileProcessor( String[] files) {
 	  String line;
	  /*Reading first input file*/
     
	  newFile( files[0] );
  	
    while ((line = readLine()) != null) {
      try {
    		Student s = studentDetails(line);
        if ( s != null ) { 
          student_list.add( s );
        }
        
        else {
          System.out.println("Error Student null");
        }
        
 	    } catch(Exception e) {
		    System.out.println("Input file 1 not found:" + e);
    		continue;
   	  }
  	}
    closeFile();
	  /*Assigning output file name to a variable*/
    resultFileName = files[1];
  }
  public ArrayList<Student> getStudentList(){
    return this.student_list;
  }
  public String getResultFileName() {
    return this.resultFileName;
  }
  private void newFile(String file){
    try{
	    b = new BufferedReader(new FileReader(file));
	  } catch(IOException e){
   	  System.out.println("File for new line not found:" + e);
	    e.printStackTrace();	
	  }
  }
  private void closeFile(){
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
  private Student studentDetails(String eachline) {
   	String[] sep = eachline.split(": ");
  
    Student s = null;
    try{
      if ( Integer.parseInt(sep[0]) >= 1000 && Integer.parseInt(sep[0]) <= 9999 ){
        if ( hm.get(sep[0]) == null ){      

          hm.put( sep[0], 1 ) ;
          List<String> sep2 = new ArrayList<String>();
          sep2.add(sep[0]);
          sep2.add(sep[1]);   	
          s = new Student(sep2);
        }
        else{
          System.out.println("Same Student Id insterted twice"+" Id was :"+sep[0]);
        }
      }
      else{
        System.out.println("Invalid Student Id should be between 1000 and 9999"+" Id was :"+sep[0]);
      }
    } catch(NumberFormatException nfe){
      System.out.println("Invalid Student Id should be in numbers"+" Id was :"+sep[0]);
    }
    return s;
  }
}
