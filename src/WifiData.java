//אובייקט שמכיל נתונים עבור כל רשת wifi
public class WifiData implements Comparable<WifiData> {
	private String SSID;
	private String MAC;
	private String Frequncy;
	private String Signal;

	public WifiData() {
		this.SSID = null;
		this.MAC = null;
		this.Frequncy = null;
		this.Signal = null;
	}

	public WifiData(String sSID, String mAC, String frequncy, String signal) {
		this.SSID = sSID;
		this.MAC = mAC;
		this.Frequncy = frequncy;
		this.Signal = signal;
	}

	public WifiData(WifiData other) {
		this.SSID = other.SSID;
		this.MAC = other.MAC;
		this.Frequncy = other.Frequncy;
		this.Signal = other.Signal;
	}

	public int compareTo(WifiData o) {
		return this.Signal.compareTo(o.Signal);
	}

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public String getFrequncy() {
		return Frequncy;
	}

	public void setFrequncy(String frequncy) {
		Frequncy = frequncy;
	}

	public String getSignal() {
		return Signal;
	}

	public void setSignal(String signal) {
		Signal = signal;
	}

	public String toString() {
		return "WifiData [SSID=" + SSID + ", MAC=" + MAC + ", Frequncy=" + Frequncy + ", Signal=" + Signal + "]";
	}

}
