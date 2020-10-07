package coursesRegistration.driver;

import coursesRegistration.util.FileProcessor;
import coursesRegistration.util.Results;
import coursesRegistration.util.FileDisplayInterface;
import coursesRegistration.util.StdoutDisplayInterface;

public class Driver {
  public static void main(String[] args) {
    if(args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}"))
    {
      System.err.println("Invalid number of inputs");
      System.exit(0);
    }
    FileProcessor fp = new FileProcessor( args ); 
   	Results rs = new Results ( fp );
    ((FileDisplayInterface) rs).FileDisplayInfo();
    ((StdoutDisplayInterface) rs).PrintOnScreen();
  }
}
