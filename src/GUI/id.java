package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Filter.AndFilter;
import Filter.FilterByID;
import Filter.Filters;
import Filter.Makefilter;
import Filter.NotFilter;
import Filter.Operator;
import Filter.OrFilter;
import object.Database;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;

public class id extends JPanel {
	private JTextField txt_id;
	private String id;

	/**
	 * Create the panel.
	 */
	public id(Filters[] filter, Connect c) {
		setBackground(new Color(250, 235, 215));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		txt_id = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txt_id, 141, SpringLayout.WEST, this);
		add(txt_id);
		txt_id.setColumns(10);

		JButton btnApply = new JButton("apply");
		btnApply.setBackground(UIManager.getColor("Button.background"));
		btnApply.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnApply.setForeground(Color.BLACK);
		springLayout.putConstraint(SpringLayout.SOUTH, btnApply, 90, SpringLayout.SOUTH, txt_id);
		springLayout.putConstraint(SpringLayout.EAST, btnApply, 66, SpringLayout.WEST, txt_id);
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnApply)) {
					if (txt_id.getText().equals("")) {
						JOptionPane.showMessageDialog(new JFrame(), "Please enter correct id");

					} else {
						id = txt_id.getText();
						Filters ft = new FilterByID(id);
						if (filter[1] != null)
							filter[2] = ft;
						else
							filter[0] = ft;
						JOptionPane.showMessageDialog(new JFrame(), "The Filter by Id is Apply ");

					}
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnApply, 67, SpringLayout.SOUTH, txt_id);
		springLayout.putConstraint(SpringLayout.WEST, btnApply, 0, SpringLayout.WEST, txt_id);
		add(btnApply);

		JButton Apply_1 = new JButton("! apply");
		springLayout.putConstraint(SpringLayout.NORTH, Apply_1, 0, SpringLayout.NORTH, btnApply);
		springLayout.putConstraint(SpringLayout.WEST, Apply_1, 49, SpringLayout.EAST, btnApply);
		springLayout.putConstraint(SpringLayout.EAST, Apply_1, -128, SpringLayout.EAST, this);
		Apply_1.setBackground(UIManager.getColor("Button.background"));
		Apply_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		Apply_1.setForeground(Color.BLACK);
		Apply_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(Apply_1)) {
					if (txt_id.getText().equals(""))
						JOptionPane.showMessageDialog(new JFrame(), "Please enter correct id");
					else {
						id = txt_id.getText();
						Filters f = new FilterByID(id);
						if (filter[1] != null)
							filter[2] = new NotFilter(f);
						else {
							filter[0] = new NotFilter(f);

						}
						JOptionPane.showMessageDialog(new JFrame(), "The Filter by not Id is Apply ");
					}
				}
			}
		});
		add(Apply_1);
		
		JLabel lblFilterId = new JLabel("Filter ID");
		springLayout.putConstraint(SpringLayout.NORTH, txt_id, 45, SpringLayout.SOUTH, lblFilterId);
		springLayout.putConstraint(SpringLayout.NORTH, lblFilterId, 41, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFilterId, 0, SpringLayout.WEST, txt_id);
		springLayout.putConstraint(SpringLayout.SOUTH, lblFilterId, 64, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblFilterId, 260, SpringLayout.WEST, this);
		lblFilterId.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblFilterId);
		
		JLabel lblId = new JLabel("ID:");
		springLayout.putConstraint(SpringLayout.NORTH, lblId, -2, SpringLayout.NORTH, txt_id);
		springLayout.putConstraint(SpringLayout.WEST, lblId, -59, SpringLayout.WEST, txt_id);
		springLayout.putConstraint(SpringLayout.SOUTH, lblId, 1, SpringLayout.SOUTH, txt_id);
		springLayout.putConstraint(SpringLayout.EAST, lblId, -6, SpringLayout.WEST, txt_id);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblId);

	}

}
