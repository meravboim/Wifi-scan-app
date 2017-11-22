package Package;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class FilterTest {

	@Test
	public void testChekFilterForKml() {
		Filter f = new Filter();
		ArrayList<Scan> num = new ArrayList<Scan>();
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor = new Cordinate(32, 34, 21);
		Scan temp = new Scan("28/10/2017  20:10:00", "ONEPLUS A3003_28_171012", cor, "4", t);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);

		if (f.TurnToKML(num, "Table.csv") == 0) {
		} else
			fail("there is problem with the method ChekFilterForKml");
	}

	@Test
	public void testDistance() {
		Filter f = new Filter();
		if (f.distance(3.4, 5, 8.4, 9) != Math.sqrt(41))
			fail("There is a broblem with the method Distance");
	}

	@Test
	public void testCheckinput() {
		Filter f = new Filter();
		if (!f.checkinput("08/09/2017", "dd/mm/yyyy") || !f.checkinput("12:30:00", "hh:mm:ss"))
			fail("There is a broblem with the method Checkinput");
	}



	@Test
	public void testCheckTime() {
		Filter f = new Filter();
		String s = f.CheckTime("10/07/2017 12:30:00");
		String l = f.CheckTime("2017/07/10 12:30:00");
		if (!s.equals("2017-07-10 12:30:00") || !l.equals("2017-07-10 12:30:00"))
			fail("There is a broblem with the method CheckTime");
	}
	

	@Test
	public void testSelectByPlace() {
		Filter f = new Filter();
		ArrayList<Scan> num = new ArrayList<Scan>();
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor = new Cordinate(32, 34, 21);
		Scan temp = new Scan("28/10/2017  20:10:00", "ONEPLUS A3003_28_171012", cor, "4", t);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		ArrayList<Scan>temp1=f.SelectByPlace(num, 4, 32, 34);
		if(temp1.size()!=5)
		fail("There is a broblem with the method SelectByPlace");
	}

	@Test
	public void testSelectById() {
		Filter f = new Filter();
		ArrayList<Scan> num = new ArrayList<Scan>();
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor = new Cordinate(32, 34, 21);
		Scan temp = new Scan("28/10/2017  20:10:00", "ONEPLUS A3003_28_171012", cor, "4", t);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		ArrayList<Scan>temp1=f.SelectById(num,"ONEPLUS A3003_28_171012");
		if(temp1.size()!=5)
		fail("There is a broblem with the method SelectById");
	}

	@Test
	public void testSelectByTime() {
		Filter f=new Filter();
		ArrayList<Scan> num = new ArrayList<Scan>();
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor = new Cordinate(32, 34, 21);
		Scan temp = new Scan("28/10/2017  20:10:00", "ONEPLUS A3003_28_171012", cor, "4", t);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
	Date minTime=f.stringToDate("28/10/2017 20:09:00");
	Date maxTime=f.stringToDate("28/10/2017 20:11:00");
		ArrayList<Scan>temp1=f.SelectByTime(num,minTime,maxTime);
		if(temp1.size()!=5)
		fail("There is a broblem with the method SelectByTime");
	}



}
