package test;

import static org.junit.Assert.*;

import org.junit.Test;

import object.AllData;

public class AllDataTest {

	@Test
	public void testAllDataStringStringStringStringStringStringStringStringStringStringStringString() {
		AllData temp= new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getAccuracyMeters().equals("16")  || !temp.getId().equals("NRD90M.G920FXXU5EQD3") || !temp.getChannel().equals("11") 
				|| !temp.getAlt().equals("688.1184747") || !temp.getMac().equals("24:c9:a1:33:34:68") || !temp.getMode().equals("[ESS]")
				|| !temp.getLat().equals("32.10432895") || !temp.getLon().equals("35.20499025") || !temp.getTime().equals("26/10/2017  14:07:00") ||
				!temp.getSsid().equals("Ariel_University") || !temp.getType().equals("WIFI")  || !temp.getSignal().equals("-56"))
			fail("there is a problem with the  constractur");
	}


	@Test
	public void testAllDataAllData() {
		AllData o = new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		AllData temp = new AllData(o);
		if(!temp.getSsid().equals(o.getSsid()) ||!temp.getMac().equals(o.getMac())  || !temp.getId().equals(o.getId()) ||
				!temp.getMode().equals(o.getMode()) || !temp.getTime().equals(o.getTime()) || !temp.getChannel().equals(o.getChannel())||
				!temp.getSignal().equals(o.getSignal()) || !temp.getLat().equals(o.getLat()) || !temp.getLon().equals(o.getLon())
				||!temp.getAlt().equals(o.getAlt()) || !temp.getAccuracyMeters().equals(o.getAccuracyMeters()) || !temp.getType().equals(o.getType())) 

			fail("there is a problem with the copy constractur");
	}

	@Test
	public void testGetId() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getId().equals("NRD90M.G920FXXU5EQD3"))
		fail("there is a problem with the method GetId");
	}

	@Test
	public void testSetId() {
		AllData temp = new AllData();
		temp.setId("NRD90M.G920FXXU5EQD3");
		if(!temp.getId().equals("NRD90M.G920FXXU5EQD3"))
		fail("there is a problem with the method SetId");
	}

	@Test
	public void testGetMac() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getMac().equals("24:c9:a1:33:34:68"))
		fail("there is a problem with the method GetMac");
	}

	@Test
	public void testSetMac() {
		AllData temp = new AllData();
		temp.setMac("24:c9:a1:33:34:68");
		if(!temp.getMac().equals("24:c9:a1:33:34:68"))
		fail("there is a problem with the method SetMac");
	}

	@Test
	public void testGetSsid() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getSsid().equals("Ariel_University"))
		fail("there is a problem with the method GetSsid");
	}

	@Test
	public void testSetSsid() {
		AllData temp = new AllData();
		temp.setSsid("Ariel_University");
		if(!temp.getSsid().equals("Ariel_University"))
		fail("there is a problem with the method SetSsid");
	}

	@Test
	public void testGetMode() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getMode().equals("[ESS]"))
		fail("there is a problem with the method GetMode");
	}

	@Test
	public void testSetMode() {
		AllData temp = new AllData();
		temp.setMode("[ESS]");
		if(!temp.getMode().equals("[ESS]"))
		fail("there is a problem with the method SetMode");
	}

	@Test
	public void testGetTime() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getTime().equals("26/10/2017  14:07:00"))
		fail("there is a problem with the method GetTime");
	}

	@Test
	public void testSetTime() {
		AllData temp = new AllData();
		temp.setTime("26/10/2017  14:07:00");
		if(!temp.getTime().equals("26/10/2017  14:07:00"))
		fail("there is a problem with the method SetTime");
	}

	@Test
	public void testGetChannel() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getChannel().equals("11"))
		fail("there is a problem with the method GetChannel");
	}

	@Test
	public void testSetChannel() {
		AllData temp = new AllData();
		temp.setChannel("11");
		if(!temp.getChannel().equals("11"))
		fail("there is a problem with the method SetChannel");
	}

	@Test
	public void testGetSignal() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getSignal().equals("-56"))
		fail("there is a problem with the method GetSignal");
	}

	@Test
	public void testSetSignal() {
		AllData temp = new AllData();
		temp.setSignal("-56");
		if(!temp.getSignal().equals("-56"))
		fail("there is a problem with the method SetSignal");
	}

	@Test
	public void testGetLat() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getLat().equals("32.10432895"))
		fail("there is a problem with the method GetLat");
	}

	@Test
	public void testSetLat() {
		AllData temp = new AllData();
		temp.setLat("32.10432895");
		if(!temp.getLat().equals("32.10432895"))
		fail("there is a problem with the method SetLat");
	}

	@Test
	public void testGetLon() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getLon().equals("35.20499025"))
		fail("there is a problem with the method GetLon");
	}

	@Test
	public void testSetLon() {
		AllData temp = new AllData();
		temp.setLon("35.20499025");
		if(!temp.getLon().equals("35.20499025"))
		fail("there is a problem with the method SetLon");
	}

	@Test
	public void testGetAlt() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getAlt().equals("688.1184747"))
		fail("there is a problem with the method GetAlt");
	}

	@Test
	public void testSetAlt() {
		AllData temp = new AllData();
		temp.setAlt("688.1184747");
		if(!temp.getAlt().equals("688.1184747"))
		fail("there is a problem with the method SetAlt");
	}

	@Test
	public void testGetAccuracyMeters() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getAccuracyMeters().equals("16"))
		fail("there is a problem with the method GetAccuracyMeters");
	}

	@Test
	public void testSetAccuracyMeters() {
		AllData temp = new AllData();
		temp.setAccuracyMeters("16");
		if(!temp.getAccuracyMeters().equals("16"))
		fail("there is a problem with the method SetAccuracyMeters");
	}

	@Test
	public void testGetType() {
		AllData temp = new AllData ("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI");
		if(!temp.getType().equals("WIFI"))
		fail("there is a problem with the method GetType");
	}

	@Test
	public void testSetType() {
		AllData temp = new AllData();
		temp.setType("WIFI");
		if(!temp.getType().equals("WIFI"))
		fail("there is a problem with the method SetType");
	}

}
