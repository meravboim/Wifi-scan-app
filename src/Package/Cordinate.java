package Package;

public class Cordinate {
	private double lon;
	private double lat;
	private double alt;

	public Cordinate() {
		this.lon = 0;
		this.lat = 0;
		this.alt = 0;
	}

	public Cordinate(double lon, double lat, double alt) {
		this.lon = lon;
		this.lat = lat;
		this.alt = alt;
	}

	public Cordinate(Cordinate other) {
		this.alt = other.alt;
		this.lat = other.lat;
		this.lon = other.lon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cordinate [lon=" + lon + ", lat=" + lat + ", alt=" + alt + "]";
	}

	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * @param lon
	 *            the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return the alt
	 */
	public double getAlt() {
		return alt;
	}

	/**
	 * @param alt
	 *            the alt to set
	 */
	public void setAlt(double alt) {
		this.alt = alt;
	}
public boolean equal (Cordinate a, Cordinate b) {
	if(a.getAlt()==b.getAlt() &&a.getLat()==b.getLat() && a.getLon()==b.getLon()) 
		return true;
	else return false;
	
}

}
