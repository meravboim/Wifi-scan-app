package Files;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import object.*;
import Filter.AndFilter;
import Filter.Filters;
import Filter.Operator;

public class Write_filter {

	public static void WriteFilter( Filters filter) throws IOException {
		FileOutputStream f = null;
		ObjectOutputStream o;
		try {
			f = new FileOutputStream("filters.txt");
			o = new ObjectOutputStream(f);
			o.writeObject(filter);
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}


		System.out.println("filter is write ...........................");

	}

	public static Filters Read_Filter() throws IOException, ClassNotFoundException {
		FileInputStream fi = new FileInputStream("filters.txt");
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] bytes = baos.toByteArray();
//		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(fi);
		Filters  e2 = (Filters) ois.readObject();
		System.out.println( "e2= "+e2);
		ois.close();
//		bais.close();1
//		baos.close();
		
		
return e2;
	}



}
