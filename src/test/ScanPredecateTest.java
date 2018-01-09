package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;

import org.junit.Test;

import Files.FileKml;
import Filter.ScanPredecate;
import object.Cordinate;
import object.Database;
import object.Scan;

public class ScanPredecateTest {

	@Test
	public void testSelectById() {
		ScanPredecate t = new ScanPredecate();
		String id ="dreamlte";
		FileKml l = new FileKml();
		ArrayList<Scan> temp =new ArrayList();
		Database c = new Database();
		c.getDatabase().addAll(l.readFromCsv("Table.csv"));
		for (int i = 0; i < c.getDatabase().size(); i++) {
				if(id.equals( c.getDatabase().get(i).getId()))
						temp.add(c.getDatabase().get(i));
			}
			if(t.filters(c.getDatabase(), t.SelectById(id)).size()!=temp.size())
				fail("Not yet implemented");
	}

	@Test
	public void testSelectByPlace() {
		ScanPredecate t = new ScanPredecate();
		FileKml l = new FileKml();
		ArrayList<Scan> temp =new ArrayList();
		Database c = new Database();
		c.getDatabase().addAll(l.readFromCsv("Table.csv"));
		double rad = 40;
		Cordinate cord = new Cordinate(32,34,20);
		for (int i = 0; i < c.getDatabase().size(); i++) {
		if(cord.distance(cord, c.getDatabase().get(i).getCore())<= rad)
			temp.add( c.getDatabase().get(i));
		}
		if(t.filters(c.getDatabase(), t.SelectByPlace(rad, cord)).size()!=temp.size())	
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByTime() {
		ScanPredecate t = new ScanPredecate();
		FileKml l = new FileKml();
		ArrayList<Scan> temp =new ArrayList();
		Database c = new Database();
		c.getDatabase().addAll(l.readFromCsv("Table.csv"));
		Scan s = new Scan();
		Date min =Scan.stringToDate("28/10/2017 20:10:00");
		Date max =Scan.stringToDate("29/10/2017 20:10:00");

		for (int i = 0; i < c.getDatabase().size(); i++) {
		if(Scan.stringToDate(c.getDatabase().get(i).getTime()).after(min) &&Scan.stringToDate(c.getDatabase().get(i).getTime()).before(max) )
			temp.add( c.getDatabase().get(i));
		}
		if(t.filters(c.getDatabase(), t.SelectByTime(min, max)).size()!=temp.size())	
		fail("Not yet implemented");
	}

	

}
