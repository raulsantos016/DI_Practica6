package es.studium.practica;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ConsultaTickets extends JFrame 
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
					ConsultaTickets frame = new ConsultaTickets();
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
	
	Datos datos = new Datos();
	
	public ConsultaTickets() {
		setTitle("Consulta Tickets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		textArea.setBounds(10, 25, 414, 162);
		textArea.setEditable(false);
		panel.add(textArea);
		
		JButton btnVolver = new JButton("Volver Menú");
		btnVolver.setBackground(new Color(255, 153, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(159, 212, 111, 23);
		panel.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 25, 414, 162);
		panel.add(scrollPane);
		
		datos.conectar();
		textArea.append(datos.consultaTickets());
		datos.desconectar();
	}
}