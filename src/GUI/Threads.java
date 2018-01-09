package GUI;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import object.Database;

public class Threads {

	ArrayList<String> csv_paths;
	ArrayList<String> folder_paths;

	public Threads() {
		csv_paths = new ArrayList<>();
		folder_paths = new ArrayList<>();
	}

	public void folow_csv(String path, Connect c) {
		csv_paths.add(path);
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (c.data) {
					changeFiles(c);

				}

			}
		}).start();

	}

	public void folow_folder(String path, Connect c) {
		folder_paths.add(path);
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (c.data) {
					try {
						changeFolder(c);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}).start();

	}

	// https://github.com/ruckc/filewatcher/blob/master/src/main/java/io/ruck/filewatcher/Watcher.java
	public void changeFolder(Connect c) throws IOException {
		ExecutorService servise = Executors.newCachedThreadPool();
		final FileSystem fs = FileSystems.getDefault();
	 final WatchService watcher= fs.newWatchService();;
		int size = folder_paths.size();
		Map<WatchKey, String> keys = new HashMap<>();
		for (int i = 0; i < this.folder_paths.size(); i++) {
			if (!keys.containsValue(folder_paths.get(i))) {
				try {
					Path path = Paths.get(folder_paths.get(i));
					// the event we want to check
					WatchKey key = path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
					keys.put(key, folder_paths.get(i));

				} 
				catch (Exception e) {
					System.out.println("error with the folder " + folder_paths.get(i));
					
				}
			
			}
		}
		servise.submit(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(Thread.interrupted()==false){
					WatchKey t=null;
					try {
						 t = watcher.poll(20, TimeUnit.MILLISECONDS);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					// if there is a change then the watchkey is change
					if(t!=null){
						database(c); // restart to the database
						servise.shutdownNow();
						Thread.currentThread().interrupt();
						try {
							changeFolder(c);
						}
						catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
					
					else	if(size!=folder_paths.size()) {
						try {
							watcher.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						servise.shutdown();
						Thread.currentThread().interrupt();
						try {
							changeFolder(c);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
			
		});
	}
	//https://stackoverflow.com/questions/2064694/how-do-i-find-the-last-modified-file-in-a-directory-in-java
	public void changeFiles(Connect c) {
		int size= csv_paths.size();
		ExecutorService servise = Executors.newCachedThreadPool();
		ArrayList<Long> lastmodify = new ArrayList<Long>();
		for (int i = 0; i < this.csv_paths.size(); i++) {
			lastmodify.add(new File(csv_paths.get(i)). lastModified());
		}
		servise.submit(new Runnable () {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(Thread.currentThread().isInterrupted()==false) {
					for (int i = 0; i < lastmodify.size(); i++) {
						if(lastmodify.get(i)!= new File(csv_paths.get(i)).lastModified() ) {
							database(c);
							servise.shutdownNow();
							Thread.currentThread().interrupt();
							changeFiles(c);
						}
					}
					if(size!=csv_paths.size()) {
						database(c);
						servise.shutdownNow();
						Thread.currentThread().interrupt();
						changeFiles(c);
					}
				}
			}
			
			
			
		});
		

	}
public void database(Connect c){
	c.data.cleardatabase();
	for (int i = 0; i < this.folder_paths.size(); i++) {
		c.enterdatabase(this.folder_paths.get(i));
		
	}
	for (int i = 0; i <this.csv_paths.size(); i++) {
		c.readCSv(this.csv_paths.get(i));
	}
}

}
