package courseplanner;
import courseplanner.state.CourseState;
import courseplanner.state.CoursePlannerStateI;
import courseplanner.state.Groups;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Student implements CoursePlannerStateI {
  private String mId;
  private ArrayList<Character> mPref = new ArrayList<Character>();
  private ArrayList<Character> mWaitList = new ArrayList<Character>();
  private ArrayList<Character> mCourseList = new ArrayList<Character>();
  private int mSem = 1;
  private CourseState mState = null;
  
  public Groups checkCriteria(char c) {return null;}
  public void checkStateChange() {}
  public boolean satisfyPreq(char c) {return false;}
  public void assignThisCourse(char c) {}
  
  /** Constructor
  @param list of strings
  */
  public Student(List<String> studentdetails) 
  {
	  setID( studentdetails.get(0) );
    setPreference( studentdetails.get(1) );
    mState = new CourseState(this);
  }
  
  /** Setters
  */
  private void setID(String idIn) 
  { 
  	this.mId = idIn;
  }
  
  private void setPreference( String preferenceIn) 
  {
    String[] sep = preferenceIn.split(" ");
     
    for(int i=0;i<sep.length;i++) {
      this.mPref.add(sep[i].charAt(0));
    }
  }
  
  public String getId()
  {
	  return this.mId;
  } 
  
  /** Getters
  */
  public ArrayList<Character> getPreference()
  {
    return this.mPref;
  }
  
  public ArrayList<Character> getCourseList()
  {
    return this.mCourseList;
  }
  
  public int getSemester()
  {
    if ( mState.didGraduate() ) { return mSem; }
    return 0;
  }
  
  public int getStateChange()
  {
    return mState.getTotalState();
  }  
  
  /** addCourseToList adds course to list and increment the semester when 3 courses are assigned
  @param character
  */
  public void addCourseToList( char c )
  {
    //Add in the course list
    mCourseList.add ( c );
    
    //If in course added in multilpe of 3 then increase one semester
    if ( mCourseList.size() % 3 == 0 ){ ++mSem; }
  }
  /** assign assigns the course to the student
  @param groups
  @param character
  @return boolean
  */
  public boolean assign(Groups g, char c )
  {
    g.setCurrentSem( mSem );
    
    if ( !g.inSameSem() ){
      g.assignThisCourse( c ); 
      addCourseToList(c);
      mState.checkStateChange();
      return true;
    }
    return false;
  }
  /** isAssigned checks whether the course is assigned
  @param character
  @return boolean
  */
  public boolean isAssigned( char c ) {
    Groups g =  mState.checkCriteria(c);
    if ( g != null ){
      return assign(g,c);
    }
    return false; 
  }
  /** findPattern checks every semester and assign courses if there prerequisite is taken in previous semester
  */
  public void findPattern( )
  {
    for ( char tem : mPref ){
      boolean isInWaitList = false;
      for ( char temWait : mWaitList ){
        if ( temWait == tem ){ 
          isInWaitList = true; 
          break;
        }
      }
      if ( !isInWaitList ){ mWaitList.add( tem ); }
    
      //Loop through waitlist
      for ( char temWait : mWaitList ) {
        if( isAssigned( temWait )){
          if ( mState.didGraduate() ){ break; }
        }
      }
      
      if ( mState.didGraduate() ){ break; }
      
      //Remove from waitlist if assigned to courselist
      for ( char temWait : mCourseList ) {
        int ind = mWaitList.indexOf( temWait ) ;
        if ( ind != -1 ){
          mWaitList.remove(ind);
        }
      }
    }
    if ( mWaitList.size() != 0 && !mState.didGraduate() ){ mState.waitListLogic( mWaitList); }
  }  
}