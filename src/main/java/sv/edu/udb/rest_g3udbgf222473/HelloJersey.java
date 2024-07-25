package sv.edu.udb.rest_g3udbgf222473;
import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("hello")
public class HelloJersey {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage(){
        return "Hola mundo, lo tipico";
    }
    //Ya que la aplicacion en su path de dijo que empezaria siempre con /rest cuando querramos acceder a los metodos de esta clase
    //hariamos asi: localhost:8080/rest/hello/metodoDeClase o simplemente: localhost:8080/rest/hello Para acceder a getMessage

    @GET
    @Path("PruebaNoel")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ClasesPrueba> getPrueba(){//Esto indirectamente regresa un json con status 200, ya que el modulo api ya viene
        //Preparado para que alguien ponga un metodo y que no retorne directamente un tipo de dato Response
        ClasesPrueba obj1 = new ClasesPrueba("Prueba1","Descripcion1");
        ClasesPrueba obj2 = new ClasesPrueba("Prueba2","Descripcion2");
        ClasesPrueba obj3 = new ClasesPrueba("Prueba3","Descripcion3");
        ArrayList<ClasesPrueba> PruebaDeRetornoNoel = new ArrayList<ClasesPrueba>();
        PruebaDeRetornoNoel.add(obj1);
        PruebaDeRetornoNoel.add(obj2);
        PruebaDeRetornoNoel.add(obj3);
        return PruebaDeRetornoNoel;
    }


    //Ruta: localhost:8080/rest/hello/mensajes
    @GET
    @Path("mensajes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensajes(){
        List<Mensaje> mensajes = new ArrayList();
        for(int i = 1; i<=10 ; i++){
            Mensaje mensaje = new Mensaje();
            mensaje.setNumero(i);
            mensaje.setMensaje("Mensaje "+i);
            mensajes.add(mensaje);
        }

        return Response.status(200).entity(mensajes).build();
    }


    //Creacion de la clase mensaje (Ya que no es necesario mucho lo haremos aqui)

    class Mensaje{
        private int numero;
        private String mensaje;

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }

}
