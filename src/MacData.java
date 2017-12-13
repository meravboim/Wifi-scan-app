import java.util.Comparator;
import java.util.Date;

public class MacData  extends WifiData { //implements Comparator<MacData> {
	private Date time;
	private Cordinate core;
	/**
	 * 
	 * @param signal
	 * @param mac
	 */
	public MacData (WifiData wifi,Cordinate core,String time) {
		super(wifi);
		this.time=stringToDate(time);
		this.core=core;
	}
	



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MacData [time=" + time + ", core=" + core + ", toString()=" + super.toString() + "]";
	}




	public MacData () {
		super();
		this.time=null;
		this.core=null;
	}
	
	public MacData (MacData other) {
		super();
		this.time=other.time;
	}
	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = stringToDate(time);
	}
	/**
	 * @return the core
	 */
	public Cordinate getCore() {
		return core;
	}
	/**
	 * @param core the core to set
	 */
	public void setCore(Cordinate core) {
		this.core = core;
	}
	
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
