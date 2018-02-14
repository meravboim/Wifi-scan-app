import object.*;
import Files.*;
import Algoritem.*;
import Filter.*;

import java.io.IOException;
import java.util.ArrayList;

import Files.FileCsv;
import Files.FileKml;
import object.Scan;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long a =System.currentTimeMillis();
		/**
//		 * read wigle-wifi files and filter them by Scan
//		 */

		FileCsv fe = new FileCsv();
		Database data = new Database(fe.readForCsv("database"));
		System.out.println("num of object = "+data.getDatabase().size());
		try {
			fe.writecsv(data.getDatabase(), "Table.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * enter Database
		 */
		FileKml fl = new FileKml();
		ChooseFilter r = new ChooseFilter();
		//r.ChekFilterForKml(data);
		System.out.println("size befor filter "+data.getDatabase().size());
		Filters f1 = new FilterByID("OnePlus3T");
		System.out.println(f1.toString());
		Cordinate t = new Cordinate(32,34,0);
		Filters f3= new FilterByPlace(t,30);
		Filters f2 = new FilterByID("dreamlte");
		Filters fi = new OrFilter(f1,f2);
		Makefilter tt = new Makefilter(fi);
		ArrayList <Scan> te = new ArrayList<Scan>();
		te.addAll(tt.filtering(data.getDatabase()));
		System.out.println("size after filter "+te.size());
		//data.addArrayList( fl.readFromCsv("Table.csv"));
		/**
		 *   turn on the kml with filters
		 */
		//	k.ChekFilterForKml(data);

		/**
		 * turn on the Algoritem for calculate approximate location, and the csv files we want to check
		 */
	//	ArrayList<Scan> sample = fl.readFromCsv("C:\\Users\\yitzhak\\Desktop\\_comb_no_gps_ts1.csv");
		Algoritem temp1 = new Algoritem();
		//temp1.algo2tocsv(data, sample);
		temp1.algo1tocsv(data.getDatabase());
		System.out.println(temp1.algo1Hash(data.getDatabase()).size());

		long b= System.currentTimeMillis();
		System.out.println(b-a);
		
		Scan aa = new Scan (data.getDatabase().get(0));
		Scan aabb = new Scan (data.getDatabase().get(0));
		if(aa.equals(aabb)){
			System.out.println("ok");
			
		}

		
		
		

	}

}
