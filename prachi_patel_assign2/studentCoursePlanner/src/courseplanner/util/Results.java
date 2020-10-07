package courseplanner.util;
import courseplanner.Student;
import courseplanner.util.FileProcessor;
import courseplanner.util.FileDisplayInterface;
import courseplanner.util.StdoutDisplayInterface;

import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	private ArrayList<Student> student_list = new ArrayList();
  private String resultFileName;
  
  public Results (FileProcessor fp) {
    student_list = fp.getStudentList();
    resultFileName = fp.getResultFileName();
  }
  
  public void fileDisplayInfo(){
    for(int i=0;i<student_list.size();i++)
    {
      Student s = student_list.get(i);
      
      s.findPattern();    
      
//      System.out.print("\n" + s.GetId() );
//      System.out.print("\t State Changes: " + s.GetStateChange() );
//      System.out.print("\t Semester: " + s.GetSemester() );
//      System.out.print(" | ");
      ArrayList<Character> cList = s.getCourseList();
      for ( char apple : cList ){
        //System.out.print( apple + " " );
      }
    }
    
  }
  /* Display output in the file*/
  public void printOnScreen(){
    try {
      FileWriter fw = new FileWriter(resultFileName,false);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      for(int i=0;i<student_list.size();i++)
      {
        Student s = student_list.get(i);
      
        s.findPattern();    
      
      
        pw.write(s.getId() + ":");
        ArrayList<Character> cList = s.getCourseList();
        for ( char apple : cList ){
          pw.write( apple + " " );
        }
        pw.write(" -- ");
        pw.write("\t" + s.getSemester() );
        pw.write("\t" + s.getStateChange() );
      }
      pw.close();    
    } catch(Exception e) {
      System.out.println("Output File not found" + e);
    }
  }

}
