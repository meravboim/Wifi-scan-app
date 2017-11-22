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
import java.util.Scanner;

import javax.sound.midi.Synthesizer;

public class FileCsv {
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

	public FileCsv(String path) {
		this.path = path;
	}

	public FileCsv() {
		this.path = null;
	}

	public FileCsv(FileCsv other) {
		this.path = other.path;
	}

	// this function read the Wiglewifi files and enter them to ArrayList of the object AllData
	//(that contains all the variables that the app measure)
	public void readForCsv(String path) throws IOException {
		ArrayList<AllData> table = new ArrayList<AllData>();
		// import source
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains("csv")) {
				System.out.println("File " + listOfFiles[i].getName() + " was read");
				File f = new File(listOfFiles[i].getPath());
				BufferedReader reader = new BufferedReader(new FileReader(listOfFiles[i].getPath()));
				int lines = 0;
				try {
				while (reader.readLine() != null) {
					lines++;
				}
				reader.close();
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("there is a problme with the line: "+ lines);
				}
				FileInputStream fi = new FileInputStream(f);
				Scanner sc = new Scanner(fi);
				String str = sc.nextLine();
				String[] data1 = str.split(",");
				String Id = FindId(data1[5]);
				if (data1[0].contains("WigleWifi") && lines > 2) {
					str = sc.nextLine();
					String[] data2 = str.split(",");
					// sc.nextLine();
					while (sc.hasNext()) {
						try {
						 str = sc.nextLine();
						String[] data = str.split(",");
						AllData temp = new AllData(Id, data[0], data[1], data[2], data[3], data[4], data[5], data[6],
								data[7], data[8], data[9], data[10]);
						table.add(temp);
						}
						catch (Exception e) {
							// TODO: handle exception
							System.out.println("there is a problem with the line: "+str);
						}
					}

				}
				sc.close();
				fi.close();

			}
		}
		sotrByScan(table);

	}
	
	public static String FindId(String Id) {
		String temp = "";
		for (int j = 8; j < Id.length(); j++) {
			temp = temp + Id.charAt(j);
		}
System.out.println(temp);
		return temp;
	}
	// the input to this fouction is the the data, that we read from the WiglWifi files,
	//the fouction sort by the change of time and loction the 10 larget wifi networks signal 
	public void sotrByScan(ArrayList<AllData> table) throws IOException {
		ArrayList<Scan> write = new ArrayList<Scan>();
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
				// System.out.println("start = "+start +" end = "+end);
				// קריאת לפונקציה שמסדרת את הנתונים לפי ההתחלה והסיום של אותו הזמן ומכניסה אותם
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
		System.out.println(write.size());
		writecsv(write, "hadar.csv");
	}
	// enter the sort data to ArrayList of the object Scan
	public static void SortAndWrite(int start, int end, ArrayList<AllData> table, ArrayList<Scan> write) {
		int[] index = IndexOfMaxRSSIWifi(start, end, table);
		if (index[10] != 0) {
			Scan temp = new Scan();
			for (int i = 0; i < index[10]; i++) {
				WifiData wifi[] = WriteWifiData(table, index);
				temp = new Scan(table.get(index[0]).getTime(), table.get(index[0]).getId(),
						table.get(index[0]).getLat(), table.get(index[0]).getLon(), table.get(index[0]).getAlt(),
						"" + index[10], wifi);

			}

			write.add(temp);
		}

	}

	public static WifiData[] WriteWifiData(ArrayList<AllData> table, int[] index) {
		WifiData wifi[] = new WifiData[index[10]];
		for (int i = 0; i < index[10]; i++) {
			wifi[i] = new WifiData(table.get(index[i]).getSsid(), table.get(index[i]).getMac(),
					ChanneltoFrequncy(table.get(index[i]).getChannel()), table.get(index[i]).getSignal());
		}
		return wifi;
	}
	// convert channel to frequncy
	// https://stackoverflow.com/questions/5485759/how-to-determine-a-wifi-channel-number-used-by-wifi-ap-network
	public static String ChanneltoFrequncy(String channel) {
		int channelint = Integer.parseInt(channel);
		int channeltostring;
		if (channelint >= 1 && channelint <= 14) {
			channeltostring = (channelint - 1) * 5 + 2412;
			return channeltostring + "";
		} else if (channelint >= 36 && channelint <= 165) {
			channeltostring = (channelint - 34) * 5 + 5170;
			return channeltostring + "";
		} else {
			return "";
		}
	}

	//the function chek who is the 10 larget wifi network signal in each scan of the app
	public static int[] IndexOfMaxRSSIWifi(int start, int end, ArrayList<AllData> table) {
		int max[] = new int[11];
		// int palceOfRSSI = PlaceOf(table, "RSSI");
		if (end - start + 1 < 10) {
			int counter = 0, j = 0;
			for (int i = start; i <= end; i++) {
				if (table.get(i).getType().equals("WIFI")) {
					max[j] = i;
					j++;
					counter++;
				}
			}
			max[10] = counter;
			return max;
		} else {
			int Max = Integer.parseInt(table.get(start).getSignal());
			// להוסיף תנאי של end-start אם הוא גדול מ10 או לא
			int MaxIndex = start;
			int counter = 0;
			for (int j = 0; j < max.length - 1; j++) {
				for (int i = start + 1; i <= end; i++) {
					if (Integer.parseInt(table.get(i).getSignal()) > Max && !contains(max, i)
							&& table.get(i).getType().equals("WIFI")) {
						MaxIndex = i;
						Max = Integer.parseInt(table.get(i).getSignal());

					}
				} // מכניס את אותו max
				max[j] = MaxIndex;
				Max = 0;
				counter++;
			}
			max[10] = counter;
			return max;
		}
	}

	public static boolean contains(int[] max, int i) {
		for (int j = 0; j < max.length; j++) {
			if (i == max[j])
				return true;

		}
		return false;

	}

	public void writecsv(ArrayList<Scan> write, String path) throws IOException {
		String[] title = new String[46];
		// הכנסת כותרות למטריצה
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
			writer.append(write.get(i).getTime() + "," + write.get(i).getID() + "," + write.get(i).getLat() + ","
					+ write.get(i).getLon() + "," + write.get(i).getAlt() + "," + write.get(i).getWifiNetwork() + ",");
			WifiData[] temp = write.get(i).getWifi();
			for (int j = 0; j < temp.length; j++) {
				writer.append(temp[j].getSSID() + "," + temp[j].getMAC() + "," + temp[j].getFrequncy() + ","
						+ temp[j].getSignal()+",");

			}
			outs.println();
		}

		writer.close();
		System.out.println("csv create complete,please chek file.");

	}



}
