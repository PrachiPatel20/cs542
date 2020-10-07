package genericCheckpointing.server;
import genericCheckpointing.util.MyAllTypesFirst;

import java.util.ArrayList;

public interface StoreI extends StoreRestoreI{
  
  ArrayList<String> writeObj(MyAllTypesFirst first,String wireFormat);
  
}