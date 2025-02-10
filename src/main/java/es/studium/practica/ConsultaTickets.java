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

public class ConsultaTickets extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tiendecitarsr";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "PonferradinA_08";
    private static final String REPORT_PATH = "src/main/resources/Ejemplo2.jasper";
    
    private JPanel panel;
    private JTextField textFieldDesde;
    private JTextField textFieldHasta;

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

        // Definición del diseño
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblFechaDesde)
                .addComponent(textFieldDesde)
                .addComponent(lblFechaHasta)
                .addComponent(textFieldHasta)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50)  // Espacio desde la izquierda
                    .addComponent(btnAceptar, 150, 150, 150)  // Aceptar a la izquierda
                    .addGap(30)  // Espacio entre los botones
                    .addComponent(btnVolver, 150, 150, 150)  // Volver Menú a la derecha
                    .addGap(50)  // Espacio hasta la derecha
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

    private java.sql.Date parseFecha(String fechaStr) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = formato.parse(fechaStr);
        return new java.sql.Date(parsedDate.getTime());
    }

    private void generarReporte(java.sql.Date fechaDesde, java.sql.Date fechaHasta) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(REPORT_PATH);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("fechaDesde", fechaDesde);
            parameters.put("fechaHasta", fechaHasta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void volverAlMenu(ActionEvent e) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        setVisible(false);
    }
}