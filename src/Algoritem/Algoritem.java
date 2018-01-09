package Algoritem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import object.*;
import Files.*;


public class Algoritem {
/**
 * this class contains to algoritim for calculate approximate location,
 *  algo 1 - calculate approximate location of one macs.
 *  algo 2- calculate approximate location of one Scan.
 *
 */
	private final static int num = 4;
	private final static double no_signal = -120.0;

/**
 * that method turn ArrayList of Scan, Are DataBase, to hash for algo1
 * @param scan
 * @return
 */
	public Map<String, ArrayList<MacData>> algo1Hash(ArrayList<Scan> scan) {
		List<MacData> macs = new ArrayList<MacData>();
		// enter all the data to object the separate mac.
		for (int i = 0; i < scan.size(); i++) {
			for (int j = 0; j < scan.get(i).getWifi().size(); j++) {
				MacData temp = new MacData(scan.get(i).getWifi().get(j), scan.get(i).getCore(), scan.get(i).getTime());
				macs.add(temp);
			}
		}
		Map<String, ArrayList<MacData>> find = new HashMap<String, ArrayList<MacData>>();
		for (int i = 0; i < macs.size(); i++) {
			if (find.containsKey(macs.get(i).getMAC())) {
				find.get(macs.get(i).getMAC()).add(macs.get(i));
			} else {
				ArrayList<MacData> temp = new ArrayList<MacData>();
				temp.add(macs.get(i));
				find.put(macs.get(i).getMAC(), temp);
			}
		}
		return find;
	}
	
	
/**
 *  the method send one mac every time to the "algo1" and enter the cordinate to the object MacData, and finnly 
 * @param scan
 */
	public void algo1tocsv(ArrayList<Scan> scan) {
		Map<String, ArrayList<MacData>> find = algo1Hash(scan);
		ArrayList<MacData> write = new ArrayList<MacData>();
		ArrayList<String> mac = new ArrayList<String>();
		Cordinate temp = new Cordinate();
		for (int i = 0; i < scan.size(); i++) {
			for (int j = 0; j < scan.get(i).getWifi().size(); j++) {
				if (!mac.contains(scan.get(i).getWifi().get(j).getMAC())) {
					mac.add(scan.get(i).getWifi().get(j).getMAC());
					temp = algo1(find, scan.get(i).getWifi().get(j).getMAC());
					MacData temp1 = new MacData(scan.get(i).getWifi().get(j), temp, scan.get(i).getTime());
					write.add(temp1);
				}
			}

		}
		writetocsv(write, "resutlt_algo1.csv");
	}

	/**
	 * that function find the the approximation coordination to one mac, 
	 * (the method get from the HashMap all the similar macs and find the approximation coordination)
	 * @param find
	 * @param mac
	 * @returnemac.add(one.get(i));
		}
		Cordinate cord = Calculate.cor1(onemac);
		return cord;
	}**/
	
	public Cordinate algo1(Map<String, ArrayList<MacData>> find, String mac) {
		ArrayList<MacData> one = find.get(mac);
		Collections.sort(one, MacData.getCompBySignal);
		ArrayList<MacData> onemac = new ArrayList<MacData>();
		for (int i = one.size() - num; i < one.size(); i++) {
			if (i >= 0)
				onemac.add(one.get(i));
		}
		Cordinate cord = Calculate.cor1(onemac);
		return cord;
	}

	/**
	 * that method write ArrayList of MacData to csv file.
	 * @param write
	 * @param path
	 */
	
	public void writetocsv(ArrayList<MacData> write, String path) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(path);
			PrintWriter outs = new PrintWriter(writer);
			for (int i = 0; i < write.size(); i++) {
				writer.append(i + "," + write.get(i).getMAC() + "," + write.get(i).getSSID() + ","
						+ write.get(i).getFrequncy() + "," + write.get(i).getSignal() + ","
						+ write.get(i).getCore().getLat() + "," + write.get(i).getCore().getLon() + ","
						+ write.get(i).getCore().getAlt() + "," + write.get(i).getTime());
				outs.println();
			}
			writer.close();
			System.out.println("csv for algo1 create complete,please chek file.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public Cordinate algo2fromUser (Database d, String mac1, String mac2, String mac3, String signal_1, String signal_2, String signal_3) {
		Set<WifiData> set =  new HashSet<WifiData>();
		WifiData data1 = new WifiData(" ", mac1, " ",signal_1);
		WifiData data2 = new WifiData (" ",mac2, " ", signal_2);
		WifiData data3 = new WifiData(" ",mac3, " ",signal_3);
		if(mac1!=null&&!mac1.isEmpty()&&signal_1!=null&&!signal_1.isEmpty())
		set.add(data1);
		if(mac2!=null&&!mac2.isEmpty()&&signal_2!=null&&!signal_2.isEmpty())
		set.add(data2);
		if(mac3!=null&&!mac3.isEmpty()&&signal_3!=null&&!signal_3.isEmpty())
		set.add(data3);
		ArrayList<WifiData> wifi = new ArrayList<WifiData>();
		wifi.addAll(set);
		Cordinate cor = new Cordinate();
		Scan data = new Scan (" ", " ",cor,wifi);
		cor = algo2(d.getHash_map(),data);
		return cor;
	}

	/**
	 *  the method get data and ArrayList of Scan with missing details, it complete them and write all to csv file.
	 * @param scan
	 * @param sample
	 */

	public void algo2tocsv(Database scan, ArrayList<Scan> sample) {
		Map<String, ArrayList<Scan>> find =scan.getHash_map();
		Cordinate cor = new Cordinate();
		for (int i = 0; i < sample.size(); i++) {
			cor = algo2(find, sample.get(i));
			sample.get(i).setCore(cor);
		}
		FileCsv fe = new FileCsv();
		try {
			fe.writecsv(sample, "resutlt_algo2.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * the method return the Approximate cordinate for each Scan.
 * @param find
 * @param sample
 * @return
 */
	
	public Cordinate algo2(Map<String, ArrayList<Scan>> find, Scan sample) {
		ArrayList<Scan> macs = new ArrayList<Scan>();
		for (int j = 0; j < sample.getWifi().size(); j++) {
			if (find.containsKey(sample.getWifi().get(j).getMAC())) {
				ArrayList<Scan> temp = find.get(sample.getWifi().get(j).getMAC());
				macs.addAll(temp);
			}
		}

		macs = douplicate(macs);
		ArrayList<Pi> data = matrix(macs, sample);
		//Comparator<Pi> t = 
		Collections.sort(data,Pi.sortbyPi);
		ArrayList<Pi> fordata = new ArrayList<Pi>();
		if (data.size() > num)
			for (int j = data.size() - num; j < data.size(); j++) {
				fordata.add(data.get(j));
			}
		else
			for (int j = 0; j < data.size(); j++) {
				fordata.add(data.get(j));
			}
		Cordinate rt = Calculate.cor2(fordata);
		return rt;
	}
/**
 * that method delete douplication.
 * @param macs
 * @return
 */
	public ArrayList<Scan> douplicate(ArrayList<Scan> macs) {
		ArrayList<Scan> temp = new ArrayList<Scan>();
		for (int i = 0; i < macs.size(); i++) {
			if (!temp.contains(macs.get(i)))
				temp.add(macs.get(i));

		}
		return temp;
	}
/**
 * that method find the pi for every Scan and return ArrayList of pi. 
 * @param macs
 * @param sample
 * @return
 */
	public static ArrayList<Pi> matrix(ArrayList<Scan> macs, Scan sample) {
		String[][] approximation = new String[2][sample.getWifi().size()];
		ArrayList<Pi> data = new ArrayList<Pi>();
		for (int i = 0; i < sample.getWifi().size(); i++) {
			approximation[0][i] = sample.getWifi().get(i).getMAC();
			approximation[1][i] = "" + sample.getWifi().get(i).getSignal();
		}

		for (int i = 0; i < macs.size(); i++) {
			double pi = 1;
			for (int k = 0; k < sample.getWifi().size(); k++) {
				String signal = "" + no_signal;
				for (int j = 0; j < macs.get(i).getWifi().size(); j++) {
					if (macs.get(i).getWifi().get(j).getMAC().equals(approximation[0][k]))

						signal = "" + macs.get(i).getWifi().get(j).getSignal();
				}
				double w = Calculate.findw(signal, approximation[1][k]);
				pi = pi * w;

			}
			Pi temp = new Pi(macs.get(i).getCore(), pi);
			data.add(temp);
		}

		return data;

	}

}