BEGIN
TRUNCATE TABLE security.USER_ CASCADE;
TRUNCATE TABLE security.AUTHORITY CASCADE;
TRUNCATE TABLE security.USERS_AUTHORITIES;

/*----------------------Party CRUD start-----------------------------*/
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (1, 'PARTY_CREATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (2, 'PARTY_READ');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (3, 'PARTY_UPDATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (4, 'PARTY_DELETE');
/*----------------------Party CRUD end-----------------------------*/

/*----------------------Person CRUD start-----------------------------*/
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (5, 'PERSON_CREATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (6, 'PERSON_READ');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (7, 'PERSON_UPDATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (8, 'PERSON_DELETE');
/*----------------------Person CRUD end-----------------------------*/

/*----------------------Organisation CRUD start-----------------------------*/
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (9,  'ORGANISATION_CREATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (10, 'ORGANISATION_READ');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (11, 'ORGANISATION_UPDATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (12, 'ORGANISATION_DELETE');
/*----------------------Organisation CRUD end-----------------------------*/

/*----------------------Role CRUD start-----------------------------*/
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (13, 'ROLE_CREATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (14, 'ROLE_READ');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (15, 'ROLE_UPDATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (16, 'ROLE_DELETE');
/*----------------------Role CRUD end-----------------------------*/

/*----------------------Customer CRUD start-----------------------------*/
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (17, 'CUSTOMER_CREATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (18, 'CUSTOMER_READ');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (19, 'CUSTOMER_UPDATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (20, 'CUSTOMER_DELETE');
/*----------------------Customer CRUD end-----------------------------*/

/*----------------------Supplier CRUD start-----------------------------*/
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (21, 'SUPPLIER_CREATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (22, 'SUPPLIER_READ');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (23, 'SUPPLIER_UPDATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (24, 'SUPPLIER_DELETE');
/*----------------------Supplier CRUD end-----------------------------*/

/*----------------------Product CRUD start-----------------------------*/
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (25, 'PRODUCT_CREATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (26, 'PRODUCT_READ');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (27, 'PRODUCT_UPDATE');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (28, 'PRODUCT_DELETE');
/*----------------------Product CRUD end-----------------------------*/


INSERT INTO security.AUTHORITY(ID, NAME) VALUES (29, 'ROLE_PRODUCT_READER');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (30, 'ROLE_PARTY_READER');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (31, 'ROLE_ROLE_READER');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (32, 'ROLE_PERSON_READER');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (33, 'ROLE_ORGANISATION_READER');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (34, 'ROLE_CUSTOMER_READER');
INSERT INTO security.AUTHORITY(ID, NAME) VALUES (35, 'ROLE_SUPPLIER_READER');
--password salt = $2a$08$


/*----------------------user accounts start (not clients)-----------------------------*/
/*
These use BCryptPasswordEncoder(8)
*/
--for securly creating accounts
 INSERT INTO security.USER_(PTY_ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (0, 'account-creator', /*admin1234*/'$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', FALSE, FALSE, FALSE, TRUE);

INSERT INTO security.USER_(PTY_ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (1, 'admin', /*admin1234*/'$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', FALSE, FALSE, FALSE, TRUE);

INSERT INTO security.USER_(PTY_ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (2, 'reader', /*reader1234*/'$2a$08$dwYz8O.qtUXboGosJFsS4u19LHKW7aCQ0LXXuNlRfjjGKwj5NfKSe', FALSE, FALSE, FALSE, TRUE);

INSERT INTO security.USER_(PTY_ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (3, 'modifier', /*modifier1234*/'$2a$08$kPjzxewXRGNRiIuL4FtQH.mhMn7ZAFBYKB3ROz.J24IX8vDAcThsG', FALSE, FALSE, FALSE, TRUE);

INSERT INTO security.USER_(PTY_ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (4, 'reader2', /*reader1234*/'$2a$08$vVXqh6S8TqfHMs1SlNTu/.J25iUCrpGBpyGExA.9yI.IlDRadR6Ea', FALSE, FALSE, FALSE, TRUE);
/*----------------------user accounts end (not clients)-----------------------------*/

/*----------------------account-creator user start-----------------------------*/
DELETE FROM security.USERS_AUTHORITIES WHERE USER_ID = 0;
INSERT INTO security.USERS_AUTHORITIES(USER_ID, AUTHORITY_ID)
SELECT (SELECT pty_id FROM security.USER_ WHERE user_name = 'account-creator') as USER_ID,
       id as AUTHORITY_ID
FROM security.AUTHORITY
WHERE
--0=0;
--AND 

(
NAME like '%CREATE'
OR NAME like '%UPDATE'
OR NAME like '%READ'
)
AND
(
NAME like 'PARTY%'
OR NAME like 'PERSON%'
OR NAME like 'ORGANISATION%'
OR NAME like 'ROLE%'
OR NAME like 'CUSTOMER%'
OR NAME like 'SUPPLIER%'
OR NAME like 'USER%'
);


/*----------------------account-creator user end-----------------------------*/


/*----------------------admin user start-----------------------------*/
INSERT INTO security.USERS_AUTHORITIES(USER_ID, AUTHORITY_ID)
SELECT (SELECT pty_id FROM security.USER_ WHERE user_name = 'admin') as USER_ID,
       id as AUTHORITY_ID
FROM security.AUTHORITY;
/*----------------------admin user end-----------------------------*/

/*----------------------reader user start-----------------------------*/
INSERT INTO security.USERS_AUTHORITIES(USER_ID, AUTHORITY_ID)
SELECT (SELECT pty_id FROM security.USER_ WHERE user_name = 'reader') as USER_ID,
       id as AUTHORITY_ID
FROM security.AUTHORITY
WHERE NAME like '%READ';
/*----------------------reader user end-----------------------------*/


/*----------------------modifier user start-----------------------------*/
INSERT INTO security.USERS_AUTHORITIES(USER_ID, AUTHORITY_ID)
SELECT (SELECT pty_id FROM security.USER_ WHERE user_name = 'modifier') as USER_ID,
       id as AUTHORITY_ID
FROM security.AUTHORITY
WHERE NAME like '%CREATE'
OR NAME like '%UPDATE'
OR NAME like '%DELETE';
/*----------------------modifier user end-----------------------------*/

END
