import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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
		if(!l.equal(l,Calculate.cor1(macs)) ){
		fail("There is a broblem withthe method cor1");
		}
	}

	@Test
	public void testCor2() {
		Cordinate core = new Cordinate(1,2,3);
		Pi p = new Pi(core,0.5);
		Pi p1 = new Pi(core,0.6);
		Pi p2 = new Pi(core,0.7);
		Pi p3 = new Pi(core,0.8);
		Pi p4 = new Pi(core,0.9);
		Pi p5 = new Pi(core,0.1);
		Pi p6 = new Pi(core,0.2);
		Pi p7 = new Pi(core,0.3);
		ArrayList<Pi> temp=new ArrayList<Pi>();
		temp.add(p);
		temp.add(p1);
		temp.add(p2);
		temp.add(p3);
		temp.add(p4);
		temp.add(p5);
		temp.add(p6);
		temp.add(p7);
		Cordinate c = Calculate.cor2(temp);
		if(c.getLat()!=1||c.getLon()!=2||c.getAlt()!=3)
			fail("There is a broblem withthe method cor2");
		
		
		
	}

	@Test
	public void testFindw() {
		assertTrue(Calculate.findw("-120", "-70")==0.3536453281403808);
		assertTrue(Calculate.findw("-80", "-70")==0.8124636133744843);
		assertTrue(Calculate.findw("-30","-69")==0.48514993297198816);
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
