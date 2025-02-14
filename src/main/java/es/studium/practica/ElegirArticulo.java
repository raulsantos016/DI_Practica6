package es.studium.practica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Color;

/**
 * Ventana que permite al usuario elegir un artículo de una lista y realizar
 * modificaciones sobre el artículo seleccionado.
 * <p>El usuario puede seleccionar un artículo de un desplegable y, al presionar el botón "Aceptar", 
 * la aplicación llevará a la ventana de modificación del artículo.</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class ElegirArticulo extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private JPanel panel;  // Panel principal donde se agregan los componentes gráficos
    private Choice choArticulos; // Lista desplegable de artículos
    private Datos datos = new Datos();  // Instancia de la clase Datos para la gestión de la base de datos

    /**
     * Método principal que lanza la aplicación.
     * <p>Este método se ejecuta al iniciar la aplicación y muestra la ventana de selección de artículo.</p>
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ElegirArticulo frame = new ElegirArticulo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor que inicializa la ventana para elegir un artículo.
     * <p>Este constructor configura la ventana con los elementos gráficos, 
     * como botones y la lista desplegable de artículos, y también agrega 
     * los eventos necesarios para la interacción del usuario.</p>
     */
    public ElegirArticulo() {
        setTitle("Elegir Artículo");  // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Acción cuando se cierra la ventana
        setBounds(100, 100, 450, 300);  // Tamaño y posición de la ventana
        panel = new JPanel();  // Crear un nuevo panel
        panel.setBackground(new Color(204, 255, 255));  // Color de fondo del panel
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));  // Bordes del panel
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla

        setContentPane(panel);  // Establecer el panel como contenido de la ventana
        panel.setLayout(null);  // Layout sin restricciones

        // Inicializar Choice (lista desplegable)
        choArticulos = new Choice();
        choArticulos.setSize(350, 20);  // Establecer tamaño de la lista
        choArticulos.setLocation(40, 112);  // Establecer posición en la ventana
        panel.add(choArticulos);  // Agregar la lista al panel

        JLabel lblPrincipal = new JLabel("Elige un artículo:");  // Etiqueta de la lista
        lblPrincipal.setBounds(159, 80, 98, 14);  // Establecer la posición de la etiqueta
        panel.add(lblPrincipal);  // Agregar la etiqueta al panel

        // Botón de "Aceptar"
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(new Color(0, 204, 204));  // Color de fondo del botón
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar si se ha seleccionado un artículo válido
                if (choArticulos.getSelectedIndex() == 0) {
                    // Mostrar el error en la consola si no se ha seleccionado un artículo
                    System.err.println("Error: No se pudo seleccionar el artículo para modificar. La opción ingresada no es válida.");
                } else {
                    // Obtener los datos del artículo seleccionado
                    String elementoSeleccionadoId = choArticulos.getSelectedItem().split(" - ")[0];
                    String elementoSeleccionadoDescripcion = choArticulos.getSelectedItem().split(" - ")[1];
                    String elementoSeleccionadoPrecio = choArticulos.getSelectedItem().split(" - ")[2];
                    String elementoSeleccionadoCantidad = choArticulos.getSelectedItem().split(" - ")[3];

                    // Crear una instancia de la ventana de modificación de artículo
                    ModificacionArticulo frame = new ModificacionArticulo();

                    // Pasar los datos del artículo seleccionado a la ventana de modificación
                    frame.recibirDato(elementoSeleccionadoId, elementoSeleccionadoDescripcion, elementoSeleccionadoPrecio, elementoSeleccionadoCantidad);

                    // Mostrar la ventana de modificación de artículo
                    frame.setVisible(true);
                    setVisible(false);  // Ocultar la ventana actual
                }
            }
        });
        btnAceptar.setBounds(75, 177, 89, 23);  // Establecer la posición y tamaño del botón
        panel.add(btnAceptar);  // Agregar el botón al panel

        // Botón para volver al menú principal
        JButton btnVolver = new JButton("Volver Menú");
        btnVolver.setBackground(new Color(255, 153, 0));  // Color de fondo del botón
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Volver al menú principal
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
                setVisible(false);  // Ocultar la ventana actual
            }
        });
        btnVolver.setBounds(242, 177, 108, 23);  // Establecer la posición y tamaño del botón
        panel.add(btnVolver);  // Agregar el botón al panel

        // Llenar la lista desplegable con los datos de los artículos
        datos.conectar();  // Conectar a la base de datos
        String[] elementos = datos.rellenarChoiceArticulos();  // Obtener los elementos de la base de datos
        datos.desconectar();  // Desconectar de la base de datos

        // Agregar cada elemento a la lista Choice
        for (String elemento : elementos) {
            choArticulos.add(elemento);  // Añadir cada artículo a la lista desplegable
        }
    }
}
