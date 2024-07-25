package sv.edu.udb.rest_g3udbgf222473;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.udb.model.Categoria;
import sv.edu.udb.model.CategoriasDAO;
import sv.edu.udb.model.ConceptosDAO;

import java.sql.SQLException;
import java.util.ArrayList;


@Path("categorias")
public class CategoriasRest {
    //localhost:8080/rest/categorias
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorias() throws SQLException {
        //Response.status, retorna el codigo de respuesta HTTP, entity = envia el tipo de dato como respuesta, si es compatible con
        //MediaType.(TIPO DE DATO)
        //Build practicamente hace el parse para enviarlo como respuesta por el metodo HTTP
        CategoriasDAO fuenteDeDatosCategorias = new CategoriasDAO();
        ArrayList<Categoria> categorias = fuenteDeDatosCategorias.findAll();
        return Response.status(200).entity(categorias).build();
    }

    //localhost:8080/rest/categorias/ejemploNoel
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("ejemploNoel")
    public Response getCategoriasEjemploNoel() throws SQLException {
        CategoriasDAO fuenteDeDatos = new CategoriasDAO();
        ArrayList<Categoria> categorias = fuenteDeDatos.findAll();
        return Response.status(200).entity(categorias).build();
    }

    /*Uso de peticiones get PARAMETRIZADAS*/
    //localhost:8080/rest/categorias/{numeroEntero}
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCategoriasId(@PathParam("id") int id) throws SQLException {
        CategoriasDAO fuenteDeDatos = new CategoriasDAO();
        Categoria categoria = fuenteDeDatos.findById(id);
        if(categoria == null) {
            return Response.status(404).entity("Categoria no encontrada").build(); //Devuelve el codigo de 404, en resumen no existe esa categoria
            //Por definicion estaria bien el 404, pero puede ser cualquier codigo serie 200, 400 o 500, facilmente puede ser el error que quieras
            //Y veras un error diferente segun el error indicado aqui
        }
        return Response.status(200).entity(categoria).build();//En caso de que categoria sea diferente de nulo, envia la categoria con un estado OK(200)
    }









}
