package Filter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Filter.AndFilter;
import Filter.Filters;
import Filter.Operator;

public class Write_filter {

	// https://github.com/yuvalmizrahi2/Task1/blob/master/src/inputoutput/IOSerialization.java
	public static void WriteFilter(String filename, Operator op, String dir, Filters filter) {
		FileOutputStream f = null;
		ObjectOutputStream o;
		try {
			f = new FileOutputStream(dir + "/" + filename);
			o = new ObjectOutputStream(f);
			o.writeObject(filter);
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
