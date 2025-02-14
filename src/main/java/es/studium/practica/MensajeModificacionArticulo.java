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

/**
 * Ventana que muestra un mensaje de confirmación cuando un artículo ha sido modificado correctamente.
 * <p>Esta ventana informa al usuario que la modificación del artículo se ha completado
 * correctamente y ofrece la opción de regresar al menú principal.</p>
 * 
 * <p>La interfaz de usuario contiene una etiqueta con el mensaje de confirmación y un
 * botón para regresar al menú principal de la aplicación.</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class MensajeModificacionArticulo extends JFrame {

    private static final long serialVersionUID = 1L;  // Identificador único de la clase para la serialización.
    private JPanel panel;  // Panel principal donde se colocan los componentes de la ventana.

    /**
     * Método principal que lanza la aplicación.
     * 
     * <p>Este método es el punto de entrada de la aplicación. Crea una nueva instancia
     * de la ventana de confirmación y la hace visible.</p>
     * 
     * @param args Argumentos de la línea de comandos. No se utilizan en este caso.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MensajeModificacionArticulo frame = new MensajeModificacionArticulo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor que inicializa la ventana de mensaje de modificación exitosa.
     * <p>Este constructor configura la interfaz de usuario, que incluye el mensaje
     * de confirmación de la modificación y el botón "Volver Menú" para regresar al menú principal.</p>
     * 
     * <p>El constructor también establece las propiedades de la ventana, como el tamaño y el comportamiento,
     * y agrega los componentes visuales como la etiqueta de mensaje y el botón.</p>
     */
    public MensajeModificacionArticulo() {
        setTitle("Mensaje");  // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Comportamiento al cerrar la ventana
        setBounds(100, 100, 450, 300);  // Establece las dimensiones de la ventana
        
        // Panel principal de la ventana
        panel = new JPanel();
        panel.setBackground(new Color(204, 255, 255));  // Color de fondo
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));  // Bordes del panel
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla

        setContentPane(panel);  // Establece el panel como el contenido de la ventana
        panel.setLayout(null);  // Establece un layout nulo (posición manual de componentes)
        
        // Etiqueta con el mensaje de confirmación
        JLabel lblMensaje = new JLabel("Artículo modificado correctamente");
        lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));  // Fuente de la etiqueta
        lblMensaje.setBounds(114, 57, 242, 14);  // Posición y tamaño de la etiqueta
        panel.add(lblMensaje);  // Añade la etiqueta al panel
        
        // Botón para regresar al menú principal
        JButton btnVolver = new JButton("Volver Menú");
        btnVolver.setBackground(new Color(255, 153, 0));  // Color de fondo del botón
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción que se ejecuta al hacer clic en el botón
                // Regresar al menú principal
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);  // Muestra el menú principal
                setVisible(false);  // Cierra la ventana actual
            }
        });
        btnVolver.setBounds(162, 198, 115, 23);  // Posición y tamaño del botón
        panel.add(btnVolver);  // Añade el botón al panel
    }
}
