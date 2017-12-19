package object;
//אובייקט שמכיל כל שורה בקובץ שמתקבל מהאפליקציה
public class AllData {
	/**
	 * the object contains all the Type of information we received from the app
	 */
	private String  id;
	private String mac;
	private String ssid;
	private String mode;
	private String time;
	private String channel;
	private String signal;
	private String lat;
	private String lon;
	private String alt;
	private String accuracyMeters;
	private String type;

	/**
	 * constructor
	 * @param id
	 * @param mac
	 * @param ssid
	 * @param mode
	 * @param time
	 * @param channel
	 * @param signal
	 * @param lat
	 * @param lon
	 * @param alt
	 * @param accuracyMeters
	 * @param type
	 */
	public AllData(String id, String mac, String ssid, String mode, String time, String channel, String signal,
			String lat, String lon, String alt, String accuracyMeters, String type) {
		this.id = id;
		this.mac = mac;
		this.ssid = ssid;
		this.mode = mode;
		this.time = time;
		this.channel = channel;
		this.signal = signal;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.accuracyMeters = accuracyMeters;
		this.type = type;
	}
/**
 * empty constructor
 */
	public AllData() {
		this.id = null;
		this.mac = null;
		this.ssid = null;
		this.mode = null;
		this.time = null;
		this.channel = null;
		this.signal = null;
		this.lat = null;
		this.lon = null;
		this.alt = null;
		this.accuracyMeters = null;
		this.type = null;
	}
/**
 * copy constructor
 * @param other
 */
	public AllData(AllData other) {
		this.id = other.id;
		this.mac = other.mac;
		this.ssid = other.ssid;
		this.mode = other.mode;
		this.time = other.time;
		this.channel = other.channel;
		this.signal = other.signal;
		this.lat = other.lat;
		this.lon = other.lon;
		this.alt = other.alt;
		this.accuracyMeters = other.accuracyMeters;
		this.type = other.type;

	}
/**
 * toString
 */
	public String toString() {
		return "AllData [mac=" + mac + ", ssid=" + ssid + ", mode=" + mode + ", time=" + time + ", channel=" + channel
				+ ", signal=" + signal + ", lat=" + lat + ", lon=" + lon + ", alt=" + alt + ", accuracyMeters="
				+ accuracyMeters + ", type=" + type + "]";
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
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @param mac
	 *            the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * @return the ssid
	 */
	public String getSsid() {
		return ssid;
	}

	/**
	 * @param ssid
	 *            the ssid to set
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the signal
	 */
	public String getSignal() {
		return signal;
	}

	/**
	 * @param signal
	 *            the signal to set
	 */
	public void setSignal(String signal) {
		this.signal = signal;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the lon
	 */
	public String getLon() {
		return lon;
	}

	/**
	 * @param lon
	 *            the lon to set
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}

	/**
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt
	 *            the alt to set
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * @return the accuracyMeters
	 */
	public String getAccuracyMeters() {
		return accuracyMeters;
	}

	/**
	 * @param accuracyMeters
	 *            the accuracyMeters to set
	 */
	public void setAccuracyMeters(String accuracyMeters) {
		this.accuracyMeters = accuracyMeters;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
