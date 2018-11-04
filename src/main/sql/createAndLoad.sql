CREATE SCHEMA ClickStream;
SET SEARCH_PATH TO ClickStream;

CREATE TABLE click(
	action VARCHAR(50) NOT NULL,
	timestampClick BIGINT NOT NULL,
	pageUrl VARCHAR (200) NOT NULL,
	client VARCHAR (20) NOT NULL,
	userLanguage VARCHAR (20) NOT NULL,
	urlFrom VARCHAR (200) NOT NULL,
	userIp VARCHAR (15) NOT NULL,
  PRIMARY KEY(timestampClick)
);

CREATE TABLE session(
  sessionId VARCHAR (100) NOT NULL,
  userName VARCHAR (100) NOT NULL,
  userAgent varchar (100) NOT NULL,
  PRIMARY KEY(sessionId)
);

CREATE TABLE clickInSession(
	sessionId VARCHAR (100) NOT NULL REFERENCES session(sessionId),
  timestampClick BIGINT NOT NULL REFERENCES  click(timestampClick),
  PRIMARY KEY(sessionId, timestampClick)
);

CREATE TABLE videoData(
	dataId VARCHAR (100) NOT NULL,
	timestampClick BIGINT NOT NULL REFERENCES  click(timestampClick),
  currentTime DOUBLE PRECISION NOT NULL,
  playbackRate INT NOT NULL,
  paused BOOLEAN NOT NULL,
  error VARCHAR (50),
  networkState INT NOT NULL,
  readyState INT NOT NULL,
  eventTimeStamp BIGINT NOT NULL,
  initTimeStamp BIGINT NOT NULL,
  type VARCHAR (50) NOT NULL,
  prevTime DOUBLE PRECISION NOT NULL,
	PRIMARY KEY(dataId)
);