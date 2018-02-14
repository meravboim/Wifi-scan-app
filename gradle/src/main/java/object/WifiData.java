package object;
import java.util.Comparator;

public class WifiData  {
		/**
	 * this object contain data of one wifi network.
	 */
	private String SSID;
	private String MAC;
	private String Frequncy;
	private String Signal;

	/**
	 * empty constructor
	 */
	public WifiData() {
		this.SSID = null;
		this.MAC = null;
		this.Frequncy = null;
		this.Signal = null;
	}
/**
 * constructor
 * @param sSID
 * @param mAC
 * @param frequncy
 * @param signal
 */
	public WifiData(String sSID, String mAC, String frequncy, String signal) {
		this.SSID = sSID;
		this.MAC = mAC;
		this.Frequncy = frequncy;
		this.Signal = signal;
	}
/**
 * copy constructor
 * @param other
 */
	public WifiData(WifiData other) {
		this.SSID = other.SSID;
		this.MAC = other.MAC;
		this.Frequncy = other.Frequncy;
		this.Signal = other.Signal;
	}
	/**
	 * @return the sSID
	 */
	public String getSSID() {
		return SSID;
	}
	/**
	 * @param sSID the sSID to set
	 */
	public void setSSID(String sSID) {
		SSID = sSID;
	}
	/**
	 * @return the mAC
	 */
	public String getMAC() {
		return MAC;
	}
	/**
	 * @param mAC the mAC to set
	 */
	public void setMAC(String mAC) {
		MAC = mAC;
	}
	/**
	 * @return the frequncy
	 */
	public String getFrequncy() {
		return Frequncy;
	}
	/**
	 * @param frequncy the frequncy to set
	 */
	public void setFrequncy(String frequncy) {
		Frequncy = frequncy;
	}
	/**
	 * @return the signal
	 */
	public String getSignal() {
		return Signal;
	}
	/**
	 * @param signal the signal to set
	 */
	public void setSignal(String signal) {
		Signal = signal;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WifiData [SSID=" + SSID + ", MAC=" + MAC + ", Frequncy=" + Frequncy + ", Signal=" + Signal + "]";
	}
	
    public static Comparator<WifiData> getCompBySignal = new Comparator<WifiData>() {
	public int compare(WifiData o1, WifiData o2) {
	   return o1.getSignal().compareTo(o2.getSignal());

    }};


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Frequncy == null) ? 0 : Frequncy.hashCode());
		result = prime * result + ((MAC == null) ? 0 : MAC.hashCode());
		result = prime * result + ((SSID == null) ? 0 : SSID.hashCode());
		result = prime * result + ((Signal == null) ? 0 : Signal.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WifiData other = (WifiData) obj;
		if (Frequncy == null) {
			if (other.Frequncy != null)
				return false;
		} else if (!Frequncy.equals(other.Frequncy))
			return false;
		if (MAC == null) {
			if (other.MAC != null)
				return false;
		} else if (!MAC.equals(other.MAC))
			return false;
		if (SSID == null) {
			if (other.SSID != null)
				return false;
		} else if (!SSID.equals(other.SSID))
			return false;
		if (Signal == null) {
			if (other.Signal != null)
				return false;
		} else if (!Signal.equals(other.Signal))
			return false;
		return true;
	}


}
