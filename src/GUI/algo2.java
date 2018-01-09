package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class algo2 extends JPanel {
	
	Connect c;
	
	public Connect getC() {
		return c;
	}

	/**
	 * Create the panel.
	 */
	public algo2(Connect con) {
		Connect c=new Connect();
		c.setData(con.data);
		setBackground(new Color(250, 235, 215));
		setLayout(null);
		
		JLabel lblAlgorithem = new JLabel("Algorithem 2 - Path of csv");
		lblAlgorithem.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlgorithem.setBounds(38, 56, 336, 27);
		add(lblAlgorithem);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(38, 231, 356, 27);
		add(lblNewLabel);
		
		JButton button = new JButton("Srart");
		button.addActionListener(new ActionListener() {
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
				c.algoritem2a(path);
				lblNewLabel.setText("resutlt_algo2.csv was created check workspace");
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(UIManager.getColor("Button.highlight"));
		button.setBounds(262, 185, 89, 23);
		add(button);
		
		JLabel lblClikOnThe = new JLabel("clik on the \"Start\" to  choose path");
		lblClikOnThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClikOnThe.setBounds(45, 122, 306, 27);
		add(lblClikOnThe);
		


	}
}
