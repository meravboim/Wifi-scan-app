package GUI;

import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

import Files.ReadFromSQL;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class sql extends JPanel {
	private JTextField t_username;
	private JTextField t_ip;
	private JTextField t_password;
	private JTextField t_url;

	/**
	 * Create the panel.
	 */
	public sql(Connect con) {
		setBackground(new Color(250, 235, 215));
		setLayout(null);

		JButton btnInset = new JButton("inset ");
		btnInset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String _ip = t_ip.getText();
				String  _url = t_url.getText();
				String _user = t_username.getText();
				String _password = t_password.getText();
				ReadFromSQL k = new ReadFromSQL();
				k.set_ip(_ip);
				k.set_password(_password);
				k.set_url(_url);
				k.set_user(_user);
				con.insertJDBS(k);

			}
		});
		btnInset.setBackground(UIManager.getColor("Button.background"));
		btnInset.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInset.setBounds(194, 240, 97, 25);
		add(btnInset);

		t_username = new JTextField();
		t_username.setText("oop1");
		t_username.setBounds(231, 71, 116, 22);
		add(t_username);
		t_username.setColumns(10);

		t_ip = new JTextField();
		t_ip.setText("5.29.193.52");
		t_ip.setToolTipText("");
		t_ip.setBounds(231, 106, 116, 22);
		add(t_ip);
		t_ip.setColumns(10);

		t_password = new JTextField();
		t_password.setText("Lambda1();");
		t_password.setBounds(231, 141, 116, 22);
		add(t_password);
		t_password.setColumns(10);

		JLabel lblUserName = new JLabel("user name");
		lblUserName.setBounds(128, 74, 76, 16);
		add(lblUserName);

		JLabel lblIp = new JLabel("ip");
		lblIp.setBounds(128, 109, 76, 16);
		add(lblIp);

		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(128, 144, 76, 16);
		add(lblPassword);

		t_url = new JTextField();
		t_url.setText("jdbc:mysql://5.29.193.52:3306/oop_course_ariel");
		t_url.setColumns(10);
		t_url.setBounds(241, 176, 180, 22);
		add(t_url);

		JLabel lblPort = new JLabel("url");
		lblPort.setBounds(128, 179, 76, 16);
		add(lblPort);
		
		JLabel lblInsertSqlTable = new JLabel("Insert SQL table");
		lblInsertSqlTable.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInsertSqlTable.setBounds(128, 34, 133, 16);
		add(lblInsertSqlTable);

	}
}
