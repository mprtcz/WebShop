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

/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) NOT NULL,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL,
  PRIMARY KEY (series)
);

CREATE TABLE ITEMS (
  id          BIGINT      NOT NULL AUTO_INCREMENT,
  item_Name   VARCHAR(30) NOT NULL,
  price       BIGINT      NOT NULL,
  item        BIGINT      NOT NULL,
  description VARCHAR(500),

  PRIMARY KEY (id)
);

CREATE TABLE RECORDS (
  id               BIGINT      NOT NULL AUTO_INCREMENT,
  item_Name        VARCHAR(30) NOT NULL,
  price            BIGINT      NOT NULL,
  quantity         BIGINT      NOT NULL,
  transaction_time DATETIME    NOT NULL,
  original_item_id INTEGER     NOT NULL,
  buyer_id         INTEGER     NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE APP_USER_RECORD (
  user_id   BIGINT NOT NULL,
  record_id BIGINT NOT NULL,
  CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES APP_USER (id),
  CONSTRAINT FK_RECORD_ID FOREIGN KEY (record_id) REFERENCES RECORDS (id)
);