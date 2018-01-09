package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Filter.Filter;
import Filter.Filters;
import object.Cordinate;
import object.Database;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class gui {

	Connect c;
	Filters[] f=new Filters[3];
	private JFrame frame;
	private JPanel algo1;
	private JPanel algo2;
	private JPanel algo2b;
	private JPanel csvpath;
	private JPanel folderpath;
	private JPanel clear;
	private JPanel tocsv;
	private JPanel  tokml;
	private JPanel current;
	private JPanel time;
	private JPanel place;
	private JPanel id;
	private JPanel or_and;
	private JPanel filteropen;


	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		c=new Connect();
		//prev=new Database(c.data.getDatabase());
		frame = new JFrame();
		frame.setBackground(new Color(250, 235, 215));
		frame.setBounds(100, 100, 575, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		csvpath=new csvpath(c);
		frame.getContentPane().add(csvpath);
		current=csvpath;
		
	
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Insert Database");
		menuBar.add(mnNewMenu);
		
		JButton btnNewButton = new JButton("Csv    ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			current.setVisible(false);
			csvpath=new csvpath(c);
			frame.getContentPane().add(csvpath);
			current=csvpath;
			}
		});
		mnNewMenu.add(btnNewButton);
		
		JButton btnFolder = new JButton("Folder");
		btnFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				folderpath=new folderpath(c);
			
				frame.getContentPane().add(folderpath);
				current=folderpath;
			//	prev.setDatabase(c.getData().getDatabase());
				//System.out.println("c.data"+prev.getDatabase().size());
			}
		});
		mnNewMenu.add(btnFolder);
		
		JMenu mnNewMenu_1 = new JMenu(" Export Database");
		menuBar.add(mnNewMenu_1);
		
		JButton btnCsv = new JButton("Csv");
		btnCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				tocsv=new tocsv(c);
				frame.getContentPane().add(tocsv);
				current=tocsv;
			}
		});
		mnNewMenu_1.add(btnCsv);
		
		JButton btnKml = new JButton("Kml");
		btnKml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				tokml=new tokml(c);
				frame.getContentPane().add(tokml);
				current=tokml;
			
			}
		});
		mnNewMenu_1.add(btnKml);
		
		JMenu mnNewMenu_2 = new JMenu("Algorithems");
		menuBar.add(mnNewMenu_2);
		
		JButton btnAlgo = new JButton("          Algo 1      ");
		btnAlgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				algo1=new algo1(c);
				frame.getContentPane().add(algo1);
				current=algo1;
				
			}
		});
		mnNewMenu_2.add(btnAlgo);
		
		JButton btnAlgocsv = new JButton("      Algo 2 (CSV)");
		btnAlgocsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				algo2=new algo2(c);
				frame.getContentPane().add(algo2);
				current=algo2;
			}
		});
		mnNewMenu_2.add(btnAlgocsv);
		
		JButton btnAlgomacsig = new JButton("Algo 2 (Mac-Sig)");
		btnAlgomacsig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				algo2b=new algo2b(c);
				frame.getContentPane().add(algo2b);
				current=algo2b;
			}
		});
		mnNewMenu_2.add(btnAlgomacsig);
		
		JMenu mnClear = new JMenu("clear");
		menuBar.add(mnClear);
		
		JButton btnClearDatabase = new JButton("Clear Database");
		btnClearDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				clear=new clear(c);
				frame.getContentPane().add(clear);
				current=clear;
			}
		});
		mnClear.add(btnClearDatabase);
		
		JMenu mnFilters = new JMenu("Filters");
		menuBar.add(mnFilters);
		
		JButton btnTime = new JButton("Time    ");
		btnTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				current.setVisible(false);
				time=new time(f,c);
				frame.getContentPane().add(time);
				current=time;
			}
		});
		
		JButton btnFilter = new JButton("Filter   ");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				filteropen=new filteropen(f,c);
				frame.getContentPane().add(filteropen);
				current=filteropen;
			}
		});
		mnFilters.add(btnFilter);
		mnFilters.add(btnTime);
		
		JButton btnPlace = new JButton("Place   ");
		btnPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				place=new place(f,c);
				frame.getContentPane().add(place);
				current=place;
			}
		});
		mnFilters.add(btnPlace);
		
		JButton btnId = new JButton(" Id        ");
		btnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				id=new id(f,c);
				frame.getContentPane().add(id);
				current=id;
			}
		});
		mnFilters.add(btnId);
		
		JButton btnAndor = new JButton("And/Or");
		btnAndor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				or_and=new or_and(f,c);
				frame.getContentPane().add(or_and);
				current=or_and;
			}
		});
		mnFilters.add(btnAndor);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
	
		

	    
		
		
	    

	}
}
