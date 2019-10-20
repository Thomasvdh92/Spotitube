package services;

import datasource.IPlaylistDAO;
import datasource.ITrackDAO;
import datasource.IOwnerDAO;
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
    private IOwnerDAO OwnerDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        Playlists playlists = new Playlists(playlistDAO.getAllPlaylists(token));
        playlists.calculateLength();
        return Response.ok(playlists, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(String body, @QueryParam("token") String token) {
        JSONObject json = new JSONObject(body);
        String name = json.getString("name");
        Owner owner = OwnerDAO.getOwnerByTokenString(token);
        playlistDAO.add(new Playlist(-1, name, owner, null), token);
        return getPlaylists(token);
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id, @QueryParam("token") String token) {
        playlistDAO.delete(id);
        return getPlaylists(token);
    }

    @PUT
    @Path("/{id}")
    public Response put(String body, @PathParam("id") int id, @QueryParam("token") String token) {
        JSONObject json = new JSONObject(body);
        String name = json.getString("name");
        Owner owner = OwnerDAO.getOwnerByTokenString(token);
        Playlist playlist = new Playlist(id, name, owner, null);
        playlistDAO.put(playlist);
        return getPlaylists(token);
    }

    @DELETE
    @Path("/{id}/tracks/{tid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@PathParam("id") Integer id, @PathParam("tid") Integer trackid, @QueryParam("token") String token) {
        playlistDAO.removeTrackFromPlaylist(id, trackid);
        return getPlaylists(token);
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
    public Response addTrackToPlaylist(@PathParam("id") Integer id, String body, @QueryParam("token") String token) {
        JSONObject json = new JSONObject(body);
        int trackid = json.getInt("id");
        String title = json.getString("title");
        String performer = json.getString("performer");
        int duration = json.getInt("duration");
        Boolean offline = json.getBoolean("offlineAvailable");

        Track track = new Track(trackid, title, performer, duration, offline);

        playlistDAO.addTrackToPlaylist(id, track, offline);
        return getPlaylists(token);

    }

}

