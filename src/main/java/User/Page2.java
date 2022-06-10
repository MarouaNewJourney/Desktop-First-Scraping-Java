package User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.SystemColor;

public class Page2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Page2 frame = new Page2();
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
	public Page2() {
		setType(Type.UTILITY);
		setTitle("General Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 402);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 114, SpringLayout.WEST, contentPane);
		panel.setBackground(new Color(153, 102, 255));
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, panel_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, -5, SpringLayout.SOUTH, panel_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, -64, SpringLayout.WEST, panel_1);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JButton btnNewButton_2 = new JButton("Data Scraping");
		btnNewButton_2.setIcon(new ImageIcon("..\\extractIdproj\\img\\icons8-zoom-selected-all-64.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Page3().setVisible(true);
			}
			
		});
		sl_panel.putConstraint(SpringLayout.WEST, btnNewButton_2, -231, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnNewButton_2, -10, SpringLayout.EAST, panel);
		btnNewButton_2.setBackground(new Color(240, 248, 255));
		sl_panel.putConstraint(SpringLayout.NORTH, btnNewButton_2, 104, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -121, SpringLayout.SOUTH, panel);
		panel.add(btnNewButton_2);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel_1, 20, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel_1, 419, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel_1, -40, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel_1, -96, SpringLayout.EAST, contentPane);
		contentPane.add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JButton btnNewButton_3 = new JButton("Machine Learning");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Algorithms().setVisible(true);
			}
		});
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setIcon(new ImageIcon("..\\extractIdproj\\img\\icons8-new-scene-64.png"));
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_3, -231, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnNewButton_3, -10, SpringLayout.EAST, panel_1);
		btnNewButton_3.setBackground(new Color(153, 102, 255));
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_3, 104, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnNewButton_3, -120, SpringLayout.SOUTH, panel_1);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Page1().setVisible(true);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -690, SpringLayout.EAST, contentPane);
		btnNewButton.setIcon(new ImageIcon("..\\extractIdproj\\img\\backward.png"));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				//new Page3().setVisible(true);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_1, 624, SpringLayout.EAST, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_1, -10, SpringLayout.EAST, contentPane);
		btnNewButton_1.setIcon(new ImageIcon("..\\extractIdproj\\img\\forward.png"));
		contentPane.add(btnNewButton_1);
	}
}
