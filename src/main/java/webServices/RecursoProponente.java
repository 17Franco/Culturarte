
package webServices;

import Configuracion.config;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.Fabrica;
import logica.IController;
import logica.Usuario.Proponente;

@Path("proponentes")
public class RecursoProponente {
    private IController controller = Fabrica.getInstance().getController();
    config conf = config.getInstance();
    
    @GET 
    @Path("{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public DTOProponente getProponente(@PathParam("id") String idProponente) {
        
        
        DTOProponente proponenteEncontrado = controller.getDTOProponente(idProponente);
        proponenteEncontrado.setPropCreadas(new ArrayList<>(controller.getPropuestasCreadasPorProponente(idProponente)));
        for(DTOPropuesta p: proponenteEncontrado.getPropCreadas()){
           p.setAporteNick(controller.colaboradoresAPropuesta(p.getTitulo()));
        }
       
        return proponenteEncontrado; 
    }
}
