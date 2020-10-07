package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import genericCheckpointing.xmlStoreRestore.SerializeTypes;
import genericCheckpointing.util.SerializableObject;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class XMLSerialization implements SerStrategy {
  @Override
	public ArrayList<String> processInput(SerializableObject sObject) {
    ArrayList<String> ser = new ArrayList();
    SerializeTypes stype = new SerializeTypes();
    ser.add("<DPSerialization>");
    ser.add("\t<complexType xsi:type=\"" + sObject.getClass().getName() + "\">");
		Field[] f = sObject.getClass().getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			try {
				Field field = f[i];
				String value;
				field.setAccessible(true);
				String type = field.getType().toString();
				if (type.equals("int")) {
					value = stype.serInt(field.get(sObject).toString(), field.getName(), type);
				} else if (type.equals("double")) {
					value = stype.serDouble(field.get(sObject).toString(), field.getName());
				} else if (type.equals("long")) {
		
			value = stype.serLong(field.get(sObject).toString(), field.getName());
				} else {
					value = stype.serOther(field.get(sObject).toString(), field.getName(), type);
				}

				if (!value.equals("")) {
					ser.add(value);
	}
			} catch (Exception e) {
				System.out.println("Exception2"+e);
			}
		}
    ser.add("\t</complexType>");
    ser.add("</DPSerialization>");
    return ser;
  }
}