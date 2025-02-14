package es.studium.practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase encargada de gestionar la conexión y las operaciones SQL relacionadas con la base de datos.
 * Permite realizar operaciones de alta, baja, modificación y consulta en las tablas de artículos y tickets, 
 * así como gestionar las relaciones entre ellos.
 * 
 * <p>Esta clase contiene métodos para conectar a la base de datos, realizar consultas sobre artículos y tickets, 
 * insertar datos en la base de datos, y gestionar las relaciones entre los artículos y los tickets.</p>
 * 
 * <p><strong>Dependencias:</strong> MySQL JDBC Driver</p>
 * 
 * @author Raúl Santos Ruiz
 * @version 1.0
 * @since 2025-02-14
 */
public class Datos {

    /** El driver para la conexión con la base de datos MySQL */
    private String driver = "com.mysql.cj.jdbc.Driver";

    /** URL de conexión a la base de datos */
    private String url = "jdbc:mysql://localhost:3306/tiendecitaRSR";

    /** Usuario para la conexión con la base de datos */
    private String login = "root";

    /** Contraseña para la conexión con la base de datos */
    private String password = "PonferradinA_08";

    /** Objeto de conexión a la base de datos */
    private Connection connection = null;

    /** Objeto Statement para ejecutar las consultas SQL */
    private Statement statement = null;

    /** Objeto ResultSet para almacenar los resultados de las consultas */
    private ResultSet rs = null;

    /**
     * Constructor por defecto de la clase `Datos`.
     * Inicializa los atributos de la clase pero no realiza ninguna acción.
     */
    Datos() {}

    /**
     * Establece la conexión a la base de datos utilizando los parámetros definidos en la clase.
     * 
     * <p>Este método intenta cargar el driver de MySQL y luego establece la conexión con la base de datos
     * utilizando el URL, el login y la contraseña especificados.</p>
     * 
     * @return `true` si la conexión fue exitosa, `false` si hubo algún error durante la conexión.
     */
    public boolean conectar() {
        boolean conexionCorrecta = true;

        try {
            // Cargar el Driver de MySQL
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Se ha producido un error al cargar el Driver");
            conexionCorrecta = false;
        }

        try {
            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println("Se produjo un error al conectar a la Base de Datos");
            conexionCorrecta = false;
        }
        return conexionCorrecta;
    }

    /**
     * Cierra la conexión a la base de datos y libera los recursos asociados.
     * 
     * <p>Este método cierra tanto la conexión a la base de datos como el Statement utilizado para realizar
     * las consultas SQL.</p>
     */
    public void desconectar() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar " + e.toString());
        }
    }

    /**
     * Realiza una consulta a la tabla de artículos y devuelve un resumen con los detalles de cada artículo.
     * 
     * <p>Este método consulta la tabla `articulos` y obtiene la descripción, precio y stock de todos los artículos.
     * Los resultados se devuelven en un formato legible.</p>
     * 
     * @return Un String con la información de todos los artículos de la base de datos.
     */
    public String consultaArticulos() {
        String contenido = "";
        String sentencia = "SELECT * FROM articulos ORDER BY idArticulo;";

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sentencia);
            while (rs.next()) {
                contenido = contenido + "ID: " + rs.getString("idArticulo") + " | Descripción: " + rs.getString("descripcionArticulo") + 
                        " | Precio: " + rs.getString("precioArticulo") + "€ | Stock: " + rs.getString("stockArticulo") + "\n";
            }
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL:" + e.toString());
        }
        return contenido;
    }

    /**
     * Realiza una consulta a los tickets de la tienda y devuelve un resumen con los detalles de cada uno.
     * 
     * <p>Este método consulta la tabla `tickets` y la tabla de relaciones `pertenecer`, para obtener los detalles
     * de cada ticket y los artículos asociados a él, incluyendo la cantidad de cada artículo.</p>
     * 
     * @return Un String con la información de los tickets y artículos asociados.
     */
    public String consultaTickets() {
        String contenido = "";
        String sentencia = "SELECT idTicket, fechaTicket, precioTotalTicket, descripcionArticulo, cantidadArticulosTicket "
                + "FROM tickets JOIN pertenecer ON tickets.idTicket = pertenecer.idTicketFK JOIN articulos "
                + "ON pertenecer.idArticuloFK = articulos.idArticulo ORDER BY idTicket;";

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sentencia);
            while (rs.next()) {
                contenido = contenido + "ID: " + rs.getString("idTicket") + " | Fecha: " + rs.getString("fechaTicket") + 
                        " | Precio: " + rs.getString("precioTotalTicket") + "€ | Artículo: " + rs.getString("descripcionArticulo") + 
                        ", " + rs.getString("cantidadArticulosTicket") + " unidad/es\n";
            }
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL:" + e.toString());
        }
        return contenido;
    }

    /**
     * Realiza el alta de un artículo en la base de datos.
     * 
     * <p>Este método inserta un nuevo artículo en la tabla `articulos`, especificando su descripción, precio y stock.</p>
     * 
     * @param descripcion Descripción del artículo.
     * @param precio Precio del artículo.
     * @param stock Cantidad de stock disponible del artículo.
     * @return `true` si la inserción fue exitosa, `false` si hubo un error.
     */
    public boolean altaArticulos(String descripcion, String precio, String stock) {
        boolean altaCorrecta = true;
        String sentencia = "INSERT INTO articulos (idArticulo, descripcionArticulo, precioArticulo, stockArticulo) "
                + "VALUES (null, '" + descripcion + "', '" + precio + "', '" + stock + "');";

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL:" + e.toString());
            altaCorrecta = false;
        }
        return altaCorrecta;
    }

    /**
     * Realiza el alta de un ticket en la base de datos.
     * 
     * <p>Este método inserta un nuevo ticket en la tabla `tickets`, especificando su fecha y precio total.</p>
     * 
     * @param fecha Fecha del ticket.
     * @param precioTotal Precio total del ticket.
     * @return `true` si la inserción fue exitosa, `false` si hubo un error.
     */
    public boolean altaTickets(String fecha, String precioTotal) {
        boolean altaCorrecta = true;
        String sentencia = "INSERT INTO tickets (fechaTicket, precioTotalTicket) VALUES ('" + fecha + "', '" + precioTotal + "');";

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL:" + e.toString());
            altaCorrecta = false;
        }
        return altaCorrecta;
    }

    /**
     * Realiza el alta de la relación entre un artículo y un ticket en la tabla "pertenecer".
     * 
     * <p>Este método inserta una nueva relación entre un artículo y un ticket en la tabla `pertenecer`, especificando
     * la cantidad de artículos en ese ticket.</p>
     * 
     * @param idArticulo El ID del artículo.
     * @param idTicket El ID del ticket.
     * @param cantidad La cantidad de artículos en el ticket.
     * @return `true` si la inserción fue exitosa, `false` si hubo un error.
     */
    public boolean altaPertenecer(String idArticulo, String idTicket, String cantidad) {
        boolean altaCorrecta2 = true;
        String sentencia = "INSERT INTO pertenecer (idArticuloFK, idTicketFK, cantidadArticulosTicket) "
                + "VALUES ('" + idArticulo + "', '" + idTicket + "', '" + cantidad + "');";

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL:" + e.toString());
            altaCorrecta2 = false;
        }
        return altaCorrecta2;
    }

    /**
     * Obtiene el último ID de ticket insertado.
     * 
     * <p>Este método consulta el ID del último ticket insertado en la base de datos mediante la función
     * `LAST_INSERT_ID()` de MySQL.</p>
     * 
     * @return El ID del último ticket insertado.
     */
    public String obtenerUltimoIdTicket() {
        String idTicket = null;
        String consulta = "SELECT LAST_INSERT_ID() AS idTicket;";

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(consulta);

            if (resultSet.next()) {
                idTicket = resultSet.getString("idTicket");
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener el último idTicket: " + e.toString());
        }
        return idTicket;
    }

    /**
     * Rellena un arreglo con los artículos disponibles para ser seleccionados en una interfaz gráfica.
     * 
     * <p>Este método consulta los artículos en la base de datos y devuelve un arreglo de Strings con los detalles
     * de los artículos que pueden ser seleccionados por el usuario.</p>
     * 
     * @return Un arreglo de Strings con los detalles de los artículos disponibles.
     */
    public String[] rellenarChoiceArticulos() {
        String elementosCadena = "Elegir un artículo...*";
        String sentencia = "SELECT * FROM articulos";
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sentencia);
            while (rs.next()) {
                elementosCadena = elementosCadena + rs.getString("idArticulo") + " - " + 
                        rs.getString("descripcionArticulo") + " - " + 
                        rs.getString("precioArticulo") + " - " + 
                        rs.getString("stockArticulo") + "*";
            }
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL:" + e.toString());
        }
        return elementosCadena.split("\\*");
    }

    /**
     * Modifica los datos de un artículo existente en la base de datos.
     * 
     * <p>Este método permite modificar la descripción, precio y stock de un artículo ya existente en la base de datos.</p>
     * 
     * @param descripcion La nueva descripción del artículo.
     * @param precio El nuevo precio del artículo.
     * @param stock El nuevo stock disponible del artículo.
     * @param id El ID del artículo a modificar.
     * @return `true` si la modificación fue exitosa, `false` si hubo un error.
     */
    public boolean modificacionArticulos(String descripcion, String precio, String stock, String id) {
        boolean ModificacionCorrecta = true;
        String sentencia = "UPDATE articulos SET descripcionArticulo='" + descripcion + "', precioArticulo='" + precio + "', stockArticulo='" 
                            + stock + "' WHERE idArticulo=" + id + ";";
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL:" + e.toString());
            ModificacionCorrecta = false;
        }
        return ModificacionCorrecta;
    }

    /**
     * Elimina un artículo de la base de datos.
     * 
     * <p>Este método elimina un artículo de la tabla `articulos` en función de su ID.</p>
     * 
     * @param elementoSeleccionado El ID del artículo a eliminar.
     */
    public void bajaArticulos(String elementoSeleccionado) {
        String sentencia = "DELETE FROM articulos WHERE idArticulo = " + elementoSeleccionado;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL:" + e.toString());
        }
    }
}
