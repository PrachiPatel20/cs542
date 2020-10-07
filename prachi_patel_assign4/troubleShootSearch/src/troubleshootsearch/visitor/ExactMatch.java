package troubleshootsearch.visitor;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.element.MyTree;
import troubleshootsearch.visitor.VisitorInterface;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class ExactMatch implements VisitorInterface {

  String input;
  public ExactMatch(String inputIn) {
    setInput(inputIn);
    MyLogger.writeMessage("Exact Match Constructor ", MyLogger.DebugLevel.CONSTRUCTOR);
  }
  public void setInput(String inputIn) {
    input = inputIn;
  }
  public String getInput() {
    return input;
  }
  @Override
  public String visit(MyArrayList myArray) {
    MyLogger.writeMessage("Exact Match", MyLogger.DebugLevel.EXACT);
    for(int i=0;i<myArray.size();i++) {
    
      if(myArray.get(i).contains(getInput())) {
        //System.out.println("Exact Match Exists");
        return myArray.get(i);
      }
      else {
        //System.out.println("No exact match found");
      }
    }
    return null;
  }
  

}