import object.*;
import Files.*;
import Algoritem.*;
import Filter.*;

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
		fe.readForCsv("C:\\Users\\yitzhak\\Desktop\\boaz1");

		/**
		 * enter Database
		 */
		FileKml fl = new FileKml();
		ArrayList<Scan> scan = fl.readFromCsv("Table.csv");
	/**
		 *   turn on the kml with filters
		 */
//		Filter k = new Filter();
//		k.ChekFilterForKml(scan);

		/**
		 * turn on the Algoritem for calculate approximate location, and the csv files we want to check
		 */
		ArrayList<Scan> sample = fl.readFromCsv("C:\\Users\\yitzhak\\Desktop\\_comb_no_gps_ts2_.csv");
		Algoritem temp1 = new Algoritem();
		temp1.algo2tocsv(scan, sample);
		//temp1.algo1tocsv(scan);


		long b= System.currentTimeMillis();
		System.out.println(b-a);

	}

}
