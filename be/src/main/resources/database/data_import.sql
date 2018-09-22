

delete from mochi.product_attr_lcl;
delete from mochi.product;


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


