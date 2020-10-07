package troubleshootsearch.visitor;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.element.MyTree;
import troubleshootsearch.visitor.VisitorInt;

public class NaiveStemmingMatch implements VisitorInt {
  String input;
  int value;
  public NaiveStemmingMatch(String inputIn) {
    setInput(inputIn);
    MyLogger.writeMessage("Naive Stemming Match Constructor ", MyLogger.DebugLevel.CONSTRUCTOR);
  }
  public void setInput(String inputIn) {
    input = inputIn;
  }
  public String getInput() {
    return input;
  }
  public int returnValue() {
    return this.value;
  }
  
  @Override
  public String visit(MyTree tree) {
    MyLogger.writeMessage("Naive Stemming Match", MyLogger.DebugLevel.NAIVE);
    String[] sep = getInput().split(" ");
    String s = sep[0];
    if(tree.getNode(s) != 0) {
      value = tree.getNode(s);
      //System.out.println(tree.getNode(s));
      return String.valueOf(tree.getNode(s));
    }
    else {
      value = 0;
      //System.out.println("No naive stemming match");
      return null;
    }
  }
}