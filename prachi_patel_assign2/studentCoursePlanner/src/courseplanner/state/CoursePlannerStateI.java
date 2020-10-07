package courseplanner.state;

public interface CoursePlannerStateI {
  public Groups checkCriteria(char c);
  public void checkStateChange();
  public boolean satisfyPreq(char c);
  public void assignThisCourse(char c);

}
