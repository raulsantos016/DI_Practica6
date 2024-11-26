package es.studium.practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datos
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tiendecitaRSR";
	String login = "root";
	String password = "PonferradinA_08";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	Datos() {}

	public boolean conectar()
	{
		boolean conexionCorrecta = true;

		// Cargar el Driver
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
			conexionCorrecta = false;
		}

		// Establecer la conexión con la base de datos
		try
		{
			connection = DriverManager.getConnection(url, login, password);
		}
		catch (SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			conexionCorrecta = false;
		}
		return conexionCorrecta;
	}


	public void desconectar() 
	{
		try 
		{
			statement.close();
			connection.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("Error al cerrar " + e.toString());
		}
	}


	public String consultaArticulos() 
	{
		String contenido = "";
		String sentencia = "SELECT * FROM articulos ORDER BY idArticulo;";

		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				contenido = contenido + "ID: " + rs.getString("idArticulo") + " | Descripción: " + rs.getString("descripcionArticulo") + 
						" | Precio: " + rs.getString("precioArticulo") + "€ | Stock: " + rs.getString("stockArticulo") + "\n";
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return contenido;
	}


	public String consultaTickets() 
	{
		String contenido = "";
		String sentencia = "SELECT idTicket, fechaTicket, precioTotalTicket, descripcionArticulo, cantidadArticulosTicket "
				+ "FROM tickets JOIN pertenecer ON tickets.idTicket = pertenecer.idTicketFK JOIN articulos "
				+ "ON pertenecer.idArticuloFK = articulos.idArticulo ORDER BY idTicket;";

		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				contenido = contenido + "ID: " + rs.getString("idTicket") + " | Fecha: " + rs.getString("fechaTicket") + 
						" | Precio: " + rs.getString("precioTotalTicket") + "€ | Artículo: " + rs.getString("descripcionArticulo") + 
						", " + rs.getString("cantidadArticulosTicket") + " unidad/es\n";
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return contenido;
	}


	public boolean altaArticulos(String descripcion, String precio, String stock) 
	{
		boolean altaCorrecta = true;
		String sentencia = "INSERT INTO articulos (idArticulo, descripcionArticulo, precioArticulo, stockArticulo) "
				+ "VALUES (null, '" + descripcion + "', '" + precio + "', '" + stock + "');";

		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} 
		catch (SQLException e) 
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}


	public boolean altaTickets(String fecha, String precioTotal) {
		boolean altaCorrecta = true;
		String sentencia = "INSERT INTO tickets (fechaTicket, precioTotalTicket) VALUES ('" + fecha + "', '" + precioTotal + "');";

		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} 
		catch (SQLException e) 
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}

	public boolean altaPertenecer(String idArticulo, String idTicket, String cantidad) {
		boolean altaCorrecta2 = true;
		String sentencia = "INSERT INTO pertenecer (idArticuloFK, idTicketFK, cantidadArticulosTicket) "
				+ "VALUES ('" + idArticulo + "', '" + idTicket + "', '" + cantidad + "');";

		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} 
		catch (SQLException e) 
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta2 = false;
		}
		return altaCorrecta2;
	}

	public String obtenerUltimoIdTicket() {
		String idTicket = null;
		String consulta = "SELECT LAST_INSERT_ID() AS idTicket;";

		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(consulta);

			if (resultSet.next()) 
			{
				idTicket = resultSet.getString("idTicket");
			}
			resultSet.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("Error al obtener el último idTicket: " + e.toString());
		}
		return idTicket;
	}


	public String[] rellenarChoiceArticulos()
	{
		String elementosCadena = "Elegir un artículo...*";
		String sentencia = "SELECT * FROM articulos";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idArticulo") + " - " + 
						rs.getString("descripcionArticulo") + " - " + 
						rs.getString("precioArticulo") + " - " + 
						rs.getString("stockArticulo") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
		}
		return elementosCadena.split("\\*");
	}


	public boolean modificacionArticulos(String descripcion, String precio, String stock, String id) 
	{
		boolean ModificacionCorrecta = true;
		String sentencia = "UPDATE articulos SET descripcionArticulo='" + descripcion + "', precioArticulo='" + precio + "', stockArticulo='" 
							+ stock + "' WHERE idArticulo=" + id + ";";
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} 
		catch (SQLException e) 
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			ModificacionCorrecta = false;
		}
		return ModificacionCorrecta;
	}


	public void bajaArticulos(String elementoSeleccionado) 
	{
		String sentencia = "DELETE FROM articulos WHERE idArticulo = " + elementoSeleccionado;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
		}
	}
}
