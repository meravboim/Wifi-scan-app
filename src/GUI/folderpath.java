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

import de.micromata.opengis.kml.v_2_2_0.Folder;

public class folderpath extends JPanel {

	/**
	 * Create the panel.
	 */
	public folderpath(Connect con) {
		setBackground(new Color(250, 235, 215));
		setLayout(null);

		JLabel lblReadDatabaseFrom = new JLabel("Read Database from folder");
		lblReadDatabaseFrom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReadDatabaseFrom.setBounds(48, 54, 249, 27);
		add(lblReadDatabaseFrom);
		


		JLabel lblScans = new JLabel("Number of Scan: " + con.data.getDatabase().size());
		lblScans.setBounds(48, 207, 295, 27);
		add(lblScans);

		JLabel lblNumberOfMacs = new JLabel("Number of Macs: " + con.data.getHash_map().size());
		lblNumberOfMacs.setBounds(48, 244, 295, 27);
		add(lblNumberOfMacs);
		
		Thread t=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
				lblScans.setText("Number of Scan: " + con.data.getDatabase().size());
				lblNumberOfMacs.setText("Number of Macs: " +con.data.getHash_map().size());
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
				
				JFileChooser choose = new JFileChooser();
				choose.setCurrentDirectory(new java.io.File("."));
				choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int name = choose.showOpenDialog(null);
				String path = "";
					if (name == JFileChooser.APPROVE_OPTION) {
						File f = choose.getSelectedFile();
						path = f.getAbsolutePath();
					}
					path = path.replace("\\", "/");
					
					
					
				con.enterdatabase(path);
				con.folow_folder(path);
				lblScans.setText("Number of Scan: " +con.getData().getDatabase().size());
				lblNumberOfMacs.setText("Number of Macs: " +con.getData().getHash_map().size());

			}
		});
	


		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInsert.setBackground(UIManager.getColor("Button.background"));
		btnInsert.setBounds(234, 173, 158, 23);
		add(btnInsert);

		JLabel label = new JLabel("clik on the \"Insert DataBase\" to choose path");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(72, 118, 306, 27);
		add(label);
	}
}
