package coursesRegistration.scheduler;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Course {
  private String name;
  private int capacity;
  private int time;
  /*Constructor*/
  public Course(List<String> courseDetails) {
	  setName(courseDetails.get(0));
	  setCapacity(Integer.parseInt(courseDetails.get(1)));
	  setTime(Integer.parseInt(courseDetails.get(2)));
  }
  /*Setter methods*/
  private void setName(String courseName) {
	  this.name = courseName;
  }
  private void setCapacity(int capacity) {
	  this.capacity = capacity;
  }
  private void setTime(int time) {
	  this.time = time;
  }
  /*Getter methods*/
  public String getName(){
    return this.name;
  }
  public int getCapacity(){
    return this.capacity;
  }
  public void ReduceCapacity(){
	  /*Reduce capacity when it is assigned to a student*/
    --(this.capacity);
  }
  public int getTime(){
    return this.time;
  }
}