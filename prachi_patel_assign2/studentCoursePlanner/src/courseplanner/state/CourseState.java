package courseplanner.state;
import courseplanner.state.CoursePlannerStateI;
import courseplanner.state.Groups;
import courseplanner.Student;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CourseState implements CoursePlannerStateI
{
  Student s = null;
  private ArrayList<Groups> mG = new ArrayList<Groups>();
  private Groups mCurrentState = null;
  private int mStateChange;
  private boolean mGraduated = false;
  
  public boolean satisfyPreq(char c) {return false;}
  public void assignThisCourse(char c) {}
  /** Constructor
  @param student
  */
  public CourseState(Student sIn)
  {
    s = sIn;
    mG.add( new Groups( 'A','D' ) );
    mG.add( new Groups( 'E','H' ) );
    mG.add( new Groups( 'I','L' ) );
    mG.add( new Groups( 'M','P' ) );
    mG.add( new Groups( 'Q','Z' ) );
    mCurrentState = mG.get(0);
    mStateChange = 0;
  }
  /** checkCriteria checks for all criteria
  @return group
  @param character
  */
  public Groups checkCriteria(char c)
  {
    for ( Groups g : mG ){
      if ( g.isInGroup(c) ){
        if ( g.satisfyPreq(c) ) {
          return g;
        }
      }
    }
    return null; 
  }
  /** checkStateChange checks for state change 
  */
  public void checkStateChange()
  {
    Groups tempState = mCurrentState;
    int maxVal = mCurrentState.getTotalCourse();
    
    for ( Groups g : mG ){
      if ( maxVal < g.getTotalCourse() ){
        maxVal = g.getTotalCourse();
        tempState = g;
      }
    }
    
    if ( mCurrentState != tempState ){ 
      ++mStateChange;
      mCurrentState = tempState;
    }
  }
  
  public int getTotalState()
  {
    return this.mStateChange;
  } 
  /** didGraduate checks whether the student is eligible for graduating
  @return boolean
  */
  public boolean didGraduate()
  {
    mGraduated = true;
    for( Groups g : mG ){
      if ( g.getTotalCourse() < 2 ){
        mGraduated = false;
      }
    }
    return mGraduated;
  }
  /** waitListLogic maintain courses in waitlist which are waiting for prerequisite
  @param character arraylist
  */
  public void waitListLogic(ArrayList<Character> waitList)
  {
    int prev = waitList.size();
    while ( waitList.size() != 0 ){ 
      for ( Groups g : mG ){
        if ( g.getTotalCourse() < 2 && g.getStartSubj() != 'Q' ) {
          //If no course is assigned to that group
          if ( g.getLastCourse() == '0' ){
            int ind = waitList.indexOf( g.getStartSubj() );
            //Check if start subject is in waitlist if so assign it to that group
            if ( ind != -1 ){
              //If Assigned then remove from list
              if ( s.assign(g,g.getStartSubj()) ){
                waitList.remove(ind);  
                if (didGraduate()){ break; }
              }
            }
          }
          else{
            //Check if last course is in waitlist if so assign it
            int xte = g.getLastCourse()+1;
            char xtemp = (char)xte;
            int ind = waitList.indexOf(xtemp);
            if ( ind != -1 ) {
              //If Assigned then remove from list
              if ( s.assign(g,xtemp) ){
                waitList.remove(ind);
                if (didGraduate()){ break; }
              } 
            }
          }
        }
      } 
      if (didGraduate()){ break; }
      if ( prev == waitList.size() ){ break; }
      else{ prev = waitList.size(); }
    } 
                
  }
  
}