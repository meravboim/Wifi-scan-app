package Filter;

import object.Scan;

public class NotFilter implements Filters {
	private Filters filter;

	public NotFilter() {
		this.filter=null;
	}
	public NotFilter(Filters filter) {
		this.filter=filter;
	}
	public boolean comper(Scan scan) {
		return !(this.filter.comper(scan));
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NotFilter [filter=" + filter + "]";
	}
	
}
