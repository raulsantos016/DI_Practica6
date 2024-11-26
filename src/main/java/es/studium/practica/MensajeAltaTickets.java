package es.studium.practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class MensajeAltaTickets extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MensajeAltaTickets frame = new MensajeAltaTickets();
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
	public MensajeAltaTickets() {
		setTitle("Mensaje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBackground(new Color(204, 255, 255));
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 434, 261);
		contentPane.add(contentPane_1);
		
		JLabel lblTicketCreadoCorrectamente = new JLabel("Ticket creado correctamente");
		lblTicketCreadoCorrectamente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTicketCreadoCorrectamente.setBounds(127, 59, 190, 14);
		contentPane_1.add(lblTicketCreadoCorrectamente);
		
		JButton btnNewButton = new JButton("Volver Menú");
		btnNewButton.setBackground(new Color(255, 153, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(156, 199, 105, 23);
		contentPane_1.add(btnNewButton);
	}

}
