package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.annotations.TokenRequired;
import nl.han.ica.oose.dea.spotitube.datasource.IPlaylistDAO;
import nl.han.ica.oose.dea.spotitube.datasource.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;
import nl.han.ica.oose.dea.spotitube.domain.*;
import nl.han.ica.oose.dea.spotitube.exceptions.ApplicationException;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
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
    @TokenRequired
    public Response getPlaylists(@QueryParam("token") String token) throws ApplicationException {
        Playlists playlists = new Playlists(playlistDAO.getAllPlaylists(token));
        playlists.calculateLength();
        return Response.status(Response.Status.OK).entity(playlists).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @TokenRequired
    public Response addPlaylist(Playlist playlist, @QueryParam("token") String token) throws ApplicationException, EntityNotFoundException {
        playlistDAO.add(new Playlist(-1, playlist.getName(), playlist.isOwner(), null), token);
        return Response.status(Response.Status.CREATED).entity(new Playlists(playlistDAO.getAllPlaylists(token))).build();
    }


    @DELETE
    @Path("/{id}")
    @TokenRequired
    public Response delete(@PathParam("id") int id, @QueryParam("token") String token) throws ApplicationException {
        playlistDAO.delete(id);
        return getPlaylists(token);
    }

    @PUT
    @Path("/{id}")
    @TokenRequired
    public Response put(Playlist playlist, @PathParam("id") int id, @QueryParam("token") String token) throws ApplicationException, EntityNotFoundException {
        playlistDAO.put(playlist);
        return getPlaylists(token);
    }

    @DELETE
    @Path("/{id}/tracks/{tid}")
    @Produces(MediaType.APPLICATION_JSON)
    @TokenRequired
    public Response removeTrackFromPlaylist(@PathParam("id") Integer id, @PathParam("tid") Integer trackid, @QueryParam("token") String token) throws ApplicationException {
        playlistDAO.removeTrackFromPlaylist(id, trackid);
        return getPlaylists(token);
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @TokenRequired
    public Response getTracksByPlaylist(@PathParam("id") Integer id) {
        Tracks tracks = new Tracks(trackDAO.tracksByPlaylistId(id));
        return Response.ok(tracks).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TokenRequired
    public Response addTrackToPlaylist(@PathParam("id") Integer id, Track track, @QueryParam("token") String token) throws ApplicationException {
        playlistDAO.addTrackToPlaylist(id, track);
        return Response.status(Response.Status.CREATED).entity(new Playlists(playlistDAO.getAllPlaylists(token))).build();

    }

}

