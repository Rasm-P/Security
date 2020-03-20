package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import entity.User;
import entity.UserFacade;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import exceptions.AuthenticationException;
import exceptions.GenericExceptionMapper;

@Path("login")
public class LoginEndpoint {

    public static final int TOKEN_EXPIRE_TIME = 1000 * 60 * 30; //30 min

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String jsonString) throws AuthenticationException {
        
            JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
            String username = json.get("username").getAsString();
            String password = json.get("password").getAsString();

            //Todo refactor into facade
            try {
                User user = UserFacade.getInstance().getVeryfiedUser(username, password);
                String token = createToken(username, user.getRolesAsStrings());
                JsonObject responseJson = new JsonObject();
                responseJson.addProperty("username", username);
                responseJson.addProperty("token", token);
                return Response.ok(new Gson().toJson(responseJson)).build();

            } catch (Exception  ex) {
                if(ex instanceof AuthenticationException){
                    throw (AuthenticationException)ex;
                }
                Logger.getLogger(GenericExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
            throw new AuthenticationException("Invalid username or password! Please try again");
        }

    

    private String createToken(String userName, List<String> roles) throws JOSEException {

           return  "In this exercise you must create a valid token, for Authenticated users";
    }
}
