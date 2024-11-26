package es.studium.practica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

public class MenuPrincipal extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	
	public MenuPrincipal() 
	{
		setTitle("Menú Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(panel);
		panel.setLayout(null);
		
		JButton btnArticulos = new JButton("Artículos");
		btnArticulos.setBackground(new Color(153, 153, 255));
		btnArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuArticulos menuArticulos = new MenuArticulos();
				menuArticulos.setVisible(true);
				setVisible(false);
			}
		});
		btnArticulos.setBounds(78, 99, 106, 35);
		panel.add(btnArticulos);
		
		JButton btnTickets = new JButton("Tickets");
		btnTickets.setBackground(new Color(204, 153, 255));
		btnTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuTickets menuTickets = new MenuTickets();
				menuTickets.setVisible(true);
				setVisible(false);
			}
		});
		btnTickets.setBounds(243, 99, 106, 35);
		panel.add(btnTickets);
	}
}