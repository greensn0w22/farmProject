CREATE DATABASE ferme CHARACTER SET utf8 COLLATE utf8_general_ci;
USE ferme;

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(50),
  last_name varchar(50),
  PRIMARY KEY (id)
);

INSERT INTO user (first_name, last_name) VALUES
("Matt","Lazana"),
("Je","Go");