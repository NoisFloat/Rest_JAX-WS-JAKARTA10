package sv.edu.udb.rest_g3udbgf222473;
import java.sql.SQLException;
import java.util.List;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.udb.model.CategoriasDAO;
import sv.edu.udb.model.Concepto;
import sv.edu.udb.model.ConceptosDAO;

//Para acceder a los metodos de esta clase se ocupara el path:
@Path("/conceptos")
public class ConceptosRest {


    ConceptosDAO conceptosDAO = new ConceptosDAO();
    CategoriasDAO categoriasDAO = new CategoriasDAO();
    //localhost:8080/rest/conceptos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConceptos() throws SQLException{

        List<Concepto> conceptos = conceptosDAO.findAll();
        for (Concepto concepto : conceptos) {
            concepto.setCategoria(categoriasDAO.findById(concepto.getCategoryId()));
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(conceptos).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getConceptosById(@PathParam("id") int id) throws SQLException{

        Concepto concepto = conceptosDAO.findById(id);
        if(concepto == null){
            //EL .header practicamente me permite que la API pueda ser accedida desde cualquier otro servidor o direccion ip no propia
            //del servidor
            //En resumen me permite que la api sea consumida desde diferentes dominios o dispositivos en internet
            //.header("Access-Control-Allow-Origin", "*")
            return Response.status(404).header("Access-Control-Allow-Origin", "*").entity("Concepto no encontrado").build();
        }
        concepto.setCategoria(categoriasDAO.findById(concepto.getCategoryId()));
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(concepto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertConcepto(@FormParam("name") String name, @FormParam("value") Double value,
            @FormParam("category_id") int category_id
    ) throws SQLException{

        Concepto concepto = new Concepto();

        if(categoriasDAO.findById(category_id)==null){
            //El código de estado HTTP 400 Bad Request indica que el servidor no puede o no procesará la petición
            // debido a un error del cliente, como una sintaxis de petición malformada o una solicitud inválida.
            // Es un código de respuesta en el rango 4XX
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Categoria no corresponde a ninguna existencia").build();
        }

        concepto.setNombre(name);
        concepto.setValor(value);
        concepto.setCategoryId(category_id);
        conceptosDAO.insert(concepto);
        concepto.setCategoria(categoriasDAO.findById(concepto.getCategoryId()));
        //El código de respuesta HTTP 201 Created indica que la solicitud ha tenido éxito
        // //y ha llevado a la creación de un recurso. Este código se utiliza para indicar
        // que se ha creado un nuevo recurso en el servidor.
        return Response.status(201)
                .header("Access-Control-Allow-Origin", "*")
                .entity(concepto)
                .build();
    }

    //@DELETE
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public Response eliminarConcepto(
            @PathParam("id") int id
    ) throws SQLException{
        Concepto concepto = conceptosDAO.findById(id);
        if(concepto == null){
            return Response.status(400)
                    .entity("Concepto no corresponde a ninguna existencia")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
        concepto.setCategoria(categoriasDAO.findById(concepto.getCategoryId()));

        conceptosDAO.delete(id);

        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(concepto)
                .build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateConcepto(
            @PathParam("id") int id,
            @FormParam("name") String name,
            @FormParam("value") Double value,
            @FormParam("category_id") int category_id
    ) throws SQLException{

        Concepto concepto = conceptosDAO.findById(id);

        if(concepto == null){
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Concepto no corresponde a ninguna existencia")
                    .build();
        }

        if(categoriasDAO.findById(category_id)==null){
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Categoria no corresponde a ninguna existencia")
                    .build();
        }

        concepto.setNombre(name);
        concepto.setValor(value);
        concepto.setCategoryId(category_id);
        concepto.setCategoria(categoriasDAO.findById(category_id));
        conceptosDAO.update(concepto);

        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(concepto)
                .build();
    }
}

