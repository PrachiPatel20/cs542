package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Method;

public class SerializeTypes {
  
  public String serInt(String value, String tagName, String typeName) {
		if ((Integer.parseInt(value) >= 10)) {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:" + typeName + "\">" + value + "</" + tagName + ">");
		}
		return "";
	}
  
  public String serDouble(String value, String tagName) {
		if ((Double.parseDouble(value) >= 10)) {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:double\">" + value + "</" + tagName + ">");
		}
		return "";
	}

  public String serLong(String value, String tagName) {
		if ((Long.parseLong(value) >= 10)) {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:long\">" + value + "</" + tagName + ">");
		}
		return "";
	}

  public String serOther(String value, String tagName, String typeName) {
		if (typeName.equals("class java.lang.String")) {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:string\">" + value + "</" + tagName + ">");
		} else {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:" + typeName + "\">" + value + "</" + tagName + ">");
		}
	} 
  
  public Method deser(String type, String tag, Class<?> c, String value, Object obj) {
  try{
    Method mInt;
    if(type.equals("int")) {
      mInt = c.getMethod("set" + tag, Integer.TYPE);
      mInt.invoke(obj,Integer.parseInt(value));
    }
    else if(type.equals("double")) {
      mInt = c.getMethod("set" + tag, Double.TYPE);
      mInt.invoke(obj,Double.parseDouble(value));
    }
    else if(type.equals("long")) {
      mInt = c.getMethod("set" + tag, Long.TYPE);
      mInt.invoke(obj,Long.parseLong(value));
    }
    else if(type.equals("string")) {
      mInt = c.getMethod("set" + tag, java.lang.String.class);
      mInt.invoke(obj,value);
    }
    else if(type.equals("float")) {
      mInt = c.getMethod("set" + tag, Float.TYPE);
      mInt.invoke(obj,Float.parseFloat(value));
    }
    else if(type.equals("short")) {
      mInt = c.getMethod("set" + tag, Short.TYPE);
      mInt.invoke(obj,Short.parseShort(value));
    }
    else if(type.equals("char")) {
      mInt = c.getMethod("set" + tag, Character.TYPE);
      mInt.invoke(obj,value.charAt(0));
    }
    else if(type.equals("boolean")) {
      mInt = c.getMethod("set" + tag, Boolean.TYPE);
      mInt.invoke(obj,Boolean.parseBoolean(value));
    }
  } catch(Exception e) {
    System.out.println("Exception1"+e);
  }
  return null;
  }
  
}