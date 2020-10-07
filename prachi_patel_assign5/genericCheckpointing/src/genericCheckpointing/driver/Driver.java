package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.file.Results;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
//import genericCheckpointing.util.MySpecialTypes;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
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
		if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 argumnets.");
			System.exit(0);
		}
		
		System.out.println("Hello World! Lets get started with the assignment");
    // FIXME: read the value of checkpointFile from the command line
	  String fname = args[1];
    String resultfile = args[2];
    
	  ProxyCreator pc = new ProxyCreator();
	
	  // create an instance of StoreRestoreHandler (which implements
	  // the InvocationHandler
     StoreRestoreHandler srhandler = new StoreRestoreHandler(fname);
	
	  // create a proxy
	  StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
       new Class[] {  StoreI.class, RestoreI.class    }, 
       new StoreRestoreHandler(fname)   );
		
	  // FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file
    
	
	  MyAllTypesFirst myFirst = new MyAllTypesFirst();
	  MyAllTypesSecond  mySecond;
	  //MySpecialTypes  mySpecialT;
	
	  Results result = new Results(resultfile);
	  SerializableObject myRecordRet;


	  // read in a loop till the end of file is indicated
        myRecordRet = ((RestoreI) cpointRef).readObj("XML");
	  // FIXME: store myRecordRet in a data structure

	
	  // NUM_OF_OBJECTS is the size of the data strucutre in which the objects have been saved
	  

	    // use "instance of" to determine which of these methods should be called
	    // use this method for MyAllTypesFirst and MyAllTypesSecond.
	    result.setResultList(((StoreI) cpointRef).writeObj(myFirst,  "XML"));
      // use this method for MySpecialTypes
      //result.getResultList().addAll(((StoreI) cpointRef).writeObj(mySpecialT, "XML"));
	    result.getResultList();
	    
     result.writeToFile();
	  


	// FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)

    
	}
}