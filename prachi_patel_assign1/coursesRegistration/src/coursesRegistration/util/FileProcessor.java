package coursesRegistration.util;
import coursesRegistration.scheduler.Course;
import coursesRegistration.scheduler.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap; 
public class FileProcessor {
  private ArrayList<Course> course_list = new ArrayList();
  private ArrayList<Student> student_list = new ArrayList();
  private String resultFileName;
  private Map< String,Integer> hm = new HashMap< String,Integer>(); 
    
  BufferedReader b = null;
  public FileProcessor( String[] files) {
 	  String line;
	  /*Reading first input file*/
	  NewFile( files[0] );
  	while ((line = readLine()) != null) {
      try {
    		course_list.add( courseDetails(line) );
 	    } catch(Exception e) {
		    System.out.println("Input file 1 not found:" + e);
    		continue;
   	  }
  	}
    CloseFile();
	  /*Readinf second input file*/
    NewFile( files[1] );
    while ((line = readLine()) != null) {
      try {
        Student s;
        if ( ( s = studentDetails(line) ) != null ) { 
          student_list.add( s ); 
        }
    	} catch(Exception e) {
		    System.out.println("Input file 2 not found:" + e);
    		continue;
      }
   	}
    CloseFile();
	  /*Assigning output file name to a variable*/
    resultFileName = files[2];
  }
  public ArrayList<Course> GetCourseList(){
    return this.course_list;
  }
  public ArrayList<Student> GetStudentList(){
    return this.student_list;
  }
  public String GetResultFileName() {
    return this.resultFileName;
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
  /*Splitting course input file into course details*/ 
  private Course courseDetails(String eachline)
  {
    List<String> course_detail = new ArrayList<String>();
    course_detail.add(eachline.substring(0,eachline.indexOf(" ")));
    course_detail.add(eachline.substring(eachline.indexOf(":")+1,eachline.indexOf(";")));
    course_detail.add(eachline.substring(eachline.lastIndexOf(":")+1));
    Course c = new Course(course_detail);
    return c;
  }	
  /*Splitting student input file into student details*/
  private Student studentDetails(String eachline) {
   	String[] sep = eachline.split("[\\s::]+");
    Student s = null;
    try{
      if ( Integer.parseInt(sep[0]) >= 100 && Integer.parseInt(sep[0]) <= 999 ){
        if ( hm.get(sep[0]) == null ){
          hm.put( sep[0], 1 ) ;
          List<String> sep2 = new ArrayList<String>();
          sep2.add(sep[0]);
          sep2.add(sep[1]);
          sep2.add(sep[2]);       	
          s = new Student(sep2);
        }
        else{
          System.out.println("Same Student Id insterted twice"+" Id was :"+sep[0]);
        }
      }
      else{
        System.out.println("Invalid Student Id should be between 100 and 999"+" Id was :"+sep[0]);
      }
    } catch(NumberFormatException nfe){
      System.out.println("Invalid Student Id should be in numbers"+" Id was :"+sep[0]);
    }
    return s;
  }
}