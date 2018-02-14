package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import object.Cordinate;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class algo1 extends JPanel{
	private JTextField textField;
	
	Connect c;
	
	public Connect getC() {
		return c;
	}

	/**
	 * Create the panel.
	 */
	public algo1(Connect con) {
		Connect c=new Connect();
		c.setData(con.data);
		setBackground(new Color(250, 235, 215));
		setLayout(null);
		
		JLabel lblAlgorithem = new JLabel("Algorithem 1");
		lblAlgorithem.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlgorithem.setBounds(57, 60, 89, 27);
		add(lblAlgorithem);
		
		JLabel lblNewLabel = new JLabel("Mac:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(61, 124, 37, 27);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(108, 128, 154, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Core:");
		lblNewLabel_1.setBounds(20, 190, 420, 27);
		add(lblNewLabel_1);
		
		JButton btnSrart = new JButton("Srart");
		btnSrart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mac = textField.getText();
				Cordinate temp = c.algoritem1(mac);
				lblNewLabel_1.setText("core: Lat:" + temp.getLat() + " Lon:" + temp.getLon() + " Alt:" + temp.getAlt());
			}
		});
		btnSrart.setBackground(UIManager.getColor("Button.highlight"));
		btnSrart.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSrart.setBounds(265, 159, 89, 23);
		add(btnSrart);

	}
}
