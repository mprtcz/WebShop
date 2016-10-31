/*All User's gets stored in APP_USER table*/
CREATE TABLE APP_USER (
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  sso_id     VARCHAR(30)  NOT NULL,
  password   VARCHAR(100) NOT NULL,
  first_name VARCHAR(30)  NOT NULL,
  last_name  VARCHAR(30)  NOT NULL,
  email      VARCHAR(30)  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (sso_id)
);

/* USER_PROFILE table contains all possible roles */
CREATE TABLE USER_PROFILE (
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  type VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (type)
);

/* JOIN TABLE for MANY-TO-MANY relationship*/
CREATE TABLE APP_USER_USER_PROFILE (
  user_id         BIGINT NOT NULL,
  user_profile_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, user_profile_id),
  CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
  CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);

/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE (type)
VALUES ('SELLER');

INSERT INTO USER_PROFILE (type)
VALUES ('ADMIN');

INSERT INTO USER_PROFILE (type)
VALUES ('CUSTOMER');


/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO APP_USER (sso_id, password, first_name, last_name, email)
VALUES ('sam', '$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Sam', 'Smith', 'samy@xyz.com');


/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT
    user.id,
    profile.id
  FROM app_user user, user_profile profile
  WHERE user.sso_id = 'sam' AND profile.type = 'ADMIN';

/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) NOT NULL,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL,
  PRIMARY KEY (series)
);