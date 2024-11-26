package es.studium.practica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

public class MenuArticulos extends JFrame 
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
					MenuArticulos frame = new MenuArticulos();
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
	
	public MenuArticulos() {
		setTitle("Menú Artículos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(panel);
		panel.setLayout(null);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBackground(new Color(255, 153, 255));
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaArticulos altaArticulos = new AltaArticulos();
				altaArticulos.setVisible(true);
				setVisible(false);
			}
		});
		btnAlta.setBounds(62, 36, 116, 34);
		panel.add(btnAlta);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBackground(new Color(255, 153, 204));
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaArticulos bajaArticulos = new BajaArticulos();
				bajaArticulos.setVisible(true);
				setVisible(false);
			}
		});
		btnBaja.setBounds(240, 36, 116, 34);
		panel.add(btnBaja);
		
		JButton btnModificacion = new JButton("Modificación");
		btnModificacion.setBackground(new Color(255, 153, 153));
		btnModificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirArticulo elegirArticulo = new ElegirArticulo();
				elegirArticulo.setVisible(true);
				setVisible(false);
			}
		});
		btnModificacion.setBounds(62, 117, 116, 34);
		panel.add(btnModificacion);
		
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.setBackground(new Color(255, 153, 102));
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaArticulos consultaArticulos = new ConsultaArticulos();
				consultaArticulos.setVisible(true);
				setVisible(false);
			}
		});
		btnConsulta.setBounds(240, 117, 116, 34);
		panel.add(btnConsulta);
		
		JButton btnVolver = new JButton("Volver Menú");
		btnVolver.setBackground(new Color(255, 153, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(160, 189, 110, 34);
		panel.add(btnVolver);
	}
}