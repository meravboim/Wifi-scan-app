package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import Files.FileCsv;
import Files.FileKml;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import object.AllData;
import object.Cordinate;
import object.Database;
import object.Scan;
import object.WifiData;

public class FileCsvTest {

	@Test
	public void testGetPath() {
		FileCsv f = new  FileCsv("C:\\Users\\yitzhak\\Desktop\\wiglewifi");
		if(!f.getPath().equals("C:\\Users\\yitzhak\\Desktop\\wiglewifi"))
			fail("there is problem with the method GetPath");
	}

	@Test
	public void testSetPath() {
		FileCsv f = new  FileCsv("C:\\Users\\yitzhak\\Desktop\\wiglewifi");
		f.setPath("C:\\Users\\yitzhak\\Desktop\\Simple_1");
		if(!f.getPath().equals("C:\\Users\\yitzhak\\Desktop\\Simple_1"))
			fail("there is problem with the method SetPath");
	}

	@Test
	public void testFileCsvString() {
		FileCsv f = new  FileCsv("C:\\Users\\yitzhak\\Desktop\\wiglewifi");
		if(!f.getPath().equals("C:\\Users\\yitzhak\\Desktop\\wiglewifi"))
			fail("there is problem with the constractor");
	}


	@Test
	public void testFileCsvFileCsv() {
		FileCsv o = new  FileCsv("C:\\Users\\yitzhak\\Desktop\\wiglewifi");
		FileCsv temp = new  FileCsv(o);
		if(!temp.getPath().equals(o.getPath()))
			fail("there is problem with the copy constractor");
	}

	@Test
	public void testReadForCsv() {
		FileCsv f = new FileCsv();
			if(f.readForCsv("database").getDatabase().size()==0)
			 fail("there is problem with the method readForCsv");

			// TODO Auto-generated catch block

	}

	@Test
	public void testFindId() {
		FileCsv f = new  FileCsv();
		String id=f.FindId("display=ONEPLUS A3003_28_171012");
		if(!id.endsWith("ONEPLUS A3003_28_171012" ))
		 fail("there is problem with the method findId");
	}

	@Test
	public void testSotrByScan() {
		FileCsv f = new  FileCsv();
		ArrayList<AllData>num=new ArrayList<AllData>();
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","34.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","8.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  13:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","8.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","8.20499025","688.1184747","16","WIFI"));
			if(f.sotrByScan(num).getDatabase().size()>0){}
			else
			 fail("there is problem with the method sotrByScan");

	
	} 

	@Test
	public void testSortAndWrite() {
		FileCsv f = new  FileCsv();
		Database t = new Database (); 
		ArrayList<AllData>num=new ArrayList<AllData>();
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","34.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","8.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  13:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","8.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","8.20499025","688.1184747","16","WIFI"));
		
			if(f.SortAndWrite(0, 4, num, t).getDatabase().size()<1)
			 fail("there is problem with the method SortAndWrite");
	}

	@Test
	public void testWriteWifiData() {
		FileCsv f = new  FileCsv();
		int[] index=new int[11];
		index[10]=4;
		index[0]=0;
		index[1]=1;
		index[2]=2;
		index[3]=3;
		ArrayList<AllData>num=new ArrayList<AllData>();
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","34.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","8.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  13:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		ArrayList<WifiData>temp =f.WriteWifiData(num,index);
		if(temp.size()!=4)
		 fail("there is problem with the method WriteWifiData");
	}

	@Test
	public void testChanneltoFrequncy() {
		FileCsv f = new  FileCsv();
		if(!f.ChanneltoFrequncy("11").equals("2462"))
		fail("there is problem with the method ChanneltoFrequncy");
	}

	@Test
	public void testIndexOfMaxRSSIWifi() {
		FileCsv f = new  FileCsv();
		ArrayList<AllData>num=new ArrayList<AllData>();
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","34.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","8.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  13:07:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		int[] index1=f.IndexOfMaxRSSIWifi(0, 12, num);
		ArrayList<AllData>num2=new ArrayList<AllData>();
		num2.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","34.20499025","688.1184747","16","WIFI"));
		num2.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:09:00","11","-56","32.10432895","35.20499025","688.1184747","16","WIFI"));
		num2.add( new AllData("NRD90M.G920FXXU5EQD3","24:c9:a1:33:34:68","Ariel_University","[ESS]", "26/10/2017  14:07:00","11","-56","32.10432895","8.20499025","688.1184747","16","WIFI"));
		int[] index2=f.IndexOfMaxRSSIWifi(0, 2, num2);
		if(index1[10]!=10||index2[10]!=3)
		 fail("there is problem with the method IndexOfMaxRSSIWifi");
	}

	@Test
	public void testContains() {
		FileCsv f = new  FileCsv();
		int max[]={1,2,3,4,5,80,46,14,0,100};
		if(!f.contains(max, 80)||f.contains(max, 22))
		 fail("there is problem with the method contains");
	}

	@Test
	public void writecsv() throws IOException {
	FileCsv e= new FileCsv();
	FileKml l = new FileKml();
		ArrayList<Scan> num = new ArrayList<Scan>();
		ArrayList<WifiData> t = new ArrayList<WifiData>();
		Cordinate cor = new Cordinate(32, 34, 21);
		Scan temp = new Scan("28/10/2017  20:10:00", "ONEPLUS A3003_28_171012", cor,  t);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		num.add(temp);
		System.out.println(num.size());
		e.writecsv(num, "C:\\Users\\yitzhak\\Desktop\\test.csv");
		ArrayList<Scan> km=l.readFromCsv("C:\\Users\\yitzhak\\Desktop\\test.csv");
		for (int i = 0; i < km.size(); i++) {
			if(!num.get(i).equals(km.get(i))) {
				 fail("there is problem with the method writecsv");

			}
				
		}
			
		}
	
 


}