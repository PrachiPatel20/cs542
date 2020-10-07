package troubleshootsearch.element;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.visitor.VisitorInterface;
import troubleshootsearch.visitor.VisitorInt;

public interface ElementInt {

  public String accept(VisitorInt vi);

}