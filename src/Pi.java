import java.util.Comparator;
import java.util.Date;

public class Pi extends Cordinate  {
	private double pi;
	/*
	 * tostring
	 * 
	 * @see java.lang.Object#toString()
	 */

	public Pi( Cordinate core ,double pi) {
		super(core);
		this.pi = pi;
	}

	public Pi() {
		super();
		this.pi = 0;
	}

	public Pi(Pi other) {
		super(other.getLon(), other.getLat(),other.getAlt() );
		this.pi = other.pi;
	
	}

	/* (non-Javadoc)
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


    public static Comparator<Pi> sortbyPi = new Comparator<Pi>() {

	public int compare(Pi s1, Pi s2) {
	   String s1Pi =""+ s1.getPi();
	   String s2Pi = ""+s2.getPi();

	   return s1Pi.compareTo(s2Pi);

    }};

}
