package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Filter.AndFilter;
import Filter.Filters;
import Filter.Makefilter;
import Filter.Operator;
import Filter.OrFilter;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

public class or_and extends JPanel {

	/**
	 * Create the panel.
	 */
	public or_and(Filters [] filter,Connect c) {
		setBackground(new Color(250, 235, 215));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JButton btnAnd = new JButton("and");
		springLayout.putConstraint(SpringLayout.NORTH, btnAnd, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnAnd, 52, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAnd, -163, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnAnd, -337, SpringLayout.EAST, this);
		btnAnd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAnd.setBackground(UIManager.getColor("Button.background"));
		btnAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter[1]=new AndFilter();

				JOptionPane.showMessageDialog(new JFrame(), "The And Filter is Apply");

			}
		});
		add(btnAnd);
		
		JButton btnOr = new JButton("or");
		springLayout.putConstraint(SpringLayout.NORTH, btnOr, 167, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnOr, 294, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnOr, -86, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnOr, -111, SpringLayout.EAST, this);
		btnOr.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOr.setBackground(UIManager.getColor("Button.background"));
		btnOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				filter[1]=new OrFilter();
				JOptionPane.showMessageDialog(new JFrame(), "The Or Filter is Apply");


			}
		});
		add(btnOr);

	}

}

