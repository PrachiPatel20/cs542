package courseplanner.driver;
import courseplanner.util.FileProcessor;
import courseplanner.util.Results;
import courseplanner.util.FileDisplayInterface;
import courseplanner.util.StdoutDisplayInterface;


public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}") ) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
			System.exit(0);
		}
		
		FileProcessor fp = new FileProcessor( args ); 
   	Results rs = new Results ( fp );
    ((FileDisplayInterface) rs).fileDisplayInfo();
    ((StdoutDisplayInterface) rs).printOnScreen();


	}
}
