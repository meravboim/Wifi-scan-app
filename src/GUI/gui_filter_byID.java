package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import Filter.Filters;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class gui_filter_byID extends gui{

	private JFrame frame;
	private JTextField id_txt;	
	private String id;
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JTextField getId_txt() {
		return id_txt;
	}

	public void setId_txt(JTextField id_txt) {
		this.id_txt = id_txt;
	}



	/**
	 * Launch the application.
	 */
	public static void GUI_ID() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui_filter_byID window = new gui_filter_byID();
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
	public gui_filter_byID() {
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
		
		JTextPane textPane = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane, 38, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textPane, 159, SpringLayout.WEST, frame.getContentPane());
		textPane.setText("     ID");
		textPane.setForeground(new Color(128, 0, 0));
		textPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		textPane.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane_1, 68, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textPane_1, 124, SpringLayout.WEST, frame.getContentPane());
		textPane_1.setText("ID:");
		textPane_1.setForeground(Color.BLACK);
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPane_1.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane_1);
		
		JButton id_bouttm = new JButton("Apply");
		id_bouttm.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		id_bouttm.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		springLayout.putConstraint(SpringLayout.NORTH, id_bouttm, 63, SpringLayout.SOUTH, textPane_1);
		springLayout.putConstraint(SpringLayout.WEST, id_bouttm, 113, SpringLayout.WEST, frame.getContentPane());
		id_bouttm.setEnabled(true);
		id_bouttm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id=id_txt.getText();
				frame.dispose();
			}
		});
		frame.getContentPane().add(id_bouttm);
		
		id_txt = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, id_txt, 6, SpringLayout.SOUTH, textPane);
		springLayout.putConstraint(SpringLayout.WEST, id_txt, 10, SpringLayout.WEST, textPane);
		frame.getContentPane().add(id_txt);
		id_txt.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 64, SpringLayout.SOUTH, id_txt);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 84, SpringLayout.EAST, id_bouttm);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, id_bouttm);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 162, SpringLayout.EAST, id_bouttm);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNewButton);
	}

}
