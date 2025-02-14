package es.studium.practica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Choice;
import java.awt.Color;

/**
 * Clase que representa la ventana de alta de tickets.
 * Permite registrar tickets con fecha, total y artículos asociados.
 * Proporciona una interfaz gráfica con campos para introducir datos y botones para gestionar la entrada.
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2024-02-14
 */
public class AltaTickets extends JFrame 
{
	private static final long serialVersionUID = 1L;

	/**
	 * Panel principal de la ventana.
	 */
	private JPanel panel;

	/**
	 * Campo de texto para la fecha del ticket.
	 */
	private JTextField txtFecha;

	/**
	 * Campo de texto para el precio total del ticket.
	 */
	private JTextField txtTotal;

	/**
	 * Desplegable para seleccionar artículos.
	 */
	private Choice choArticulos;

	/**
	 * Campo de texto para la cantidad de artículos en el ticket.
	 */
	private JTextField txtCantidad;

	/**
	 * Objeto de la clase Datos para la conexión y gestión de la base de datos.
	 */
	Datos datos = new Datos();

	/**
	 * Método principal para ejecutar la aplicación.
	 * Lanza la ventana de alta de tickets.
	 * 
	 * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaTickets frame = new AltaTickets();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor de la clase AltaTickets.
	 * Inicializa la interfaz y sus componentes gráficos.
	 * Configura los eventos de los botones y carga los artículos desde la base de datos.
	 */
	public AltaTickets() {
		setTitle("Alta Tickets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(panel);
		panel.setLayout(null);

		// Etiqueta y campo de fecha
		JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setBounds(26, 25, 130, 14);
		panel.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(170, 25, 225, 20);
		panel.add(txtFecha);

		// Etiqueta y campo de precio total
		JLabel lblPrecioTotal = new JLabel("Precio total (ej: 19.99):");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecioTotal.setBounds(26, 75, 130, 14);
		panel.add(lblPrecioTotal);

		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(170, 75, 225, 20);
		panel.add(txtTotal);

		// Etiqueta y selector de artículos
		JLabel lblArticulos = new JLabel("Artículos:");
		lblArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArticulos.setBounds(26, 125, 50, 14);
		panel.add(lblArticulos);

		choArticulos = new Choice();
		choArticulos.setSize(307, 20);
		choArticulos.setLocation(88, 125);
		panel.add(choArticulos);

		// Etiqueta y campo de cantidad de artículos
		JLabel lblCantidad = new JLabel("Número de artículos:");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidad.setBounds(26, 175, 130, 14);
		panel.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(170, 175, 225, 20);
		panel.add(txtCantidad);

		// Botón Aceptar
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(0, 204, 204));
		btnAceptar.addActionListener(new ActionListener() {
		    /**
		     * Acción que se ejecuta al pulsar el botón "Aceptar".
		     * Registra un nuevo ticket con los datos ingresados.
		     * 
		     * @param e Evento de acción del botón.
		     */
		    public void actionPerformed(ActionEvent e) {
		        // Implementación del registro del ticket
		    }
		});
		btnAceptar.setBounds(26, 208, 89, 23);
		panel.add(btnAceptar);

		// Botón Limpiar
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(new Color(0, 255, 0));
		btnLimpiar.addActionListener(new ActionListener() {
			/**
			 * Acción que se ejecuta al pulsar el botón "Limpiar".
			 * Borra el contenido de los campos de entrada.
			 * 
			 * @param e Evento de acción del botón.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				txtFecha.setText("");
				txtTotal.setText("");
				choArticulos.select(0);
			}
		});
		btnLimpiar.setBounds(170, 208, 89, 23);
		panel.add(btnLimpiar);

		// Botón Volver al Menú
		JButton btnVolver = new JButton("Volver Menú");
		btnVolver.setBackground(new Color(255, 153, 0));
		btnVolver.addActionListener(new ActionListener() {
			/**
			 * Acción que se ejecuta al pulsar el botón "Volver Menú".
			 * Redirige a la ventana principal del menú.
			 * 
			 * @param e Evento de acción del botón.
			 */
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(303, 208, 106, 23);
		panel.add(btnVolver);

		// Etiqueta de símbolo de euro
		JLabel lblEuro = new JLabel("€");
		lblEuro.setBounds(405, 76, 19, 14);
		panel.add(lblEuro);

		// Cargar los artículos en el selector desde la base de datos
		datos.conectar();
		String[] elementos = datos.rellenarChoiceArticulos();
		datos.desconectar();
		for (String elemento : elementos) 
		{
			choArticulos.add(elemento);
		}
	}
}
