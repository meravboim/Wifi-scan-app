package GUI;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Algoritem.Algoritem;
import Files.FileCsv;
import Files.FileKml;
import Files.ReadFromSQL;
import Files.Write_filter;
import Filter.ChooseFilter;
import Filter.Filter;
import Filter.FilterByID;
import Filter.FilterByPlace;
import Filter.FilterByTime;
import Filter.Filters;
import Filter.Makefilter;
import Filter.NotFilter;
import object.Database;
import object.MacData;
import object.Scan;
import object.Cordinate;

public class Connect {
	Database data;
	Database prev;
	ArrayList<String> csv_paths;
	ArrayList<String> folder_paths;
	ArrayList<ReadFromSQL> sql_paths;
	boolean flag=false;
	public Database GETprev_databacs() {
		return this.prev;
	}
	public Database getData() {
		return data;
	}

	public void setData(Database data) {
		this.data.setDatabase(data.getDatabase());;
	}
	public void prevSetData(Database other) {
		this.prev.setDatabase(other.getDatabase());
	}
	/**
	 * counstractor
	 */
	public Connect() {
		this.data = new Database();
		this.csv_paths = new ArrayList<>();
		this.folder_paths = new ArrayList<>();
		this.prev= new Database();
		this.sql_paths=new ArrayList<>();
	}
	public void insertJDBS (ReadFromSQL m) {
		this.sql_paths.add(m);
		this.data.addArrayList(m.test_ex4_db());

		this.flag=true;
	}
	public void write (Filters f) throws IOException {
		Write_filter t= new Write_filter();
		t.WriteFilter(f);
	}

	public Filters read () {
		Write_filter t= new Write_filter();
		Filters f=null;
		try {
			f = t.Read_Filter();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;

	}
	/**
	 * function that follow change on files 
	 * @param path
	 */
	public void folow_csv(String path) {
		this.csv_paths.add(path);
		new Thread(new Runnable() {
			@Override
			public void run() {
				changeFiles();
				//readCSv(path);
			}

		}).start();

	}
	/**
	 * the  shell function check change in the folder
	 * @param path
	 */
	public void folow_folder(String path) {
		System.out.println("enter folow folder");
		this.folder_paths.add(path);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("enter run folow folder");
					changeFolder(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}).start();

	}
	/**
	 * the function contians pool tread that follow the folder
	 * @param data
	 * @throws IOException
	 */
	// https://github.com/yuvalmizrahi2/Task1/blob/master/src/wraper/Listener.java
	// https://github.com/ruckc/filewatcher/blob/master/src/main/java/io/ruck/filewatcher/Watcher.java
	public void changeFolder(Database data) throws IOException {
		// System.out.println("enter change folder");
		ExecutorService servise = Executors.newCachedThreadPool();
		final FileSystem fs = FileSystems.getDefault();
		final WatchService watcher = fs.newWatchService();
		int size = folder_paths.size();
		System.out.println("size of the csv_path " + size);
		Map<WatchKey, String> keys = new HashMap<>();
		for (int i = 0; i < this.folder_paths.size(); i++) {
			if (!keys.containsValue(folder_paths.get(i))) {
				try {
					Path path = Paths.get(folder_paths.get(i));
					// the event we want to check
					WatchKey key = path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
					keys.put(key, folder_paths.get(i));

				} catch (Exception e) {
					System.out.println("error with the folder " + folder_paths.get(i));

				}

			}
		}
		servise.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("enter ruunable");
				while (Thread.interrupted() == false) {
					WatchKey t = null;
					try {
						t = watcher.poll(20, TimeUnit.MILLISECONDS);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					// if there is a change then the watchkey is change
					if (t != null) {
						System.out.println("change");
						database(); // restart to the database
						System.out.println("data size  in the function" + data.getDatabase().size());
						servise.shutdownNow();
						Thread.currentThread().interrupt();
						try {
							changeFolder(data);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}

					else if (size != folder_paths.size()) {
						try {
							watcher.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						servise.shutdown();
						Thread.currentThread().interrupt();
						try {
							changeFolder(data);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

		});
	}
	/**
	 * the function follow on the change on the csv file
	 */
	// https://github.com/yuvalmizrahi2/Task1/blob/master/src/wraper/Listener.java
	// https://stackoverflow.com/questions/2064694/how-do-i-find-the-last-modified-file-in-a-directory-in-java
	public void changeFiles() {
		int size = csv_paths.size();
		ExecutorService servise = Executors.newCachedThreadPool();
		ArrayList<Long> lastmodify = new ArrayList<Long>();
		for (int i = 0; i < this.csv_paths.size(); i++) {
			lastmodify.add(new File(csv_paths.get(i)).lastModified());
		}
		servise.submit(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (Thread.currentThread().isInterrupted() == false) {
					for (int i = 0; i < lastmodify.size(); i++) {
						if (lastmodify.get(i) != new File(csv_paths.get(i)).lastModified()) {
							database();
							servise.shutdownNow();
							Thread.currentThread().interrupt();
							changeFiles();
						}
					}
					if (size != csv_paths.size()) {
						database();
						servise.shutdownNow();
						Thread.currentThread().interrupt();
						changeFiles();
					}
				}
			}

		});

	}
	/**
	 * the function enter the wigle-wifi files from folder to the database
	 * @param path
	 */
	public void enterdatabase(String path) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (data) {
					FileCsv c = new FileCsv();
					data.addArrayList(c.readForCsv(path).getDatabase());
					prevSetData(data);
					System.out.println(data.getDatabase().size());
					System.out.println("prve size threads "+prev.getDatabase().size());
				}
			}
		});
		t.start();
		// return this.data;
	}
	/**
	 * The function enter the Table.csv files  to the database
	 * @param path
	 */
	public void readCSv(String path) {
		if (!path.substring(path.length() - 3, path.length()).equals("csv"))
			path = path + ".csv";
		final String str=path;
		FileKml k = new FileKml();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (data) {

					data.addArrayList(k.readFromCsv(str) );
					data.douplicate();
				}
			}
		});t.start();

	}

	/**
	 * the function call to Algo1 methods
	 * @param mac
	 * @return
	 */
	public Cordinate algoritem1(String mac) {

		Algoritem l = new Algoritem();
		Map<String, ArrayList<MacData>> map = l.algo1Hash(this.data.getDatabase());
		Cordinate cor = new Cordinate(l.algo1(map, mac));
		return cor;
	}
	/**
	 * the function call to Algo 2 methods
	 * @param path
	 */
	public void algoritem2a(String path) {
		Algoritem l = new Algoritem();
		FileKml s = new FileKml();
		l.algo2tocsv(this.data, s.readFromCsv(path));
	}
	/**
	 * the function call to the Algo2 method that get 3 macs and signal
	 * @param mac1
	 * @param mac2
	 * @param mac3
	 * @param sig1
	 * @param sig2
	 * @param sig3
	 * @return
	 */
	public Cordinate algoritem2b(String mac1, String mac2, String mac3, String sig1, String sig2, String sig3) {
		Algoritem l = new Algoritem();
		Cordinate cor = new Cordinate(l.algo2fromUser(this.data, mac1, mac2, mac3, sig1, sig2, sig3));
		return cor;
	}
	/**
	 * check input for the macs
	 * @param mac
	 * @return
	 */
	public boolean check_user_macs(String mac) {
		String[] checkmac = mac.split(":");
		boolean flag = true;
		if (checkmac.length == 6) {
			for (int i = 0; i < checkmac.length; i++) {
				for (int j = 0; j < checkmac[i].length(); j++) {
					int ascii = (int) checkmac[i].charAt(j);
					if (!(ascii >= (int) 'a' && ascii <= (int) 'z') || !(ascii >= (int) 'A' && ascii <= (int) 'Z')
							|| !(ascii >= '0' && ascii <= '9'))
						return false;
				}
			}
			return true;
		} else
			return false;
	}
	/**
	 * check input to the signal
	 * @param sig
	 * @return
	 */
	public boolean check_user_Signal(String sig) {

		String k = sig.substring(1, sig.length());
		if (sig.length() > 4)
			return false;
		for (int i = 0; i < k.length(); i++) {
			if (!((int) k.charAt(i) >= '0' && (int) k.charAt(i) <= '9')) {
				return false;
			}
		}
		return true;

	}
	/**
	 * the function clear database
	 * @return
	 */
	public Database clear() {
		this.data.getDatabase().clear();
		this.data.getHash_map().clear();
		return this.data;
	}

	/**
	 * the function write the database to csv
	 * @param name
	 */
	public void writeCSV(String name) {
		if (name.length() == 0)
			name = "database.csv";

		else if (!name.substring(name.length() - 3, name.length()).equals("csv"))
			name = name + ".csv";
		FileCsv t = new FileCsv();
		try {
			t.writecsv(this.data.getDatabase(), name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
	/**
	 * the function write the database to kml
	 * @param name
	 */

	public void saveinkml(String name) {
		if (name.substring(name.length() - 3, name.length()).equals("kml")) {
			name = name + ".kml";
			FileKml k = new FileKml();
			k.TurnToKML(this.data.getDatabase(), name);
		}
	}

	/**
	 * the function do !filter by time
	 * @param min
	 * @param max
	 * @return
	 */

	public Filters notfiltertime(String min, String max) {
		Filters time = new FilterByTime(min, max);
		Filters id2 = new NotFilter(time);
		return id2;
	}

	/**
	 * the function do filter by time
	 * @param min
	 * @param max
	 * @return
	 */
	public Filters filtertime(String min, String max) {
		Filters time = new FilterByTime(min, max);
		return time;
	}

	/**
	 * the function do filter by place
	 * @param cor
	 * @param rad
	 * @return
	 */
	public Filters filterplace(Cordinate cor, double rad) {
		Filters place = new FilterByPlace(cor, rad);
		return place;
	}
	/**
	 * the function do !filter by place
	 * @param cor
	 * @param rad
	 * @return
	 */
	public Filters notfilterplace(Cordinate cor, double rad) {
		Filters place = new FilterByPlace(cor, rad);
		Filters id2 = new NotFilter(place);
		return id2;
	}

	/**
	 * the function do filter by id
	 * @param id
	 * @return
	 */
	public Filters filterId(String id) {
		Filters id1 = new FilterByID(id);
		return id1;
	}
	/**
	 * the function do !filter by id
	 * @param id
	 * @return
	 */
	public Filters NOtfilterId(String id) {
		Filters id1 = new FilterByID(id);
		Filters id2 = new NotFilter(id1);
		return id2;
	}
	/**
	 * the function restart the database
	 * @param data
	 */

	public  void database() {
		System.out.println("restart database");
		this.data.cleardatabase();
		for (int i = 0; i < this.folder_paths.size(); i++) {
			enterdatabase(this.folder_paths.get(i));
		}
		for (int i = 0; i < this.csv_paths.size(); i++) {
			readCSv(this.csv_paths.get(i));
		}
		if (this.flag==true) {
			for (int i = 0; i < this.sql_paths.size(); i++) {
				data.addArrayList(sql_paths.get(i).test_ex4_db());
			}
		}
		System.out.println("data     size " + data.numOfScan());
		System.out.println("data size " + data.getDatabase().size());
	}

	public void checkForChange (ReadFromSQL s) {
			Thread t = new Thread(new Runnable() {
				 Statement st = null;
				 ResultSet rs = null;
				 ResultSet temp = null;


				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {     
						s.set_con( DriverManager.getConnection(s.get_url(),s.get_user(), s.get_password()));
						st =s.get_con().createStatement();
						rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
						boolean flag = true;
						while(flag){
							s.set_con( DriverManager.getConnection(s.get_url(),s.get_user(), s.get_password()));
							st =s.get_con().createStatement();
							temp = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
							if(!temp.equals(rs)){
								database();
								flag=false;
								Thread.interrupted();
								insertJDBS(s);

							}
						}


					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
			
			}); t.start();

		}}