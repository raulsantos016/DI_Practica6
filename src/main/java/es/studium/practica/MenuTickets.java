package es.studium.practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuTickets extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuTickets frame = new MenuTickets();
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
	public MenuTickets() {
		setTitle("Menú Tickets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Alta");
		btnNewButton.setBackground(new Color(255, 153, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaTickets altaTickets = new AltaTickets();
				altaTickets.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(60, 85, 116, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Consulta");
		btnNewButton_1.setBackground(new Color(255, 153, 102));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaTickets consultaTickets = new ConsultaTickets();
				consultaTickets.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(246, 85, 116, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Volver Menú");
		btnNewButton_2.setBackground(new Color(255, 153, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(150, 184, 116, 36);
		contentPane.add(btnNewButton_2);
	}

}
