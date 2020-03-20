package exceptions;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/*
  Utility class to map errors into JSON
*/
class Error {

    private int statusCode;
    private String statusDescription;
    private String errorMessage;

    public Error(int statusCode, String statusDescription, String errorMessage) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>  {
    static private final Gson gson = new Gson();
    @Override
    public Response toResponse(Throwable  ex) {
       
        Response.StatusType type = getStatusType(ex);
        Logger.getLogger(GenericExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);

        Error error = new Error(
                type.getStatusCode(),
                type.getReasonPhrase(),
                ex.getLocalizedMessage());
        
        String errJson =gson.toJson(error); 
        return Response.status(error.getStatusCode())
                .entity(errJson)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response.StatusType getStatusType(Throwable ex) {
        if (ex instanceof WebApplicationException) {
            return((WebApplicationException)ex).getResponse().getStatusInfo();
        } 
        if(ex instanceof AuthenticationException){
            return Response.Status.FORBIDDEN;
        } else {
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }
    
    //Small hack, to provide json-error response in the filter
    public static Response makeErrRes(String msg,int status){
        Error error = new Error(
                status,
                msg,
                msg);
        
        String errJson =gson.toJson(error); 
        return Response.status(error.getStatusCode())
                .entity(errJson)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}