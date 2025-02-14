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
import java.awt.Color;
import java.awt.Font;

/**
 * Ventana para modificar los detalles de un artículo en la aplicación.
 * <p>Esta ventana permite al usuario modificar la descripción, precio y cantidad de un artículo existente.
 * También permite limpiar los campos de texto o regresar al menú principal.</p>
 * 
 * <p><strong>Autor:</strong> Raúl Santos Ruiz</p>
 * <p><strong>Versión:</strong> 1.0</p>
 * <p><strong>Fecha:</strong> 2025-02-14</p>
 * 
 * @see Datos
 */
public class ModificacionArticulo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextField txtId;  // txtId como variable de instancia
    private Datos datos = new Datos();

    /**
     * Método principal que lanza la aplicación.
     * <p>Este método ejecuta la aplicación y muestra la ventana de modificación del artículo.</p>
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ModificacionArticulo frame = new ModificacionArticulo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor que inicializa la interfaz de usuario para la modificación del artículo.
     * <p>Este constructor configura la ventana y los campos necesarios para modificar la información de un artículo.
     * También agrega los botones para aceptar los cambios, limpiar los campos o volver al menú principal.</p>
     */
    public ModificacionArticulo() {
        setTitle("Modificación Artículo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        panel = new JPanel();
        panel.setBackground(new Color(204, 255, 255));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);

        setContentPane(panel);
        panel.setLayout(null);

        // Etiquetas para los campos de texto
        JLabel lblId = new JLabel("ID: ");
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblId.setBounds(10, 32, 79, 14);
        panel.add(lblId);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblDescripcion.setBounds(10, 72, 79, 14);
        panel.add(lblDescripcion);

        JLabel lblPrecio = new JLabel("Precio (ej: 19.99):");
        lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPrecio.setBounds(10, 112, 105, 14);
        panel.add(lblPrecio);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCantidad.setBounds(10, 152, 66, 14);
        panel.add(lblCantidad);

        // Campos de texto para ingresar los datos del artículo
        txtId = new JTextField();
        txtId.setColumns(10);
        txtId.setBounds(125, 30, 282, 20);
        txtId.setEditable(false);  // El campo ID no es editable
        panel.add(txtId);

        txtDescripcion = new JTextField();
        txtDescripcion.setColumns(10);
        txtDescripcion.setBounds(125, 70, 282, 20);
        panel.add(txtDescripcion);

        txtPrecio = new JTextField();
        txtPrecio.setColumns(10);
        txtPrecio.setBounds(125, 110, 225, 20);
        panel.add(txtPrecio);

        txtCantidad = new JTextField();
        txtCantidad.setColumns(10);
        txtCantidad.setBounds(125, 150, 282, 20);
        panel.add(txtCantidad);

        // Botón para aceptar la modificación del artículo
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(new Color(51, 204, 204));
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btnAceptar)) {
                    // Conexión a la base de datos y modificación del artículo
                    datos.conectar();
                    boolean modificacionCorrecta = datos.modificacionArticulos(
                            txtDescripcion.getText(), 
                            txtPrecio.getText(), 
                            txtCantidad.getText(), 
                            txtId.getText()
                    );
                    datos.desconectar();

                    if (modificacionCorrecta) {
                        new MensajeModificacionArticulo().setVisible(true);
                        setVisible(false);
                    } else {
                        System.err.println("Error al modificar el artículo.");
                    }
                }
            }
        });
        btnAceptar.setBounds(26, 208, 89, 23);
        panel.add(btnAceptar);

        // Botón para limpiar los campos de texto
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
        btnLimpiar.setBounds(170, 208, 89, 23);
        panel.add(btnLimpiar);

        // Botón para volver al menú principal
        JButton btnVolver = new JButton("Volver Menú");
        btnVolver.setBackground(new Color(255, 153, 0));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Volver al menú principal
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
                setVisible(false);
            }
        });
        btnVolver.setBounds(303, 208, 104, 23);
        panel.add(btnVolver);

        // Etiqueta para mostrar el símbolo de Euro junto al campo de precio
        JLabel lblEuro = new JLabel("€");
        lblEuro.setBounds(361, 113, 46, 14);
        panel.add(lblEuro);
    }

    /**
     * Método que recibe los datos del artículo a modificar y los coloca en los campos correspondientes.
     * 
     * @param id El ID del artículo.
     * @param descripcion La descripción del artículo.
     * @param precio El precio del artículo.
     * @param cantidad La cantidad del artículo.
     */
    public void recibirDato(String id, String descripcion, String precio, String cantidad) {
        txtId.setText(id);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(precio);
        txtCantidad.setText(cantidad);
    }
}
