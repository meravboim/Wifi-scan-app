package Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;
import object.Cordinate;
import object.Database;
import object.Scan;
import object.WifiData;

import java.util.*;

public class FileKml {
	/**
	 * the class input is :csv file with the data of the Scan output: Kml file with
	 * the filter information.
	 */
	/**
	 * the path is the path to the csv file
	 */
	private String path;

	/**
	 * return the path
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * constructor
	 * 
	 * @param path
	 */
	public FileKml(String path) {
		this.path = path;
	}

	/**
	 * empty constructor
	 * 
	 * @param path
	 */
	public FileKml() {
		this.path = null;
	}

	/**
	 * copy constructor
	 * 
	 * @param path
	 */
	public FileKml(FileKml other) {
		this.path = other.path;
	}

	/**
	 * this method read the csv file that we created, and enter the data to
	 * ArrayList of the object Scan (that contains all the variables that inside the
	 * csv file)
	 * 
	 * @param path
	 * @throws IOException
	 */
	public ArrayList<Scan> readFromCsv(String path)  {
		ArrayList<Scan> arrayOfscan = new ArrayList<Scan>();
		File f = new File(path);
		FileInputStream fi=null;
		try {
			fi = new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scanner sc = new Scanner(fi);
		String str = sc.nextLine();
		String[] data1 = str.split(",");
		while (sc.hasNext()) {
			try {
				str = sc.nextLine();
				String[] data = str.split(",");
				ArrayList<WifiData> dataw = new ArrayList<WifiData>();
				int i = 6;
				for (int j = 0; j < Integer.parseInt(data[5]); j++) {
					WifiData temp = new WifiData(data[i], data[i + 1], data[i + 2], data[i + 3]);
					i = i + 4;
					dataw.add(temp);

				}
				Cordinate cord = new Cordinate();

				if(!data[3].equals("?"))
				 cord = new Cordinate(Double.parseDouble(data[2]), Double.parseDouble(data[3]),Double.parseDouble(data[4]));
				else
				 cord=new Cordinate(-1,-1,-1);
				Scan temp = new Scan(data[0], data[1], cord,  dataw);// he get a Date and not a String
				arrayOfscan.add(temp);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("there is a problem with the line:" + str);
			}
		}
		sc.close();
		try {
			fi.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayOfscan;
	}

	/**
	 * the method take the information from the ArrayList arrayOfscan and write it
	 * into Kml file
	 * 
	 * @param arrayOfscan
	 * @param path
	 * @throws IOException
	 */
	public int TurnToKML(ArrayList<Scan> arrayOfscan, String name) {
		Collections.sort(arrayOfscan,Scan.getCompByTime);
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		for (int i = 0; i < arrayOfscan.size(); i++) {
			for (int j = 0; j < arrayOfscan.get(i).getWifi().size(); j++) {
				try {
					doc.createAndAddPlacemark().withName(arrayOfscan.get(i).getWifi().get(j).getSSID())
					.withTimePrimitive(turnTime(arrayOfscan.get(i).getTime()))
					.withDescription("Wifi num: " + j + " Mac: " + arrayOfscan.get(i).getWifi().get(j).getMAC()
							+ " Frequency: " + arrayOfscan.get(i).getWifi().get(j).getFrequncy() + " Signal: "
							+ arrayOfscan.get(i).getWifi().get(j).getSignal())
					.createAndSetPoint().addToCoordinates(arrayOfscan.get(i).getCore().getLon(),
							arrayOfscan.get(i).getCore().getLat(), arrayOfscan.get(i).getCore().getAlt());
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		try {
			kml.marshal(new File(name));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error mar");
		}
		System.out.println("the Kml file was created");
		return 0;
	}

	/**
	 * the method decide which color the point will be, by the Signal
	 * 
	 * @param select
	 * @param i
	 * @return
	 */

	public String Color(ArrayList<WifiData> select, int i) {
		if (Integer.parseInt(select.get(i).getSignal()) > -70) {
			return "green";
		} else if (Integer.parseInt(select.get(i).getSignal()) < -70
				&& Integer.parseInt(select.get(i).getSignal()) > -90) {
			return "yellow";
		} else {
			return "red";
		}
	}

	/**
	 * turn the time to TimeStamp object
	 * 
	 * @param time
	 * @return
	 */
	public TimeStamp turnTime(String time) {
		time = time.replace("-", "/");
		time = CheckTime(time);
		String day = "" + time.charAt(8) + time.charAt(9);
		String year = "" + time.charAt(0) + time.charAt(1) + time.charAt(2) + time.charAt(3);
		String month = "" + time.charAt(5) + time.charAt(6);
		String hour, minute, second;
		hour = "" + time.charAt(11) + time.charAt(12);
		minute = "" + time.charAt(14) + time.charAt(15);
		if (time.length() <= 16)
			second = "00";
		else
			second = "" + time.charAt(17) + time.charAt(18);
		TimeStamp t = new TimeStamp();
		String turntime = "" + year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second + "Z";
		t.setWhen(turntime);
		return t;
	}

	/**
	 * check if the time is exhibit in American date format
	 * 
	 * @param time1
	 * @return
	 */
	public static String CheckTime(String time1) {
		String[] Time = time1.split(" ");
		String time = "";
		String[] Date = Time[0].split("/");
		if (Date[0].length() == 4) {
			time += Date[0] + "-" + Date[1] + "-" + Date[2] + " " + Time[1];
			return time;
		}

		else
			time += Date[2] + "-" + Date[1] + "-" + Date[0] + " " + Time[1];
		return time;
	}
	public static boolean checkinput(String input, String format) {
		if (format.equals("dd/mm/yyyy")) {
			if ((input.length() == 10) && (input.charAt(2) == '/') && (input.charAt(5) == '/'))
				return true;
		}

		if (format.equals("hh:mm:ss")) {
			if ((input.length() == 8) && (input.charAt(2) == ':') && (input.charAt(5) == ':'))
				return true;
		}
		return false;
	}

}
