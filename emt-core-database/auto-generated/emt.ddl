CREATE TABLE `user` (
  id         varchar(255) NOT NULL, 
  password   varchar(255), 
  last_login timestamp DEFAULT '1970-01-01 00:00:01' NULL, 
  PRIMARY KEY (id));
CREATE TABLE role (
  name        varchar(45) NOT NULL, 
  description varchar(255), 
  PRIMARY KEY (name));
CREATE TABLE personalinformation (
  id           varchar(255) NOT NULL, 
  first_name   varchar(255), 
  last_name    varchar(255), 
  email        varchar(255), 
  dob          timestamp NULL, 
  gender       varchar(255), 
  civil_status varchar(255), 
  phone_number varchar(255), 
  address      varchar(255), 
  PRIMARY KEY (id));
CREATE TABLE clinical_history_format (
  id          int(10) NOT NULL AUTO_INCREMENT, 
  name        varchar(255), 
  description varchar(255), 
  disable     tinyint(1), 
  created_at  timestamp NULL, 
  PRIMARY KEY (id));
CREATE TABLE field (
  id                        int(10) NOT NULL AUTO_INCREMENT, 
  name                      varchar(255), 
  description               varchar(255), 
  type                      varchar(255), 
  clinical_history_formatid int(10) NOT NULL, 
  `index`                   int(11), 
  PRIMARY KEY (id, 
  clinical_history_formatid));
CREATE TABLE option_item (
  id                             int(10) NOT NULL AUTO_INCREMENT, 
  content                        varchar(255), 
  fieldid                        int(10) NOT NULL, 
  fieldclinical_history_formatid int(10), 
  PRIMARY KEY (id));
CREATE TABLE pacient (
  id         varchar(255) NOT NULL, 
  eps        int(11), 
  disability tinyint(1), 
  PRIMARY KEY (id));
CREATE TABLE clinical_history (
  id                        int(11) NOT NULL AUTO_INCREMENT, 
  pacientid                 varchar(255) NOT NULL, 
  payload                   json, 
  clinical_history_formatid int(10) NOT NULL, 
  created_at                timestamp NULL, 
  PRIMARY KEY (id));
CREATE TABLE user_role (
  userid   varchar(255) NOT NULL, 
  rolename varchar(45) NOT NULL, 
  PRIMARY KEY (userid, 
  rolename));
ALTER TABLE `user` ADD CONSTRAINT FKuser432238 FOREIGN KEY (id) REFERENCES personalinformation (id);
ALTER TABLE pacient ADD CONSTRAINT FKpacient150320 FOREIGN KEY (id) REFERENCES personalinformation (id);
ALTER TABLE option_item ADD CONSTRAINT FKoption_ite924721 FOREIGN KEY (fieldid, fieldclinical_history_formatid) REFERENCES field (id, clinical_history_formatid);
ALTER TABLE field ADD CONSTRAINT FKfield109767 FOREIGN KEY (clinical_history_formatid) REFERENCES clinical_history_format (id);
ALTER TABLE clinical_history ADD CONSTRAINT FKclinical_h450476 FOREIGN KEY (pacientid) REFERENCES pacient (id);
ALTER TABLE clinical_history ADD CONSTRAINT FKclinical_h693953 FOREIGN KEY (clinical_history_formatid) REFERENCES clinical_history_format (id);
ALTER TABLE user_role ADD CONSTRAINT FKuser_role954230 FOREIGN KEY (userid) REFERENCES `user` (id);
ALTER TABLE user_role ADD CONSTRAINT FKuser_role527292 FOREIGN KEY (rolename) REFERENCES role (name);
