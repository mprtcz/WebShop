/*All User's gets stored in APP_USER table*/
CREATE TABLE IF NOT EXISTS APP_USER (
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  sso_id     VARCHAR(30)  NOT NULL,
  password   VARCHAR(100) NOT NULL,
  first_name VARCHAR(30)  NOT NULL,
  last_name  VARCHAR(30)  NOT NULL,
  email      VARCHAR(30)  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (sso_id)
);

/*ITEMS table containing all items */
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

/* JOIN TABLE for MANY-TO-MANY relationship*/
CREATE TABLE ITEM3 (
  id               BIGINT   NOT NULL AUTO_INCREMENT,
  user_id          BIGINT   NOT NULL,
  item_id          BIGINT   NOT NULL,
  item_price       BIGINT   NOT NULL,
  transaction_time DATETIME NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
  CONSTRAINT FK_ITEMS FOREIGN KEY (item_id) REFERENCES ITEMS (id)
);

CREATE TABLE ITEM4 (
  user_id          BIGINT   NOT NULL,
  item_id          BIGINT   NOT NULL,
  item_price       BIGINT   NOT NULL,
  transaction_time DATETIME NOT NULL,
  CONSTRAINT FK_USER4 FOREIGN KEY (user_id) REFERENCES APP_USER (id),
  CONSTRAINT FK_ITEMS4 FOREIGN KEY (item_id) REFERENCES ITEMS (id)
);

CREATE TABLE APP_USER_RECORD (
  user_id   BIGINT NOT NULL,
  record_id BIGINT NOT NULL,
  CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES APP_USER (id),
  CONSTRAINT FK_RECORD_ID FOREIGN KEY (record_id) REFERENCES RECORDS (id)
);