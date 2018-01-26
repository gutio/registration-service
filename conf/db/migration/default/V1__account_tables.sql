CREATE TABLE IF NOT EXISTS Account (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  accessToken VARCHAR(128) NOT NULL,
  sessionKey VARCHAR(128) NOT NULL,
  createdAt DATETIME  NOT NULL,
  updatedAt DATETIME  NOT NULL
);

CREATE UNIQUE INDEX UAccount_AccessToken ON Account(accessToken);
CREATE UNIQUE INDEX UAccount_SessionKey ON Account(sessionKey);

--

CREATE TABLE IF NOT EXISTS GoogleAccount (
  accountId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  googleId VARCHAR(128) NOT NULL,
  gmailAddress VARCHAR(128) NOT NULL,
  createdAt DATETIME  NOT NULL,
  updatedAt DATETIME  NOT NULL
);

CREATE UNIQUE INDEX UGoogleAccount_GoogleId ON GoogleAccount(googleId);
