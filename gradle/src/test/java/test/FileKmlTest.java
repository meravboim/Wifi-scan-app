package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import Files.FileKml;
import object.Cordinate;
import object.Scan;
import object.WifiData;

public class FileKmlTest {

	@Test
	public void testGetPath() {
		FileKml f = new FileKml("C:\\Users\\yitzhak\\Desktop\\wiglewifi");
		if (!f.getPath().equals("C:\\Users\\yitzhak\\Desktop\\wiglewifi"))
			fail("there is problem with the method GetPath");
	}

	@Test
	public void testSetPath() {
		FileKml f = new FileKml("C:\\Users\\yitzhak\\Desktop\\wiglewifi");
		f.setPath("C:\\Users\\yitzhak\\Desktop\\Simple_1");
		if (!f.getPath().equals("C:\\Users\\yitzhak\\Desktop\\Simple_1"))
			fail("there is problem with the method SetPath");
	}

	@Test
	public void testFileKmlString() {
		FileKml f = new FileKml("C:\\Users\\yitzhak\\Desktop\\wiglewifi");
		if (!f.getPath().equals("C:\\Users\\yitzhak\\Desktop\\wiglewifi"))
			fail("there is problem with the constractor");
	}

	@Test
	public void testFileKMlFileKml() {
		FileKml o = new FileKml("C:\\Users\\yitzhak\\Desktop\\wiglewifi");
		FileKml temp = new FileKml(o);
		if (!temp.getPath().equals(o.getPath()))
			fail("there is problem with the copy constractor");
	}

	@Test
	public void testReadFromCsv() {
		FileKml f = new FileKml();
		
			if (f.readFromCsv("Table.csv").size()>0) {
			} else
				fail("there is problem with the method ReadFromCsvToKml");
	
	}

	@Test
	public void testColor() {
		FileKml f = new FileKml();
		ArrayList<WifiData> temp = new ArrayList<WifiData>();
		temp.add(new WifiData("FHOME", "14:cc:20:c8:83:9c", "2432", "-83"));
		temp.add(new WifiData("FHOME", "14:cc:20:c8:83:9c", "2432", "-60"));
		temp.add(new WifiData("FHOME", "14:cc:20:c8:83:9c", "2432", "-100"));
		if (!f.Color(temp, 0).equals("yellow") || !f.Color(temp, 1).equals("green") || !f.Color(temp, 2).equals("red"))
			fail("there is a problem with the method color");
	}

	@Test
	public void testCheckTime() {
		FileKml f = new FileKml();
		if (!f.CheckTime("08/09/2017 18:25:00").equals("2017-09-08 18:25:00"))
			fail("there is a problem with the method CheckTime");
	}

	@Test
	public void testTurnToKML() {
		FileKml f = new FileKml();
		ArrayList<Scan> num = new ArrayList<Scan>();
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor = new Cordinate(32, 34, 21);
		Scan temp = new Scan("28/10/2017  20:10:00", "ONEPLUS A3003_28_171012", cor,  t);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);

		if (f.TurnToKML(num, "Table.csv") == 0) {
		} else
			fail("there is problem with the method TurnToKML");

		// TODO Auto-generated catch block

	}
}
