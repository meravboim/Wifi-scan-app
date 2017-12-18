import static org.junit.Assert.*;

import org.junit.Test;

public class PiTest {

	@Test
	public void testPiCordinateDouble() {
		Cordinate core = new Cordinate(1,2,3);
		Pi p = new Pi(core,0.5);
		if(p.getPi()!=0.5 || p.getLat()!=1 || p.getLon()!=2 || p.getAlt()!=3)
		fail("there is a problen with the constractur");
	}

	@Test
	public void testPi() {
		Pi p = new Pi();
		if(p.getPi()!=0 || p.getLat()!=-1 || p.getLon()!=-1 || p.getAlt()!=-1)
		fail("there is a problen with the empty constractur");
	}

	@Test
	public void testPiPi() {
		Cordinate core = new Cordinate(1,2,3);
		Pi pl = new Pi(core,0.5);
		Pi p = new Pi(pl);
		if(p.getPi()!=0.5 || p.getLat()!=1 || p.getLon()!=2 || p.getAlt()!=3)
		  fail("there is a problen with the copy constractur");
	}

	@Test
	public void testGetPi() {
		Cordinate core = new Cordinate(1,2,3);
		Pi p = new Pi(core,0.5);
		if(p.getPi()!=0.5)
		fail("there is a problen with the method getpi");
	}

	@Test
	public void testSetPi() {
		Cordinate core = new Cordinate(1,2,3);
		Pi p = new Pi(core,0.5);
		p.setPi(0.8);
		if(p.getPi()!=0.8)
		fail("there is a problen with the method setpi");
		
	}

}
