package troubleshootsearch.element;
import troubleshootsearch.visitor.VisitorInt;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.element.Tree;
import java.util.Map;
import java.util.HashMap; 

public class MyTree implements ElementInt{

  Tree t = new Tree();
  private Map< String,Integer> hm = new HashMap< String,Integer>();
  int c = 1;
  public MyTree(String[] tree) {
    MyLogger.writeMessage("MyTree Constructor ", MyLogger.DebugLevel.CONSTRUCTOR);
    for(int i =0;i<tree.length;i++) {
      if(hm.get(tree[i]) == null) {
        hm.put(tree[i],1);
        t.insert(tree[i]);
      }
      else {
        int cu = c++;
        hm.put(tree[i],cu);
      }
    }
    //t.inorder();
  }
  public int getCount(String word) {
    return hm.get(word);
  }
  public int getNode(String w) {
    int ct = 0;
    for(String i : hm.keySet()) {
      //System.out.println(i);
      if(i.equals(w)) {
      }
      else {
        if(i.contains(w)) {
          ct++;
        }
      }
    }
    return ct;
  }
  
  @Override
  public String accept(VisitorInt visitor) {
    return visitor.visit(this);
  }

}