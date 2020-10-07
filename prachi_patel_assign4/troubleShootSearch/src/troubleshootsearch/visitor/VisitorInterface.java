package troubleshootsearch.visitor;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.element.MyTree;

public interface VisitorInterface {
  public String visit(MyArrayList marray);
  //public void visit(MyTree mtree);
}