package User;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.BD;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Window.Type;
import java.awt.Color;

public class annonces extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					annonces frame = new annonces();
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
	public annonces() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Annonces Scraping");
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
		model.addColumn("Nom Compagnie");
		model.addColumn("Date");
		model.addColumn("Profil");
		model.addColumn("Nbr Post");
		model.addColumn("Ville");
		model.addColumn("Région");
		model.addColumn("Secteur");
		model.addColumn("Domaine");
		model.addColumn("Niveau d'étude");
		model.addColumn("Type Contrat");
		model.addColumn("Niveau Experience");
		model.addColumn("j2ee");
		model.addColumn("jquery");
		model.addColumn("css");
		model.addColumn("git");
		model.addColumn("php");
		model.addColumn("laravel");
		model.addColumn("symfony");
		model.addColumn("springboot");
		model.addColumn("maven");
		model.addColumn("docker");
		model.addColumn("jenkins");
		model.addColumn("java");
		model.addColumn("html");
		model.addColumn("mariadb");
		model.addColumn("mongodb");
		model.addColumn("mysql");
		model.addColumn("ux");
		model.addColumn("ui");
		model.addColumn("angular");
		model.addColumn("react");
		model.addColumn("rest");
		model.addColumn("bootstrap");
		model.addColumn("agil");
		model.addColumn("swift");
		model.addColumn("javascript");
		model.addColumn("c");
		model.addColumn("c++");
		model.addColumn("poo");
		model.addColumn("ruby");
		model.addColumn("c#");
		model.addColumn("sap");
		model.addColumn("express");
		model.addColumn("nestjs");
		model.addColumn("node");

		try {
				String sql="SELECT * FROM annonce";
				Statement smt = con.createStatement();
				ResultSet rs = smt.executeQuery(sql);
				//model.addRow(new Object[] {"ID", "Nom Compagnie", "Date", "Profil", "Nbr_post","Ville","Region","Secteur","Domaine","NiveauEtude","Type Contrat", "NiveauExp", "j2ee","jquery","css","git","php","laravel","symfony","springboot","maven","docker","jenkins","java","html","mariadb","mongodb","mysql","ux","ui","angular","react","rest","bootstrap","agile","swift","javascript", "c","c++","poo","ruby","c#","sap","express","nestjs","node"});
				
				while (rs.next())
				{
					model.addRow(new Object[] {rs.getInt("id_annonce"), rs.getString("nomCompagnie"), rs.getString("date"), rs.getString("profil"), rs.getInt("nbr_post"), rs.getString("ville"), rs.getString("region"), rs.getString("secteur"), rs.getString("domaine"), rs.getString("niveauEtude"), rs.getString("type_contrat"), rs.getString("Niveau_Experience"), rs.getInt("j2ee"), rs.getInt("jquery"), rs.getInt("css"), rs.getInt("git"), rs.getInt("php"), rs.getInt("laravel"), rs.getInt("symfony"), rs.getInt("springboot"), rs.getInt("maven"), rs.getInt("docker"), rs.getInt("jenkins"), rs.getInt("java"), rs.getInt("html"), rs.getInt("mariadb"), rs.getInt("mongodb"), rs.getInt("mysql"), rs.getInt("ux"), rs.getInt("ui"), rs.getInt("angular"), rs.getInt("react"), rs.getInt("rest"), rs.getInt("bootstrap"), rs.getInt("agile"), rs.getInt("swift"), rs.getInt("javascript"), rs.getInt("c"), rs.getInt("c++"), rs.getInt("poo"), rs.getInt("poo"), rs.getInt("ruby"), rs.getInt("c#"), rs.getInt("sap"), rs.getInt("express"), rs.getInt("nestjs"),rs.getInt("node")});
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
				new compagnies().setVisible(true);
			}
		});
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_6, 0, SpringLayout.NORTH, btnNewButton_5);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_6, 624, SpringLayout.EAST, btnNewButton_5);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_6, -10, SpringLayout.EAST, contentPane);
		btnNewButton_6.setIcon(new ImageIcon("..\\extractIdproj\\img\\forward.png"));
		contentPane.add(btnNewButton_6);

	}
}
