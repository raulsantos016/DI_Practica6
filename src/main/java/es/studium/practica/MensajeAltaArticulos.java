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

public class MensajeAltaArticulos extends JFrame 
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
					MensajeAltaArticulos frame = new MensajeAltaArticulos();
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
	
	public MensajeAltaArticulos() {
		setTitle("Mensaje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblMensaje = new JLabel("Artículo creado correctamente");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMensaje.setBounds(122, 50, 201, 14);
		panel.add(lblMensaje);
		
		JButton btnVolver = new JButton("Volver Menú");
		btnVolver.setBackground(new Color(255, 153, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(162, 198, 105, 23);
		panel.add(btnVolver);
	}
}