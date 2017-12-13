import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScanPredecate {
/**
 * the class make the filter by use Predicate
 */

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
		return p ->(stringToDate(p.getTime())).after(minTime) && (stringToDate(p.getTime())).before(maxTime);
	}

	/**
	 * convert String to Date
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String time)  {
		time=time.replace("-", "/");
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

		Date date=new Date();
		date.setYear(Integer.parseInt(year));
		date.setMonth(Integer.parseInt(month));
		date.setDate(Integer.parseInt(day));
		date.setHours(Integer.parseInt(hour));
		date.setMinutes(Integer.parseInt(minute));
		date.setSeconds(Integer.parseInt(second));
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
			time += Date[0] + "/" + Date[1] + "/" + Date[2] + " " + Time[1];
			return time;
		}

		else
			time += Date[2] + "/" + Date[1] + "/" + Date[0] + " " + Time[1];
		return time;
	}
}


