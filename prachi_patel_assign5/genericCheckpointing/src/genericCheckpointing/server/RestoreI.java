package genericCheckpointing.server;

import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.SerializableObject;

public interface RestoreI extends StoreRestoreI{
  
  SerializableObject readObj(String wireFormat);
  
}