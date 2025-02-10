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

public class ConsultaArticulos extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tiendecitarsr";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "PonferradinA_08";
    private static final String REPORT_PATH = "src/main/resources/Ejemplo1.jasper";
    
    private JPanel panel;
    private JTextArea textArea;
    private Datos datos;

    /**
     * Launch the application.
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
     * Create the frame.
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
        
        // Botón Volver Menú (ubicado a la izquierda)
        JButton btnVolver = new JButton("Volver Menú");
        btnVolver.setBackground(new Color(255, 153, 0));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
                setVisible(false);
            }
        });
        btnVolver.setBounds(50, 214, 111, 23);  // Colocado a la izquierda
        panel.add(btnVolver);
        
        // Botón Informe Artículos (ubicado a la derecha)
        JButton btnMostrarInforme = new JButton("Informe Artículos");
        btnMostrarInforme.setBackground(new Color(51, 204, 204));
        btnMostrarInforme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInformeArticulos(e);
            }
        });
        btnMostrarInforme.setBounds(250, 214, 150, 23);  // Colocado a la derecha
        panel.add(btnMostrarInforme);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 25, 414, 162);
        panel.add(scrollPane);
        
        cargarDatosArticulos();
    }

    private void cargarDatosArticulos() {
        datos.conectar();
        textArea.append(datos.consultaArticulos());
        datos.desconectar();
    }

    // Método para mostrar el informe
    private void mostrarInformeArticulos(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(REPORT_PATH);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), conn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}