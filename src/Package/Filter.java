package Package;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.junit.experimental.max.MaxCore;

public class Filter extends FileKml {
	/*
	 * this class contains methods that filter the data from the csv table for
	 * create kml file
	 */

	/**
	 * the method give the user to decide how to filter the data
	 * 
	 * @param arryOfscan
	 * @throws IOException
	 */

	public static int ChekFilterForKml(ArrayList<Scan> arryOfscan) throws IOException {
		FileKml fe = new FileKml();
		System.out.println("Enter 1 to select by time, 2 to select by place or 3 to select by id");
		Scanner sc = new Scanner(System.in);
		int select = sc.nextInt();
		if (select == 1) {
			int legalinput = 0;
			System.out.println("Enter MinDate dd/mm/yyyy");
			String MinTime = sc.next();
			while (legalinput == 0) {
				if (checkinput(MinTime, "dd/mm/yyyy"))
					legalinput = 1;
				else {
					System.out.println("The format is wrong Enter MinDate dd/mm/yyyy");
					MinTime = sc.next();
				}
			}
			System.out.println("Enter MinClock hh:mm:ss");
			String MinClock = sc.next();
			while (legalinput == 0) {
				if (checkinput(MinClock, "hh:mm:ss"))
					legalinput = 1;
				else {
					System.out.println("The format is wrong Enter MinClock hh:mm:ss");
					MinClock = sc.next();
				}
			}
			System.out.println("Enter MaxDate dd/mm/yyyy");
			String MaxTime = sc.next();
			while (legalinput == 0) {
				if (checkinput(MaxTime, "dd/mm/yyyy"))
					legalinput = 1;
				else {
					System.out.println("The format is wrong Enter MaxTime dd/mm/yyyy");
					MaxTime = sc.next();
				}
			}
			System.out.println("Enter MaxClock hh:mm:ss ");
			String MaxClock = sc.next();
			while (legalinput == 0) {
				if (checkinput(MaxClock, "hh:mm:ss"))
					legalinput = 1;
				else {
					System.out.println("The format is wrong Enter MaxClock hh:mm:ss");
					MaxClock = sc.next();
				}
			}
			Date min = new Date();
			Date max = new Date();
			min = stringToDate(MinTime + " " + MinClock);
			max = stringToDate(MaxTime + " " + MaxClock);
			// // TODO Auto-generated catch block
			// Date min = new Date();
			// Date max = new Date();
			// String s1 = "28/10/2017 20:11:00";
			// String s2 = "28/10/2017 21:32:17";
			// min = stringToDate(s1);
			// max = stringToDate(s2);
			fe.TurnToKML(SelectByTime(arryOfscan, min, max), "KmlByTime.kml");

		}
		if (select == 2) {
			System.out.println("Enter Radus, CenterLon and CenterLat");
			double Radus = sc.nextDouble();
			double CenterLon = sc.nextDouble();
			double CenterLat = sc.nextDouble();

			fe.TurnToKML(SelectByPlace(arryOfscan, Radus, CenterLon, CenterLat),
					"C:\\Users\\merav\\Desktop\\KmlByPlace1.kml");

		}
		if (select == 3) {
			System.out.println("Enter Id");
			String Id = sc.next();
			fe.TurnToKML(SelectById(arryOfscan, Id), "KmlById.kml");

		}

		sc.close();
		return 0;
	}

	/**
	 * this method filter the data by place
	 * 
	 * @param arrayOfscan
	 * @param Radus
	 * @param CenterLon
	 * @param CenterLat
	 * @return
	 */

	public static ArrayList<Scan> SelectByPlace(ArrayList<Scan> arrayOfscan, double Radus, double CenterLon,
			double CenterLat) {
		ArrayList<Scan> arrayOfplace = new ArrayList<Scan>();

		int indexPlace = 0;

		for (int i = 0; i < arrayOfscan.size(); i++) {

			if (distance(CenterLat, CenterLon, arrayOfscan.get(i).getCore().getLat(),
					arrayOfscan.get(i).getCore().getLon()) <= Radus) {
				arrayOfplace.add(arrayOfscan.get(i));

				indexPlace++;
			}
		}

		return arrayOfplace;
	}

	/**
	 * this method calculate the distance
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double distance(double x1, double y1, double x2, double y2) {
		double x = Math.pow(x1 - x2, 2);
		double y = Math.pow(y1 - y2, 2);
		double dis = Math.sqrt(x + y);
		return dis;
	}

	/**
	 * this method filter the data by id
	 * 
	 * @param arrayOfscan
	 * @param Id
	 * @return
	 */

	public static ArrayList<Scan> SelectById(ArrayList<Scan> arrayOfscan, String Id) {

		ArrayList<Scan> arrayOfId = new ArrayList<Scan>();

		for (int i = 0; i < arrayOfscan.size(); i++) {
			if (Id.equals(arrayOfscan.get(i).getId())) {
				arrayOfId.add(arrayOfscan.get(i));
			}

		}
		System.out.println(arrayOfId);
		return arrayOfId;
	}

	/**
	 * this method filter the data by time
	 * 
	 * @param arrayOfscan
	 * @param MinTime
	 * @param MaxT1ime
	 * @return
	 * @throws ParseException
	 */

	public static ArrayList<Scan> SelectByTime(ArrayList<Scan> arrayOfscan, Date minTime, Date maxTime) {
		ArrayList<Scan> arrayOftime = new ArrayList<Scan>();

		for (int i = 0; i < arrayOfscan.size(); i++) {
			Date d = stringToDate(arrayOfscan.get(i).getTime());
			if (d.after(minTime) && d.before(maxTime)) {
				arrayOftime.add(arrayOfscan.get(i));
			}
		}
		return arrayOftime;
	}

	/**
	 * convert String to Date
	 * https://www.mkyong.com/java/java-date-and-calendar-examples/
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String time) {

		time = time.replace("-", "/");
		// time = CheckTime(time);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy  hh:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(time);
			// System.out.println(date.toString());
			return date;
		} catch (ParseException e) {
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/mm/yyyy hh:mm");
		try {
			date = sdf2.parse(time);
			// System.out.println(date.toString());
			return date;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
		try {
			date = sdf3.parse(time);
			// System.out.println(date.toString());
			return date;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy/mm/dd hh:mm");
		try {
			date = sdf4.parse(time);
			// System.out.println(date.toString());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

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

	/**
	 * check if the input is in the same format of the String-format
	 * 
	 * @param input
	 * @param format
	 * @return
	 */

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
