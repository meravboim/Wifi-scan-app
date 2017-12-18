import java.util.Comparator;
import java.util.Date;

public class Pi extends Cordinate  {
	/**
	 * this class contians Cordinate and double pi, we will this Object when we need to sort and take the 
	 * bigges pi to send them to the Weighted average. 
	 */
	private double pi;
/**
 *  Constractor
 * @param core
 * @param pi
 */
	public Pi( Cordinate core ,double pi) {
		super(core);
		this.pi = pi;
	}
/**
 * empty Constractor
 */
	public Pi() {
		super();
		this.pi = 0;
	}
/**
 * copy Constractor 
 * @param other
 */
	public Pi(Pi other) {
		super(other.getLon(), other.getLat(),other.getAlt() );
		this.pi = other.pi;
	
	}

	/* toString
	 * 
	 */
	@Override
	public String toString() {
		return "Pi [pi=" + pi + ", toString()=" + super.toString() + "]";
	}

	public double getPi() {
		return pi;
	}

	/**
	 * @param pi
	 *            the pi to set
	 */
	public void setPi(double pi) {
		this.pi = pi;
	}

/**
 * Comparator, we will use the Comprator when we want to sort the ArrayList Pi, because we want the bigges Pi. 
 */
    public static Comparator<Pi> sortbyPi = new Comparator<Pi>() {

	public int compare(Pi s1, Pi s2) {
	   String s1Pi =""+ s1.getPi();
	   String s2Pi = ""+s2.getPi();

	   return s1Pi.compareTo(s2Pi);

    }};

}
