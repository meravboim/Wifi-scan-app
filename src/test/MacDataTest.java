package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import object.Cordinate;
import object.MacData;
import object.WifiData;

public class MacDataTest {

	@Test
	public void testMacDataWifiDataCordinateString() {
		Cordinate core = new Cordinate(1,2,3);
		WifiData o = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		MacData m=new MacData(o,core,"28/10/2017 20:10:00");
		if(!m.getCore().equals(core)||!m.getTime().equals(m.stringToDate("28/10/2017 20:10:00"))||!m.getSSID().equals("FHOME")||!m.getMAC().equals("14:cc:20:c8:83:9c")
				||!m.getFrequncy().equals("2432")||!m.getSignal().equals("-83"))
		fail("there is a problem with the  constractur");
	}

	@Test
	public void testMacData() {
		MacData m=new MacData();
		if(m.getCore()!=null||m.getTime()!=null||m.getFrequncy()!=null||m.getMAC()!=null||m.getSignal()!=null||m.getSSID()!=null)
		 fail("there is a problem with the  empty constractur");
	}

	@Test
	public void testMacDataMacData() {
		Cordinate core = new Cordinate(1,2,3);
		WifiData o = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		MacData f=new MacData(o,core,"28/10/2017 20:10:00");
		MacData m=new MacData(f);
		if(!m.getCore().equals(f.getCore())||!m.getTime().equals(f.getTime())||!m.getSSID().equals(f.getSSID())
				||!m.getMAC().equals(f.getMAC())||!m.getFrequncy().equals(f.getFrequncy())||
				!m.getSignal().equals(f.getSignal()))
		
				fail("there is a problem with the copy constractur");
	}

	@Test
	public void testGetTime() {
		Cordinate core = new Cordinate(1.0,2.0,3.0);
		WifiData o = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		MacData f=new MacData(o,core,"28/10/2017 20:10:00");
		Date e = f.stringToDate("28/10/2017 20:10:00");
		if(!f.getTime().equals(e))
		fail("there is a problem with the method gettime");
	}

	@Test
	public void testSetTime() {
		Cordinate core = new Cordinate(1,2,3);
		WifiData o = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		MacData f=new MacData(o,core,"28/10/2017 20:10:00");
		f.setTime("28/10/2017 20:11:00");

		if(!f.getTime().equals(f.stringToDate("28/10/2017 20:11:00")))
		fail("there is a problem with the method settime");
	}

	@Test
	public void testGetCore() {
		Cordinate core = new Cordinate(1.0,2.0,3.0);
		WifiData o = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		MacData f=new MacData(o,core,"28/10/2017 20:10:00");
if(!f.getCore().equal(core, f.getCore()))
		fail("there is a problem with the method getcore");
	}

	@Test
	public void testSetCore() {
		Cordinate core = new Cordinate(1.0,2.0,3.0);
		WifiData o = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		MacData f=new MacData(o,core,"28/10/2017 20:10:00");
		Cordinate core1 = new Cordinate(2.0,1.0,4.0);
		f.setCore(core1);
		if(!f.getCore().equal(f.getCore(), core1))
		fail("there is a problem with the method setcore");
	}

}
