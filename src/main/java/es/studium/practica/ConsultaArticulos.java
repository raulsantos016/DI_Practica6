package es.studium.practica;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Ventana de la aplicación que permite consultar los artículos disponibles
 * y generar un informe en formato JasperReport.
 * 
 * Esta clase se encarga de conectar con la base de datos, recuperar información sobre los artículos 
 * y mostrarlos en una interfaz gráfica. Además, permite generar y visualizar un informe en PDF 
 * utilizando JasperReports.
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class ConsultaArticulos extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    // Constantes para la conexión a la base de datos y el reporte
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tiendecitarsr";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "PonferradinA_08";
    private static final String REPORT_PATH = "src/main/resources/Ejemplo1.jasper";
    
    private JPanel panel;
    private JTextArea textArea;
    private Datos datos;

    /**
     * Método principal que inicia la aplicación y crea una instancia de la ventana.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConsultaArticulos frame = new ConsultaArticulos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor que inicializa la ventana de consulta de artículos.
     * Se encarga de configurar los componentes gráficos y cargar los datos de los artículos.
     */
    public ConsultaArticulos() {
        datos = new Datos();
        setTitle("Consulta Artículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        panel = new JPanel();
        panel.setBackground(new Color(204, 255, 255));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(panel);
        panel.setLayout(null);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        textArea.setBounds(10, 25, 414, 162);
        textArea.setEditable(false);
        panel.add(textArea);
        
        // Botón para volver al menú principal
        JButton btnVolver = new JButton("Volver Menú");
        btnVolver.setBackground(new Color(255, 153, 0));
        btnVolver.addActionListener(e -> {
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            menuPrincipal.setVisible(true);
            setVisible(false);
        });
        btnVolver.setBounds(50, 214, 111, 23);
        panel.add(btnVolver);
        
        // Botón para generar el informe de artículos
        JButton btnMostrarInforme = new JButton("Informe Artículos");
        btnMostrarInforme.setBackground(new Color(51, 204, 204));
        btnMostrarInforme.addActionListener(this::mostrarInformeArticulos);
        btnMostrarInforme.setBounds(250, 214, 150, 23);
        panel.add(btnMostrarInforme);
        
        // Agregar área de texto con scroll
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 25, 414, 162);
        panel.add(scrollPane);
        
        // Cargar los artículos desde la base de datos
        cargarDatosArticulos();
    }

    /**
     * Carga los artículos de la base de datos y los muestra en el área de texto.
     * Se conecta a la base de datos mediante la clase {@link Datos}.
     */
    private void cargarDatosArticulos() {
        datos.conectar();
        textArea.append(datos.consultaArticulos());
        datos.desconectar();
    }

    /**
     * Genera y muestra el informe de los artículos en formato JasperReport.
     * 
     * @param event Evento que activa el método (clic en el botón "Informe Artículos").
     */
    private void mostrarInformeArticulos(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            
            // Cargar el reporte Jasper desde el archivo
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(REPORT_PATH);
            
            // Crear el mapa de parámetros para JasperReports
            HashMap<String, Object> parametros = new HashMap<>();
            
            // Establecer el nombre del autor en el parámetro correcto
            parametros.put("Parametro1", "Raúl Santos");

            // Llenar el reporte con la conexión y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            
            // Mostrar el informe en un visor de JasperReports
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (JRException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
