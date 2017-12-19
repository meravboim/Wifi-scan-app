package object;

public class Cordinate  {
	private double lat;
	private double lon;
	private double alt;

	public Cordinate() {
		this.lat = -1;
		this.lon =-1;
		this.alt = -1;
	}


	public Cordinate(double lat, double lon, double alt) {
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
	}

	public Cordinate(Cordinate other) {
		this.alt = other.alt;
		this.lat = other.lat;
		this.lon = other.lon;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cordinate [lat=" + lat + ", lon=" + lon + ", alt=" + alt + "]";
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
public  double distance(Cordinate x, Cordinate y) {
	double lon = Math.pow( x.getLon()- y.getLon(), 2);
	double lat = Math.pow(x.getLat()-y.getLat(), 2);
	double dis = Math.sqrt(lon+lat);
	return dis;
}

}
