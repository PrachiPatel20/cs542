package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.driver.Driver;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.file.FileProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class StoreRestoreHandler implements InvocationHandler {
  
  FileProcessor fp;
  ArrayList<String> serObj;
  
  public StoreRestoreHandler(String fpIn) {
    fp = new FileProcessor(fpIn);
  }
  
  @Override 
  public Object invoke(Object proxy, Method method,Object[] args) throws Throwable{
    String m = method.getName();
    
    if(m.equals("readObj")) {
      if(args[0].equals("XML")) {
        //read file and deserialize it
        DeserStrategy strategy = new XMLDeserialization();
        copy();
        return strategy.processInput(serObj);
      }
    }
    else if(m.equals("writeObj")) {
      if(args[1].equals("XML")) {
        //serialize it into XML format
        SerStrategy strategy = new XMLSerialization();
        return strategy.processInput((SerializableObject)args[0]);
      }
    }
    return null;
  }
  
  public void copy() {
    serObj=fp.getList();
  }
  
}