package User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.BD;

public class regressiongen extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					regressiongen frame = new regressiongen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public regressiongen() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Regression II");
		setBounds(100, 100, 780, 508);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		BD bdcon = new BD();
		Connection con = bdcon.connexion();
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.setRowSelectionAllowed(false);
		model.addColumn("ID");
		model.addColumn("Nbr Postes");
		model.addColumn("Compagnie");
		model.addColumn("Secteur");
		model.addColumn("Region");
		model.addColumn("Ville");
		model.addColumn("Domaine");
		model.addColumn("Profil");
		model.addColumn("Date");


		try {
				String sql="SELECT * FROM regressiongen";
				Statement smt = con.createStatement();
				ResultSet rs = smt.executeQuery(sql);
				
				while (rs.next())
				{
					model.addRow(new Object[] {rs.getInt("id"), rs.getInt("nbrPostes"), rs.getString("Compagnie"), rs.getString("secteur"), rs.getString("Region"), rs.getString("ville"), rs.getString("domaine"), rs.getString("profil"), rs.getDate("date")});
				}
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		//contentPane.add(table, BorderLayout.CENTER);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane pane = new JScrollPane(table);
		getContentPane().add(pane, BorderLayout.CENTER);
		
		JButton btnNewButton_5 = new JButton("");
		sl_contentPane.putConstraint(SpringLayout.WEST, pane, 73, SpringLayout.EAST, btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new menutable().setVisible(true);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_5, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_5, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_5, -690, SpringLayout.EAST, contentPane);
		btnNewButton_5.setIcon(new ImageIcon("..\\extractIdproj\\img\\backward.png"));
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new annonces().setVisible(true);
			}
		});
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_6, 0, SpringLayout.NORTH, btnNewButton_5);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_6, 624, SpringLayout.EAST, btnNewButton_5);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_6, -10, SpringLayout.EAST, contentPane);
		btnNewButton_6.setIcon(new ImageIcon("..\\extractIdproj\\img\\forward.png"));
		contentPane.add(btnNewButton_6);

	}

}
