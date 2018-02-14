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

public class tokml extends JPanel {
	private JTextField textField;
	
	Connect c;
	
	public Connect getC() {
		return c;
	}

	/**
	 * Create the panel.
	 */
	public tokml(Connect con) {
		Connect c=new Connect();
		c.setData(con.data);
		setBackground(new Color(250, 235, 215));
		setLayout(null);
		
		JLabel lblExportKmlFile = new JLabel("Export KML file");
		lblExportKmlFile.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExportKmlFile.setBounds(32, 41, 249, 27);
		add(lblExportKmlFile);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(105, 121, 207, 20);
		add(textField);
		
		JLabel lblName = new JLabel("name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(58, 117, 37, 27);
		add(lblName);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(58, 227, 295, 27);
		add(label_1);
		
		JLabel lblFirstEnterName = new JLabel("first enter name and then path");
		lblFirstEnterName.setBounds(61, 81, 362, 27);
		add(lblFirstEnterName);
		String pathname [] = new String [2];
		JButton button = new JButton("Choose path");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name1 = textField.getText();
		
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
					System.out.println(name1);
					if(!name1.substring(name1.length()-3, name1.length()).equals("kml")) 
						name1=name1+".kml";
					  pathname[0]=path+"/"+name1;
					lblFirstEnterName.setText(path);


			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(UIManager.getColor("Button.background"));
		button.setBounds(46, 151, 140, 23);
		add(button);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
	
				c.saveinkml(pathname[0]);
				label_1.setText("The KML file wab created");
			}
		});
		btnExport.setBackground(UIManager.getColor("Button.background"));
		btnExport.setBounds(265, 198, 97, 25);
		add(btnExport);
		

	

	}
}
