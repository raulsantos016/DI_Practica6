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
 * Ventana que muestra un mensaje confirmando que un artículo ha sido creado correctamente.
 * <p>Esta ventana contiene un mensaje informativo y un botón para volver al menú principal.</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class MensajeAltaArticulos extends JFrame {

    // UID de la clase para la serialización
    private static final long serialVersionUID = 1L;
    
    // Panel que contiene todos los elementos gráficos
    private JPanel panel;

    /**
     * Método principal que lanza la aplicación.
     * <p>Este método es el punto de entrada que inicia la ventana de mensaje para la creación exitosa de un artículo.</p>
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Crear e inicializar la ventana
                    MensajeAltaArticulos frame = new MensajeAltaArticulos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor que inicializa la ventana de mensaje para la creación exitosa de un artículo.
     * <p>Este constructor configura la interfaz de usuario, incluyendo el mensaje de confirmación
     * y el botón para volver al menú principal.</p>
     * <p>Establece el título de la ventana, el tamaño, el fondo del panel y el contenido de la interfaz.</p>
     */
    public MensajeAltaArticulos() {
        // Título de la ventana
        setTitle("Mensaje");
        
        // Configuración del cierre de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Establece las dimensiones de la ventana
        setBounds(100, 100, 450, 300);
        
        // Panel principal con fondo de color
        panel = new JPanel();
        panel.setBackground(new Color(204, 255, 255));  // Color de fondo del panel
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));    // Bordes vacíos
        setLocationRelativeTo(null);                      // Centra la ventana en la pantalla

        setContentPane(panel);
        panel.setLayout(null);  // Desactiva el diseño automático y permite configurar manualmente las posiciones

        // Etiqueta con el mensaje de confirmación
        JLabel lblMensaje = new JLabel("Artículo creado correctamente");
        lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Configura la fuente del texto
        lblMensaje.setBounds(122, 50, 201, 14);  // Establece la posición y el tamaño de la etiqueta
        panel.add(lblMensaje);

        // Botón para volver al menú principal
        JButton btnVolver = new JButton("Volver Menú");
        btnVolver.setBackground(new Color(255, 153, 0));  // Color de fondo del botón
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Al hacer clic en "Volver Menú", se regresa al menú principal
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);  // Muestra el menú principal
                setVisible(false);  // Oculta la ventana actual
            }
        });
        btnVolver.setBounds(162, 198, 105, 23);  // Establece la posición y el tamaño del botón
        panel.add(btnVolver);
    }
}
