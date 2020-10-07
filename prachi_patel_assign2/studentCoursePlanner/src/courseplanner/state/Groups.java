package courseplanner.state;
import courseplanner.state.CoursePlannerStateI;
import courseplanner.util.FileProcessor;
import courseplanner.util.Results;
import courseplanner.Student;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Groups implements CoursePlannerStateI {
  private char mStart;
  private char mEnd;
  private int mPrevSem;
  private int mCurrentSem;
  private ArrayList<Character> mAssignCourse = new ArrayList<Character>();
  
  /** Constructor
  @param startIn which takes the starting character of parcticular group
  @param endIn which takes the ending character of parcticular group
  */
  public Groups (char startIn, char endIn) 
  {
    mStart = startIn;
    mEnd = endIn;
    mPrevSem = 0;
    mCurrentSem = -1;
  }
  
  public Groups checkCriteria(char c) { return null; }
  public void checkStateChange() {}
  
  /** isInGroup checks whether that course is in group or not
  @return boolean
  @param character c
  */
  public boolean isInGroup(char c) 
  {
    if ( c >= mStart && c <= mEnd ) { return true; }
    return false;
  }
  
  /** SatisfyPreq checks whether that course satisfies prerequisite of that group or not
  @return boolean
  @param character c
  */
  public boolean satisfyPreq(char c) 
  {
    if ( mStart == 'Q' ) { return true; }
    if ( c == mStart && mAssignCourse.size() == 0 ) { return true; }
    if ( mAssignCourse.size() == 0 ) { return false; }
    
    char last_subj = mAssignCourse.get(mAssignCourse.size()-1);
    
    if ( c == last_subj + 1 ) { return true; }
    return false;
  }
  
  /** Setter method for mCurrentSem 
  */
  public void setCurrentSem(int semIn)
  {
    mCurrentSem = semIn;
  }
  
  /** inSameSem returns true if prev sem and current sem are equal 
  only for first four groups
  @return boolean
  */
  public boolean inSameSem()
  {
    if ( mStart == 'Q' ) { return false; }
    if ( mCurrentSem != mPrevSem ){ return false; }
    return true;
  }
  
  /** assignThisCourse adds that course in the list of assigned courses
  @return boolean
  @param character c
  */
  public void assignThisCourse(char c) 
  {
    mAssignCourse.add( c );
    mPrevSem = mCurrentSem;
  }  
  
  /** Getter method which returns 0 as last course if size of assigned course is 0 
  else returns the last course assigned from the list of assigned course 
  */
  public char getLastCourse()
  {
    if ( mAssignCourse.size() == 0 ) { return '0'; }
    return mAssignCourse.get( mAssignCourse.size() - 1 ); 
  }
  /** Getter method to get size of assigned courses
  */
  public int getTotalCourse()
  {
    return mAssignCourse.size();
  }
  /** Getter method to get start course of that group
  */
  public char getStartSubj()
  {
    return mStart;
  }
}