package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("data")
public class DemoResource {

  @Context
  private UriInfo context;

  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
  ;
    
    @Context
  SecurityContext securityContext;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed("user")
  @Path("{id}")
  public String getFromUser(@PathParam("id") String id) {
    EntityManager em = emf.createEntityManager();
    try {
      User user = em.find(User.class, id);
      if (user == null) {
        return "";
      }

      return ("{\"data\": \"" + user.getUsersData() + "\"}");  //Return as JSON
    } finally {
      em.close();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("all")
  public String a(@PathParam("id") String id) {
    return ("{\"data\": \" This can be read by all\"}");  //Return as JSON);
  }

}
