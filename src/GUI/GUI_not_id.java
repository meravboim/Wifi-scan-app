package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import Filter.Filters;
import javax.swing.UIManager;

public class GUI_not_id extends gui{

	private JFrame frame;
	private JTextField id_txt;
	private String not_id;
	


	public String getnot_Id() {
		return not_id;
	}

	public void setnot_Id(String not_id) {
		this.not_id = not_id;
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
	public static void GUI_not_ID() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 GUI_not_id window = new  GUI_not_id();
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
	public GUI_not_id() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 250, 356, 207);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JTextPane txtpnNotId = new JTextPane();
		springLayout.putConstraint(SpringLayout.WEST, txtpnNotId, 78, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnNotId, -117, SpringLayout.SOUTH, frame.getContentPane());
		txtpnNotId.setText("   Not  ID");
		txtpnNotId.setForeground(new Color(128, 0, 0));
		txtpnNotId.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtpnNotId.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(txtpnNotId);
		
		JTextPane textPane_1 = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane_1, 6, SpringLayout.SOUTH, txtpnNotId);
		textPane_1.setText("ID:");
		textPane_1.setForeground(Color.BLACK);
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPane_1.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(textPane_1);
		
		JButton id_bouttm = new JButton("Apply");
		springLayout.putConstraint(SpringLayout.NORTH, id_bouttm, 33, SpringLayout.SOUTH, textPane_1);
		springLayout.putConstraint(SpringLayout.WEST, id_bouttm, 67, SpringLayout.WEST, frame.getContentPane());
		id_bouttm.setBackground(UIManager.getColor("Button.background"));
		id_bouttm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		

				frame.dispose();
			}
		});
		frame.getContentPane().add(id_bouttm);
		
		id_txt = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, id_txt, -110, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textPane_1, -11, SpringLayout.WEST, id_txt);
		springLayout.putConstraint(SpringLayout.NORTH, id_txt, 6, SpringLayout.SOUTH, txtpnNotId);
		frame.getContentPane().add(id_txt);
		id_txt.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				not_id=id_txt.getText();
				frame.dispose();
			}
		});
		btnCancel.setBackground(UIManager.getColor("Button.background"));
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, id_bouttm);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 74, SpringLayout.EAST, id_bouttm);
		frame.getContentPane().add(btnCancel);
	}

}
