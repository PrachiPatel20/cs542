package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.file.FileProcessor;
import genericCheckpointing.util.SerializableObject;

import java.util.ArrayList;

public interface DeserStrategy {
  SerializableObject processInput(ArrayList<String> serObj);
} 