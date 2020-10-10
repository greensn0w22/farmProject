DROP IF EXIST farmProject;
CREATE DATABASE farmProject;
USE farmProject;

CREATE TABLE field (
    field_pk integer NOT NULL AUTO_INCREMENT,
    field_name varchar(45) NOT NULL,
    field_number integer NOT NULL,
    PRIMARY KEY (field_pk)
);

CREATE TABLE tonnage (
    tonnage_pk integer NOT NULL AUTO_INCREMENT,
    date_entry datetime NOT NULL,
    number_of_pounds integer NOT NULL,
    is_valid bool NOT NULL,
    usager_fk integer NOT NULL,
    field_fk integer NOT NULL,
    PRIMARY KEY (tonnage_pk)
);

CREATE TABLE usager (
    usager_pk integer NOT NULL AUTO_INCREMENT,
    first_name varchar(45) NOT NULL,
    last_name varchar(45) NOT NULL,
    api_key varchar(100) NOT NULL,
    PRIMARY KEY (usager_pk)
);

CREATE TABLE usager_field (
    usager_field_pk integer NOT NULL AUTO_INCREMENT,
    usager_fk integer NOT NULL,
    field_fk integer NOT NULL,
    percent_of_field float NOT NULL,
    PRIMARY KEY (usager_field_pk)
);

INSERT INTO field (field_name,field_number) VALUES ('champ 1',1),('champ 2',2),('champ 3',3),('champ 4',4);
INSERT INTO  usager (first_name,last_name,api_key) VALUES ('nugget','chong','DSDF##Hdfgd33d9867vcd543sd3@@aasd@#dss2sd35sdd11c'),('nix','guay','d4G6gh6GF$dfAQSd1zDSF5GHUU&&*5tg3eded2323/**/*'),('jemy','goul','DSDF##Hdfgd33d9867vcd543sd3@@aasd@#dss2sd35sdd11c');
INSERT INTO tonnage (date_entry,number_of_pounds,is_valid,usager_fk,field_fk) VALUES (NOW(),345678,1,1,false),(NOW(),3111,1,2,true),(NOW(),344,2,1,false);
INSERT INTO usager_field (usager_fk,field_fk,percent_of_field) VALUES (1,1,100),(1,2,50),(2,2,50);
