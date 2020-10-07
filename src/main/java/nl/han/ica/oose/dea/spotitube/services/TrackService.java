package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasource.ITrackDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
