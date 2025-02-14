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
 * Ventana de confirmación para la eliminación de un artículo.
 * <p>Esta ventana pregunta al usuario si está seguro de eliminar un artículo y ejecuta
 * la acción correspondiente dependiendo de la respuesta (Sí o No).</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class MensajeBajaArticulos extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Panel principal de la ventana
    private JPanel panel;
    
    // ID del artículo que se desea eliminar
    private String idArticulo;  

    /**
     * Método principal que lanza la aplicación y muestra la ventana de confirmación.
     * Este método inicializa y hace visible la ventana de confirmación para eliminar un artículo.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MensajeBajaArticulos frame = new MensajeBajaArticulos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor que inicializa la ventana de confirmación de baja del artículo.
     * <p>Este constructor configura la interfaz de usuario, que incluye el mensaje
     * de confirmación y los botones "Sí" y "No" para la respuesta del usuario. Al presionar 
     * el botón "Sí", se elimina el artículo; si se presiona "No", se vuelve a la ventana anterior.</p>
     */
    Datos datos = new Datos();
    
    public MensajeBajaArticulos() {
        // Configuración de la ventana
        setTitle("¿Segur@?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        // Panel principal de la ventana
        panel = new JPanel();
        panel.setBackground(new Color(204, 255, 255));  // Color de fondo del panel
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla

        setContentPane(panel);
        panel.setLayout(null);
        
        // Etiqueta con la pregunta de confirmación
        JLabel lblPrincipal = new JLabel("¿Seguro de borrar?");
        lblPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrincipal.setBounds(154, 65, 128, 23);
        panel.add(lblPrincipal);
        
        // Botón "Sí" para confirmar la eliminación del artículo
        JButton btnSi = new JButton("Sí");
        btnSi.setBackground(new Color(0, 204, 0));  // Color verde para indicar afirmación
        btnSi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Conectar a la base de datos y ejecutar la eliminación del artículo
                datos.conectar();
                datos.bajaArticulos(idArticulo);  // Realizar la baja del artículo
                datos.desconectar();
                
                // Regresar al menú principal después de eliminar el artículo
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
                setVisible(false);  // Ocultar la ventana actual
            }
        });
        btnSi.setBounds(81, 174, 89, 23);
        panel.add(btnSi);
        
        // Botón "No" para cancelar la eliminación y volver a la vista de baja de artículos
        JButton btnNo = new JButton("No");
        btnNo.setBackground(new Color(255, 0, 51));  // Color rojo para indicar negación
        btnNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Regresar a la ventana de baja de artículos sin hacer cambios
                BajaArticulos bajaArticulos = new BajaArticulos();
                bajaArticulos.setVisible(true);
                setVisible(false);  // Ocultar la ventana actual
            }
        });
        btnNo.setBounds(263, 174, 89, 23);
        panel.add(btnNo);
    }
    
    /**
     * Método para recibir el ID del artículo a eliminar desde la ventana anterior.
     * Este método es utilizado para pasar el ID del artículo seleccionado por el usuario 
     * en la ventana de baja de artículos.
     * 
     * @param id El ID del artículo que será eliminado.
     */
    public void recibirId(String id) {
        idArticulo = id;
    }
}
