package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import genericCheckpointing.xmlStoreRestore.SerializeTypes;
import genericCheckpointing.util.SerializableObject;

import java.lang.reflect.Constructor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class XMLDeserialization implements DeserStrategy {

  public SerializableObject processInput(ArrayList<String> serObj) {
    SerializeTypes stype = new SerializeTypes();
    Object obj = null;
    Class<?> c = null;
    
    for(String eachline : serObj) {
      Pattern tagComp = Pattern.compile("<(.*?) xsi:");
      Pattern typeComp = Pattern.compile("xsd:(.*?)\">");
      Pattern valueComp = Pattern.compile(">(.?)<");
      if(eachline.contains("<complexType")) {
        //reflection code here
        String[] sep = eachline.split("type=\"");
        String clsName = sep[1].split("\">")[0];
        try {
          c = Class.forName(clsName);
          Constructor<?> ctor = c.getConstructor();
          obj = ctor.newInstance();
        } catch(Exception e) {
          System.out.println("Error in deserializing the file");
        }
      }
      else if(eachline.contains("</complexType>")) {
        break;
      }
      else {
        String tag=null,type=null,value=null;
        
        Matcher ma = tagComp.matcher(eachline);
        if(ma.find()) {
          tag = ma.group(1);
        }
        ma = typeComp.matcher(eachline);
        if(ma.find()) {
          type = ma.group(1);
        }
        ma = valueComp.matcher(eachline);
        if(ma.find()) {
          value = ma.group(1);
        }
        tag = tag.substring(0,1).toUpperCase() + tag.substring(1);
        try {
          stype.deser(type, tag, c, value, obj);
        } catch(Exception e) {
          System.out.println("Error");
        }
      }
    }
    return (SerializableObject)obj;
  }

}