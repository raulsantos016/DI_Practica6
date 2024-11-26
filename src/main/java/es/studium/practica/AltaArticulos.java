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

public class AltaArticulos extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtCantidad;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	
	Datos datos = new Datos();
	
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
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(51, 204, 204));
		btnAceptar.addActionListener(new ActionListener() {
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
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(new Color(51, 255, 0));
		btnLimpiar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Limpiar los campos de texto
		        txtDescripcion.setText("");
		        txtPrecio.setText("");
		        txtCantidad.setText("");
		    }
		});
		btnLimpiar.setBounds(176, 227, 89, 23);
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
		btnVolver.setBounds(309, 227, 104, 23);
		panel.add(btnVolver);
		
		JLabel lblEuro = new JLabel("€");
		lblEuro.setBounds(367, 94, 46, 14);
		panel.add(lblEuro);
	}
}
