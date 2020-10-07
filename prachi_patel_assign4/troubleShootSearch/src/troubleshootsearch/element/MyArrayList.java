package troubleshootsearch.element;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.visitor.VisitorInterface;
import troubleshootsearch.util.MyLogger;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class MyArrayList implements ElementInterface{
  ArrayList<String> marray = new ArrayList<String>();
  int size;
  //public MyArrayList() { }

  public MyArrayList(ArrayList<String> mstring) {
    MyLogger.writeMessage("MyArrayList Constructor ", MyLogger.DebugLevel.CONSTRUCTOR);
    setArrayList(mstring);
  }
  
  public void setArrayList(ArrayList<String> marrayIn) {
    marray.addAll(marrayIn);
    setSize();
  }
  
  public ArrayList<String> getArrayList() {
    return marray;
  }
  public String get(int i) {
    return marray.get(i);
  }
  public void setSize() {
    size = marray.size();
  }
  public int size() {
    return size;
  }
  
  @Override
  public String accept(VisitorInterface visitor) {
    return visitor.visit(this);
  } 
}