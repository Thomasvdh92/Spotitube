drop database if exists spotitube;
CREATE database spotitube;

use spotitube;

CREATE TABLE Owner
(
  OwnerID  INT          NOT NULL AUTO_INCREMENT,
  Username VARCHAR(100) NOT NULL,
  Password VARCHAR(100) NOT NULL,
  PRIMARY KEY (OwnerID),
  UNIQUE (Username)
);

CREATE TABLE Track
(
  TrackID          INT          NOT NULL AUTO_INCREMENT,
  title            VARCHAR(55)  NOT NULL,
  performer        VARCHAR(55)  NOT NULL,
  duration         INT          NOT NULL,
  album            VARCHAR(55)  NOT NULL,
  playcount        int          NULL,
  publicationDate  varchar(15)  NULL,
  description      VARCHAR(100) NULL,
  offlineAvailable BOOLEAN DEFAULT false,
  PRIMARY KEY (TrackID)
);

CREATE TABLE Playlist
(
  PlaylistID INT          NOT NULL AUTO_INCREMENT,
  Name       VARCHAR(100) NOT NULL,
  Owner      INT          NOT NULL,
  PRIMARY KEY (PlaylistID),
  FOREIGN KEY (Owner) REFERENCES Owner (OwnerID)
);

CREATE TABLE Playlist_Track
(
  TrackID    INT NOT NULL,
  PlaylistID INT NOT NULL,
  FOREIGN KEY (TrackID) REFERENCES Track (TrackID),
  FOREIGN KEY (PlaylistID) REFERENCES Playlist (PlaylistID) ON DELETE cascade,
  PRIMARY KEY (TrackID, PlaylistID)
);

CREATE TABLE Token_Owner
(
  Token VARCHAR(40)  NOT NULL,
  Owner VARCHAR(100) NOT NULL,
  PRIMARY KEY (Token, Owner)
);

CREATE TABLE Playlist_Owner
(
  PlaylistID INT NOT NULL,
  OwnerID    INT NOT NULL,
  FOREIGN KEY (PlaylistID) REFERENCES Playlist (PlaylistID) ON DELETE cascade,
  FOREIGN KEY (OwnerID) REFERENCES Owner (OwnerID),
  PRIMARY KEY (PlaylistID, OwnerID)
);