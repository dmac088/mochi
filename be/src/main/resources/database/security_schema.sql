--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.14
-- Dumped by pg_dump version 9.5.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE ONLY security.user_role DROP CONSTRAINT user_role_user_;
ALTER TABLE ONLY security.user_role DROP CONSTRAINT user_role_role;
ALTER TABLE ONLY security.role_permission DROP CONSTRAINT role_permission_role;
ALTER TABLE ONLY security.role_permission DROP CONSTRAINT role_permission_permission;
ALTER TABLE ONLY security.user_ DROP CONSTRAINT user_user_name;
ALTER TABLE ONLY security.user_role DROP CONSTRAINT user_role_pkey;
ALTER TABLE ONLY security.user_ DROP CONSTRAINT user___pkey;
ALTER TABLE ONLY security.role DROP CONSTRAINT role_pkey;
ALTER TABLE ONLY security.role_permission DROP CONSTRAINT role_permission_pkey;
ALTER TABLE ONLY security.oauth_client_token DROP CONSTRAINT oauth_client_token_pkey;
ALTER TABLE ONLY security.oauth_client_details DROP CONSTRAINT oauth_client_details_pkey;
ALTER TABLE ONLY security.oauth_access_token DROP CONSTRAINT oauth_access_token_pkey;
ALTER TABLE ONLY security.clientdetails DROP CONSTRAINT clientdetails_pkey;
ALTER TABLE ONLY security.permission DROP CONSTRAINT authority_pkey;
ALTER TABLE ONLY security.permission DROP CONSTRAINT authority_name;
ALTER TABLE security.permission ALTER COLUMN id DROP DEFAULT;
DROP TABLE security.user_role;
DROP SEQUENCE security.user_role_id_seq;
DROP TABLE security.user_;
DROP TABLE security.role_permission;
DROP SEQUENCE security.role_permission_id_seq;
DROP TABLE security.role;
DROP SEQUENCE security.role_id_seq;
DROP TABLE security.oauth_refresh_token;
DROP TABLE security.oauth_code;
DROP TABLE security.oauth_client_token;
DROP TABLE security.oauth_client_details;
DROP TABLE security.oauth_approvals;
DROP TABLE security.oauth_access_token;
DROP TABLE security.clientdetails;
DROP SEQUENCE security.authority_id_seq;
DROP TABLE security.permission;
DROP SCHEMA security;
--
-- Name: security; Type: SCHEMA; Schema: -; Owner: security_owner
--

CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO security_owner;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: permission; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.permission (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE security.permission OWNER TO security_owner;

--
-- Name: authority_id_seq; Type: SEQUENCE; Schema: security; Owner: security_owner
--

CREATE SEQUENCE security.authority_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.authority_id_seq OWNER TO security_owner;

--
-- Name: authority_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: security_owner
--

ALTER SEQUENCE security.authority_id_seq OWNED BY security.permission.id;


--
-- Name: clientdetails; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.clientdetails (
    appid character varying(256) NOT NULL,
    resourceids character varying(256),
    appsecret character varying(256),
    scope character varying(256),
    granttypes character varying(256),
    redirecturl character varying(256),
    authorities character varying(256),
    access_token_validity integer,
    refresh_token_validity integer,
    additionalinformation character varying(4096),
    autoapprovescopes character varying(256)
);


ALTER TABLE security.clientdetails OWNER TO security_owner;

--
-- Name: oauth_access_token; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_access_token (
    token_id character varying(256),
    token bytea,
    authentication_id character varying(256) NOT NULL,
    user_name character varying(256),
    client_id character varying(256),
    authentication bytea,
    refresh_token character varying(256)
);


ALTER TABLE security.oauth_access_token OWNER TO security_owner;

--
-- Name: oauth_approvals; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_approvals (
    userid character varying(256),
    clientid character varying(256),
    scope character varying(256),
    status character varying(10),
    expiresat timestamp without time zone,
    lastmodifiedat timestamp without time zone
);


ALTER TABLE security.oauth_approvals OWNER TO security_owner;

--
-- Name: oauth_client_details; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_client_details (
    client_id character varying(256) NOT NULL,
    resource_ids character varying(256),
    client_secret character varying(256),
    scope character varying(256),
    authorized_grant_types character varying(256),
    web_server_redirect_uri character varying(256),
    authorities character varying(256),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information character varying(4096),
    autoapprove character varying(256)
);


ALTER TABLE security.oauth_client_details OWNER TO security_owner;

--
-- Name: oauth_client_token; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_client_token (
    token_id character varying(256),
    token bytea,
    authentication_id character varying(256) NOT NULL,
    user_name character varying(256),
    client_id character varying(256)
);


ALTER TABLE security.oauth_client_token OWNER TO security_owner;

--
-- Name: oauth_code; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_code (
    code character varying(256),
    authentication bytea
);


ALTER TABLE security.oauth_code OWNER TO security_owner;

--
-- Name: oauth_refresh_token; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_refresh_token (
    token_id character varying(256),
    token bytea,
    authentication bytea
);


ALTER TABLE security.oauth_refresh_token OWNER TO security_owner;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: security; Owner: security_owner
--

CREATE SEQUENCE security.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.role_id_seq OWNER TO security_owner;

--
-- Name: role; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.role (
    id bigint DEFAULT nextval('security.role_id_seq'::regclass) NOT NULL,
    name character varying(40)
);


ALTER TABLE security.role OWNER TO security_owner;

--
-- Name: role_permission_id_seq; Type: SEQUENCE; Schema: security; Owner: security_owner
--

CREATE SEQUENCE security.role_permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.role_permission_id_seq OWNER TO security_owner;

--
-- Name: role_permission; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.role_permission (
    id bigint DEFAULT nextval('security.role_permission_id_seq'::regclass) NOT NULL,
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL
);


ALTER TABLE security.role_permission OWNER TO security_owner;

--
-- Name: user_; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.user_ (
    pty_id bigint NOT NULL,
    account_expired boolean,
    account_locked boolean,
    credentials_expired boolean,
    enabled boolean,
    password character varying(255),
    user_name character varying(255)
);


ALTER TABLE security.user_ OWNER TO security_owner;

--
-- Name: user_role_id_seq; Type: SEQUENCE; Schema: security; Owner: security_owner
--

CREATE SEQUENCE security.user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.user_role_id_seq OWNER TO security_owner;

--
-- Name: user_role; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.user_role (
    id bigint DEFAULT nextval('security.user_role_id_seq'::regclass) NOT NULL,
    pty_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE security.user_role OWNER TO security_owner;

--
-- Name: id; Type: DEFAULT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.permission ALTER COLUMN id SET DEFAULT nextval('security.authority_id_seq'::regclass);


--
-- Name: authority_name; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.permission
    ADD CONSTRAINT authority_name UNIQUE (name);


--
-- Name: authority_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.permission
    ADD CONSTRAINT authority_pkey PRIMARY KEY (id);


--
-- Name: clientdetails_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.clientdetails
    ADD CONSTRAINT clientdetails_pkey PRIMARY KEY (appid);


--
-- Name: oauth_access_token_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.oauth_access_token
    ADD CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id);


--
-- Name: oauth_client_details_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.oauth_client_details
    ADD CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id);


--
-- Name: oauth_client_token_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.oauth_client_token
    ADD CONSTRAINT oauth_client_token_pkey PRIMARY KEY (authentication_id);


--
-- Name: role_permission_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.role_permission
    ADD CONSTRAINT role_permission_pkey PRIMARY KEY (id);


--
-- Name: role_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: user___pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_
    ADD CONSTRAINT user___pkey PRIMARY KEY (pty_id);


--
-- Name: user_role_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);


--
-- Name: user_user_name; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_
    ADD CONSTRAINT user_user_name UNIQUE (user_name);


--
-- Name: role_permission_permission; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.role_permission
    ADD CONSTRAINT role_permission_permission FOREIGN KEY (permission_id) REFERENCES security.permission(id);


--
-- Name: role_permission_role; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.role_permission
    ADD CONSTRAINT role_permission_role FOREIGN KEY (role_id) REFERENCES security.role(id);


--
-- Name: user_role_role; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_role
    ADD CONSTRAINT user_role_role FOREIGN KEY (role_id) REFERENCES security.role(id);


--
-- Name: user_role_user_; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_role
    ADD CONSTRAINT user_role_user_ FOREIGN KEY (pty_id) REFERENCES security.user_(pty_id);


--
-- Name: SCHEMA security; Type: ACL; Schema: -; Owner: security_owner
--

REVOKE ALL ON SCHEMA security FROM PUBLIC;
REVOKE ALL ON SCHEMA security FROM security_owner;
GRANT ALL ON SCHEMA security TO security_owner;
GRANT USAGE ON SCHEMA security TO mochi_app;
GRANT USAGE ON SCHEMA security TO security_app;


--
-- Name: TABLE permission; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.permission FROM PUBLIC;
REVOKE ALL ON TABLE security.permission FROM security_owner;
GRANT ALL ON TABLE security.permission TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.permission TO security_app;
GRANT SELECT ON TABLE security.permission TO mochi_app;


--
-- Name: SEQUENCE authority_id_seq; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON SEQUENCE security.authority_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE security.authority_id_seq FROM security_owner;
GRANT ALL ON SEQUENCE security.authority_id_seq TO security_owner;
GRANT SELECT,USAGE ON SEQUENCE security.authority_id_seq TO mochi_app;


--
-- Name: TABLE clientdetails; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.clientdetails FROM PUBLIC;
REVOKE ALL ON TABLE security.clientdetails FROM security_owner;
GRANT ALL ON TABLE security.clientdetails TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.clientdetails TO security_app;


--
-- Name: TABLE oauth_access_token; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.oauth_access_token FROM PUBLIC;
REVOKE ALL ON TABLE security.oauth_access_token FROM security_owner;
GRANT ALL ON TABLE security.oauth_access_token TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_access_token TO security_app;


--
-- Name: TABLE oauth_approvals; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.oauth_approvals FROM PUBLIC;
REVOKE ALL ON TABLE security.oauth_approvals FROM security_owner;
GRANT ALL ON TABLE security.oauth_approvals TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_approvals TO security_app;


--
-- Name: TABLE oauth_client_details; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.oauth_client_details FROM PUBLIC;
REVOKE ALL ON TABLE security.oauth_client_details FROM security_owner;
GRANT ALL ON TABLE security.oauth_client_details TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_client_details TO security_app;


--
-- Name: TABLE oauth_client_token; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.oauth_client_token FROM PUBLIC;
REVOKE ALL ON TABLE security.oauth_client_token FROM security_owner;
GRANT ALL ON TABLE security.oauth_client_token TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_client_token TO security_app;


--
-- Name: TABLE oauth_code; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.oauth_code FROM PUBLIC;
REVOKE ALL ON TABLE security.oauth_code FROM security_owner;
GRANT ALL ON TABLE security.oauth_code TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_code TO security_app;


--
-- Name: TABLE oauth_refresh_token; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.oauth_refresh_token FROM PUBLIC;
REVOKE ALL ON TABLE security.oauth_refresh_token FROM security_owner;
GRANT ALL ON TABLE security.oauth_refresh_token TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_refresh_token TO security_app;


--
-- Name: SEQUENCE role_id_seq; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON SEQUENCE security.role_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE security.role_id_seq FROM security_owner;
GRANT ALL ON SEQUENCE security.role_id_seq TO security_owner;
GRANT SELECT,USAGE ON SEQUENCE security.role_id_seq TO mochi_app;


--
-- Name: TABLE role; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.role FROM PUBLIC;
REVOKE ALL ON TABLE security.role FROM security_owner;
GRANT ALL ON TABLE security.role TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.role TO security_app;
GRANT SELECT ON TABLE security.role TO mochi_app;


--
-- Name: SEQUENCE role_permission_id_seq; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON SEQUENCE security.role_permission_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE security.role_permission_id_seq FROM security_owner;
GRANT ALL ON SEQUENCE security.role_permission_id_seq TO security_owner;
GRANT SELECT,USAGE ON SEQUENCE security.role_permission_id_seq TO mochi_app;


--
-- Name: TABLE role_permission; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.role_permission FROM PUBLIC;
REVOKE ALL ON TABLE security.role_permission FROM security_owner;
GRANT ALL ON TABLE security.role_permission TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.role_permission TO security_app;
GRANT SELECT ON TABLE security.role_permission TO mochi_app;


--
-- Name: TABLE user_; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.user_ FROM PUBLIC;
REVOKE ALL ON TABLE security.user_ FROM security_owner;
GRANT ALL ON TABLE security.user_ TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_ TO mochi_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_ TO security_app;


--
-- Name: SEQUENCE user_role_id_seq; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON SEQUENCE security.user_role_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE security.user_role_id_seq FROM security_owner;
GRANT ALL ON SEQUENCE security.user_role_id_seq TO security_owner;
GRANT SELECT,USAGE ON SEQUENCE security.user_role_id_seq TO mochi_app;


--
-- Name: TABLE user_role; Type: ACL; Schema: security; Owner: security_owner
--

REVOKE ALL ON TABLE security.user_role FROM PUBLIC;
REVOKE ALL ON TABLE security.user_role FROM security_owner;
GRANT ALL ON TABLE security.user_role TO security_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_role TO mochi_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_role TO security_app;


--
-- PostgreSQL database dump complete
--

