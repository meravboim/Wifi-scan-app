package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Filter.AndFilter;
import Filter.FilterByPlace;
import Filter.Filters;
import Filter.Makefilter;
import Filter.NotFilter;
import Filter.Operator;
import Filter.OrFilter;
import object.Cordinate;
import object.Database;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;

public class place extends JPanel {
	private JTextField txt_lat;
	private JTextField txt_lon;
	private JTextField txt_rad;
	private JPanel and_or;

	private double lon;
	private double lat;
	private double rad;

	/**
	 * Create the panel.
	 */
	public place(Filters [] filter,Connect c) {
		setBackground(new Color(250, 235, 215));
			SpringLayout springLayout = new SpringLayout();
			setLayout(springLayout);
			
			txt_lat = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, txt_lat, 101, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, txt_lat, 146, SpringLayout.WEST, this);
			add(txt_lat);
			txt_lat.setColumns(10);
			
			txt_lon = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, txt_lon, 6, SpringLayout.SOUTH, txt_lat);
			springLayout.putConstraint(SpringLayout.WEST, txt_lon, 0, SpringLayout.WEST, txt_lat);
			add(txt_lon);
			txt_lon.setColumns(10);
			
			txt_rad = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, txt_rad, 6, SpringLayout.SOUTH, txt_lon);
			springLayout.putConstraint(SpringLayout.EAST, txt_rad, 0, SpringLayout.EAST, txt_lat);
			add(txt_rad);
			txt_rad.setColumns(10);
			
			JButton btnApply = new JButton("apply");
			springLayout.putConstraint(SpringLayout.NORTH, btnApply, 29, SpringLayout.SOUTH, txt_rad);
			springLayout.putConstraint(SpringLayout.WEST, btnApply, 0, SpringLayout.WEST, txt_lat);
			btnApply.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnApply.setBackground(UIManager.getColor("Button.background"));
			btnApply.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txt_lon.getText().equals("")){
						JOptionPane.showMessageDialog(new JFrame(), "Please enter correct lon");
					}
					lon=Double.parseDouble(txt_lon.getText());

					if(txt_lat.getText().equals("")){
						JOptionPane.showMessageDialog(new JFrame(), "Please enter correct lat");
					}
					lat=Double.parseDouble(txt_lat.getText());

					if(txt_rad.getText().equals("")){
						JOptionPane.showMessageDialog(new JFrame(), "Please enter correct lat");

					}
					rad=Double.parseDouble(txt_rad.getText());

					Cordinate core = new Cordinate(lat,lon,0);
					Filters ft= new FilterByPlace(core, rad);
					if(filter[1]!=null)
					filter[2]= ft;
					else 
						filter[0]= ft;
					
					JOptionPane.showMessageDialog(new JFrame(), "The Filter by Place is Apply ");
				}
			});
			add(btnApply);
			
			JButton btnapply = new JButton("!apply");
			springLayout.putConstraint(SpringLayout.NORTH, btnapply, 0, SpringLayout.NORTH, btnApply);
			springLayout.putConstraint(SpringLayout.WEST, btnapply, 37, SpringLayout.EAST, btnApply);
			btnapply.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnapply.setBackground(UIManager.getColor("Button.background"));
			btnapply.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txt_lon.getText().equals("")){
						JOptionPane.showMessageDialog(new JFrame(), "Please enter correct lon");
					}
					lon=Double.parseDouble(txt_lon.getText());

					if(txt_lat.getText().equals("")){
						JOptionPane.showMessageDialog(new JFrame(), "Please enter correct lat");
					}
					lat=Double.parseDouble(txt_lat.getText());

					if(txt_rad.getText().equals("")){
						JOptionPane.showMessageDialog(new JFrame(), "Please enter correct lat");

					}
					rad=Double.parseDouble(txt_rad.getText());
					
					Cordinate core = new Cordinate(lat,lon,0);
					Filters s= new FilterByPlace(core, rad);
					System.out.println(core.toString());
					System.out.println(s.toString());
					if(filter[1]!=null)
					filter[2]= new NotFilter(s);

					else 
						filter[0]=new NotFilter(s);

					JOptionPane.showMessageDialog(new JFrame(), "The Filter Not by Place is Apply");
					
				}
			});
			add(btnapply);
			
			JLabel lblFilterByPlace_1 = new JLabel("Filter by Place");
			springLayout.putConstraint(SpringLayout.WEST, lblFilterByPlace_1, 152, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, lblFilterByPlace_1, -34, SpringLayout.NORTH, txt_lat);
			lblFilterByPlace_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			add(lblFilterByPlace_1);
			
			JLabel lblLat = new JLabel("Lat:");
			springLayout.putConstraint(SpringLayout.NORTH, lblLat, 0, SpringLayout.NORTH, txt_lat);
			springLayout.putConstraint(SpringLayout.EAST, lblLat, -6, SpringLayout.WEST, txt_lat);
			lblLat.setFont(new Font("Tahoma", Font.PLAIN, 13));
			add(lblLat);
			
			JLabel lblLon = new JLabel("Lon:");
			springLayout.putConstraint(SpringLayout.SOUTH, lblLon, 0, SpringLayout.SOUTH, txt_lon);
			springLayout.putConstraint(SpringLayout.EAST, lblLon, -7, SpringLayout.WEST, txt_lon);
			lblLon.setFont(new Font("Tahoma", Font.PLAIN, 13));
			add(lblLon);
			
			JLabel lblRadius = new JLabel("Radius:");
			springLayout.putConstraint(SpringLayout.SOUTH, lblRadius, 0, SpringLayout.SOUTH, txt_rad);
			springLayout.putConstraint(SpringLayout.EAST, lblRadius, -4, SpringLayout.WEST, txt_rad);
			lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 13));
			add(lblRadius);

		

	}

}
