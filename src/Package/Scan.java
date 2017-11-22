package Package;

import java.util.ArrayList;

public class Scan {
	private String time;
	private String id;
	private Cordinate core;
	private String wifiNetWork;
    private ArrayList<WifiData> wifi= new ArrayList<WifiData>();

	public Scan(String time, String id, Cordinate core, String wifiNetWork, ArrayList<WifiData> wifi) {
		this.time = time;
		this.id = id;
		this.core = core;
		this.wifiNetWork = wifiNetWork;
		this.wifi.addAll(wifi);
	}

	public Scan(Scan other) {
		this.core = other.core;
		this.time = other.time;
		this.id = other.id;
		this.wifiNetWork = other.wifiNetWork;
		this.wifi.addAll(wifi);
	}

	public Scan() {
		super();
		this.time = null;
		this.id = null;
		this.wifiNetWork = null;
		this.wifi.clear();
		;
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
	public String getWifiNetWork() {
		return wifiNetWork;
	}

	/**
	 * @param wifiNetWork
	 *            the wifiNetWork to set
	 */
	public void setWifiNetWork(String wifiNetWork) {
		this.wifiNetWork = wifiNetWork;
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

}