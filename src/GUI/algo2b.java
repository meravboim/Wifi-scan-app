package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import object.Cordinate;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class algo2b extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	Connect c;
	
	public Connect getC() {
		return c;
	}
	/**
	 * Create the panel.
	 */
	public algo2b(Connect con){
			Connect c=new Connect();
			c.setData(con.data);
		setBackground(new Color(250, 235, 215));
		setLayout(null);
		
		JLabel lblAlgorithem = new JLabel("Algorithem 2 - Mac and Signal");
		lblAlgorithem.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlgorithem.setBounds(38, 51, 336, 27);
		add(lblAlgorithem);
		
		JLabel lblMac = new JLabel("Mac 1:");
		lblMac.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMac.setBounds(48, 98, 50, 27);
		add(lblMac);
		
		JLabel lblMac_1 = new JLabel("Mac 2:");
		lblMac_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMac_1.setBounds(48, 136, 50, 27);
		add(lblMac_1);
		
		JLabel lblMac_2 = new JLabel("Mac 3:");
		lblMac_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMac_2.setBounds(48, 177, 50, 27);
		add(lblMac_2);
		
		JLabel lblSignal = new JLabel("Signal 1:");
		lblSignal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSignal.setBounds(284, 98, 62, 27);
		add(lblSignal);
		
		JLabel lblSignal_1 = new JLabel("Signal 2:");
		lblSignal_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSignal_1.setBounds(284, 136, 62, 27);
		add(lblSignal_1);
		
		JLabel lblSignal_2 = new JLabel("Signal 3:");
		lblSignal_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSignal_2.setBounds(284, 177, 62, 27);
		add(lblSignal_2);
		
		textField = new JTextField();
		textField.setBounds(342, 102, 62, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(342, 140, 62, 20);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(342, 181, 62, 20);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(89, 102, 175, 20);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(89, 140, 175, 20);
		add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(89, 181, 175, 20);
		add(textField_5);
		
		JLabel label = new JLabel("Core:");
		label.setBounds(10, 261, 414, 27);
		add(label);

		
		JButton button = new JButton("Srart");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mac1 = null;
				String mac2 = null;
				String mac3 = null;
				String sig1 = null;
				String sig2 = null;
				String sig3 = null;
				mac1 = textField_3.getText();
				mac2 = textField_4.getText();
				mac3 = textField_5.getText();
				sig1 = textField.getText();
				sig2 = textField_1.getText();
				sig3 = textField_2.getText();

				Cordinate temp = c.algoritem2b(mac1, mac2, mac3, sig1, sig2, sig3);
				label.setText("core: Lat:" + temp.getLat() + " Lon:" + temp.getLon() + " Alt:" + temp.getAlt());

			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(UIManager.getColor("Button.highlight"));
		button.setBounds(284, 227, 89, 23);
		add(button);
		
	
	}

}
