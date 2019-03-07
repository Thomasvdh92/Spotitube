package services;

import datasource.ITrackDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackService {

    @Inject
    private ITrackDAO trackDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksForPlaylist(
            @QueryParam("forPlaylist") int forPlaylist,
            @QueryParam("token") String token)  {
        return Response.ok(trackDAO.tracksForPlaylist(forPlaylist)).build();
    }
}
