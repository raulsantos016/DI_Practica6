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
 * Clase que representa la ventana del menú principal de la aplicación.
 * Este menú permite al usuario elegir entre dos opciones principales:
 * acceder al menú de artículos o al menú de tickets.
 * 
 * <p>El menú principal se compone de dos botones que permiten al usuario navegar entre
 * las funcionalidades de artículos y tickets, accediendo a las ventanas correspondientes.</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class MenuPrincipal extends JFrame {

    /** 
     * UID serial para la serialización de la clase.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Panel principal donde se colocan los componentes de la interfaz.
     */
    private JPanel panel;

    /**
     * Método principal que lanza la aplicación y muestra el menú principal.
     * <p>Este método ejecuta la aplicación en un hilo de la interfaz gráfica utilizando
     * la cola de eventos de la plataforma y hace visible la ventana del menú principal.</p>
     * 
     * @param args Argumentos de la línea de comandos, que no se utilizan en este caso.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Crea y muestra la ventana del menú principal
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    // Captura cualquier excepción que ocurra al iniciar la ventana
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor de la clase `MenuPrincipal` que inicializa la interfaz de usuario del menú.
     * <p>Este constructor configura la ventana principal con el título adecuado, establece
     * su tamaño y añade los botones necesarios para navegar hacia los menús de artículos y tickets.</p>
     * 
     * Además, inicializa el panel principal y define los eventos que ocurrirán cuando el usuario
     * haga clic en los botones correspondientes.
     */
    public MenuPrincipal() {
        setTitle("Menú Principal");  // Establece el título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Define que se cierre al salir
        setBounds(100, 100, 450, 300);  // Establece la ubicación y tamaño de la ventana
        
        // Inicializa el panel principal con un color de fondo y bordes
        panel = new JPanel();
        panel.setBackground(new Color(204, 255, 255));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla

        setContentPane(panel);  // Asigna el panel como contenido de la ventana
        panel.setLayout(null);  // Establece el diseño del panel a uno de disposición absoluta

        // Crea y configura el botón para acceder al menú de Artículos
        JButton btnArticulos = new JButton("Artículos");
        btnArticulos.setBackground(new Color(153, 153, 255));  // Establece el color de fondo del botón
        btnArticulos.addActionListener(new ActionListener() {
            /**
             * Acción que se ejecuta al hacer clic en el botón de Artículos.
             * Este método abre la ventana del menú de Artículos y oculta la ventana principal.
             * 
             * @param e El evento de acción generado al hacer clic en el botón.
             */
            public void actionPerformed(ActionEvent e) {
                // Muestra la ventana de MenuArticulos
                MenuArticulos menuArticulos = new MenuArticulos();
                menuArticulos.setVisible(true);
                setVisible(false);  // Oculta la ventana principal
            }
        });
        btnArticulos.setBounds(78, 99, 106, 35);  // Define la ubicación y tamaño del botón
        panel.add(btnArticulos);  // Añade el botón al panel

        // Crea y configura el botón para acceder al menú de Tickets
        JButton btnTickets = new JButton("Tickets");
        btnTickets.setBackground(new Color(204, 153, 255));  // Establece el color de fondo del botón
        btnTickets.addActionListener(new ActionListener() {
            /**
             * Acción que se ejecuta al hacer clic en el botón de Tickets.
             * Este método abre la ventana del menú de Tickets y oculta la ventana principal.
             * 
             * @param e El evento de acción generado al hacer clic en el botón.
             */
            public void actionPerformed(ActionEvent e) {
                // Muestra la ventana de MenuTickets
                MenuTickets menuTickets = new MenuTickets();
                menuTickets.setVisible(true);
                setVisible(false);  // Oculta la ventana principal
            }
        });
        btnTickets.setBounds(243, 99, 106, 35);  // Define la ubicación y tamaño del botón
        panel.add(btnTickets);  // Añade el botón al panel
    }
}
