package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import Algoritem.Calculate;
import object.Cordinate;
import object.MacData;
import object.WifiData;

public class CalculateTest {

	@Test
	public void testCor1() {
		ArrayList<MacData> macs = new ArrayList<MacData>();
		WifiData a1 = new WifiData("SIEMENS_LivingRoom","d4:6e:0e:3c:44:e1","2412","-77");
		WifiData a3 = new WifiData("SIEMENS_LivingRoom","d4:6e:0e:3c:44:e1","2412","-84");


		Cordinate a2 = new Cordinate(32,34,34);
		MacData a = new MacData(a1,a2,"01/12/2017 10:59:44");
		macs.add(a);
		Cordinate b2 = new Cordinate(33,35,37);
		MacData b = new MacData(a3,b2,"01/12/2017 10:59:44");

		Cordinate t = new Cordinate(31,38,39);
		MacData c = new MacData(a1,t,"01/12/2017 10:59:44");
		macs.add(b);
		macs.add(c);
		a.setCore(t);
		a.setSignal("-84");
		Calculate k =new Calculate ();
		Cordinate  l=new Cordinate(31.62694300518135,37.05958549222798,38.37305699481866);
		System.out.println(Calculate.cor1(macs).toString());
		if(!l.equal(l,Calculate.cor1(macs)) ){
		fail("There is a broblem withthe method cor1");
		}
	}

	@Test
	public void testCor2() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindw() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindWeight() {
		assertTrue(Calculate.findWeight(-4)==(0.0625));
		assertTrue(Calculate.findWeight(20)==(1.0/(20*20)));
		assertTrue(Calculate.findWeight(-70)==(1.0/(-70*-70)));
	}

	@Test
	public void testWeightcore() {
		assertTrue(Calculate.weightcore(0.5,70)==35);
		assertTrue(Calculate.weightcore(0.66,32.55)==21.483);
		assertTrue(Calculate.weightcore(0.9,34.4)==30.96);
	}

}
