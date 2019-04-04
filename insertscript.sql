INSERT INTO OWNER(username, password) VALUES ('user', 'password');
INSERT INTO TRACK(trackid, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable)
    VALUES(1, 'title', 'performer', 60, 'album', 5, '01-01-2000', 'description', true);
    VALUES(2, 'title2', 'performer', 60, 'album', 5, '01-01-2000', 'description', true);
INSERT INTO Token_Owner(token, owner) VALUES ('1234-1234-1234', 'user');
INSERT INTO Playlist(playlistid, name, owner) VALUES(1, 'playlistname', 1), (2, 'playlistname2', 1), (3, 'playlistname3', 1);
INSERT INTO Playlist_Track(trackid, playlistid) VALUES (1, 1);
