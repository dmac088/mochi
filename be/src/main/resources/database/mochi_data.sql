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

--
-- Data for Name: address; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: address_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: brand; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.brand VALUES (32, 'DLE01');
INSERT INTO mochi.brand VALUES (33, 'ENZ01');
INSERT INTO mochi.brand VALUES (34, 'DIV01');
INSERT INTO mochi.brand VALUES (35, 'DRI01');
INSERT INTO mochi.brand VALUES (36, 'WON01');
INSERT INTO mochi.brand VALUES (37, 'PLA01');
INSERT INTO mochi.brand VALUES (38, 'GLO01');
INSERT INTO mochi.brand VALUES (39, 'ADO01');
INSERT INTO mochi.brand VALUES (40, 'GOL01');
INSERT INTO mochi.brand VALUES (41, 'SHI01');


--
-- Data for Name: locale; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.locale VALUES ('af-ZA');
INSERT INTO mochi.locale VALUES ('am-ET');
INSERT INTO mochi.locale VALUES ('ar-AE');
INSERT INTO mochi.locale VALUES ('ar-BH');
INSERT INTO mochi.locale VALUES ('ar-DZ');
INSERT INTO mochi.locale VALUES ('ar-EG');
INSERT INTO mochi.locale VALUES ('ar-IQ');
INSERT INTO mochi.locale VALUES ('ar-JO');
INSERT INTO mochi.locale VALUES ('ar-KW');
INSERT INTO mochi.locale VALUES ('ar-LB');
INSERT INTO mochi.locale VALUES ('ar-LY');
INSERT INTO mochi.locale VALUES ('ar-MA');
INSERT INTO mochi.locale VALUES ('arn-CL');
INSERT INTO mochi.locale VALUES ('ar-OM');
INSERT INTO mochi.locale VALUES ('ar-QA');
INSERT INTO mochi.locale VALUES ('ar-SA');
INSERT INTO mochi.locale VALUES ('ar-SY');
INSERT INTO mochi.locale VALUES ('ar-TN');
INSERT INTO mochi.locale VALUES ('ar-YE');
INSERT INTO mochi.locale VALUES ('as-IN');
INSERT INTO mochi.locale VALUES ('az-Cyrl-AZ');
INSERT INTO mochi.locale VALUES ('az-Latn-AZ');
INSERT INTO mochi.locale VALUES ('ba-RU');
INSERT INTO mochi.locale VALUES ('be-BY');
INSERT INTO mochi.locale VALUES ('bg-BG');
INSERT INTO mochi.locale VALUES ('bn-BD');
INSERT INTO mochi.locale VALUES ('bn-IN');
INSERT INTO mochi.locale VALUES ('bo-CN');
INSERT INTO mochi.locale VALUES ('br-FR');
INSERT INTO mochi.locale VALUES ('bs-Cyrl-BA');
INSERT INTO mochi.locale VALUES ('bs-Latn-BA');
INSERT INTO mochi.locale VALUES ('ca-ES');
INSERT INTO mochi.locale VALUES ('co-FR');
INSERT INTO mochi.locale VALUES ('cs-CZ');
INSERT INTO mochi.locale VALUES ('cy-GB');
INSERT INTO mochi.locale VALUES ('da-DK');
INSERT INTO mochi.locale VALUES ('de-AT');
INSERT INTO mochi.locale VALUES ('de-CH');
INSERT INTO mochi.locale VALUES ('de-DE');
INSERT INTO mochi.locale VALUES ('de-LI');
INSERT INTO mochi.locale VALUES ('de-LU');
INSERT INTO mochi.locale VALUES ('dsb-DE');
INSERT INTO mochi.locale VALUES ('dv-MV');
INSERT INTO mochi.locale VALUES ('el-GR');
INSERT INTO mochi.locale VALUES ('en-029');
INSERT INTO mochi.locale VALUES ('en-AU');
INSERT INTO mochi.locale VALUES ('en-BZ');
INSERT INTO mochi.locale VALUES ('en-CA');
INSERT INTO mochi.locale VALUES ('en-GB');
INSERT INTO mochi.locale VALUES ('en-IE');
INSERT INTO mochi.locale VALUES ('en-IN');
INSERT INTO mochi.locale VALUES ('en-JM');
INSERT INTO mochi.locale VALUES ('en-MY');
INSERT INTO mochi.locale VALUES ('en-NZ');
INSERT INTO mochi.locale VALUES ('en-PH');
INSERT INTO mochi.locale VALUES ('en-SG');
INSERT INTO mochi.locale VALUES ('en-TT');
INSERT INTO mochi.locale VALUES ('en-US');
INSERT INTO mochi.locale VALUES ('en-ZA');
INSERT INTO mochi.locale VALUES ('en-ZW');
INSERT INTO mochi.locale VALUES ('es-AR');
INSERT INTO mochi.locale VALUES ('es-BO');
INSERT INTO mochi.locale VALUES ('es-CL');
INSERT INTO mochi.locale VALUES ('es-CO');
INSERT INTO mochi.locale VALUES ('es-CR');
INSERT INTO mochi.locale VALUES ('es-DO');
INSERT INTO mochi.locale VALUES ('es-EC');
INSERT INTO mochi.locale VALUES ('es-ES');
INSERT INTO mochi.locale VALUES ('es-GT');
INSERT INTO mochi.locale VALUES ('es-HN');
INSERT INTO mochi.locale VALUES ('es-MX');
INSERT INTO mochi.locale VALUES ('es-NI');
INSERT INTO mochi.locale VALUES ('es-PA');
INSERT INTO mochi.locale VALUES ('es-PE');
INSERT INTO mochi.locale VALUES ('es-PR');
INSERT INTO mochi.locale VALUES ('es-PY');
INSERT INTO mochi.locale VALUES ('es-SV');
INSERT INTO mochi.locale VALUES ('es-US');
INSERT INTO mochi.locale VALUES ('es-UY');
INSERT INTO mochi.locale VALUES ('es-VE');
INSERT INTO mochi.locale VALUES ('et-EE');
INSERT INTO mochi.locale VALUES ('eu-ES');
INSERT INTO mochi.locale VALUES ('fa-IR');
INSERT INTO mochi.locale VALUES ('fi-FI');
INSERT INTO mochi.locale VALUES ('fil-PH');
INSERT INTO mochi.locale VALUES ('fo-FO');
INSERT INTO mochi.locale VALUES ('fr-BE');
INSERT INTO mochi.locale VALUES ('fr-CA');
INSERT INTO mochi.locale VALUES ('fr-CH');
INSERT INTO mochi.locale VALUES ('fr-FR');
INSERT INTO mochi.locale VALUES ('fr-LU');
INSERT INTO mochi.locale VALUES ('fr-MC');
INSERT INTO mochi.locale VALUES ('fy-NL');
INSERT INTO mochi.locale VALUES ('ga-IE');
INSERT INTO mochi.locale VALUES ('gd-GB');
INSERT INTO mochi.locale VALUES ('gl-ES');
INSERT INTO mochi.locale VALUES ('gsw-FR');
INSERT INTO mochi.locale VALUES ('gu-IN');
INSERT INTO mochi.locale VALUES ('ha-Latn-NG');
INSERT INTO mochi.locale VALUES ('he-IL');
INSERT INTO mochi.locale VALUES ('hi-IN');
INSERT INTO mochi.locale VALUES ('hr-BA');
INSERT INTO mochi.locale VALUES ('hr-HR');
INSERT INTO mochi.locale VALUES ('hsb-DE');
INSERT INTO mochi.locale VALUES ('hu-HU');
INSERT INTO mochi.locale VALUES ('hy-AM');
INSERT INTO mochi.locale VALUES ('id-ID');
INSERT INTO mochi.locale VALUES ('ig-NG');
INSERT INTO mochi.locale VALUES ('ii-CN');
INSERT INTO mochi.locale VALUES ('is-IS');
INSERT INTO mochi.locale VALUES ('it-CH');
INSERT INTO mochi.locale VALUES ('it-IT');
INSERT INTO mochi.locale VALUES ('iu-Cans-CA');
INSERT INTO mochi.locale VALUES ('iu-Latn-CA');
INSERT INTO mochi.locale VALUES ('ja-JP');
INSERT INTO mochi.locale VALUES ('ka-GE');
INSERT INTO mochi.locale VALUES ('kk-KZ');
INSERT INTO mochi.locale VALUES ('kl-GL');
INSERT INTO mochi.locale VALUES ('km-KH');
INSERT INTO mochi.locale VALUES ('kn-IN');
INSERT INTO mochi.locale VALUES ('kok-IN');
INSERT INTO mochi.locale VALUES ('ko-KR');
INSERT INTO mochi.locale VALUES ('ky-KG');
INSERT INTO mochi.locale VALUES ('lb-LU');
INSERT INTO mochi.locale VALUES ('lo-LA');
INSERT INTO mochi.locale VALUES ('lt-LT');
INSERT INTO mochi.locale VALUES ('lv-LV');
INSERT INTO mochi.locale VALUES ('mi-NZ');
INSERT INTO mochi.locale VALUES ('mk-MK');
INSERT INTO mochi.locale VALUES ('ml-IN');
INSERT INTO mochi.locale VALUES ('mn-MN');
INSERT INTO mochi.locale VALUES ('mn-Mong-CN');
INSERT INTO mochi.locale VALUES ('moh-CA');
INSERT INTO mochi.locale VALUES ('mr-IN');
INSERT INTO mochi.locale VALUES ('ms-BN');
INSERT INTO mochi.locale VALUES ('ms-MY');
INSERT INTO mochi.locale VALUES ('mt-MT');
INSERT INTO mochi.locale VALUES ('nb-NO');
INSERT INTO mochi.locale VALUES ('ne-NP');
INSERT INTO mochi.locale VALUES ('nl-BE');
INSERT INTO mochi.locale VALUES ('nl-NL');
INSERT INTO mochi.locale VALUES ('nn-NO');
INSERT INTO mochi.locale VALUES ('nso-ZA');
INSERT INTO mochi.locale VALUES ('oc-FR');
INSERT INTO mochi.locale VALUES ('or-IN');
INSERT INTO mochi.locale VALUES ('pa-IN');
INSERT INTO mochi.locale VALUES ('pl-PL');
INSERT INTO mochi.locale VALUES ('prs-AF');
INSERT INTO mochi.locale VALUES ('ps-AF');
INSERT INTO mochi.locale VALUES ('pt-BR');
INSERT INTO mochi.locale VALUES ('pt-PT');
INSERT INTO mochi.locale VALUES ('qut-GT');
INSERT INTO mochi.locale VALUES ('quz-BO');
INSERT INTO mochi.locale VALUES ('quz-EC');
INSERT INTO mochi.locale VALUES ('quz-PE');
INSERT INTO mochi.locale VALUES ('rm-CH');
INSERT INTO mochi.locale VALUES ('ro-RO');
INSERT INTO mochi.locale VALUES ('ru-RU');
INSERT INTO mochi.locale VALUES ('rw-RW');
INSERT INTO mochi.locale VALUES ('sah-RU');
INSERT INTO mochi.locale VALUES ('sa-IN');
INSERT INTO mochi.locale VALUES ('se-FI');
INSERT INTO mochi.locale VALUES ('se-NO');
INSERT INTO mochi.locale VALUES ('se-SE');
INSERT INTO mochi.locale VALUES ('si-LK');
INSERT INTO mochi.locale VALUES ('sk-SK');
INSERT INTO mochi.locale VALUES ('sl-SI');
INSERT INTO mochi.locale VALUES ('sma-NO');
INSERT INTO mochi.locale VALUES ('sma-SE');
INSERT INTO mochi.locale VALUES ('smj-NO');
INSERT INTO mochi.locale VALUES ('smj-SE');
INSERT INTO mochi.locale VALUES ('smn-FI');
INSERT INTO mochi.locale VALUES ('sms-FI');
INSERT INTO mochi.locale VALUES ('sq-AL');
INSERT INTO mochi.locale VALUES ('sr-Cyrl-BA');
INSERT INTO mochi.locale VALUES ('sr-Cyrl-CS');
INSERT INTO mochi.locale VALUES ('sr-Cyrl-ME');
INSERT INTO mochi.locale VALUES ('sr-Cyrl-RS');
INSERT INTO mochi.locale VALUES ('sr-Latn-BA');
INSERT INTO mochi.locale VALUES ('sr-Latn-CS');
INSERT INTO mochi.locale VALUES ('sr-Latn-ME');
INSERT INTO mochi.locale VALUES ('sr-Latn-RS');
INSERT INTO mochi.locale VALUES ('sv-FI');
INSERT INTO mochi.locale VALUES ('sv-SE');
INSERT INTO mochi.locale VALUES ('sw-KE');
INSERT INTO mochi.locale VALUES ('syr-SY');
INSERT INTO mochi.locale VALUES ('ta-IN');
INSERT INTO mochi.locale VALUES ('te-IN');
INSERT INTO mochi.locale VALUES ('tg-Cyrl-TJ');
INSERT INTO mochi.locale VALUES ('th-TH');
INSERT INTO mochi.locale VALUES ('tk-TM');
INSERT INTO mochi.locale VALUES ('tn-ZA');
INSERT INTO mochi.locale VALUES ('tr-TR');
INSERT INTO mochi.locale VALUES ('tt-RU');
INSERT INTO mochi.locale VALUES ('tzm-Latn-DZ');
INSERT INTO mochi.locale VALUES ('ug-CN');
INSERT INTO mochi.locale VALUES ('uk-UA');
INSERT INTO mochi.locale VALUES ('ur-PK');
INSERT INTO mochi.locale VALUES ('uz-Cyrl-UZ');
INSERT INTO mochi.locale VALUES ('uz-Latn-UZ');
INSERT INTO mochi.locale VALUES ('vi-VN');
INSERT INTO mochi.locale VALUES ('wo-SN');
INSERT INTO mochi.locale VALUES ('xh-ZA');
INSERT INTO mochi.locale VALUES ('yo-NG');
INSERT INTO mochi.locale VALUES ('zh-CN');
INSERT INTO mochi.locale VALUES ('zh-HK');
INSERT INTO mochi.locale VALUES ('zh-MO');
INSERT INTO mochi.locale VALUES ('zh-SG');
INSERT INTO mochi.locale VALUES ('zh-TW');
INSERT INTO mochi.locale VALUES ('zu-ZA');


--
-- Data for Name: brand_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.brand_attr_lcl VALUES (59, 32, 'Dole', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (60, 32, 'Dole', NULL, 'zh-HK');
INSERT INTO mochi.brand_attr_lcl VALUES (61, 33, 'Enza', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (62, 33, 'Enza', NULL, 'zh-HK');
INSERT INTO mochi.brand_attr_lcl VALUES (63, 34, 'Diva', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (64, 34, 'Diva', NULL, 'zh-HK');
INSERT INTO mochi.brand_attr_lcl VALUES (65, 35, 'Driscolls', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (66, 35, 'Driscolls', NULL, 'zh-HK');
INSERT INTO mochi.brand_attr_lcl VALUES (67, 36, 'Wonderful', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (68, 36, 'Wonderful', NULL, 'zh-HK');
INSERT INTO mochi.brand_attr_lcl VALUES (69, 37, 'Planters', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (70, 37, 'Planters', NULL, 'zh-HK');
INSERT INTO mochi.brand_attr_lcl VALUES (71, 38, 'Glorys', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (72, 38, 'Glorys', NULL, 'zh-HK');
INSERT INTO mochi.brand_attr_lcl VALUES (73, 39, 'Adora', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (74, 39, 'Adora', NULL, 'zh-HK');
INSERT INTO mochi.brand_attr_lcl VALUES (75, 40, 'Gold', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (76, 40, 'Gold', NULL, 'zh-HK');
INSERT INTO mochi.brand_attr_lcl VALUES (77, 41, 'Shine', NULL, 'en-GB');
INSERT INTO mochi.brand_attr_lcl VALUES (78, 41, 'Shine', NULL, 'zh-HK');


--
-- Name: brand_attr_lcl_bnd_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.brand_attr_lcl_bnd_id_seq', 78, true);


--
-- Name: brand_bnd_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.brand_bnd_id_seq', 41, true);


--
-- Data for Name: hierarchy; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.hierarchy VALUES (41, 'PRM01', 'Primary Product Category Hierarchy');
INSERT INTO mochi.hierarchy VALUES (42, 'SEC02', 'Secondary Product Category Hierarchy');
INSERT INTO mochi.hierarchy VALUES (43, 'BND01', 'Primary Product Brand Hierarchy');


--
-- Data for Name: category; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.category VALUES (43, 'BSP01', 39, 1, 1, 42);
INSERT INTO mochi.category VALUES (39, 'PRM02', NULL, 0, 1, 42);
INSERT INTO mochi.category VALUES (38, 'FET01', 39, 1, 1, 42);
INSERT INTO mochi.category VALUES (40, 'BND01', NULL, 0, 1, 43);
INSERT INTO mochi.category VALUES (42, 'OTH02', 40, 1, 1, 43);
INSERT INTO mochi.category VALUES (8, 'SVG01', 4, 2, 1, 41);
INSERT INTO mochi.category VALUES (16, 'NUT01', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (28, 'DUM02', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (36, 'REV01', 6, 3, 1, 41);
INSERT INTO mochi.category VALUES (15, 'TRO01', 3, 2, 1, 41);
INSERT INTO mochi.category VALUES (4, 'VEG01', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (30, 'DUM04', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (23, 'BRA01', 16, 2, 1, 41);
INSERT INTO mochi.category VALUES (20, 'ALM01', 16, 2, 1, 41);
INSERT INTO mochi.category VALUES (25, 'OFT01', 3, 2, 1, 41);
INSERT INTO mochi.category VALUES (26, 'ONT01', 16, 2, 1, 41);
INSERT INTO mochi.category VALUES (13, 'MEL01', 3, 2, 1, 41);
INSERT INTO mochi.category VALUES (22, 'PEC01', 16, 2, 1, 41);
INSERT INTO mochi.category VALUES (27, 'DUM01', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (21, 'PEA01', 16, 2, 1, 41);
INSERT INTO mochi.category VALUES (5, 'DGV01', 4, 2, 1, 41);
INSERT INTO mochi.category VALUES (11, 'DRU01', 3, 2, 1, 41);
INSERT INTO mochi.category VALUES (3, 'FRT01', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (14, 'CIT01', 3, 2, 1, 41);
INSERT INTO mochi.category VALUES (17, 'PIS01', 16, 2, 1, 41);
INSERT INTO mochi.category VALUES (33, 'DUM07', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (19, 'CAS01', 16, 2, 1, 41);
INSERT INTO mochi.category VALUES (31, 'DUM05', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (35, 'DUM09', 36, 4, 1, 41);
INSERT INTO mochi.category VALUES (37, 'ORV01', 6, 3, 1, 41);
INSERT INTO mochi.category VALUES (34, 'DUM08', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (32, 'DUM06', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (12, 'BER01', 3, 2, 1, 41);
INSERT INTO mochi.category VALUES (10, 'POM01', 3, 2, 1, 41);
INSERT INTO mochi.category VALUES (18, 'HAZ01', 16, 2, 1, 41);
INSERT INTO mochi.category VALUES (9, 'OTH01', 4, 2, 1, 41);
INSERT INTO mochi.category VALUES (24, 'MAC01', 16, 2, 1, 41);
INSERT INTO mochi.category VALUES (6, 'ROV01', 4, 2, 1, 41);
INSERT INTO mochi.category VALUES (29, 'DUM03', 2, 1, 1, 41);
INSERT INTO mochi.category VALUES (2, 'PRM01', NULL, 0, 1, 41);
INSERT INTO mochi.category VALUES (7, 'BAP01', 4, 2, 1, 41);
INSERT INTO mochi.category VALUES (44, 'UNK01', 39, 1, 1, 42);
INSERT INTO mochi.category VALUES (45, 'FBR01', 40, 0, 2, 43);


--
-- Data for Name: brand_category; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.brand_category VALUES (11, 32, 45);
INSERT INTO mochi.brand_category VALUES (12, 33, 45);
INSERT INTO mochi.brand_category VALUES (13, 34, 45);
INSERT INTO mochi.brand_category VALUES (14, 35, 45);


--
-- Name: brand_category_bnd_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.brand_category_bnd_cat_id_seq', 14, true);


--
-- Data for Name: category_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.category_attr_lcl VALUES (14, 3, '水果', '', 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (17, 5, '深綠色蔬菜', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (19, 6, '紅色和橙色蔬菜', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (21, 7, '豆類和豌豆', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (23, 8, '澱粉蔬菜', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (25, 9, '其他蔬菜', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (27, 10, '梨果', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (29, 11, '核果', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (31, 12, '漿果', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (33, 13, '瓜', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (35, 14, '柑橘', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (37, 15, '熱帶', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (39, 16, '堅果', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (41, 17, '開心果', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (43, 18, '榛子', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (45, 19, '腰果', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (47, 20, '杏仁', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (49, 21, '花生', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (51, 22, '胡桃', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (53, 23, '巴西', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (55, 24, '澳洲', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (57, 25, '其他水果', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (59, 26, '其他堅果', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (61, 27, '假 1', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (63, 28, '假 2', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (65, 29, '假 3', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (67, 30, '假 4', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (69, 31, '假 5', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (71, 32, '假 6', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (73, 33, '假 7', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (75, 34, '假 8', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (77, 35, '假 9', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (79, 36, '紅色蔬菜', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (81, 37, '橙色蔬菜', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (10, 2, 'ALL', '', 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (11, 3, 'Fruit', '', 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (20, 7, 'Beans and Peas', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (26, 10, 'Pomes', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (28, 11, 'Drupes', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (30, 12, 'Berries', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (32, 13, 'Melons', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (34, 14, 'Citrus', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (36, 15, 'Tropical', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (38, 16, 'Nuts', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (15, 4, '蔬菜', 'VEG01.jpg', 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (12, 4, 'Vegetables', 'VEG01.jpg', 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (40, 17, 'Pistachios', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (42, 18, 'Hazelnuts', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (44, 19, 'Cashews', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (46, 20, 'Almonds', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (48, 21, 'Peanuts', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (50, 22, 'Pecans', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (52, 23, 'Brazil', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (54, 24, 'Macadamia', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (56, 25, 'Other Fruit', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (58, 26, 'Other Nuts', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (60, 27, 'DUMMY 1', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (62, 28, 'DUMMY 2', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (64, 29, 'DUMMY 3', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (66, 30, 'DUMMY 4', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (68, 31, 'DUMMY 5', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (70, 32, 'DUMMY 6', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (72, 33, 'DUMMY 7', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (74, 34, 'DUMMY 8', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (76, 35, 'DUMMY 9', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (82, 38, 'Featured', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (83, 38, '精選', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (84, 39, 'MISC', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (85, 39, '雜', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (86, 40, 'All Brands', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (87, 40, '所有品牌', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (90, 42, 'Other Brands', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (99, 42, '其他品牌', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (100, 43, 'Best Sellers', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (101, 43, '最暢銷', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (16, 5, 'Dark Green', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (18, 6, 'Red and Orange', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (22, 8, 'Starchy', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (24, 9, 'Other', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (78, 36, 'Red', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (80, 37, 'Orange', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (102, 44, 'Unknown', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (103, 44, '未知', NULL, 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (13, 2, 'ALL', '', 'zh-HK');
INSERT INTO mochi.category_attr_lcl VALUES (104, 45, 'Featured Brands', NULL, 'en-GB');
INSERT INTO mochi.category_attr_lcl VALUES (105, 45, '推薦品牌', NULL, 'zh-HK');


--
-- Name: category_attr_lcl_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.category_attr_lcl_cat_id_seq', 21, true);


--
-- Data for Name: category_brand; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.category_brand VALUES (45);


--
-- Name: category_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.category_cat_id_seq', 7, true);


--
-- Data for Name: category_product; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.category_product VALUES (43);
INSERT INTO mochi.category_product VALUES (39);
INSERT INTO mochi.category_product VALUES (38);
INSERT INTO mochi.category_product VALUES (40);
INSERT INTO mochi.category_product VALUES (42);
INSERT INTO mochi.category_product VALUES (8);
INSERT INTO mochi.category_product VALUES (16);
INSERT INTO mochi.category_product VALUES (28);
INSERT INTO mochi.category_product VALUES (36);
INSERT INTO mochi.category_product VALUES (15);
INSERT INTO mochi.category_product VALUES (4);
INSERT INTO mochi.category_product VALUES (30);
INSERT INTO mochi.category_product VALUES (23);
INSERT INTO mochi.category_product VALUES (20);
INSERT INTO mochi.category_product VALUES (25);
INSERT INTO mochi.category_product VALUES (26);
INSERT INTO mochi.category_product VALUES (13);
INSERT INTO mochi.category_product VALUES (22);
INSERT INTO mochi.category_product VALUES (27);
INSERT INTO mochi.category_product VALUES (21);
INSERT INTO mochi.category_product VALUES (5);
INSERT INTO mochi.category_product VALUES (11);
INSERT INTO mochi.category_product VALUES (3);
INSERT INTO mochi.category_product VALUES (14);
INSERT INTO mochi.category_product VALUES (17);
INSERT INTO mochi.category_product VALUES (33);
INSERT INTO mochi.category_product VALUES (19);
INSERT INTO mochi.category_product VALUES (31);
INSERT INTO mochi.category_product VALUES (35);
INSERT INTO mochi.category_product VALUES (37);
INSERT INTO mochi.category_product VALUES (34);
INSERT INTO mochi.category_product VALUES (32);
INSERT INTO mochi.category_product VALUES (12);
INSERT INTO mochi.category_product VALUES (10);
INSERT INTO mochi.category_product VALUES (18);
INSERT INTO mochi.category_product VALUES (9);
INSERT INTO mochi.category_product VALUES (24);
INSERT INTO mochi.category_product VALUES (6);
INSERT INTO mochi.category_product VALUES (29);
INSERT INTO mochi.category_product VALUES (2);
INSERT INTO mochi.category_product VALUES (7);
INSERT INTO mochi.category_product VALUES (44);


--
-- Data for Name: category_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.category_type VALUES (1, 'PRD01', 'product');
INSERT INTO mochi.category_type VALUES (2, 'BND01', 'brand');
INSERT INTO mochi.category_type VALUES (3, 'PRM01', 'promotion');


--
-- Name: category_type_cat_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.category_type_cat_typ_id_seq', 2, true);


--
-- Data for Name: currency; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.currency VALUES (1, 'HKD');
INSERT INTO mochi.currency VALUES (2, 'USD');


--
-- Data for Name: party_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.party_type VALUES (1, 'Person');
INSERT INTO mochi.party_type VALUES (2, 'Organisation');


--
-- Data for Name: party; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.party VALUES (1, 1);
INSERT INTO mochi.party VALUES (232216, 1);
INSERT INTO mochi.party VALUES (232220, 1);
INSERT INTO mochi.party VALUES (232230, 1);
INSERT INTO mochi.party VALUES (232232, 1);
INSERT INTO mochi.party VALUES (232234, 1);
INSERT INTO mochi.party VALUES (232236, 1);
INSERT INTO mochi.party VALUES (232238, 1);
INSERT INTO mochi.party VALUES (232240, 1);
INSERT INTO mochi.party VALUES (232242, 1);
INSERT INTO mochi.party VALUES (232244, 1);
INSERT INTO mochi.party VALUES (232246, 1);
INSERT INTO mochi.party VALUES (232248, 1);
INSERT INTO mochi.party VALUES (232250, 1);
INSERT INTO mochi.party VALUES (232252, 1);
INSERT INTO mochi.party VALUES (232254, 1);
INSERT INTO mochi.party VALUES (232256, 1);
INSERT INTO mochi.party VALUES (232258, 1);
INSERT INTO mochi.party VALUES (232260, 1);
INSERT INTO mochi.party VALUES (232262, 1);
INSERT INTO mochi.party VALUES (232304, 1);
INSERT INTO mochi.party VALUES (232310, 1);
INSERT INTO mochi.party VALUES (232312, 1);


--
-- Data for Name: role_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.role_type VALUES (1, 'Customer');
INSERT INTO mochi.role_type VALUES (2, 'Supplier');


--
-- Data for Name: role; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.role VALUES (1, 1, '2018-11-15', 1);
INSERT INTO mochi.role VALUES (232217, 1, '2018-11-15', 232216);
INSERT INTO mochi.role VALUES (232221, 1, '2018-11-15', 232220);
INSERT INTO mochi.role VALUES (232231, 1, '2018-11-15', 232230);
INSERT INTO mochi.role VALUES (232233, 1, '2018-11-15', 232232);
INSERT INTO mochi.role VALUES (232235, 1, '2018-12-01', 232234);
INSERT INTO mochi.role VALUES (232237, 1, '2018-12-01', 232236);
INSERT INTO mochi.role VALUES (232239, 1, '2018-12-01', 232238);
INSERT INTO mochi.role VALUES (232241, 1, '2018-12-01', 232240);
INSERT INTO mochi.role VALUES (232243, 1, '2018-12-01', 232242);
INSERT INTO mochi.role VALUES (232245, 1, '2018-12-01', 232244);
INSERT INTO mochi.role VALUES (232247, 1, '2018-12-01', 232246);
INSERT INTO mochi.role VALUES (232249, 1, '2018-12-12', 232248);
INSERT INTO mochi.role VALUES (232251, 1, '2018-12-24', 232250);
INSERT INTO mochi.role VALUES (232253, 1, '2018-12-24', 232252);
INSERT INTO mochi.role VALUES (232255, 1, '2019-04-06', 232254);
INSERT INTO mochi.role VALUES (232257, 1, '2019-04-06', 232256);
INSERT INTO mochi.role VALUES (232259, 1, '2019-04-06', 232258);
INSERT INTO mochi.role VALUES (232261, 1, '2019-04-15', 232260);
INSERT INTO mochi.role VALUES (232263, 1, '2019-07-10', 232262);
INSERT INTO mochi.role VALUES (232305, 1, '2019-07-11', 232304);
INSERT INTO mochi.role VALUES (232311, 1, '2019-07-11', 232310);
INSERT INTO mochi.role VALUES (232313, 1, '2019-07-11', 232312);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.customer VALUES (1, '1000000011');
INSERT INTO mochi.customer VALUES (232217, '1000000026');
INSERT INTO mochi.customer VALUES (232221, '1000000028');
INSERT INTO mochi.customer VALUES (232231, '1000000033');
INSERT INTO mochi.customer VALUES (232233, '1000000034');
INSERT INTO mochi.customer VALUES (232235, '1000000035');
INSERT INTO mochi.customer VALUES (232237, '1000000036');
INSERT INTO mochi.customer VALUES (232239, '1000000037');
INSERT INTO mochi.customer VALUES (232241, '1000000038');
INSERT INTO mochi.customer VALUES (232243, '1000000039');
INSERT INTO mochi.customer VALUES (232245, '1000000040');
INSERT INTO mochi.customer VALUES (232247, '1000000041');
INSERT INTO mochi.customer VALUES (232249, '1000000042');
INSERT INTO mochi.customer VALUES (232251, '1000000043');
INSERT INTO mochi.customer VALUES (232253, '1000000044');
INSERT INTO mochi.customer VALUES (232255, '1000000045');
INSERT INTO mochi.customer VALUES (232257, '1000000046');
INSERT INTO mochi.customer VALUES (232259, '1000000047');
INSERT INTO mochi.customer VALUES (232261, '1000000048');
INSERT INTO mochi.customer VALUES (232263, '1000000049');
INSERT INTO mochi.customer VALUES (232305, '1000000070');
INSERT INTO mochi.customer VALUES (232311, '1000000073');
INSERT INTO mochi.customer VALUES (232313, '1000000074');


--
-- Name: customer_cst_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.customer_cst_id_seq', 1000000237, true);


--
-- Data for Name: discount; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: discount_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.discount_type VALUES (1, 'percentage');
INSERT INTO mochi.discount_type VALUES (2, 'value');


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.hibernate_sequence', 232784, true);


--
-- Name: hierarchy_hir_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.hierarchy_hir_id_seq', 43, true);


--
-- Data for Name: inventory_on_hand; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: inventory_transaction; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: layout; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.layout VALUES (1, 'LNDMM01', 'landing main menu 01');
INSERT INTO mochi.layout VALUES (2, 'LNDHC01', 'landing highlighted categories 01');
INSERT INTO mochi.layout VALUES (3, 'LNDPC01', 'landing preview categories 01');
INSERT INTO mochi.layout VALUES (4, 'LNDBS01', 'landing best selling products 01');
INSERT INTO mochi.layout VALUES (5, 'LNDHM01', 'landing header menu brands');


--
-- Data for Name: layout_category; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.layout_category VALUES (17, 1, 2);
INSERT INTO mochi.layout_category VALUES (18, 2, 38);
INSERT INTO mochi.layout_category VALUES (19, 3, 3);
INSERT INTO mochi.layout_category VALUES (20, 3, 4);
INSERT INTO mochi.layout_category VALUES (21, 4, 43);
INSERT INTO mochi.layout_category VALUES (22, 5, 45);


--
-- Name: layout_category_lay_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.layout_category_lay_cat_id_seq', 22, true);


--
-- Name: layout_lay_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.layout_lay_id_seq', 4, true);


--
-- Data for Name: location; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: order; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: product_status; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.product_status VALUES (1, 'ACT01', 'active');
INSERT INTO mochi.product_status VALUES (2, 'INA01', 'inactive');


--
-- Data for Name: product_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.product_type VALUES (1, 'NML01', 'retail product');


--
-- Data for Name: product; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.product VALUES (5, '12383658', '2019-01-04', 34, 1, 1);
INSERT INTO mochi.product VALUES (7, '14445678', '2019-01-04', 37, 1, 1);
INSERT INTO mochi.product VALUES (9, '12366678', '2019-01-04', 32, 1, 1);
INSERT INTO mochi.product VALUES (13, '76477789', '2019-01-04', 33, 1, 1);
INSERT INTO mochi.product VALUES (21, '18170258', '2019-02-05', 38, 1, 1);
INSERT INTO mochi.product VALUES (23, '17236024', '2019-02-05', 35, 1, 1);
INSERT INTO mochi.product VALUES (25, '15483827', '2019-02-05', 35, 1, 1);
INSERT INTO mochi.product VALUES (26, '13627671', '2019-02-05', 35, 1, 1);
INSERT INTO mochi.product VALUES (28, '16153782', '2019-02-05', 34, 1, 1);
INSERT INTO mochi.product VALUES (30, '16067775', '2019-02-05', 33, 1, 1);
INSERT INTO mochi.product VALUES (2, '23464789', '2019-01-04', 40, 1, 1);
INSERT INTO mochi.product VALUES (8, '25556789', '2019-01-04', 41, 1, 1);
INSERT INTO mochi.product VALUES (17, '18911676', '2019-02-05', 37, 1, 1);
INSERT INTO mochi.product VALUES (3, '19633678', '2019-01-04', 37, 1, 1);
INSERT INTO mochi.product VALUES (4, '23456645', '2019-01-04', 41, 1, 1);
INSERT INTO mochi.product VALUES (6, '23739283', '2019-01-04', 38, 1, 1);
INSERT INTO mochi.product VALUES (12, '64377789', '2019-01-04', 34, 1, 1);
INSERT INTO mochi.product VALUES (16, '18188784', '2019-02-05', 41, 1, 1);
INSERT INTO mochi.product VALUES (10, '23477789', '2019-01-04', 35, 1, 1);
INSERT INTO mochi.product VALUES (11, '3577789', '2019-01-04', 32, 1, 1);
INSERT INTO mochi.product VALUES (14, '13159658', '2019-02-05', 32, 1, 1);
INSERT INTO mochi.product VALUES (15, '15410595', '2019-02-05', 38, 1, 1);
INSERT INTO mochi.product VALUES (19, '10760430', '2019-02-05', 33, 1, 1);
INSERT INTO mochi.product VALUES (20, '10688155', '2019-02-05', 37, 1, 1);
INSERT INTO mochi.product VALUES (22, '17235347', '2019-02-05', 39, 1, 1);
INSERT INTO mochi.product VALUES (24, '19037164', '2019-02-05', 41, 1, 1);
INSERT INTO mochi.product VALUES (27, '15946292', '2019-02-05', 39, 1, 1);
INSERT INTO mochi.product VALUES (29, '17152401', '2019-02-05', 33, 1, 1);
INSERT INTO mochi.product VALUES (1, '12345678', '2019-01-04', 38, 1, 1);
INSERT INTO mochi.product VALUES (18, '17366878', '2019-02-05', 39, 1, 1);


--
-- Data for Name: order_line; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: organisation; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Name: party_party_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.party_party_id_seq', 1, false);


--
-- Name: party_pty_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.party_pty_id_seq', 1, false);


--
-- Name: party_type_pty_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.party_type_pty_typ_id_seq', 1, false);


--
-- Data for Name: person; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.person VALUES (1, 'Daniel', 'Mackie', true);
INSERT INTO mochi.person VALUES (232216, '', '', NULL);
INSERT INTO mochi.person VALUES (232220, '', '', NULL);
INSERT INTO mochi.person VALUES (232230, 'Will', 'Parkhouse', NULL);
INSERT INTO mochi.person VALUES (232232, 'Will', 'Parkhouse', NULL);
INSERT INTO mochi.person VALUES (232234, 'zoro', 'zoro', NULL);
INSERT INTO mochi.person VALUES (232236, 'j', 'j', NULL);
INSERT INTO mochi.person VALUES (232238, 'x', 'x', NULL);
INSERT INTO mochi.person VALUES (232240, 'z', 'z', NULL);
INSERT INTO mochi.person VALUES (232242, 'h', 'h', NULL);
INSERT INTO mochi.person VALUES (232244, 'i', 'i', NULL);
INSERT INTO mochi.person VALUES (232246, 'f', 'f', NULL);
INSERT INTO mochi.person VALUES (232248, 'Alexia', 'V', NULL);
INSERT INTO mochi.person VALUES (232250, 'marley', 'macculloch', NULL);
INSERT INTO mochi.person VALUES (232252, 'Layla', 'MacCulloch', NULL);
INSERT INTO mochi.person VALUES (232254, 'bill', 'bill', NULL);
INSERT INTO mochi.person VALUES (232256, 'dan', 'dan', NULL);
INSERT INTO mochi.person VALUES (232258, 'dan', 'dan', NULL);
INSERT INTO mochi.person VALUES (232260, 'nod', 'nod', NULL);
INSERT INTO mochi.person VALUES (232262, 'Daniel', 'Mackie', NULL);
INSERT INTO mochi.person VALUES (232304, 'Daniel', 'Mackie', NULL);
INSERT INTO mochi.person VALUES (232310, 'Daniel', 'Mackie', NULL);
INSERT INTO mochi.person VALUES (232312, 'Daniel', 'Mackie', NULL);


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.person_id_seq', 6, false);


--
-- Data for Name: price; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.price VALUES (1, 1, 1, 75, 1);
INSERT INTO mochi.price VALUES (2, 1, 2, 75, 1);
INSERT INTO mochi.price VALUES (3, 1, 3, 120, 1);
INSERT INTO mochi.price VALUES (4, 1, 4, 60, 1);
INSERT INTO mochi.price VALUES (5, 1, 5, 48, 1);
INSERT INTO mochi.price VALUES (6, 1, 6, 32, 1);
INSERT INTO mochi.price VALUES (7, 1, 7, 56, 1);
INSERT INTO mochi.price VALUES (8, 1, 8, 16, 1);
INSERT INTO mochi.price VALUES (9, 1, 9, 82, 1);
INSERT INTO mochi.price VALUES (10, 1, 10, 35, 1);
INSERT INTO mochi.price VALUES (11, 1, 11, 60, 1);
INSERT INTO mochi.price VALUES (12, 1, 12, 60, 1);
INSERT INTO mochi.price VALUES (13, 1, 13, 22, 1);
INSERT INTO mochi.price VALUES (14, 1, 14, 48, 1);
INSERT INTO mochi.price VALUES (15, 1, 15, 75, 1);
INSERT INTO mochi.price VALUES (16, 1, 16, 16, 1);
INSERT INTO mochi.price VALUES (17, 1, 17, 72, 1);
INSERT INTO mochi.price VALUES (18, 1, 18, 45, 1);
INSERT INTO mochi.price VALUES (19, 1, 19, 60, 1);
INSERT INTO mochi.price VALUES (20, 1, 20, 36, 1);
INSERT INTO mochi.price VALUES (21, 1, 21, 69, 1);
INSERT INTO mochi.price VALUES (22, 1, 22, 95, 1);
INSERT INTO mochi.price VALUES (23, 1, 23, 160, 1);
INSERT INTO mochi.price VALUES (24, 1, 24, 180, 1);
INSERT INTO mochi.price VALUES (25, 1, 25, 28, 1);
INSERT INTO mochi.price VALUES (26, 1, 26, 200, 1);
INSERT INTO mochi.price VALUES (27, 1, 27, 190, 1);
INSERT INTO mochi.price VALUES (28, 1, 28, 950, 1);
INSERT INTO mochi.price VALUES (29, 1, 29, 650, 1);
INSERT INTO mochi.price VALUES (30, 1, 30, 170, 1);
INSERT INTO mochi.price VALUES (31, 1, 1, 9.6, 2);
INSERT INTO mochi.price VALUES (32, 1, 2, 9.6, 2);
INSERT INTO mochi.price VALUES (33, 1, 3, 15.4, 2);
INSERT INTO mochi.price VALUES (34, 1, 4, 7.7, 2);
INSERT INTO mochi.price VALUES (35, 1, 5, 6.2, 2);
INSERT INTO mochi.price VALUES (36, 1, 6, 4.1, 2);
INSERT INTO mochi.price VALUES (37, 1, 7, 7.2, 2);
INSERT INTO mochi.price VALUES (38, 1, 8, 2.1, 2);
INSERT INTO mochi.price VALUES (39, 1, 9, 10.5, 2);
INSERT INTO mochi.price VALUES (40, 1, 10, 4.5, 2);
INSERT INTO mochi.price VALUES (41, 1, 11, 7.7, 2);
INSERT INTO mochi.price VALUES (42, 1, 12, 7.7, 2);
INSERT INTO mochi.price VALUES (43, 1, 13, 2.8, 2);
INSERT INTO mochi.price VALUES (44, 1, 14, 6.2, 2);
INSERT INTO mochi.price VALUES (45, 1, 15, 9.6, 2);
INSERT INTO mochi.price VALUES (46, 1, 16, 2.1, 2);
INSERT INTO mochi.price VALUES (47, 1, 17, 9.2, 2);
INSERT INTO mochi.price VALUES (48, 1, 18, 5.8, 2);
INSERT INTO mochi.price VALUES (49, 1, 19, 7.7, 2);
INSERT INTO mochi.price VALUES (50, 1, 20, 4.6, 2);
INSERT INTO mochi.price VALUES (51, 1, 21, 8.8, 2);
INSERT INTO mochi.price VALUES (52, 1, 22, 12.2, 2);
INSERT INTO mochi.price VALUES (53, 1, 23, 20.5, 2);
INSERT INTO mochi.price VALUES (54, 1, 24, 23.1, 2);
INSERT INTO mochi.price VALUES (55, 1, 25, 3.6, 2);
INSERT INTO mochi.price VALUES (56, 1, 26, 25.6, 2);
INSERT INTO mochi.price VALUES (57, 1, 27, 24.4, 2);
INSERT INTO mochi.price VALUES (58, 1, 28, 121.8, 2);
INSERT INTO mochi.price VALUES (59, 1, 29, 83.3, 2);
INSERT INTO mochi.price VALUES (60, 1, 30, 21.8, 2);
INSERT INTO mochi.price VALUES (61, 2, 1, 8.64, 2);
INSERT INTO mochi.price VALUES (62, 2, 2, 8.64, 2);
INSERT INTO mochi.price VALUES (63, 2, 3, 13.86, 2);
INSERT INTO mochi.price VALUES (64, 2, 4, 6.93, 2);
INSERT INTO mochi.price VALUES (65, 2, 5, 5.58, 2);
INSERT INTO mochi.price VALUES (66, 2, 6, 3.69, 2);
INSERT INTO mochi.price VALUES (67, 2, 7, 6.48, 2);
INSERT INTO mochi.price VALUES (68, 2, 8, 1.89, 2);
INSERT INTO mochi.price VALUES (69, 2, 9, 9.45, 2);
INSERT INTO mochi.price VALUES (70, 2, 10, 4.05, 2);
INSERT INTO mochi.price VALUES (71, 2, 11, 6.93, 2);
INSERT INTO mochi.price VALUES (72, 2, 12, 6.93, 2);
INSERT INTO mochi.price VALUES (73, 2, 13, 2.52, 2);
INSERT INTO mochi.price VALUES (74, 2, 14, 5.58, 2);
INSERT INTO mochi.price VALUES (75, 2, 15, 8.64, 2);
INSERT INTO mochi.price VALUES (76, 2, 16, 1.89, 2);
INSERT INTO mochi.price VALUES (77, 2, 17, 8.28, 2);
INSERT INTO mochi.price VALUES (78, 2, 18, 5.22, 2);
INSERT INTO mochi.price VALUES (79, 2, 19, 6.93, 2);
INSERT INTO mochi.price VALUES (80, 2, 20, 4.14, 2);
INSERT INTO mochi.price VALUES (81, 2, 21, 7.92, 2);
INSERT INTO mochi.price VALUES (82, 2, 22, 10.98, 2);
INSERT INTO mochi.price VALUES (83, 2, 23, 18.45, 2);
INSERT INTO mochi.price VALUES (84, 2, 24, 20.79, 2);
INSERT INTO mochi.price VALUES (85, 2, 25, 3.24, 2);
INSERT INTO mochi.price VALUES (86, 2, 26, 23.04, 2);
INSERT INTO mochi.price VALUES (87, 2, 27, 21.96, 2);
INSERT INTO mochi.price VALUES (88, 2, 28, 109.62, 2);
INSERT INTO mochi.price VALUES (89, 2, 29, 74.97, 2);
INSERT INTO mochi.price VALUES (90, 2, 30, 19.62, 2);
INSERT INTO mochi.price VALUES (91, 2, 1, 67.50, 1);
INSERT INTO mochi.price VALUES (92, 2, 2, 67.50, 1);
INSERT INTO mochi.price VALUES (93, 2, 3, 108.00, 1);
INSERT INTO mochi.price VALUES (94, 2, 4, 54.00, 1);
INSERT INTO mochi.price VALUES (95, 2, 5, 43.20, 1);
INSERT INTO mochi.price VALUES (96, 2, 6, 28.80, 1);
INSERT INTO mochi.price VALUES (97, 2, 7, 50.40, 1);
INSERT INTO mochi.price VALUES (98, 2, 8, 14.40, 1);
INSERT INTO mochi.price VALUES (99, 2, 9, 73.80, 1);
INSERT INTO mochi.price VALUES (100, 2, 10, 31.50, 1);
INSERT INTO mochi.price VALUES (101, 2, 11, 54.00, 1);
INSERT INTO mochi.price VALUES (102, 2, 12, 54.00, 1);
INSERT INTO mochi.price VALUES (103, 2, 13, 19.80, 1);
INSERT INTO mochi.price VALUES (104, 2, 14, 43.20, 1);
INSERT INTO mochi.price VALUES (105, 2, 15, 67.50, 1);
INSERT INTO mochi.price VALUES (106, 2, 16, 14.40, 1);
INSERT INTO mochi.price VALUES (107, 2, 17, 64.80, 1);
INSERT INTO mochi.price VALUES (108, 2, 18, 40.50, 1);
INSERT INTO mochi.price VALUES (109, 2, 19, 54.00, 1);
INSERT INTO mochi.price VALUES (110, 2, 20, 32.40, 1);
INSERT INTO mochi.price VALUES (111, 2, 21, 62.10, 1);
INSERT INTO mochi.price VALUES (112, 2, 22, 85.50, 1);
INSERT INTO mochi.price VALUES (113, 2, 23, 144.00, 1);
INSERT INTO mochi.price VALUES (114, 2, 24, 162.00, 1);
INSERT INTO mochi.price VALUES (115, 2, 25, 25.20, 1);
INSERT INTO mochi.price VALUES (116, 2, 26, 180.00, 1);
INSERT INTO mochi.price VALUES (117, 2, 27, 171.00, 1);
INSERT INTO mochi.price VALUES (118, 2, 28, 855.00, 1);
INSERT INTO mochi.price VALUES (119, 2, 29, 585.00, 1);
INSERT INTO mochi.price VALUES (120, 2, 30, 153.00, 1);


--
-- Name: price_prc_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.price_prc_id_seq', 120, true);


--
-- Data for Name: price_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.price_type VALUES (1, 'retail', 'RET01');
INSERT INTO mochi.price_type VALUES (2, 'markdown', 'MKD01');


--
-- Data for Name: product_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.product_attr_lcl VALUES (2, 1, '芒果', 'mango.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (4, 2, '橙子', 'orange.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (6, 3, '西兰花', 'broccoli.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (8, 4, '菜花', 'cauliflower.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (10, 5, '黄瓜', 'cucumber.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (12, 6, '红菜头', 'beetroot.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (14, 7, '胡萝卜', 'carrots.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (16, 8, '番茄', 'tomato.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (18, 9, '豆子', 'beans.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (20, 10, '茄子', 'brinjal.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (22, 11, '辣椒', 'capsicum.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (24, 12, '蘑菇', 'button-mushroom.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (26, 13, '土豆', 'potato.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (28, 14, '南瓜', 'pumpkin.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (30, 15, '玉米', 'corn.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (32, 16, '洋蔥', 'onion.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (34, 17, '蘋果', 'apple.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (36, 18, '香蕉', 'banana.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (38, 19, '葡萄', 'grapes.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (40, 20, '麝香甜瓜', 'musk-melon.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (42, 21, '梨', 'pears.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (44, 22, '石榴', 'pomegranate.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (46, 23, '覆盆子', 'raspberry.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (48, 24, '草莓', 'strawberry.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (50, 25, '西瓜', 'water-melon.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (52, 26, '杏仁', 'almonds.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (54, 27, '開心果', 'pistachio.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (56, 28, '堅果混合物', 'nuts-mixture.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (58, 29, '腰果', 'cashews.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (60, 30, '核桃', 'walnuts.jpg', 'zh-HK');
INSERT INTO mochi.product_attr_lcl VALUES (1, 1, 'mango', 'mango.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (3, 2, 'orange', 'orange.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (5, 3, 'brocolli', 'broccoli.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (7, 4, 'cauliflower', 'cauliflower.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (9, 5, 'cucumber', 'cucumber.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (11, 6, 'beetroot', 'beetroot.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (13, 7, 'carrot', 'carrots.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (15, 8, 'tomato', 'tomato.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (17, 9, 'beans', 'beans.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (19, 10, 'brinjal', 'brinjal.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (21, 11, 'capsicum', 'capsicum.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (23, 12, 'mushroom', 'button-mushroom.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (25, 13, 'potato', 'potato.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (27, 14, 'Pumpkin', 'pumpkin.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (29, 15, 'Corn', 'corn.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (31, 16, 'Onion', 'onion.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (33, 17, 'Apple', 'apple.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (35, 18, 'Banana', 'banana.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (37, 19, 'Grapes', 'grapes.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (39, 20, 'Musk Melon', 'musk-melon.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (41, 21, 'Pears', 'pears.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (43, 22, 'Pomegranate', 'pomegranate.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (47, 24, 'Strawberry', 'strawberry.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (49, 25, 'Water Melon', 'water-melon.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (51, 26, 'Almonds', 'almonds.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (53, 27, 'Pistachio', 'pistachio.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (55, 28, 'Nuts Mixture', 'nuts-mixture.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (57, 29, 'Cashews', 'cashews.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (59, 30, 'Walnuts', 'walnuts.jpg', 'en-GB');
INSERT INTO mochi.product_attr_lcl VALUES (45, 23, 'RaspberryDan', 'raspberry.jpg', 'en-GB');


--
-- Data for Name: product_category; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.product_category VALUES (39, 1, 15);
INSERT INTO mochi.product_category VALUES (40, 2, 14);
INSERT INTO mochi.product_category VALUES (41, 3, 5);
INSERT INTO mochi.product_category VALUES (42, 4, 8);
INSERT INTO mochi.product_category VALUES (43, 5, 5);
INSERT INTO mochi.product_category VALUES (44, 6, 6);
INSERT INTO mochi.product_category VALUES (45, 7, 8);
INSERT INTO mochi.product_category VALUES (46, 8, 14);
INSERT INTO mochi.product_category VALUES (47, 9, 7);
INSERT INTO mochi.product_category VALUES (48, 10, 9);
INSERT INTO mochi.product_category VALUES (50, 12, 9);
INSERT INTO mochi.product_category VALUES (51, 13, 8);
INSERT INTO mochi.product_category VALUES (52, 14, 8);
INSERT INTO mochi.product_category VALUES (53, 15, 9);
INSERT INTO mochi.product_category VALUES (54, 16, 9);
INSERT INTO mochi.product_category VALUES (55, 17, 10);
INSERT INTO mochi.product_category VALUES (56, 18, 15);
INSERT INTO mochi.product_category VALUES (57, 19, 14);
INSERT INTO mochi.product_category VALUES (58, 20, 13);
INSERT INTO mochi.product_category VALUES (59, 21, 10);
INSERT INTO mochi.product_category VALUES (60, 22, 10);
INSERT INTO mochi.product_category VALUES (61, 23, 12);
INSERT INTO mochi.product_category VALUES (62, 24, 12);
INSERT INTO mochi.product_category VALUES (63, 25, 13);
INSERT INTO mochi.product_category VALUES (64, 26, 20);
INSERT INTO mochi.product_category VALUES (65, 27, 17);
INSERT INTO mochi.product_category VALUES (66, 28, 26);
INSERT INTO mochi.product_category VALUES (67, 29, 19);
INSERT INTO mochi.product_category VALUES (68, 30, 26);
INSERT INTO mochi.product_category VALUES (69, 1, 38);
INSERT INTO mochi.product_category VALUES (70, 10, 38);
INSERT INTO mochi.product_category VALUES (71, 11, 38);
INSERT INTO mochi.product_category VALUES (72, 14, 38);
INSERT INTO mochi.product_category VALUES (73, 15, 38);
INSERT INTO mochi.product_category VALUES (74, 18, 38);
INSERT INTO mochi.product_category VALUES (75, 19, 38);
INSERT INTO mochi.product_category VALUES (76, 20, 38);
INSERT INTO mochi.product_category VALUES (77, 22, 38);
INSERT INTO mochi.product_category VALUES (78, 24, 38);
INSERT INTO mochi.product_category VALUES (79, 27, 38);
INSERT INTO mochi.product_category VALUES (80, 29, 38);
INSERT INTO mochi.product_category VALUES (81, 2, 43);
INSERT INTO mochi.product_category VALUES (82, 14, 43);
INSERT INTO mochi.product_category VALUES (83, 18, 43);
INSERT INTO mochi.product_category VALUES (84, 22, 43);
INSERT INTO mochi.product_category VALUES (85, 12, 43);
INSERT INTO mochi.product_category VALUES (86, 16, 43);
INSERT INTO mochi.product_category VALUES (87, 26, 43);
INSERT INTO mochi.product_category VALUES (88, 6, 43);
INSERT INTO mochi.product_category VALUES (89, 20, 43);
INSERT INTO mochi.product_category VALUES (90, 5, 43);


--
-- Name: product_category_prd_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.product_category_prd_cat_id_seq', 102, true);


--
-- Name: product_status_prd_sts_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.product_status_prd_sts_id_seq', 1, false);


--
-- Data for Name: product_supplier; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: tag; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.tag VALUES (15, 'ORG01');
INSERT INTO mochi.tag VALUES (17, 'GFR01');


--
-- Data for Name: product_tag; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.product_tag VALUES (25, 10, 17);
INSERT INTO mochi.product_tag VALUES (26, 11, 15);
INSERT INTO mochi.product_tag VALUES (27, 22, 17);
INSERT INTO mochi.product_tag VALUES (30, 7, 17);
INSERT INTO mochi.product_tag VALUES (31, 2, 15);
INSERT INTO mochi.product_tag VALUES (32, 5, 17);
INSERT INTO mochi.product_tag VALUES (34, 13, 17);


--
-- Name: product_tag_attr_lcl_tag_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.product_tag_attr_lcl_tag_id_seq', 1, false);


--
-- Name: product_tag_prd_tag_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.product_tag_prd_tag_id_seq', 34, true);


--
-- Name: product_type_prd_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.product_type_prd_typ_id_seq', 1, false);


--
-- Data for Name: promotion; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: promotion_brand; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: promotion_category; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Name: promotion_category_prm_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.promotion_category_prm_cat_id_seq', 1, false);


--
-- Data for Name: promotion_order; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: promotion_product; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: promotion_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.promotion_type VALUES (1, 'product');
INSERT INTO mochi.promotion_type VALUES (2, 'order');
INSERT INTO mochi.promotion_type VALUES (3, 'category');
INSERT INTO mochi.promotion_type VALUES (4, 'brand');


--
-- Name: role_rle_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.role_rle_id_seq', 1, false);


--
-- Name: role_type_rle_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.role_type_rle_typ_id_seq', 1, false);


--
-- Name: role_type_role_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.role_type_role_typ_id_seq', 1, false);


--
-- Data for Name: supplier; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: tag_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO mochi.tag_attr_lcl VALUES (1, 15, 'Organic', NULL, 'en-GB');
INSERT INTO mochi.tag_attr_lcl VALUES (3, 17, 'Gluten Free', NULL, 'en-GB');
INSERT INTO mochi.tag_attr_lcl VALUES (2, 15, '有機', NULL, 'zh-HK');
INSERT INTO mochi.tag_attr_lcl VALUES (4, 17, '不含麩質', NULL, 'zh-HK');


--
-- Name: tag_attr_lcl_tag_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.tag_attr_lcl_tag_id_seq', 4, true);


--
-- Name: tag_tag_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('mochi.tag_tag_id_seq', 18, true);


--
-- PostgreSQL database dump complete
--

