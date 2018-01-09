package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import Filter.Filters;
import object.Cordinate;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class gui_filter_byPlace extends gui{

	private JFrame frame;
	private JTextField lat;
	private JTextField radius;
	private JTextField lon;
	private double lon1;
	private double lat1;
	private double radius1;
	
	public double getLon1() {
		return lon1;
	}

	public void setLon1(double lon1) {
		this.lon1 = lon1;
	}

	public double getLat1() {
		return lat1;
	}

	public void setLat1(double lat1) {
		this.lat1 = lat1;
	}

	public double getRadius1() {
		return radius1;
	}

	public void setRadius1(double radius1) {
		this.radius1 = radius1;
	}

	public JTextField getLat() {
		return lat;
	}

	public void setLat(JTextField lat) {
		this.lat = lat;
	}

	public JTextField getRadius() {
		return radius;
	}

	public void setRadius(JTextField radius) {
		this.radius = radius;
	}

	public JTextField getLon() {
		return lon;
	}

	public void setLon(JTextField lon) {
		this.lon = lon;
	}
	/**
	 * Launch the application.
	 */
	public static void GUI_BY_PLACE() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui_filter_byPlace window = new gui_filter_byPlace();
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
	public gui_filter_byPlace() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 250, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		lat = new JTextField();
		lat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lat.setColumns(10);
		frame.getContentPane().add(lat);
		
		radius = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, radius, 6, SpringLayout.SOUTH, lat);
		springLayout.putConstraint(SpringLayout.WEST, radius, 0, SpringLayout.WEST, lat);
		radius.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radius.setColumns(10);
		frame.getContentPane().add(radius);
		
		JTextPane textPane = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, lat, 0, SpringLayout.NORTH, textPane);
		textPane.setText("Lat:");
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPane.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane, 6, SpringLayout.SOUTH, textPane_1);
		springLayout.putConstraint(SpringLayout.WEST, textPane, 0, SpringLayout.WEST, textPane_1);
		springLayout.putConstraint(SpringLayout.NORTH, textPane_1, 58, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textPane_1, 110, SpringLayout.WEST, frame.getContentPane());
		textPane_1.setText("Lon:");
		textPane_1.setForeground(Color.BLACK);
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPane_1.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane_1);
		
		lon = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, lat, 0, SpringLayout.EAST, lon);
		springLayout.putConstraint(SpringLayout.WEST, lon, 6, SpringLayout.EAST, textPane_1);
		springLayout.putConstraint(SpringLayout.SOUTH, lon, 0, SpringLayout.SOUTH, textPane_1);
		lon.setToolTipText("enter radis");
		lon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lon.setColumns(10);
		frame.getContentPane().add(lon);
		
		JTextPane textPane_2 = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane_2, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textPane_2, 46, SpringLayout.WEST, frame.getContentPane());
		textPane_2.setText("  Place");
		textPane_2.setForeground(new Color(128, 0, 0));
		textPane_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textPane_2.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane_3, 0, SpringLayout.NORTH, radius);
		springLayout.putConstraint(SpringLayout.EAST, textPane_3, -6, SpringLayout.WEST, radius);
		textPane_3.setText("Radius:");
		textPane_3.setForeground(Color.BLACK);
		textPane_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPane_3.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane_3);
		
		JButton apply = new JButton("apply");
		apply.setBackground(UIManager.getColor("Button.background"));
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lon1= Double.parseDouble(lon.getText());
				lat1 = Double.parseDouble(lat.getText());
				radius1 = Double.parseDouble(radius.getText());
				frame.dispose();
			}
		});

	
		frame.getContentPane().add(apply);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		springLayout.putConstraint(SpringLayout.NORTH, apply, 0, SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, apply, -25, SpringLayout.WEST, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 210, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -39, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
	}

}
