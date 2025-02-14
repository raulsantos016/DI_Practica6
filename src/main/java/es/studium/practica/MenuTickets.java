package es.studium.practica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

/**
 * Ventana del menú de tickets de la aplicación.
 * <p>Este menú permite al usuario elegir entre las opciones de alta y consulta de tickets,
 * o volver al menú principal.</p>
 * 
 * <p>El menú está compuesto por tres botones que permiten navegar entre las distintas funcionalidades:
 * alta de tickets, consulta de tickets y regreso al menú principal.</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class MenuTickets extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane; // Panel que contiene los componentes de la interfaz gráfica.

    /**
     * Método principal que lanza la aplicación.
     * <p>Este método ejecuta la aplicación y muestra el menú de tickets.</p>
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Lanza la ventana del menú de tickets
                    MenuTickets frame = new MenuTickets();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor que inicializa la interfaz de usuario del menú de tickets.
     * <p>Este constructor configura la ventana del menú de tickets y añade botones
     * para acceder a las opciones de alta, consulta y volver al menú principal.</p>
     */
    public MenuTickets() {
        // Configuración básica de la ventana
        setTitle("Menú Tickets"); // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        setBounds(100, 100, 450, 300); // Establece el tamaño y la posición inicial de la ventana
        contentPane = new JPanel(); // Panel que contendrá los botones
        contentPane.setBackground(new Color(204, 255, 255)); // Color de fondo del panel
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Bordes alrededor del panel
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        setContentPane(contentPane); // Establece el panel como contenido de la ventana
        contentPane.setLayout(null); // Utiliza un layout nulo para posicionar los componentes manualmente
        
        // Botón para acceder a la opción de Alta de tickets
        JButton btnNewButton = new JButton("Alta");
        btnNewButton.setBackground(new Color(255, 153, 255)); // Color de fondo del botón
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de AltaTickets al hacer clic en el botón
                AltaTickets altaTickets = new AltaTickets();
                altaTickets.setVisible(true); // Muestra la ventana de alta de tickets
                setVisible(false); // Oculta la ventana actual
            }
        });
        btnNewButton.setBounds(60, 85, 116, 36); // Posiciona y dimensiona el botón
        contentPane.add(btnNewButton); // Añade el botón al panel
        
        // Botón para acceder a la opción de Consulta de tickets
        JButton btnNewButton_1 = new JButton("Consulta");
        btnNewButton_1.setBackground(new Color(255, 153, 102)); // Color de fondo del botón
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de ConsultaTickets al hacer clic en el botón
                ConsultaTickets consultaTickets = new ConsultaTickets();
                consultaTickets.setVisible(true); // Muestra la ventana de consulta de tickets
                setVisible(false); // Oculta la ventana actual
            }
        });
        btnNewButton_1.setBounds(246, 85, 116, 36); // Posiciona y dimensiona el botón
        contentPane.add(btnNewButton_1); // Añade el botón al panel
        
        // Botón para regresar al menú principal
        JButton btnNewButton_2 = new JButton("Volver Menú");
        btnNewButton_2.setBackground(new Color(255, 153, 0)); // Color de fondo del botón
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de MenuPrincipal al hacer clic en el botón
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true); // Muestra la ventana del menú principal
                setVisible(false); // Oculta la ventana actual
            }
        });
        btnNewButton_2.setBounds(150, 184, 116, 36); // Posiciona y dimensiona el botón
        contentPane.add(btnNewButton_2); // Añade el botón al panel
    }
}
