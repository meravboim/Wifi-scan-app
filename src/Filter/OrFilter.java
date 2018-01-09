package Filter;

import object.Scan;

public class OrFilter implements Filters {

	private Filters filter_1;
	private Filters filter_2;
	
	public OrFilter() {
		this.filter_1=null;
		this.filter_2=null;
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrFilter [ " + filter_1 + " OR  "+ filter_2 + "]";
	}
	public OrFilter(Filters f1 , Filters  f2) {
		this.filter_1=f1;
		this.filter_2=f2;
	}
	
	public boolean comper(Scan scan) {
		return this.filter_1.comper(scan) || this.filter_2.comper(scan);
	}

}
