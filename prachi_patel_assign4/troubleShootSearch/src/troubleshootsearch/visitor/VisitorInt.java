package troubleshootsearch.visitor;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.element.MyTree;

public interface VisitorInt {
  //public void visit(MyArrayList marray);
  public String visit(MyTree mtree);
}