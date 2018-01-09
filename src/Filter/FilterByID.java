package Filter;

import java.util.function.Predicate;

import object.Scan;

public class FilterByID implements Filters {
	private String id;
	
	public FilterByID(String id) {
		this.id=id;
	}
	

	public boolean comper(Scan scan) {
		return scan.getId().equals(this.id);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FilterByID [id=" + id + "]";
	}

	
}
