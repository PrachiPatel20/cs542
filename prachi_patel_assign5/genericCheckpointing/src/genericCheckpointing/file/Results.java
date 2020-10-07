package genericCheckpointing.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Results implements FileDisplayInterface {

  private ArrayList<String> resultList = null;
	BufferedWriter br = null;
 
  public Results(String fileIn) {
		resultList = new ArrayList<>();
		try {
			br = new BufferedWriter(new FileWriter(fileIn));
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

  @Override
	public void writeToFile() {
		try {
			for (String data : resultList) {
				br.write(data+"\n");
			}
			resultList.clear();
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

  public void close() {
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public ArrayList<String> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<String> resultList) {
		this.resultList = resultList;
	}


}