package GUI;

import java.awt.Color;

import javax.swing.JPanel;

import object.Database;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class clear extends JPanel {

	Connect c;

	public Connect getC() {
		return c;
	}

	/**
	 * Create the panel.
	 */
	public clear(Connect con) {
		setBackground(new Color(250, 235, 215));
		setLayout(null);

		JLabel lblClearDatabase = new JLabel("Clear Database");
		lblClearDatabase.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClearDatabase.setBounds(49, 68, 249, 27);
		add(lblClearDatabase);

		JLabel label = new JLabel("Number of Scan: " + con.data.getDatabase().size());
		label.setBounds(42, 201, 295, 27);
		add(label);

		JLabel label_1 = new JLabel("Number of Macs: " + con.data.getHash_map().size());
		label_1.setBounds(42, 239, 295, 27);
		add(label_1);

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (con.data) {
					while (true) {
						label.setText("Number of Scan: " + con.data.getDatabase().size());
						label_1.setText("Number of Macs: " + con.data.getHash_map().size());
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		});

		JButton btnClear = new JButton("Clear Database");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Database data = con.clear();
				label.setText("Number of Scan: " + data.getDatabase().size());
				label_1.setText("Number of Macs: " + data.getHash_map().size());
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClear.setBackground(UIManager.getColor("Button.highlight"));
		btnClear.setBounds(209, 146, 156, 23);
		add(btnClear);

	}

}
