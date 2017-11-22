import java.util.Arrays;

//   אובייקט שמכיל נתונים עבור כל סריקה (שורה בקובץ החדש )
public class Scan {
	private String Time;
	private String ID;
	private String Lat;
	private String Lon;
	private String Alt;
	private String WifiNetwork;
	private WifiData[] Wifi;

	public String toString() {
		return "Scan [Time=" + Time + ", ID=" + ID + ", Lat=" + Lat + ", Lon=" + Lon + ", Alt=" + Alt + ", WifiNetwork="
				+ WifiNetwork + ", Wifi=" + Arrays.toString(Wifi) + "]";
	}

	public Scan(String time, String iD, String lat, String lon, String alt, String wifiNetwork, WifiData[] wifi) {
		Time = time;
		ID = iD;
		Lat = lat;
		Lon = lon;
		Alt = alt;
		WifiNetwork = wifiNetwork;
		Wifi = wifi;
	}

	public Scan() {
		this.Time = null;
		this.ID = null;
		this.Alt = null;
		this.Lon = null;
		this.Lat = null;
		this.WifiNetwork = null;
	}

	public Scan(Scan other) {
		this.Time = other.Time;
		this.ID = other.ID;
		this.Lat = other.Lat;
		this.Lon = other.Lon;
		this.Alt = other.Alt;
		this.WifiNetwork = other.WifiNetwork;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getLon() {
		return Lon;
	}

	public void setLon(String lon) {
		Lon = lon;
	}

	public String getAlt() {
		return Alt;
	}

	public void setAlt(String alt) {
		Alt = alt;
	}

	public String getWifiNetwork() {
		return WifiNetwork;
	}

	public void setWifiNetwork(String wifiNetwork) {
		WifiNetwork = wifiNetwork;
	}

	public WifiData[] getWifi() {
		return Wifi;
	}

	public void setWifi(WifiData[] wifi) {
		Wifi = wifi;
	}
}
