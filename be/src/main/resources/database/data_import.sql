
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
	VALUES (0, 2);

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

INSERT INTO mochi.organisation(
            org_id, org_nme, org_reg_no)
    VALUES (0, 'dummy', '12345679');

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


delete from mochi.product_category;
delete from mochi.product_attr_lcl;
delete from mochi.product;


insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (1, '12345678', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (2, '23464789', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (3, '19633678', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (4, '23456645', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (5, '12383658', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (6, '23739283', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (7, '14445678', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (8, '25556789', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (9, '12366678', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (10, '23477789', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (11, '3577789', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (12, '64377789', now());

insert into mochi.product(prd_id, upc_cd, prd_crtd_dt)
values (13, '76477789', now());


insert into mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (1, 1, 'mango', 'images/mango.jpg', 75, 'ENG');

insert into mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (2, 1, '芒果', 'images/mango.jpg', 75,'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (3, 2, 'orange', 'images/orange.jpg', 75, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (4, 2, '橙子', 'images/orange.jpg', 75, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (5, 3, 'brocolli', 'images/broccoli.jpg', 120, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (6, 3, '西兰花', 'images/broccoli.jpg', 120, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (7, 4, 'cauliflower', 'images/cauliflower.jpg', 60, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (8, 4, '菜花', 'images/broccoli.jpg', 60, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (9, 5, 'cucumber', 'images/cucumber.jpg', 48, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (10, 5, '黄瓜', 'images/cucumber.jpg', 48, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (11, 6, 'beetroot', 'images/beetroot.jpg', 32, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (12, 6, '红菜头', 'images/beetroot.jpg', 32, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (13, 7, 'carrot', 'images/carrots.jpg', 56, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (14, 7, '胡萝卜', 'images/carrots.jpg', 56, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (15, 8, 'tomato', 'images/tomato.jpg', 16, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (16, 8, '番茄', 'images/tomato.jpg', 16, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (17, 9, 'beans', 'images/beans.jpg', 82, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (18, 9, '豆子', 'images/beans.jpg', 82, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (19, 10, 'brinjal', 'images/brinjal.jpg', 35, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (20, 10, '茄子', 'images/brinjal.jpg', 35, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (21, 11, 'capsicum', 'images/capsicum.jpg', 60, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (22, 11, '辣椒', 'images/capsicum.jpg', 60, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (23, 12, 'mushroom', 'images/button-mushroom.jpg', 60, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (24, 12, '蘑菇', 'images/button-mushroom.jpg', 60, 'HKG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)	
values (25, 13, 'potato', 'images/potato.jpg', 22, 'ENG');

insert into  mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, prd_rrp, lcl_cd)
values (26, 13, '土豆', 'images/potato.jpg', 22, 'HKG');






/*
    {
        "id": 12,
        "name": "Pumpkin - 1 Kg",
        "price": 48,
        "image": "images/pumpkin.jpg",
        "category": "vegetables"
    },
    {
        "id": 13,
        "name": "Corn - 1 Kg",
        "price": 75,
        "image": "images/corn.jpg",
        "category": "vegetables"
    },
    {
        "id": 14,
        "name": "Onion - 1 Kg",
        "price": 16,
        "image": "images/onion.jpg",
        "category": "vegetables"
    },
    {
        "id": 15,
        "name": "Apple - 1 Kg",
        "price": 72,
        "image": "images/apple.jpg",
        "category": "fruits"
    },
    {
        "id": 16,
        "name": "Banana - 1 Kg",
        "price": 45,
        "image": "images/banana.jpg",
        "category": "fruits"
    },
    {
        "id": 17,
        "name": "Grapes - 1 Kg",
        "price": 60,
        "image": "images/grapes.jpg",
        "category": "fruits"
    },
    {
        "id": 18,
        "name": "Mango - 1 Kg",
        "price": 75,
        "image": "images/mango.jpg",
        "category": "fruits"
    },
    {
        "id": 19,
        "name": "Musk Melon - 1 Kg",
        "price": 36,
        "image": "images/musk-melon.jpg",
        "category": "fruits"
    },
    {
        "id": 20,
        "name": "Orange - 1 Kg",
        "price": 75,
        "image": "images/orange.jpg",
        "category": "fruits"
    },
    {
        "id": 21,
        "name": "Pears - 1 Kg",
        "price": 69,
        "image": "images/pears.jpg",
        "category": "fruits"
    },
    {
        "id": 22,
        "name": "Pomegranate - 1 Kg",
        "price": 95,
        "image": "images/pomegranate.jpg",
        "category": "fruits"
    },
    {
        "id": 23,
        "name": "Raspberry - 1/4 Kg",
        "price": 160,
        "image": "images/raspberry.jpg",
        "category": "fruits"
    },
    {
        "id": 24,
        "name": "Strawberry - 1/4 Kg",
        "price": 180,
        "image": "images/strawberry.jpg",
        "category": "fruits"
    },
    {
        "id": 25,
        "name": "Water Melon - 1 Kg",
        "price": 28,
        "image": "images/water-melon.jpg",
        "category": "fruits"
    },
    {
        "id": 26,
        "name": "Almonds - 1/4 Kg",
        "price": 200,
        "image": "images/almonds.jpg",
        "category": "nuts"
    },
    {
        "id": 27,
        "name": "Pista - 1/4 Kg",
        "price": 190,
        "image": "images/pista.jpg",
        "category": "nuts"
    },
    {
        "id": 28,
        "name": "Nuts Mixture - 1 Kg",
        "price": 950,
        "image": "images/nuts-mixture.jpg",
        "category": "nuts"
    },
    {
        "id": 29,
        "name": "Cashews - 1 Kg",
        "price": 650,
        "image": "images/cashews.jpg",
        "category": "nuts"
    },
    {
        "id": 30,
        "name": "Walnuts - 1/4 Kg",
        "price": 170,
        "image": "images/walnuts.jpg",
        "category": "nuts"
    }
*/

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (1, 3);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (2, 3);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (3, 4);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (4, 4);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (5, 4);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (6, 4);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (7, 4);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (8, 3);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (9, 4);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (10, 4);
  
INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (11, 4);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (12, 4);

INSERT INTO mochi.product_category(
            prd_id, cat_id)
VALUES (13, 4);


INSERT INTO mochi.role(
	rle_id, rle_typ_id, rle_start_dttm, pty_id)
	VALUES (1, 1, now(), 1);

INSERT INTO mochi.role(
	rle_id, rle_typ_id, rle_start_dttm, pty_id)
	VALUES (2, 1, now(), 2);

INSERT INTO mochi.customer(
	rle_id)
	VALUES (1);

INSERT INTO mochi.customer(
	rle_id)
	VALUES (2);
