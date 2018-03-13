
/* Drop Indexes */

DROP INDEX IF EXISTS role_role_typ_id_role_start_dttm_party_id_key;
DROP INDEX IF EXISTS spring_session_ix1;



/* Drop Tables */

DROP TABLE IF EXISTS mochi.customer;
DROP TABLE IF EXISTS mochi.point_of_interest;
DROP TABLE IF EXISTS mochi.obj;
DROP TABLE IF EXISTS mochi.object_type;
DROP TABLE IF EXISTS mochi.order_line;
DROP TABLE IF EXISTS mochi.orders;
DROP TABLE IF EXISTS mochi.person;
DROP TABLE IF EXISTS mochi.role;
DROP TABLE IF EXISTS mochi.party;
DROP TABLE IF EXISTS mochi.party_type;
DROP TABLE IF EXISTS mochi.person_role;
DROP TABLE IF EXISTS mochi.product;
DROP TABLE IF EXISTS mochi.role_type;
DROP TABLE IF EXISTS mochi.spring_session_attributes;
DROP TABLE IF EXISTS mochi.spring_session;



/* Drop Sequences */

DROP SEQUENCE IF EXISTS mochi.hibernate_sequence;
DROP SEQUENCE IF EXISTS mochi.party_party_id_seq;
DROP SEQUENCE IF EXISTS mochi.party_type_pty_typ_id_seq;
DROP SEQUENCE IF EXISTS mochi.person_id_seq;
DROP SEQUENCE IF EXISTS mochi.role_type_role_typ_id_seq;
DROP SEQUENCE IF EXISTS mochi.thing_id_seq;




/* Create Sequences */

CREATE SEQUENCE mochi.hibernate_sequence INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 232 CACHE 1;
CREATE SEQUENCE mochi.party_party_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE SEQUENCE mochi.person_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 6 CACHE 1;
CREATE SEQUENCE mochi.role_type_role_typ_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE SEQUENCE mochi.thing_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 9 CACHE 1;



/* Create Tables */

CREATE TABLE mochi.customer
(
	rle_id bigint NOT NULL,
	cst_id char(10) UNIQUE,
	CONSTRAINT customer_pkey PRIMARY KEY (rle_id)
) WITHOUT OIDS;


CREATE TABLE mochi.obj
(
	object_id bigint NOT NULL,
	object_type_id bigint NOT NULL UNIQUE,
	CONSTRAINT object_pkey PRIMARY KEY (object_id)
) WITHOUT OIDS;


CREATE TABLE mochi.object_type
(
	object_type_id bigint NOT NULL,
	object_type_desc varchar(100) NOT NULL UNIQUE,
	CONSTRAINT object_type_pkey PRIMARY KEY (object_type_id)
) WITHOUT OIDS;


CREATE TABLE mochi.orders
(
	ord_id bigint NOT NULL,
	pty_id bigint NOT NULL UNIQUE,
	CONSTRAINT orders_pkey PRIMARY KEY (ord_id)
) WITHOUT OIDS;


CREATE TABLE mochi.order_line
(
	ord_id bigint NOT NULL UNIQUE,
	prd_id bigint NOT NULL UNIQUE,
	ord_lne_no bigint NOT NULL,
	CONSTRAINT order_line_pkey PRIMARY KEY (ord_id, prd_id, ord_lne_no)
) WITHOUT OIDS;


CREATE TABLE mochi.party
(
	pty_id bigserial NOT NULL,
	pty_typ_id bigint NOT NULL,
	pty_usr_nm text NOT NULL UNIQUE,
	pty_pwd text NOT NULL,
	CONSTRAINT party_pkey PRIMARY KEY (pty_id)
) WITHOUT OIDS;


CREATE TABLE mochi.party_type
(
	pty_typ_id bigserial NOT NULL,
	pty_typ_desc varchar NOT NULL UNIQUE,
	CONSTRAINT party_type_pkey PRIMARY KEY (pty_typ_id)
) WITHOUT OIDS;


CREATE TABLE mochi.person
(
	psn_id bigint NOT NULL UNIQUE,
	psn_gvn_nm_en varchar,
	psn_fml_nm_en varchar,
	psn_nm_cn varchar,
	enb boolean
) WITHOUT OIDS;


CREATE TABLE mochi.person_role
(
	person_role_id bigint NOT NULL,
	person_role_desc varchar(100) NOT NULL UNIQUE,
	CONSTRAINT person_role_pkey PRIMARY KEY (person_role_id)
) WITHOUT OIDS;


CREATE TABLE mochi.point_of_interest
(
	point_of_interest_id bigint NOT NULL,
	lat float,
	lng float,
	name varchar(255),
	type varchar(255),
	CONSTRAINT poi_pkey PRIMARY KEY (point_of_interest_id)
) WITHOUT OIDS;


CREATE TABLE mochi.product
(
	prd_id bigint NOT NULL,
	prd_desc varchar(100),
	prd_img_pth varchar(100),
	prd_rrp numeric,
	CONSTRAINT product_pkey PRIMARY KEY (prd_id)
) WITHOUT OIDS;


CREATE TABLE mochi.role
(
	rle_id bigint NOT NULL,
	rle_typ_id bigint NOT NULL,
	rle_start_dttm date NOT NULL,
	pty_id bigint NOT NULL UNIQUE,
	CONSTRAINT role_pkey PRIMARY KEY (rle_id)
) WITHOUT OIDS;


CREATE TABLE mochi.role_type
(
	rle_typ_id bigserial NOT NULL,
	rle_typ_desc varchar NOT NULL UNIQUE,
	CONSTRAINT role_type_pkey PRIMARY KEY (rle_typ_id)
) WITHOUT OIDS;


CREATE TABLE mochi.spring_session
(
	session_id char(36) NOT NULL,
	creation_time bigint NOT NULL,
	last_access_time bigint NOT NULL,
	max_inactive_interval int NOT NULL,
	principal_name varchar(100),
	CONSTRAINT spring_session_pk PRIMARY KEY (session_id)
) WITHOUT OIDS;


CREATE TABLE mochi.spring_session_attributes
(
	session_id char(36) NOT NULL,
	attribute_name varchar(200) NOT NULL,
	attribute_bytes bytea,
	CONSTRAINT spring_session_attributes_pk PRIMARY KEY (session_id, attribute_name)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE mochi.point_of_interest
	ADD CONSTRAINT fk_object_point_of_interest FOREIGN KEY (point_of_interest_id)
	REFERENCES mochi.obj (object_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.obj
	ADD CONSTRAINT obj_object_type_id_fkey FOREIGN KEY (object_type_id)
	REFERENCES mochi.object_type (object_type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.order_line
	ADD CONSTRAINT order_line_order_id_fkey FOREIGN KEY (ord_id)
	REFERENCES mochi.orders (ord_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.orders
	ADD CONSTRAINT orders_party_id_fkey FOREIGN KEY (pty_id)
	REFERENCES mochi.party (pty_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.person
	ADD CONSTRAINT person_person_id_fkey FOREIGN KEY (psn_id)
	REFERENCES mochi.party (pty_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.role
	ADD CONSTRAINT role_party_id_fkey FOREIGN KEY (pty_id)
	REFERENCES mochi.party (pty_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.party
	ADD CONSTRAINT party_pty_typ_id_fkey FOREIGN KEY (pty_typ_id)
	REFERENCES mochi.party_type (pty_typ_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.order_line
	ADD CONSTRAINT order_line_product_id_fkey FOREIGN KEY (prd_id)
	REFERENCES mochi.product (prd_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.customer
	ADD CONSTRAINT customer_role_id_fkey FOREIGN KEY (rle_id)
	REFERENCES mochi.role (rle_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.role
	ADD CONSTRAINT role_role_typ_id_fkey FOREIGN KEY (rle_typ_id)
	REFERENCES mochi.role_type (rle_typ_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mochi.spring_session_attributes
	ADD CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_id)
	REFERENCES mochi.spring_session (session_id)
	ON UPDATE NO ACTION
	ON DELETE CASCADE
;



/* Create Indexes */

CREATE UNIQUE INDEX role_role_typ_id_role_start_dttm_party_id_key ON mochi.role USING BTREE (rle_typ_id, rle_start_dttm, pty_id);
CREATE INDEX spring_session_ix1 ON mochi.spring_session USING BTREE (last_access_time);



