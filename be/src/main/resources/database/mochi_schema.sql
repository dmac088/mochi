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

ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT tag_attr_lcl_tag_id_fkey;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT tag_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_role_typ_id_fkey;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_party_id_fkey;
ALTER TABLE ONLY mochi.promotion_category DROP CONSTRAINT promotion_category_prm_id_promotion_prm_id_fkey;
ALTER TABLE ONLY mochi.promotion_category DROP CONSTRAINT promotion_category_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT product_typ_id_product_type_typ_id_fkey;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT product_tag_tag_id_tag_tag_id_fkey;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT product_tag_prd_id_product_prd_id_fkey;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT product_sts_id_product_status_sts_id_fkey;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT product_category_prd_id_product_prd_id_fkey;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT product_category_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT product_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.person DROP CONSTRAINT person_person_id_fkey;
ALTER TABLE ONLY mochi.party DROP CONSTRAINT party_pty_typ_id_fkey;
ALTER TABLE ONLY mochi.organisation DROP CONSTRAINT organisation_org_id_fkey;
ALTER TABLE ONLY mochi."order" DROP CONSTRAINT orders_party_id_fkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_product_id_fkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_order_id_fkey;
ALTER TABLE ONLY mochi.layout_category DROP CONSTRAINT layout_category_lay_id_layout_lay_id_fkey;
ALTER TABLE ONLY mochi.layout_category DROP CONSTRAINT layout_category_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.customer DROP CONSTRAINT customer_role_id_fkey;
ALTER TABLE ONLY mochi.category_product DROP CONSTRAINT category_product_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.category DROP CONSTRAINT category_hir_id_hierarchy_hir_id_fkey;
ALTER TABLE ONLY mochi.category_brand DROP CONSTRAINT category_brand_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT category_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT category_attr_lcl_cat_id_fkey;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT brand_category_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT brand_category_bnd_id_brand_bnd_id_fkey;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT brand_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT brand_attr_lcl_bnd_id_fkey;
DROP INDEX mochi.role_role_typ_id_role_start_dttm_party_id_key;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT uc_tag_lcl;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT uc_tag_desc;
ALTER TABLE ONLY mochi.promotion_category DROP CONSTRAINT uc_promotion_category;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT uc_product_tag;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT uc_product_category;
ALTER TABLE ONLY mochi.promotion DROP CONSTRAINT uc_prm_cd;
ALTER TABLE ONLY mochi.product_type DROP CONSTRAINT uc_prd_typ_cd;
ALTER TABLE ONLY mochi.product_status DROP CONSTRAINT uc_prd_sts_cd;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT uc_prd_lcl_1;
ALTER TABLE ONLY mochi.layout_category DROP CONSTRAINT uc_layout_category;
ALTER TABLE ONLY mochi.layout DROP CONSTRAINT uc_lay_desc;
ALTER TABLE ONLY mochi.layout DROP CONSTRAINT uc_lay_cd;
ALTER TABLE ONLY mochi.hierarchy DROP CONSTRAINT uc_hir_cd;
ALTER TABLE ONLY mochi.category_type DROP CONSTRAINT uc_cat_typ_cd;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT uc_cat_lcl;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT uc_cat_desc;
ALTER TABLE ONLY mochi.category DROP CONSTRAINT uc_cat_cd;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT uc_brand_category;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT uc_bnd_lcl;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT uc_bnd_desc_lcl_cd;
ALTER TABLE ONLY mochi.tag DROP CONSTRAINT tag_pkey;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT tag_attr_lcl_pkey;
ALTER TABLE ONLY mochi.role_type DROP CONSTRAINT role_type_rle_typ_desc_key;
ALTER TABLE ONLY mochi.role_type DROP CONSTRAINT role_type_pkey;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_pty_id_key;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_pkey;
ALTER TABLE ONLY mochi.promotion_type DROP CONSTRAINT promotion_type_pkey;
ALTER TABLE ONLY mochi.promotion DROP CONSTRAINT promotion_pkey;
ALTER TABLE ONLY mochi.promotion_category DROP CONSTRAINT promotion_category_pkey;
ALTER TABLE ONLY mochi.product_type DROP CONSTRAINT product_type_pkey;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT product_tag_pkey;
ALTER TABLE ONLY mochi.product_status DROP CONSTRAINT product_status_pkey;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT product_pkey;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT product_category_pkey;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT product_attr_lcl_pkey;
ALTER TABLE ONLY mochi.price_type DROP CONSTRAINT price_type_pkey;
ALTER TABLE ONLY mochi.price DROP CONSTRAINT price_pkey;
ALTER TABLE ONLY mochi.locale DROP CONSTRAINT pk_locale;
ALTER TABLE ONLY mochi.person DROP CONSTRAINT person_psn_id_key;
ALTER TABLE ONLY mochi.party_type DROP CONSTRAINT party_type_pty_typ_desc_key;
ALTER TABLE ONLY mochi.party_type DROP CONSTRAINT party_type_pkey;
ALTER TABLE ONLY mochi.party DROP CONSTRAINT party_pkey;
ALTER TABLE ONLY mochi.organisation DROP CONSTRAINT organisation_org_id_key;
ALTER TABLE ONLY mochi."order" DROP CONSTRAINT orders_pty_id_key;
ALTER TABLE ONLY mochi."order" DROP CONSTRAINT orders_pkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_pkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_ord_id_key;
ALTER TABLE ONLY mochi.layout DROP CONSTRAINT layout_pkey;
ALTER TABLE ONLY mochi.layout_category DROP CONSTRAINT layout_category_pkey;
ALTER TABLE ONLY mochi.hierarchy DROP CONSTRAINT hierarchy_pkey;
ALTER TABLE ONLY mochi.discount_type DROP CONSTRAINT discount_type_pkey;
ALTER TABLE ONLY mochi.discount DROP CONSTRAINT discount_pkey;
ALTER TABLE ONLY mochi.customer DROP CONSTRAINT customer_pkey;
ALTER TABLE ONLY mochi.customer DROP CONSTRAINT customer_cst_id_key;
ALTER TABLE ONLY mochi.currency DROP CONSTRAINT currency_pkey;
ALTER TABLE ONLY mochi.category_type DROP CONSTRAINT category_type_pkey;
ALTER TABLE ONLY mochi.category_product DROP CONSTRAINT category_product_pkey;
ALTER TABLE ONLY mochi.category DROP CONSTRAINT category_pkey;
ALTER TABLE ONLY mochi.category_brand DROP CONSTRAINT category_brand_pkey;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT category_attr_lcl_pkey;
ALTER TABLE ONLY mochi.brand DROP CONSTRAINT brand_pkey;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT brand_category_pkey;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT brand_attr_lcl_pkey;
ALTER TABLE mochi.role_type ALTER COLUMN rle_typ_id DROP DEFAULT;
ALTER TABLE mochi.party_type ALTER COLUMN pty_typ_id DROP DEFAULT;
ALTER TABLE mochi.party ALTER COLUMN pty_id DROP DEFAULT;
DROP VIEW mochi.vw_category_product;
DROP VIEW mochi.vw_category_brand;
DROP TABLE mochi.tag_attr_lcl;
DROP SEQUENCE mochi.tag_attr_lcl_tag_id_seq;
DROP TABLE mochi.tag;
DROP SEQUENCE mochi.tag_tag_id_seq;
DROP TABLE mochi.supplier;
DROP SEQUENCE mochi.role_type_role_typ_id_seq;
DROP SEQUENCE mochi.role_type_rle_typ_id_seq;
DROP TABLE mochi.role_type;
DROP TABLE mochi.role;
DROP SEQUENCE mochi.role_rle_id_seq;
DROP TABLE mochi.promotion_type;
DROP TABLE mochi.promotion_product;
DROP TABLE mochi.promotion_order;
DROP TABLE mochi.promotion_category;
DROP SEQUENCE mochi.promotion_category_prm_cat_id_seq;
DROP TABLE mochi.promotion_brand;
DROP TABLE mochi.promotion;
DROP TABLE mochi.product_type;
DROP SEQUENCE mochi.product_type_prd_typ_id_seq;
DROP SEQUENCE mochi.product_tag_attr_lcl_tag_id_seq;
DROP TABLE mochi.product_tag;
DROP SEQUENCE mochi.product_tag_prd_tag_id_seq;
DROP TABLE mochi.product_supplier;
DROP TABLE mochi.product_status;
DROP SEQUENCE mochi.product_status_prd_sts_id_seq;
DROP TABLE mochi.product_category;
DROP SEQUENCE mochi.product_category_prd_cat_id_seq;
DROP TABLE mochi.product_attr_lcl;
DROP TABLE mochi.product;
DROP TABLE mochi.price_type;
DROP TABLE mochi.price;
DROP SEQUENCE mochi.price_prc_id_seq;
DROP SEQUENCE mochi.person_id_seq;
DROP TABLE mochi.person;
DROP SEQUENCE mochi.party_type_pty_typ_id_seq;
DROP TABLE mochi.party_type;
DROP SEQUENCE mochi.party_pty_id_seq;
DROP SEQUENCE mochi.party_party_id_seq;
DROP TABLE mochi.party;
DROP TABLE mochi.organisation;
DROP TABLE mochi.order_line;
DROP TABLE mochi."order";
DROP TABLE mochi.location;
DROP TABLE mochi.locale;
DROP TABLE mochi.layout_category;
DROP SEQUENCE mochi.layout_category_lay_cat_id_seq;
DROP TABLE mochi.layout;
DROP SEQUENCE mochi.layout_lay_id_seq;
DROP TABLE mochi.inventory_transaction;
DROP TABLE mochi.inventory_on_hand;
DROP TABLE mochi.hierarchy;
DROP SEQUENCE mochi.hierarchy_hir_id_seq;
DROP SEQUENCE mochi.hibernate_sequence;
DROP TABLE mochi.discount_type;
DROP TABLE mochi.discount;
DROP TABLE mochi.customer;
DROP SEQUENCE mochi.customer_cst_id_seq;
DROP TABLE mochi.currency;
DROP TABLE mochi.category_type;
DROP SEQUENCE mochi.category_type_cat_typ_id_seq;
DROP TABLE mochi.category_product;
DROP TABLE mochi.category_brand;
DROP TABLE mochi.category_attr_lcl;
DROP SEQUENCE mochi.category_attr_lcl_cat_id_seq;
DROP TABLE mochi.category;
DROP SEQUENCE mochi.category_cat_id_seq;
DROP TABLE mochi.brand_category;
DROP SEQUENCE mochi.brand_category_bnd_cat_id_seq;
DROP TABLE mochi.brand_attr_lcl;
DROP SEQUENCE mochi.brand_attr_lcl_bnd_id_seq;
DROP TABLE mochi.brand;
DROP SEQUENCE mochi.brand_bnd_id_seq;
DROP TABLE mochi.address_type;
DROP TABLE mochi.address;
DROP FUNCTION mochi.ft_product_categories(text, text);
DROP FUNCTION mochi.ft_categories(text);
DROP FUNCTION mochi.ft_brand_categories(text, text);
DROP SCHEMA mochi;
--
-- Name: mochi; Type: SCHEMA; Schema: -; Owner: mochidb_owner
--

CREATE SCHEMA mochi;


ALTER SCHEMA mochi OWNER TO mochidb_owner;

--
-- Name: ft_brand_categories(text, text); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION mochi.ft_brand_categories(text, text) RETURNS TABLE(cat_id bigint, cat_cd text, cat_lvl bigint, hir_id bigint, cat_prnt_id bigint, cat_typ_id bigint, product_count bigint, child_cat_count bigint, max_price numeric)
    LANGUAGE sql
    AS '



 SELECT p.cat_id,



    p.cat_cd,



    p.cat_lvl,



    p.hir_id,



    p.cat_typ_id,



    p.cat_prnt_id,



    count(DISTINCT prd.upc_cd) AS product_count,



    count(DISTINCT cc.cat_cd) AS child_cat_count,



    coalesce(



    max(CASE



	WHEN pt.prc_typ_cd = ''MKD01''



	THEN prc.prc_val



	END),



    max(CASE



	WHEN pt.prc_typ_cd = ''RET01''



	THEN prc.prc_val



	END)) as max_price



   FROM mochi.category p



     JOIN LATERAL mochi.ft_categories(p.cat_cd::text) cc(cat_id, cat_cd, cat_prnt_id, cat_typ_id) ON 1 = 1



     LEFT JOIN mochi.brand_category pc ON cc.cat_id = pc.cat_id



     LEFT JOIN mochi.brand bnd ON pc.bnd_id = bnd.bnd_id



     LEFT JOIN mochi.product prd ON pc.bnd_id = prd.bnd_id



     LEFT JOIN mochi.price prc ON prd.prd_id = prc.prd_id AND now() between prc.prc_st_dt and prc.prc_en_dt



     LEFT JOIN mochi.currency curr ON prc.ccy_id = curr.ccy_id 



     LEFT JOIN mochi.price_type pt ON prc.prc_typ_id = pt.prc_typ_id 



  WHERE cc.cat_typ_id = 2



  AND p.cat_cd = $1



  AND ccy_cd = $2



  GROUP BY p.cat_id, p.cat_cd, p.cat_lvl, p.hir_id, p.cat_typ_id, p.cat_prnt_id;



';


ALTER FUNCTION mochi.ft_brand_categories(text, text) OWNER TO mochidb_owner;

--
-- Name: ft_categories(text); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION mochi.ft_categories(text) RETURNS TABLE(cat_id bigint, cat_cd text, cat_prnt_id bigint, cat_typ_id bigint)
    LANGUAGE sql
    AS '


WITH RECURSIVE 


    -- starting node(s)


    starting (cat_id, cat_cd, cat_prnt_id, cat_typ_id) AS


    (


      SELECT t.cat_id, t.cat_cd, t.cat_prnt_id, t.cat_typ_id


      FROM mochi.category AS t


      WHERE t.cat_cd = $1        


    ),


    descendants (cat_id, cat_cd, cat_prnt_id, cat_typ_id) AS


    (


      SELECT t.cat_id, t.cat_cd, t.cat_prnt_id, t.cat_typ_id


      FROM mochi.category AS t


      WHERE t.cat_cd = $1


      UNION ALL


      SELECT t.cat_id, t.cat_cd, t.cat_prnt_id, t.cat_typ_id


      FROM mochi.category AS t 


		JOIN descendants AS d 


		ON t.cat_prnt_id = d.cat_id


    )





SELECT 	descendants.cat_id,


		descendants.cat_cd,


		descendants.cat_prnt_id,


		descendants.cat_typ_id


FROM  starting 


		cross join descendants ';


ALTER FUNCTION mochi.ft_categories(text) OWNER TO mochidb_owner;

--
-- Name: ft_product_categories(text, text); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION mochi.ft_product_categories(text, text) RETURNS TABLE(cat_id bigint, cat_cd text, cat_lvl bigint, hir_id bigint, cat_prnt_id bigint, cat_typ_id bigint, product_count bigint, child_cat_count bigint, max_price numeric)
    LANGUAGE sql
    AS '



 SELECT p.cat_id,



    p.cat_cd,



    p.cat_lvl,



    p.hir_id,



    p.cat_typ_id,



    p.cat_prnt_id,



    count(DISTINCT prd.upc_cd) AS product_count,



    count(DISTINCT cc.cat_cd) AS child_cat_count,



    coalesce(



    max(CASE



	WHEN pt.prc_typ_cd = ''MKD01''



	THEN prc.prc_val



	END),



    max(CASE



	WHEN pt.prc_typ_cd = ''RET01''



	THEN prc.prc_val



	END)) as max_price



   FROM mochi.category p



     JOIN LATERAL mochi.ft_categories(p.cat_cd::text) cc(cat_id, cat_cd, cat_prnt_id, cat_typ_id) ON 1 = 1



     LEFT JOIN mochi.product_category pc ON cc.cat_id = pc.cat_id



     LEFT JOIN mochi.product prd ON pc.prd_id = prd.prd_id



     LEFT JOIN mochi.price prc ON prd.prd_id = prc.prd_id AND now() between prc.prc_st_dt and prc.prc_en_dt



     LEFT JOIN mochi.currency curr ON prc.ccy_id = curr.ccy_id 



     LEFT JOIN mochi.price_type pt ON prc.prc_typ_id = pt.prc_typ_id 



  WHERE cc.cat_typ_id = 1



  AND p.cat_cd = $1



  AND ccy_cd = $2



  GROUP BY p.cat_id, p.cat_cd, p.cat_lvl, p.hir_id, p.cat_typ_id, p.cat_prnt_id;



';


ALTER FUNCTION mochi.ft_product_categories(text, text) OWNER TO mochidb_owner;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: address; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.address (
);


ALTER TABLE mochi.address OWNER TO mochidb_owner;

--
-- Name: address_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.address_type (
);


ALTER TABLE mochi.address_type OWNER TO mochidb_owner;

--
-- Name: brand_bnd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.brand_bnd_id_seq
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.brand_bnd_id_seq OWNER TO mochidb_owner;

--
-- Name: brand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.brand (
    bnd_id bigint DEFAULT nextval('mochi.brand_bnd_id_seq'::regclass) NOT NULL,
    bnd_cd character(5)
);


ALTER TABLE mochi.brand OWNER TO mochidb_owner;

--
-- Name: brand_attr_lcl_bnd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.brand_attr_lcl_bnd_id_seq
    START WITH 19
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.brand_attr_lcl_bnd_id_seq OWNER TO mochidb_owner;

--
-- Name: brand_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.brand_attr_lcl (
    bnd_lcl_id bigint DEFAULT nextval('mochi.brand_attr_lcl_bnd_id_seq'::regclass) NOT NULL,
    bnd_id bigint NOT NULL,
    bnd_desc character varying(100),
    bnd_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.brand_attr_lcl OWNER TO mochidb_owner;

--
-- Name: brand_category_bnd_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.brand_category_bnd_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.brand_category_bnd_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: brand_category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.brand_category (
    bnd_cat_id bigint DEFAULT nextval('mochi.brand_category_bnd_cat_id_seq'::regclass) NOT NULL,
    bnd_id bigint,
    cat_id bigint
);


ALTER TABLE mochi.brand_category OWNER TO mochidb_owner;

--
-- Name: category_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.category_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category (
    cat_id bigint DEFAULT nextval('mochi.category_cat_id_seq'::regclass) NOT NULL,
    cat_cd character varying(5) NOT NULL,
    cat_prnt_id bigint,
    cat_lvl bigint,
    cat_typ_id bigint,
    hir_id bigint
);


ALTER TABLE mochi.category OWNER TO mochidb_owner;

--
-- Name: category_attr_lcl_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.category_attr_lcl_cat_id_seq
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_attr_lcl_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: category_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category_attr_lcl (
    cat_lcl_id bigint DEFAULT nextval('mochi.category_attr_lcl_cat_id_seq'::regclass) NOT NULL,
    cat_id bigint NOT NULL,
    cat_desc character varying(100),
    cat_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.category_attr_lcl OWNER TO mochidb_owner;

--
-- Name: category_brand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category_brand (
    cat_id bigint DEFAULT nextval('mochi.category_cat_id_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.category_brand OWNER TO mochidb_owner;

--
-- Name: category_product; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category_product (
    cat_id bigint DEFAULT nextval('mochi.category_cat_id_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.category_product OWNER TO mochidb_owner;

--
-- Name: category_type_cat_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.category_type_cat_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_type_cat_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: category_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category_type (
    cat_typ_id bigint DEFAULT nextval('mochi.category_type_cat_typ_id_seq'::regclass) NOT NULL,
    cat_typ_cd character varying(5) NOT NULL,
    cat_typ_desc character varying(20)
);


ALTER TABLE mochi.category_type OWNER TO mochidb_owner;

--
-- Name: currency; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.currency (
    ccy_id bigint NOT NULL,
    ccy_cd character(3)
);


ALTER TABLE mochi.currency OWNER TO mochidb_owner;

--
-- Name: customer_cst_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.customer_cst_id_seq
    START WITH 1000000001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.customer_cst_id_seq OWNER TO mochidb_owner;

--
-- Name: customer; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.customer (
    rle_id bigint NOT NULL,
    cst_id character(10) DEFAULT nextval('mochi.customer_cst_id_seq'::regclass)
);


ALTER TABLE mochi.customer OWNER TO mochidb_owner;

--
-- Name: discount; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.discount (
    dis_id bigint NOT NULL,
    dis_cd character(5),
    prm_id bigint,
    dis_typ_id bigint NOT NULL,
    dis_val numeric
);


ALTER TABLE mochi.discount OWNER TO mochidb_owner;

--
-- Name: discount_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.discount_type (
    dis_typ_id bigint NOT NULL,
    dis_typ_desc character varying NOT NULL
);


ALTER TABLE mochi.discount_type OWNER TO mochidb_owner;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.hibernate_sequence
    START WITH 232134
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.hibernate_sequence OWNER TO mochidb_owner;

--
-- Name: hierarchy_hir_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.hierarchy_hir_id_seq
    START WITH 41
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.hierarchy_hir_id_seq OWNER TO mochidb_owner;

--
-- Name: hierarchy; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.hierarchy (
    hir_id bigint DEFAULT nextval('mochi.hierarchy_hir_id_seq'::regclass) NOT NULL,
    hir_cd character varying(5) NOT NULL,
    hir_desc character varying(100) NOT NULL
);


ALTER TABLE mochi.hierarchy OWNER TO mochidb_owner;

--
-- Name: inventory_on_hand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.inventory_on_hand (
);


ALTER TABLE mochi.inventory_on_hand OWNER TO mochidb_owner;

--
-- Name: inventory_transaction; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.inventory_transaction (
);


ALTER TABLE mochi.inventory_transaction OWNER TO mochidb_owner;

--
-- Name: layout_lay_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.layout_lay_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.layout_lay_id_seq OWNER TO mochidb_owner;

--
-- Name: layout; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.layout (
    lay_id bigint DEFAULT nextval('mochi.layout_lay_id_seq'::regclass) NOT NULL,
    lay_cd character varying(10) NOT NULL,
    lay_desc character varying(100) NOT NULL
);


ALTER TABLE mochi.layout OWNER TO mochidb_owner;

--
-- Name: layout_category_lay_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.layout_category_lay_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.layout_category_lay_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: layout_category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.layout_category (
    lay_cat_id bigint DEFAULT nextval('mochi.layout_category_lay_cat_id_seq'::regclass) NOT NULL,
    lay_id bigint,
    cat_id bigint
);


ALTER TABLE mochi.layout_category OWNER TO mochidb_owner;

--
-- Name: locale; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.locale (
    lcl_cd character varying(20) NOT NULL
);


ALTER TABLE mochi.locale OWNER TO mochidb_owner;

--
-- Name: location; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.location (
);


ALTER TABLE mochi.location OWNER TO mochidb_owner;

--
-- Name: order; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi."order" (
    ord_id bigint NOT NULL,
    pty_id bigint NOT NULL
);


ALTER TABLE mochi."order" OWNER TO mochidb_owner;

--
-- Name: order_line; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.order_line (
    ord_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    ord_lne_no bigint NOT NULL
);


ALTER TABLE mochi.order_line OWNER TO mochidb_owner;

--
-- Name: organisation; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.organisation (
    org_id bigint NOT NULL,
    org_nme character varying(100) NOT NULL,
    org_reg_no character varying(50) NOT NULL
);


ALTER TABLE mochi.organisation OWNER TO mochidb_owner;

--
-- Name: party; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.party (
    pty_id bigint NOT NULL,
    pty_typ_id bigint NOT NULL
);


ALTER TABLE mochi.party OWNER TO mochidb_owner;

--
-- Name: party_party_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.party_party_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.party_party_id_seq OWNER TO mochidb_owner;

--
-- Name: party_pty_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.party_pty_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.party_pty_id_seq OWNER TO mochidb_owner;

--
-- Name: party_pty_id_seq; Type: SEQUENCE OWNED BY; Schema: mochi; Owner: mochidb_owner
--

ALTER SEQUENCE mochi.party_pty_id_seq OWNED BY mochi.party.pty_id;


--
-- Name: party_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.party_type (
    pty_typ_id bigint NOT NULL,
    pty_typ_desc character varying NOT NULL
);


ALTER TABLE mochi.party_type OWNER TO mochidb_owner;

--
-- Name: party_type_pty_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.party_type_pty_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.party_type_pty_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: party_type_pty_typ_id_seq; Type: SEQUENCE OWNED BY; Schema: mochi; Owner: mochidb_owner
--

ALTER SEQUENCE mochi.party_type_pty_typ_id_seq OWNED BY mochi.party_type.pty_typ_id;


--
-- Name: person; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.person (
    psn_id bigint NOT NULL,
    psn_gvn_nm character varying,
    psn_fml_nm character varying,
    enb boolean
);


ALTER TABLE mochi.person OWNER TO mochidb_owner;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.person_id_seq
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.person_id_seq OWNER TO mochidb_owner;

--
-- Name: price_prc_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.price_prc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.price_prc_id_seq OWNER TO mochidb_owner;

--
-- Name: price; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.price (
    prc_id bigint DEFAULT nextval('mochi.price_prc_id_seq'::regclass) NOT NULL,
    prc_typ_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    prc_val numeric NOT NULL,
    ccy_id bigint NOT NULL
);


ALTER TABLE mochi.price OWNER TO mochidb_owner;

--
-- Name: price_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.price_type (
    prc_typ_id bigint NOT NULL,
    prc_typ_desc character varying,
    prc_typ_cd character varying
);


ALTER TABLE mochi.price_type OWNER TO mochidb_owner;

--
-- Name: product; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product (
    prd_id bigint NOT NULL,
    upc_cd character varying(12) NOT NULL,
    prd_crtd_dt date NOT NULL,
    bnd_id bigint NOT NULL,
    prd_typ_id bigint NOT NULL,
    prd_sts_id bigint NOT NULL
);


ALTER TABLE mochi.product OWNER TO mochidb_owner;

--
-- Name: product_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_attr_lcl (
    prd_lcl_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    prd_desc character varying(100),
    prd_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.product_attr_lcl OWNER TO mochidb_owner;

--
-- Name: product_category_prd_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_category_prd_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_category_prd_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: product_category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_category (
    prd_cat_id bigint DEFAULT nextval('mochi.product_category_prd_cat_id_seq'::regclass) NOT NULL,
    prd_id bigint,
    cat_id bigint
);


ALTER TABLE mochi.product_category OWNER TO mochidb_owner;

--
-- Name: product_status_prd_sts_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_status_prd_sts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_status_prd_sts_id_seq OWNER TO mochidb_owner;

--
-- Name: product_status; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_status (
    prd_sts_id bigint DEFAULT nextval('mochi.product_status_prd_sts_id_seq'::regclass) NOT NULL,
    prd_sts_cd character varying(5) NOT NULL,
    prd_sts_desc character varying(20)
);


ALTER TABLE mochi.product_status OWNER TO mochidb_owner;

--
-- Name: product_supplier; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_supplier (
);


ALTER TABLE mochi.product_supplier OWNER TO mochidb_owner;

--
-- Name: product_tag_prd_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_tag_prd_tag_id_seq
    START WITH 25
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_tag_prd_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: product_tag; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_tag (
    prd_tag_id bigint DEFAULT nextval('mochi.product_tag_prd_tag_id_seq'::regclass) NOT NULL,
    prd_id bigint,
    tag_id bigint
);


ALTER TABLE mochi.product_tag OWNER TO mochidb_owner;

--
-- Name: product_tag_attr_lcl_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_tag_attr_lcl_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_tag_attr_lcl_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: product_type_prd_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_type_prd_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_type_prd_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: product_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_type (
    prd_typ_id bigint DEFAULT nextval('mochi.product_type_prd_typ_id_seq'::regclass) NOT NULL,
    prd_typ_cd character varying(5) NOT NULL,
    prd_typ_desc character varying(20)
);


ALTER TABLE mochi.product_type OWNER TO mochidb_owner;

--
-- Name: promotion; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion (
    prm_id bigint NOT NULL,
    prm_cd character(5) NOT NULL,
    prm_sht_desc character varying,
    prm_lng_desc character varying,
    prm_st_dt date NOT NULL,
    prm_en_dt date NOT NULL,
    prm_typ_id bigint NOT NULL
);


ALTER TABLE mochi.promotion OWNER TO mochidb_owner;

--
-- Name: promotion_brand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_brand (
);


ALTER TABLE mochi.promotion_brand OWNER TO mochidb_owner;

--
-- Name: promotion_category_prm_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.promotion_category_prm_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.promotion_category_prm_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: promotion_category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_category (
    prm_cat_id bigint DEFAULT nextval('mochi.promotion_category_prm_cat_id_seq'::regclass) NOT NULL,
    prm_id bigint,
    cat_id bigint
);


ALTER TABLE mochi.promotion_category OWNER TO mochidb_owner;

--
-- Name: promotion_order; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_order (
);


ALTER TABLE mochi.promotion_order OWNER TO mochidb_owner;

--
-- Name: promotion_product; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_product (
);


ALTER TABLE mochi.promotion_product OWNER TO mochidb_owner;

--
-- Name: promotion_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_type (
    prm_typ_id bigint NOT NULL,
    prm_typ_desc character varying
);


ALTER TABLE mochi.promotion_type OWNER TO mochidb_owner;

--
-- Name: role_rle_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.role_rle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.role_rle_id_seq OWNER TO mochidb_owner;

--
-- Name: role; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.role (
    rle_id bigint DEFAULT nextval('mochi.role_rle_id_seq'::regclass) NOT NULL,
    rle_typ_id bigint NOT NULL,
    rle_start_dttm date DEFAULT now() NOT NULL,
    pty_id bigint NOT NULL
);


ALTER TABLE mochi.role OWNER TO mochidb_owner;

--
-- Name: role_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.role_type (
    rle_typ_id bigint NOT NULL,
    rle_typ_desc character varying NOT NULL
);


ALTER TABLE mochi.role_type OWNER TO mochidb_owner;

--
-- Name: role_type_rle_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.role_type_rle_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.role_type_rle_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: role_type_rle_typ_id_seq; Type: SEQUENCE OWNED BY; Schema: mochi; Owner: mochidb_owner
--

ALTER SEQUENCE mochi.role_type_rle_typ_id_seq OWNED BY mochi.role_type.rle_typ_id;


--
-- Name: role_type_role_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.role_type_role_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.role_type_role_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: supplier; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.supplier (
);


ALTER TABLE mochi.supplier OWNER TO mochidb_owner;

--
-- Name: tag_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.tag_tag_id_seq
    START WITH 15
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.tag_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: tag; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.tag (
    tag_id bigint DEFAULT nextval('mochi.tag_tag_id_seq'::regclass) NOT NULL,
    tag_cd character(5) NOT NULL
);


ALTER TABLE mochi.tag OWNER TO mochidb_owner;

--
-- Name: tag_attr_lcl_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.tag_attr_lcl_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.tag_attr_lcl_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: tag_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.tag_attr_lcl (
    tag_lcl_id bigint DEFAULT nextval('mochi.tag_attr_lcl_tag_id_seq'::regclass) NOT NULL,
    tag_id bigint NOT NULL,
    tag_desc character varying(100),
    tag_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.tag_attr_lcl OWNER TO mochidb_owner;

--
-- Name: vw_category_brand; Type: VIEW; Schema: mochi; Owner: mochidb_owner
--

CREATE VIEW mochi.vw_category_brand AS
 SELECT p.cat_id,
    p.cat_cd,
    p.cat_lvl,
    p.hir_id,
    p.cat_typ_id,
    p.cat_prnt_id,
    curr.ccy_cd,
    count(DISTINCT prd.upc_cd) AS product_count,
    count(DISTINCT cc.cat_cd) AS child_cat_count,
    COALESCE(max(
        CASE
            WHEN ((pt.prc_typ_cd)::text = 'MKD01'::text) THEN prc.prc_val
            ELSE NULL::numeric
        END), max(
        CASE
            WHEN ((pt.prc_typ_cd)::text = 'RET01'::text) THEN prc.prc_val
            ELSE NULL::numeric
        END)) AS max_price
   FROM (((((((mochi.category p
     JOIN LATERAL mochi.ft_categories((p.cat_cd)::text) cc(cat_id, cat_cd, cat_prnt_id, cat_typ_id) ON ((1 = 1)))
     LEFT JOIN mochi.brand_category pc ON ((cc.cat_id = pc.cat_id)))
     LEFT JOIN mochi.brand bnd ON ((pc.bnd_id = bnd.bnd_id)))
     LEFT JOIN mochi.product prd ON ((pc.bnd_id = prd.bnd_id)))
     LEFT JOIN mochi.price prc ON ((prd.prd_id = prc.prd_id)))
     LEFT JOIN mochi.currency curr ON ((prc.ccy_id = curr.ccy_id)))
     LEFT JOIN mochi.price_type pt ON ((prc.prc_typ_id = pt.prc_typ_id)))
  WHERE (cc.cat_typ_id = 2)
  GROUP BY p.cat_id, p.cat_cd, p.cat_lvl, p.hir_id, p.cat_typ_id, p.cat_prnt_id, curr.ccy_cd;


ALTER TABLE mochi.vw_category_brand OWNER TO mochidb_owner;

--
-- Name: vw_category_product; Type: VIEW; Schema: mochi; Owner: mochidb_owner
--

CREATE VIEW mochi.vw_category_product AS
 SELECT p.cat_id,
    p.cat_cd,
    p.cat_lvl,
    p.hir_id,
    p.cat_typ_id,
    p.cat_prnt_id,
    curr.ccy_cd,
    count(DISTINCT prd.upc_cd) AS product_count,
    count(DISTINCT cc.cat_cd) AS child_cat_count,
    COALESCE(max(
        CASE
            WHEN ((pt.prc_typ_cd)::text = 'MKD01'::text) THEN prc.prc_val
            ELSE NULL::numeric
        END), max(
        CASE
            WHEN ((pt.prc_typ_cd)::text = 'RET01'::text) THEN prc.prc_val
            ELSE NULL::numeric
        END)) AS max_price
   FROM ((((((mochi.category p
     JOIN LATERAL mochi.ft_categories((p.cat_cd)::text) cc(cat_id, cat_cd, cat_prnt_id, cat_typ_id) ON ((1 = 1)))
     LEFT JOIN mochi.product_category pc ON ((cc.cat_id = pc.cat_id)))
     LEFT JOIN mochi.product prd ON ((pc.prd_id = prd.prd_id)))
     LEFT JOIN mochi.price prc ON ((prd.prd_id = prc.prd_id)))
     LEFT JOIN mochi.currency curr ON ((prc.ccy_id = curr.ccy_id)))
     LEFT JOIN mochi.price_type pt ON ((prc.prc_typ_id = pt.prc_typ_id)))
  WHERE (cc.cat_typ_id = 1)
  GROUP BY p.cat_id, p.cat_cd, p.cat_lvl, p.hir_id, p.cat_typ_id, p.cat_prnt_id, curr.ccy_cd
 HAVING (count(DISTINCT prd.upc_cd) <> 0);


ALTER TABLE mochi.vw_category_product OWNER TO mochidb_owner;

--
-- Name: pty_id; Type: DEFAULT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party ALTER COLUMN pty_id SET DEFAULT nextval('mochi.party_pty_id_seq'::regclass);


--
-- Name: pty_typ_id; Type: DEFAULT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party_type ALTER COLUMN pty_typ_id SET DEFAULT nextval('mochi.party_type_pty_typ_id_seq'::regclass);


--
-- Name: rle_typ_id; Type: DEFAULT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role_type ALTER COLUMN rle_typ_id SET DEFAULT nextval('mochi.role_type_rle_typ_id_seq'::regclass);


--
-- Name: brand_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT brand_attr_lcl_pkey PRIMARY KEY (bnd_lcl_id);


--
-- Name: brand_category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_category
    ADD CONSTRAINT brand_category_pkey PRIMARY KEY (bnd_cat_id);


--
-- Name: brand_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (bnd_id);


--
-- Name: category_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT category_attr_lcl_pkey PRIMARY KEY (cat_lcl_id);


--
-- Name: category_brand_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_brand
    ADD CONSTRAINT category_brand_pkey PRIMARY KEY (cat_id);


--
-- Name: category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (cat_id);


--
-- Name: category_product_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_product
    ADD CONSTRAINT category_product_pkey PRIMARY KEY (cat_id);


--
-- Name: category_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_type
    ADD CONSTRAINT category_type_pkey PRIMARY KEY (cat_typ_id);


--
-- Name: currency_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (ccy_id);


--
-- Name: customer_cst_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.customer
    ADD CONSTRAINT customer_cst_id_key UNIQUE (cst_id);


--
-- Name: customer_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (rle_id);


--
-- Name: discount_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.discount
    ADD CONSTRAINT discount_pkey PRIMARY KEY (dis_id);


--
-- Name: discount_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.discount_type
    ADD CONSTRAINT discount_type_pkey PRIMARY KEY (dis_typ_id);


--
-- Name: hierarchy_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.hierarchy
    ADD CONSTRAINT hierarchy_pkey PRIMARY KEY (hir_id);


--
-- Name: layout_category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.layout_category
    ADD CONSTRAINT layout_category_pkey PRIMARY KEY (lay_cat_id);


--
-- Name: layout_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.layout
    ADD CONSTRAINT layout_pkey PRIMARY KEY (lay_id);


--
-- Name: order_line_ord_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.order_line
    ADD CONSTRAINT order_line_ord_id_key UNIQUE (ord_id);


--
-- Name: order_line_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.order_line
    ADD CONSTRAINT order_line_pkey PRIMARY KEY (ord_id, prd_id, ord_lne_no);


--
-- Name: orders_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi."order"
    ADD CONSTRAINT orders_pkey PRIMARY KEY (ord_id);


--
-- Name: orders_pty_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi."order"
    ADD CONSTRAINT orders_pty_id_key UNIQUE (pty_id);


--
-- Name: organisation_org_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.organisation
    ADD CONSTRAINT organisation_org_id_key UNIQUE (org_id);


--
-- Name: party_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party
    ADD CONSTRAINT party_pkey PRIMARY KEY (pty_id);


--
-- Name: party_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party_type
    ADD CONSTRAINT party_type_pkey PRIMARY KEY (pty_typ_id);


--
-- Name: party_type_pty_typ_desc_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party_type
    ADD CONSTRAINT party_type_pty_typ_desc_key UNIQUE (pty_typ_desc);


--
-- Name: person_psn_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.person
    ADD CONSTRAINT person_psn_id_key UNIQUE (psn_id);


--
-- Name: pk_locale; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.locale
    ADD CONSTRAINT pk_locale PRIMARY KEY (lcl_cd);


--
-- Name: price_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.price
    ADD CONSTRAINT price_pkey PRIMARY KEY (prc_id);


--
-- Name: price_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.price_type
    ADD CONSTRAINT price_type_pkey PRIMARY KEY (prc_typ_id);


--
-- Name: product_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_attr_lcl
    ADD CONSTRAINT product_attr_lcl_pkey PRIMARY KEY (prd_lcl_id);


--
-- Name: product_category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (prd_cat_id);


--
-- Name: product_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (prd_id);


--
-- Name: product_status_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_status
    ADD CONSTRAINT product_status_pkey PRIMARY KEY (prd_sts_id);


--
-- Name: product_tag_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_tag
    ADD CONSTRAINT product_tag_pkey PRIMARY KEY (prd_tag_id);


--
-- Name: product_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_type
    ADD CONSTRAINT product_type_pkey PRIMARY KEY (prd_typ_id);


--
-- Name: promotion_category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_category
    ADD CONSTRAINT promotion_category_pkey PRIMARY KEY (prm_cat_id);


--
-- Name: promotion_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion
    ADD CONSTRAINT promotion_pkey PRIMARY KEY (prm_id);


--
-- Name: promotion_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_type
    ADD CONSTRAINT promotion_type_pkey PRIMARY KEY (prm_typ_id);


--
-- Name: role_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (rle_id);


--
-- Name: role_pty_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role
    ADD CONSTRAINT role_pty_id_key UNIQUE (pty_id);


--
-- Name: role_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role_type
    ADD CONSTRAINT role_type_pkey PRIMARY KEY (rle_typ_id);


--
-- Name: role_type_rle_typ_desc_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role_type
    ADD CONSTRAINT role_type_rle_typ_desc_key UNIQUE (rle_typ_desc);


--
-- Name: tag_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT tag_attr_lcl_pkey PRIMARY KEY (tag_lcl_id);


--
-- Name: tag_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (tag_id);


--
-- Name: uc_bnd_desc_lcl_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT uc_bnd_desc_lcl_cd UNIQUE (bnd_desc, lcl_cd);


--
-- Name: uc_bnd_lcl; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT uc_bnd_lcl UNIQUE (bnd_id, lcl_cd);


--
-- Name: uc_brand_category; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_category
    ADD CONSTRAINT uc_brand_category UNIQUE (bnd_id, cat_id);


--
-- Name: uc_cat_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category
    ADD CONSTRAINT uc_cat_cd UNIQUE (cat_cd);


--
-- Name: uc_cat_desc; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT uc_cat_desc UNIQUE (cat_desc, lcl_cd);


--
-- Name: uc_cat_lcl; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT uc_cat_lcl UNIQUE (cat_id, lcl_cd);


--
-- Name: uc_cat_typ_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_type
    ADD CONSTRAINT uc_cat_typ_cd UNIQUE (cat_typ_cd);


--
-- Name: uc_hir_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.hierarchy
    ADD CONSTRAINT uc_hir_cd UNIQUE (hir_cd);


--
-- Name: uc_lay_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.layout
    ADD CONSTRAINT uc_lay_cd UNIQUE (lay_cd);


--
-- Name: uc_lay_desc; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.layout
    ADD CONSTRAINT uc_lay_desc UNIQUE (lay_desc);


--
-- Name: uc_layout_category; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.layout_category
    ADD CONSTRAINT uc_layout_category UNIQUE (lay_id, cat_id);


--
-- Name: uc_prd_lcl_1; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_attr_lcl
    ADD CONSTRAINT uc_prd_lcl_1 UNIQUE (prd_id, lcl_cd);


--
-- Name: uc_prd_sts_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_status
    ADD CONSTRAINT uc_prd_sts_cd UNIQUE (prd_sts_cd);


--
-- Name: uc_prd_typ_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_type
    ADD CONSTRAINT uc_prd_typ_cd UNIQUE (prd_typ_cd);


--
-- Name: uc_prm_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion
    ADD CONSTRAINT uc_prm_cd UNIQUE (prm_cd);


--
-- Name: uc_product_category; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_category
    ADD CONSTRAINT uc_product_category UNIQUE (prd_id, cat_id);


--
-- Name: uc_product_tag; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_tag
    ADD CONSTRAINT uc_product_tag UNIQUE (prd_id, tag_id);


--
-- Name: uc_promotion_category; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_category
    ADD CONSTRAINT uc_promotion_category UNIQUE (prm_id, cat_id);


--
-- Name: uc_tag_desc; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT uc_tag_desc UNIQUE (tag_desc, lcl_cd);


--
-- Name: uc_tag_lcl; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT uc_tag_lcl UNIQUE (tag_id, lcl_cd);


--
-- Name: role_role_typ_id_role_start_dttm_party_id_key; Type: INDEX; Schema: mochi; Owner: mochidb_owner
--

CREATE UNIQUE INDEX role_role_typ_id_role_start_dttm_party_id_key ON mochi.role USING btree (rle_typ_id, rle_start_dttm, pty_id);


--
-- Name: brand_attr_lcl_bnd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT brand_attr_lcl_bnd_id_fkey FOREIGN KEY (bnd_id) REFERENCES mochi.brand(bnd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: brand_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT brand_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: brand_category_bnd_id_brand_bnd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_category
    ADD CONSTRAINT brand_category_bnd_id_brand_bnd_id_fkey FOREIGN KEY (bnd_id) REFERENCES mochi.brand(bnd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: brand_category_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_category
    ADD CONSTRAINT brand_category_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_attr_lcl_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT category_attr_lcl_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT category_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: category_brand_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_brand
    ADD CONSTRAINT category_brand_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_hir_id_hierarchy_hir_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category
    ADD CONSTRAINT category_hir_id_hierarchy_hir_id_fkey FOREIGN KEY (hir_id) REFERENCES mochi.hierarchy(hir_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_product_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_product
    ADD CONSTRAINT category_product_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: customer_role_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.customer
    ADD CONSTRAINT customer_role_id_fkey FOREIGN KEY (rle_id) REFERENCES mochi.role(rle_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: layout_category_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.layout_category
    ADD CONSTRAINT layout_category_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: layout_category_lay_id_layout_lay_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.layout_category
    ADD CONSTRAINT layout_category_lay_id_layout_lay_id_fkey FOREIGN KEY (lay_id) REFERENCES mochi.layout(lay_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: order_line_order_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.order_line
    ADD CONSTRAINT order_line_order_id_fkey FOREIGN KEY (ord_id) REFERENCES mochi."order"(ord_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: order_line_product_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.order_line
    ADD CONSTRAINT order_line_product_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: orders_party_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi."order"
    ADD CONSTRAINT orders_party_id_fkey FOREIGN KEY (pty_id) REFERENCES mochi.party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: organisation_org_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.organisation
    ADD CONSTRAINT organisation_org_id_fkey FOREIGN KEY (org_id) REFERENCES mochi.party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: party_pty_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party
    ADD CONSTRAINT party_pty_typ_id_fkey FOREIGN KEY (pty_typ_id) REFERENCES mochi.party_type(pty_typ_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: person_person_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.person
    ADD CONSTRAINT person_person_id_fkey FOREIGN KEY (psn_id) REFERENCES mochi.party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_attr_lcl
    ADD CONSTRAINT product_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: product_category_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_category
    ADD CONSTRAINT product_category_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_category_prd_id_product_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_category
    ADD CONSTRAINT product_category_prd_id_product_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_sts_id_product_status_sts_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product
    ADD CONSTRAINT product_sts_id_product_status_sts_id_fkey FOREIGN KEY (prd_sts_id) REFERENCES mochi.product_status(prd_sts_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_tag_prd_id_product_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_tag
    ADD CONSTRAINT product_tag_prd_id_product_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_tag_tag_id_tag_tag_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_tag
    ADD CONSTRAINT product_tag_tag_id_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES mochi.tag(tag_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_typ_id_product_type_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product
    ADD CONSTRAINT product_typ_id_product_type_typ_id_fkey FOREIGN KEY (prd_typ_id) REFERENCES mochi.product_type(prd_typ_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: promotion_category_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_category
    ADD CONSTRAINT promotion_category_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: promotion_category_prm_id_promotion_prm_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_category
    ADD CONSTRAINT promotion_category_prm_id_promotion_prm_id_fkey FOREIGN KEY (prm_id) REFERENCES mochi.promotion(prm_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: role_party_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role
    ADD CONSTRAINT role_party_id_fkey FOREIGN KEY (pty_id) REFERENCES mochi.party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: role_role_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role
    ADD CONSTRAINT role_role_typ_id_fkey FOREIGN KEY (rle_typ_id) REFERENCES mochi.role_type(rle_typ_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: tag_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT tag_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: tag_attr_lcl_tag_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT tag_attr_lcl_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES mochi.tag(tag_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: SCHEMA mochi; Type: ACL; Schema: -; Owner: mochidb_owner
--

REVOKE ALL ON SCHEMA mochi FROM PUBLIC;
REVOKE ALL ON SCHEMA mochi FROM mochidb_owner;
GRANT ALL ON SCHEMA mochi TO mochidb_owner;
GRANT USAGE ON SCHEMA mochi TO mochi_app;
GRANT ALL ON SCHEMA mochi TO security_owner;
GRANT USAGE ON SCHEMA mochi TO security_app;


--
-- Name: TABLE address; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.address FROM PUBLIC;
REVOKE ALL ON TABLE mochi.address FROM mochidb_owner;
GRANT ALL ON TABLE mochi.address TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.address TO mochi_app;


--
-- Name: TABLE address_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.address_type FROM PUBLIC;
REVOKE ALL ON TABLE mochi.address_type FROM mochidb_owner;
GRANT ALL ON TABLE mochi.address_type TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.address_type TO mochi_app;


--
-- Name: SEQUENCE brand_bnd_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.brand_bnd_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.brand_bnd_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.brand_bnd_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.brand_bnd_id_seq TO mochi_app;


--
-- Name: TABLE brand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.brand FROM PUBLIC;
REVOKE ALL ON TABLE mochi.brand FROM mochidb_owner;
GRANT ALL ON TABLE mochi.brand TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.brand TO mochi_app;


--
-- Name: SEQUENCE brand_attr_lcl_bnd_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.brand_attr_lcl_bnd_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.brand_attr_lcl_bnd_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.brand_attr_lcl_bnd_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.brand_attr_lcl_bnd_id_seq TO mochi_app;


--
-- Name: TABLE brand_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.brand_attr_lcl FROM PUBLIC;
REVOKE ALL ON TABLE mochi.brand_attr_lcl FROM mochidb_owner;
GRANT ALL ON TABLE mochi.brand_attr_lcl TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.brand_attr_lcl TO mochi_app;


--
-- Name: SEQUENCE brand_category_bnd_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.brand_category_bnd_cat_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.brand_category_bnd_cat_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.brand_category_bnd_cat_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.brand_category_bnd_cat_id_seq TO mochi_app;


--
-- Name: TABLE brand_category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.brand_category FROM PUBLIC;
REVOKE ALL ON TABLE mochi.brand_category FROM mochidb_owner;
GRANT ALL ON TABLE mochi.brand_category TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.brand_category TO mochi_app;


--
-- Name: SEQUENCE category_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.category_cat_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.category_cat_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.category_cat_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.category_cat_id_seq TO mochi_app;


--
-- Name: TABLE category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.category FROM PUBLIC;
REVOKE ALL ON TABLE mochi.category FROM mochidb_owner;
GRANT ALL ON TABLE mochi.category TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category TO mochi_app;


--
-- Name: SEQUENCE category_attr_lcl_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.category_attr_lcl_cat_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.category_attr_lcl_cat_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.category_attr_lcl_cat_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.category_attr_lcl_cat_id_seq TO mochi_app;


--
-- Name: TABLE category_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.category_attr_lcl FROM PUBLIC;
REVOKE ALL ON TABLE mochi.category_attr_lcl FROM mochidb_owner;
GRANT ALL ON TABLE mochi.category_attr_lcl TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category_attr_lcl TO mochi_app;


--
-- Name: TABLE category_brand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.category_brand FROM PUBLIC;
REVOKE ALL ON TABLE mochi.category_brand FROM mochidb_owner;
GRANT ALL ON TABLE mochi.category_brand TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category_brand TO mochi_app;


--
-- Name: TABLE category_product; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.category_product FROM PUBLIC;
REVOKE ALL ON TABLE mochi.category_product FROM mochidb_owner;
GRANT ALL ON TABLE mochi.category_product TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category_product TO mochi_app;


--
-- Name: SEQUENCE category_type_cat_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.category_type_cat_typ_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.category_type_cat_typ_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.category_type_cat_typ_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.category_type_cat_typ_id_seq TO mochi_app;


--
-- Name: TABLE category_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.category_type FROM PUBLIC;
REVOKE ALL ON TABLE mochi.category_type FROM mochidb_owner;
GRANT ALL ON TABLE mochi.category_type TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category_type TO mochi_app;


--
-- Name: TABLE currency; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.currency FROM PUBLIC;
REVOKE ALL ON TABLE mochi.currency FROM mochidb_owner;
GRANT ALL ON TABLE mochi.currency TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.currency TO mochi_app;


--
-- Name: SEQUENCE customer_cst_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.customer_cst_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.customer_cst_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.customer_cst_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.customer_cst_id_seq TO mochi_app;


--
-- Name: TABLE customer; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.customer FROM PUBLIC;
REVOKE ALL ON TABLE mochi.customer FROM mochidb_owner;
GRANT ALL ON TABLE mochi.customer TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.customer TO mochi_app;
GRANT SELECT ON TABLE mochi.customer TO security_app;


--
-- Name: TABLE discount; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.discount FROM PUBLIC;
REVOKE ALL ON TABLE mochi.discount FROM mochidb_owner;
GRANT ALL ON TABLE mochi.discount TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.discount TO mochi_app;


--
-- Name: TABLE discount_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.discount_type FROM PUBLIC;
REVOKE ALL ON TABLE mochi.discount_type FROM mochidb_owner;
GRANT ALL ON TABLE mochi.discount_type TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.discount_type TO mochi_app;


--
-- Name: SEQUENCE hibernate_sequence; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.hibernate_sequence FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.hibernate_sequence FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.hibernate_sequence TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.hibernate_sequence TO mochi_app;


--
-- Name: SEQUENCE hierarchy_hir_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.hierarchy_hir_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.hierarchy_hir_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.hierarchy_hir_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.hierarchy_hir_id_seq TO mochi_app;


--
-- Name: TABLE hierarchy; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.hierarchy FROM PUBLIC;
REVOKE ALL ON TABLE mochi.hierarchy FROM mochidb_owner;
GRANT ALL ON TABLE mochi.hierarchy TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.hierarchy TO mochi_app;


--
-- Name: TABLE inventory_on_hand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.inventory_on_hand FROM PUBLIC;
REVOKE ALL ON TABLE mochi.inventory_on_hand FROM mochidb_owner;
GRANT ALL ON TABLE mochi.inventory_on_hand TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.inventory_on_hand TO mochi_app;


--
-- Name: TABLE inventory_transaction; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.inventory_transaction FROM PUBLIC;
REVOKE ALL ON TABLE mochi.inventory_transaction FROM mochidb_owner;
GRANT ALL ON TABLE mochi.inventory_transaction TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.inventory_transaction TO mochi_app;


--
-- Name: SEQUENCE layout_lay_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.layout_lay_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.layout_lay_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.layout_lay_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.layout_lay_id_seq TO mochi_app;


--
-- Name: TABLE layout; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.layout FROM PUBLIC;
REVOKE ALL ON TABLE mochi.layout FROM mochidb_owner;
GRANT ALL ON TABLE mochi.layout TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.layout TO mochi_app;


--
-- Name: SEQUENCE layout_category_lay_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.layout_category_lay_cat_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.layout_category_lay_cat_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.layout_category_lay_cat_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.layout_category_lay_cat_id_seq TO mochi_app;


--
-- Name: TABLE layout_category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.layout_category FROM PUBLIC;
REVOKE ALL ON TABLE mochi.layout_category FROM mochidb_owner;
GRANT ALL ON TABLE mochi.layout_category TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.layout_category TO mochi_app;


--
-- Name: TABLE locale; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.locale FROM PUBLIC;
REVOKE ALL ON TABLE mochi.locale FROM mochidb_owner;
GRANT ALL ON TABLE mochi.locale TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.locale TO mochi_app;


--
-- Name: TABLE location; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.location FROM PUBLIC;
REVOKE ALL ON TABLE mochi.location FROM mochidb_owner;
GRANT ALL ON TABLE mochi.location TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.location TO mochi_app;


--
-- Name: TABLE "order"; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi."order" FROM PUBLIC;
REVOKE ALL ON TABLE mochi."order" FROM mochidb_owner;
GRANT ALL ON TABLE mochi."order" TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi."order" TO mochi_app;


--
-- Name: TABLE order_line; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.order_line FROM PUBLIC;
REVOKE ALL ON TABLE mochi.order_line FROM mochidb_owner;
GRANT ALL ON TABLE mochi.order_line TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.order_line TO mochi_app;


--
-- Name: TABLE organisation; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.organisation FROM PUBLIC;
REVOKE ALL ON TABLE mochi.organisation FROM mochidb_owner;
GRANT ALL ON TABLE mochi.organisation TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.organisation TO mochi_app;
GRANT SELECT ON TABLE mochi.organisation TO security_app;


--
-- Name: TABLE party; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.party FROM PUBLIC;
REVOKE ALL ON TABLE mochi.party FROM mochidb_owner;
GRANT ALL ON TABLE mochi.party TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.party TO mochi_app;
GRANT SELECT ON TABLE mochi.party TO security_app;


--
-- Name: SEQUENCE party_party_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.party_party_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.party_party_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.party_party_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.party_party_id_seq TO mochi_app;


--
-- Name: SEQUENCE party_pty_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.party_pty_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.party_pty_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.party_pty_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.party_pty_id_seq TO mochi_app;


--
-- Name: TABLE party_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.party_type FROM PUBLIC;
REVOKE ALL ON TABLE mochi.party_type FROM mochidb_owner;
GRANT ALL ON TABLE mochi.party_type TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.party_type TO mochi_app;
GRANT SELECT ON TABLE mochi.party_type TO security_app;


--
-- Name: SEQUENCE party_type_pty_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.party_type_pty_typ_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.party_type_pty_typ_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.party_type_pty_typ_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.party_type_pty_typ_id_seq TO mochi_app;


--
-- Name: TABLE person; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.person FROM PUBLIC;
REVOKE ALL ON TABLE mochi.person FROM mochidb_owner;
GRANT ALL ON TABLE mochi.person TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.person TO mochi_app;
GRANT SELECT ON TABLE mochi.person TO security_app;


--
-- Name: SEQUENCE person_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.person_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.person_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.person_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.person_id_seq TO mochi_app;


--
-- Name: SEQUENCE price_prc_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.price_prc_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.price_prc_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.price_prc_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.price_prc_id_seq TO mochi_app;


--
-- Name: TABLE price; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.price FROM PUBLIC;
REVOKE ALL ON TABLE mochi.price FROM mochidb_owner;
GRANT ALL ON TABLE mochi.price TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.price TO mochi_app;


--
-- Name: TABLE price_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.price_type FROM PUBLIC;
REVOKE ALL ON TABLE mochi.price_type FROM mochidb_owner;
GRANT ALL ON TABLE mochi.price_type TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.price_type TO mochi_app;


--
-- Name: TABLE product; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.product FROM PUBLIC;
REVOKE ALL ON TABLE mochi.product FROM mochidb_owner;
GRANT ALL ON TABLE mochi.product TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product TO mochi_app;


--
-- Name: TABLE product_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.product_attr_lcl FROM PUBLIC;
REVOKE ALL ON TABLE mochi.product_attr_lcl FROM mochidb_owner;
GRANT ALL ON TABLE mochi.product_attr_lcl TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_attr_lcl TO mochi_app;


--
-- Name: SEQUENCE product_category_prd_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.product_category_prd_cat_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.product_category_prd_cat_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_category_prd_cat_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_category_prd_cat_id_seq TO mochi_app;


--
-- Name: TABLE product_category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.product_category FROM PUBLIC;
REVOKE ALL ON TABLE mochi.product_category FROM mochidb_owner;
GRANT ALL ON TABLE mochi.product_category TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_category TO mochi_app;


--
-- Name: SEQUENCE product_status_prd_sts_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.product_status_prd_sts_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.product_status_prd_sts_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_status_prd_sts_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_status_prd_sts_id_seq TO mochi_app;


--
-- Name: TABLE product_status; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.product_status FROM PUBLIC;
REVOKE ALL ON TABLE mochi.product_status FROM mochidb_owner;
GRANT ALL ON TABLE mochi.product_status TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_status TO mochi_app;


--
-- Name: TABLE product_supplier; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.product_supplier FROM PUBLIC;
REVOKE ALL ON TABLE mochi.product_supplier FROM mochidb_owner;
GRANT ALL ON TABLE mochi.product_supplier TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_supplier TO mochi_app;


--
-- Name: SEQUENCE product_tag_prd_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.product_tag_prd_tag_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.product_tag_prd_tag_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_tag_prd_tag_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_tag_prd_tag_id_seq TO mochi_app;


--
-- Name: TABLE product_tag; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.product_tag FROM PUBLIC;
REVOKE ALL ON TABLE mochi.product_tag FROM mochidb_owner;
GRANT ALL ON TABLE mochi.product_tag TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_tag TO mochi_app;


--
-- Name: SEQUENCE product_tag_attr_lcl_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.product_tag_attr_lcl_tag_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.product_tag_attr_lcl_tag_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_tag_attr_lcl_tag_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_tag_attr_lcl_tag_id_seq TO mochi_app;


--
-- Name: SEQUENCE product_type_prd_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.product_type_prd_typ_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.product_type_prd_typ_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_type_prd_typ_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.product_type_prd_typ_id_seq TO mochi_app;


--
-- Name: TABLE product_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.product_type FROM PUBLIC;
REVOKE ALL ON TABLE mochi.product_type FROM mochidb_owner;
GRANT ALL ON TABLE mochi.product_type TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_type TO mochi_app;


--
-- Name: TABLE promotion; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.promotion FROM PUBLIC;
REVOKE ALL ON TABLE mochi.promotion FROM mochidb_owner;
GRANT ALL ON TABLE mochi.promotion TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion TO mochi_app;


--
-- Name: TABLE promotion_brand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.promotion_brand FROM PUBLIC;
REVOKE ALL ON TABLE mochi.promotion_brand FROM mochidb_owner;
GRANT ALL ON TABLE mochi.promotion_brand TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_brand TO mochi_app;


--
-- Name: SEQUENCE promotion_category_prm_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.promotion_category_prm_cat_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.promotion_category_prm_cat_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.promotion_category_prm_cat_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.promotion_category_prm_cat_id_seq TO mochi_app;


--
-- Name: TABLE promotion_category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.promotion_category FROM PUBLIC;
REVOKE ALL ON TABLE mochi.promotion_category FROM mochidb_owner;
GRANT ALL ON TABLE mochi.promotion_category TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_category TO mochi_app;


--
-- Name: TABLE promotion_order; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.promotion_order FROM PUBLIC;
REVOKE ALL ON TABLE mochi.promotion_order FROM mochidb_owner;
GRANT ALL ON TABLE mochi.promotion_order TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_order TO mochi_app;


--
-- Name: TABLE promotion_product; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.promotion_product FROM PUBLIC;
REVOKE ALL ON TABLE mochi.promotion_product FROM mochidb_owner;
GRANT ALL ON TABLE mochi.promotion_product TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_product TO mochi_app;


--
-- Name: TABLE promotion_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.promotion_type FROM PUBLIC;
REVOKE ALL ON TABLE mochi.promotion_type FROM mochidb_owner;
GRANT ALL ON TABLE mochi.promotion_type TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_type TO mochi_app;


--
-- Name: SEQUENCE role_rle_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.role_rle_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.role_rle_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.role_rle_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.role_rle_id_seq TO mochi_app;


--
-- Name: TABLE role; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.role FROM PUBLIC;
REVOKE ALL ON TABLE mochi.role FROM mochidb_owner;
GRANT ALL ON TABLE mochi.role TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.role TO mochi_app;
GRANT SELECT ON TABLE mochi.role TO security_app;


--
-- Name: TABLE role_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.role_type FROM PUBLIC;
REVOKE ALL ON TABLE mochi.role_type FROM mochidb_owner;
GRANT ALL ON TABLE mochi.role_type TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.role_type TO mochi_app;
GRANT SELECT ON TABLE mochi.role_type TO security_app;


--
-- Name: SEQUENCE role_type_rle_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.role_type_rle_typ_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.role_type_rle_typ_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.role_type_rle_typ_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.role_type_rle_typ_id_seq TO mochi_app;


--
-- Name: SEQUENCE role_type_role_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.role_type_role_typ_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.role_type_role_typ_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.role_type_role_typ_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.role_type_role_typ_id_seq TO mochi_app;


--
-- Name: TABLE supplier; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.supplier FROM PUBLIC;
REVOKE ALL ON TABLE mochi.supplier FROM mochidb_owner;
GRANT ALL ON TABLE mochi.supplier TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.supplier TO mochi_app;


--
-- Name: SEQUENCE tag_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.tag_tag_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.tag_tag_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.tag_tag_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.tag_tag_id_seq TO mochi_app;


--
-- Name: TABLE tag; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.tag FROM PUBLIC;
REVOKE ALL ON TABLE mochi.tag FROM mochidb_owner;
GRANT ALL ON TABLE mochi.tag TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.tag TO mochi_app;


--
-- Name: SEQUENCE tag_attr_lcl_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON SEQUENCE mochi.tag_attr_lcl_tag_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE mochi.tag_attr_lcl_tag_id_seq FROM mochidb_owner;
GRANT ALL ON SEQUENCE mochi.tag_attr_lcl_tag_id_seq TO mochidb_owner;
GRANT ALL ON SEQUENCE mochi.tag_attr_lcl_tag_id_seq TO mochi_app;


--
-- Name: TABLE tag_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.tag_attr_lcl FROM PUBLIC;
REVOKE ALL ON TABLE mochi.tag_attr_lcl FROM mochidb_owner;
GRANT ALL ON TABLE mochi.tag_attr_lcl TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.tag_attr_lcl TO mochi_app;


--
-- Name: TABLE vw_category_brand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.vw_category_brand FROM PUBLIC;
REVOKE ALL ON TABLE mochi.vw_category_brand FROM mochidb_owner;
GRANT ALL ON TABLE mochi.vw_category_brand TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.vw_category_brand TO mochi_app;


--
-- Name: TABLE vw_category_product; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

REVOKE ALL ON TABLE mochi.vw_category_product FROM PUBLIC;
REVOKE ALL ON TABLE mochi.vw_category_product FROM mochidb_owner;
GRANT ALL ON TABLE mochi.vw_category_product TO mochidb_owner;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.vw_category_product TO mochi_app;


--
-- PostgreSQL database dump complete
--

