package es.studium.practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;

public class BajaArticulos extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Choice choArticulos;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaArticulos frame = new BajaArticulos();
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

	public BajaArticulos() {
		setTitle("Baja Artículo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(panel);
		panel.setLayout(null);
		
		choArticulos = new Choice();
		choArticulos.setSize(350, 20);
		choArticulos.setLocation(40, 112);
		panel.add(choArticulos);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(0, 204, 204));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choArticulos.getSelectedIndex() == 0) {
					// Mostrar el error en la consola
					System.err.println("Error: No se pudo seleccionar el artículo para eliminar. La opción ingresada no es válida.");
				} else {
					// Obtener el artículo seleccionado del Choice
					String elementoSeleccionadoId = choArticulos.getSelectedItem().split(" - ")[0];
					
					// Crear una instancia de MenuModificacionArticulo
					MensajeBajaArticulos frame = new MensajeBajaArticulos();

					// Pasar la información seleccionada a MenuModificacionArticulo
					frame.recibirId(elementoSeleccionadoId);

					// Mostrar la ventana de MenuModificacionArticulo
					frame.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnAceptar.setBounds(69, 196, 89, 23);
		panel.add(btnAceptar);

		JButton btnVolver = new JButton("Volver Menú");
		btnVolver.setBackground(new Color(255, 153, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(252, 196, 106, 23);
		panel.add(btnVolver);

		JLabel lblPrincipal = new JLabel("Elegir Artículo a borrar:");
		lblPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrincipal.setBounds(147, 43, 148, 14);
		panel.add(lblPrincipal);

		datos.conectar();
		String[] elementos = datos.rellenarChoiceArticulos();
		datos.desconectar();
		for (String elemento : elementos) 
		{
			choArticulos.add(elemento);
		}
	}
}