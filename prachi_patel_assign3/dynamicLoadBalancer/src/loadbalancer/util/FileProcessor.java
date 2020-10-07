package loadbalancer.util;
import loadbalancer.driver.Driver;
import loadbalancer.subject.Cluster;
import loadbalancer.entities.Service;
import loadbalancer.entities.Machine;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.observer.ServiceManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class FileProcessor {
  private ArrayList<String> input_list = new ArrayList();
  private String resultFileName;
	private BufferedReader b = null;
  Cluster c = new Cluster();
  LoadBalancer lb = new LoadBalancer();
  Service se;
  
  public FileProcessor( String[] files) {
 	  String line;
	  /*Reading first input file*/
    NewFile( files[0] );
    while ((line = readLine()) != null) {
      try {
//    		String s= inputDetails(line);
//        System.out.println(s);
//        if ( s != null ) { 
//          c.createcluster(s);
//        }
//        else {
//          System.out.println("Error input null");
//        }
          inputDetails(line);
          //System.out.println(line);
 	    } catch(Exception e) {
		    System.out.println("Input file 1 not found:" + e);
    		continue;
   	  }
  	}
    CloseFile();
	  /*Assigning output file name to a variable*/
    resultFileName = files[1];
  }
  public ArrayList<String> GetInputList(){
    return this.input_list;
  }
  public String GetResultFileName() {
    return this.resultFileName;
  }
  /*public String getmessage() {
    String message = c.messagedisplay();
    return message;
  }*/
  private void NewFile(String file){
    try{
	    b = new BufferedReader(new FileReader(file));
	  } catch(IOException e){
   	  System.out.println("File for new line not found:" + e);
	    e.printStackTrace();	
	  }
  }
  private void CloseFile(){
    try{
      b.close();    
   	} catch(IOException e){
      System.out.println("file to close not found:" + e);
      e.printStackTrace();	
   	}
  }
  private String readLine() {
	  String inp = "";
	  String eachline = null;
	  try{
 	    if((inp = b.readLine()) != null) {
		    eachline = inp;	
	    }
	  } catch (IOException e) {
	    System.out.println("New line not assigned:" + e);
	    e.printStackTrace();
	  }
	  return eachline;
  }
  private String inputDetails(String eachline) {
    String[] sep = eachline.split(" ");
    String s = null;
    try {
      List<String> sep1 = new ArrayList<String>();
      List<String> sep2 = new ArrayList<String>();
      List<String> sep3 = new ArrayList<String>();
      if(sep[0].equals("CLUSTER_OP__SCALE_UP")) {
        c.addMachine(sep[1]);
      }
      else if(sep[0].equals("CLUSTER_OP__SCALE_DOWN")) {
        c.removeMachine(sep[1]);
      }
      else if(sep[0].equals("SERVICE_OP__ADD_SERVICE")) {
        sep2.add(sep[1]);
        sep2.add(sep[2]);
        sep2.add(sep[3]);
        lb.addService(sep2);
      }
      else if(sep[0].equals("SERVICE_OP__REMOVE_SERVICE")) {
        lb.removeService(sep[1]);
      }
      else if(sep[0].equals("SERVICE_OP__ADD_INSTANCE")) {
        sep1.add(sep[1]);
        sep1.add(sep[2]);
        lb.addInstance(sep1);
      }
      else if(sep[0].equals("SERVICE_OP__REMOVE_INSTANCE")) {
        sep3.add(sep[1]);
        sep3.add(sep[2]);
        lb.removeInstance(sep3);
      }
      else if(sep[0].equals("REQUEST")) {
        lb.request(sep[1]);
      } 
    } catch(NumberFormatException nfe) {
      System.out.println("Invalid input");
    }
    return s;
  }
}
