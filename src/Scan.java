import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Scan {//implements Comparator<Scan> {
	private String time;
	private String id;
	private Cordinate core;
	private int wifiNetWork;
	private ArrayList<WifiData> wifi= new ArrayList<WifiData>();

	public Scan(String time, String id, Cordinate core, ArrayList<WifiData> wifi) {
		this.time = time;
		this.id = id;
		this.core = core;
		this.wifi.addAll(wifi);
		this.wifiNetWork =wifi.size() ;
	}

	public Scan(Scan other) {
		this.core = other.core;
		this.time = other.time;
		this.id = other.id;
		this.wifiNetWork = other.wifiNetWork;
		this.wifi.addAll(wifi);
	}

	public Scan() {
		this.core= null;		
		this.time = null;
		this.id = null;
		this.wifiNetWork = 0;
		this.wifi.clear();

	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return the core
	 */
	public Cordinate getCore() {
		return core;
	}

	/**
	 * @param core
	 *            the core to set
	 */
	public void setCore(Cordinate core) {
		this.core = core;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the wifiNetWork
	 */
	public int getWifiNetWork() {
		return wifiNetWork;
	}

	/**
	 * @return the wifi
	 */
	public ArrayList<WifiData> getWifi() {
		return wifi;
	}

	/**
	 * @param wifi
	 *            the wifi to set
	 */
	public void setWifi(ArrayList<WifiData> wifi) {
		this.wifi.clear();
		this.wifi.addAll(wifi);
	}

	/*
	 * toString
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Scan [time=" + time + ", id=" + id + ", core=" + core + ", wifiNetWork=" + wifiNetWork + ", wifi="
				+ wifi + "]";
	}

	public static Comparator<Scan> getCompByTime = new Comparator<Scan>() {

		public int compare(Scan a, Scan b) {
			Date a1= stringToDate(a.getTime());
			Date b1= stringToDate(b.getTime());
			if(	a1.before(b1))	  
				return 1;
			else
				return 0;

		}};

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
		public int equals (Scan other) {
			if(this.getTime().equals(other.getTime()) && this.getId().equals(other.getId()) && this.core.equal(this.core, other.core)
					&& this.getWifiNetWork() == other.getWifiNetWork() && this.wifi.equals(other.wifi))
				return 1;
			else return 0;

		}


}



