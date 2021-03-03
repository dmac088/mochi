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

SET search_path = mochi, pg_catalog;

ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT tag_attr_lcl_tag_id_fkey;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT tag_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.supplier DROP CONSTRAINT supplier_role_id_fkey;
ALTER TABLE ONLY mochi.stock_on_hand DROP CONSTRAINT stock_on_hand_prd_id_fkey;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_role_typ_id_fkey;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_party_id_fkey;
ALTER TABLE ONLY mochi.promotion_attr_lcl DROP CONSTRAINT promotion_attr_lcl_prm_id_fkey;
ALTER TABLE ONLY mochi.promotion_attr_lcl DROP CONSTRAINT promotion_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT product_typ_id_product_type_typ_id_fkey;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT product_tag_tag_id_tag_tag_id_fkey;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT product_tag_prd_id_product_prd_id_fkey;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT product_sts_id_product_status_sts_id_fkey;
ALTER TABLE ONLY mochi.product_shipping DROP CONSTRAINT product_shipping_prd_id_fkey;
ALTER TABLE ONLY mochi.product_shipping DROP CONSTRAINT product_shipping_postage_type_shp_typ_id_fkey;
ALTER TABLE ONLY mochi.product_shipping DROP CONSTRAINT product_shipping_postage_destination_shp_dst_id_fkey;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT product_category_prd_id_product_prd_id_fkey;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT product_category_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.product_basic DROP CONSTRAINT product_basic_prd_id_fkey;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT product_attr_lcl_prd_id_fkey;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT product_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.price DROP CONSTRAINT price_product_id_fkey;
ALTER TABLE ONLY mochi.price DROP CONSTRAINT price_currency_id_fkey;
ALTER TABLE ONLY mochi.shipping_type_attr_lcl DROP CONSTRAINT postage_type_attr_lcl_pst_typ_id_fkey;
ALTER TABLE ONLY mochi.shipping_type_attr_lcl DROP CONSTRAINT postage_type_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.shipping_destination_attr_lcl DROP CONSTRAINT postage_destination_attr_lcl_pst_dst_id_fkey;
ALTER TABLE ONLY mochi.shipping_destination_attr_lcl DROP CONSTRAINT postage_destination_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.person DROP CONSTRAINT person_person_id_fkey;
ALTER TABLE ONLY mochi.party DROP CONSTRAINT party_pty_typ_id_fkey;
ALTER TABLE ONLY mochi.organisation DROP CONSTRAINT organisation_org_id_fkey;
ALTER TABLE ONLY mochi."order" DROP CONSTRAINT orders_party_id_fkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_product_id_fkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_order_id_fkey;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_inv_trx_typ_id_inventory_transaction_type;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_inv_pty_id_supplier_pty_id;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_inv_prd_id_product_prd_id;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_inv_loc_id_inventory_location_inv_loc_id;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_ccy_id_currency_ccy_id;
ALTER TABLE ONLY mochi.department_attr_lcl DROP CONSTRAINT department_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.department_attr_lcl DROP CONSTRAINT department_attr_lcl_dept_id_fkey;
ALTER TABLE ONLY mochi.customer DROP CONSTRAINT customer_role_id_fkey;
ALTER TABLE ONLY mochi.category_product DROP CONSTRAINT category_product_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.category_brand DROP CONSTRAINT category_brand_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT category_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT category_attr_lcl_cat_id_fkey;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT brand_category_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT brand_category_bnd_id_brand_bnd_id_fkey;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT brand_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT brand_attr_lcl_bnd_id_fkey;
ALTER TABLE ONLY mochi.bag DROP CONSTRAINT bag_pty_id_party_pty_id_fkey;
ALTER TABLE ONLY mochi.bag_item DROP CONSTRAINT bag_item_sts_id_bag_item_sts_bag_item_sts_id_fkey;
ALTER TABLE ONLY mochi.bag_item_disc DROP CONSTRAINT bag_item_disc_bag_item_id_bag_item_bag_item_id_fkey;
ALTER TABLE ONLY mochi.bag_item DROP CONSTRAINT bag_item_bag_id_bag_bag_id_fkey;
ALTER TABLE ONLY mochi.address DROP CONSTRAINT address_pty_id_party_pty_id_fkey;
ALTER TABLE ONLY mochi.address DROP CONSTRAINT address_addr_typ_id_address_type_addr_typ_id_fkey;
ALTER TABLE ONLY mochi.accessories_attr_lcl DROP CONSTRAINT accessories_attr_lcl_lcl_cd_fkey;
DROP INDEX mochi.role_role_typ_id_role_start_dttm_party_id_key;
DROP INDEX mochi.fki_promotion_attr_lcl_prm_id_fkey;
DROP INDEX mochi.fki_product_attr_lcl_prd_id_fkey;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT uc_tag_lcl;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT uc_tag_desc;
ALTER TABLE ONLY mochi.tag DROP CONSTRAINT uc_tag_cd;
ALTER TABLE ONLY mochi.shipping_type DROP CONSTRAINT uc_pst_typ_cd;
ALTER TABLE ONLY mochi.shipping_destination DROP CONSTRAINT uc_pst_dst_cd;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT uc_product_upc_cd;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT uc_product_tag;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT uc_product_category;
ALTER TABLE ONLY mochi.promotion_mechanic DROP CONSTRAINT uc_prm_mec_cd;
ALTER TABLE ONLY mochi.promotion_attr_lcl DROP CONSTRAINT uc_prm_lcl_1;
ALTER TABLE ONLY mochi.promotion DROP CONSTRAINT uc_prm_cd;
ALTER TABLE ONLY mochi.department DROP CONSTRAINT uc_prd_typ_cd;
ALTER TABLE ONLY mochi.product_status DROP CONSTRAINT uc_prd_sts_cd;
ALTER TABLE ONLY mochi.product_rating DROP CONSTRAINT uc_prd_rat;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT uc_prd_lcl_1;
ALTER TABLE ONLY mochi.stock_on_hand DROP CONSTRAINT uc_prd_id;
ALTER TABLE ONLY mochi.category_type DROP CONSTRAINT uc_cat_typ_cd;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT uc_cat_lcl;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT uc_cat_desc;
ALTER TABLE ONLY mochi.category DROP CONSTRAINT uc_cat_cd;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT uc_brand_category;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT uc_bnd_lcl;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT uc_bnd_desc_lcl_cd;
ALTER TABLE ONLY mochi.brand DROP CONSTRAINT uc_bnd_cd;
ALTER TABLE ONLY mochi.bag_item_status DROP CONSTRAINT uc_bag_sts_cd;
ALTER TABLE ONLY mochi.bag_item DROP CONSTRAINT uc_bag_item;
ALTER TABLE ONLY mochi.tag DROP CONSTRAINT tag_pkey;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT tag_attr_lcl_pkey;
ALTER TABLE ONLY mochi.supplier DROP CONSTRAINT supplier_pkey;
ALTER TABLE ONLY mochi.stock_on_hand DROP CONSTRAINT stock_on_hand_pkey;
ALTER TABLE ONLY mochi.role_type DROP CONSTRAINT role_type_rle_typ_desc_key;
ALTER TABLE ONLY mochi.role_type DROP CONSTRAINT role_type_pkey;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_pty_id_key;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_pkey;
ALTER TABLE ONLY mochi.rating DROP CONSTRAINT rating_pkey;
ALTER TABLE ONLY mochi.shipping_type_attr_lcl DROP CONSTRAINT pst_typ_id_lcl_cd;
ALTER TABLE ONLY mochi.shipping_destination_attr_lcl DROP CONSTRAINT pst_dst_id_lcl_cd;
ALTER TABLE ONLY mochi.promotion DROP CONSTRAINT promotion_pkey;
ALTER TABLE ONLY mochi.promotion_mechanic DROP CONSTRAINT promotion_mechanic_pkey;
ALTER TABLE ONLY mochi.promotion_attr_lcl DROP CONSTRAINT promotion_attr_lcl_pkey;
ALTER TABLE ONLY mochi.department DROP CONSTRAINT product_type_pkey;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT product_tag_pkey;
ALTER TABLE ONLY mochi.product_status DROP CONSTRAINT product_status_pkey;
ALTER TABLE ONLY mochi.product_shipping DROP CONSTRAINT product_shipping_pkey;
ALTER TABLE ONLY mochi.product_rating DROP CONSTRAINT product_rating_pkey;
ALTER TABLE ONLY mochi.product_promotion DROP CONSTRAINT product_promotion_pkey;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT product_pkey;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT product_category_pkey;
ALTER TABLE ONLY mochi.product_basic DROP CONSTRAINT product_basic_pkey;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT product_attr_lcl_pkey;
ALTER TABLE ONLY mochi.price_type DROP CONSTRAINT price_type_pkey;
ALTER TABLE ONLY mochi.price DROP CONSTRAINT price_pkey;
ALTER TABLE ONLY mochi.department_attr_lcl DROP CONSTRAINT prd_id_lcl_cd_3;
ALTER TABLE ONLY mochi.accessories_attr_lcl DROP CONSTRAINT prd_id_lcl_cd_1;
ALTER TABLE ONLY mochi.shipping_type DROP CONSTRAINT postage_type_pkey;
ALTER TABLE ONLY mochi.shipping_type_attr_lcl DROP CONSTRAINT postage_type_attr_lcl_pkey;
ALTER TABLE ONLY mochi.shipping_destination DROP CONSTRAINT postage_destination_pkey;
ALTER TABLE ONLY mochi.shipping_destination_attr_lcl DROP CONSTRAINT postage_destination_attr_lcl_pkey;
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
ALTER TABLE ONLY mochi.inventory_transaction_type DROP CONSTRAINT inventory_transaction_type_pkey;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_pkey;
ALTER TABLE ONLY mochi.inventory_location DROP CONSTRAINT inventory_location_pkey;
ALTER TABLE ONLY mochi.discount_type DROP CONSTRAINT discount_type_pkey;
ALTER TABLE ONLY mochi.discount DROP CONSTRAINT discount_pkey;
ALTER TABLE ONLY mochi.department_attr_lcl DROP CONSTRAINT department_attr_lcl_pkey;
ALTER TABLE ONLY mochi.customer DROP CONSTRAINT customer_pkey;
ALTER TABLE ONLY mochi.customer DROP CONSTRAINT customer_cst_id_key;
ALTER TABLE ONLY mochi.currency DROP CONSTRAINT currency_pkey;
ALTER TABLE ONLY mochi.category_type DROP CONSTRAINT category_type_pkey;
ALTER TABLE ONLY mochi.category_promotion DROP CONSTRAINT category_promotion_pkey;
ALTER TABLE ONLY mochi.category_product DROP CONSTRAINT category_product_pkey;
ALTER TABLE ONLY mochi.category DROP CONSTRAINT category_pkey;
ALTER TABLE ONLY mochi.category_brand DROP CONSTRAINT category_brand_pkey;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT category_attr_lcl_pkey;
ALTER TABLE ONLY mochi.brand_promotion DROP CONSTRAINT brand_promotion_pkey;
ALTER TABLE ONLY mochi.brand DROP CONSTRAINT brand_pkey;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT brand_category_pkey;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT brand_attr_lcl_pkey;
ALTER TABLE ONLY mochi.bag_item_status DROP CONSTRAINT bag_status_pkey;
ALTER TABLE ONLY mochi.bag DROP CONSTRAINT bag_pkey;
ALTER TABLE ONLY mochi.bag_item DROP CONSTRAINT bag_item_pkey;
ALTER TABLE ONLY mochi.bag_item_disc DROP CONSTRAINT bag_item_disc_pkey;
ALTER TABLE ONLY mochi.address_type DROP CONSTRAINT address_type_pkey;
ALTER TABLE ONLY mochi.address DROP CONSTRAINT address_pkey;
ALTER TABLE ONLY mochi.accessories_attr_lcl DROP CONSTRAINT accessories_attr_lcl_pkey;
ALTER TABLE mochi.role_type ALTER COLUMN rle_typ_id DROP DEFAULT;
ALTER TABLE mochi.party_type ALTER COLUMN pty_typ_id DROP DEFAULT;
ALTER TABLE mochi.party ALTER COLUMN pty_id DROP DEFAULT;
DROP TABLE mochi.tag_attr_lcl;
DROP SEQUENCE mochi.tag_attr_lcl_tag_id_seq;
DROP TABLE mochi.tag;
DROP SEQUENCE mochi.tag_tag_id_seq;
DROP TABLE mochi.supplier;
DROP SEQUENCE mochi.supplier_sup_num_seq;
DROP TABLE mochi.stock_on_hand;
DROP TABLE mochi.shipping_type_attr_lcl;
DROP TABLE mochi.shipping_type;
DROP TABLE mochi.shipping_destination_attr_lcl;
DROP TABLE mochi.shipping_destination;
DROP SEQUENCE mochi.role_type_role_typ_id_seq;
DROP SEQUENCE mochi.role_type_rle_typ_id_seq;
DROP TABLE mochi.role_type;
DROP TABLE mochi.role;
DROP SEQUENCE mochi.role_rle_id_seq;
DROP TABLE mochi.rating;
DROP TABLE mochi.promotion_mechanic;
DROP SEQUENCE mochi.promotion_category_prm_cat_id_seq;
DROP TABLE mochi.promotion_attr_lcl;
DROP TABLE mochi.promotion;
DROP SEQUENCE mochi.product_tag_attr_lcl_tag_id_seq;
DROP TABLE mochi.product_tag;
DROP SEQUENCE mochi.product_tag_prd_tag_id_seq;
DROP TABLE mochi.product_supplier;
DROP TABLE mochi.product_status;
DROP SEQUENCE mochi.product_status_prd_sts_id_seq;
DROP TABLE mochi.product_shipping;
DROP TABLE mochi.product_rating;
DROP SEQUENCE mochi.product_rating_prd_rat_id_seq;
DROP TABLE mochi.product_promotion;
DROP TABLE mochi.product_category;
DROP SEQUENCE mochi.product_category_prd_cat_id_seq;
DROP TABLE mochi.product_basic;
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
DROP TABLE mochi.locale;
DROP SEQUENCE mochi.layout_lay_id_seq;
DROP SEQUENCE mochi.layout_category_lay_cat_id_seq;
DROP TABLE mochi.inventory_transaction_type;
DROP TABLE mochi.inventory_transaction;
DROP SEQUENCE mochi.inventory_transaction_inv_trx_id_seq;
DROP TABLE mochi.inventory_location;
DROP SEQUENCE mochi.hierarchy_hir_id_seq;
DROP SEQUENCE mochi.hibernate_sequence;
DROP TABLE mochi.discount_type;
DROP TABLE mochi.discount;
DROP TABLE mochi.department_attr_lcl;
DROP TABLE mochi.department;
DROP SEQUENCE mochi.product_type_prd_typ_id_seq;
DROP TABLE mochi.customer;
DROP SEQUENCE mochi.customer_cst_num_seq;
DROP TABLE mochi.currency;
DROP TABLE mochi.category_type;
DROP SEQUENCE mochi.category_type_cat_typ_id_seq;
DROP TABLE mochi.category_promotion;
DROP TABLE mochi.category_product;
DROP TABLE mochi.category_brand;
DROP TABLE mochi.category_attr_lcl;
DROP SEQUENCE mochi.category_attr_lcl_cat_id_seq;
DROP TABLE mochi.category;
DROP SEQUENCE mochi.category_cat_id_seq;
DROP TABLE mochi.brand_promotion;
DROP TABLE mochi.brand_category;
DROP SEQUENCE mochi.brand_category_bnd_cat_id_seq;
DROP TABLE mochi.brand_attr_lcl;
DROP SEQUENCE mochi.brand_attr_lcl_bnd_id_seq;
DROP TABLE mochi.brand;
DROP SEQUENCE mochi.brand_bnd_id_seq;
DROP TABLE mochi.bag_item_status;
DROP SEQUENCE mochi.bag_status_bag_sts_id_seq;
DROP TABLE mochi.bag_item_disc;
DROP TABLE mochi.bag_item;
DROP TABLE mochi.bag;
DROP SEQUENCE mochi.bag_bag_id_seq;
DROP TABLE mochi.address_type;
DROP TABLE mochi.address;
DROP TABLE mochi.accessories_attr_lcl;
DROP FUNCTION mochi.load_shipping_products();
DROP FUNCTION mochi.load_postage_type();
DROP FUNCTION mochi.load_postage_destination();
DROP FUNCTION mochi.ft_product_categories(text, text);
DROP FUNCTION mochi.ft_categories(text);
DROP FUNCTION mochi.ft_brand_categories(text, text);
DROP SCHEMA mochi;
--
-- Name: mochi; Type: SCHEMA; Schema: -; Owner: mochidb_owner
--

CREATE SCHEMA mochi;


ALTER SCHEMA mochi OWNER TO mochidb_owner;

SET search_path = mochi, pg_catalog;

--
-- Name: ft_brand_categories(text, text); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION ft_brand_categories(text, text) RETURNS TABLE(cat_id bigint, cat_cd text, cat_lvl bigint, hir_id bigint, cat_prnt_id bigint, cat_typ_id bigint, product_count bigint, child_cat_count bigint, max_price numeric)
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

CREATE FUNCTION ft_categories(text) RETURNS TABLE(cat_id bigint, cat_cd text, cat_prnt_id bigint, cat_typ_id bigint)
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

CREATE FUNCTION ft_product_categories(text, text) RETURNS TABLE(cat_id bigint, cat_cd text, cat_lvl bigint, hir_id bigint, cat_prnt_id bigint, cat_typ_id bigint, product_count bigint, child_cat_count bigint, max_price numeric)
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

--
-- Name: load_postage_destination(); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION load_postage_destination() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	ALTER TABLE mochi.postage_destination_attr_lcl
    DROP CONSTRAINT IF EXISTS postage_destination_attr_lcl_pst_dst_id_fkey;

	ALTER TABLE mochi.product_shipping
    DROP CONSTRAINT IF EXISTS  product_shipping_postage_destination_pst_dst_id_fkey;

	TRUNCATE TABLE mochi.postage_destination_attr_lcl;

	TRUNCATE TABLE mochi.postage_destination;

	INSERT INTO mochi.postage_destination(pst_dst_id, pst_dst_cd, pst_zne_cd)
	SELECT pst_dst_id, destinationcode, zonecode
	FROM mochi.vw_postage_destination;
	
	INSERT INTO mochi.postage_destination_attr_lcl(pst_dst_lcl_id, pst_dst_id, pst_dst_desc, lcl_cd)
	SELECT pst_dst_lcl_id, pst_dst_id, pst_dst_desc, lcl_cd
	FROM mochi.vw_postage_destination_attr_lcl;

	ALTER TABLE mochi.product_shipping
    ADD CONSTRAINT product_shipping_postage_destination_pst_dst_id_fkey FOREIGN KEY (pst_dst_id)
    REFERENCES mochi.postage_destination (pst_dst_id) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT;
	
	ALTER TABLE mochi.postage_destination_attr_lcl
    ADD CONSTRAINT postage_destination_attr_lcl_pst_dst_id_fkey FOREIGN KEY (pst_dst_id)
    REFERENCES mochi.postage_destination (pst_dst_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

	RETURN 1;
end;
';


ALTER FUNCTION mochi.load_postage_destination() OWNER TO mochidb_owner;

--
-- Name: load_postage_type(); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION load_postage_type() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin
	ALTER TABLE mochi.postage_type_attr_lcl
    DROP CONSTRAINT IF EXISTS postage_type_attr_lcl_pst_typ_id_fkey;

	ALTER TABLE mochi.product_shipping
    DROP CONSTRAINT IF EXISTS  product_shipping_postage_type_pst_typ_id_fkey;

	TRUNCATE TABLE mochi.postage_type_attr_lcl;

	TRUNCATE TABLE mochi.postage_type;

	INSERT INTO mochi.postage_type(pst_typ_id, pst_typ_cd)
	SELECT pst_typ_id, pst_typ_cd
	FROM mochi.vw_postage_type;
	
	INSERT INTO mochi.postage_type_attr_lcl(pst_typ_lcl_id, pst_typ_id, pst_typ_desc, lcl_cd)
	SELECT pst_typ_lcl_id, pst_typ_id, pst_typ_desc, lcl_cd
	FROM mochi.vw_postage_type_attr_lcl;

	ALTER TABLE mochi.product_shipping
    ADD CONSTRAINT product_shipping_postage_type_pst_typ_id_fkey FOREIGN KEY (pst_typ_id)
    REFERENCES mochi.postage_type (pst_typ_id) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT;
	
	ALTER TABLE mochi.postage_type_attr_lcl
    ADD CONSTRAINT postage_type_attr_lcl_pst_typ_id_fkey FOREIGN KEY (pst_typ_id)
    REFERENCES mochi.postage_type (pst_typ_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

	RETURN 1;
end;
';


ALTER FUNCTION mochi.load_postage_type() OWNER TO mochidb_owner;

--
-- Name: load_shipping_products(); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION load_shipping_products() RETURNS integer
    LANGUAGE plpgsql
    AS '
declare 
record_count integer = 0;
begin

INSERT INTO mochi.product(prd_id, upc_cd, prd_crtd_dt, bnd_id, dept_id, prd_sts_id)
SELECT 	
		nextval(''mochi."hibernate_sequence"''),
		_product_upc_code, 
		to_date(_product_created_date, ''YYYY-MM-DD HH:MM:SS'') as _product_created_date,
		42 as bnd_id,
		1 dept_id,
		1 prd_sts_id
FROM yahoo.vw_export_shipping_master m;

insert into mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, lcl_cd, prd_lng_desc)
select nextval(''mochi."hibernate_sequence"''),
		p.prd_id,
		trim(m._product_description_en),
		null,
		''en-GB'',
		trim(m._product_description_en)
from mochi.product p
	left join yahoo.vw_export_shipping_master m
		on p.upc_cd = m._product_upc_code
where p.dept_id = 1;



insert into mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, lcl_cd, prd_lng_desc)
select nextval(''mochi."hibernate_sequence"''),
		p.prd_id,
		trim(m._product_description_hk),
		null,
		''zh-HK'',
		trim(m._product_description_hk)
from mochi.product p
	left join yahoo.vw_export_shipping_master m
		on p.upc_cd = m._product_upc_code
where p.dept_id = 1;

insert into mochi.product_shipping (prd_id, shp_dst_id, shp_typ_id, shp_wgt_lim, shp_wgt_frm, shp_wgt_to, shp_trk_lvl)
select prd_id,
	   sd.shp_dst_id,
	   st.shp_typ_id,
	   m._weight_limit, 
	   m._weight_from,
	   m._weight_to, 
	   m._tracking_level::smallint
from mochi.product p
	left join yahoo.vw_export_shipping_master m
		on p.upc_cd = m._product_upc_code
		
	left join mochi.shipping_destination sd
		on m._destination_code = sd.shp_dst_cd
		
	left join mochi.shipping_type st 
		on m._service_type_code = st.shp_typ_cd
where p.dept_id = 1;

	RETURN 1;
end;
';


ALTER FUNCTION mochi.load_shipping_products() OWNER TO mochidb_owner;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: accessories_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE accessories_attr_lcl (
    prd_lcl_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    primary_metal_type character varying(100),
    primary_stone_type character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE accessories_attr_lcl OWNER TO mochidb_owner;

--
-- Name: address; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE address (
    addr_id bigint NOT NULL,
    addr_ln_1 character varying(100),
    addr_ln_2 character varying(100),
    addr_ln_3 character varying(100),
    addr_cnty character varying(100) NOT NULL,
    addr_pst_cd character varying(10),
    addr_typ_id bigint NOT NULL,
    pty_id bigint
);


ALTER TABLE address OWNER TO mochidb_owner;

--
-- Name: address_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE address_type (
    addr_typ_id bigint NOT NULL,
    addr_typ_cd character varying(10) NOT NULL,
    addr_typ_desc character varying(20) NOT NULL
);


ALTER TABLE address_type OWNER TO mochidb_owner;

--
-- Name: bag_bag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE bag_bag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bag_bag_id_seq OWNER TO mochidb_owner;

--
-- Name: bag; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE bag (
    bag_id bigint DEFAULT nextval('bag_bag_id_seq'::regclass) NOT NULL,
    pty_id bigint NOT NULL,
    bag_crd_dt timestamp with time zone NOT NULL,
    bag_upd_dt timestamp with time zone NOT NULL
);


ALTER TABLE bag OWNER TO mochidb_owner;

--
-- Name: bag_item; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE bag_item (
    bag_item_id bigint NOT NULL,
    bag_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    qty smallint NOT NULL,
    bag_item_sts_id bigint NOT NULL
);


ALTER TABLE bag_item OWNER TO mochidb_owner;

--
-- Name: bag_item_disc; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE bag_item_disc (
    bag_item_disc_id bigint NOT NULL,
    bag_item_id bigint NOT NULL,
    disc_amt numeric NOT NULL
);


ALTER TABLE bag_item_disc OWNER TO mochidb_owner;

--
-- Name: bag_status_bag_sts_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE bag_status_bag_sts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bag_status_bag_sts_id_seq OWNER TO mochidb_owner;

--
-- Name: bag_item_status; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE bag_item_status (
    bag_item_sts_id bigint DEFAULT nextval('bag_status_bag_sts_id_seq'::regclass) NOT NULL,
    bag_item_sts_cd character varying(5) NOT NULL,
    bag_item_sts_desc character varying(20)
);


ALTER TABLE bag_item_status OWNER TO mochidb_owner;

--
-- Name: brand_bnd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE brand_bnd_id_seq
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brand_bnd_id_seq OWNER TO mochidb_owner;

--
-- Name: brand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE brand (
    bnd_id bigint DEFAULT nextval('brand_bnd_id_seq'::regclass) NOT NULL,
    bnd_cd character(5)
);


ALTER TABLE brand OWNER TO mochidb_owner;

--
-- Name: brand_attr_lcl_bnd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE brand_attr_lcl_bnd_id_seq
    START WITH 19
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brand_attr_lcl_bnd_id_seq OWNER TO mochidb_owner;

--
-- Name: brand_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE brand_attr_lcl (
    bnd_lcl_id bigint DEFAULT nextval('brand_attr_lcl_bnd_id_seq'::regclass) NOT NULL,
    bnd_id bigint NOT NULL,
    bnd_desc character varying(100),
    bnd_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE brand_attr_lcl OWNER TO mochidb_owner;

--
-- Name: brand_category_bnd_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE brand_category_bnd_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brand_category_bnd_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: brand_category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE brand_category (
    bnd_cat_id bigint DEFAULT nextval('brand_category_bnd_cat_id_seq'::regclass) NOT NULL,
    bnd_id bigint,
    cat_id bigint
);


ALTER TABLE brand_category OWNER TO mochidb_owner;

--
-- Name: brand_promotion; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE brand_promotion (
    bnd_id bigint NOT NULL,
    prm_id bigint NOT NULL
);


ALTER TABLE brand_promotion OWNER TO mochidb_owner;

--
-- Name: category_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE category_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE category (
    cat_id bigint DEFAULT nextval('category_cat_id_seq'::regclass) NOT NULL,
    cat_cd character(5) NOT NULL,
    cat_typ_id bigint,
    cat_lvl bigint,
    cat_prnt_cd character(5),
    cat_prnt_id bigint
);


ALTER TABLE category OWNER TO mochidb_owner;

--
-- Name: category_attr_lcl_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE category_attr_lcl_cat_id_seq
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_attr_lcl_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: category_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE category_attr_lcl (
    cat_lcl_id bigint DEFAULT nextval('category_attr_lcl_cat_id_seq'::regclass) NOT NULL,
    cat_id bigint NOT NULL,
    cat_desc character varying(100),
    cat_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE category_attr_lcl OWNER TO mochidb_owner;

--
-- Name: category_brand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE category_brand (
    cat_id bigint DEFAULT nextval('category_cat_id_seq'::regclass) NOT NULL
);


ALTER TABLE category_brand OWNER TO mochidb_owner;

--
-- Name: category_product; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE category_product (
    cat_id bigint DEFAULT nextval('category_cat_id_seq'::regclass) NOT NULL
);


ALTER TABLE category_product OWNER TO mochidb_owner;

--
-- Name: category_promotion; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE category_promotion (
    cat_id bigint NOT NULL,
    prm_id bigint NOT NULL
);


ALTER TABLE category_promotion OWNER TO mochidb_owner;

--
-- Name: category_type_cat_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE category_type_cat_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_type_cat_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: category_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE category_type (
    cat_typ_id bigint DEFAULT nextval('category_type_cat_typ_id_seq'::regclass) NOT NULL,
    cat_typ_cd character varying(5) NOT NULL,
    cat_typ_desc character varying(20)
);


ALTER TABLE category_type OWNER TO mochidb_owner;

--
-- Name: currency; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE currency (
    ccy_id bigint NOT NULL,
    ccy_cd character(3)
);


ALTER TABLE currency OWNER TO mochidb_owner;

--
-- Name: customer_cst_num_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE customer_cst_num_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customer_cst_num_seq OWNER TO mochidb_owner;

--
-- Name: customer; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE customer (
    rle_id bigint NOT NULL,
    cst_num character(10) DEFAULT nextval('customer_cst_num_seq'::regclass) NOT NULL
);


ALTER TABLE customer OWNER TO mochidb_owner;

--
-- Name: product_type_prd_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE product_type_prd_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_type_prd_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: department; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE department (
    dept_id bigint DEFAULT nextval('product_type_prd_typ_id_seq'::regclass) NOT NULL,
    dept_cd character varying(5) NOT NULL,
    dept_class character varying(20)
);


ALTER TABLE department OWNER TO mochidb_owner;

--
-- Name: department_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE department_attr_lcl (
    dept_lcl_id bigint NOT NULL,
    dept_id bigint NOT NULL,
    dept_desc character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE department_attr_lcl OWNER TO mochidb_owner;

--
-- Name: discount; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE discount (
    dis_id bigint NOT NULL,
    dis_cd character(5),
    prm_id bigint,
    dis_typ_id bigint NOT NULL,
    dis_val numeric
);


ALTER TABLE discount OWNER TO mochidb_owner;

--
-- Name: discount_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE discount_type (
    dis_typ_id bigint NOT NULL,
    dis_typ_desc character varying NOT NULL
);


ALTER TABLE discount_type OWNER TO mochidb_owner;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE hibernate_sequence
    START WITH 232134
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO mochidb_owner;

--
-- Name: hierarchy_hir_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE hierarchy_hir_id_seq
    START WITH 41
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hierarchy_hir_id_seq OWNER TO mochidb_owner;

--
-- Name: inventory_location; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE inventory_location (
    inv_loc_id bigint NOT NULL,
    inv_loc_cd character(5) NOT NULL,
    inv_loc_desc character varying(50) NOT NULL,
    inv_loc_act boolean NOT NULL
);


ALTER TABLE inventory_location OWNER TO mochidb_owner;

--
-- Name: inventory_transaction_inv_trx_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE inventory_transaction_inv_trx_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE inventory_transaction_inv_trx_id_seq OWNER TO mochidb_owner;

--
-- Name: inventory_transaction; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE inventory_transaction (
    inv_trx_id bigint DEFAULT nextval('inventory_transaction_inv_trx_id_seq'::regclass) NOT NULL,
    inv_loc_id bigint NOT NULL,
    inv_prd_id bigint NOT NULL,
    inv_qty bigint NOT NULL,
    inv_prc numeric NOT NULL,
    inv_ccy_id bigint NOT NULL,
    inv_trx_typ_id bigint NOT NULL,
    inv_pty_id bigint NOT NULL,
    inv_trx_dt timestamp(4) with time zone NOT NULL
);


ALTER TABLE inventory_transaction OWNER TO mochidb_owner;

--
-- Name: inventory_transaction_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE inventory_transaction_type (
    inv_trx_typ_id bigint NOT NULL,
    inv_trx_typ_cd character varying(10) NOT NULL
);


ALTER TABLE inventory_transaction_type OWNER TO mochidb_owner;

--
-- Name: layout_category_lay_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE layout_category_lay_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE layout_category_lay_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: layout_lay_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE layout_lay_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE layout_lay_id_seq OWNER TO mochidb_owner;

--
-- Name: locale; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE locale (
    lcl_cd character varying(20) NOT NULL
);


ALTER TABLE locale OWNER TO mochidb_owner;

--
-- Name: order; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE "order" (
    ord_id bigint NOT NULL,
    pty_id bigint NOT NULL
);


ALTER TABLE "order" OWNER TO mochidb_owner;

--
-- Name: order_line; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE order_line (
    ord_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    ord_lne_no bigint NOT NULL,
    qty smallint NOT NULL,
    prm_id bigint NOT NULL,
    amt money NOT NULL
);


ALTER TABLE order_line OWNER TO mochidb_owner;

--
-- Name: organisation; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE organisation (
    pty_id bigint NOT NULL,
    org_nme character varying(100) NOT NULL,
    org_reg_no character varying(50) NOT NULL
);


ALTER TABLE organisation OWNER TO mochidb_owner;

--
-- Name: party; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE party (
    pty_id bigint NOT NULL,
    pty_typ_id bigint NOT NULL
);


ALTER TABLE party OWNER TO mochidb_owner;

--
-- Name: party_party_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE party_party_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE party_party_id_seq OWNER TO mochidb_owner;

--
-- Name: party_pty_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE party_pty_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE party_pty_id_seq OWNER TO mochidb_owner;

--
-- Name: party_pty_id_seq; Type: SEQUENCE OWNED BY; Schema: mochi; Owner: mochidb_owner
--

ALTER SEQUENCE party_pty_id_seq OWNED BY party.pty_id;


--
-- Name: party_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE party_type (
    pty_typ_id bigint NOT NULL,
    pty_typ_desc character varying NOT NULL
);


ALTER TABLE party_type OWNER TO mochidb_owner;

--
-- Name: party_type_pty_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE party_type_pty_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE party_type_pty_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: party_type_pty_typ_id_seq; Type: SEQUENCE OWNED BY; Schema: mochi; Owner: mochidb_owner
--

ALTER SEQUENCE party_type_pty_typ_id_seq OWNED BY party_type.pty_typ_id;


--
-- Name: person; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE person (
    pty_id bigint NOT NULL,
    psn_gvn_nm character varying NOT NULL,
    psn_fml_nm character varying NOT NULL,
    enb boolean NOT NULL
);


ALTER TABLE person OWNER TO mochidb_owner;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE person_id_seq
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_id_seq OWNER TO mochidb_owner;

--
-- Name: price_prc_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE price_prc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE price_prc_id_seq OWNER TO mochidb_owner;

--
-- Name: price; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE price (
    prc_id bigint DEFAULT nextval('price_prc_id_seq'::regclass) NOT NULL,
    prc_typ_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    prc_val numeric NOT NULL,
    ccy_id bigint NOT NULL
);


ALTER TABLE price OWNER TO mochidb_owner;

--
-- Name: price_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE price_type (
    prc_typ_id bigint NOT NULL,
    prc_typ_desc character varying,
    prc_typ_cd character varying
);


ALTER TABLE price_type OWNER TO mochidb_owner;

--
-- Name: product; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product (
    prd_id bigint NOT NULL,
    upc_cd character varying(30) NOT NULL,
    prd_crtd_dt timestamp(4) with time zone NOT NULL,
    bnd_id bigint NOT NULL,
    dept_id bigint NOT NULL,
    prd_sts_id bigint NOT NULL
);


ALTER TABLE product OWNER TO mochidb_owner;

--
-- Name: product_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product_attr_lcl (
    prd_lcl_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    prd_desc character varying(200),
    prd_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL,
    prd_lng_desc character varying(500)
);


ALTER TABLE product_attr_lcl OWNER TO mochidb_owner;

--
-- Name: product_basic; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product_basic (
    prd_id bigint NOT NULL,
    width bigint,
    height bigint,
    length bigint,
    weight bigint
);


ALTER TABLE product_basic OWNER TO mochidb_owner;

--
-- Name: product_category_prd_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE product_category_prd_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_category_prd_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: product_category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product_category (
    prd_cat_id bigint DEFAULT nextval('product_category_prd_cat_id_seq'::regclass) NOT NULL,
    prd_id bigint,
    cat_id bigint
);


ALTER TABLE product_category OWNER TO mochidb_owner;

--
-- Name: product_promotion; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product_promotion (
    prd_id bigint NOT NULL,
    prm_id bigint NOT NULL
);


ALTER TABLE product_promotion OWNER TO mochidb_owner;

--
-- Name: product_rating_prd_rat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE product_rating_prd_rat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_rating_prd_rat_id_seq OWNER TO mochidb_owner;

--
-- Name: product_rating; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product_rating (
    prd_rat_id bigint DEFAULT nextval('product_rating_prd_rat_id_seq'::regclass) NOT NULL,
    prd_id bigint NOT NULL,
    rat_id bigint NOT NULL,
    pty_id bigint NOT NULL,
    prd_rat_dt timestamp with time zone NOT NULL
);


ALTER TABLE product_rating OWNER TO mochidb_owner;

--
-- Name: product_shipping; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product_shipping (
    prd_id bigint NOT NULL,
    shp_dst_id bigint NOT NULL,
    shp_typ_id bigint NOT NULL,
    shp_wgt_lim numeric,
    shp_wgt_frm numeric NOT NULL,
    shp_wgt_to numeric NOT NULL,
    shp_trk_lvl smallint NOT NULL
);


ALTER TABLE product_shipping OWNER TO mochidb_owner;

--
-- Name: product_status_prd_sts_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE product_status_prd_sts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_status_prd_sts_id_seq OWNER TO mochidb_owner;

--
-- Name: product_status; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product_status (
    prd_sts_id bigint DEFAULT nextval('product_status_prd_sts_id_seq'::regclass) NOT NULL,
    prd_sts_cd character varying(5) NOT NULL,
    prd_sts_desc character varying(20)
);


ALTER TABLE product_status OWNER TO mochidb_owner;

--
-- Name: product_supplier; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product_supplier (
);


ALTER TABLE product_supplier OWNER TO mochidb_owner;

--
-- Name: product_tag_prd_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE product_tag_prd_tag_id_seq
    START WITH 25
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_tag_prd_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: product_tag; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE product_tag (
    prd_tag_id bigint DEFAULT nextval('product_tag_prd_tag_id_seq'::regclass) NOT NULL,
    prd_id bigint,
    tag_id bigint
);


ALTER TABLE product_tag OWNER TO mochidb_owner;

--
-- Name: product_tag_attr_lcl_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE product_tag_attr_lcl_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_tag_attr_lcl_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: promotion; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE promotion (
    prm_id bigint NOT NULL,
    prm_cd character(5) NOT NULL,
    prm_st_dt timestamp(4) with time zone NOT NULL,
    prm_en_dt timestamp(4) with time zone NOT NULL,
    prm_mec_id bigint NOT NULL,
    prm_act boolean NOT NULL
);


ALTER TABLE promotion OWNER TO mochidb_owner;

--
-- Name: promotion_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE promotion_attr_lcl (
    prm_lcl_id bigint NOT NULL,
    prm_id bigint NOT NULL,
    prm_desc character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE promotion_attr_lcl OWNER TO mochidb_owner;

--
-- Name: promotion_category_prm_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE promotion_category_prm_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE promotion_category_prm_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: promotion_mechanic; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE promotion_mechanic (
    prm_mec_id bigint NOT NULL,
    prm_mec_cd character varying(10) NOT NULL,
    prm_mec_desc character varying
);


ALTER TABLE promotion_mechanic OWNER TO mochidb_owner;

--
-- Name: rating; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE rating (
    rat_id bigint NOT NULL,
    rat_desc character varying(50),
    rat_val smallint
);


ALTER TABLE rating OWNER TO mochidb_owner;

--
-- Name: role_rle_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE role_rle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_rle_id_seq OWNER TO mochidb_owner;

--
-- Name: role; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE role (
    rle_id bigint DEFAULT nextval('role_rle_id_seq'::regclass) NOT NULL,
    rle_typ_id bigint NOT NULL,
    rle_start_dt timestamp(4) with time zone DEFAULT now() NOT NULL,
    pty_id bigint NOT NULL
);


ALTER TABLE role OWNER TO mochidb_owner;

--
-- Name: role_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE role_type (
    rle_typ_id bigint NOT NULL,
    rle_typ_desc character varying NOT NULL
);


ALTER TABLE role_type OWNER TO mochidb_owner;

--
-- Name: role_type_rle_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE role_type_rle_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_type_rle_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: role_type_rle_typ_id_seq; Type: SEQUENCE OWNED BY; Schema: mochi; Owner: mochidb_owner
--

ALTER SEQUENCE role_type_rle_typ_id_seq OWNED BY role_type.rle_typ_id;


--
-- Name: role_type_role_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE role_type_role_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_type_role_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: shipping_destination; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE shipping_destination (
    shp_dst_id bigint NOT NULL,
    shp_dst_cd character varying(10) NOT NULL,
    shp_zne_cd character(2) NOT NULL,
    shp_dst_desc character varying(100) NOT NULL,
    shp_dst_sht_cd character(2) NOT NULL
);


ALTER TABLE shipping_destination OWNER TO mochidb_owner;

--
-- Name: shipping_destination_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE shipping_destination_attr_lcl (
    shp_dst_lcl_id bigint NOT NULL,
    shp_dst_id bigint NOT NULL,
    shp_dst_desc character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE shipping_destination_attr_lcl OWNER TO mochidb_owner;

--
-- Name: shipping_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE shipping_type (
    shp_typ_id bigint NOT NULL,
    shp_typ_cd character varying(9) NOT NULL
);


ALTER TABLE shipping_type OWNER TO mochidb_owner;

--
-- Name: shipping_type_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE shipping_type_attr_lcl (
    shp_typ_lcl_id bigint NOT NULL,
    shp_typ_id bigint NOT NULL,
    shp_typ_desc character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE shipping_type_attr_lcl OWNER TO mochidb_owner;

--
-- Name: stock_on_hand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE stock_on_hand (
    soh_id bigint NOT NULL,
    soh_prd_id bigint NOT NULL,
    soh_qty bigint NOT NULL
);


ALTER TABLE stock_on_hand OWNER TO mochidb_owner;

--
-- Name: supplier_sup_num_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE supplier_sup_num_seq
    START WITH 1000000001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE supplier_sup_num_seq OWNER TO mochidb_owner;

--
-- Name: supplier; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE supplier (
    rle_id bigint NOT NULL,
    sup_num character(10) DEFAULT nextval('supplier_sup_num_seq'::regclass) NOT NULL
);


ALTER TABLE supplier OWNER TO mochidb_owner;

--
-- Name: tag_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE tag_tag_id_seq
    START WITH 15
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tag_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: tag; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE tag (
    tag_id bigint DEFAULT nextval('tag_tag_id_seq'::regclass) NOT NULL,
    tag_cd character(5) NOT NULL
);


ALTER TABLE tag OWNER TO mochidb_owner;

--
-- Name: tag_attr_lcl_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE tag_attr_lcl_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tag_attr_lcl_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: tag_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE tag_attr_lcl (
    tag_lcl_id bigint DEFAULT nextval('tag_attr_lcl_tag_id_seq'::regclass) NOT NULL,
    tag_id bigint NOT NULL,
    tag_desc character varying(100),
    tag_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE tag_attr_lcl OWNER TO mochidb_owner;

--
-- Name: party pty_id; Type: DEFAULT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY party ALTER COLUMN pty_id SET DEFAULT nextval('party_pty_id_seq'::regclass);


--
-- Name: party_type pty_typ_id; Type: DEFAULT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY party_type ALTER COLUMN pty_typ_id SET DEFAULT nextval('party_type_pty_typ_id_seq'::regclass);


--
-- Name: role_type rle_typ_id; Type: DEFAULT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY role_type ALTER COLUMN rle_typ_id SET DEFAULT nextval('role_type_rle_typ_id_seq'::regclass);


--
-- Name: accessories_attr_lcl accessories_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY accessories_attr_lcl
    ADD CONSTRAINT accessories_attr_lcl_pkey PRIMARY KEY (prd_lcl_id);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (addr_id);


--
-- Name: address_type address_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY address_type
    ADD CONSTRAINT address_type_pkey PRIMARY KEY (addr_typ_id);


--
-- Name: bag_item_disc bag_item_disc_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag_item_disc
    ADD CONSTRAINT bag_item_disc_pkey PRIMARY KEY (bag_item_disc_id);


--
-- Name: bag_item bag_item_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag_item
    ADD CONSTRAINT bag_item_pkey PRIMARY KEY (bag_item_id);


--
-- Name: bag bag_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag
    ADD CONSTRAINT bag_pkey PRIMARY KEY (bag_id);


--
-- Name: bag_item_status bag_status_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag_item_status
    ADD CONSTRAINT bag_status_pkey PRIMARY KEY (bag_item_sts_id);


--
-- Name: brand_attr_lcl brand_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_attr_lcl
    ADD CONSTRAINT brand_attr_lcl_pkey PRIMARY KEY (bnd_lcl_id);


--
-- Name: brand_category brand_category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_category
    ADD CONSTRAINT brand_category_pkey PRIMARY KEY (bnd_cat_id);


--
-- Name: brand brand_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (bnd_id);


--
-- Name: brand_promotion brand_promotion_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_promotion
    ADD CONSTRAINT brand_promotion_pkey PRIMARY KEY (bnd_id, prm_id);


--
-- Name: category_attr_lcl category_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_attr_lcl
    ADD CONSTRAINT category_attr_lcl_pkey PRIMARY KEY (cat_lcl_id);


--
-- Name: category_brand category_brand_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_brand
    ADD CONSTRAINT category_brand_pkey PRIMARY KEY (cat_id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (cat_id);


--
-- Name: category_product category_product_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_product
    ADD CONSTRAINT category_product_pkey PRIMARY KEY (cat_id);


--
-- Name: category_promotion category_promotion_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_promotion
    ADD CONSTRAINT category_promotion_pkey PRIMARY KEY (cat_id, prm_id);


--
-- Name: category_type category_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_type
    ADD CONSTRAINT category_type_pkey PRIMARY KEY (cat_typ_id);


--
-- Name: currency currency_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (ccy_id);


--
-- Name: customer customer_cst_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_cst_id_key UNIQUE (cst_num);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (rle_id);


--
-- Name: department_attr_lcl department_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY department_attr_lcl
    ADD CONSTRAINT department_attr_lcl_pkey PRIMARY KEY (dept_lcl_id);


--
-- Name: discount discount_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY discount
    ADD CONSTRAINT discount_pkey PRIMARY KEY (dis_id);


--
-- Name: discount_type discount_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY discount_type
    ADD CONSTRAINT discount_type_pkey PRIMARY KEY (dis_typ_id);


--
-- Name: inventory_location inventory_location_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY inventory_location
    ADD CONSTRAINT inventory_location_pkey PRIMARY KEY (inv_loc_id);


--
-- Name: inventory_transaction inventory_transaction_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY inventory_transaction
    ADD CONSTRAINT inventory_transaction_pkey PRIMARY KEY (inv_trx_id);


--
-- Name: inventory_transaction_type inventory_transaction_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY inventory_transaction_type
    ADD CONSTRAINT inventory_transaction_type_pkey PRIMARY KEY (inv_trx_typ_id);


--
-- Name: order_line order_line_ord_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY order_line
    ADD CONSTRAINT order_line_ord_id_key UNIQUE (ord_id);


--
-- Name: order_line order_line_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY order_line
    ADD CONSTRAINT order_line_pkey PRIMARY KEY (ord_id, prd_id, ord_lne_no);


--
-- Name: order orders_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT orders_pkey PRIMARY KEY (ord_id);


--
-- Name: order orders_pty_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT orders_pty_id_key UNIQUE (pty_id);


--
-- Name: organisation organisation_org_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY organisation
    ADD CONSTRAINT organisation_org_id_key UNIQUE (pty_id);


--
-- Name: party party_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY party
    ADD CONSTRAINT party_pkey PRIMARY KEY (pty_id);


--
-- Name: party_type party_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY party_type
    ADD CONSTRAINT party_type_pkey PRIMARY KEY (pty_typ_id);


--
-- Name: party_type party_type_pty_typ_desc_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY party_type
    ADD CONSTRAINT party_type_pty_typ_desc_key UNIQUE (pty_typ_desc);


--
-- Name: person person_psn_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_psn_id_key UNIQUE (pty_id);


--
-- Name: locale pk_locale; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY locale
    ADD CONSTRAINT pk_locale PRIMARY KEY (lcl_cd);


--
-- Name: shipping_destination_attr_lcl postage_destination_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_destination_attr_lcl
    ADD CONSTRAINT postage_destination_attr_lcl_pkey PRIMARY KEY (shp_dst_lcl_id);


--
-- Name: shipping_destination postage_destination_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_destination
    ADD CONSTRAINT postage_destination_pkey PRIMARY KEY (shp_dst_id);


--
-- Name: shipping_type_attr_lcl postage_type_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_type_attr_lcl
    ADD CONSTRAINT postage_type_attr_lcl_pkey PRIMARY KEY (shp_typ_lcl_id);


--
-- Name: shipping_type postage_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_type
    ADD CONSTRAINT postage_type_pkey PRIMARY KEY (shp_typ_id);


--
-- Name: accessories_attr_lcl prd_id_lcl_cd_1; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY accessories_attr_lcl
    ADD CONSTRAINT prd_id_lcl_cd_1 UNIQUE (prd_id, lcl_cd);


--
-- Name: department_attr_lcl prd_id_lcl_cd_3; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY department_attr_lcl
    ADD CONSTRAINT prd_id_lcl_cd_3 UNIQUE (dept_id, lcl_cd);


--
-- Name: price price_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY price
    ADD CONSTRAINT price_pkey PRIMARY KEY (prc_id);


--
-- Name: price_type price_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY price_type
    ADD CONSTRAINT price_type_pkey PRIMARY KEY (prc_typ_id);


--
-- Name: product_attr_lcl product_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_attr_lcl
    ADD CONSTRAINT product_attr_lcl_pkey PRIMARY KEY (prd_lcl_id);


--
-- Name: product_basic product_basic_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_basic
    ADD CONSTRAINT product_basic_pkey PRIMARY KEY (prd_id);


--
-- Name: product_category product_category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (prd_cat_id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (prd_id);


--
-- Name: product_promotion product_promotion_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_promotion
    ADD CONSTRAINT product_promotion_pkey PRIMARY KEY (prd_id, prm_id);


--
-- Name: product_rating product_rating_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_rating
    ADD CONSTRAINT product_rating_pkey PRIMARY KEY (prd_rat_id);


--
-- Name: product_shipping product_shipping_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_shipping
    ADD CONSTRAINT product_shipping_pkey PRIMARY KEY (prd_id);


--
-- Name: product_status product_status_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_status
    ADD CONSTRAINT product_status_pkey PRIMARY KEY (prd_sts_id);


--
-- Name: product_tag product_tag_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_tag
    ADD CONSTRAINT product_tag_pkey PRIMARY KEY (prd_tag_id);


--
-- Name: department product_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY department
    ADD CONSTRAINT product_type_pkey PRIMARY KEY (dept_id);


--
-- Name: promotion_attr_lcl promotion_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY promotion_attr_lcl
    ADD CONSTRAINT promotion_attr_lcl_pkey PRIMARY KEY (prm_lcl_id);


--
-- Name: promotion_mechanic promotion_mechanic_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY promotion_mechanic
    ADD CONSTRAINT promotion_mechanic_pkey PRIMARY KEY (prm_mec_id);


--
-- Name: promotion promotion_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY promotion
    ADD CONSTRAINT promotion_pkey PRIMARY KEY (prm_id);


--
-- Name: shipping_destination_attr_lcl pst_dst_id_lcl_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_destination_attr_lcl
    ADD CONSTRAINT pst_dst_id_lcl_cd UNIQUE (shp_dst_id, lcl_cd);


--
-- Name: shipping_type_attr_lcl pst_typ_id_lcl_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_type_attr_lcl
    ADD CONSTRAINT pst_typ_id_lcl_cd UNIQUE (shp_typ_id, lcl_cd);


--
-- Name: rating rating_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY rating
    ADD CONSTRAINT rating_pkey PRIMARY KEY (rat_id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (rle_id);


--
-- Name: role role_pty_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pty_id_key UNIQUE (pty_id);


--
-- Name: role_type role_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY role_type
    ADD CONSTRAINT role_type_pkey PRIMARY KEY (rle_typ_id);


--
-- Name: role_type role_type_rle_typ_desc_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY role_type
    ADD CONSTRAINT role_type_rle_typ_desc_key UNIQUE (rle_typ_desc);


--
-- Name: stock_on_hand stock_on_hand_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY stock_on_hand
    ADD CONSTRAINT stock_on_hand_pkey PRIMARY KEY (soh_id);


--
-- Name: supplier supplier_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY supplier
    ADD CONSTRAINT supplier_pkey PRIMARY KEY (rle_id);


--
-- Name: tag_attr_lcl tag_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY tag_attr_lcl
    ADD CONSTRAINT tag_attr_lcl_pkey PRIMARY KEY (tag_lcl_id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (tag_id);


--
-- Name: bag_item uc_bag_item; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag_item
    ADD CONSTRAINT uc_bag_item UNIQUE (bag_id, prd_id);


--
-- Name: bag_item_status uc_bag_sts_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag_item_status
    ADD CONSTRAINT uc_bag_sts_cd UNIQUE (bag_item_sts_cd);


--
-- Name: brand uc_bnd_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand
    ADD CONSTRAINT uc_bnd_cd UNIQUE (bnd_cd);


--
-- Name: brand_attr_lcl uc_bnd_desc_lcl_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_attr_lcl
    ADD CONSTRAINT uc_bnd_desc_lcl_cd UNIQUE (bnd_desc, lcl_cd);


--
-- Name: brand_attr_lcl uc_bnd_lcl; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_attr_lcl
    ADD CONSTRAINT uc_bnd_lcl UNIQUE (bnd_id, lcl_cd);


--
-- Name: brand_category uc_brand_category; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_category
    ADD CONSTRAINT uc_brand_category UNIQUE (bnd_id, cat_id);


--
-- Name: category uc_cat_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category
    ADD CONSTRAINT uc_cat_cd UNIQUE (cat_cd);


--
-- Name: category_attr_lcl uc_cat_desc; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_attr_lcl
    ADD CONSTRAINT uc_cat_desc UNIQUE (cat_desc, lcl_cd);


--
-- Name: category_attr_lcl uc_cat_lcl; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_attr_lcl
    ADD CONSTRAINT uc_cat_lcl UNIQUE (cat_id, lcl_cd);


--
-- Name: category_type uc_cat_typ_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_type
    ADD CONSTRAINT uc_cat_typ_cd UNIQUE (cat_typ_cd);


--
-- Name: stock_on_hand uc_prd_id; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY stock_on_hand
    ADD CONSTRAINT uc_prd_id UNIQUE (soh_prd_id);


--
-- Name: product_attr_lcl uc_prd_lcl_1; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_attr_lcl
    ADD CONSTRAINT uc_prd_lcl_1 UNIQUE (prd_id, lcl_cd);


--
-- Name: product_rating uc_prd_rat; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_rating
    ADD CONSTRAINT uc_prd_rat UNIQUE (prd_id, rat_id, pty_id);


--
-- Name: product_status uc_prd_sts_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_status
    ADD CONSTRAINT uc_prd_sts_cd UNIQUE (prd_sts_cd);


--
-- Name: department uc_prd_typ_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY department
    ADD CONSTRAINT uc_prd_typ_cd UNIQUE (dept_cd);


--
-- Name: promotion uc_prm_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY promotion
    ADD CONSTRAINT uc_prm_cd UNIQUE (prm_cd);


--
-- Name: promotion_attr_lcl uc_prm_lcl_1; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY promotion_attr_lcl
    ADD CONSTRAINT uc_prm_lcl_1 UNIQUE (prm_id, lcl_cd);


--
-- Name: promotion_mechanic uc_prm_mec_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY promotion_mechanic
    ADD CONSTRAINT uc_prm_mec_cd UNIQUE (prm_mec_cd);


--
-- Name: product_category uc_product_category; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_category
    ADD CONSTRAINT uc_product_category UNIQUE (prd_id, cat_id);


--
-- Name: product_tag uc_product_tag; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_tag
    ADD CONSTRAINT uc_product_tag UNIQUE (prd_id, tag_id);


--
-- Name: product uc_product_upc_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product
    ADD CONSTRAINT uc_product_upc_cd UNIQUE (upc_cd);


--
-- Name: shipping_destination uc_pst_dst_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_destination
    ADD CONSTRAINT uc_pst_dst_cd UNIQUE (shp_dst_cd, shp_zne_cd);


--
-- Name: shipping_type uc_pst_typ_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_type
    ADD CONSTRAINT uc_pst_typ_cd UNIQUE (shp_typ_cd);


--
-- Name: tag uc_tag_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT uc_tag_cd UNIQUE (tag_cd);


--
-- Name: tag_attr_lcl uc_tag_desc; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY tag_attr_lcl
    ADD CONSTRAINT uc_tag_desc UNIQUE (tag_desc, lcl_cd);


--
-- Name: tag_attr_lcl uc_tag_lcl; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY tag_attr_lcl
    ADD CONSTRAINT uc_tag_lcl UNIQUE (tag_id, lcl_cd);


--
-- Name: fki_product_attr_lcl_prd_id_fkey; Type: INDEX; Schema: mochi; Owner: mochidb_owner
--

CREATE INDEX fki_product_attr_lcl_prd_id_fkey ON product_attr_lcl USING btree (prd_id);


--
-- Name: fki_promotion_attr_lcl_prm_id_fkey; Type: INDEX; Schema: mochi; Owner: mochidb_owner
--

CREATE INDEX fki_promotion_attr_lcl_prm_id_fkey ON promotion_attr_lcl USING btree (prm_id);


--
-- Name: role_role_typ_id_role_start_dttm_party_id_key; Type: INDEX; Schema: mochi; Owner: mochidb_owner
--

CREATE UNIQUE INDEX role_role_typ_id_role_start_dttm_party_id_key ON role USING btree (rle_typ_id, rle_start_dt, pty_id);


--
-- Name: accessories_attr_lcl accessories_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY accessories_attr_lcl
    ADD CONSTRAINT accessories_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES locale(lcl_cd);


--
-- Name: address address_addr_typ_id_address_type_addr_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_addr_typ_id_address_type_addr_typ_id_fkey FOREIGN KEY (addr_typ_id) REFERENCES address_type(addr_typ_id);


--
-- Name: address address_pty_id_party_pty_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pty_id_party_pty_id_fkey FOREIGN KEY (pty_id) REFERENCES party(pty_id);


--
-- Name: bag_item bag_item_bag_id_bag_bag_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag_item
    ADD CONSTRAINT bag_item_bag_id_bag_bag_id_fkey FOREIGN KEY (bag_id) REFERENCES bag(bag_id);


--
-- Name: bag_item_disc bag_item_disc_bag_item_id_bag_item_bag_item_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag_item_disc
    ADD CONSTRAINT bag_item_disc_bag_item_id_bag_item_bag_item_id_fkey FOREIGN KEY (bag_item_id) REFERENCES bag_item(bag_item_id);


--
-- Name: bag_item bag_item_sts_id_bag_item_sts_bag_item_sts_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag_item
    ADD CONSTRAINT bag_item_sts_id_bag_item_sts_bag_item_sts_id_fkey FOREIGN KEY (bag_item_sts_id) REFERENCES bag_item_status(bag_item_sts_id);


--
-- Name: bag bag_pty_id_party_pty_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY bag
    ADD CONSTRAINT bag_pty_id_party_pty_id_fkey FOREIGN KEY (pty_id) REFERENCES party(pty_id);


--
-- Name: brand_attr_lcl brand_attr_lcl_bnd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_attr_lcl
    ADD CONSTRAINT brand_attr_lcl_bnd_id_fkey FOREIGN KEY (bnd_id) REFERENCES brand(bnd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: brand_attr_lcl brand_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_attr_lcl
    ADD CONSTRAINT brand_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES locale(lcl_cd);


--
-- Name: brand_category brand_category_bnd_id_brand_bnd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_category
    ADD CONSTRAINT brand_category_bnd_id_brand_bnd_id_fkey FOREIGN KEY (bnd_id) REFERENCES brand(bnd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: brand_category brand_category_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY brand_category
    ADD CONSTRAINT brand_category_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_attr_lcl category_attr_lcl_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_attr_lcl
    ADD CONSTRAINT category_attr_lcl_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_attr_lcl category_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_attr_lcl
    ADD CONSTRAINT category_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES locale(lcl_cd);


--
-- Name: category_brand category_brand_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_brand
    ADD CONSTRAINT category_brand_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_product category_product_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY category_product
    ADD CONSTRAINT category_product_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: customer customer_role_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_role_id_fkey FOREIGN KEY (rle_id) REFERENCES role(rle_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: department_attr_lcl department_attr_lcl_dept_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY department_attr_lcl
    ADD CONSTRAINT department_attr_lcl_dept_id_fkey FOREIGN KEY (dept_id) REFERENCES department(dept_id);


--
-- Name: department_attr_lcl department_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY department_attr_lcl
    ADD CONSTRAINT department_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES locale(lcl_cd);


--
-- Name: inventory_transaction inventory_transaction_ccy_id_currency_ccy_id; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY inventory_transaction
    ADD CONSTRAINT inventory_transaction_ccy_id_currency_ccy_id FOREIGN KEY (inv_ccy_id) REFERENCES currency(ccy_id);


--
-- Name: inventory_transaction inventory_transaction_inv_loc_id_inventory_location_inv_loc_id; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY inventory_transaction
    ADD CONSTRAINT inventory_transaction_inv_loc_id_inventory_location_inv_loc_id FOREIGN KEY (inv_loc_id) REFERENCES inventory_location(inv_loc_id);


--
-- Name: inventory_transaction inventory_transaction_inv_prd_id_product_prd_id; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY inventory_transaction
    ADD CONSTRAINT inventory_transaction_inv_prd_id_product_prd_id FOREIGN KEY (inv_prd_id) REFERENCES product(prd_id);


--
-- Name: inventory_transaction inventory_transaction_inv_pty_id_supplier_pty_id; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY inventory_transaction
    ADD CONSTRAINT inventory_transaction_inv_pty_id_supplier_pty_id FOREIGN KEY (inv_pty_id) REFERENCES party(pty_id);


--
-- Name: inventory_transaction inventory_transaction_inv_trx_typ_id_inventory_transaction_type; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY inventory_transaction
    ADD CONSTRAINT inventory_transaction_inv_trx_typ_id_inventory_transaction_type FOREIGN KEY (inv_trx_typ_id) REFERENCES inventory_transaction_type(inv_trx_typ_id);


--
-- Name: order_line order_line_order_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY order_line
    ADD CONSTRAINT order_line_order_id_fkey FOREIGN KEY (ord_id) REFERENCES "order"(ord_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: order_line order_line_product_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY order_line
    ADD CONSTRAINT order_line_product_id_fkey FOREIGN KEY (prd_id) REFERENCES product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: order orders_party_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT orders_party_id_fkey FOREIGN KEY (pty_id) REFERENCES party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: organisation organisation_org_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY organisation
    ADD CONSTRAINT organisation_org_id_fkey FOREIGN KEY (pty_id) REFERENCES party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: party party_pty_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY party
    ADD CONSTRAINT party_pty_typ_id_fkey FOREIGN KEY (pty_typ_id) REFERENCES party_type(pty_typ_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: person person_person_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_person_id_fkey FOREIGN KEY (pty_id) REFERENCES party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: shipping_destination_attr_lcl postage_destination_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_destination_attr_lcl
    ADD CONSTRAINT postage_destination_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES locale(lcl_cd);


--
-- Name: shipping_destination_attr_lcl postage_destination_attr_lcl_pst_dst_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_destination_attr_lcl
    ADD CONSTRAINT postage_destination_attr_lcl_pst_dst_id_fkey FOREIGN KEY (shp_dst_id) REFERENCES shipping_destination(shp_dst_id);


--
-- Name: shipping_type_attr_lcl postage_type_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_type_attr_lcl
    ADD CONSTRAINT postage_type_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES locale(lcl_cd);


--
-- Name: shipping_type_attr_lcl postage_type_attr_lcl_pst_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY shipping_type_attr_lcl
    ADD CONSTRAINT postage_type_attr_lcl_pst_typ_id_fkey FOREIGN KEY (shp_typ_id) REFERENCES shipping_type(shp_typ_id);


--
-- Name: price price_currency_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY price
    ADD CONSTRAINT price_currency_id_fkey FOREIGN KEY (ccy_id) REFERENCES currency(ccy_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: price price_product_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY price
    ADD CONSTRAINT price_product_id_fkey FOREIGN KEY (prd_id) REFERENCES product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_attr_lcl product_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_attr_lcl
    ADD CONSTRAINT product_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES locale(lcl_cd);


--
-- Name: product_attr_lcl product_attr_lcl_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_attr_lcl
    ADD CONSTRAINT product_attr_lcl_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES product(prd_id);


--
-- Name: product_basic product_basic_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_basic
    ADD CONSTRAINT product_basic_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES product(prd_id);


--
-- Name: product_category product_category_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_category
    ADD CONSTRAINT product_category_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_category product_category_prd_id_product_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_category
    ADD CONSTRAINT product_category_prd_id_product_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_shipping product_shipping_postage_destination_shp_dst_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_shipping
    ADD CONSTRAINT product_shipping_postage_destination_shp_dst_id_fkey FOREIGN KEY (shp_dst_id) REFERENCES shipping_destination(shp_dst_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_shipping product_shipping_postage_type_shp_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_shipping
    ADD CONSTRAINT product_shipping_postage_type_shp_typ_id_fkey FOREIGN KEY (shp_typ_id) REFERENCES shipping_type(shp_typ_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_shipping product_shipping_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_shipping
    ADD CONSTRAINT product_shipping_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES product(prd_id);


--
-- Name: product product_sts_id_product_status_sts_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_sts_id_product_status_sts_id_fkey FOREIGN KEY (prd_sts_id) REFERENCES product_status(prd_sts_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_tag product_tag_prd_id_product_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_tag
    ADD CONSTRAINT product_tag_prd_id_product_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_tag product_tag_tag_id_tag_tag_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product_tag
    ADD CONSTRAINT product_tag_tag_id_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES tag(tag_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product product_typ_id_product_type_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_typ_id_product_type_typ_id_fkey FOREIGN KEY (dept_id) REFERENCES department(dept_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: promotion_attr_lcl promotion_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY promotion_attr_lcl
    ADD CONSTRAINT promotion_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES locale(lcl_cd);


--
-- Name: promotion_attr_lcl promotion_attr_lcl_prm_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY promotion_attr_lcl
    ADD CONSTRAINT promotion_attr_lcl_prm_id_fkey FOREIGN KEY (prm_id) REFERENCES promotion(prm_id);


--
-- Name: role role_party_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_party_id_fkey FOREIGN KEY (pty_id) REFERENCES party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: role role_role_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_role_typ_id_fkey FOREIGN KEY (rle_typ_id) REFERENCES role_type(rle_typ_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: stock_on_hand stock_on_hand_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY stock_on_hand
    ADD CONSTRAINT stock_on_hand_prd_id_fkey FOREIGN KEY (soh_prd_id) REFERENCES product(prd_id);


--
-- Name: supplier supplier_role_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY supplier
    ADD CONSTRAINT supplier_role_id_fkey FOREIGN KEY (rle_id) REFERENCES role(rle_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: tag_attr_lcl tag_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY tag_attr_lcl
    ADD CONSTRAINT tag_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES locale(lcl_cd);


--
-- Name: tag_attr_lcl tag_attr_lcl_tag_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY tag_attr_lcl
    ADD CONSTRAINT tag_attr_lcl_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES tag(tag_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: mochi; Type: ACL; Schema: -; Owner: mochidb_owner
--

GRANT USAGE ON SCHEMA mochi TO security_app;
GRANT ALL ON SCHEMA mochi TO security_owner;
GRANT USAGE ON SCHEMA mochi TO mochi_app;


--
-- Name: accessories_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE accessories_attr_lcl TO mochi_app;


--
-- Name: address; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE address TO mochi_app;


--
-- Name: address_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE address_type TO mochi_app;


--
-- Name: bag_bag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE bag_bag_id_seq TO mochi_app;


--
-- Name: bag; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE bag TO mochi_app;


--
-- Name: bag_item; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE bag_item TO mochi_app;


--
-- Name: bag_item_disc; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE bag_item_disc TO mochi_app;


--
-- Name: bag_status_bag_sts_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE bag_status_bag_sts_id_seq TO mochi_app;


--
-- Name: bag_item_status; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE bag_item_status TO mochi_app;


--
-- Name: brand_bnd_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE brand_bnd_id_seq TO mochi_app;


--
-- Name: brand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE brand TO mochi_app;


--
-- Name: brand_attr_lcl_bnd_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE brand_attr_lcl_bnd_id_seq TO mochi_app;


--
-- Name: brand_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE brand_attr_lcl TO mochi_app;


--
-- Name: brand_category_bnd_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE brand_category_bnd_cat_id_seq TO mochi_app;


--
-- Name: brand_category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE brand_category TO mochi_app;


--
-- Name: category_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE category_cat_id_seq TO mochi_app;


--
-- Name: category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE category TO mochi_app;


--
-- Name: category_attr_lcl_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE category_attr_lcl_cat_id_seq TO mochi_app;


--
-- Name: category_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE category_attr_lcl TO mochi_app;


--
-- Name: category_brand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE category_brand TO mochi_app;


--
-- Name: category_product; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE category_product TO mochi_app;


--
-- Name: category_promotion; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE category_promotion TO mochi_app;


--
-- Name: category_type_cat_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE category_type_cat_typ_id_seq TO mochi_app;


--
-- Name: category_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE category_type TO mochi_app;


--
-- Name: currency; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE currency TO mochi_app;


--
-- Name: customer_cst_num_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE customer_cst_num_seq TO mochi_app;


--
-- Name: customer; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE customer TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE customer TO mochi_app;


--
-- Name: product_type_prd_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE product_type_prd_typ_id_seq TO mochi_app;


--
-- Name: department; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE department TO mochi_app;


--
-- Name: department_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE department_attr_lcl TO mochi_app;


--
-- Name: discount; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE discount TO mochi_app;


--
-- Name: discount_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE discount_type TO mochi_app;


--
-- Name: hibernate_sequence; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE hibernate_sequence TO mochi_app;


--
-- Name: hierarchy_hir_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE hierarchy_hir_id_seq TO mochi_app;


--
-- Name: inventory_location; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE inventory_location TO mochi_app;


--
-- Name: inventory_transaction_inv_trx_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE inventory_transaction_inv_trx_id_seq TO mochi_app;


--
-- Name: inventory_transaction; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE inventory_transaction TO mochi_app;


--
-- Name: inventory_transaction_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE inventory_transaction_type TO mochi_app;


--
-- Name: layout_category_lay_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE layout_category_lay_cat_id_seq TO mochi_app;


--
-- Name: layout_lay_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE layout_lay_id_seq TO mochi_app;


--
-- Name: locale; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE locale TO mochi_app;


--
-- Name: order; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE "order" TO mochi_app;


--
-- Name: order_line; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE order_line TO mochi_app;


--
-- Name: organisation; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE organisation TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE organisation TO mochi_app;


--
-- Name: party; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE party TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE party TO mochi_app;


--
-- Name: party_party_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE party_party_id_seq TO mochi_app;


--
-- Name: party_pty_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE party_pty_id_seq TO mochi_app;


--
-- Name: party_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE party_type TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE party_type TO mochi_app;


--
-- Name: party_type_pty_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE party_type_pty_typ_id_seq TO mochi_app;


--
-- Name: person; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE person TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE person TO mochi_app;


--
-- Name: person_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE person_id_seq TO mochi_app;


--
-- Name: price_prc_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE price_prc_id_seq TO mochi_app;


--
-- Name: price; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE price TO mochi_app;


--
-- Name: price_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE price_type TO mochi_app;


--
-- Name: product; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product TO mochi_app;


--
-- Name: product_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product_attr_lcl TO mochi_app;


--
-- Name: product_basic; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product_basic TO mochi_app;


--
-- Name: product_category_prd_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE product_category_prd_cat_id_seq TO mochi_app;


--
-- Name: product_category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product_category TO mochi_app;


--
-- Name: product_promotion; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product_promotion TO mochi_app;


--
-- Name: product_rating_prd_rat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE product_rating_prd_rat_id_seq TO mochi_app;


--
-- Name: product_rating; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product_rating TO mochi_app;


--
-- Name: product_shipping; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product_shipping TO mochi_app;


--
-- Name: product_status_prd_sts_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE product_status_prd_sts_id_seq TO mochi_app;


--
-- Name: product_status; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product_status TO mochi_app;


--
-- Name: product_supplier; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product_supplier TO mochi_app;


--
-- Name: product_tag_prd_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE product_tag_prd_tag_id_seq TO mochi_app;


--
-- Name: product_tag; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE product_tag TO mochi_app;


--
-- Name: product_tag_attr_lcl_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE product_tag_attr_lcl_tag_id_seq TO mochi_app;


--
-- Name: promotion; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE promotion TO mochi_app;


--
-- Name: promotion_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE promotion_attr_lcl TO mochi_app;


--
-- Name: promotion_category_prm_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE promotion_category_prm_cat_id_seq TO mochi_app;


--
-- Name: promotion_mechanic; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE promotion_mechanic TO mochi_app;


--
-- Name: rating; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE rating TO mochi_app;


--
-- Name: role_rle_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE role_rle_id_seq TO mochi_app;


--
-- Name: role; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE role TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE role TO mochi_app;


--
-- Name: role_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE role_type TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE role_type TO mochi_app;


--
-- Name: role_type_rle_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE role_type_rle_typ_id_seq TO mochi_app;


--
-- Name: role_type_role_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE role_type_role_typ_id_seq TO mochi_app;


--
-- Name: shipping_destination; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE shipping_destination TO mochi_app;


--
-- Name: shipping_destination_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE shipping_destination_attr_lcl TO mochi_app;


--
-- Name: shipping_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE shipping_type TO mochi_app;


--
-- Name: shipping_type_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE shipping_type_attr_lcl TO mochi_app;


--
-- Name: stock_on_hand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE stock_on_hand TO mochi_app;


--
-- Name: supplier_sup_num_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE supplier_sup_num_seq TO mochi_app;


--
-- Name: supplier; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE supplier TO mochi_app;


--
-- Name: tag_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE tag_tag_id_seq TO mochi_app;


--
-- Name: tag; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE tag TO mochi_app;


--
-- Name: tag_attr_lcl_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE tag_attr_lcl_tag_id_seq TO mochi_app;


--
-- Name: tag_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE tag_attr_lcl TO mochi_app;


--
-- PostgreSQL database dump complete
--

