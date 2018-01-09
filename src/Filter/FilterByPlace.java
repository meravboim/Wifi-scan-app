package Filter;

import java.util.function.Predicate;

import object.Cordinate;
import object.Scan;

public class FilterByPlace implements Filters {

	private Cordinate cord;
	private double radius;
	
	public FilterByPlace() {
		this.cord=null;
		this.radius=-1;
	}
	public FilterByPlace(Cordinate cord,double radius ) {
		this.cord=cord;
		this.radius=radius;
	}
	public boolean comper(Scan scan) {
 		return  cord.distance(this.cord, scan.getCore())<=this.radius;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FilterByPlace [cord=" + cord.getLat()+","+cord.getLon() + ", radius=" + radius + "]";
	}
	
}
