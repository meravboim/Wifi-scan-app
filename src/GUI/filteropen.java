package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;

import Filter.AndFilter;
import Filter.Filters;
import Filter.Makefilter;
import Filter.Operator;
import Filter.OrFilter;
import object.Database;
import object.Scan;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class filteropen extends JPanel {

	/**
	 * Create the panel.
	 */
	public filteropen(Filters[] filter, Connect c) {

		setBackground(new Color(250, 235, 215));
		setLayout(null);

		JLabel label = new JLabel("Number of Scan: " + c.data.getDatabase().size());
		label.setBackground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(42, 213, 253, 27);
		add(label);

		JLabel label_1 = new JLabel("Number of Macs: " + c.data.getHash_map().size());
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(42, 251, 228, 27);
		add(label_1);


		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFilters.setBounds(149, 46, 119, 23);
		add(lblFilters);

		JLabel lblChooseFiltersAnd = new JLabel("Choose Filters and operators and then play Start filtering");
		lblChooseFiltersAnd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChooseFiltersAnd.setBounds(21, 88, 400, 55);
		add(lblChooseFiltersAnd);

		JButton btnStartFiltering = new JButton("Start filtering");
		btnStartFiltering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("enter filter");
				 if (filter[1] == null) {
					Makefilter t = new Makefilter(filter[0]);
					ArrayList<Scan> temp = new ArrayList<Scan>();
					temp.addAll(t.filtering(c.data.getDatabase()));
					c.data.setDatabase(temp);
					t.filtering(c.data.getDatabase());
					try {
						c.write(filter[0]);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					System.out.println(filter[0].toString());
					lblChooseFiltersAnd.setText(filter[0].toString());

					filter[0] = null;
					filter[1] = null;
					filter[2] = null;
					System.out.println(" one filter ");
				}

				if (filter[filter.length - 1] != null) {
					Filters And = new AndFilter();
					Filters Or = new OrFilter();
					System.out.println("enter filter -1!=null");
System.out.println(filter[1].toString());
			if (filter[1].toString().equals("AndFilter [ null AND null]")
							|| filter[1].toString().equals("OrFilter [ null OR  null]")) {
					//if(filter[1].equals(And) || filter[1].equals(Or)) {
						System.out.println("And/or");
						Filters f1;
						System.out.println("enter filter And/or");

						if (filter[1].toString().equals("AndFilter [ null AND null]")) {
							System.out.println("enter And");
							f1 = new AndFilter(filter[0], filter[2]);
							Makefilter t = new Makefilter(f1);
							ArrayList<Scan> temp = new ArrayList<Scan>();
							t.filtering(c.data.getDatabase());
							temp.addAll(t.filtering(c.data.getDatabase()));
							try {
								c.write(f1);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							lblChooseFiltersAnd.setText(f1.toString());
							System.out.println(f1.toString());
							System.out.println(" And filter ");

							c.data.setDatabase(temp);
							filter[0] = null;
							filter[1] = null;
							filter[2] = null;

						} else if (filter[1].toString().equals("OrFilter [ null OR  null]")) {
							f1 = new OrFilter(filter[0], filter[2]);
							System.out.println(Arrays.toString(filter));
							Makefilter t = new Makefilter(f1);
							ArrayList<Scan> temp = new ArrayList<Scan>();
							temp.addAll(t.filtering(c.data.getDatabase()));
							c.data.setDatabase(temp);
							lblChooseFiltersAnd.setText(f1.toString());
							System.out.println(f1.toString());

							try {
								c.write(f1);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							filter[0] = null;
							filter[1] = null;
							filter[2] = null;
							System.out.println(" Or filter ");

						}

					}

				}

				label.setText("Number of Scan: " + c.data.getDatabase().size());
				label_1.setText("Number of Macs: " + c.data.getHash_map().size());

			}
		});
		btnStartFiltering.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnStartFiltering.setBackground(UIManager.getColor("Button.background"));
		btnStartFiltering.setBounds(249, 154, 119, 23);
		add(btnStartFiltering);

		JButton btnUndoFiltering = new JButton("Undo filtering");
		btnUndoFiltering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("prev size is "+ c.GETprev_databacs().getDatabase().size());
				if (c.prev.getDatabase().size() > 0) {
					c.setData(c.prev);		

				}
				label.setText("Number of Scan: " + c.data.getDatabase().size());
				label_1.setText("Number of Macs: " + c.data.getHash_map().size());

			}
		});
		btnUndoFiltering.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUndoFiltering.setBackground(UIManager.getColor("Button.background"));
		btnUndoFiltering.setBounds(69, 154, 119, 23);
		add(btnUndoFiltering);
		
		JButton btnReturnLastFilter = new JButton("return last filter");
		btnReturnLastFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Filters s= c.read();
				System.out.println(s.toString());
				Makefilter t= new Makefilter(s);
				ArrayList<Scan> temp = new ArrayList<Scan>();

		//		temp.addAll(t.filtering(c.getData().getDatabase()));
				c.data.setDatabase(temp);
				
			}
		});
		btnReturnLastFilter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReturnLastFilter.setBounds(301, 213, 137, 25);
		add(btnReturnLastFilter);
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//synchronized (c.data) {
					while (true) {

						label.setText("Number of Scan: " + c.data.getDatabase().size());
						label_1.setText("Number of Macs: " + c.data.getHash_map().size());
					}
				}
			//}

		});
		t.start();


	}
}
