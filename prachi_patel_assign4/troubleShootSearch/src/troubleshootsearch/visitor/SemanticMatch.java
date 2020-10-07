package troubleshootsearch.visitor;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.element.MyTree;
import troubleshootsearch.visitor.VisitorInterface;
import java.util.Map;
import java.util.HashMap; 
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class SemanticMatch implements VisitorInterface {
  
  String input,syn;
  Map<String,String> map = new HashMap<String,String>();
  public SemanticMatch(String inputIn,Map<String,String> mIn) {
    MyLogger.writeMessage("Semantic Match Constructor ", MyLogger.DebugLevel.CONSTRUCTOR);
    setInput(inputIn);
    setMap(mIn);
    
  }
  public void setInput(String inputIn) {
    input = inputIn;
  }
  public String getInput() {
    return input;
  }
  public void setMap(Map<String,String> mapIn) {
    map.putAll(mapIn);
  }
  public Map<String,String> getMap() {
    return map;
  }
  @Override
  public String visit(MyArrayList array) {
    MyLogger.writeMessage("Semantic Match", MyLogger.DebugLevel.SEMANTIC);
    String[] sep = getInput().split(" ");
    String s = sep[sep.length - 1];
    String inp = null;
    if(getMap().containsKey(s)) {
      System.out.println(s+"-----"+getMap().get(s));
      syn = getMap().get(s);
      sep[sep.length - 1]=syn;
      inp = String.join(" ",sep);
      for(int i=0;i<array.size();i++) {
    ////
      if(array.get(i).toLowerCase().contains(inp.toLowerCase())) {
        System.out.println("Semantic Match Exists");
        return "Sem match";
      }
      else {
        //System.out.println("No semantic match found");
      }
      }
    }
    else if(getMap().containsValue(s)) {
      for(String i : getMap().keySet()) {
        if(s.equals(getMap().get(i))) {
          syn = i;
        }
      }
      sep[sep.length - 1]=syn;
      inp = String.join(" ",sep);
      for(int i=0;i<array.size();i++) {

      if(array.get(i).toLowerCase().contains(inp.toLowerCase())) {
        //System.out.println("Semantic Match Exists");
        return array.get(i);
      }
      else {
        //System.out.println("No semantic match found");
      }
    }
    }
    return null;
    
  }
  //@Override
  //public void visit(MyTree tree) { }
}