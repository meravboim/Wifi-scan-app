package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import object.Database;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class csvpath extends JPanel {
	







	/**
	 * Create the panel.
	 */
	public csvpath(Connect con ) {
		setBackground(new Color(250, 235, 215));
		setLayout(null);
		
		JLabel lblReadDatabaseFrom = new JLabel("Read Database from Csv file");
		lblReadDatabaseFrom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReadDatabaseFrom.setBounds(34, 54, 249, 27);
		add(lblReadDatabaseFrom);
		
		JLabel lblClikOnThe = new JLabel("clik on the \"Insert DataBase\" to choose path");
		lblClikOnThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClikOnThe.setBounds(47, 125, 306, 27);
		add(lblClikOnThe);
		
		JLabel label_1 = new JLabel("Number of Scan: " + con.data.getDatabase().size());
		label_1.setBounds(58, 216, 295, 27);
		add(label_1);
		
		JLabel label_2 = new JLabel("Number of Macs: " + con.data.getHash_map().size());
		label_2.setBounds(58, 254, 295, 27);
		add(label_2);
		
		Thread t=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					label_1.setText("Number of Scan: " + con.data.getDatabase().size());
					label_2.setText("Number of Macs: " + con.data.getHash_map().size());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		t.start();
		
		JButton btnInsert = new JButton("Insert Database");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose= new JFileChooser();
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"csv", "csv");
				String path="";
				chooser.setFileFilter(filter);
				chooser.setDialogTitle("Choose Csv File");
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				path=chooser.getSelectedFile().getAbsolutePath();
				}
				
				con.readCSv(path);
			
				con.folow_csv(path);
				label_1 .setText("Number of Scan: " + con.getData().getDatabase().size());
				label_2.setText("Number of Macs: " + con.getData().getHash_map().size());
				con.prev.set_prev(con.data);
			}
		});
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInsert.setBackground(UIManager.getColor("Button.background"));
		btnInsert.setBounds(213, 182, 158, 23);
		add(btnInsert);
		
	

	}

}
