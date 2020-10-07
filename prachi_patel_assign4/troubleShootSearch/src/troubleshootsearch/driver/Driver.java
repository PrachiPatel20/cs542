package troubleshootsearch.driver;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.Results;
import troubleshootsearch.util.StdoutDisplayInterface;
/**
 * @author John Doe
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") ) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 4 argumnets.");
			System.exit(0);
		}
		
		//System.out.println("Hello World! Lets get started with the assignment");
    FileProcessor fp = new FileProcessor(args);
    Results rs = new Results ( fp );
    //((FileDisplayInterface) rs).fileDisplayInfo();
    ((StdoutDisplayInterface) rs).printOnScreen();
	}
}
