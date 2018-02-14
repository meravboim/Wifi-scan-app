package Filter;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.micromata.opengis.kml.v_2_2_0.Data;
import object.Database;
import object.Scan;

public class Makefilter {
	private Filters f;
	public Makefilter(Filters f) {
		this.f=f;
	}
	/* * https://howtodoinjava.com/java-8/how-to-use-predicate-in-java-8/
	 * @param Id
	 * @return
	 */
//	public static ArrayList <Scan> filters (ArrayList<Scan> arrayOfScan, Predicate <Scan> pre){
//		return (ArrayList<Scan>) arrayOfScan.stream().filter(pre).collect(Collectors.<Scan>toList());
//	}


	public Set<Scan> filtering( ArrayList<Scan> data) {
		return data.stream().filter(scan ->this.f.comper(scan)).collect(Collectors.<Scan>toSet());
	}
	
}
