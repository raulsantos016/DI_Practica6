package es.studium.practica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Ventana que permite consultar los tickets generados en un rango de fechas
 * y generar un reporte en formato JasperReport.
 * <p>Los usuarios pueden ingresar un rango de fechas para generar el informe de tickets.</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class ConsultaTickets extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    /** URL de la base de datos. */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tiendecitarsr";
    
    /** Usuario de la base de datos. */
    private static final String DB_USER = "root";
    
    /** Contraseña de la base de datos. */
    private static final String DB_PASSWORD = "PonferradinA_08";
    
    /** Ruta del archivo de reporte JasperReport. */
    private static final String REPORT_PATH = "src/main/resources/Ejemplo2.jasper";
    
    /** Panel principal de la ventana. */
    private JPanel panel;
    
    /** Campo de texto para la fecha de inicio. */
    private JTextField textFieldDesde;
    
    /** Campo de texto para la fecha de fin. */
    private JTextField textFieldHasta;

    /**
     * Método principal que lanza la aplicación.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConsultaTickets frame = new ConsultaTickets();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor de la clase. Inicializa la ventana de consulta de tickets,
     * configurando la interfaz gráfica, los campos de fecha y los botones.
     */
    public ConsultaTickets() {
        setTitle("Consulta Tickets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBackground(new Color(204, 255, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panel);

        // Configuración del layout
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Componentes
        JLabel lblFechaDesde = new JLabel("Fecha Desde (AAAA-MM-DD):");
        lblFechaDesde.setFont(new Font("Tahoma", Font.PLAIN, 12));
        textFieldDesde = new JTextField(10);

        JLabel lblFechaHasta = new JLabel("Fecha Hasta (AAAA-MM-DD):");
        lblFechaHasta.setFont(new Font("Tahoma", Font.PLAIN, 12));
        textFieldHasta = new JTextField(10);

        // Botones
        JButton btnVolver = new JButton("Volver Menú");
        btnVolver.setBackground(new Color(255, 153, 0));
        btnVolver.addActionListener(this::volverAlMenu);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(new Color(51, 204, 204));
        btnAceptar.addActionListener(this::procesarConsulta);

        // Diseño del layout
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblFechaDesde)
                .addComponent(textFieldDesde)
                .addComponent(lblFechaHasta)
                .addComponent(textFieldHasta)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50)
                    .addComponent(btnAceptar, 150, 150, 150)
                    .addGap(30)
                    .addComponent(btnVolver, 150, 150, 150)
                    .addGap(50)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblFechaDesde)
                .addComponent(textFieldDesde)
                .addComponent(lblFechaHasta)
                .addComponent(textFieldHasta)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnVolver)
                )
        );
    }

    /**
     * Procesa la consulta de tickets en el rango de fechas proporcionado
     * por el usuario. Verifica si las fechas son válidas y genera un reporte
     * si la entrada es correcta.
     * 
     * @param e El evento que dispara la acción de procesar la consulta.
     */
    private void procesarConsulta(ActionEvent e) {
        String fechaDesdeStr = textFieldDesde.getText();
        String fechaHastaStr = textFieldHasta.getText();

        if (fechaDesdeStr.isEmpty() || fechaHastaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Las fechas no pueden estar vacías.", "Error: Consulta Tickets", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            java.sql.Date fechaDesde = parseFecha(fechaDesdeStr);
            java.sql.Date fechaHasta = parseFecha(fechaHastaStr);
            generarReporte(fechaDesde, fechaHasta);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use AAAA-MM-DD.", "Error: Consulta Tickets", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Convierte una cadena de texto en una fecha en formato SQL.
     * 
     * @param fechaStr La cadena de texto con el formato "AAAA-MM-DD".
     * @return La fecha convertida en formato SQL.
     * @throws ParseException Si el formato de la fecha no es válido.
     */
    private java.sql.Date parseFecha(String fechaStr) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = formato.parse(fechaStr);
        return new java.sql.Date(parsedDate.getTime());
    }

    /**
     * Genera y muestra el reporte de tickets basado en el rango de fechas
     * proporcionado. Utiliza JasperReports para generar el informe y mostrarlo.
     * 
     * @param fechaDesde Fecha de inicio del rango de consulta.
     * @param fechaHasta Fecha de fin del rango de consulta.
     */
    private void generarReporte(java.sql.Date fechaDesde, java.sql.Date fechaHasta) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(REPORT_PATH);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("fechaDesde", fechaDesde);
            parameters.put("fechaHasta", fechaHasta);
            parameters.put("Parametro1", "Raúl Santos");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Vuelve al menú principal de la aplicación.
     * 
     * @param e El evento que dispara la acción de volver al menú.
     */
    private void volverAlMenu(ActionEvent e) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        setVisible(false);
    }
}
