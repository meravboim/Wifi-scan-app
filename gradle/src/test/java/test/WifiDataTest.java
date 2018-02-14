package test;

import static org.junit.Assert.*;

import org.junit.Test;

import object.WifiData;

public class WifiDataTest {



	@Test
	public void testWifiDataWifiData() {
		WifiData o = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		WifiData temp = new WifiData(o);
		if(!temp.getSSID().equals(o.getSSID())|| !temp.getMAC().equals(o.getMAC()) ||
				!temp.getSignal().equals(o.getSignal()) || !temp.getFrequncy().equals(o.getFrequncy()))
		fail("there is a problem with the copy constractur");
	}

	@Test
	public void testGetSSID() {
		WifiData temp = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		if(!temp.getSSID().equals("FHOME"))
		fail("there is a problem with the method GetSSID");
	}

	@Test
	public void testSetSSID() {
		WifiData temp = new WifiData();
		temp.setSSID("FHOME");
		if(!temp.getSSID().equals("FHOME"))
		fail("there is a problem with the method SetSSID");

	}

	@Test
	public void testGetMAC() {
		WifiData temp = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		if(!temp.getMAC().equals("14:cc:20:c8:83:9c"))
		fail("there is a problem with the method GetMAC");
	}

	@Test
	public void testSetMAC() {
		WifiData temp = new WifiData();
		temp.setMAC("14:cc:20:c8:83:9c");
		if(!temp.getMAC().equals("14:cc:20:c8:83:9c"))
		fail("there is a problem with the method SetMAC");
	
	}

	@Test
	public void testGetFrequncy() {
		WifiData temp = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		if(!temp.getFrequncy().equals("2432"))
		fail("there is a problem with the method GetFrequncy");
	}

	@Test
	public void testSetFrequncy() {
		WifiData temp = new WifiData();
		temp.setFrequncy("2432");
		if(!temp.getFrequncy().equals("2432"))
		fail("there is a problem with the method SetFrequncy");
	}

	@Test
	public void testGetSignal() {
		WifiData temp = new WifiData("FHOME","14:cc:20:c8:83:9c","2432", "-83");
		if(!temp.getSignal().equals("-83"))
		fail("there is a problem with the method GetSignal");
	}

	@Test
	public void testSetSignal() {
		WifiData temp = new WifiData();
		temp.setSignal("-83");
		if(!temp.getSignal().equals("-83"))
		fail("there is a problem with the method SetSignal");
	}

}
