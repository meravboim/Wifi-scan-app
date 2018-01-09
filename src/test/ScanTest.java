package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import object.Cordinate;
import object.Scan;
import object.WifiData;

public class ScanTest {


	@Test
	public void testScanScan() {
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor= new Cordinate(32,34,21);
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		Scan o = new Scan (temp);
		if(!temp.getTime().equals(o.getTime()) && !temp.getId().equals(o.getId()) && !temp.getCore().equals(o.getCore()) && 
				temp.getWifiNetWork()!=(o.getWifiNetWork()) && !temp.getWifi().equals(o.getWifi()))
			fail("Not yet implemented");
	}


	@Test
	public void testGetTime() {
		Cordinate cor= new Cordinate(32,34,21);
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		if(!temp.getTime().equals("28/10/2017  20:10:00"))
			fail("Not yet implemented");
	}

	@Test
	public void testGetCore() {
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor= new Cordinate(32,34,21);
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		if(temp.getCore().getAlt()!=21 && temp.getCore().getLon()!=32 && temp.getCore().getLat()!=34)
			fail("Not yet implemented");
	}

	@Test
	public void testSetCore() {
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor= new Cordinate(32,34,21);
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		Cordinate core= new Cordinate(20,30,231);
		temp.setCore(core);
		if(!temp.getCore().equal(temp.getCore(), core))
			fail("Not yet implemented");
	}

	@Test
	public void testSetTime() {
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor= new Cordinate(32,34,21);
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		temp.setTime("27/10/2017  20:11:00");
		if(!temp.getTime().equals("27/10/2017  20:11:00"))
			fail("Not yet implemented");
	}

	@Test
	public void testGetId() {
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor= new Cordinate(32,34,21);
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		if(!temp.getId().equals("ONEPLUS A3003_28_171012"))
			fail("Not yet implemented");
	}

	@Test
	public void testSetId() {
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor= new Cordinate(32,34,21);
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		temp.setId("ONEPLUS _28_171012");
		if(!temp.getId().equals("ONEPLUS _28_171012"))
			fail("Not yet implemented");
	}

	@Test
	public void testGetWifiNetWork() {
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor= new Cordinate(32,34,21);
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		if(temp.getWifiNetWork()!=t.size())		
			fail("Not yet implemented");
	}

	@Test
	public void testGetWifi() {
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor= new Cordinate(32,34,21);
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		if(!temp.getWifi().equals(t))
			fail("Not yet implemented");
	}

	@Test
	public void testSetWifi() {
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor= new Cordinate(32,34,21);
		Scan temp = new Scan("28/10/2017  20:10:00","ONEPLUS A3003_28_171012",cor,t);
		ArrayList<WifiData> k = new ArrayList<WifiData>();
		temp.setWifi(k);
		if(!temp.getWifi().equals(k))
		fail("Not yet implemented");
	}

}
