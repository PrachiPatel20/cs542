package troubleshootsearch.util;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.element.MyTree;
import troubleshootsearch.element.ElementInterface;
import troubleshootsearch.visitor.VisitorInterface;
import troubleshootsearch.visitor.VisitorInt;
import troubleshootsearch.visitor.ExactMatch;
import troubleshootsearch.visitor.SemanticMatch;
import troubleshootsearch.visitor.NaiveStemmingMatch;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap; 

public class FileProcessor {
  private String userInput;
  private ArrayList<String> technicalInfo = new ArrayList();
  private ArrayList<String> temp = new ArrayList();
  private ArrayList<String> output_list = new ArrayList();
  private String resultFileName;
  private Map<String, String> smap = new HashMap<String,String>();
  private Map< String,Integer> hm = new HashMap< String,Integer>();
  
	private BufferedReader b = null;
  public FileProcessor( String[] files) {
 	  String line;
    String ematch,nmatch,smatch = null;
    /*Assigning output file name to a variable*/
    resultFileName = files[3];
      
	  /*Reading first input file*/
	  NewFile(files[0]);
  	while ((line = ReadLine()) != null) {
      try {
          technicalInfo.add(line);
          temp = technical(line);
 	    } catch(Exception e) {
		    System.out.println("Input file 1 not found:" + e);
    		continue;
   	  }
    }
    CloseFile();
    MyArrayList myarray = new MyArrayList(technicalInfo);
    String[] techdetails=new String[temp.size()];
    temp.toArray(techdetails);
    MyTree mytree = new MyTree(techdetails);
        /*Reading second input file*/
	  
    
    //Reading third input file
	  NewFile( files[2] );
  	while ((line = ReadLine()) != null) {
      try {
    		String s = synonym(line);
 	    } catch(Exception e) {
		    System.out.println("Input file 3 not found:" + e);
    		continue;
   	  }
  	}
    CloseFile();
    
    NewFile( files[1] );
  	while ((line = ReadLine()) != null) {
      try {
        userInput = line;
        //System.out.println("Input: "+userInput);
        //call ExactMatch
        ematch = ExactMatchTry(myarray,userInput);
        //call SemanticMatch
        smatch = SemanticMatchTry(myarray,userInput,smap);
        //call NaiveStemming match
        nmatch = NaiveStemmingTry(mytree,userInput);
        output_list.add(userInput);
        output_list.add(ematch);
        output_list.add(smatch);
        output_list.add(nmatch);
 	    } catch(Exception e) {
		    System.out.println("Input file 2 not found:" + e);
    		continue;
   	  }
  	}
    CloseFile();
  }
  public String getResultFileName() {
    return this.resultFileName;
  }
  public ArrayList<String> getOutput() {
    return this.output_list;
  }
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
  private String ReadLine() {
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
  
  public String ExactMatchTry(MyArrayList array,String input) {
    VisitorInterface visitor = new ExactMatch(input);
    String s1;
    if(array.accept(visitor) != null) {
      s1 = array.accept(visitor);
    }
    else {
      s1 = "No exact match found";
    }
    String s = "---------Exact-----------\n" + s1;
    //System.out.println("---------Exact-----------");
    //System.out.println(s);
    return s;
  }
  public String SemanticMatchTry(MyArrayList array,String input,Map<String,String> m) {
    VisitorInterface visitor = new SemanticMatch(input,m);
    String s1;
    if(array.accept(visitor) != null) {
      s1 = array.accept(visitor);
    }
    else {
      s1 = "No semantic match found";
    }
    String s = "---------Semantic-----------\n" + s1;
    //System.out.println("---------Semantic-----------");
    //System.out.println(s);
    return s;
  }
  public String NaiveStemmingTry(MyTree tree,String input) {
    VisitorInt visitor = new NaiveStemmingMatch(input);
    String s1;
    if(tree.accept(visitor) != null) {
      s1 = tree.accept(visitor);
    }
    else {
      s1 = "No naive stemming match found";
    }
    String s = "---------Naive Stemming-----------\n" + s1;
    //System.out.println("---------Naive Stemming-----------");
    //System.out.println(s);
    return s;
  }
  
  private ArrayList<String> technical(String eachline) {
    String[] sep = eachline.replaceAll(",.","").toLowerCase().split(" ");
    for(int i=0;i<sep.length;i++) {
      temp.add(sep[i]);
    }
    return temp;
  }
  private String synonym(String eachline) {
    String[] sep1 = eachline.split("=");
    if(smap.get(sep1[0]) == null) {
      smap.put(sep1[0],sep1[1]);
      return sep1[0];
    }
    return null;
  }
  
  /*
  private Student studentDetails(String eachline) {
   	String[] sep = eachline.split(": ");
  
    Student s = null;
    try{
      if ( Integer.parseInt(sep[0]) >= 1000 && Integer.parseInt(sep[0]) <= 9999 ){
        if ( hm.get(sep[0]) == null ){      

          hm.put( sep[0], 1 ) ;
          List<String> sep2 = new ArrayList<String>();
          sep2.add(sep[0]);
          sep2.add(sep[1]);   	
          s = new Student(sep2);
        }
        else{
          System.out.println("Same Student Id insterted twice"+" Id was :"+sep[0]);
        }
      }
      else{
        System.out.println("Invalid Student Id should be between 1000 and 9999"+" Id was :"+sep[0]);
      }
    } catch(NumberFormatException nfe){
      System.out.println("Invalid Student Id should be in numbers"+" Id was :"+sep[0]);
    }
    return s;
  }*/
}
