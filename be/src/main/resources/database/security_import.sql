--BEGIN;
TRUNCATE TABLE security.USER_ CASCADE;
TRUNCATE TABLE security.USER_ROLE CASCADE;
TRUNCATE TABLE security.PERMISSION CASCADE;
TRUNCATE TABLE security.ROLE CASCADE;
TRUNCATE TABLE security.ROLE_PERMISSION CASCADE;

/*----------------------INSERT THE ROLES-----------------------------*/
--remmeber it does not matter what the role name is since the role name is not
--used byt he application it simply aggregates the PERMISSIONS
--so when we insert a new user we simply add a row to security.user_role
--instead of 100s of rows to a table such as user_permissions (which i've deprecated)

INSERT INTO security.ROLE(ID, NAME) VALUES (1, 'ADMIN');
INSERT INTO security.ROLE(ID, NAME) VALUES (2, 'CUSTOMER');


INSERT INTO security.USER_ROLE(pty_id, role_id) VALUES (1, 1);

/*----------------------Party CRUD start-----------------------------*/
INSERT INTO security.PERMISSION(ID, NAME) VALUES (1, 'PARTY_CREATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (2, 'PARTY_READ');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (3, 'PARTY_UPDATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (4, 'PARTY_DELETE');
/*----------------------Party CRUD end-----------------------------*/

/*----------------------Person CRUD start-----------------------------*/
INSERT INTO security.PERMISSION(ID, NAME) VALUES (5, 'PERSON_CREATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (6, 'PERSON_READ');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (7, 'PERSON_UPDATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (8, 'PERSON_DELETE');
/*----------------------Person CRUD end-----------------------------*/

/*----------------------Organisation CRUD start-----------------------------*/
INSERT INTO security.PERMISSION(ID, NAME) VALUES (9,  'ORGANISATION_CREATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (10, 'ORGANISATION_READ');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (11, 'ORGANISATION_UPDATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (12, 'ORGANISATION_DELETE');
/*----------------------Organisation CRUD end-----------------------------*/

/*----------------------Role CRUD start-----------------------------*/
INSERT INTO security.PERMISSION(ID, NAME) VALUES (13, 'ROLE_CREATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (14, 'ROLE_READ');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (15, 'ROLE_UPDATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (16, 'ROLE_DELETE');
/*----------------------Role CRUD end-----------------------------*/

/*----------------------Customer CRUD start-----------------------------*/
INSERT INTO security.PERMISSION(ID, NAME) VALUES (17, 'CUSTOMER_CREATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (18, 'CUSTOMER_READ');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (19, 'CUSTOMER_UPDATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (20, 'CUSTOMER_DELETE');
/*----------------------Customer CRUD end-----------------------------*/

/*----------------------Supplier CRUD start-----------------------------*/
INSERT INTO security.PERMISSION(ID, NAME) VALUES (21, 'SUPPLIER_CREATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (22, 'SUPPLIER_READ');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (23, 'SUPPLIER_UPDATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (24, 'SUPPLIER_DELETE');
/*----------------------Supplier CRUD end-----------------------------*/

/*----------------------Product CRUD start-----------------------------*/
INSERT INTO security.PERMISSION(ID, NAME) VALUES (25, 'PRODUCT_CREATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (26, 'PRODUCT_READ');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (27, 'PRODUCT_UPDATE');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (28, 'PRODUCT_DELETE');
/*----------------------Product CRUD end-----------------------------*/

/*----------------------Reading Arrays-----------------------------*/
INSERT INTO security.PERMISSION(ID, NAME) VALUES (29, 'PRODUCT_READER');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (30, 'PARTY_READER');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (31, 'ROLE_READER');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (32, 'PERSON_READER');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (33, 'ORGANISATION_READER');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (34, 'CUSTOMER_READER');
INSERT INTO security.PERMISSION(ID, NAME) VALUES (35, 'SUPPLIER_READER');
/*----------------------Reading Arrays-----------------------------*/


--password salt = $2a$08$


/*----------------------user accounts start (not clients)-----------------------------*/
/*
These use BCryptPasswordEncoder(8)
*/

INSERT INTO security.USER_(PTY_ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (1, 'admin', /*admin1234*/'$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', FALSE, FALSE, FALSE, TRUE);

/*
INSERT INTO security.USER_(PTY_ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (2, 'reader', /*reader1234*/'$2a$08$dwYz8O.qtUXboGosJFsS4u19LHKW7aCQ0LXXuNlRfjjGKwj5NfKSe', FALSE, FALSE, FALSE, TRUE);

INSERT INTO security.USER_(PTY_ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (3, 'modifier', /*modifier1234*/'$2a$08$kPjzxewXRGNRiIuL4FtQH.mhMn7ZAFBYKB3ROz.J24IX8vDAcThsG', FALSE, FALSE, FALSE, TRUE);

INSERT INTO security.USER_(PTY_ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (4, 'reader2', /*reader1234*/'$2a$08$vVXqh6S8TqfHMs1SlNTu/.J25iUCrpGBpyGExA.9yI.IlDRadR6Ea', FALSE, FALSE, FALSE, TRUE);
  */
/*----------------------user accounts end (not clients)-----------------------------*/


/*----------------------admin user start-----------------------------*/
INSERT INTO security.ROLE_PERMISSION(ROLE_ID, PERMISSION_ID)
SELECT (SELECT id FROM security.ROLE WHERE name = 'ADMIN') as ROLE_ID,
       id as AUTHORITY_ID
FROM security.PERMISSION;
/*----------------------admin user end-----------------------------*/

/*----------------------customer start-----------------------------*/
INSERT INTO security.ROLE_PERMISSION(ROLE_ID, PERMISSION_ID)
SELECT (SELECT id FROM security.ROLE WHERE name = 'CUSTOMER') as USER_ID,
       id as AUTHORITY_ID
FROM security.PERMISSION
WHERE NAME like '%READ';
/*----------------------customer end-----------------------------*/

--END;
