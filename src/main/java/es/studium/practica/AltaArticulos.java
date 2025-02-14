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
import java.awt.Color;

/**
 * Clase que representa la interfaz gráfica para dar de alta artículos.
 * Permite ingresar la descripción, el precio y la cantidad de un artículo
 * y almacenarlo en la base de datos.
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2024-02-14
 */
public class AltaArticulos extends JFrame 
{
	private static final long serialVersionUID = 1L;

	/** Panel principal de la ventana. */
	private JPanel panel;

	/** Campo de texto para la descripción del artículo. */
	private JTextField txtDescripcion;

	/** Campo de texto para el precio del artículo. */
	private JTextField txtPrecio;

	/** Campo de texto para la cantidad del artículo. */
	private JTextField txtCantidad;

	/** Objeto de la clase Datos para gestionar la conexión a la base de datos. */
	Datos datos = new Datos();

	/**
	 * Método principal que inicia la aplicación.
	 * @param args Argumentos de la línea de comandos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaArticulos frame = new AltaArticulos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor de la clase AltaArticulos.
	 * Inicializa la ventana y sus componentes gráficos.
	 */
	public AltaArticulos() {
		setTitle("Alta Artículo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(panel);
		panel.setLayout(null);
		
		// Etiquetas de la interfaz
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescripcion.setBounds(10, 47, 79, 14);
		panel.add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio (ej: 19.99):");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecio.setBounds(10, 93, 111, 14);
		panel.add(lblPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidad.setBounds(10, 138, 66, 14);
		panel.add(lblCantidad);
		
		// Campos de texto
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(131, 45, 282, 20);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(131, 91, 225, 20);
		panel.add(txtPrecio);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(131, 136, 282, 20);
		panel.add(txtCantidad);
		
		// Botón Aceptar
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(51, 204, 204));
		btnAceptar.addActionListener(new ActionListener() {
			/**
			 * Acción realizada al presionar el botón "Aceptar". 
			 * Registra el artículo en la base de datos si los datos son correctos.
			 * Muestra un mensaje de éxito o error según el resultado.
			 * @param e Evento de acción
			 */
			public void actionPerformed(ActionEvent e) {
			    if (e.getSource() == btnAceptar) {
			        datos.conectar();
			        boolean altaCorrecta = datos.altaArticulos(
			            txtDescripcion.getText(),
			            txtPrecio.getText(),
			            txtCantidad.getText()
			        );

			        if (altaCorrecta) {
			            new MensajeAltaArticulos().setVisible(true);
			            setVisible(false);
			        } else {
			            System.err.println("Error al registrar el artículo.");
			        }

			        datos.desconectar();
			    }
			}
		});
		btnAceptar.setBounds(32, 227, 89, 23);
		panel.add(btnAceptar);
		
		// Botón Limpiar
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(new Color(51, 255, 0));
		btnLimpiar.addActionListener(new ActionListener() {
		    /**
		     * Limpia los campos de texto de la interfaz.
		     * @param e Evento de acción
		     */
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        txtDescripcion.setText("");
		        txtPrecio.setText("");
		        txtCantidad.setText("");
		    }
		});
		btnLimpiar.setBounds(176, 227, 89, 23);
		panel.add(btnLimpiar);
		
		// Botón Volver al menú principal
		JButton btnVolver = new JButton("Volver Menú");
		btnVolver.setBackground(new Color(255, 153, 0));
		btnVolver.addActionListener(new ActionListener() {
		    /**
		     * Cierra la ventana actual y abre el menú principal.
		     * @param e Evento de acción
		     */
		    public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
		    }
		});
		btnVolver.setBounds(309, 227, 106, 23);
		panel.add(btnVolver);
		
		// Símbolo del euro junto al precio
		JLabel lblEuro = new JLabel("€");
		lblEuro.setBounds(367, 94, 46, 14);
		panel.add(lblEuro);
	}
}
