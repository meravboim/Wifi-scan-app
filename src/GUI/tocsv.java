package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class tocsv extends JPanel {
	private JTextField textField;
	
	Connect c;
	
	public Connect getC() {
		return c;
	}
	/**
	 * Create the panel.
	 */
	public tocsv(Connect con) {
		Connect c=new Connect();
		c.setData(con.data);
		setBackground(new Color(250, 235, 215));
		setLayout(null);
		
		JLabel lblExportCsvFile = new JLabel("Export CSV file");
		lblExportCsvFile.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExportCsvFile.setBounds(50, 54, 249, 27);
		add(lblExportCsvFile);
		
		JLabel lblName = new JLabel("name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(50, 118, 37, 27);
		add(lblName);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(92, 122, 207, 20);
		add(textField);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(68, 231, 295, 27);
		add(label_1);
		
		JLabel lblFirstEnterName = new JLabel("first enter name and then path");
		lblFirstEnterName.setBounds(94, 83, 321, 27);
		add(lblFirstEnterName);
		
		String pathname [] = new String [2];
		JButton btnExport = new JButton("Choose path");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose = new JFileChooser();
				choose.setCurrentDirectory(new java.io.File("."));
				choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int name = choose.showOpenDialog(null);
				String path = "";
				String name1 = textField.getText();
					if (name == JFileChooser.APPROVE_OPTION) {
						File f = choose.getSelectedFile();
						path = f.getAbsolutePath();
						System.out.println(path);
					}
					path = path.replace("\\", "/");

				if(!name1.substring(name1.length()-3, name1.length()).equals("csv"))
						name1=name1+".csv";
				  pathname[0]=path+"/"+name1;
				lblFirstEnterName.setText(path);
			
			
			}
		});
		btnExport.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExport.setBackground(UIManager.getColor("Button.background"));
		btnExport.setBounds(50, 158, 122, 23);
		add(btnExport);
		
		JButton btnExport_1 = new JButton("Export");
		btnExport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.writeCSV(pathname[0]);
				label_1.setText("The CSV file was created");
			}
		});
		btnExport_1.setBounds(266, 204, 97, 25);
		add(btnExport_1);
		
	
	

	}

}
