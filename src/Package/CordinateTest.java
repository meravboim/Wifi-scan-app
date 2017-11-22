package Package;

import static org.junit.Assert.*;

import org.junit.Test;

public class CordinateTest {


	@Test
	public void testCordinatedoubledoubledouble() {
		Cordinate temp= new Cordinate(45.00003,32.000505,34.66660);
		if(temp.getLon()!= 45.00003||temp.getLat()!= 32.000505||temp.getAlt()!= 34.66660)
			fail("there is a problem with the  constractur");
	}


	@Test
	public void testCordinateCordinate() {
		Cordinate o = new Cordinate(45.00003,32.000505,34.66660);
		Cordinate temp = new Cordinate(o);
		if(temp.getLon()!=o.getLon()|| temp.getLat()!=o.getLat()||temp.getAlt()!=o.getAlt())

			fail("there is a problem with the copy constractur");
	}

	@Test
	public void testGetLon() {
		Cordinate temp= new Cordinate(45.00003,32.000505,34.66660);
		if(temp.getLon()!= 45.00003)
		fail("there is a problem with the method GetLon");
	}

	@Test
	public void testSetLon() {
		Cordinate temp = new Cordinate();
		temp.setLon( 45.00003);
		if(temp.getLon()!= 45.00003)
		fail("there is a problem with the method SetLon");
	}

	@Test
	public void testGetLat() {
		Cordinate temp= new Cordinate(45.00003,32.000505,34.66660);
		if(temp.getLat()!= 32.000505)
		fail("there is a problem with the method GetLat");
	}

	@Test
	public void testSetLat() {
		Cordinate temp = new Cordinate();
		temp.setLat(32.000505);
		if(temp.getLat()!= 32.000505)
		fail("there is a problem with the method SetLat");
	}

	@Test
	public void testGetAlt() {
		Cordinate temp= new Cordinate(45.00003,32.000505,34.66660);
		if(temp.getAlt()!= 34.66660)
		fail("there is a problem with the method GetAlt");
	}

	@Test
	public void testSetAlt() {
		Cordinate temp = new Cordinate();
		temp.setAlt(34.66660);
		if(temp.getAlt()!= 34.66660)
		fail("there is a problem with the method SetAlt");
	}


}
