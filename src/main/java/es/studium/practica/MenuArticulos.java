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
 * Ventana del menú principal de operaciones sobre artículos.
 * <p>Este menú permite al usuario seleccionar entre varias opciones relacionadas con los artículos:
 * Alta, Baja, Modificación, Consulta o regresar al menú principal.</p>
 * <p>Los botones permiten navegar hacia otras ventanas donde el usuario podrá realizar las diferentes operaciones sobre los artículos.</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class MenuArticulos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panel;

    /**
     * Método principal que lanza la aplicación.
     * <p>Este método ejecuta la aplicación y muestra el menú de artículos.</p>
     * <p>Este método es ejecutado automáticamente cuando se inicia el programa y crea la interfaz gráfica de la ventana.</p>
     * 
     * @param args Argumentos de la línea de comandos. No se usan en este caso.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Lanza la ventana de MenuArticulos
                    MenuArticulos frame = new MenuArticulos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor que inicializa la interfaz de usuario del menú de artículos.
     * <p>Este constructor configura la ventana del menú, añadiendo los botones correspondientes
     * para las acciones de alta, baja, modificación, consulta y regresar al menú principal.</p>
     * <p>Los botones permiten al usuario seleccionar una acción sobre los artículos de la tienda, como alta, baja, etc.</p>
     */
    public MenuArticulos() {
        setTitle("Menú Artículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        // Panel principal de la ventana
        panel = new JPanel();
        panel.setBackground(new Color(204, 255, 255));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla

        setContentPane(panel);
        panel.setLayout(null);

        // Botón para Alta de artículos
        JButton btnAlta = new JButton("Alta");
        btnAlta.setBackground(new Color(255, 153, 255));
        btnAlta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de AltaArticulos para agregar un nuevo artículo
                AltaArticulos altaArticulos = new AltaArticulos();
                altaArticulos.setVisible(true);
                setVisible(false); // Cierra la ventana actual
            }
        });
        btnAlta.setBounds(62, 36, 116, 34);
        panel.add(btnAlta);

        // Botón para Baja de artículos
        JButton btnBaja = new JButton("Baja");
        btnBaja.setBackground(new Color(255, 153, 204));
        btnBaja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de BajaArticulos para eliminar un artículo
                BajaArticulos bajaArticulos = new BajaArticulos();
                bajaArticulos.setVisible(true);
                setVisible(false); // Cierra la ventana actual
            }
        });
        btnBaja.setBounds(240, 36, 116, 34);
        panel.add(btnBaja);

        // Botón para Modificación de artículos
        JButton btnModificacion = new JButton("Modificación");
        btnModificacion.setBackground(new Color(255, 153, 153));
        btnModificacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de ElegirArticulo para modificar un artículo
                ElegirArticulo elegirArticulo = new ElegirArticulo();
                elegirArticulo.setVisible(true);
                setVisible(false); // Cierra la ventana actual
            }
        });
        btnModificacion.setBounds(62, 117, 116, 34);
        panel.add(btnModificacion);

        // Botón para Consulta de artículos
        JButton btnConsulta = new JButton("Consulta");
        btnConsulta.setBackground(new Color(255, 153, 102));
        btnConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de ConsultaArticulos para ver todos los artículos
                ConsultaArticulos consultaArticulos = new ConsultaArticulos();
                consultaArticulos.setVisible(true);
                setVisible(false); // Cierra la ventana actual
            }
        });
        btnConsulta.setBounds(240, 117, 116, 34);
        panel.add(btnConsulta);

        // Botón para regresar al menú principal
        JButton btnVolver = new JButton("Volver Menú");
        btnVolver.setBackground(new Color(255, 153, 0));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Regresa al menú principal de la aplicación
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
                setVisible(false); // Cierra la ventana actual
            }
        });
        btnVolver.setBounds(160, 189, 110, 34);
        panel.add(btnVolver);
    }
}
