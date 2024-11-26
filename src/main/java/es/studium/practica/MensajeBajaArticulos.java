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

public class MensajeBajaArticulos extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private String idArticulo;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MensajeBajaArticulos frame = new MensajeBajaArticulos();
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
	
	public MensajeBajaArticulos() {
		setTitle("¿Segur@?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblPrincipal = new JLabel("¿Seguro de borrar?");
		lblPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrincipal.setBounds(154, 65, 128, 23);
		panel.add(lblPrincipal);
		
		JButton btnSi = new JButton("Sí");
		btnSi.setBackground(new Color(0, 204, 0));
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datos.conectar();
				datos.bajaArticulos(idArticulo);
				datos.desconectar();
				
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnSi.setBounds(81, 174, 89, 23);
		panel.add(btnSi);
		
		JButton btnNo = new JButton("No");
		btnNo.setBackground(new Color(255, 0, 51));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaArticulos bajaArticulos = new BajaArticulos();
				bajaArticulos.setVisible(true);
				setVisible(false);
			}
		});
		btnNo.setBounds(263, 174, 89, 23);
		panel.add(btnNo);
	}
	
	public void recibirId(String id) 
	{
		idArticulo = id;
	}
}