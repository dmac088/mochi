
truncate table mochi.customer cascade;

truncate table mochi.role cascade;
truncate table mochi.role_type cascade;

truncate table mochi.organisation cascade;
truncate table mochi.person cascade;
truncate table mochi.party cascade;
truncate table mochi.party_type cascade;

truncate table mochi.product_attr_lcl cascade;
truncate table mochi.product cascade;



INSERT INTO mochi.party_type(
	pty_typ_id, pty_typ_desc)
	VALUES (1, 'Person');
    
INSERT INTO mochi.party_type(
	pty_typ_id, pty_typ_desc)
	VALUES (2, 'Organisation');    


INSERT INTO mochi.role_type(
	rle_typ_id, rle_typ_desc)
	VALUES (1, 'Customer');

INSERT INTO mochi.role_type(
	rle_typ_id, rle_typ_desc)
	VALUES (2, 'Supplier');

INSERT INTO mochi.party(
	pty_id, pty_typ_id)
	VALUES (1, 1);

INSERT INTO mochi.party(
	pty_id, pty_typ_id)
	VALUES (2, 2);

INSERT INTO mochi.party(
	pty_id, pty_typ_id)
	VALUES (3, 1);

INSERT INTO mochi.party(
	pty_id, pty_typ_id)
	VALUES (4, 2);

INSERT INTO mochi.person(
	psn_id, psn_gvn_nm_en, psn_fml_nm_en, psn_nm_cn, enb)
	VALUES (1, 'Daniel', 'Mackie', '丹尼爾麥基', '1');
    
INSERT INTO mochi.organisation(
	org_id, org_nme, org_reg_no)
	VALUES (2, 'Big Balloons LTD', '1234567');    

INSERT INTO mochi.person(
	psn_id, psn_gvn_nm_en, psn_fml_nm_en, psn_nm_cn, enb)
	VALUES (3, 'Purple', 'Wong', '丹尼爾麥基', '1');
    
INSERT INTO mochi.organisation(
	org_id, org_nme, org_reg_no)
	VALUES (4, 'Hop Lun LTD', '3216547');    

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


INSERT INTO mochi.role(
	rle_id, rle_typ_id, rle_start_dttm, pty_id)
	VALUES (1, 1, now(), 1);

INSERT INTO mochi.role(
	rle_id, rle_typ_id, rle_start_dttm, pty_id)
	VALUES (2, 1, now(), 2);

INSERT INTO mochi.customer(
	rle_id, cst_id)
	VALUES (1, '0123456789');

INSERT INTO mochi.customer(
	rle_id, cst_id)
	VALUES (2, '9876543210');
