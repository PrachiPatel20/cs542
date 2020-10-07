package genericCheckpointing.util;
import genericCheckpointing.util.SerializableObject;

public class MyAllTypesFirst extends SerializableObject {
  int myInt;
  long myLong;
  String myString;
  boolean myBool;
  int myOtherInt;
  long myOtherLong;
  
  public MyAllTypesFirst() {
  }
  
  public MyAllTypesFirst(int myIntIn,long myLongIn, String myStringIn, boolean myBoolIn, int myOtherIntIn, long myOtherLongIn) {
    myInt=myIntIn;
    myLong=myLongIn;
    myString=myStringIn;
    myBool=myBoolIn;
    myOtherInt=myOtherIntIn;
    myOtherLong=myOtherLongIn;
  }
  
  public void setMyInt(int myIntIn) {
    myInt = myIntIn;
  }
  
  public int getMyInt() {
    return myInt;
  }
  
  public void setMyLong(long myLongIn) {
    myLong = myLongIn;
  }
  
  public long getMyLong() {
    return myLong;
  }
  
  public void setMyString(String myStringIn) {
    myString = myStringIn;
  }
  
  public String getMyString() {
    return myString;
  }
  
  public void setMyBool(boolean myBoolIn) {
    myBool = myBoolIn;
  }
  
  public boolean getMyBool() {
    return myBool;
  }
  
  public void setMyOtherInt(int myOtherIntIn) {
    myOtherInt = myOtherIntIn;
  }
  
  public int getMyOtherInt() {
    return myOtherInt;
  }
  
  public void setMyOtherLong(long myOtherLongIn) {
    myOtherLong = myOtherLongIn;
  }
  
  public long getMyOtherLong() {
    return myOtherLong;
  }
  
  @Override
  public boolean equals(Object object) {
    if(!(object instanceof MyAllTypesFirst)) {
      return false;
    }
    else {
      MyAllTypesFirst myFirst = (MyAllTypesFirst) object;
      
      if ((myFirst.myBool == myBool) && (myFirst.myInt == myInt) && (myFirst.myLong == myLong) && (myFirst.myOtherInt == myOtherInt) && (myFirst.myString.equals(myString)) && (myFirst.myOtherLong == myOtherLong)) {
				return true;
			}
      return false;
    }
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
		int result = 1;
		result = prime * result + (myBool ? 1231 : 1237);
		result = prime * result + myInt;
		result = prime * result + (int) (myLong ^ (myLong >>> 32));
		result = prime * result + myOtherInt;
		result = prime * result + (int) (myOtherLong ^ (myOtherLong >>> 32));
		result = prime * result + ((myString == null) ? 0 : myString.hashCode());
		return result;
  }
}