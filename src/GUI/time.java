package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.toedter.calendar.JDateChooser;

import Filter.AndFilter;
import Filter.FilterByTime;
import Filter.Filters;
import Filter.Makefilter;
import Filter.NotFilter;
import Filter.Operator;
import Filter.OrFilter;
import object.Database;
import java.awt.Font;
import java.awt.Color;

public class time extends JPanel {
	private String min;
	private String max;

	/**
	 * Create the panel.
	 */
	public time(Filters [] filter,Connect c) {
		setBackground(new Color(250, 235, 215));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JDateChooser txt_max_year = new JDateChooser();
		springLayout.putConstraint(SpringLayout.NORTH, txt_max_year, 89, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, txt_max_year, 120, SpringLayout.WEST, this);
		add(txt_max_year);

		JDateChooser txt_min_year = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, txt_min_year, 0, SpringLayout.WEST, txt_max_year);
		add(txt_min_year);

		JLabel lblFilterByTime = new JLabel("Filter by Time");
		springLayout.putConstraint(SpringLayout.WEST, lblFilterByTime, 180, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblFilterByTime, -31, SpringLayout.NORTH, txt_max_year);
		lblFilterByTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblFilterByTime);

		JLabel lblStart = new JLabel("Start:");
		springLayout.putConstraint(SpringLayout.NORTH, lblStart, 0, SpringLayout.NORTH, txt_max_year);
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblStart);

		JLabel lblEnd_1 = new JLabel("End:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblEnd_1, -107, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, txt_min_year, 0, SpringLayout.NORTH, lblEnd_1);
		springLayout.putConstraint(SpringLayout.WEST, lblStart, 0, SpringLayout.WEST, lblEnd_1);
		springLayout.putConstraint(SpringLayout.WEST, lblEnd_1, 66, SpringLayout.WEST, this);
		lblEnd_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblEnd_1);
		
		   String m [] = new String [60];
		   m[0]="00";
		   m[1]="01";
		   m[2]="02";
		   m[3]="03";
		   m[4]="04";
		   m[5]="05";
		   m[6]="06";
		   m[7]="07";
		   m[8]="08";
		   m[9]="09";
		   
					for (int i = 10; i < m.length; i++) {
						
						m[i]=""+i;
					}
					
		String h [] = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};

					
		
		JComboBox min1 = new JComboBox(m);
		springLayout.putConstraint(SpringLayout.WEST, min1, 0, SpringLayout.WEST, txt_max_year);
		add(min1);
		
		JComboBox sec1 = new JComboBox(m);
		springLayout.putConstraint(SpringLayout.NORTH, sec1, 6, SpringLayout.SOUTH, txt_max_year);
		springLayout.putConstraint(SpringLayout.WEST, sec1, 16, SpringLayout.EAST, min1);
		springLayout.putConstraint(SpringLayout.NORTH, min1, 0, SpringLayout.NORTH, sec1);
		add(sec1);
		
		JComboBox hur1= new JComboBox(h);
		springLayout.putConstraint(SpringLayout.NORTH, hur1, 10, SpringLayout.SOUTH, lblStart);
		springLayout.putConstraint(SpringLayout.WEST, hur1, 10, SpringLayout.WEST, lblStart);
		add(hur1);
		
		JComboBox min2 = new JComboBox(m);
		springLayout.putConstraint(SpringLayout.NORTH, min2, 6, SpringLayout.SOUTH, txt_min_year);
		springLayout.putConstraint(SpringLayout.WEST, min2, 0, SpringLayout.WEST, txt_max_year);
		add(min2);
		
		JComboBox sec2 = new JComboBox(m);
		springLayout.putConstraint(SpringLayout.NORTH, sec2, 6, SpringLayout.SOUTH, txt_min_year);
		springLayout.putConstraint(SpringLayout.WEST, sec2, 18, SpringLayout.EAST, min2);
		add(sec2);
		
		JComboBox hur2 = new JComboBox(h);
		springLayout.putConstraint(SpringLayout.WEST, hur2, 10, SpringLayout.WEST, lblEnd_1);
		add(hur2);
		
		JButton button = new JButton("apply");
		springLayout.putConstraint(SpringLayout.SOUTH, hur2, -10, SpringLayout.NORTH, button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date_mintest=txt_max_year.getDate().toLocaleString();
				String date_min=date_mintest.substring(9, date_mintest.length());
				String date_maxt =txt_min_year.getDate().toLocaleString();
				String date_max=date_maxt.substring(9, date_maxt.length());
				String min_time=""+hur1.getSelectedItem()+":"+""+min1.getSelectedItem()+":"+""+sec1.getSelectedItem();
				String max_time=hur2.getSelectedItem()+":"+""+min2.getSelectedItem()+":"+""+sec2.getSelectedItem();
				System.out.println("date min"+ date_min);
				min = date_min +" "+min_time;
				max = date_max+ " "+max_time;
				System.out.println(min + "          "+ max);
				min=FilterByTime.CheckTime(min);
				max=FilterByTime.CheckTime(max);
				Date mindate = FilterByTime.stringToDate(min);
				Date maxdate = FilterByTime.stringToDate(max);
				if(!mindate.before(maxdate)) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter correct time");
				}
				else if(min.equals(min_time) || max.equals(max_time)) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter correct time");

				}
				Filters ft= new FilterByTime(mindate, maxdate);
				if(filter[1]!=null)
					filter[2]= ft;
				else 
					filter[0]= ft;
				JOptionPane.showMessageDialog(new JFrame(), "The Filter by Time is Apply");


			}

		});
		springLayout.putConstraint(SpringLayout.WEST, button, 93, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, button, -44, SpringLayout.SOUTH, this);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(button);
		
		JButton button_1 = new JButton("! apply");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date_mintest=txt_max_year.getDate().toLocaleString();
				String date_min=date_mintest.substring(9, date_mintest.length());
				String date_maxt =txt_min_year.getDate().toLocaleString();
				String date_max=date_maxt.substring(9, date_maxt.length());
				String min_time=""+hur1.getSelectedItem()+":"+""+min1.getSelectedItem()+":"+""+sec1.getSelectedItem();
				String max_time=hur2.getSelectedItem()+":"+""+min2.getSelectedItem()+":"+""+sec2.getSelectedItem();
				System.out.println("date min"+ date_min);
				min = date_min +" "+min_time;
				max = date_max+ " "+max_time;
				System.out.println(min + "          "+ max);
				min=FilterByTime.CheckTime(min);
				max=FilterByTime.CheckTime(max);
				Date mindate = FilterByTime.stringToDate(min);
				Date maxdate = FilterByTime.stringToDate(max);
				if(!mindate.before(maxdate)) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter correct time");
				}
				else if(min.equals(min_time) || max.equals(max_time)) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter correct time");

				}
				Filters ft= new FilterByTime(mindate, maxdate);
				if(filter[1]!=null)
					filter[2]= ft;
				else 
					filter[0]= ft;
				JOptionPane.showMessageDialog(new JFrame(), "The Filter not by Time is Apply ");
			}

		});
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 0, SpringLayout.NORTH, button);
		springLayout.putConstraint(SpringLayout.WEST, button_1, 80, SpringLayout.EAST, button);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(button_1);
		
    
	}
}

