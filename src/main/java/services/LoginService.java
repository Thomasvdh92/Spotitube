package services;

import datasource.ITokenDAO;
import datasource.IOwnerDAO;
import domain.Owner;
import domain.Token;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/login")
public class LoginService {

    @Inject
    private IOwnerDAO OwnerDAO;

    @Inject
    private ITokenDAO tokenDAO;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String body) {
        System.out.println(body);
        JSONObject json = new JSONObject(body);
        String Owner = json.getString("user");
        String password = json.getString("password");
        if (authenticate(Owner, password)) {
            Random rand = new Random();
            StringBuilder tokenString = new StringBuilder();
            for (int i = 1; i < 15; i++) {
                if(i%5==0) tokenString.append("-");
                else {
                    int n = rand.nextInt(10);
                    tokenString.append(n);
                }
            }
            Token token = new Token(tokenString.toString(), Owner);
            tokenDAO.insert(token);
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
        }
    }

    public Boolean authenticate(String Ownername, String password) {
        Owner owner = OwnerDAO.read(Ownername);
        if (owner != null) {
            if (owner.getPassword().equals(password)) {
                System.out.println("AUTHENTICATED");
                return true;
            }
        }
        System.out.println("NOT AUTHENTICATED");
        return false;
    }

}
