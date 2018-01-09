package GUI;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Filter.Filters;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class choose_filter_gui extends JPanel {
private	JButton And ;
	
	private JButton Or;
	
	/**
	 * @return the and
	 */
	public JButton getAnd() {
		return And;
	}

	/**
	 * @param and the and to set
	 */
	public void setAnd(JButton and) {
		And = and;
	}

	/**
	 * @return the or
	 */
	public JButton getOr() {
		return Or;
	}

	/**
	 * @param or the or to set
	 */
	public void setOr(JButton or) {
		Or = or;
	}

	/**
	 * Create the panel.
	 */
	public choose_filter_gui(Filters [] filter, int size) {
		aaaa(filter,  size);
	}
	public void aaaa(Filters [] filter, int size) {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		 And = new JButton("And");
		And.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(And);
		
		 Or = new JButton("Or");
		springLayout.putConstraint(SpringLayout.NORTH, Or, 134, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, Or, 222, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, And, 0, SpringLayout.NORTH, Or);
		springLayout.putConstraint(SpringLayout.EAST, And, -43, SpringLayout.WEST, Or);
		add(Or);

	}

}
