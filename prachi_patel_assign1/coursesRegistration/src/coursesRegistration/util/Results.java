package coursesRegistration.util;
import coursesRegistration.scheduler.Course;
import coursesRegistration.scheduler.Student;
import coursesRegistration.util.FileProcessor;
import coursesRegistration.util.FileDisplayInterface;
import coursesRegistration.util.StdoutDisplayInterface;

import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
  private ArrayList<Course> course_list = new ArrayList();
  private ArrayList<Student> student_list = new ArrayList(); 
  private ArrayList<Student> answer = new ArrayList();
  private String resultFileName;
  private float total_avg_sat = 0;
  /*Function to check if the timing of new course don't clash with the assigned course timing*/
  public boolean CheckForTime( ArrayList<Course> stud_course, Course c ){
    for ( int i=0; i<stud_course.size(); ++i ){
      Course stu_cour = stud_course.get(i);
      if ( stu_cour.getTime() == c.getTime() ) { 
        return false; 
      }
    }
    return true;
  }

  public Course CheckIfAvailable( String pref, ArrayList<Course> stud_course ){
    for(int i=0;i<course_list.size();i++) {
      Course c = course_list.get(i);
      if ( c.getName().equals( pref ) ) {
        if ( c.getCapacity() > 0 ) { 
          if ( CheckForTime( stud_course, c ) ){
            c.ReduceCapacity();
            return c;      
          }
        }
      }
    }
    return null;
  }
    
  public ArrayList<Student> LoopStudentList(ArrayList<Student> student_i){
    for(int i=0;i<student_i.size();++i) {
      Student s = student_i.get(i);
         
      ArrayList<String> pref = s.getPreference();
      ArrayList<String> delpref = new ArrayList();
            
      for(int j=0;j<pref.size();++j) {
        Course c = CheckIfAvailable( pref.get(j), s.courses );
        if ( c != null ){ 
          student_i.get(i).courses.add( c ); 
          delpref.add( c.getName() );
          break;
        }
      }
          
      for ( int j=0; j<delpref.size(); ++j){
        student_i.get(i).DeletePreference( delpref.get(j) );
      }
            
      if ( delpref.size() == 0 ){
        student_i.get(i).allCoursesFull = true;
      }
    }
    return student_i;
  }
    
  public ArrayList<Student> DeleteFromStudentList(ArrayList<Student> student_i){
    ArrayList<Integer> deleteIndex = new ArrayList();
    for ( int i=0; i<student_i.size(); ++i ){
      if ( student_i.get(i).courses.size() == 3 || student_i.get(i).allCoursesFull) {
        answer.add( student_i.get(i) );
        deleteIndex.add( i );
      }
    }
    
    for ( int i=0; i<deleteIndex.size(); ++i ){
      student_i.remove(deleteIndex.get(i));
    }
    return student_i;
  }

  public Results (FileProcessor fp) {
    course_list = fp.GetCourseList();
    student_list = fp.GetStudentList();
    resultFileName = fp.GetResultFileName();
  }

  public void FileDisplayInfo(){
    ArrayList<Student> student_3 = new ArrayList();
    ArrayList<Student> student_2 = new ArrayList();
    ArrayList<Student> student_1 = new ArrayList();
        
    for(int i=0;i<student_list.size();i++) {
      Student s = student_list.get(i);
      if ( s.getLevel().equals( "FIRST_YEAR" ) ){ student_1.add( s ); }
      else if ( s.getLevel().equals( "SECOND_YEAR")){ student_2.add( s ); }
      else if ( s.getLevel().equals( "THIRD_YEAR" )) { student_3.add( s ); }     
    } 
    
    while ( answer.size() != student_3.size() ) {
      student_3 = LoopStudentList( student_3 );
      student_3 = DeleteFromStudentList( student_3 );     
    }    
        
    while ( answer.size() != ( student_2.size() + student_3.size() ) ){
      student_2 = LoopStudentList( student_2 );
      student_2 = DeleteFromStudentList( student_2 );
    }
        
    while ( answer.size() != student_list.size() ){
      student_1 = LoopStudentList( student_1 );
      student_1 = DeleteFromStudentList( student_1 );
    }

    total_avg_sat = 0;
    for ( int i=0; i<answer.size(); ++i){
      Student s = answer.get(i);
      float sat = 0; 
      for ( int j =0; j<s.courses.size(); ++j ){
        for ( int k=0; k<s.originalPreference.size(); ++k){
          if ( s.originalPreference.get(k).equals( s.courses.get(j).getName() ) ){
            sat += 9-k;
          }
        }
      }      
      answer.get(i).avg_sat = sat/3;
      total_avg_sat += answer.get(i).avg_sat;
    }
    total_avg_sat = total_avg_sat/answer.size();
  }

  public void PrintOnScreen(){
    try {
      FileWriter fw = new FileWriter(resultFileName,false);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      for ( int i=0; i<answer.size(); ++i){
        Student s = answer.get(i);
        String store = "";
        store += s.getID().toString();
        store += ":";
             
                
        int j;
        boolean wentIn = false; 
                
        for ( j =0; j<s.courses.size()-1; ++j ){
          wentIn = true;
          store += s.courses.get(j).getName();
          store += ",";
        }
        if ( wentIn ) {
          store += s.courses.get(j).getName();
        }
        store += "::SatisfactionRating=";
        store += new Float(s.avg_sat).toString();
        pw.write(store + "\n");
      }
      pw.write("AverageSatisfactionRating="+ ( new Float(total_avg_sat).toString()));
      pw.close();    
    } catch(Exception e) {
      System.out.println("Output File not found" + e);
    }
  }
}
