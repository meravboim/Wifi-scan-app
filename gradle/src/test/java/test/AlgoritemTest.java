package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import Algoritem.Algoritem;
import object.Cordinate;
import object.MacData;
import object.WifiData;

public class AlgoritemTest {


	@Test
	public void testAlgo1() {
		ArrayList<MacData> macs = new ArrayList<MacData>();
		WifiData a1 = new WifiData("SIEMENS_LivingRoom","d4:6e:0e:3c:44:e1","2412","-77");
		Cordinate a2 = new Cordinate(32,34,34);
		MacData a = new MacData(a1,a2,"01/12/2017 10:59:44");
		macs.add(a);
		WifiData b1 = new WifiData("HP-Print-8A-Deskjet 4640 series","8c:dc:d4:6a:a9:8a","2462","-84");
		Cordinate b2 = new Cordinate(33,35,37);
		MacData b = new MacData(b1,b2,"01/12/2017 11:00:34");
		macs.add(b);
		a.setCore(b2);
		b.setCore(a2);	
		macs.add(a);
		macs.add(b);
		Cordinate t = new Cordinate(31,38, 39);
		a.setCore(t);
		a.setSignal("-84");
		b.setCore(t);
		macs.add(a);
		macs.add(b);
		Map<String, ArrayList<MacData>> find = new HashMap<String, ArrayList<MacData>>();
		for (int i = 0; i < macs.size(); i++) {
			if (find.containsKey(macs.get(i).getMAC())) {
				find.get(macs.get(i).getMAC()).add(macs.get(i));
			} else {
				ArrayList<MacData> temp = new ArrayList<MacData>();
				temp.add(macs.get(i));
				find.put(macs.get(i).getMAC(), temp);
			}
		}
		Algoritem k =new Algoritem();
		Cordinate  l=new Cordinate(32.05623472,35.53545232,36.53545232);
	
		assertEquals(k.algo1(find,"d4:6e:0e:3c:44:e1"),l);
	}

	@Test
	public void testWritetocsv() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlgo2tocsv() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlgo2() {
		fail("Not yet implemented");
	}

	@Test
	public void testDouplicate() {
		fail("Not yet implemented");
	}

	@Test
	public void testMatrix() {
		fail("Not yet implemented");
	}

}
