INSERT INTO USER_PROFILE (type)
VALUES ('ADMIN');

INSERT INTO USER_PROFILE (type)
VALUES ('CUSTOMER');

INSERT INTO APP_USER (sso_id, password, first_name, last_name, email)
VALUES ('admin', '$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Sam', 'Smith', 'samy@xyz.com');

/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT
    user.id,
    profile.id
  FROM app_user user, user_profile profile
  WHERE user.sso_id = 'sam' AND profile.type = 'ADMIN';

INSERT INTO ITEMS (id, item_Name, price, item, description)
    VALUES (1, 'Some item', 10, 5, 'This is a test item');