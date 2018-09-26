delete from mochi.customer;

delete from mochi.role;
delete from mochi.role_type;

delete from mochi.person;
delete from mochi.party;
delete from mochi.party_type;

delete from mochi.product_attr_lcl;
delete from mochi.product;


INSERT INTO mochi.party_type(
	pty_typ_id, pty_typ_desc)
	VALUES (1, 'Person');
    
INSERT INTO mochi.party_type(
	pty_typ_id, pty_typ_desc)
	VALUES (2, 'Organisation');    

INSERT INTO mochi.party(
	pty_id, pty_typ_id, pty_usr_nm, pty_pwd)
	VALUES (1, 2, 'dmac088', 'password');

INSERT INTO mochi.person(
	pty_id, psn_gvn_nm_en, psn_fml_nm_en, psn_nm_cn, enb)
	VALUES (1, 'Daniel', 'Mackie', '丹尼爾麥基', '1');

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (1, '12345678', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (2, '23456789', now());

insert into mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, prd_cat_desc, lcl_cd)
values (1, 1, 'mango', 'images/mango.jpg', 5.99, 'fruit', 'ENG');

insert into mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, prd_cat_desc, lcl_cd)
values (2, 1, '芒果', 'images/mango.jpg', 5.99, '水果', 'HKG');


insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, prd_cat_desc, lcl_cd)	
values (3, 2, 'orange', 'images/orange.jpg', 8.99, 'fruit', 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, prd_cat_desc, lcl_cd)
values (4, 2, '橙子', 'images/orange.jpg', 8.99, '水果', 'HKG');

INSERT INTO mochi.role_type(
	rle_typ_id, rle_typ_desc)
	VALUES (1, 'Customer');

INSERT INTO mochi.role_type(
	rle_typ_id, rle_typ_desc)
	VALUES (2, 'Supplier');

INSERT INTO mochi.role(
	rle_id, rle_typ_id, rle_start_dttm, pty_id)
	VALUES (1, 1, now(), 1);

INSERT INTO mochi.customer(
	rle_id, cst_id)
	VALUES (1, '0123456789');
