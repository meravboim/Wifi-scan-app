package Filter;

import object.Scan;

public class Operator implements Filters {
	
	@Override
	public String toString() {
		return "Operator [operator=" + operator + "]";
	}

	private String  operator;

	public  Operator (String op) {
		// TODO Auto-generated constructor stub
		this.operator=op;
	}
	
	public boolean equal (Operator o){
		if(o.getOperator().equals(this.operator))
			return true;
		else 
			return false;
	}
	
	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	@Override
	public boolean comper(Scan scan) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
