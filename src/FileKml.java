import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileKml {
	
	private String path;
	
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

	public FileKml(String path) {
		this.path = path;
	}

	public FileKml() {
		this.path = null;
	}

	public FileKml(FileKml other) {
		this.path = other.path;
	}
	
	// this function read the csv table, that we created, and enter them to ArrayList of the object Scan
		//	//(that contains all the variables that inside the csv table)

		public void readFromCsvToKml(String path) throws IOException {
			ArrayList<Scan> arrayOfscan = new ArrayList<Scan>();
			File f = new File(path);
			FileInputStream fi = new FileInputStream(f);
			Scanner sc = new Scanner(fi);
			String str = sc.nextLine();
			String[] data1 = str.split(",");
			while (sc.hasNext()) {
				try {
				 str = sc.nextLine();
				String[] data = str.split(",");
				WifiData Dataw[] = new WifiData[Integer.parseInt(data[5])];
				int i = 6;
				for (int j = 0; j < Integer.parseInt(data[5]); j++) {
					Dataw[j] = new WifiData(data[i], data[i + 1], data[i + 2], data[i + 3]);
					i = i + 4;

				}

				Scan temp = new Scan(data[0], data[1], data[2], data[3], data[4], data[5], Dataw);
				arrayOfscan.add(temp);
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("there is a problem with the line:"+str);
				}
			}
			System.out.println(arrayOfscan.toString());
			sc.close();
			fi.close();

			ChekFilterForKml(arrayOfscan);
		}
		
		public static void ChekFilterForKml(ArrayList<Scan> arryOfscan) throws IOException {
			FileKml fe = new FileKml();
			System.out.println("Enter 1 to select by time, 2 to select by place or 3 to select by id");
			Scanner sc = new Scanner(System.in);
			int select = sc.nextInt();
			if (select == 1) {
				System.out.println("Enter MinTime and MaxTime (dd/mm/yy)");
				String mDate = sc.next();
				String mTime = sc.next();
				String MDate = sc.next();
				String MTime = sc.next();
				String MaxTime = MDate + " " + MTime;
				String MinTime = mDate + " " + mTime;
				System.out.println(MinTime + " " + MaxTime);

				fe.TurnToKML(SelectByTime(arryOfscan, MinTime, MaxTime), "C:\\Users\\yitzhak\\Desktop\\KmlByTime.kml");

			}
			if (select == 2) {
				System.out.println("Enter Radus, CenterLon and CenterLat");
				double Radus = sc.nextDouble();
				double CenterLon = sc.nextDouble();
				double CenterLat = sc.nextDouble();

				fe.TurnToKML(SelectByPlace(arryOfscan, Radus, CenterLon, CenterLat),
						"C:\\Users\\yitzhak\\Desktop\\KmlByPlace.kml");

			}
			if (select == 3) {
				System.out.println("Enter Id");
				String Id = sc.next();
				System.out.println(Id);
				fe.TurnToKML(SelectById(arryOfscan, Id), "C:\\Users\\yitzhak\\Desktop\\KmlById.kml");
				
			}
			
			sc.close();
		}

		public static ArrayList<Scan> SelectByPlace(ArrayList<Scan> arrayOfscan, double Radus, double CenterLon,
				double CenterLat) {
			ArrayList<Scan> arrayOfplace = new ArrayList<Scan>();

			int indexPlace = 0;

			for (int i = 0; i < arrayOfscan.size(); i++) {

				if (Distans(CenterLat, CenterLon, Double.parseDouble(arrayOfscan.get(i).getLat()),

						Double.parseDouble(arrayOfscan.get(i).getLon())) <= Radus) {
					arrayOfplace.add(arrayOfscan.get(i));

					indexPlace++;
				}
			}

			return arrayOfplace;
		}

		public static double Distans(double x1, double y1, double x2, double y2) {
			double x = Math.pow(x1 - x2, 2);
			double y = Math.pow(y1 - y2, 2);
			double dis = Math.sqrt(x + y);
			return dis;
		}

		public static ArrayList<Scan> SelectById(ArrayList<Scan> arrayOfscan, String Id) {

			ArrayList<Scan> arrayOfId = new ArrayList<Scan>();
			int indexId = 0;

			for (int i = 0; i < arrayOfscan.size(); i++) {
				if (Id.equals(arrayOfscan.get(i).getID())) {
					arrayOfId.add(arrayOfscan.get(i));
					indexId++;
				}

			}
			System.out.println(arrayOfId.toString());
			return arrayOfId;
		}

		public static ArrayList<Scan> SelectByTime(ArrayList<Scan> arrayOfscan, String MinTime, String MaxTime) {
			ArrayList<Scan> arrayOftime = new ArrayList<Scan>();

			int indexTime = 0;

			for (int i = 0; i < arrayOfscan.size(); i++) {

				if (arrayOfscan.get(i).getTime().compareTo(MinTime) >= 0
						&& arrayOfscan.get(i).getTime().compareTo(MaxTime) <= 0) {

					arrayOftime.add(arrayOfscan.get(i));
					indexTime++;
				}
			}

			return arrayOftime;
		}

		public void TurnToKML(ArrayList<Scan> arrayOfscan, String path) throws IOException {
			System.out.println("choose a name for the kml file: ");
			Scanner sc = new Scanner(System.in);
			path = sc.next();
			path = path + ".kml";
			FileWriter writer = new FileWriter(path);
			PrintWriter outs = new PrintWriter(writer);
			String xml1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
			String OpenPlacemark = "<Placemark>";
			String OpenName = "<name><![CDATA[";
			String CloseName = "]]></name>";
			String OpenDescription = "<description><![CDATA[";
			String CloseDesription = "<]]></description>";
			String OpenPoint = "<Point>";
			String ClosePoint = "</Point>";
			String OpenCordinate = "<coordinates>";
			String CloseCordinate = "</coordinates>";
			String kml = "</Folder></Document></kml>";
			String ClosePlacemark = "</Placemark>";
			outs.println(xml1);
			outs.println(
					"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n"
							+ "");
			for (int k = 1; k < arrayOfscan.size(); k++) {
				WifiData[] temp = arrayOfscan.get(k).getWifi();
				Arrays.sort(temp);
				System.out.println(Arrays.toString(temp));
				outs.println(OpenPlacemark);
				outs.println(OpenName + arrayOfscan.get(k).getTime() + CloseName);
				outs.println(OpenDescription + "Number of WifiNetwork: " + arrayOfscan.get(k).getWifiNetwork());
				outs.println("<br> Time: " + arrayOfscan.get(k).getTime());
				outs.println("<table  border=\"1\" style=\"font-size:12px;\">");
				outs.println("<tr><td><b>Name</b></td>\n" + "<td><b>Mac</b></td>\n" + "<td><b>Frequency</b></td>\n"
						+ "<td><b>Signal</b></td>\n" + "</tr>");
				for (int i = 0; i < temp.length; i++) {
					outs.println("<tr><td>" + temp[i].getSSID() + "</td>" + "<td>" + temp[i].getMAC() + "</td>" + "<td>"
							+ temp[i].getFrequncy() + "</td>" + "<td>" + temp[i].getSignal() + "</td></tr>");
				}
				outs.println("</table>" + CloseDesription);
				outs.println("<styleUrl>#" + Color(temp, 0) + "</styleUrl>");
				outs.println(OpenPoint);
				outs.println(OpenCordinate + arrayOfscan.get(k).getLon() + "," + arrayOfscan.get(k).getLat() + CloseCordinate);
				outs.println(ClosePoint);
				outs.println(ClosePlacemark);
			}
			outs.println(kml);
			outs.close();
			System.out.println("the file is created");

		}

		public String Color(WifiData[] Select, int i) {
			if (Integer.parseInt(Select[i].getSignal()) > -70) {
				return "green";
			} else if (Integer.parseInt(Select[i].getSignal()) < -70 && Integer.parseInt(Select[i].getSignal()) > -90) {
				return "yellow";
			} else {
				return "red";
			}
		}


}
