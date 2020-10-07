package genericCheckpointing.util;
import genericCheckpointing.util.SerializableObject;

public class MyAllTypesSecond extends MyAllTypesFirst {
  double myDoubleT;
	float myFloatT;
	short myShortT;
	char myCharT;
	double myOtherDoubleT;
  
  public MyAllTypesSecond() {
    super();
  }
  
  public MyAllTypesSecond(double myDoubleTIn, float myFloatTIn, short myShortTIn, char myCharTIn, double myOtherDoubleTIn) {
    myDoubleT = myDoubleTIn;
		myFloatT = myFloatTIn;
		myShortT = myShortTIn;
		myCharT = myCharTIn;
		myOtherDoubleT = myOtherDoubleTIn;
  }
  
  public void setMyDoubleT(double myDoubleTIn) {
		this.myDoubleT = myDoubleTIn;
	}
  
  public double getMyDoubleT() {
		return myDoubleT;
	}
 
  public void setMyFloatT(float myFloatTIn) {
		this.myFloatT = myFloatTIn;
	}

	public float getMyFloatT() {
		return myFloatT;
	}

	public void setMyShortT(short myShortTIn) {
		this.myShortT = myShortTIn;
	}
 
	public short getMyShortT() {
		return myShortT;
	}
  
  public void setMyCharT(char myCharTIn) {
		this.myCharT = myCharTIn;
	}

	public char getMyCharT() {
		return myCharT;
	}

	public void setMyOtherDoubleT(double myOtherDoubleTIn) {
		this.myOtherDoubleT = myOtherDoubleTIn;
	}

	public double getMyOtherDoubleT() {
		return myOtherDoubleT;
	}


  @Override
  public boolean equals(Object object) {
    if(!(object instanceof MyAllTypesSecond)) {
      return false;
    }
    else {
      MyAllTypesSecond mySecond = (MyAllTypesSecond) object;
      
      if ((mySecond.myCharT == myCharT) && (mySecond.myDoubleT == myDoubleT) && (mySecond.myFloatT == myFloatT) && (mySecond.myShortT == myShortT) && (mySecond.myOtherDoubleT == myOtherDoubleT)) {
				return true;
			}
      return false;
    }
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
		int result = 1;
		result = prime * result + myCharT;
		long temp;
		temp = Double.doubleToLongBits(myDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(myFloatT);
		temp = Double.doubleToLongBits(myOtherDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + myShortT;
		return result;
  }
}