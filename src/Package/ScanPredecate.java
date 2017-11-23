package Package;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScanPredecate {


		/**
		 * https://howtodoinjava.com/java-8/how-to-use-predicate-in-java-8/
		 * @param Id
		 * @return
		 */
	public static ArrayList <Scan> filters (ArrayList<Scan> arrayOfScan, Predicate <Scan> pre){
		return (ArrayList<Scan>) arrayOfScan.stream().filter(pre).collect(Collectors.<Scan>toList());
	}
		/**
		 * this method filter the data by id
		 * @param Id
		 * @return
		 */
	public static Predicate <Scan> SelectById(String id){
		return p -> p.getId().equals(id);
	}
	/**
	 * filter by radus and point
	 * @param rad
	 * @param cord
	 * @return
	 */
	public static Predicate<Scan> SelectByPlace (double rad,Cordinate cord) {
		return p-> cord.distance(cord, p.getCore())<= rad;
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
	public static Predicate <Scan> SelectByTime(Date minTime, Date maxTime){
		return p ->stringToDate(p.getTime()).after(minTime) && stringToDate(p.getTime()).before(maxTime);
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
				
				return date;
			} catch (ParseException e) {
			}
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/mm/yyyy hh:mm");
			try {
				date = sdf2.parse(time);
				
				return date;

			} catch (ParseException e) {
				e.printStackTrace();
			}
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
			try {
				date = sdf3.parse(time);
				
				return date;

			} catch (ParseException e) {
				e.printStackTrace();
			}

			SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy/mm/dd hh:mm");
			try {
				date = sdf4.parse(time);
				
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
	}


