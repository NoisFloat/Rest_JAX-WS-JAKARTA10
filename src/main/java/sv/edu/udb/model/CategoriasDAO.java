package sv.edu.udb.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriasDAO extends AppConnection{
    public void insert(Categoria categoria) throws SQLException {
        connect();//Me conecto a la base de datos
        //Es la variable de preparedStatement
        pstmt = conn.prepareStatement("INSERT INTO CATEGORIA (nombre) VALUES (?)");
        //Le estoy diciendo que me prepare la consulta //Como escribirla en el editor de query pero sin ejecutar, y que use el conn
        //para expecificarle a que bdd y servidor va a preparar la consulta;

        pstmt.setString(1,categoria.getNombre()); //Le indico donde va a interpolar el valor en mi consulta preparada
        //Ademas internamente evita SQL INJECTION
        pstmt.execute(); //El metodo heredado de Conneccion //Heredacion de Heredacion me permite ejecutar la consulta de esta manera
        close();//Metodo de la clase AppConnection que me vuelve mi variable heredada conn = null
    }
    public void update(Categoria categoria) throws SQLException{
        connect();
        pstmt = conn.prepareStatement("update categoria set nombre = ? where id = ?");
        pstmt.setString(1, categoria.getNombre());
        pstmt.execute();
        close();
    }


    public void delete(int id) throws SQLException{
        connect();
        pstmt = conn.prepareStatement("delete from categoria where id = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }


    public ArrayList<Categoria> findAll() throws SQLException{
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("select id, nombre from categoria");
        ArrayList<Categoria> categorias = new ArrayList();

        while(resultSet.next()){
            Categoria tmp = new Categoria();
            tmp.setIdCategoria(resultSet.getInt(1));
            tmp.setNombre(resultSet.getString(2));

            categorias.add(tmp);
        }

        close();

        return categorias;
    }


    public Categoria findById(int id) throws SQLException{

        Categoria categoria = null;

        connect();
        pstmt = conn.prepareStatement("select id, nombre from categoria where id = ?");
        pstmt.setInt(1, id);

        resultSet = pstmt.executeQuery();

        while(resultSet.next()){
            categoria = new Categoria();
            categoria.setIdCategoria(resultSet.getInt(1));
            categoria.setNombre(resultSet.getString(2));
        }

        close();
        return categoria;
    }
}

