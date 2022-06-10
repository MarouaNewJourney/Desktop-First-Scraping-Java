package User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;


import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Page1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtWelcomeToSky;
	private JTextField txtMadeWithLove;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Page1 frame = new Page1();
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
	public Page1() {
		setType(Type.UTILITY);
		setTitle("Welcome");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 402);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		txtWelcomeToSky = new JTextField();
		txtWelcomeToSky.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtWelcomeToSky, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtWelcomeToSky, 68, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtWelcomeToSky, 46, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtWelcomeToSky, -59, SpringLayout.EAST, contentPane);
		txtWelcomeToSky.setBackground(new Color(153, 102, 255));
		txtWelcomeToSky.setEditable(false);
		txtWelcomeToSky.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 18));
		txtWelcomeToSky.setText("Welcome to Sky Scraper");
		txtWelcomeToSky.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtWelcomeToSky);
		txtWelcomeToSky.setColumns(10);
		
		txtMadeWithLove = new JTextField();
		txtMadeWithLove.setEditable(false);
		txtMadeWithLove.setForeground(new Color(0, 0, 0));
		sl_contentPane.putConstraint(SpringLayout.WEST, txtMadeWithLove, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtMadeWithLove, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtMadeWithLove, 0, SpringLayout.EAST, contentPane);
		txtMadeWithLove.setBackground(new Color(230, 230, 250));
		txtMadeWithLove.setHorizontalAlignment(SwingConstants.CENTER);
		txtMadeWithLove.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 11));
		txtMadeWithLove.setText("Made with love by Maroua and Rhita");
		contentPane.add(txtMadeWithLove);
		txtMadeWithLove.setColumns(10);
		
		JButton btnNewButton = new JButton("Let's get started !");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 223, SpringLayout.SOUTH, txtWelcomeToSky);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 203, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, -30, SpringLayout.NORTH, txtMadeWithLove);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -215, SpringLayout.EAST, contentPane);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Page2().setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(248, 248, 255));
		btnNewButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		contentPane.add(btnNewButton);
		
		JLabel img1 = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, img1, 21, SpringLayout.SOUTH, txtWelcomeToSky);
		sl_contentPane.putConstraint(SpringLayout.WEST, img1, 22, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, img1, -51, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, img1, -542, SpringLayout.EAST, contentPane);
		img1.setIcon(new ImageIcon("..\\extractIdproj\\img\\cloud.jpg"));
		contentPane.add(img1);
		
		lblNewLabel = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 19, SpringLayout.SOUTH, txtWelcomeToSky);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 21, SpringLayout.EAST, img1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -50, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -232, SpringLayout.EAST, contentPane);
		lblNewLabel.setIcon(new ImageIcon("..\\extractIdproj\\img\\2.jpg"));
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 6, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 21, SpringLayout.SOUTH, txtWelcomeToSky);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -48, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1, -15, SpringLayout.EAST, contentPane);
		lblNewLabel_1.setIcon(new ImageIcon("..\\extractIdproj\\img\\cloudrev.jpg"));
		contentPane.add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new info().setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("..\\extractIdproj\\img\\info.png"));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 0, SpringLayout.SOUTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_1, -29, SpringLayout.EAST, contentPane);
		contentPane.add(btnNewButton_1);
	}
}
