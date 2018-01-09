package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Filter.FilterByTime;
import Filter.Filters;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class gui_filter_NOttime extends gui{

	private JFrame frame;
	private JTextField time_min;
	private JTextField time_max;
	private String min;
	private String max;


	/**
	 * @return the min
	 */
	public String getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(String min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public String getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * Launch the application.
	 */
	public static void NOTtime_filer_Screen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui_filter_NOttime window = new gui_filter_NOttime();
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
	public gui_filter_NOttime() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 250, 343, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JTextPane textPane = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane, 24, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textPane, 34, SpringLayout.WEST, frame.getContentPane());
		textPane.setText("  Start");
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Time:");
		textPane_1.setForeground(Color.BLACK);
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPane_1.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane_1);

		time_min = new JTextField();
		time_min.setText(" hh:mm:ss");
		time_min.setFont(new Font("Tahoma", Font.PLAIN, 13));
		time_min.setColumns(10);
		frame.getContentPane().add(time_min);

		JTextPane date_Max = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane_1, 7, SpringLayout.SOUTH, date_Max);
		springLayout.putConstraint(SpringLayout.WEST, textPane_1, 0, SpringLayout.WEST, date_Max);
		date_Max.setText("Date:");
		date_Max.setForeground(Color.BLACK);
		date_Max.setFont(new Font("Tahoma", Font.BOLD, 13));
		date_Max.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(date_Max);

		JDateChooser dateMin = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, time_min, 0, SpringLayout.WEST, dateMin);
		frame.getContentPane().add(dateMin);

		JTextPane textPane_3 = new JTextPane();
		springLayout.putConstraint(SpringLayout.SOUTH, textPane_3, -120, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textPane_3, 0, SpringLayout.WEST, textPane);
		textPane_3.setText(" End");
		textPane_3.setForeground(Color.BLACK);
		textPane_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane_3.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane_3);

		JTextPane textPane_4 = new JTextPane();
		springLayout.putConstraint(SpringLayout.EAST, date_Max, 0, SpringLayout.EAST, textPane_4);
		springLayout.putConstraint(SpringLayout.WEST, textPane_4, 80, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, time_min, 0, SpringLayout.NORTH, textPane_4);
		textPane_4.setText("Time:");
		textPane_4.setForeground(Color.BLACK);
		textPane_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPane_4.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane_4);

		time_max = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, time_max, 0, SpringLayout.SOUTH, textPane_1);
		springLayout.putConstraint(SpringLayout.EAST, time_max, 0, SpringLayout.EAST, time_min);
		time_max.setText(" hh:mm:ss");
		time_max.setFont(new Font("Tahoma", Font.PLAIN, 13));
		time_max.setColumns(10);
		frame.getContentPane().add(time_max);

		JTextPane textPane_5 = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane_5, 52, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, dateMin, 11, SpringLayout.EAST, textPane_5);
		springLayout.putConstraint(SpringLayout.NORTH, textPane_4, 9, SpringLayout.SOUTH, textPane_5);
		springLayout.putConstraint(SpringLayout.NORTH, dateMin, 0, SpringLayout.NORTH, textPane_5);
		springLayout.putConstraint(SpringLayout.WEST, textPane_5, 84, SpringLayout.WEST, frame.getContentPane());
		textPane_5.setText("Date:");
		textPane_5.setForeground(Color.BLACK);
		textPane_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPane_5.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane_5);

		JDateChooser dataMax = new JDateChooser();
		springLayout.putConstraint(SpringLayout.NORTH, dataMax, 42, SpringLayout.SOUTH, time_min);
		springLayout.putConstraint(SpringLayout.NORTH, date_Max, 0, SpringLayout.NORTH, dataMax);
		springLayout.putConstraint(SpringLayout.EAST, dataMax, 0, SpringLayout.EAST, dateMin);
		frame.getContentPane().add(dataMax);

		JButton btnApply = new JButton("Apply");
		springLayout.putConstraint(SpringLayout.WEST, btnApply, 0, SpringLayout.WEST, textPane_1);
		springLayout.putConstraint(SpringLayout.SOUTH, btnApply, -10, SpringLayout.SOUTH, frame.getContentPane());
		btnApply.setBackground(UIManager.getColor("Button.background"));
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date_mintest=dateMin.getDate().toLocaleString();
				String date_min=date_mintest.substring(9, date_mintest.length());
				System.out.println(date_min);
				String date_maxt =dataMax.getDate().toLocaleString();
				String date_max=date_maxt.substring(9, date_maxt.length());
				String min_time=time_min.getText();
				String max_time=time_max.getText();
				 min = date_min +" "+min_time;
				 max = date_max+ " "+max_time;
				min=	FilterByTime.CheckTime(min);
				max=FilterByTime.CheckTime(max);
	
				Filters one=c.notfiltertime(min, max);

				frame.dispose();
				
			}
		});
		frame.getContentPane().add(btnApply);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBackground(UIManager.getColor("Button.background"));
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, 0, SpringLayout.SOUTH, btnApply);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, time_min);
		frame.getContentPane().add(btnCancel);
	}

}