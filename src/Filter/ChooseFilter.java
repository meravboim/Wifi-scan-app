package Filter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

import Files.FileKml;
import object.Cordinate;
import object.Database;
import object.Scan;



public class ChooseFilter extends FileKml {
	/*
	 * this class contains methods that filter the data from the csv table for
	 * create kml file
	 */

	/**
	 * the method give the user to decide how to filter the data
	 * (by Time range, or by Coordinate and  radius, or by Id )
	 * @param data
	 * @throws IOException
	 */
	public static Database ChekFilterForKml(Database data)  {
		FileKml fe = new FileKml();
		//ScanPredecate pe= new ScanPredecate();
		Filters pe ;
		Makefilter fil;
		ChooseFilter t = new ChooseFilter();
		System.out.println("Enter 1 to select by time, 2 to select by place or 3 to select by id");
		Scanner sc = new Scanner(System.in);
		int select = sc.nextInt();
		if (select == 1) {
			int legalinput = 0;
			System.out.println("Enter MinDate yyyy/mm/dd");
			String MinTime = sc.next();
			while (legalinput == 0) {
				if (checkinput(MinTime,"yyyy/mm/dd"))
					legalinput = 1;
				else {
					System.out.println("The format is wrong Enter MinDate yyyy/mm/dd");
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
			System.out.println("Enter MaxDate yyyy/mm/dd");
			String MaxTime = sc.next();
			while (legalinput == 0) {
				if (checkinput(MaxTime, "yyyy/mm/dd"))
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
			min = stringToDate(MinTime+" "+MinClock);
			max = stringToDate(MaxTime+" "+MaxClock);
			System.out.println(min.toString());
			pe= new FilterByTime(min,max);
			fil= new Makefilter(pe);
			ArrayList<Scan> temp = new ArrayList<Scan>(); 
			temp.addAll(fil.filtering(data.getDatabase() ));
			fe.TurnToKML(oneMac(fil.filtering(data.getDatabase() )  ), "KmlByTimeWithTimeLine.kml");
			data.setDatabase(temp);

		}
		if (select == 2) {
			System.out.println("Enter Radus, CenterLat and CenterLon");
			double radus = sc.nextDouble();
			double centerLon = sc.nextDouble();
			double centerLat = sc.nextDouble();
			Cordinate cord=new Cordinate();
			cord.setLon(centerLon);
			cord.setLat(centerLat);
			pe= new FilterByPlace(cord,radus);
			fil= new Makefilter(pe);
			ArrayList<Scan> temp = new ArrayList<Scan>(); 
			fe.TurnToKML(oneMac(fil.filtering(data.getDatabase() )  ), "KmlByPlaceWithTimeLine.kml");
			temp.addAll(fil.filtering(data.getDatabase()));
			data.setDatabase(temp);


		}
		if (select == 3) {
			System.out.println(data.getDatabase().size());
			System.out.println("Enter Id");
			String id = sc.next();
			pe= new FilterByID(id);
			fil= new Makefilter(pe);
			ArrayList<Scan> temp = new ArrayList<Scan>(); 
			temp.addAll(fil.filtering(data.getDatabase() ));
			fe.TurnToKML(oneMac(fil.filtering(data.getDatabase() )  ), "KmlByIdWithTimeLine.kml");
			data.setDatabase(temp);
			System.out.println(data.getDatabase().size());
		}

		sc.close();
		return data;
	}

/**
 *  the method convert String to Date Object
 * @param time
 * @return
 */
	@SuppressWarnings("deprecation")
	public static Date stringToDate(String time)  {
		time = CheckTime(time);
		time=time.replace("-", "/");
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

		Date date=new Date();
		date.setYear(Integer.parseInt(year));
		date.setMonth(Integer.parseInt(month));
		date.setDate(Integer.parseInt(day));
		date.setHours(Integer.parseInt(hour));
		date.setMinutes(Integer.parseInt(minute));
		date.setSeconds(Integer.parseInt(second));
		System.out.println(date.toString());
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
		System.out.println(Time.length);
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
		if (format.equals("yyyy/mm/dd")) {
			if ((input.length() == 10) && (input.charAt(4) == '/') && (input.charAt(7) == '/'))
				return true;
		}

		if (format.equals("hh:mm:ss")) {
			if ((input.length() == 8) && (input.charAt(2) == ':') && (input.charAt(5) == ':'))
				return true;
		}
		return false;
	}
	/**
	 * the complexity of the function is O(n^2).
	 * the method delete duplication of mac address, after the filter

	 * @param filter
	 * @return
	 */
	public static ArrayList <Scan> oneMac (Set <Scan> filter1){
		ArrayList<Scan> filter = new ArrayList<Scan>();
		filter.addAll(filter1);
		for (int i = 0; i < filter.size(); i++) {
			for (int j = 0; j < filter.get(i).getWifi().size(); j++) {
				String mac =filter.get(i).getWifi().get(j).getMAC();
				int max=Integer.parseInt(filter.get(i).getWifi().get(j).getSignal());
				for (int k = i+1; k < filter.size()-2; k++) {
					for (int k2 = 0; k2 < filter.get(k).getWifi().size(); k2++) {
						if(filter.get(k).getWifi().get(k2).getMAC().equals(mac)) {
							if(Integer.parseInt(filter.get(i).getWifi().get(j).getSignal())<
									Integer.parseInt(filter.get(k).getWifi().get(k2).getSignal())){
								if(filter.get(i).getWifi().size()>1) {
									filter.get(i).getWifi().remove(j);
									if(j!=0)
										j--;
								}
							}
							else {
								if(filter.get(k).getWifi().size()>1) {

									filter.get(k).getWifi().remove(k2);
									if(k2!=0)
										k2--;
								}
							}
						}
					}
				}

			}
		}
		return filter;
	}
}










