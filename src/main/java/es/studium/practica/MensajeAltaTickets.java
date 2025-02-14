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
 * Ventana que muestra un mensaje informando que un ticket ha sido creado correctamente.
 * Esta ventana contiene un mensaje de confirmación y un botón para volver al menú principal de la aplicación.
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class MensajeAltaTickets extends JFrame {

    private static final long serialVersionUID = 1L;
    
    /** Panel principal de la ventana */
    private JPanel contentPane;

    /**
     * Método principal que lanza la aplicación.
     * Este método es el punto de entrada para ejecutar la aplicación y mostrar la ventana de confirmación.
     * 
     * @param args Argumentos de la línea de comandos que pueden ser utilizados para iniciar la aplicación con parámetros específicos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MensajeAltaTickets frame = new MensajeAltaTickets();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor que inicializa la ventana de mensaje para la creación exitosa de un ticket.
     * Configura la interfaz de usuario, mostrando un mensaje de confirmación y un botón para regresar al menú principal.
     */
    public MensajeAltaTickets() {
        setTitle("Mensaje");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        // Panel principal que contiene todos los elementos visuales
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Panel secundario con fondo colorido
        JPanel contentPane_1 = new JPanel();
        contentPane_1.setBackground(new Color(204, 255, 255));
        contentPane_1.setLayout(null);
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane_1.setBounds(0, 0, 434, 261);
        contentPane.add(contentPane_1);
        
        // Etiqueta que muestra el mensaje de confirmación
        JLabel lblTicketCreadoCorrectamente = new JLabel("Ticket creado correctamente");
        lblTicketCreadoCorrectamente.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTicketCreadoCorrectamente.setBounds(127, 59, 190, 14);
        contentPane_1.add(lblTicketCreadoCorrectamente);
        
        // Botón para volver al menú principal
        JButton btnNewButton = new JButton("Volver Menú");
        btnNewButton.setBackground(new Color(255, 153, 0));
        
        // Acción a realizar al hacer clic en el botón
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Al hacer clic en "Volver Menú", se abre el menú principal y se cierra esta ventana
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true); // Muestra el menú principal
                setVisible(false); // Oculta la ventana de confirmación
            }
        });
        
        btnNewButton.setBounds(156, 199, 105, 23);
        contentPane_1.add(btnNewButton);
    }
}
