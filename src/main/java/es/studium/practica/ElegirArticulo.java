package es.studium.practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Color;

public class ElegirArticulo extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Choice choArticulos; // Hacer choArticulos accesible en toda la clase
	private Datos datos = new Datos();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElegirArticulo frame = new ElegirArticulo();
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

	public ElegirArticulo() {
		setTitle("Elegir Artículo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(panel);
		panel.setLayout(null);

		// Inicializar Choice
		choArticulos = new Choice();
		choArticulos.setSize(350, 20);
		choArticulos.setLocation(40, 112);
		panel.add(choArticulos);

		JLabel lblPrincipal = new JLabel("Elige un artículo:");
		lblPrincipal.setBounds(159, 80, 98, 14);
		panel.add(lblPrincipal);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(0, 204, 204));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choArticulos.getSelectedIndex() == 0) {
					// Mostrar el error en la consola
					System.err.println("Error: No se pudo seleccionar el artículo para modificar. La opción ingresada no es válida.");
				} else {
					// Obtener el artículo seleccionado del Choice
					String elementoSeleccionadoId = choArticulos.getSelectedItem().split(" - ")[0];
					String elementoSeleccionadoDescripcion = choArticulos.getSelectedItem().split(" - ")[1];
					String elementoSeleccionadoPrecio = choArticulos.getSelectedItem().split(" - ")[2];
					String elementoSeleccionadoCantidad = choArticulos.getSelectedItem().split(" - ")[3];

					// Crear una instancia de MenuModificacionArticulo
					ModificacionArticulo frame = new ModificacionArticulo();

					// Pasar la información seleccionada a MenuModificacionArticulo
					frame.recibirDato(elementoSeleccionadoId, elementoSeleccionadoDescripcion, elementoSeleccionadoPrecio, elementoSeleccionadoCantidad);

					// Mostrar la ventana de MenuModificacionArticulo
					frame.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnAceptar.setBounds(75, 177, 89, 23);
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
		btnVolver.setBounds(242, 177, 108, 23);
		panel.add(btnVolver);

		// Llenar el Choice con datos
		datos.conectar();
		String[] elementos = datos.rellenarChoiceArticulos();
		datos.desconectar();

		for (String elemento : elementos) {
			choArticulos.add(elemento);
		}
	}
}
