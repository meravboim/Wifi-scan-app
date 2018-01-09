package Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.sound.midi.Synthesizer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import object.AllData;
import object.Cordinate;
import object.Database;
import object.Scan;
import object.WifiData;

import java.text.SimpleDateFormat;

public class FileCsv {
	/**
	 * the class input is :the folder of Wiglewifi files. output: csv file that
	 * contians the wanted information.
	 */
	private final static int ten=10;
	/**
	 * the path is the path for the folder of the wiglewifi files
	 */
	private String path;

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 *  constructor
	 * 
	 * @param path
	 */
	public FileCsv(String path) {
		this.path = path;
	}

	/**
	 * empty constructor
	 */
	public FileCsv() {
		this.path = null;
	}

	/**
	 * copy constructor
	 * 
	 * @param other
	 */
	public FileCsv(FileCsv other) {
		this.path = other.path;
	}

	/**
	 * this method read the Wiglewifi files and enter them to ArrayList of the
	 * object AllData (that contains all the variables that the app measure).
	 * https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
	 * 
	 * @param path
	 * @throws  
	 * @throws IOException
	 */
	public Database readForCsv(String path)   {
		ArrayList<AllData> table = new ArrayList<AllData>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains("csv")) {
				System.out.println("File " + listOfFiles[i].getName() + " was read");
				File f = new File(listOfFiles[i].getPath());
				BufferedReader reader=null;
				try {
					reader = new BufferedReader(new FileReader(listOfFiles[i].getPath()));
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				int lines = 0;
				try {
					while (reader.readLine() != null) {
						lines++;
					}
					reader.close();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("there is a problme with the line: " + lines);
				}
				FileInputStream fi=null;
				try {
					fi = new FileInputStream(f);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Scanner sc = new Scanner(fi);
				String str = sc.nextLine();
				String[] data1 = str.split(",");
				String Id = FindId(data1[4]);
				if (data1[0].contains("WigleWifi") && lines > 2) {
					str = sc.nextLine();
					String[] data2 = str.split(",");
					// sc.nextLine();
					while (sc.hasNext()) {
						try {
							str = sc.nextLine();
							String[] data = str.split(",");
							AllData temp = new AllData(Id, data[0], data[1], data[2], data[3], data[4], data[5],
									data[6], data[7], data[8], data[9], data[10]);
							table.add(temp);
							
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("there is a problem with the line: " + str);
						}
					}
				}
				sc.close();
				try {
					fi.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Database t = new Database();

		t=sotrByScan(table);
		Database data = new Database(t);
		return  data;
	}

	/**
	 * the method cut the word "display=" from the String id
	 * 
	 * @param Id
	 * @return
	 */
	public static String FindId(String Id) {
		String temp = "";
		for (int j = 7; j < Id.length(); j++) {
			temp = temp + Id.charAt(j);
		}
		return temp;
	}

	/**
	 * the input to this method is the the data, that we read from the WiglWifi
	 * files, the method sort by the change of time and location the 10 largest wifi
	 * networks signal
	 * 
	 * @param table
	 * @throws IOException
	 */
	public Database sotrByScan(ArrayList<AllData> table)  {
		Database write = new Database();
		//ArrayList<Scan> write = new ArrayList<Scan>();
		String time = table.get(0).getTime();
		String lon = table.get(0).getLon();
		String lat = table.get(0).getLat();
		int start = 0;
		int end = 0;
		while (end < table.size() - 1) {
			if (table.get(end + 1).getLon().equals(lon) && table.get(end + 1).getTime().equals(time)
					&& table.get(end + 1).getLat().equals(lat)) {
				end++;

			} else {
				SortAndWrite(start, end, table, write);
				start = end + 1;
				end = start;
				time = table.get(start).getTime();
				lon = table.get(start).getLon();
				lat = table.get(start).getLat();
			}

		}
		end = table.size() - 1;
		SortAndWrite(start, end, table, write);
//		try {
//			writecsv(write.getDatabase(), "Table.csv");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return write;
	}

	/**
	 * enter the sort data of one scan to ArrayList of the object Scan
	 * 
	 * @param start
	 * @param end
	 * @param table
	 * @param write
	 */
	public static Database SortAndWrite(int start, int end, ArrayList<AllData> table, Database write) {
		int[] index = IndexOfMaxRSSIWifi(start, end, table);
		if (index[ten] != 0) {
			Scan temp = new Scan();
			Cordinate cord = new Cordinate();
			for (int i = 0; i < index[ten]; i++) {
				ArrayList<WifiData> wifi = WriteWifiData(table, index);
				Collections.sort(wifi,WifiData.getCompBySignal);
				try {
					cord = new Cordinate(Double.parseDouble(table.get(index[0]).getLat()),
							Double.parseDouble(table.get(index[0]).getLon()),
							Double.parseDouble(table.get(index[0]).getAlt()));
					temp = new Scan(table.get(index[0]).getTime(), table.get(index[0]).getId(), cord,
							wifi);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			write.addScan(temp);;
		}
		return write;
	}

	/**
	 * the method enter the data of the wifi network to ArrayList (that ArrayList is
	 * part of the object Scan)
	 * 
	 * @param table
	 * @param index
	 * @return
	 */
	public static ArrayList<WifiData> WriteWifiData(ArrayList<AllData> table, int[] index) {
		ArrayList<WifiData> wifi = new ArrayList<WifiData>();
		for (int i = 0; i < index[ten]; i++) {
			WifiData tempWifi = new WifiData(table.get(index[i]).getSsid(), table.get(index[i]).getMac(),
					ChanneltoFrequncy(table.get(index[i]).getChannel()), table.get(index[i]).getSignal());
			wifi.add(tempWifi);
		}
		return wifi;
	}

	/**
	 * the method convert channel to frequncy
	 * https://stackoverflow.com/questions/5485759/how-to-determine-a-wifi-channel-number-used-by-wifi-ap-network
	 * 
	 * @param channel
	 * @return
	 */
	public static String ChanneltoFrequncy(String channel1) {
		int channel = Integer.parseInt(channel1);
		int channelstr;
		if (channel >= 1 && channel <= 14) {
			channelstr = (channel - 1) * 5 + 2412;
			return channelstr + "";
		} else if (channel >= 36 && channel <= 165) {
			channelstr = (channel - 34) * 5 + 5170;
			return channelstr + "";
		} else {
			return "";
		}
	}

	//
	/**
	 * the method check who is the 10 largest wifi network signal in each scan of
	 * the app.
	 * 
	 * @param start
	 * @param end
	 * @param table
	 * @return
	 */
	public static int[] IndexOfMaxRSSIWifi(int start, int end, ArrayList<AllData> table) {
		int max[] = new int[ten+1];
		// int palceOfRSSI = PlaceOf(table, "RSSI");
		if (end - start + 1 < ten) {
			int counter = 0, j = 0;
			for (int i = start; i <= end; i++) {
				if (table.get(i).getType().equals("WIFI")) {
					max[j] = i;
					j++;
					counter++;
				}
			}
			max[ten] = counter;
			return max;
		} else {
			int min = findmin(start, end, table);
			int Max = min;
			int MaxIndex = start;
			int counter = 0;
			for (int j = 0; j < max.length - 1; j++) {
				for (int i = start + 1; i <= end; i++) {
					if (Integer.parseInt(table.get(i).getSignal()) > Max && !contains(max, i)
							&& table.get(i).getType().equals("WIFI")) {
						MaxIndex = i;
						Max = Integer.parseInt(table.get(i).getSignal());

					}
				}
				max[j] = MaxIndex;
				Max = min;
				counter++;
			}
			max[ten] = counter;
			return max;
		}
	}

	/**
	 * the method check if the Array max contains the index i
	 * 
	 * @param max
	 * @param i
	 * @return
	 */
	public static boolean contains(int[] max, int i) {
		for (int j = 0; j < max.length; j++) {
			if (i == max[j])
				return true;

		}
		return false;

	}

	/**
	 * the method write the data from the ArrayList write to a csv file
	 * 
	 * @param write
	 * @param path
	 * @throws IOException
	 */

	public void writecsv(ArrayList<Scan> write, String path) throws IOException {
		String[] title = new String[46];
		title[0] = "Time";
		title[1] = "ID";
		title[2] = "Lat";
		title[3] = "Lon";
		title[4] = "Alt";
		title[5] = "WiFi Networks";

		int wifinum = 1;
		for (int j = 6; j < 46; j = j + 4) {
			title[j] = "SSID" + wifinum;
			title[j + 1] = "MAC" + wifinum;
			title[j + 2] = "Frequncy" + wifinum;
			title[j + 3] = "Signal" + wifinum;
			wifinum++;

		}

		FileWriter writer = new FileWriter(path);
		PrintWriter outs = new PrintWriter(writer);

		for (int k = 0; k < title.length; k++) {
			writer.append(title[k]);
			writer.append(",");
		}
		outs.println();
		for (int i = 0; i < write.size(); i++) {
			writer.append(write.get(i).getTime() + "," + write.get(i).getId() + "," + ""
					+ write.get(i).getCore().getLat() + "," + "" + write.get(i).getCore().getLon() + "," + ""
					+ write.get(i).getCore().getAlt() + "," + write.get(i).getWifiNetWork() + ",");
			ArrayList<WifiData> temp = write.get(i).getWifi();
			for (int j = 0; j < temp.size(); j++) {
				writer.append(temp.get(j).getSSID() + "," + temp.get(j).getMAC() + "," + temp.get(j).getFrequncy() + ","
						+ temp.get(j).getSignal() + ",");

			}
			outs.println();
		}

		writer.close();
		System.out.println("csv create complete,please chek file.");

	}
	/**
	 * The method find the minimal signal
	 * @param start
	 * @param end
	 * @param table
	 * @return the index of the min Signal
	 */

	public static int findmin(int start, int end, ArrayList<AllData> table) {
		int Min = Integer.parseInt(table.get(start).getSignal());
		for (int i = start + 1; i <= end; i++) {
			if (Integer.parseInt(table.get(i).getSignal()) < Min)
				Min = Integer.parseInt(table.get(i).getSignal());

		}
		return Min;
	}

}
