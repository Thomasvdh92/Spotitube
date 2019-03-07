package services;

import datasource.IPlaylistDAO;
import datasource.ITokenDAO;
import datasource.ITrackDAO;
import datasource.IUserDAO;
import domain.*;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistService {

    @Inject
    private IPlaylistDAO playlistDAO;

    @Inject
    private ITrackDAO trackDAO;

    @Inject
    private ITokenDAO tokenDAO;

    @Inject
    private IUserDAO userDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists() {
        Playlists playlists = new Playlists(playlistDAO.getAllPlaylists());
        playlists.calculateLength();
        return Response.ok(playlists, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(String body, @QueryParam("token") String token) {
        JSONObject json = new JSONObject(body);
        String name = json.getString("name");
        User user = userDAO.getUserByTokenString(token);
        playlistDAO.add(new Playlist(-1, name, user, null));
        return getPlaylists();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        playlistDAO.delete(id);
        return getPlaylists();
    }

    @PUT
    @Path("/{id}")
    public Response put(String body, @PathParam("id") int id, @QueryParam("token") String token) {
        JSONObject json = new JSONObject(body);
        String name = json.getString("name");
        User user = userDAO.getUserByTokenString(token);
        Playlist playlist = new Playlist(id, name, user, null);
        playlistDAO.put(playlist);
        return getPlaylists();
    }

    @DELETE
    @Path("/{id}/tracks/{tid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@PathParam("id") Integer id, @PathParam("tid") Integer trackid, String token) {
        playlistDAO.removeTrackFromPlaylist(id, trackid);
        return getPlaylists();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByPlaylist(@PathParam("id") Integer id) {
        Tracks tracks = new Tracks(trackDAO.tracksByPlaylistId(id));
        return Response.ok(tracks).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, Track track) {
        Playlist playlist = new Playlist();
        playlistDAO.addTrackToPlaylist(playlist, track);
        return getPlaylists();

    }

}

