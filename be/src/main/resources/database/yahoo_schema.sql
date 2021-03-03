--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = yahoo, pg_catalog;

ALTER TABLE ONLY yahoo.tbl_lcl_smart_weight_step DROP CONSTRAINT uc_lcl_smart_weight_step;
ALTER TABLE ONLY yahoo.tbl_lcl_smart DROP CONSTRAINT uc_lcl_smart;
ALTER TABLE ONLY yahoo.tbl_lcl_registered_weight_step DROP CONSTRAINT uc_lcl_registered_weight_step;
ALTER TABLE ONLY yahoo.tbl_lcl_registered DROP CONSTRAINT uc_lcl_registered;
ALTER TABLE ONLY yahoo.tbl_lcl_parcel_weight_step DROP CONSTRAINT uc_lcl_parcel_weight_step;
ALTER TABLE ONLY yahoo.tbl_lcl_parcel DROP CONSTRAINT uc_lcl_parcel;
ALTER TABLE ONLY yahoo.tbl_lcl_courier_weight_step DROP CONSTRAINT uc_lcl_courier_weight_step;
ALTER TABLE ONLY yahoo.tbl_lcl_courier DROP CONSTRAINT uc_lcl_courier;
ALTER TABLE ONLY yahoo.tbl_int_surparcel_weight_step DROP CONSTRAINT uc_int_surparcel_weight_step;
ALTER TABLE ONLY yahoo.tbl_int_surparcel DROP CONSTRAINT uc_int_surparcel;
ALTER TABLE ONLY yahoo.tbl_int_speedpost_weight_step DROP CONSTRAINT uc_int_speedpost_weight_step;
ALTER TABLE ONLY yahoo.tbl_int_speedpost DROP CONSTRAINT uc_int_speedpost;
ALTER TABLE ONLY yahoo.tbl_int_registered_weight_step DROP CONSTRAINT uc_int_registered_weight_step;
ALTER TABLE ONLY yahoo.tbl_int_registered DROP CONSTRAINT uc_int_registered;
ALTER TABLE ONLY yahoo.tbl_int_ordinary_weight_step DROP CONSTRAINT uc_int_ordinary_weight_step;
ALTER TABLE ONLY yahoo.tbl_int_ordinary DROP CONSTRAINT uc_int_ordinary;
ALTER TABLE ONLY yahoo.tbl_int_eexp_weight_step DROP CONSTRAINT uc_int_eexp_weight_step;
ALTER TABLE ONLY yahoo.tbl_int_eexp DROP CONSTRAINT uc_int_eexp;
ALTER TABLE ONLY yahoo.tbl_int_airparcel_weight_step DROP CONSTRAINT uc_int_airparcel_weight_step;
ALTER TABLE ONLY yahoo.tbl_int_airparcel DROP CONSTRAINT uc_int_airparcel;
DROP VIEW yahoo.vw_lcl_smart_weight_step;
DROP VIEW yahoo.vw_lcl_smart;
DROP VIEW yahoo.vw_lcl_registered_weight_step;
DROP VIEW yahoo.vw_lcl_registered;
DROP VIEW yahoo.vw_lcl_parcel_weight_step;
DROP VIEW yahoo.vw_lcl_parcel;
DROP VIEW yahoo.vw_lcl_courier_weight_step;
DROP VIEW yahoo.vw_lcl_courier;
DROP VIEW yahoo.vw_int_surparcel_weight_step;
DROP VIEW yahoo.vw_int_surparcel;
DROP VIEW yahoo.vw_int_speedpost_weight_step;
DROP VIEW yahoo.vw_int_speedpost;
DROP VIEW yahoo.vw_int_registered_weight_step;
DROP VIEW yahoo.vw_int_registered;
DROP VIEW yahoo.vw_int_ordinary_weight_step;
DROP VIEW yahoo.vw_int_ordinary;
DROP VIEW yahoo.vw_int_eexp_weight_step;
DROP VIEW yahoo.vw_int_eexp;
DROP VIEW yahoo.vw_int_airparcel_weight_step;
DROP VIEW yahoo.vw_int_airparcel;
DROP VIEW yahoo.vw_export_shipping_type;
DROP VIEW yahoo.vw_export_shipping_master;
DROP VIEW yahoo.vw_postage_type;
DROP VIEW yahoo.vw_postage_destination;
DROP VIEW yahoo.vw_export_shipping_destination;
DROP VIEW yahoo.vw_shipping;
DROP VIEW yahoo.vw_local_shipping;
DROP VIEW yahoo.vw_international_shipping;
DROP TABLE yahoo.tbl_shipping;
DROP TABLE yahoo.tbl_lcl_smart_weight_step;
DROP TABLE yahoo.tbl_lcl_smart;
DROP TABLE yahoo.tbl_lcl_registered_weight_step;
DROP TABLE yahoo.tbl_lcl_registered;
DROP TABLE yahoo.tbl_lcl_parcel_weight_step;
DROP TABLE yahoo.tbl_lcl_parcel;
DROP TABLE yahoo.tbl_lcl_courier_weight_step;
DROP TABLE yahoo.tbl_lcl_courier;
DROP TABLE yahoo.tbl_int_surparcel_weight_step;
DROP TABLE yahoo.tbl_int_surparcel;
DROP TABLE yahoo.tbl_int_speedpost_weight_step;
DROP TABLE yahoo.tbl_int_speedpost;
DROP TABLE yahoo.tbl_int_registered_weight_step;
DROP TABLE yahoo.tbl_int_registered;
DROP TABLE yahoo.tbl_int_ordinary_weight_step;
DROP TABLE yahoo.tbl_int_ordinary;
DROP TABLE yahoo.tbl_int_eexp_weight_step;
DROP TABLE yahoo.tbl_int_eexp;
DROP TABLE yahoo.tbl_int_airparcel_weight_step;
DROP TABLE yahoo.tbl_int_airparcel;
DROP TABLE yahoo.stg_lcl_smart;
DROP TABLE yahoo.stg_lcl_registered;
DROP TABLE yahoo.stg_lcl_parcel;
DROP TABLE yahoo.stg_lcl_courier;
DROP TABLE yahoo.stg_int_surparcel;
DROP TABLE yahoo.stg_int_speedpost;
DROP TABLE yahoo.stg_int_registered;
DROP TABLE yahoo.stg_int_ordinary;
DROP TABLE yahoo.stg_int_airparcel;
DROP TABLE yahoo.hk_post_surface_parcel_costing;
DROP TABLE yahoo.hk_post_air_parcel_costing;
DROP TABLE yahoo.stg_int_eexp;
DROP FUNCTION yahoo.load_lcl_smart();
DROP FUNCTION yahoo.load_lcl_registered();
DROP FUNCTION yahoo.load_lcl_parcel();
DROP FUNCTION yahoo.load_lcl_courier();
DROP FUNCTION yahoo.load_int_surparcel();
DROP FUNCTION yahoo.load_int_speedpost();
DROP FUNCTION yahoo.load_int_registered();
DROP FUNCTION yahoo.load_int_ordinary();
DROP FUNCTION yahoo.load_int_eexp();
DROP FUNCTION yahoo.load_int_airparcel();
DROP SCHEMA yahoo;
--
-- Name: yahoo; Type: SCHEMA; Schema: -; Owner: mochidb_owner
--

CREATE SCHEMA yahoo;


ALTER SCHEMA yahoo OWNER TO mochidb_owner;

SET search_path = yahoo, pg_catalog;

--
-- Name: load_int_airparcel(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_int_airparcel() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_int_airparcel;

	INSERT INTO yahoo.tbl_int_airparcel(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_int_airparcel;

	TRUNCATE TABLE yahoo.tbl_int_airparcel_weight_step;

	INSERT INTO yahoo.tbl_int_airparcel_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_int_airparcel_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_int_airparcel() OWNER TO mochidb_owner;

--
-- Name: load_int_eexp(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_int_eexp() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_int_eexp;

	INSERT INTO yahoo.tbl_int_eexp(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_int_eexp;

	TRUNCATE TABLE yahoo.tbl_int_eexp_weight_step;

	INSERT INTO yahoo.tbl_int_eexp_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_int_eexp_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_int_eexp() OWNER TO mochidb_owner;

--
-- Name: load_int_ordinary(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_int_ordinary() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_int_ordinary;

	INSERT INTO yahoo.tbl_int_ordinary(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_int_ordinary;

	TRUNCATE TABLE yahoo.tbl_int_ordinary_weight_step;

	INSERT INTO yahoo.tbl_int_ordinary_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_int_ordinary_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_int_ordinary() OWNER TO mochidb_owner;

--
-- Name: load_int_registered(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_int_registered() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_int_registered;

	INSERT INTO yahoo.tbl_int_registered(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_int_registered;

	TRUNCATE TABLE yahoo.tbl_int_registered_weight_step;

	INSERT INTO yahoo.tbl_int_registered_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_int_registered_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_int_registered() OWNER TO mochidb_owner;

--
-- Name: load_int_speedpost(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_int_speedpost() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_int_speedpost;

	INSERT INTO yahoo.tbl_int_speedpost(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_int_speedpost;

	TRUNCATE TABLE yahoo.tbl_int_speedpost_weight_step;

	INSERT INTO yahoo.tbl_int_speedpost_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	DISTINCT
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_int_speedpost_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_int_speedpost() OWNER TO mochidb_owner;

--
-- Name: load_int_surparcel(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_int_surparcel() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_int_surparcel;

	INSERT INTO yahoo.tbl_int_surparcel(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_int_surparcel;

	TRUNCATE TABLE yahoo.tbl_int_surparcel_weight_step;

	INSERT INTO yahoo.tbl_int_surparcel_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	DISTINCT
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_int_surparcel_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_int_surparcel() OWNER TO mochidb_owner;

--
-- Name: load_lcl_courier(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_lcl_courier() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_lcl_courier;

	INSERT INTO yahoo.tbl_lcl_courier(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_lcl_courier;

	TRUNCATE TABLE yahoo.tbl_lcl_courier_weight_step;

	INSERT INTO yahoo.tbl_lcl_courier_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	DISTINCT
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_lcl_courier_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_lcl_courier() OWNER TO mochidb_owner;

--
-- Name: load_lcl_parcel(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_lcl_parcel() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_lcl_parcel;

	INSERT INTO yahoo.tbl_lcl_parcel(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_lcl_parcel;

	TRUNCATE TABLE yahoo.tbl_lcl_parcel_weight_step;

	INSERT INTO yahoo.tbl_lcl_parcel_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	DISTINCT
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_lcl_parcel_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_lcl_parcel() OWNER TO mochidb_owner;

--
-- Name: load_lcl_registered(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_lcl_registered() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_lcl_registered;

	INSERT INTO yahoo.tbl_lcl_registered(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_lcl_registered;

	TRUNCATE TABLE yahoo.tbl_lcl_registered_weight_step;

	INSERT INTO yahoo.tbl_lcl_registered_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	DISTINCT
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_lcl_registered_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_lcl_registered() OWNER TO mochidb_owner;

--
-- Name: load_lcl_smart(); Type: FUNCTION; Schema: yahoo; Owner: mochidb_owner
--

CREATE FUNCTION load_lcl_smart() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	TRUNCATE TABLE yahoo.tbl_lcl_smart;

	INSERT INTO yahoo.tbl_lcl_smart(
	servicecode, servicenameen, servicenametc, servicenamesc, trackinglevel, destinationnameen, destinationnamesc, destinationnametc, destinationcode, wgtlimit, zonecode)
	SELECT 	servicecode, 
			servicenameen, 
			servicenametc, 
			servicenamesc, 
			trackinglevel, 
			destinationnameen, 
			destinationnamesc, 
			destinationnametc, 
			destinationcode, 
			wgtlimit::numeric as wgtlimit,
			zonecode
	FROM yahoo.vw_lcl_smart;

	TRUNCATE TABLE yahoo.tbl_lcl_smart_weight_step;

	INSERT INTO yahoo.tbl_lcl_smart_weight_step(
		destinationcode, servicenameen, weightfrom, weightto, amount, zonecode, additionalweight, additionalamount)
	SELECT 	DISTINCT
			destinationcode,
			servicenameen, 
			weightfrom::numeric as weightfrom, 
			weightto::numeric as weightto, 
			amount::numeric as amount,
			zonecode,
			additionalweight::numeric as additionalweight,
			additionalamount::numeric as additionalamount
	FROM yahoo.vw_lcl_smart_weight_step;
		
	RETURN 1;
end;
';


ALTER FUNCTION yahoo.load_lcl_smart() OWNER TO mochidb_owner;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: stg_int_eexp; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_int_eexp (
    payload jsonb
);


ALTER TABLE stg_int_eexp OWNER TO mochidb_owner;

--
-- Name: hk_post_air_parcel_costing; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE hk_post_air_parcel_costing (
    destination character varying(50),
    zone_no character(1),
    weight_limits numeric,
    size_limits character varying(2),
    multi_part character(1),
    add_cust_frm character varying(10),
    air_rate_to_1 numeric,
    air_rate_add_1_over_1_to_5 numeric,
    air_rate_add_500_over_5 numeric,
    insurance_air character varying(2),
    insurance_surface character varying(2)
);


ALTER TABLE hk_post_air_parcel_costing OWNER TO mochidb_owner;

--
-- Name: hk_post_surface_parcel_costing; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE hk_post_surface_parcel_costing (
    destination character varying(50),
    zone_no character(1),
    weight_limits numeric,
    size_limits character varying(2),
    multi_part character(1),
    add_cust_frm character varying(10),
    surface_rate_to_1 numeric,
    surface_rate_add_1_over_1_to_7 numeric,
    surface_rate_add_1_over_7 numeric,
    insurance_air character varying(2),
    insurance_surface character varying(2)
);


ALTER TABLE hk_post_surface_parcel_costing OWNER TO mochidb_owner;

--
-- Name: stg_int_airparcel; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_int_airparcel (
    payload jsonb
);


ALTER TABLE stg_int_airparcel OWNER TO mochidb_owner;

--
-- Name: stg_int_ordinary; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_int_ordinary (
    payload jsonb
);


ALTER TABLE stg_int_ordinary OWNER TO mochidb_owner;

--
-- Name: stg_int_registered; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_int_registered (
    payload jsonb
);


ALTER TABLE stg_int_registered OWNER TO mochidb_owner;

--
-- Name: stg_int_speedpost; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_int_speedpost (
    payload jsonb
);


ALTER TABLE stg_int_speedpost OWNER TO mochidb_owner;

--
-- Name: stg_int_surparcel; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_int_surparcel (
    payload jsonb
);


ALTER TABLE stg_int_surparcel OWNER TO mochidb_owner;

--
-- Name: stg_lcl_courier; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_lcl_courier (
    payload jsonb
);


ALTER TABLE stg_lcl_courier OWNER TO mochidb_owner;

--
-- Name: stg_lcl_parcel; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_lcl_parcel (
    payload jsonb
);


ALTER TABLE stg_lcl_parcel OWNER TO mochidb_owner;

--
-- Name: stg_lcl_registered; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_lcl_registered (
    payload jsonb
);


ALTER TABLE stg_lcl_registered OWNER TO mochidb_owner;

--
-- Name: stg_lcl_smart; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE stg_lcl_smart (
    payload jsonb
);


ALTER TABLE stg_lcl_smart OWNER TO mochidb_owner;

--
-- Name: tbl_int_airparcel; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_airparcel (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_int_airparcel OWNER TO mochidb_owner;

--
-- Name: tbl_int_airparcel_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_airparcel_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_int_airparcel_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_int_eexp; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_eexp (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_int_eexp OWNER TO mochidb_owner;

--
-- Name: tbl_int_eexp_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_eexp_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_int_eexp_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_int_ordinary; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_ordinary (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_int_ordinary OWNER TO mochidb_owner;

--
-- Name: tbl_int_ordinary_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_ordinary_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_int_ordinary_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_int_registered; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_registered (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_int_registered OWNER TO mochidb_owner;

--
-- Name: tbl_int_registered_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_registered_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_int_registered_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_int_speedpost; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_speedpost (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_int_speedpost OWNER TO mochidb_owner;

--
-- Name: tbl_int_speedpost_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_speedpost_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_int_speedpost_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_int_surparcel; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_surparcel (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_int_surparcel OWNER TO mochidb_owner;

--
-- Name: tbl_int_surparcel_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_int_surparcel_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_int_surparcel_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_lcl_courier; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_lcl_courier (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_lcl_courier OWNER TO mochidb_owner;

--
-- Name: tbl_lcl_courier_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_lcl_courier_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_lcl_courier_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_lcl_parcel; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_lcl_parcel (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_lcl_parcel OWNER TO mochidb_owner;

--
-- Name: tbl_lcl_parcel_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_lcl_parcel_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_lcl_parcel_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_lcl_registered; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_lcl_registered (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_lcl_registered OWNER TO mochidb_owner;

--
-- Name: tbl_lcl_registered_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_lcl_registered_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_lcl_registered_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_lcl_smart; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_lcl_smart (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    destinationcode text,
    wgtlimit numeric,
    zonecode text
);


ALTER TABLE tbl_lcl_smart OWNER TO mochidb_owner;

--
-- Name: tbl_lcl_smart_weight_step; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_lcl_smart_weight_step (
    zonecode text,
    servicenameen text,
    weightfrom numeric,
    weightto numeric,
    amount numeric,
    destinationcode text,
    additionalweight numeric,
    additionalamount numeric
);


ALTER TABLE tbl_lcl_smart_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_shipping; Type: TABLE; Schema: yahoo; Owner: mochidb_owner
--

CREATE TABLE tbl_shipping (
    servicecode text,
    servicenameen text,
    servicenametc text,
    servicenamesc text,
    trackinglevel text,
    destinationnameen text,
    destinationnamesc text,
    destinationnametc text,
    zonecode text,
    wgtlimit numeric,
    weightfrom numeric,
    weightto numeric,
    amount numeric
);


ALTER TABLE tbl_shipping OWNER TO mochidb_owner;

--
-- Name: vw_international_shipping; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_international_shipping AS
 SELECT a.servicecode,
    a.servicenameen,
    a.servicenametc,
    a.servicenamesc,
    a.trackinglevel,
    a.destinationnameen,
    a.destinationnamesc,
    a.destinationnametc,
    a.destinationcode,
    a.zonecode,
    a.wgtlimit,
    (
        CASE
            WHEN (a.additionalweight IS NOT NULL) THEN COALESCE((a.weightto - a.additionalweight), (0)::numeric)
            ELSE a.weightfrom
        END + 0.001) AS weightfrom,
    a.weightto,
    (a.baseamount + sum(a.additionalamount) OVER (PARTITION BY a.servicecode, a.servicenameen, a.zonecode, a.destinationcode, a.baseamount ORDER BY a.weightto)) AS amount,
    a.additionalweight,
    a.additionalamount
   FROM ( SELECT t.servicecode,
            t.servicenameen,
            t.servicenametc,
            t.servicenamesc,
            t.trackinglevel,
            t.destinationnameen,
            t.destinationnamesc,
            t.destinationnametc,
            t.destinationcode,
            t.zonecode,
            t.wgtlimit,
            ws.weightfrom,
            generate_series(ws.weightfrom, ws.weightto, COALESCE(ws.additionalweight, ws.weightto)) AS weightto,
            ws.additionalweight,
            ws.amount AS baseamount,
            COALESCE(ws.additionalamount, (0)::numeric) AS additionalamount
           FROM (tbl_int_airparcel t
             LEFT JOIN tbl_int_airparcel_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))))
          WHERE (0 = 0)
          ORDER BY t.servicecode, ws.weightfrom) a
  WHERE (a.weightfrom <> a.weightto)
UNION ALL
 SELECT a.servicecode,
    a.servicenameen,
    a.servicenametc,
    a.servicenamesc,
    a.trackinglevel,
    a.destinationnameen,
    a.destinationnamesc,
    a.destinationnametc,
    a.destinationcode,
    a.zonecode,
    a.wgtlimit,
    (
        CASE
            WHEN (a.additionalweight IS NOT NULL) THEN COALESCE((a.weightto - a.additionalweight), (0)::numeric)
            ELSE a.weightfrom
        END + 0.001) AS weightfrom,
    a.weightto,
    (a.baseamount + sum(a.additionalamount) OVER (PARTITION BY a.servicecode, a.servicenameen, a.zonecode, a.destinationcode, a.baseamount ORDER BY a.weightto)) AS amount,
    a.additionalweight,
    a.additionalamount
   FROM ( SELECT t.servicecode,
            t.servicenameen,
            t.servicenametc,
            t.servicenamesc,
            t.trackinglevel,
            t.destinationnameen,
            t.destinationnamesc,
            t.destinationnametc,
            t.destinationcode,
            t.zonecode,
            t.wgtlimit,
            ws.weightfrom,
            generate_series(ws.weightfrom, ws.weightto, COALESCE(ws.additionalweight, ws.weightto)) AS weightto,
            ws.additionalweight,
            ws.amount AS baseamount,
            COALESCE(ws.additionalamount, (0)::numeric) AS additionalamount
           FROM (tbl_int_surparcel t
             LEFT JOIN tbl_int_surparcel_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))))
          WHERE (0 = 0)
          ORDER BY t.servicecode, ws.weightfrom) a
  WHERE (a.weightfrom <> a.weightto)
UNION ALL
 SELECT a.servicecode,
    a.servicenameen,
    a.servicenametc,
    a.servicenamesc,
    a.trackinglevel,
    a.destinationnameen,
    a.destinationnamesc,
    a.destinationnametc,
    a.destinationcode,
    a.zonecode,
    a.wgtlimit,
    (
        CASE
            WHEN (a.additionalweight IS NOT NULL) THEN COALESCE((a.weightto - a.additionalweight), (0)::numeric)
            ELSE a.weightfrom
        END + 0.001) AS weightfrom,
    a.weightto,
    (a.baseamount + sum(a.additionalamount) OVER (PARTITION BY a.servicecode, a.servicenameen, a.zonecode, a.destinationcode, a.baseamount ORDER BY a.weightto)) AS amount,
    a.additionalweight,
    a.additionalamount
   FROM ( SELECT t.servicecode,
            t.servicenameen,
            t.servicenametc,
            t.servicenamesc,
            t.trackinglevel,
            t.destinationnameen,
            t.destinationnamesc,
            t.destinationnametc,
            t.destinationcode,
            t.zonecode,
            t.wgtlimit,
            ws.weightfrom,
            generate_series(ws.weightfrom, ws.weightto, COALESCE(ws.additionalweight, ws.weightto)) AS weightto,
            ws.additionalweight,
            ws.amount AS baseamount,
            COALESCE(ws.additionalamount, (0)::numeric) AS additionalamount
           FROM (tbl_int_eexp t
             LEFT JOIN tbl_int_eexp_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))))
          WHERE (0 = 0)
          ORDER BY t.servicecode, ws.weightfrom) a
  WHERE (a.weightfrom <> a.weightto)
UNION ALL
 SELECT a.servicecode,
    a.servicenameen,
    a.servicenametc,
    a.servicenamesc,
    a.trackinglevel,
    a.destinationnameen,
    a.destinationnamesc,
    a.destinationnametc,
    a.destinationcode,
    a.zonecode,
    a.wgtlimit,
    (
        CASE
            WHEN (a.additionalweight IS NOT NULL) THEN COALESCE((a.weightto - a.additionalweight), (0)::numeric)
            ELSE a.weightfrom
        END + 0.001) AS weightfrom,
    a.weightto,
    (a.baseamount + sum(a.additionalamount) OVER (PARTITION BY a.servicecode, a.servicenameen, a.zonecode, a.destinationcode, a.baseamount ORDER BY a.weightto)) AS amount,
    a.additionalweight,
    a.additionalamount
   FROM ( SELECT t.servicecode,
            t.servicenameen,
            t.servicenametc,
            t.servicenamesc,
            t.trackinglevel,
            t.destinationnameen,
            t.destinationnamesc,
            t.destinationnametc,
            t.destinationcode,
            t.zonecode,
            t.wgtlimit,
            ws.weightfrom,
                CASE
                    WHEN (ws.additionalweight IS NOT NULL) THEN generate_series(ws.weightfrom, ws.weightto, COALESCE(ws.additionalweight, ws.weightto))
                    ELSE ws.weightto
                END AS weightto,
            ws.additionalweight,
            ws.amount AS baseamount,
            COALESCE(ws.additionalamount, (0)::numeric) AS additionalamount
           FROM (tbl_int_ordinary t
             LEFT JOIN tbl_int_ordinary_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))))
          WHERE (0 = 0)
          ORDER BY t.servicecode, ws.weightfrom) a
  WHERE (a.weightfrom <> a.weightto)
UNION ALL
 SELECT a.servicecode,
    a.servicenameen,
    a.servicenametc,
    a.servicenamesc,
    a.trackinglevel,
    a.destinationnameen,
    a.destinationnamesc,
    a.destinationnametc,
    a.destinationcode,
    a.zonecode,
    a.wgtlimit,
    (
        CASE
            WHEN (a.additionalweight IS NOT NULL) THEN COALESCE((a.weightto - a.additionalweight), (0)::numeric)
            ELSE a.weightfrom
        END + 0.001) AS weightfrom,
    a.weightto,
    (a.baseamount + sum(a.additionalamount) OVER (PARTITION BY a.servicecode, a.servicenameen, a.zonecode, a.destinationcode, a.baseamount ORDER BY a.weightto)) AS amount,
    a.additionalweight,
    a.additionalamount
   FROM ( SELECT t.servicecode,
            t.servicenameen,
            t.servicenametc,
            t.servicenamesc,
            t.trackinglevel,
            t.destinationnameen,
            t.destinationnamesc,
            t.destinationnametc,
            t.destinationcode,
            t.zonecode,
            t.wgtlimit,
            ws.weightfrom,
                CASE
                    WHEN (ws.additionalweight IS NOT NULL) THEN generate_series(ws.weightfrom, ws.weightto, COALESCE(ws.additionalweight, ws.weightto))
                    ELSE ws.weightto
                END AS weightto,
            ws.additionalweight,
            ws.amount AS baseamount,
            COALESCE(ws.additionalamount, (0)::numeric) AS additionalamount
           FROM (tbl_int_registered t
             LEFT JOIN tbl_int_registered_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))))
          WHERE (0 = 0)
          ORDER BY t.servicecode, ws.weightfrom) a
  WHERE (a.weightfrom <> a.weightto)
UNION ALL
 SELECT t.servicecode,
    t.servicenameen,
    t.servicenametc,
    t.servicenamesc,
    t.trackinglevel,
    t.destinationnameen,
    t.destinationnamesc,
    t.destinationnametc,
    t.destinationcode,
    t.zonecode,
    t.wgtlimit,
    (ws.weightfrom + 0.001) AS weightfrom,
    ws.weightto,
    ws.amount,
    ws.additionalweight,
    ws.additionalamount
   FROM (tbl_int_speedpost t
     LEFT JOIN tbl_int_speedpost_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))));


ALTER TABLE vw_international_shipping OWNER TO mochidb_owner;

--
-- Name: vw_local_shipping; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_local_shipping AS
 SELECT t.servicecode,
    t.servicenameen,
    t.servicenametc,
    t.servicenamesc,
    t.trackinglevel,
    t.destinationnameen,
    t.destinationnamesc,
    t.destinationnametc,
    t.destinationcode,
    t.zonecode,
    t.wgtlimit,
    ws.weightfrom,
    ws.weightto,
    ws.amount,
    ws.additionalweight,
    ws.additionalamount
   FROM (tbl_lcl_smart t
     LEFT JOIN tbl_lcl_smart_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))))
UNION ALL
 SELECT t.servicecode,
    t.servicenameen,
    t.servicenametc,
    t.servicenamesc,
    t.trackinglevel,
    t.destinationnameen,
    t.destinationnamesc,
    t.destinationnametc,
    t.destinationcode,
    t.zonecode,
    t.wgtlimit,
    ws.weightfrom,
    ws.weightto,
    ws.amount,
    ws.additionalweight,
    ws.additionalamount
   FROM (tbl_lcl_registered t
     LEFT JOIN tbl_lcl_registered_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))))
UNION ALL
 SELECT t.servicecode,
    t.servicenameen,
    t.servicenametc,
    t.servicenamesc,
    t.trackinglevel,
    t.destinationnameen,
    t.destinationnamesc,
    t.destinationnametc,
    t.destinationcode,
    t.zonecode,
    t.wgtlimit,
    ws.weightfrom,
    ws.weightto,
    ws.amount,
    ws.additionalweight,
    ws.additionalamount
   FROM (tbl_lcl_parcel t
     LEFT JOIN tbl_lcl_parcel_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))))
UNION ALL
 SELECT t.servicecode,
    t.servicenameen,
    t.servicenametc,
    t.servicenamesc,
    t.trackinglevel,
    t.destinationnameen,
    t.destinationnamesc,
    t.destinationnametc,
    t.destinationcode,
    t.zonecode,
    t.wgtlimit,
    ws.weightfrom,
    ws.weightto,
    ws.amount,
    ws.additionalweight,
    ws.additionalamount
   FROM (tbl_lcl_courier t
     LEFT JOIN tbl_lcl_courier_weight_step ws ON (((t.destinationcode = ws.destinationcode) AND (t.zonecode = ws.zonecode) AND (t.servicenameen = ws.servicenameen))));


ALTER TABLE vw_local_shipping OWNER TO mochidb_owner;

--
-- Name: vw_shipping; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_shipping AS
 SELECT ((a.servicecode || '_'::text) || dense_rank() OVER (PARTITION BY a.servicecode ORDER BY a.servicenamesc)) AS servicecode,
    btrim(a.servicenameen) AS servicenameen,
    btrim(a.servicenametc) AS servicenametc,
    btrim(a.servicenamesc) AS servicenamesc,
    btrim(a.trackinglevel) AS trackinglevel,
    btrim(a.destinationnameen) AS destinationnameen,
    btrim(a.destinationnamesc) AS destinationnamesc,
    btrim(a.destinationnametc) AS destinationnametc,
    btrim(a.destinationcode) AS destinationcode,
    btrim(a.zonecode) AS zonecode,
    a.wgtlimit,
    a.weightfrom,
    a.weightto,
    a.amount,
    a.additionalweight,
    a.additionalamount
   FROM ( SELECT vw_international_shipping.servicecode,
            vw_international_shipping.servicenameen,
            vw_international_shipping.servicenametc,
            vw_international_shipping.servicenamesc,
            vw_international_shipping.trackinglevel,
            vw_international_shipping.destinationnameen,
            vw_international_shipping.destinationnamesc,
            vw_international_shipping.destinationnametc,
            vw_international_shipping.destinationcode,
            vw_international_shipping.zonecode,
            vw_international_shipping.wgtlimit,
            vw_international_shipping.weightfrom,
            vw_international_shipping.weightto,
            vw_international_shipping.amount,
            vw_international_shipping.additionalweight,
            vw_international_shipping.additionalamount
           FROM vw_international_shipping
        UNION ALL
         SELECT vw_local_shipping.servicecode,
            vw_local_shipping.servicenameen,
            vw_local_shipping.servicenametc,
            vw_local_shipping.servicenamesc,
            vw_local_shipping.trackinglevel,
            upper(vw_local_shipping.destinationnameen) AS destinationnameen,
            vw_local_shipping.destinationnamesc,
            vw_local_shipping.destinationnametc,
            vw_local_shipping.destinationcode,
            vw_local_shipping.zonecode,
            vw_local_shipping.wgtlimit,
            vw_local_shipping.weightfrom,
            vw_local_shipping.weightto,
            vw_local_shipping.amount,
            vw_local_shipping.additionalweight,
            vw_local_shipping.additionalamount
           FROM vw_local_shipping) a;


ALTER TABLE vw_shipping OWNER TO mochidb_owner;

--
-- Name: vw_export_shipping_destination; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_export_shipping_destination AS
 SELECT (((b.destinationcode || upper(b.zonecode)) || "right"(('00'::text || dense_rank() OVER (PARTITION BY b.destinationcode, b.zonecode ORDER BY b.destinationnameen)), 1)))::character(5) AS _destination_code,
    b.destinationcode AS _destination_short_code,
    b.destinationnameen AS _destination_desc_en,
    b.destinationnametc AS _destination_desc_hk,
    b.destinationnamesc AS _destination_desc_cn,
    b.zonecode AS _destination_zone_code
   FROM ( SELECT DISTINCT a.destinationcode,
            a.destinationnameen,
            a.destinationnametc,
            a.destinationnamesc,
            a.zonecode
           FROM ( SELECT vw_shipping.destinationcode,
                    vw_shipping.destinationnameen,
                    vw_shipping.destinationnametc,
                    vw_shipping.destinationnamesc,
                    vw_shipping.zonecode
                   FROM vw_shipping) a) b;


ALTER TABLE vw_export_shipping_destination OWNER TO mochidb_owner;

--
-- Name: vw_postage_destination; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_postage_destination AS
 SELECT dense_rank() OVER (PARTITION BY b.destinationcode, b.zonecode ORDER BY b.destinationnameen) AS pst_dst_id,
    (((b.destinationcode || upper(b.zonecode)) || "right"(('00'::text || dense_rank() OVER (PARTITION BY b.destinationcode, b.zonecode ORDER BY b.destinationnameen)), 1)))::character(5) AS pst_dst,
    b.destinationcode AS pst_dst_cd,
    b.destinationnameen AS pst_dst_desc,
    b.zonecode AS pst_zne_cd
   FROM ( SELECT DISTINCT a.destinationcode,
            a.destinationnameen,
            a.zonecode
           FROM ( SELECT vw_shipping.destinationcode,
                    vw_shipping.destinationnameen,
                    vw_shipping.zonecode
                   FROM vw_shipping) a) b;


ALTER TABLE vw_postage_destination OWNER TO mochidb_owner;

--
-- Name: vw_postage_type; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_postage_type AS
 SELECT row_number() OVER (PARTITION BY NULL::text) AS pst_typ_id,
    b.postage_type AS pst_typ,
    b.postage_type_desc AS pst_typ_desc
   FROM ( SELECT DISTINCT a.postage_type,
            a.postage_type_desc
           FROM ( SELECT vw_shipping.servicecode AS postage_type,
                    vw_shipping.servicenameen AS postage_type_desc
                   FROM vw_shipping) a) b;


ALTER TABLE vw_postage_type OWNER TO mochidb_owner;

--
-- Name: vw_export_shipping_master; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_export_shipping_master AS
 SELECT ((((((((upper(pt.pst_typ) || '_'::text) || s.zonecode) || '_'::text) || s.destinationcode) || '_'::text) || s.weightfrom) || '_'::text) || s.weightto) AS _product_upc_code,
    (((btrim(s.servicenameen) || ''::text) || ' to '::text) || s.destinationnameen) AS _product_description_en,
    (((btrim(s.servicenametc) || ''::text) || ' 至 '::text) || s.destinationnametc) AS _product_description_hk,
    ((((((((btrim(s.servicenameen) || ''::text) || ' to '::text) || s.destinationnameen) || ', weight from '::text) || s.weightfrom) || 'kg to '::text) || s.weightto) || 'kg'::text) AS _product_long_description_en,
    ((((((((btrim(s.servicenametc) || ''::text) || ' 至 '::text) || s.destinationnametc) || ', '::text) || s.weightfrom) || 'kg 至 '::text) || s.weightto) || 'kg 重'::text) AS _product_long_description_hk,
    pt.pst_typ AS _service_type_code,
    s.servicenameen AS _service_type_name_en,
    s.servicenametc AS _service_type_name_hk,
    s.servicenamesc AS _service_type_name_cn,
    s.zonecode AS _zone_code,
    'HKP01' AS _brand_code,
    'Hong Kong Post' AS _brand_description_en,
    '香港郵政' AS _brand_description_hk,
    'SHP01' AS _product_template_code,
    pd.pst_dst AS _destination_code,
    s.destinationnameen AS _destination_name_en,
    s.destinationnametc AS _destination_name_hk,
    s.destinationnamesc AS _destination_name_cn,
    s.wgtlimit AS _weight_limit,
    s.weightfrom AS _weight_from,
    s.weightto AS _weight_to,
    s.trackinglevel AS _tracking_level,
    s.amount AS _product_retail_price_hkd,
    (s.amount / 7.8) AS _product_retail_price_usd,
    to_char(now(), 'YYYY-MM-DD HH:MM:SS'::text) AS _product_created_date
   FROM ((vw_shipping s
     LEFT JOIN vw_postage_destination pd ON (((s.destinationnameen = pd.pst_dst_desc) AND (s.zonecode = pd.pst_zne_cd) AND (s.destinationcode = pd.pst_dst_cd))))
     LEFT JOIN vw_postage_type pt ON ((s.servicenameen = pt.pst_typ_desc)));


ALTER TABLE vw_export_shipping_master OWNER TO mochidb_owner;

--
-- Name: vw_export_shipping_type; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_export_shipping_type AS
 SELECT b.servicecode AS _type_code,
    b.servicenameen AS _type_desc_en,
    b.servicenametc AS _type_desc_hk,
    b.servicenamesc AS _type_desc_cn
   FROM ( SELECT DISTINCT a.servicecode,
            a.servicenameen,
            a.servicenametc,
            a.servicenamesc
           FROM ( SELECT vw_shipping.servicecode,
                    vw_shipping.servicenameen,
                    vw_shipping.servicenametc,
                    vw_shipping.servicenamesc
                   FROM vw_shipping) a) b;


ALTER TABLE vw_export_shipping_type OWNER TO mochidb_owner;

--
-- Name: vw_int_airparcel; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_airparcel AS
 SELECT (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_int_airparcel eexp,
    LATERAL jsonb_array_elements((eexp.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_int_airparcel OWNER TO mochidb_owner;

--
-- Name: vw_int_airparcel_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_airparcel_weight_step AS
 SELECT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_int_airparcel airp,
    LATERAL jsonb_array_elements((airp.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_int_airparcel_weight_step OWNER TO mochidb_owner;

--
-- Name: vw_int_eexp; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_eexp AS
 SELECT (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_int_eexp eexp,
    LATERAL jsonb_array_elements((eexp.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_int_eexp OWNER TO mochidb_owner;

--
-- Name: vw_int_eexp_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_eexp_weight_step AS
 SELECT DISTINCT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_int_eexp eexp,
    LATERAL jsonb_array_elements((eexp.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_int_eexp_weight_step OWNER TO mochidb_owner;

--
-- Name: vw_int_ordinary; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_ordinary AS
 SELECT (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_int_ordinary ordinary,
    LATERAL jsonb_array_elements((ordinary.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_int_ordinary OWNER TO mochidb_owner;

--
-- Name: vw_int_ordinary_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_ordinary_weight_step AS
 SELECT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_int_ordinary ordinary,
    LATERAL jsonb_array_elements((ordinary.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_int_ordinary_weight_step OWNER TO mochidb_owner;

--
-- Name: vw_int_registered; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_registered AS
 SELECT (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_int_registered registered,
    LATERAL jsonb_array_elements((registered.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_int_registered OWNER TO mochidb_owner;

--
-- Name: vw_int_registered_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_registered_weight_step AS
 SELECT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_int_registered registered,
    LATERAL jsonb_array_elements((registered.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_int_registered_weight_step OWNER TO mochidb_owner;

--
-- Name: vw_int_speedpost; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_speedpost AS
 SELECT (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_int_speedpost speedpost,
    LATERAL jsonb_array_elements((speedpost.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_int_speedpost OWNER TO mochidb_owner;

--
-- Name: vw_int_speedpost_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_speedpost_weight_step AS
 SELECT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_int_speedpost speedpost,
    LATERAL jsonb_array_elements((speedpost.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_int_speedpost_weight_step OWNER TO mochidb_owner;

--
-- Name: vw_int_surparcel; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_surparcel AS
 SELECT (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_int_surparcel surparcel,
    LATERAL jsonb_array_elements((surparcel.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_int_surparcel OWNER TO mochidb_owner;

--
-- Name: vw_int_surparcel_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_int_surparcel_weight_step AS
 SELECT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_int_surparcel surparcel,
    LATERAL jsonb_array_elements((surparcel.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_int_surparcel_weight_step OWNER TO mochidb_owner;

--
-- Name: vw_lcl_courier; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_lcl_courier AS
 SELECT (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_lcl_courier courier,
    LATERAL jsonb_array_elements((courier.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_lcl_courier OWNER TO mochidb_owner;

--
-- Name: vw_lcl_courier_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_lcl_courier_weight_step AS
 SELECT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_lcl_courier courier,
    LATERAL jsonb_array_elements((courier.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_lcl_courier_weight_step OWNER TO mochidb_owner;

--
-- Name: vw_lcl_parcel; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_lcl_parcel AS
 SELECT (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_lcl_parcel parcel,
    LATERAL jsonb_array_elements((parcel.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_lcl_parcel OWNER TO mochidb_owner;

--
-- Name: vw_lcl_parcel_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_lcl_parcel_weight_step AS
 SELECT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_lcl_parcel parcel,
    LATERAL jsonb_array_elements((parcel.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_lcl_parcel_weight_step OWNER TO mochidb_owner;

--
-- Name: vw_lcl_registered; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_lcl_registered AS
 SELECT (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_lcl_registered registered,
    LATERAL jsonb_array_elements((registered.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_lcl_registered OWNER TO mochidb_owner;

--
-- Name: vw_lcl_registered_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_lcl_registered_weight_step AS
 SELECT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_lcl_registered registered,
    LATERAL jsonb_array_elements((registered.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_lcl_registered_weight_step OWNER TO mochidb_owner;

--
-- Name: vw_lcl_smart; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_lcl_smart AS
 SELECT (dataset.value ->> 'serviceCode'::text) AS servicecode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (dataset.value ->> 'serviceNameTC'::text) AS servicenametc,
    (dataset.value ->> 'serviceNameSC'::text) AS servicenamesc,
    (dataset.value ->> 'trackingLevel'::text) AS trackinglevel,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (postage.value ->> 'destinationNameEN'::text) AS destinationnameen,
    (postage.value ->> 'destinationNameSC'::text) AS destinationnamesc,
    (postage.value ->> 'destinationNameTC'::text) AS destinationnametc,
    (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'wgtLimit'::text) AS wgtlimit
   FROM stg_lcl_smart smart,
    LATERAL jsonb_array_elements((smart.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value);


ALTER TABLE vw_lcl_smart OWNER TO mochidb_owner;

--
-- Name: vw_lcl_smart_weight_step; Type: VIEW; Schema: yahoo; Owner: mochidb_owner
--

CREATE VIEW vw_lcl_smart_weight_step AS
 SELECT (postage.value ->> 'zoneCode'::text) AS zonecode,
    (postage.value ->> 'destinationCode'::text) AS destinationcode,
    (dataset.value ->> 'serviceNameEN'::text) AS servicenameen,
    (weightstep.value ->> 'weightFrom'::text) AS weightfrom,
    (weightstep.value ->> 'weightTo'::text) AS weightto,
    (weightstep.value ->> 'amount'::text) AS amount,
    (weightstep.value ->> 'additionalWeight'::text) AS additionalweight,
    (weightstep.value ->> 'additionalAmount'::text) AS additionalamount
   FROM stg_lcl_smart smart,
    LATERAL jsonb_array_elements((smart.payload -> 'data'::text)) dataset(value),
    LATERAL jsonb_array_elements((dataset.value -> 'postage'::text)) postage(value),
    LATERAL jsonb_array_elements((postage.value -> 'weightStep'::text)) weightstep(value);


ALTER TABLE vw_lcl_smart_weight_step OWNER TO mochidb_owner;

--
-- Name: tbl_int_airparcel uc_int_airparcel; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_airparcel
    ADD CONSTRAINT uc_int_airparcel UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_int_airparcel_weight_step uc_int_airparcel_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_airparcel_weight_step
    ADD CONSTRAINT uc_int_airparcel_weight_step UNIQUE (zonecode, servicenameen, weightfrom, weightto, amount);


--
-- Name: tbl_int_eexp uc_int_eexp; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_eexp
    ADD CONSTRAINT uc_int_eexp UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_int_eexp_weight_step uc_int_eexp_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_eexp_weight_step
    ADD CONSTRAINT uc_int_eexp_weight_step UNIQUE (zonecode, destinationcode, servicenameen, weightfrom, weightto, amount);


--
-- Name: tbl_int_ordinary uc_int_ordinary; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_ordinary
    ADD CONSTRAINT uc_int_ordinary UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_int_ordinary_weight_step uc_int_ordinary_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_ordinary_weight_step
    ADD CONSTRAINT uc_int_ordinary_weight_step UNIQUE (zonecode, servicenameen, weightfrom, weightto, amount);


--
-- Name: tbl_int_registered uc_int_registered; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_registered
    ADD CONSTRAINT uc_int_registered UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_int_registered_weight_step uc_int_registered_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_registered_weight_step
    ADD CONSTRAINT uc_int_registered_weight_step UNIQUE (zonecode, servicenameen, weightfrom, weightto, amount);


--
-- Name: tbl_int_speedpost uc_int_speedpost; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_speedpost
    ADD CONSTRAINT uc_int_speedpost UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_int_speedpost_weight_step uc_int_speedpost_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_speedpost_weight_step
    ADD CONSTRAINT uc_int_speedpost_weight_step UNIQUE (zonecode, destinationcode, servicenameen, weightfrom, weightto, amount);


--
-- Name: tbl_int_surparcel uc_int_surparcel; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_surparcel
    ADD CONSTRAINT uc_int_surparcel UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_int_surparcel_weight_step uc_int_surparcel_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_int_surparcel_weight_step
    ADD CONSTRAINT uc_int_surparcel_weight_step UNIQUE (zonecode, servicenameen, weightfrom, weightto, amount);


--
-- Name: tbl_lcl_courier uc_lcl_courier; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_lcl_courier
    ADD CONSTRAINT uc_lcl_courier UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_lcl_courier_weight_step uc_lcl_courier_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_lcl_courier_weight_step
    ADD CONSTRAINT uc_lcl_courier_weight_step UNIQUE (zonecode, servicenameen, weightfrom, weightto, amount);


--
-- Name: tbl_lcl_parcel uc_lcl_parcel; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_lcl_parcel
    ADD CONSTRAINT uc_lcl_parcel UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_lcl_parcel_weight_step uc_lcl_parcel_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_lcl_parcel_weight_step
    ADD CONSTRAINT uc_lcl_parcel_weight_step UNIQUE (zonecode, servicenameen, weightfrom, weightto, amount);


--
-- Name: tbl_lcl_registered uc_lcl_registered; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_lcl_registered
    ADD CONSTRAINT uc_lcl_registered UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_lcl_registered_weight_step uc_lcl_registered_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_lcl_registered_weight_step
    ADD CONSTRAINT uc_lcl_registered_weight_step UNIQUE (zonecode, servicenameen, weightfrom, weightto, amount);


--
-- Name: tbl_lcl_smart uc_lcl_smart; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_lcl_smart
    ADD CONSTRAINT uc_lcl_smart UNIQUE (servicecode, servicenameen, destinationcode, destinationnameen);


--
-- Name: tbl_lcl_smart_weight_step uc_lcl_smart_weight_step; Type: CONSTRAINT; Schema: yahoo; Owner: mochidb_owner
--

ALTER TABLE ONLY tbl_lcl_smart_weight_step
    ADD CONSTRAINT uc_lcl_smart_weight_step UNIQUE (zonecode, servicenameen, weightfrom, weightto, amount);


--
-- Name: yahoo; Type: ACL; Schema: -; Owner: mochidb_owner
--

GRANT USAGE ON SCHEMA yahoo TO mochi_app;


--
-- Name: vw_shipping; Type: ACL; Schema: yahoo; Owner: mochidb_owner
--

GRANT SELECT ON TABLE vw_shipping TO mochi_app;


--
-- PostgreSQL database dump complete
--

