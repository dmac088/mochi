﻿DROP TABLE IF EXISTS security.USER_ CASCADE;
DROP TABLE IF EXISTS security.AUTHORITY CASCADE;
DROP TABLE IF EXISTS security.USERS_AUTHORITIES CASCADE;

CREATE TABLE security.AUTHORITY (
   ID  BIGSERIAL NOT NULL,
   NAME VARCHAR(255),
   PRIMARY KEY (ID)
);

ALTER TABLE IF EXISTS security.AUTHORITY ADD CONSTRAINT AUTHORITY_NAME UNIQUE (NAME);

CREATE TABLE security.USER_ (
   ID  BIGSERIAL NOT NULL,
   ACCOUNT_EXPIRED BOOLEAN,
   ACCOUNT_LOCKED BOOLEAN,
   CREDENTIALS_EXPIRED BOOLEAN,
   ENABLED BOOLEAN,
   PASSWORD VARCHAR(255),
   USER_NAME VARCHAR(255),
   PRIMARY KEY (ID)
);

ALTER TABLE IF EXISTS security.USER_ ADD CONSTRAINT USER_USER_NAME UNIQUE (USER_NAME);

CREATE TABLE security.USERS_AUTHORITIES (
   USER_ID INT8 NOT NULL,
   AUTHORITY_ID INT8 NOT NULL,
   PRIMARY KEY (USER_ID, AUTHORITY_ID)
);

ALTER TABLE security.USERS_AUTHORITIES ADD CONSTRAINT USERS_AUTHORITIES_AUTHORITY
  FOREIGN KEY (AUTHORITY_ID) REFERENCES security.AUTHORITY;

ALTER TABLE security.USERS_AUTHORITIES ADD CONSTRAINT USERS_AUTHORITIES_USER_
  FOREIGN KEY (USER_ID) REFERENCES security.USER_;