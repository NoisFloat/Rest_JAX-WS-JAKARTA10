package sv.edu.udb.model;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


//Las clases abstractas no se pueden instanciar, solamente pueden ser extendidas por otras //En otras palabras heredadas
public abstract class AppConnection {
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/presupuesto";

    private static String USER = "root";
    private static String PASS = "gf222473";

    Connection conn = null; //La conexion a la base de datos
    Statement stmt = null; //Para ejecutas sentencias SQL estaticas
    PreparedStatement pstmt = null; //Para ejecutar sentencias SQL Parametrizadas(preparadas) insert into tabla (?,?)
    ResultSet resultSet = null; //Para almacenar los resultados de las consultas

    public AppConnection() { //Se utiliza para cargar el controlador
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) { //En caso de no encontrarse captura el error y registra el error usando un logger
            Logger.getLogger(AppConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    Usa DriverManager.getConnection(DB_URL, USER, PASS) para establecer una conexión a la base de datos usando la URL
    y las credenciales definidas anteriormente.
    Puede lanzar una excepción SQLException si ocurre un error al intentar conectar.
    */
    public void connect() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }


    /*Cierra la conexión a la base de datos si está abierta (conn != null).
    Establece la variable conn a null después de cerrarla.
    Puede lanzar una excepción SQLException si ocurre un error al intentar cerrar la conexión.*/
    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }




}
