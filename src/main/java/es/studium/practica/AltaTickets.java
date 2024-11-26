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

public class AltaTickets extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField txtFecha;
	private JTextField txtTotal;
	private Choice choArticulos;
	private JTextField txtCantidad;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */

	Datos datos = new Datos();

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

		JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setBounds(26, 25, 130, 14);
		panel.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(170, 25, 225, 20);
		panel.add(txtFecha);

		JLabel lblPrecioTotal = new JLabel("Precio total (ej: 19.99):");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecioTotal.setBounds(26, 75, 130, 14);
		panel.add(lblPrecioTotal);

		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(170, 75, 225, 20);
		panel.add(txtTotal);

		JLabel lblArticulos = new JLabel("Artículos:");
		lblArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArticulos.setBounds(26, 125, 50, 14);
		panel.add(lblArticulos);

		choArticulos = new Choice();
		choArticulos.setSize(307, 20);
		choArticulos.setLocation(88, 125);
		panel.add(choArticulos);

		JLabel lblCantidad = new JLabel("Número de artículos:");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidad.setBounds(26, 175, 130, 14);
		panel.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(170, 175, 225, 20);
		panel.add(txtCantidad);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(0, 204, 204));
		btnAceptar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Comprobar si no se ha seleccionado un artículo válido
		            if (choArticulos.getSelectedIndex() == 0) {
		                System.err.println("Error: No se pudo seleccionar el artículo para registrar el ticket. La opción ingresada no es válida.");
		                return; // Salir del método si no es válido
		            }

		            // Validar si los campos de texto no están vacíos
		            if (txtFecha.getText().isEmpty() || txtTotal.getText().isEmpty() || txtCantidad.getText().isEmpty()) {
		                System.err.println("Error: Todos los campos deben estar completos.");
		                return; // Salir del método si no es válido
		            }

		            // Validar que txtCantidad contenga un número entero positivo
		            String cantidadArticulos = txtCantidad.getText();
		            try {
		                int cantidad = Integer.parseInt(cantidadArticulos);
		                if (cantidad <= 0) {
		                    System.err.println("Error: La cantidad debe ser un número entero positivo.");
		                    return; // Salir del método si no es válido
		                }
		            } catch (NumberFormatException ex) {
		                System.err.println("Error: La cantidad debe ser un número entero válido.");
		                return; // Salir del método si no es válido
		            }

		            // Conectar a la base de datos
		            datos.conectar();

		            // Alta del ticket
		            boolean altaCorrecta = datos.altaTickets(txtFecha.getText(), txtTotal.getText());
		            String ultimoIdTicket = datos.obtenerUltimoIdTicket();

		            // Alta de pertenecer al artículo
		            boolean altaCorrecta2 = datos.altaPertenecer(
		                choArticulos.getSelectedItem().split(" - ")[0],
		                ultimoIdTicket,
		                cantidadArticulos // Aquí usamos la cantidad ya validada
		            );

		            // Mostrar mensaje de éxito o error
		            if (altaCorrecta && altaCorrecta2) {
		                new MensajeAltaTickets().setVisible(true);
		                setVisible(false);
		            } else {
		                System.err.println("Error al registrar el ticket.");
		            }
		        } catch (Exception ex) {
		            // Manejo de excepciones
		            System.err.println("Ocurrió un error durante el proceso: " + ex.getMessage());
		        } finally {
		            // Asegurarse de desconectar siempre
		            datos.desconectar();
		        }
		    }
		});
		btnAceptar.setBounds(26, 208, 89, 23);
		panel.add(btnAceptar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(new Color(0, 255, 0));
		btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Limpiar los campos de texto
				txtFecha.setText("");
				txtTotal.setText("");
				choArticulos.select(0);
			}
		});
		btnLimpiar.setBounds(170, 208, 89, 23);
		panel.add(btnLimpiar);

		JButton btnVolver = new JButton("Volver Menú");
		btnVolver.setBackground(new Color(255, 153, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(303, 208, 104, 23);
		panel.add(btnVolver);

		JLabel lblEuro = new JLabel("€");
		lblEuro.setBounds(405, 76, 19, 14);
		panel.add(lblEuro);

		datos.conectar();
		String[] elementos = datos.rellenarChoiceArticulos();
		datos.desconectar();
		for (String elemento : elementos) 
		{
			choArticulos.add(elemento);
		}
	}
}