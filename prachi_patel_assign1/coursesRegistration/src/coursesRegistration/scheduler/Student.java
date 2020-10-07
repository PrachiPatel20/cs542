package coursesRegistration.scheduler;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Student {
  public ArrayList<Course> courses = new ArrayList();
  public boolean allCoursesFull = false;
  public ArrayList<String> originalPreference = null;
  public float avg_sat = 0;
  private String id;
  private ArrayList<String> preference = null;
  private String lev;
  /*Constructor*/ 
  public Student(List<String> studentdetails) {
	  setID( studentdetails.get(0) );
    setPreference( studentdetails.get(1) );
    setLevel( studentdetails.get(2) );
  }
  /*Setter method*/
  private void setID(String _id) { 
  	this.id = _id;
  }
  private void setPreference( String _preference) {
   	String[] _sep = _preference.split(",");
   	preference = new ArrayList();
   	originalPreference = new ArrayList();
   	for(int i=0;i<_sep.length;i++) {
      this.preference.add( _sep[i] );
      this.originalPreference.add( _sep[i] );
	  } 
  }
  private void setLevel(String _lev) {
 	  this.lev = _lev;
  }
  public void DeletePreference(String course){
	  int find = -1;
  	for ( int i=0; i<preference.size(); ++i ){
      if ( course == preference.get(i) ){
       	find = i;
        break;
      }
  	}
    if ( find != -1 ){
      preference.remove( find );
   	}
  }
  /*Getter methods*/
  public String getID(){
	  return this.id;
  } 
  public ArrayList<String> getPreference(){
    return this.preference;
  }
  public String getLevel(){
    return this.lev;
  }
}