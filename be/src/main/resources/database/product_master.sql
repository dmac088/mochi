﻿select 	upc_cd,
	prd_crtd_dt as product_created_date,
	max(case
	when pa.lcl_cd = 'en-GB'
	then pa.prd_desc
	end) as product_description_engb,
	max(case
	when pa.lcl_cd = 'zh-HK'
	then pa.prd_desc
	end) as product_description_zhhk,
	bnd.bnd_cd,
	max(case
	when ba.lcl_cd = 'en-GB'
	then ba.bnd_desc
	end) as brand_description_engb,
	max(case
	when ba.lcl_cd = 'zh-HK'
	then ba.bnd_desc
	end) as brand_description_zhhk,
	max(case
	when ba.lcl_cd = 'en-GB'
	then ba.bnd_img_pth
	end) as brand_image_path_engb,
	max(case
	when ba.lcl_cd = 'zh-HK'
	then ba.bnd_img_pth
	end) as brand_image_path_zhhk    ,
	max(case
	when prct.prc_typ_cd = 'RET01'
	and  curr.ccy_cd = 'HKD'
	then prc.prc_val
	end) as product_retail_price_hkd,
	max(case
	when prct.prc_typ_cd = 'MKD01'
	and  curr.ccy_cd = 'HKD'
	then prc.prc_val
	end) as product_markdown_price_hkd,
	max(case
	when prct.prc_typ_cd = 'RET01'
	and  curr.ccy_cd = 'USD'
	then prc.prc_val
	end) as product_retail_price_usd,
	max(case
	when prct.prc_typ_cd = 'MKD01'
	and  curr.ccy_cd = 'USD'
	then prc.prc_val
	end) as product_markdown_price_usd,
	max(case
	when pa.lcl_cd = 'en-GB'
	then pa.prd_img_pth
	end) as product_image_path_engb,
	max(case
	when pa.lcl_cd = 'zh-HK'
	then pa.prd_img_pth
	end) as product_image_path_zhhk     
		 
from mochi.product p
	inner join mochi.product_attr_lcl pa
	on p.prd_id = pa.prd_id
	
	inner join mochi.brand bnd
	on p.bnd_id = bnd.bnd_id
	
	inner join mochi.brand_attr_lcl ba
	on bnd.bnd_id = ba.bnd_id
	
	inner join mochi.price prc
	on p.prd_id = prc.prd_id
	and now() between prc.prc_st_dt and prc.prc_en_dt

	inner join mochi.price_type prct
	on prc.prc_typ_id = prct.prc_typ_id
	
	inner join mochi.currency curr
	on prc.ccy_id = curr.ccy_id

	inner join mochi.product_status ps
	on p.prd_sts_id = ps.prd_sts_id
	
	inner join mochi.product_type pt
	on p.prd_typ_id = pt.prd_typ_id
	
where pt.prd_typ_cd = 'NML01'
and ps.prd_sts_cd = 'ACT01'
group by 	upc_cd,
	prd_crtd_dt,
	bnd.bnd_cd;
		