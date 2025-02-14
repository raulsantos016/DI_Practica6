package es.studium.practica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;

/**
 * Clase que permite realizar la baja de artículos en la aplicación.
 * <p>Esta clase genera una ventana en la que el usuario puede seleccionar un artículo
 * de una lista para proceder a su eliminación.</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class BajaArticulos extends JFrame {

    private static final long serialVersionUID = 1L;
    
    /**
     * Panel principal de la ventana donde se ubican los componentes gráficos.
     */
    private JPanel panel;
    
    /**
     * Componente de selección que muestra la lista de artículos disponibles.
     */
    private Choice choArticulos;

    /**
     * Objeto de la clase Datos que gestiona la conexión y consultas a la base de datos.
     */
    Datos datos = new Datos();

    /**
     * Método principal que inicia la aplicación.
     * <p>Este método ejecuta la ventana dentro de un hilo seguro para la interfaz gráfica.</p>
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BajaArticulos frame = new BajaArticulos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor de la clase que inicializa la ventana de baja de artículos.
     * <p>Configura la apariencia de la ventana, inicializa los componentes gráficos
     * y carga la lista de artículos desde la base de datos.</p>
     */
    public BajaArticulos() {
        // Configuración de la ventana
        setTitle("Baja Artículo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);

        // Creación del panel principal
        panel = new JPanel();
        panel.setBackground(new Color(204, 255, 255));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);
        
        // Componente Choice para seleccionar artículos
        choArticulos = new Choice();
        choArticulos.setSize(350, 20);
        choArticulos.setLocation(40, 112);
        panel.add(choArticulos);

        // Etiqueta principal
        JLabel lblPrincipal = new JLabel("Elegir Artículo a borrar:");
        lblPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPrincipal.setBounds(147, 43, 148, 14);
        panel.add(lblPrincipal);

        // Botón Aceptar
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(new Color(0, 204, 204));
        btnAceptar.setBounds(69, 196, 89, 23);
        panel.add(btnAceptar);

        /**
         * Acción del botón Aceptar.
         * <p>Verifica si se ha seleccionado un artículo válido y abre la ventana de confirmación de baja.</p>
         */
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (choArticulos.getSelectedIndex() == 0) {
                    // Mostrar el error en la consola
                    System.err.println("Error: No se pudo seleccionar el artículo para eliminar. La opción ingresada no es válida.");
                } else {
                    // Obtener el artículo seleccionado del Choice
                    String elementoSeleccionadoId = choArticulos.getSelectedItem().split(" - ")[0];
                    
                    // Crear una instancia de la ventana de confirmación
                    MensajeBajaArticulos frame = new MensajeBajaArticulos();

                    // Pasar la información seleccionada a la ventana de confirmación
                    frame.recibirId(elementoSeleccionadoId);

                    // Mostrar la ventana de confirmación
                    frame.setVisible(true);
                    setVisible(false);
                }
            }
        });

        // Botón Volver al Menú
        JButton btnVolver = new JButton("Volver Menú");
        btnVolver.setBackground(new Color(255, 153, 0));
        btnVolver.setBounds(252, 196, 106, 23);
        panel.add(btnVolver);

        /**
         * Acción del botón Volver.
         * <p>Redirige al usuario al menú principal de la aplicación.</p>
         */
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
                setVisible(false);
            }
        });

        // Cargar los artículos desde la base de datos
        cargarArticulos();
    }

    /**
     * Carga los artículos disponibles desde la base de datos y los muestra en el componente Choice.
     */
    private void cargarArticulos() {
        // Conectar con la base de datos
        datos.conectar();
        
        // Obtener la lista de artículos
        String[] elementos = datos.rellenarChoiceArticulos();
        
        // Desconectar la base de datos
        datos.desconectar();
        
        // Agregar los artículos al componente Choice
        for (String elemento : elementos) {
            choArticulos.add(elemento);
        }
    }
}
