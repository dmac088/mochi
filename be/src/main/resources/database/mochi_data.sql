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

--
-- Data for Name: department; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO department VALUES (1, 'SHP01', 'Shipping');
INSERT INTO department VALUES (2, 'ACC01', 'ProductBasic');


--
-- Data for Name: locale; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO locale VALUES ('af-ZA');
INSERT INTO locale VALUES ('am-ET');
INSERT INTO locale VALUES ('ar-AE');
INSERT INTO locale VALUES ('ar-BH');
INSERT INTO locale VALUES ('ar-DZ');
INSERT INTO locale VALUES ('ar-EG');
INSERT INTO locale VALUES ('ar-IQ');
INSERT INTO locale VALUES ('ar-JO');
INSERT INTO locale VALUES ('ar-KW');
INSERT INTO locale VALUES ('ar-LB');
INSERT INTO locale VALUES ('ar-LY');
INSERT INTO locale VALUES ('ar-MA');
INSERT INTO locale VALUES ('arn-CL');
INSERT INTO locale VALUES ('ar-OM');
INSERT INTO locale VALUES ('ar-QA');
INSERT INTO locale VALUES ('ar-SA');
INSERT INTO locale VALUES ('ar-SY');
INSERT INTO locale VALUES ('ar-TN');
INSERT INTO locale VALUES ('ar-YE');
INSERT INTO locale VALUES ('as-IN');
INSERT INTO locale VALUES ('az-Cyrl-AZ');
INSERT INTO locale VALUES ('az-Latn-AZ');
INSERT INTO locale VALUES ('ba-RU');
INSERT INTO locale VALUES ('be-BY');
INSERT INTO locale VALUES ('bg-BG');
INSERT INTO locale VALUES ('bn-BD');
INSERT INTO locale VALUES ('bn-IN');
INSERT INTO locale VALUES ('bo-CN');
INSERT INTO locale VALUES ('br-FR');
INSERT INTO locale VALUES ('bs-Cyrl-BA');
INSERT INTO locale VALUES ('bs-Latn-BA');
INSERT INTO locale VALUES ('ca-ES');
INSERT INTO locale VALUES ('co-FR');
INSERT INTO locale VALUES ('cs-CZ');
INSERT INTO locale VALUES ('cy-GB');
INSERT INTO locale VALUES ('da-DK');
INSERT INTO locale VALUES ('de-AT');
INSERT INTO locale VALUES ('de-CH');
INSERT INTO locale VALUES ('de-DE');
INSERT INTO locale VALUES ('de-LI');
INSERT INTO locale VALUES ('de-LU');
INSERT INTO locale VALUES ('dsb-DE');
INSERT INTO locale VALUES ('dv-MV');
INSERT INTO locale VALUES ('el-GR');
INSERT INTO locale VALUES ('en-029');
INSERT INTO locale VALUES ('en-AU');
INSERT INTO locale VALUES ('en-BZ');
INSERT INTO locale VALUES ('en-CA');
INSERT INTO locale VALUES ('en-GB');
INSERT INTO locale VALUES ('en-IE');
INSERT INTO locale VALUES ('en-IN');
INSERT INTO locale VALUES ('en-JM');
INSERT INTO locale VALUES ('en-MY');
INSERT INTO locale VALUES ('en-NZ');
INSERT INTO locale VALUES ('en-PH');
INSERT INTO locale VALUES ('en-SG');
INSERT INTO locale VALUES ('en-TT');
INSERT INTO locale VALUES ('en-US');
INSERT INTO locale VALUES ('en-ZA');
INSERT INTO locale VALUES ('en-ZW');
INSERT INTO locale VALUES ('es-AR');
INSERT INTO locale VALUES ('es-BO');
INSERT INTO locale VALUES ('es-CL');
INSERT INTO locale VALUES ('es-CO');
INSERT INTO locale VALUES ('es-CR');
INSERT INTO locale VALUES ('es-DO');
INSERT INTO locale VALUES ('es-EC');
INSERT INTO locale VALUES ('es-ES');
INSERT INTO locale VALUES ('es-GT');
INSERT INTO locale VALUES ('es-HN');
INSERT INTO locale VALUES ('es-MX');
INSERT INTO locale VALUES ('es-NI');
INSERT INTO locale VALUES ('es-PA');
INSERT INTO locale VALUES ('es-PE');
INSERT INTO locale VALUES ('es-PR');
INSERT INTO locale VALUES ('es-PY');
INSERT INTO locale VALUES ('es-SV');
INSERT INTO locale VALUES ('es-US');
INSERT INTO locale VALUES ('es-UY');
INSERT INTO locale VALUES ('es-VE');
INSERT INTO locale VALUES ('et-EE');
INSERT INTO locale VALUES ('eu-ES');
INSERT INTO locale VALUES ('fa-IR');
INSERT INTO locale VALUES ('fi-FI');
INSERT INTO locale VALUES ('fil-PH');
INSERT INTO locale VALUES ('fo-FO');
INSERT INTO locale VALUES ('fr-BE');
INSERT INTO locale VALUES ('fr-CA');
INSERT INTO locale VALUES ('fr-CH');
INSERT INTO locale VALUES ('fr-FR');
INSERT INTO locale VALUES ('fr-LU');
INSERT INTO locale VALUES ('fr-MC');
INSERT INTO locale VALUES ('fy-NL');
INSERT INTO locale VALUES ('ga-IE');
INSERT INTO locale VALUES ('gd-GB');
INSERT INTO locale VALUES ('gl-ES');
INSERT INTO locale VALUES ('gsw-FR');
INSERT INTO locale VALUES ('gu-IN');
INSERT INTO locale VALUES ('ha-Latn-NG');
INSERT INTO locale VALUES ('he-IL');
INSERT INTO locale VALUES ('hi-IN');
INSERT INTO locale VALUES ('hr-BA');
INSERT INTO locale VALUES ('hr-HR');
INSERT INTO locale VALUES ('hsb-DE');
INSERT INTO locale VALUES ('hu-HU');
INSERT INTO locale VALUES ('hy-AM');
INSERT INTO locale VALUES ('id-ID');
INSERT INTO locale VALUES ('ig-NG');
INSERT INTO locale VALUES ('ii-CN');
INSERT INTO locale VALUES ('is-IS');
INSERT INTO locale VALUES ('it-CH');
INSERT INTO locale VALUES ('it-IT');
INSERT INTO locale VALUES ('iu-Cans-CA');
INSERT INTO locale VALUES ('iu-Latn-CA');
INSERT INTO locale VALUES ('ja-JP');
INSERT INTO locale VALUES ('ka-GE');
INSERT INTO locale VALUES ('kk-KZ');
INSERT INTO locale VALUES ('kl-GL');
INSERT INTO locale VALUES ('km-KH');
INSERT INTO locale VALUES ('kn-IN');
INSERT INTO locale VALUES ('kok-IN');
INSERT INTO locale VALUES ('ko-KR');
INSERT INTO locale VALUES ('ky-KG');
INSERT INTO locale VALUES ('lb-LU');
INSERT INTO locale VALUES ('lo-LA');
INSERT INTO locale VALUES ('lt-LT');
INSERT INTO locale VALUES ('lv-LV');
INSERT INTO locale VALUES ('mi-NZ');
INSERT INTO locale VALUES ('mk-MK');
INSERT INTO locale VALUES ('ml-IN');
INSERT INTO locale VALUES ('mn-MN');
INSERT INTO locale VALUES ('mn-Mong-CN');
INSERT INTO locale VALUES ('moh-CA');
INSERT INTO locale VALUES ('mr-IN');
INSERT INTO locale VALUES ('ms-BN');
INSERT INTO locale VALUES ('ms-MY');
INSERT INTO locale VALUES ('mt-MT');
INSERT INTO locale VALUES ('nb-NO');
INSERT INTO locale VALUES ('ne-NP');
INSERT INTO locale VALUES ('nl-BE');
INSERT INTO locale VALUES ('nl-NL');
INSERT INTO locale VALUES ('nn-NO');
INSERT INTO locale VALUES ('nso-ZA');
INSERT INTO locale VALUES ('oc-FR');
INSERT INTO locale VALUES ('or-IN');
INSERT INTO locale VALUES ('pa-IN');
INSERT INTO locale VALUES ('pl-PL');
INSERT INTO locale VALUES ('prs-AF');
INSERT INTO locale VALUES ('ps-AF');
INSERT INTO locale VALUES ('pt-BR');
INSERT INTO locale VALUES ('pt-PT');
INSERT INTO locale VALUES ('qut-GT');
INSERT INTO locale VALUES ('quz-BO');
INSERT INTO locale VALUES ('quz-EC');
INSERT INTO locale VALUES ('quz-PE');
INSERT INTO locale VALUES ('rm-CH');
INSERT INTO locale VALUES ('ro-RO');
INSERT INTO locale VALUES ('ru-RU');
INSERT INTO locale VALUES ('rw-RW');
INSERT INTO locale VALUES ('sah-RU');
INSERT INTO locale VALUES ('sa-IN');
INSERT INTO locale VALUES ('se-FI');
INSERT INTO locale VALUES ('se-NO');
INSERT INTO locale VALUES ('se-SE');
INSERT INTO locale VALUES ('si-LK');
INSERT INTO locale VALUES ('sk-SK');
INSERT INTO locale VALUES ('sl-SI');
INSERT INTO locale VALUES ('sma-NO');
INSERT INTO locale VALUES ('sma-SE');
INSERT INTO locale VALUES ('smj-NO');
INSERT INTO locale VALUES ('smj-SE');
INSERT INTO locale VALUES ('smn-FI');
INSERT INTO locale VALUES ('sms-FI');
INSERT INTO locale VALUES ('sq-AL');
INSERT INTO locale VALUES ('sr-Cyrl-BA');
INSERT INTO locale VALUES ('sr-Cyrl-CS');
INSERT INTO locale VALUES ('sr-Cyrl-ME');
INSERT INTO locale VALUES ('sr-Cyrl-RS');
INSERT INTO locale VALUES ('sr-Latn-BA');
INSERT INTO locale VALUES ('sr-Latn-CS');
INSERT INTO locale VALUES ('sr-Latn-ME');
INSERT INTO locale VALUES ('sr-Latn-RS');
INSERT INTO locale VALUES ('sv-FI');
INSERT INTO locale VALUES ('sv-SE');
INSERT INTO locale VALUES ('sw-KE');
INSERT INTO locale VALUES ('syr-SY');
INSERT INTO locale VALUES ('ta-IN');
INSERT INTO locale VALUES ('te-IN');
INSERT INTO locale VALUES ('tg-Cyrl-TJ');
INSERT INTO locale VALUES ('th-TH');
INSERT INTO locale VALUES ('tk-TM');
INSERT INTO locale VALUES ('tn-ZA');
INSERT INTO locale VALUES ('tr-TR');
INSERT INTO locale VALUES ('tt-RU');
INSERT INTO locale VALUES ('tzm-Latn-DZ');
INSERT INTO locale VALUES ('ug-CN');
INSERT INTO locale VALUES ('uk-UA');
INSERT INTO locale VALUES ('ur-PK');
INSERT INTO locale VALUES ('uz-Cyrl-UZ');
INSERT INTO locale VALUES ('uz-Latn-UZ');
INSERT INTO locale VALUES ('vi-VN');
INSERT INTO locale VALUES ('wo-SN');
INSERT INTO locale VALUES ('xh-ZA');
INSERT INTO locale VALUES ('yo-NG');
INSERT INTO locale VALUES ('zh-CN');
INSERT INTO locale VALUES ('zh-HK');
INSERT INTO locale VALUES ('zh-MO');
INSERT INTO locale VALUES ('zh-SG');
INSERT INTO locale VALUES ('zh-TW');
INSERT INTO locale VALUES ('zu-ZA');


--
-- Data for Name: product_status; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO product_status VALUES (1, 'ACT01', 'active');
INSERT INTO product_status VALUES (2, 'INA01', 'inactive');


--
-- Data for Name: product; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO product VALUES (11, '3577789', '2019-01-04 00:00:00+08', 32, 2, 1);
INSERT INTO product VALUES (233275, '30833030', '2020-03-07 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233282, '30833031', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233289, '30833032', '2020-03-07 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233296, '30833033', '2020-03-07 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233303, '30833034', '2020-03-07 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233310, '30833035', '2020-03-07 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233317, '30833036', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233324, '30833037', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233331, '30833038', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233338, '30833039', '2020-07-18 00:00:00+08', 233203, 2, 1);
INSERT INTO product VALUES (233345, '30833040', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233352, '30833041', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233359, '30833042', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233366, '30833043', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233373, '30833044', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233380, '30833045', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233387, '30833046', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233394, '30833047', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233401, '30833048', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233408, '30833049', '2020-07-18 00:00:00+08', 233200, 2, 1);
INSERT INTO product VALUES (233415, '30833050', '2020-07-18 00:00:00+08', 233200, 2, 1);
INSERT INTO product VALUES (233422, '30833051', '2020-07-18 00:00:00+08', 233200, 2, 1);
INSERT INTO product VALUES (233429, '30833052', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233436, '30833029', '2020-02-08 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233443, '30833053', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233450, '30833054', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233457, '30833055', '2020-07-18 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233464, '30833056', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233471, '30833057', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233478, '30833058', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233485, '30833059', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233492, '30833060', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233499, '30833061', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233506, '30833062', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233513, '30833063', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233520, '30833064', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233527, '30833065', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233534, '30833066', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233541, '30833067', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233548, '30833068', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233555, '30833069', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233562, '30833070', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233569, '30833071', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233576, '30833072', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233583, '30833073', '2020-07-18 00:00:00+08', 233209, 2, 1);
INSERT INTO product VALUES (233590, '30833074', '2020-07-18 00:00:00+08', 233209, 2, 1);
INSERT INTO product VALUES (233597, '30833075', '2020-07-18 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233604, '30833076', '2020-02-08 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233611, '30833077', '2020-02-08 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233618, '30833078', '2020-02-08 00:00:00+08', 233212, 2, 1);
INSERT INTO product VALUES (233625, '30833079', '2020-02-08 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233632, '30833080', '2020-02-08 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233639, '30833081', '2020-02-08 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233646, '30833082', '2020-02-08 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233653, '30833083', '2020-02-08 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233660, '30833084', '2020-02-08 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233667, '30833085', '2020-02-09 00:00:00+08', 233206, 2, 1);
INSERT INTO product VALUES (233674, '30833086', '2020-02-10 00:00:00+08', 233215, 2, 1);
INSERT INTO product VALUES (233681, '30833087', '2020-02-11 00:00:00+08', 233215, 2, 1);
INSERT INTO product VALUES (233688, '30833088', '2020-02-12 00:00:00+08', 233215, 2, 1);
INSERT INTO product VALUES (233695, '30833089', '2020-02-13 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233702, '30833090', '2020-02-14 00:00:00+08', 233215, 2, 1);
INSERT INTO product VALUES (233709, '30833091', '2020-02-15 00:00:00+08', 233215, 2, 1);
INSERT INTO product VALUES (233716, '30833092', '2020-02-16 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233723, '30833093', '2020-02-17 00:00:00+08', 233191, 2, 1);
INSERT INTO product VALUES (233730, '30833094', '2020-02-18 00:00:00+08', 233215, 2, 1);
INSERT INTO product VALUES (14, '13159658', '2020-02-20 00:00:00+08', 32, 2, 1);
INSERT INTO product VALUES (20, '10688155', '2020-02-21 00:00:00+08', 37, 2, 1);
INSERT INTO product VALUES (17, '18911676', '2020-02-22 00:00:00+08', 37, 2, 1);
INSERT INTO product VALUES (7, '14445678', '2020-02-23 00:00:00+08', 37, 2, 1);
INSERT INTO product VALUES (12, '64377789', '2020-02-24 00:00:00+08', 34, 2, 1);
INSERT INTO product VALUES (29, '17152401', '2020-02-25 00:00:00+08', 33, 2, 1);
INSERT INTO product VALUES (30, '16067775', '2020-02-26 00:00:00+08', 33, 2, 1);
INSERT INTO product VALUES (28, '16153782', '2020-02-27 00:00:00+08', 34, 2, 1);
INSERT INTO product VALUES (2, '23464789', '2020-02-28 00:00:00+08', 40, 2, 1);
INSERT INTO product VALUES (15, '15410595', '2020-02-29 00:00:00+08', 38, 2, 1);
INSERT INTO product VALUES (10, '23477789', '2020-03-01 00:00:00+08', 35, 2, 1);
INSERT INTO product VALUES (27, '15946292', '2020-03-02 00:00:00+08', 39, 2, 1);
INSERT INTO product VALUES (21, '18170258', '2020-03-03 00:00:00+08', 38, 2, 1);
INSERT INTO product VALUES (26, '13627671', '2020-03-04 00:00:00+08', 35, 2, 1);
INSERT INTO product VALUES (24, '19037164', '2020-03-05 00:00:00+08', 41, 2, 1);
INSERT INTO product VALUES (25, '15483827', '2020-03-06 00:00:00+08', 35, 2, 1);
INSERT INTO product VALUES (16, '18188784', '2020-03-07 00:00:00+08', 41, 2, 1);
INSERT INTO product VALUES (6, '23739283', '2020-03-08 00:00:00+08', 38, 2, 1);
INSERT INTO product VALUES (22, '17235347', '2020-03-09 00:00:00+08', 39, 2, 1);
INSERT INTO product VALUES (23, '17236024', '2020-03-10 00:00:00+08', 35, 2, 1);
INSERT INTO product VALUES (3, '19633678', '2020-03-11 00:00:00+08', 37, 2, 1);
INSERT INTO product VALUES (9, '12366678', '2020-03-12 00:00:00+08', 32, 2, 1);
INSERT INTO product VALUES (4, '23456645', '2020-03-13 00:00:00+08', 41, 2, 1);
INSERT INTO product VALUES (13, '76477789', '2020-03-14 00:00:00+08', 33, 2, 1);
INSERT INTO product VALUES (18, '17366878', '2020-03-15 00:00:00+08', 39, 2, 1);
INSERT INTO product VALUES (19, '10760430', '2020-03-16 00:00:00+08', 33, 2, 1);
INSERT INTO product VALUES (8, '25556789', '2020-03-17 00:00:00+08', 41, 2, 1);
INSERT INTO product VALUES (1, '12345678', '2020-03-18 00:00:00+08', 38, 2, 1);
INSERT INTO product VALUES (5, '12383658', '2020-03-19 00:00:00+08', 34, 2, 1);


--
-- Data for Name: product_basic; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO product_basic VALUES (5);
INSERT INTO product_basic VALUES (7);
INSERT INTO product_basic VALUES (9);
INSERT INTO product_basic VALUES (13);
INSERT INTO product_basic VALUES (21);
INSERT INTO product_basic VALUES (23);
INSERT INTO product_basic VALUES (25);
INSERT INTO product_basic VALUES (26);
INSERT INTO product_basic VALUES (28);
INSERT INTO product_basic VALUES (30);
INSERT INTO product_basic VALUES (2);
INSERT INTO product_basic VALUES (8);
INSERT INTO product_basic VALUES (17);
INSERT INTO product_basic VALUES (3);
INSERT INTO product_basic VALUES (4);
INSERT INTO product_basic VALUES (6);
INSERT INTO product_basic VALUES (12);
INSERT INTO product_basic VALUES (16);
INSERT INTO product_basic VALUES (10);
INSERT INTO product_basic VALUES (11);
INSERT INTO product_basic VALUES (14);
INSERT INTO product_basic VALUES (15);
INSERT INTO product_basic VALUES (19);
INSERT INTO product_basic VALUES (20);
INSERT INTO product_basic VALUES (22);
INSERT INTO product_basic VALUES (24);
INSERT INTO product_basic VALUES (27);
INSERT INTO product_basic VALUES (29);
INSERT INTO product_basic VALUES (1);
INSERT INTO product_basic VALUES (18);
INSERT INTO product_basic VALUES (233275);
INSERT INTO product_basic VALUES (233282);
INSERT INTO product_basic VALUES (233289);
INSERT INTO product_basic VALUES (233296);
INSERT INTO product_basic VALUES (233303);
INSERT INTO product_basic VALUES (233310);
INSERT INTO product_basic VALUES (233317);
INSERT INTO product_basic VALUES (233324);
INSERT INTO product_basic VALUES (233331);
INSERT INTO product_basic VALUES (233338);
INSERT INTO product_basic VALUES (233345);
INSERT INTO product_basic VALUES (233352);
INSERT INTO product_basic VALUES (233359);
INSERT INTO product_basic VALUES (233366);
INSERT INTO product_basic VALUES (233373);
INSERT INTO product_basic VALUES (233380);
INSERT INTO product_basic VALUES (233387);
INSERT INTO product_basic VALUES (233394);
INSERT INTO product_basic VALUES (233401);
INSERT INTO product_basic VALUES (233408);
INSERT INTO product_basic VALUES (233415);
INSERT INTO product_basic VALUES (233422);
INSERT INTO product_basic VALUES (233429);
INSERT INTO product_basic VALUES (233436);
INSERT INTO product_basic VALUES (233443);
INSERT INTO product_basic VALUES (233450);
INSERT INTO product_basic VALUES (233457);
INSERT INTO product_basic VALUES (233464);
INSERT INTO product_basic VALUES (233471);
INSERT INTO product_basic VALUES (233478);
INSERT INTO product_basic VALUES (233485);
INSERT INTO product_basic VALUES (233492);
INSERT INTO product_basic VALUES (233499);
INSERT INTO product_basic VALUES (233506);
INSERT INTO product_basic VALUES (233513);
INSERT INTO product_basic VALUES (233520);
INSERT INTO product_basic VALUES (233527);
INSERT INTO product_basic VALUES (233534);
INSERT INTO product_basic VALUES (233541);
INSERT INTO product_basic VALUES (233548);
INSERT INTO product_basic VALUES (233555);
INSERT INTO product_basic VALUES (233562);
INSERT INTO product_basic VALUES (233569);
INSERT INTO product_basic VALUES (233576);
INSERT INTO product_basic VALUES (233583);
INSERT INTO product_basic VALUES (233590);
INSERT INTO product_basic VALUES (233597);
INSERT INTO product_basic VALUES (233604);
INSERT INTO product_basic VALUES (233611);
INSERT INTO product_basic VALUES (233618);
INSERT INTO product_basic VALUES (233625);
INSERT INTO product_basic VALUES (233632);
INSERT INTO product_basic VALUES (233639);
INSERT INTO product_basic VALUES (233646);
INSERT INTO product_basic VALUES (233653);
INSERT INTO product_basic VALUES (233660);
INSERT INTO product_basic VALUES (233667);
INSERT INTO product_basic VALUES (233674);
INSERT INTO product_basic VALUES (233681);
INSERT INTO product_basic VALUES (233688);
INSERT INTO product_basic VALUES (233695);
INSERT INTO product_basic VALUES (233702);
INSERT INTO product_basic VALUES (233709);
INSERT INTO product_basic VALUES (233716);
INSERT INTO product_basic VALUES (233723);
INSERT INTO product_basic VALUES (233730);


--
-- Data for Name: accessories_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO accessories_attr_lcl VALUES (6, 5, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (8, 7, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (10, 9, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (14, 13, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (22, 21, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (24, 23, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (26, 25, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (27, 26, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (29, 28, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (31, 30, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (3, 2, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (9, 8, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (18, 17, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (4, 3, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (5, 4, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (7, 6, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (13, 12, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (17, 16, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (11, 10, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (12, 11, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (15, 14, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (16, 15, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (20, 19, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (21, 20, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (23, 22, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (25, 24, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (28, 27, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (30, 29, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (2, 1, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (19, 18, NULL, NULL, 'en-GB');
INSERT INTO accessories_attr_lcl VALUES (45, 5, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (47, 7, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (49, 9, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (53, 13, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (61, 21, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (63, 23, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (65, 25, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (66, 26, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (68, 28, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (70, 30, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (42, 2, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (48, 8, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (57, 17, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (43, 3, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (44, 4, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (46, 6, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (52, 12, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (56, 16, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (50, 10, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (51, 11, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (54, 14, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (55, 15, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (59, 19, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (60, 20, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (62, 22, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (64, 24, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (67, 27, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (69, 29, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (41, 1, NULL, NULL, 'zh-HK');
INSERT INTO accessories_attr_lcl VALUES (58, 18, NULL, NULL, 'zh-HK');


--
-- Data for Name: address; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: address_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: party_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO party_type VALUES (1, 'Person');
INSERT INTO party_type VALUES (2, 'Organisation');


--
-- Data for Name: party; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO party VALUES (1, 1);
INSERT INTO party VALUES (232216, 1);
INSERT INTO party VALUES (232220, 1);
INSERT INTO party VALUES (232230, 1);
INSERT INTO party VALUES (232232, 1);
INSERT INTO party VALUES (232234, 1);
INSERT INTO party VALUES (232236, 1);
INSERT INTO party VALUES (232238, 1);
INSERT INTO party VALUES (232240, 1);
INSERT INTO party VALUES (232242, 1);
INSERT INTO party VALUES (232244, 1);
INSERT INTO party VALUES (232246, 1);
INSERT INTO party VALUES (232248, 1);
INSERT INTO party VALUES (232250, 1);
INSERT INTO party VALUES (232252, 1);
INSERT INTO party VALUES (232254, 1);
INSERT INTO party VALUES (232256, 1);
INSERT INTO party VALUES (232258, 1);
INSERT INTO party VALUES (232260, 1);
INSERT INTO party VALUES (232262, 1);
INSERT INTO party VALUES (232304, 1);
INSERT INTO party VALUES (232310, 1);
INSERT INTO party VALUES (232312, 1);
INSERT INTO party VALUES (233016, 1);
INSERT INTO party VALUES (233018, 1);
INSERT INTO party VALUES (233020, 1);
INSERT INTO party VALUES (233022, 1);
INSERT INTO party VALUES (233024, 1);
INSERT INTO party VALUES (233054, 1);
INSERT INTO party VALUES (233055, 2);


--
-- Data for Name: bag; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO bag VALUES (233760, 233024, '2020-09-19 11:20:11.58+08', '2020-11-22 10:46:40.419+08');


--
-- Name: bag_bag_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('bag_bag_id_seq', 1, false);


--
-- Data for Name: bag_item_status; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO bag_item_status VALUES (1, 'NEW01', 'New');
INSERT INTO bag_item_status VALUES (2, 'PRO01', 'Processed');
INSERT INTO bag_item_status VALUES (3, 'PND01', 'Pending');


--
-- Data for Name: bag_item; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Name: bag_status_bag_sts_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('bag_status_bag_sts_id_seq', 3, true);


--
-- Data for Name: brand; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO brand VALUES (32, 'DLE01');
INSERT INTO brand VALUES (33, 'ENZ01');
INSERT INTO brand VALUES (34, 'DIV01');
INSERT INTO brand VALUES (35, 'DRI01');
INSERT INTO brand VALUES (36, 'WON01');
INSERT INTO brand VALUES (37, 'PLA01');
INSERT INTO brand VALUES (38, 'GLO01');
INSERT INTO brand VALUES (39, 'ADO01');
INSERT INTO brand VALUES (40, 'GOL01');
INSERT INTO brand VALUES (41, 'SHI01');
INSERT INTO brand VALUES (233191, 'LAYLA');
INSERT INTO brand VALUES (233194, 'WILOW');
INSERT INTO brand VALUES (233197, 'MARLY');
INSERT INTO brand VALUES (233200, 'SCOTI');
INSERT INTO brand VALUES (233203, 'IPSY ');
INSERT INTO brand VALUES (233206, 'LBS  ');
INSERT INTO brand VALUES (233209, 'LINSY');
INSERT INTO brand VALUES (233212, 'PHONY');
INSERT INTO brand VALUES (233215, 'HLEAF');


--
-- Data for Name: brand_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO brand_attr_lcl VALUES (59, 32, 'Dole', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (60, 32, 'Dole', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (61, 33, 'Enza', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (62, 33, 'Enza', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (63, 34, 'Diva', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (64, 34, 'Diva', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (65, 35, 'Driscolls', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (66, 35, 'Driscolls', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (67, 36, 'Wonderful', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (68, 36, 'Wonderful', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (69, 37, 'Planters', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (70, 37, 'Planters', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (71, 38, 'Glorys', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (72, 38, 'Glorys', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (73, 39, 'Adora', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (74, 39, 'Adora', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (75, 40, 'Gold', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (76, 40, 'Gold', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (77, 41, 'Shine', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (78, 41, 'Shine', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (233192, 233191, 'LAYLA', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (233193, 233191, 'Layla萊拉', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (233195, 233194, 'WILLOW', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (233196, 233194, 'Willow小柳', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (233198, 233197, 'MARLEY ', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (233199, 233197, 'Marley馬利', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (233201, 233200, 'Scottish  Hendoz ', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (233202, 233200, 'Scottish Hendoz蘇格蘭亨多斯', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (233204, 233203, 'Ipsy', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (233205, 233203, 'Ipsy葉子', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (233207, 233206, 'Little Bag', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (233208, 233206, 'Little Bat小包包', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (233210, 233209, 'LINSY', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (233211, 233209, 'Linsy連斯', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (233213, 233212, 'Phoney ', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (233214, 233212, 'Phoney ', NULL, 'zh-HK');
INSERT INTO brand_attr_lcl VALUES (233216, 233215, 'HAPPYLEAF', NULL, 'en-GB');
INSERT INTO brand_attr_lcl VALUES (233217, 233215, '開心葉', NULL, 'zh-HK');


--
-- Name: brand_attr_lcl_bnd_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('brand_attr_lcl_bnd_id_seq', 78, true);


--
-- Name: brand_bnd_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('brand_bnd_id_seq', 41, true);


--
-- Data for Name: category; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO category VALUES (45, 'FBR01', 2, 0, NULL, NULL);
INSERT INTO category VALUES (39, 'PRM02', 1, 0, NULL, NULL);
INSERT INTO category VALUES (40, 'BND01', 1, 0, NULL, NULL);
INSERT INTO category VALUES (2, 'PRM01', 1, 0, NULL, NULL);
INSERT INTO category VALUES (38, 'FET01', 1, 1, 'PRM02', 39);
INSERT INTO category VALUES (25, 'OFT01', 1, 2, 'FRT01', 3);
INSERT INTO category VALUES (31, 'DUM05', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (33, 'DUM07', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (4, 'VEG01', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (13, 'MEL01', 1, 2, 'FRT01', 3);
INSERT INTO category VALUES (11, 'DRU01', 1, 2, 'FRT01', 3);
INSERT INTO category VALUES (36, 'REV01', 1, 3, 'ROV01', 6);
INSERT INTO category VALUES (22, 'PEC01', 1, 2, 'NUT01', 16);
INSERT INTO category VALUES (28, 'DUM02', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (10, 'POM01', 1, 2, 'FRT01', 3);
INSERT INTO category VALUES (42, 'OTH02', 1, 1, 'BND01', 40);
INSERT INTO category VALUES (17, 'PIS01', 1, 2, 'NUT01', 16);
INSERT INTO category VALUES (34, 'DUM08', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (5, 'DGV01', 1, 2, 'VEG01', 4);
INSERT INTO category VALUES (15, 'TRO01', 1, 2, 'FRT01', 3);
INSERT INTO category VALUES (32, 'DUM06', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (30, 'DUM04', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (6, 'ROV01', 1, 2, 'VEG01', 4);
INSERT INTO category VALUES (12, 'BER01', 1, 2, 'FRT01', 3);
INSERT INTO category VALUES (43, 'BSP01', 1, 1, 'PRM02', 39);
INSERT INTO category VALUES (8, 'SVG01', 1, 2, 'VEG01', 4);
INSERT INTO category VALUES (16, 'NUT01', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (18, 'HAZ01', 1, 2, 'NUT01', 16);
INSERT INTO category VALUES (26, 'ONT01', 1, 2, 'NUT01', 16);
INSERT INTO category VALUES (7, 'BAP01', 1, 2, 'VEG01', 4);
INSERT INTO category VALUES (29, 'DUM03', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (27, 'DUM01', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (14, 'CIT01', 1, 2, 'FRT01', 3);
INSERT INTO category VALUES (44, 'UNK01', 1, 1, 'PRM02', 39);
INSERT INTO category VALUES (21, 'PEA01', 1, 2, 'NUT01', 16);
INSERT INTO category VALUES (24, 'MAC01', 1, 2, 'NUT01', 16);
INSERT INTO category VALUES (23, 'BRA01', 1, 2, 'NUT01', 16);
INSERT INTO category VALUES (19, 'CAS01', 1, 2, 'NUT01', 16);
INSERT INTO category VALUES (35, 'DUM09', 1, 4, 'REV01', 36);
INSERT INTO category VALUES (9, 'OTH01', 1, 2, 'VEG01', 4);
INSERT INTO category VALUES (3, 'FRT01', 1, 1, 'PRM01', 2);
INSERT INTO category VALUES (20, 'ALM01', 1, 2, 'NUT01', 16);
INSERT INTO category VALUES (37, 'ORV01', 1, 3, 'ROV01', 6);
INSERT INTO category VALUES (233095, 'PRM05', 1, 0, '     ', NULL);
INSERT INTO category VALUES (233131, 'FFSHO', 1, 3, 'FFOT1', 233107);
INSERT INTO category VALUES (233185, 'HFCAR', 1, 3, 'HFLO1', 233182);
INSERT INTO category VALUES (233059, 'AHAR1', 1, 2, 'ACC01', 233056);
INSERT INTO category VALUES (233152, 'FCTOP', 1, 3, 'FCLO1', 233116);
INSERT INTO category VALUES (233113, 'FWAT1', 1, 2, 'FAS01', 233104);
INSERT INTO category VALUES (233155, 'FCBOT', 1, 3, 'FCLO1', 233116);
INSERT INTO category VALUES (233098, 'AOLAN', 1, 3, 'AOTH1', 233062);
INSERT INTO category VALUES (233134, 'FFSLI', 1, 3, 'FFOT1', 233107);
INSERT INTO category VALUES (233173, 'FCPOC', 1, 3, 'FCOO1', 233125);
INSERT INTO category VALUES (233101, 'AOPAD', 1, 3, 'AOTH1', 233062);
INSERT INTO category VALUES (233167, 'FBHAN', 1, 3, 'FBAG1', 233119);
INSERT INTO category VALUES (233065, 'AJEW1', 1, 2, 'ACC01', 233056);
INSERT INTO category VALUES (233074, 'AHHAT', 1, 3, 'AHAR1', 233059);
INSERT INTO category VALUES (233116, 'FCLO1', 1, 2, 'FAS01', 233104);
INSERT INTO category VALUES (233107, 'FFOT1', 1, 2, 'FAS01', 233104);
INSERT INTO category VALUES (233140, 'FBFAB', 1, 3, 'FBEL1', 233110);
INSERT INTO category VALUES (233068, 'AHHAB', 1, 3, 'AHAR1', 233059);
INSERT INTO category VALUES (233110, 'FBEL1', 1, 2, 'FAS01', 233104);
INSERT INTO category VALUES (233143, 'FHSUN', 1, 3, 'FHEA1', 233122);
INSERT INTO category VALUES (233071, 'AHHAC', 1, 3, 'AHAR1', 233059);
INSERT INTO category VALUES (233080, 'AJEAR', 1, 3, 'AJEW1', 233065);
INSERT INTO category VALUES (233137, 'FFSOC', 1, 3, 'FFOT1', 233107);
INSERT INTO category VALUES (233164, 'FBBAC', 1, 3, 'FBAG1', 233119);
INSERT INTO category VALUES (233092, 'AOKEY', 1, 3, 'AOTH1', 233062);
INSERT INTO category VALUES (233161, 'FCSCA', 1, 3, 'FCLO1', 233116);
INSERT INTO category VALUES (233122, 'FHEA1', 1, 2, 'FAS01', 233104);
INSERT INTO category VALUES (233158, 'FCJAC', 1, 3, 'FCLO1', 233116);
INSERT INTO category VALUES (233062, 'AOTH1', 1, 2, 'ACC01', 233056);
INSERT INTO category VALUES (233119, 'FBAG1', 1, 2, 'FAS01', 233104);
INSERT INTO category VALUES (233170, 'FBWAL', 1, 3, 'FBAG1', 233119);
INSERT INTO category VALUES (233179, 'HOM01', 1, 1, 'PRM05', 233095);
INSERT INTO category VALUES (233128, 'FPHO1', 1, 2, 'FAS01', 233104);
INSERT INTO category VALUES (233104, 'FAS01', 1, 1, 'PRM05', 233095);
INSERT INTO category VALUES (233089, 'AJBRO', 1, 3, 'AJEW1', 233065);
INSERT INTO category VALUES (233146, 'FHHAT', 1, 3, 'FHEA1', 233122);
INSERT INTO category VALUES (233188, 'HFCUP', 1, 3, 'HFLO1', 233182);
INSERT INTO category VALUES (233125, 'FCOO1', 1, 2, 'FAS01', 233104);
INSERT INTO category VALUES (233176, 'FPAPP', 1, 3, 'FPHO1', 233128);
INSERT INTO category VALUES (233077, 'AJBRA', 1, 3, 'AJEW1', 233065);
INSERT INTO category VALUES (233056, 'ACC01', 1, 1, 'PRM05', 233095);
INSERT INTO category VALUES (233083, 'AJNEC', 1, 3, 'AJEW1', 233065);
INSERT INTO category VALUES (233182, 'HFLO1', 1, 2, 'HOM01', 233179);
INSERT INTO category VALUES (233086, 'AJRIN', 1, 3, 'AJEW1', 233065);
INSERT INTO category VALUES (233149, 'FWWAT', 1, 3, 'FWAT1', 233113);


--
-- Data for Name: brand_category; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO brand_category VALUES (11, 32, 45);
INSERT INTO brand_category VALUES (12, 33, 45);
INSERT INTO brand_category VALUES (13, 34, 45);
INSERT INTO brand_category VALUES (14, 35, 45);


--
-- Name: brand_category_bnd_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('brand_category_bnd_cat_id_seq', 14, true);


--
-- Data for Name: brand_promotion; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: category_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO category_attr_lcl VALUES (14, 3, '水果', '', 'zh-HK');
INSERT INTO category_attr_lcl VALUES (17, 5, '深綠色蔬菜', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (19, 6, '紅色和橙色蔬菜', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (21, 7, '豆類和豌豆', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (23, 8, '澱粉蔬菜', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (25, 9, '其他蔬菜', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (27, 10, '梨果', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (29, 11, '核果', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (31, 12, '漿果', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (33, 13, '瓜', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (35, 14, '柑橘', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (37, 15, '熱帶', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (39, 16, '堅果', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (41, 17, '開心果', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (43, 18, '榛子', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (45, 19, '腰果', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (47, 20, '杏仁', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (49, 21, '花生', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (51, 22, '胡桃', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (53, 23, '巴西', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (55, 24, '澳洲', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (57, 25, '其他水果', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (59, 26, '其他堅果', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (61, 27, '假 1', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (63, 28, '假 2', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (65, 29, '假 3', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (67, 30, '假 4', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (69, 31, '假 5', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (71, 32, '假 6', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (73, 33, '假 7', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (75, 34, '假 8', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (77, 35, '假 9', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (79, 36, '紅色蔬菜', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (81, 37, '橙色蔬菜', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (10, 2, 'ALL', '', 'en-GB');
INSERT INTO category_attr_lcl VALUES (11, 3, 'Fruit', '', 'en-GB');
INSERT INTO category_attr_lcl VALUES (20, 7, 'Beans and Peas', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (26, 10, 'Pomes', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (28, 11, 'Drupes', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (30, 12, 'Berries', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (32, 13, 'Melons', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (34, 14, 'Citrus', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (36, 15, 'Tropical', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (38, 16, 'Nuts', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (15, 4, '蔬菜', 'VEG01.jpg', 'zh-HK');
INSERT INTO category_attr_lcl VALUES (12, 4, 'Vegetables', 'VEG01.jpg', 'en-GB');
INSERT INTO category_attr_lcl VALUES (40, 17, 'Pistachios', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (42, 18, 'Hazelnuts', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (44, 19, 'Cashews', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (46, 20, 'Almonds', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (48, 21, 'Peanuts', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (50, 22, 'Pecans', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (52, 23, 'Brazil', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (54, 24, 'Macadamia', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (56, 25, 'Other Fruit', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (58, 26, 'Other Nuts', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (60, 27, 'DUMMY 1', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (62, 28, 'DUMMY 2', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (64, 29, 'DUMMY 3', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (66, 30, 'DUMMY 4', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (68, 31, 'DUMMY 5', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (70, 32, 'DUMMY 6', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (72, 33, 'DUMMY 7', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (74, 34, 'DUMMY 8', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (76, 35, 'DUMMY 9', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (82, 38, 'Featured', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (83, 38, '精選', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (84, 39, 'MISC', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (85, 39, '雜', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (86, 40, 'All Brands', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (87, 40, '所有品牌', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (90, 42, 'Other Brands', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (99, 42, '其他品牌', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (100, 43, 'Best Sellers', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (101, 43, '最暢銷', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (16, 5, 'Dark Green', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (18, 6, 'Red and Orange', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (22, 8, 'Starchy', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (24, 9, 'Other', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (78, 36, 'Red', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (80, 37, 'Orange', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (102, 44, 'Unknown', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (103, 44, '未知', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (13, 2, 'ALL', '', 'zh-HK');
INSERT INTO category_attr_lcl VALUES (104, 45, 'Featured Brands', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (105, 45, '推薦品牌', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233057, 233056, 'Accessories', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233058, 233056, '配件', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233060, 233059, 'Hair Accessories', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233061, 233059, '髮飾', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233063, 233062, 'Other Accessories ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233064, 233062, '其他飾物', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233066, 233065, 'Jewelry', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233067, 233065, '首飾', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233069, 233068, 'Hair Bands', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233070, 233068, '頭箍', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233072, 233071, 'Hair Clips', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233073, 233071, '髮夾', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233075, 233074, 'Hair Ties ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233076, 233074, '頭髮橡筋', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233078, 233077, 'Bracelets', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233079, 233077, '手鏈 ', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233081, 233080, 'Earrings', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233082, 233080, '耳環', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233084, 233083, 'Necklaces', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233085, 233083, '項鍊', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233087, 233086, 'Rings', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233088, 233086, '戒指', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233090, 233089, 'Brooch', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233091, 233089, '胸針', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233093, 233092, 'Keyrings', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233094, 233092, '鎖匙扣', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233096, 233095, 'Master Category', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233097, 233095, '主類別', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233099, 233098, 'Lanyards', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233100, 233098, '頸繩', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233102, 233101, 'Packing decorations', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233103, 233101, '包裝裝飾物', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233105, 233104, 'Fashion ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233106, 233104, '時裝', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233108, 233107, 'Footwear', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233109, 233107, '鞋類', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233111, 233110, 'Belts', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233112, 233110, '皮帶', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233114, 233113, 'Watches', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233115, 233113, '手錶', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233117, 233116, 'Clothes ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233118, 233116, '衣服', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233120, 233119, 'Bags and wallets', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233121, 233119, '手袋銀包', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233123, 233122, 'Head Accessories ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233124, 233122, '頭飾', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233126, 233125, 'Cool Stationary ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233127, 233125, '時尚文具', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233129, 233128, 'Phone Accessories', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233130, 233128, '手機配件', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233132, 233131, 'Shoes', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233133, 233131, '鞋', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233135, 233134, 'Slippers', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233136, 233134, '拖鞋', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233138, 233137, 'Socks', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233139, 233137, '襪', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233141, 233140, 'Fashion Belts ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233142, 233140, '時尚皮帶', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233144, 233143, 'Sunglasses', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233145, 233143, '太陽眼鏡', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233147, 233146, 'Hats', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233148, 233146, '帽', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233150, 233149, 'Fashion Watches', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233151, 233149, '時尚手錶', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233153, 233152, 'Tops', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233154, 233152, '上衫', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233156, 233155, 'Bottoms ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233157, 233155, '外褲', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233159, 233158, 'Jackets', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233160, 233158, '外褸', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233162, 233161, 'Scarfs', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233163, 233161, '頸巾', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233165, 233164, 'Backpacks', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233166, 233164, '背囊', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233168, 233167, 'Handbags', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233169, 233167, '包包', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233171, 233170, 'Wallet ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233172, 233170, '銀包', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233174, 233173, 'Pockets ', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233175, 233173, '文件袋', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233177, 233176, 'Apple', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233178, 233176, '蘋果手機配件 ', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233180, 233179, 'Home', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233181, 233179, '家居', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233183, 233182, 'Floor', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233184, 233182, '地板', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233186, 233185, 'Carpets', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233187, 233185, '地毯', NULL, 'zh-HK');
INSERT INTO category_attr_lcl VALUES (233189, 233188, 'Cupboards', NULL, 'en-GB');
INSERT INTO category_attr_lcl VALUES (233190, 233188, '收納', NULL, 'zh-HK');


--
-- Name: category_attr_lcl_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('category_attr_lcl_cat_id_seq', 22, true);


--
-- Data for Name: category_brand; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO category_brand VALUES (45);


--
-- Name: category_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('category_cat_id_seq', 8, true);


--
-- Data for Name: category_product; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO category_product VALUES (43);
INSERT INTO category_product VALUES (39);
INSERT INTO category_product VALUES (38);
INSERT INTO category_product VALUES (40);
INSERT INTO category_product VALUES (42);
INSERT INTO category_product VALUES (8);
INSERT INTO category_product VALUES (16);
INSERT INTO category_product VALUES (28);
INSERT INTO category_product VALUES (36);
INSERT INTO category_product VALUES (15);
INSERT INTO category_product VALUES (4);
INSERT INTO category_product VALUES (30);
INSERT INTO category_product VALUES (23);
INSERT INTO category_product VALUES (20);
INSERT INTO category_product VALUES (25);
INSERT INTO category_product VALUES (26);
INSERT INTO category_product VALUES (13);
INSERT INTO category_product VALUES (22);
INSERT INTO category_product VALUES (27);
INSERT INTO category_product VALUES (21);
INSERT INTO category_product VALUES (5);
INSERT INTO category_product VALUES (11);
INSERT INTO category_product VALUES (3);
INSERT INTO category_product VALUES (14);
INSERT INTO category_product VALUES (17);
INSERT INTO category_product VALUES (33);
INSERT INTO category_product VALUES (19);
INSERT INTO category_product VALUES (31);
INSERT INTO category_product VALUES (35);
INSERT INTO category_product VALUES (37);
INSERT INTO category_product VALUES (34);
INSERT INTO category_product VALUES (32);
INSERT INTO category_product VALUES (12);
INSERT INTO category_product VALUES (10);
INSERT INTO category_product VALUES (18);
INSERT INTO category_product VALUES (9);
INSERT INTO category_product VALUES (24);
INSERT INTO category_product VALUES (6);
INSERT INTO category_product VALUES (29);
INSERT INTO category_product VALUES (2);
INSERT INTO category_product VALUES (7);
INSERT INTO category_product VALUES (44);
INSERT INTO category_product VALUES (233056);
INSERT INTO category_product VALUES (233059);
INSERT INTO category_product VALUES (233062);
INSERT INTO category_product VALUES (233065);
INSERT INTO category_product VALUES (233068);
INSERT INTO category_product VALUES (233071);
INSERT INTO category_product VALUES (233074);
INSERT INTO category_product VALUES (233077);
INSERT INTO category_product VALUES (233080);
INSERT INTO category_product VALUES (233083);
INSERT INTO category_product VALUES (233086);
INSERT INTO category_product VALUES (233089);
INSERT INTO category_product VALUES (233092);
INSERT INTO category_product VALUES (233095);
INSERT INTO category_product VALUES (233098);
INSERT INTO category_product VALUES (233101);
INSERT INTO category_product VALUES (233104);
INSERT INTO category_product VALUES (233107);
INSERT INTO category_product VALUES (233110);
INSERT INTO category_product VALUES (233113);
INSERT INTO category_product VALUES (233116);
INSERT INTO category_product VALUES (233119);
INSERT INTO category_product VALUES (233122);
INSERT INTO category_product VALUES (233125);
INSERT INTO category_product VALUES (233128);
INSERT INTO category_product VALUES (233131);
INSERT INTO category_product VALUES (233134);
INSERT INTO category_product VALUES (233137);
INSERT INTO category_product VALUES (233140);
INSERT INTO category_product VALUES (233143);
INSERT INTO category_product VALUES (233146);
INSERT INTO category_product VALUES (233149);
INSERT INTO category_product VALUES (233152);
INSERT INTO category_product VALUES (233155);
INSERT INTO category_product VALUES (233158);
INSERT INTO category_product VALUES (233161);
INSERT INTO category_product VALUES (233164);
INSERT INTO category_product VALUES (233167);
INSERT INTO category_product VALUES (233170);
INSERT INTO category_product VALUES (233173);
INSERT INTO category_product VALUES (233176);
INSERT INTO category_product VALUES (233179);
INSERT INTO category_product VALUES (233182);
INSERT INTO category_product VALUES (233185);
INSERT INTO category_product VALUES (233188);


--
-- Data for Name: category_promotion; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: category_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO category_type VALUES (1, 'PRD01', 'product');
INSERT INTO category_type VALUES (2, 'BND01', 'brand');
INSERT INTO category_type VALUES (3, 'PRM01', 'promotion');
INSERT INTO category_type VALUES (4, 'LAY01', 'layout');


--
-- Name: category_type_cat_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('category_type_cat_typ_id_seq', 2, true);


--
-- Data for Name: currency; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO currency VALUES (1, 'HKD');
INSERT INTO currency VALUES (2, 'USD');


--
-- Data for Name: role_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO role_type VALUES (1, 'Customer');
INSERT INTO role_type VALUES (2, 'Supplier');


--
-- Data for Name: role; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO role VALUES (1, 1, '2018-11-15 00:00:00+08', 1);
INSERT INTO role VALUES (232217, 1, '2018-11-15 00:00:00+08', 232216);
INSERT INTO role VALUES (232221, 1, '2018-11-15 00:00:00+08', 232220);
INSERT INTO role VALUES (232231, 1, '2018-11-15 00:00:00+08', 232230);
INSERT INTO role VALUES (232233, 1, '2018-11-15 00:00:00+08', 232232);
INSERT INTO role VALUES (232235, 1, '2018-12-01 00:00:00+08', 232234);
INSERT INTO role VALUES (232237, 1, '2018-12-01 00:00:00+08', 232236);
INSERT INTO role VALUES (232239, 1, '2018-12-01 00:00:00+08', 232238);
INSERT INTO role VALUES (232241, 1, '2018-12-01 00:00:00+08', 232240);
INSERT INTO role VALUES (232243, 1, '2018-12-01 00:00:00+08', 232242);
INSERT INTO role VALUES (232245, 1, '2018-12-01 00:00:00+08', 232244);
INSERT INTO role VALUES (232247, 1, '2018-12-01 00:00:00+08', 232246);
INSERT INTO role VALUES (232249, 1, '2018-12-12 00:00:00+08', 232248);
INSERT INTO role VALUES (232251, 1, '2018-12-24 00:00:00+08', 232250);
INSERT INTO role VALUES (232253, 1, '2018-12-24 00:00:00+08', 232252);
INSERT INTO role VALUES (232255, 1, '2019-04-06 00:00:00+08', 232254);
INSERT INTO role VALUES (232257, 1, '2019-04-06 00:00:00+08', 232256);
INSERT INTO role VALUES (232259, 1, '2019-04-06 00:00:00+08', 232258);
INSERT INTO role VALUES (232261, 1, '2019-04-15 00:00:00+08', 232260);
INSERT INTO role VALUES (232263, 1, '2019-07-10 00:00:00+08', 232262);
INSERT INTO role VALUES (232305, 1, '2019-07-11 00:00:00+08', 232304);
INSERT INTO role VALUES (232311, 1, '2019-07-11 00:00:00+08', 232310);
INSERT INTO role VALUES (232313, 1, '2019-07-11 00:00:00+08', 232312);
INSERT INTO role VALUES (233017, 1, '2020-06-11 00:00:00+08', 233016);
INSERT INTO role VALUES (233019, 1, '2020-06-11 00:00:00+08', 233018);
INSERT INTO role VALUES (233021, 1, '2020-06-11 00:00:00+08', 233020);
INSERT INTO role VALUES (233023, 1, '2020-06-11 00:00:00+08', 233022);
INSERT INTO role VALUES (233025, 1, '2020-06-11 00:00:00+08', 233024);
INSERT INTO role VALUES (233055, 1, '2020-08-20 00:00:00+08', 233054);
INSERT INTO role VALUES (233056, 2, '2020-09-28 11:04:23.6741+08', 233055);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO customer VALUES (1, '1000000011');
INSERT INTO customer VALUES (232217, '1000000026');
INSERT INTO customer VALUES (232221, '1000000028');
INSERT INTO customer VALUES (232231, '1000000033');
INSERT INTO customer VALUES (232233, '1000000034');
INSERT INTO customer VALUES (232235, '1000000035');
INSERT INTO customer VALUES (232237, '1000000036');
INSERT INTO customer VALUES (232239, '1000000037');
INSERT INTO customer VALUES (232241, '1000000038');
INSERT INTO customer VALUES (232243, '1000000039');
INSERT INTO customer VALUES (232245, '1000000040');
INSERT INTO customer VALUES (232247, '1000000041');
INSERT INTO customer VALUES (232249, '1000000042');
INSERT INTO customer VALUES (232251, '1000000043');
INSERT INTO customer VALUES (232253, '1000000044');
INSERT INTO customer VALUES (232255, '1000000045');
INSERT INTO customer VALUES (232257, '1000000046');
INSERT INTO customer VALUES (232259, '1000000047');
INSERT INTO customer VALUES (232261, '1000000048');
INSERT INTO customer VALUES (232263, '1000000049');
INSERT INTO customer VALUES (232305, '1000000070');
INSERT INTO customer VALUES (232311, '1000000073');
INSERT INTO customer VALUES (232313, '1000000074');
INSERT INTO customer VALUES (233017, '1000000264');
INSERT INTO customer VALUES (233019, '1000000265');
INSERT INTO customer VALUES (233021, '1000000266');
INSERT INTO customer VALUES (233023, '1000000267');
INSERT INTO customer VALUES (233025, '1000000268');
INSERT INTO customer VALUES (233055, '1000000269');


--
-- Name: customer_cst_num_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('customer_cst_num_seq', 1, false);


--
-- Data for Name: department_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO department_attr_lcl VALUES (1, 2, 'Jewellery', 'en-GB');
INSERT INTO department_attr_lcl VALUES (2, 2, '首飾', 'zh-HK');


--
-- Data for Name: discount; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: discount_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO discount_type VALUES (1, 'percentage');
INSERT INTO discount_type VALUES (2, 'value');


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('hibernate_sequence', 234450, true);


--
-- Name: hierarchy_hir_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('hierarchy_hir_id_seq', 43, true);


--
-- Data for Name: inventory_location; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO inventory_location VALUES (1, 'LCK01', 'Lai Chi Kok Apartment', true);


--
-- Data for Name: inventory_transaction_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO inventory_transaction_type VALUES (1, 'IN');
INSERT INTO inventory_transaction_type VALUES (2, 'OUT');


--
-- Data for Name: inventory_transaction; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO inventory_transaction VALUES (234238, 1, 11, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234240, 1, 21, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234242, 1, 23, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234244, 1, 25, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234246, 1, 26, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234248, 1, 28, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234250, 1, 30, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234252, 1, 17, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234254, 1, 16, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234256, 1, 14, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234258, 1, 15, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234260, 1, 19, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234262, 1, 20, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234264, 1, 22, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234266, 1, 24, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234268, 1, 27, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234270, 1, 29, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234272, 1, 18, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234274, 1, 233275, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234276, 1, 233282, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234278, 1, 233289, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234280, 1, 233296, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234282, 1, 233303, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234284, 1, 233310, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234286, 1, 233317, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234288, 1, 233324, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234290, 1, 233331, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234292, 1, 233338, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234294, 1, 233345, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234296, 1, 233352, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234298, 1, 233359, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234300, 1, 233366, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234302, 1, 233373, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234304, 1, 233380, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234306, 1, 233387, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234308, 1, 233394, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234310, 1, 233401, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234312, 1, 233408, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234314, 1, 233415, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234316, 1, 233422, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234318, 1, 233429, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234320, 1, 233436, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234322, 1, 233443, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234324, 1, 233450, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234326, 1, 233457, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234328, 1, 233464, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234330, 1, 233471, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234332, 1, 233478, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234334, 1, 233485, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234336, 1, 233492, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234338, 1, 233499, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234340, 1, 233506, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234342, 1, 233513, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234344, 1, 233520, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234346, 1, 233527, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234348, 1, 233534, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234350, 1, 233541, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234352, 1, 233548, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234354, 1, 233555, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234356, 1, 233562, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234358, 1, 233569, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234360, 1, 233576, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234362, 1, 233583, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234364, 1, 233590, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234366, 1, 233597, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234368, 1, 233604, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234370, 1, 233611, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234372, 1, 233618, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234374, 1, 233625, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234376, 1, 233632, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234378, 1, 233639, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234380, 1, 233646, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234382, 1, 233653, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234384, 1, 233660, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234386, 1, 233667, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234388, 1, 233674, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234390, 1, 233681, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234392, 1, 233688, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234394, 1, 233695, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234396, 1, 233702, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234398, 1, 233709, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234400, 1, 233716, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234402, 1, 233723, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234404, 1, 233730, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234406, 1, 13, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234408, 1, 4, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234410, 1, 7, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234412, 1, 9, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234414, 1, 12, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234416, 1, 1, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234418, 1, 10, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234420, 1, 8, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234422, 1, 6, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234424, 1, 2, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234426, 1, 3, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');
INSERT INTO inventory_transaction VALUES (234428, 1, 5, 10, 2.59, 2, 1, 233055, '2020-10-04 00:00:00+08');


--
-- Name: inventory_transaction_inv_trx_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('inventory_transaction_inv_trx_id_seq', 1, false);


--
-- Name: layout_category_lay_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('layout_category_lay_cat_id_seq', 22, true);


--
-- Name: layout_lay_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('layout_lay_id_seq', 4, true);


--
-- Data for Name: order; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: order_line; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: organisation; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO organisation VALUES (233055, 'Taobao', 'NA');


--
-- Name: party_party_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('party_party_id_seq', 1, false);


--
-- Name: party_pty_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('party_pty_id_seq', 1, true);


--
-- Name: party_type_pty_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('party_type_pty_typ_id_seq', 1, false);


--
-- Data for Name: person; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO person VALUES (1, 'Daniel', 'Mackie', true);
INSERT INTO person VALUES (232230, 'Will', 'Parkhouse', true);
INSERT INTO person VALUES (232232, 'Will', 'Parkhouse', true);
INSERT INTO person VALUES (232234, 'zoro', 'zoro', true);
INSERT INTO person VALUES (232236, 'j', 'j', true);
INSERT INTO person VALUES (232238, 'x', 'x', true);
INSERT INTO person VALUES (232240, 'z', 'z', true);
INSERT INTO person VALUES (232242, 'h', 'h', true);
INSERT INTO person VALUES (232244, 'i', 'i', true);
INSERT INTO person VALUES (232246, 'f', 'f', true);
INSERT INTO person VALUES (232248, 'Alexia', 'V', true);
INSERT INTO person VALUES (232250, 'marley', 'macculloch', true);
INSERT INTO person VALUES (232252, 'Layla', 'MacCulloch', true);
INSERT INTO person VALUES (232254, 'bill', 'bill', true);
INSERT INTO person VALUES (232256, 'dan', 'dan', true);
INSERT INTO person VALUES (232258, 'dan', 'dan', true);
INSERT INTO person VALUES (232260, 'nod', 'nod', true);
INSERT INTO person VALUES (232262, 'Daniel', 'Mackie', true);
INSERT INTO person VALUES (232304, 'Daniel', 'Mackie', true);
INSERT INTO person VALUES (232310, 'Daniel', 'Mackie', true);
INSERT INTO person VALUES (232312, 'Daniel', 'Mackie', true);
INSERT INTO person VALUES (232216, 'billy', 'wong', true);
INSERT INTO person VALUES (232220, 'purple', 'wong', true);
INSERT INTO person VALUES (233016, 'adam', 'apple', false);
INSERT INTO person VALUES (233018, 'barry', 'white', false);
INSERT INTO person VALUES (233020, 'will', 'parkhouse', false);
INSERT INTO person VALUES (233022, 'john', 'wayne', false);
INSERT INTO person VALUES (233024, 'bob', 'bob', false);
INSERT INTO person VALUES (233054, 'Daniel', 'Mackie', false);


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('person_id_seq', 6, false);


--
-- Data for Name: price; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO price VALUES (1, 1, 1, 75, 1);
INSERT INTO price VALUES (2, 1, 2, 75, 1);
INSERT INTO price VALUES (3, 1, 3, 120, 1);
INSERT INTO price VALUES (4, 1, 4, 60, 1);
INSERT INTO price VALUES (5, 1, 5, 48, 1);
INSERT INTO price VALUES (6, 1, 6, 32, 1);
INSERT INTO price VALUES (7, 1, 7, 56, 1);
INSERT INTO price VALUES (8, 1, 8, 16, 1);
INSERT INTO price VALUES (9, 1, 9, 82, 1);
INSERT INTO price VALUES (10, 1, 10, 35, 1);
INSERT INTO price VALUES (11, 1, 11, 60, 1);
INSERT INTO price VALUES (12, 1, 12, 60, 1);
INSERT INTO price VALUES (13, 1, 13, 22, 1);
INSERT INTO price VALUES (14, 1, 14, 48, 1);
INSERT INTO price VALUES (15, 1, 15, 75, 1);
INSERT INTO price VALUES (16, 1, 16, 16, 1);
INSERT INTO price VALUES (17, 1, 17, 72, 1);
INSERT INTO price VALUES (18, 1, 18, 45, 1);
INSERT INTO price VALUES (19, 1, 19, 60, 1);
INSERT INTO price VALUES (20, 1, 20, 36, 1);
INSERT INTO price VALUES (21, 1, 21, 69, 1);
INSERT INTO price VALUES (22, 1, 22, 95, 1);
INSERT INTO price VALUES (23, 1, 23, 160, 1);
INSERT INTO price VALUES (24, 1, 24, 180, 1);
INSERT INTO price VALUES (25, 1, 25, 28, 1);
INSERT INTO price VALUES (26, 1, 26, 200, 1);
INSERT INTO price VALUES (27, 1, 27, 190, 1);
INSERT INTO price VALUES (28, 1, 28, 950, 1);
INSERT INTO price VALUES (29, 1, 29, 650, 1);
INSERT INTO price VALUES (30, 1, 30, 170, 1);
INSERT INTO price VALUES (41, 1, 11, 7.7, 2);
INSERT INTO price VALUES (71, 2, 11, 6.93, 2);
INSERT INTO price VALUES (91, 2, 1, 67.50, 1);
INSERT INTO price VALUES (92, 2, 2, 67.50, 1);
INSERT INTO price VALUES (93, 2, 3, 108.00, 1);
INSERT INTO price VALUES (94, 2, 4, 54.00, 1);
INSERT INTO price VALUES (95, 2, 5, 43.20, 1);
INSERT INTO price VALUES (96, 2, 6, 28.80, 1);
INSERT INTO price VALUES (97, 2, 7, 50.40, 1);
INSERT INTO price VALUES (98, 2, 8, 14.40, 1);
INSERT INTO price VALUES (99, 2, 9, 73.80, 1);
INSERT INTO price VALUES (100, 2, 10, 31.50, 1);
INSERT INTO price VALUES (101, 2, 11, 54.00, 1);
INSERT INTO price VALUES (102, 2, 12, 54.00, 1);
INSERT INTO price VALUES (103, 2, 13, 19.80, 1);
INSERT INTO price VALUES (104, 2, 14, 43.20, 1);
INSERT INTO price VALUES (105, 2, 15, 67.50, 1);
INSERT INTO price VALUES (106, 2, 16, 14.40, 1);
INSERT INTO price VALUES (107, 2, 17, 64.80, 1);
INSERT INTO price VALUES (108, 2, 18, 40.50, 1);
INSERT INTO price VALUES (109, 2, 19, 54.00, 1);
INSERT INTO price VALUES (110, 2, 20, 32.40, 1);
INSERT INTO price VALUES (111, 2, 21, 62.10, 1);
INSERT INTO price VALUES (112, 2, 22, 85.50, 1);
INSERT INTO price VALUES (113, 2, 23, 144.00, 1);
INSERT INTO price VALUES (114, 2, 24, 162.00, 1);
INSERT INTO price VALUES (115, 2, 25, 25.20, 1);
INSERT INTO price VALUES (116, 2, 26, 180.00, 1);
INSERT INTO price VALUES (117, 2, 27, 171.00, 1);
INSERT INTO price VALUES (118, 2, 28, 855.00, 1);
INSERT INTO price VALUES (119, 2, 29, 585.00, 1);
INSERT INTO price VALUES (120, 2, 30, 153.00, 1);
INSERT INTO price VALUES (233277, 1, 233275, 6.4, 2);
INSERT INTO price VALUES (233278, 2, 233275, 6.4, 2);
INSERT INTO price VALUES (233280, 1, 233275, 50, 1);
INSERT INTO price VALUES (233281, 2, 233275, 50, 1);
INSERT INTO price VALUES (233284, 1, 233282, 6.4, 2);
INSERT INTO price VALUES (233285, 2, 233282, 6.4, 2);
INSERT INTO price VALUES (233287, 1, 233282, 50, 1);
INSERT INTO price VALUES (233288, 2, 233282, 50, 1);
INSERT INTO price VALUES (233291, 1, 233289, 3.8, 2);
INSERT INTO price VALUES (233292, 2, 233289, 3.8, 2);
INSERT INTO price VALUES (233294, 1, 233289, 30, 1);
INSERT INTO price VALUES (233295, 2, 233289, 30, 1);
INSERT INTO price VALUES (233298, 1, 233296, 2.6, 2);
INSERT INTO price VALUES (233299, 2, 233296, 2.6, 2);
INSERT INTO price VALUES (233301, 1, 233296, 20, 1);
INSERT INTO price VALUES (233302, 2, 233296, 20, 1);
INSERT INTO price VALUES (233305, 1, 233303, 2.6, 2);
INSERT INTO price VALUES (233306, 2, 233303, 2.6, 2);
INSERT INTO price VALUES (233308, 1, 233303, 20, 1);
INSERT INTO price VALUES (233309, 2, 233303, 20, 1);
INSERT INTO price VALUES (233312, 1, 233310, 10.3, 2);
INSERT INTO price VALUES (233313, 2, 233310, 10.3, 2);
INSERT INTO price VALUES (233315, 1, 233310, 80, 1);
INSERT INTO price VALUES (233316, 2, 233310, 80, 1);
INSERT INTO price VALUES (233319, 1, 233317, 3.2, 2);
INSERT INTO price VALUES (233320, 2, 233317, 3.2, 2);
INSERT INTO price VALUES (233322, 1, 233317, 25, 1);
INSERT INTO price VALUES (233323, 2, 233317, 25, 1);
INSERT INTO price VALUES (233326, 1, 233324, 5.1, 2);
INSERT INTO price VALUES (233327, 2, 233324, 5.1, 2);
INSERT INTO price VALUES (233329, 1, 233324, 40, 1);
INSERT INTO price VALUES (233330, 2, 233324, 40, 1);
INSERT INTO price VALUES (233333, 1, 233331, 5.1, 2);
INSERT INTO price VALUES (233334, 2, 233331, 5.1, 2);
INSERT INTO price VALUES (233336, 1, 233331, 40, 1);
INSERT INTO price VALUES (233337, 2, 233331, 40, 1);
INSERT INTO price VALUES (233340, 1, 233338, 3.8, 2);
INSERT INTO price VALUES (233341, 2, 233338, 3.8, 2);
INSERT INTO price VALUES (233343, 1, 233338, 30, 1);
INSERT INTO price VALUES (233344, 2, 233338, 30, 1);
INSERT INTO price VALUES (233347, 1, 233345, 5.1, 2);
INSERT INTO price VALUES (233348, 2, 233345, 5.1, 2);
INSERT INTO price VALUES (233350, 1, 233345, 40, 1);
INSERT INTO price VALUES (233351, 2, 233345, 40, 1);
INSERT INTO price VALUES (233354, 1, 233352, 3.2, 2);
INSERT INTO price VALUES (233355, 2, 233352, 3.2, 2);
INSERT INTO price VALUES (233357, 1, 233352, 25, 1);
INSERT INTO price VALUES (233358, 2, 233352, 25, 1);
INSERT INTO price VALUES (233361, 1, 233359, 5.1, 2);
INSERT INTO price VALUES (233362, 2, 233359, 5.1, 2);
INSERT INTO price VALUES (233364, 1, 233359, 40, 1);
INSERT INTO price VALUES (233365, 2, 233359, 40, 1);
INSERT INTO price VALUES (233368, 1, 233366, 2.6, 2);
INSERT INTO price VALUES (233369, 2, 233366, 2.6, 2);
INSERT INTO price VALUES (233371, 1, 233366, 20, 1);
INSERT INTO price VALUES (233372, 2, 233366, 20, 1);
INSERT INTO price VALUES (233375, 1, 233373, 2.6, 2);
INSERT INTO price VALUES (233376, 2, 233373, 2.6, 2);
INSERT INTO price VALUES (233378, 1, 233373, 20, 1);
INSERT INTO price VALUES (233379, 2, 233373, 20, 1);
INSERT INTO price VALUES (233382, 1, 233380, 5.1, 2);
INSERT INTO price VALUES (233383, 2, 233380, 5.1, 2);
INSERT INTO price VALUES (233385, 1, 233380, 40, 1);
INSERT INTO price VALUES (233386, 2, 233380, 40, 1);
INSERT INTO price VALUES (233389, 1, 233387, 5.8, 2);
INSERT INTO price VALUES (233390, 2, 233387, 5.8, 2);
INSERT INTO price VALUES (233392, 1, 233387, 45, 1);
INSERT INTO price VALUES (233393, 2, 233387, 45, 1);
INSERT INTO price VALUES (233396, 1, 233394, 0.6, 2);
INSERT INTO price VALUES (233397, 2, 233394, 0.6, 2);
INSERT INTO price VALUES (233399, 1, 233394, 5, 1);
INSERT INTO price VALUES (233400, 2, 233394, 5, 1);
INSERT INTO price VALUES (233403, 1, 233401, 1.3, 2);
INSERT INTO price VALUES (233404, 2, 233401, 1.3, 2);
INSERT INTO price VALUES (233406, 1, 233401, 10, 1);
INSERT INTO price VALUES (233407, 2, 233401, 10, 1);
INSERT INTO price VALUES (233410, 1, 233408, 3.8, 2);
INSERT INTO price VALUES (233411, 2, 233408, 3.8, 2);
INSERT INTO price VALUES (233413, 1, 233408, 30, 1);
INSERT INTO price VALUES (233414, 2, 233408, 30, 1);
INSERT INTO price VALUES (233417, 1, 233415, 3.8, 2);
INSERT INTO price VALUES (233418, 2, 233415, 3.8, 2);
INSERT INTO price VALUES (233420, 1, 233415, 30, 1);
INSERT INTO price VALUES (233421, 2, 233415, 30, 1);
INSERT INTO price VALUES (233424, 1, 233422, 3.8, 2);
INSERT INTO price VALUES (233425, 2, 233422, 3.8, 2);
INSERT INTO price VALUES (233427, 1, 233422, 30, 1);
INSERT INTO price VALUES (233428, 2, 233422, 30, 1);
INSERT INTO price VALUES (233431, 1, 233429, 6.4, 2);
INSERT INTO price VALUES (233432, 2, 233429, 6.4, 2);
INSERT INTO price VALUES (233434, 1, 233429, 50, 1);
INSERT INTO price VALUES (233435, 2, 233429, 50, 1);
INSERT INTO price VALUES (233438, 1, 233436, 5.1, 2);
INSERT INTO price VALUES (233439, 2, 233436, 5.1, 2);
INSERT INTO price VALUES (233441, 1, 233436, 40, 1);
INSERT INTO price VALUES (233442, 2, 233436, 40, 1);
INSERT INTO price VALUES (233445, 1, 233443, 4.5, 2);
INSERT INTO price VALUES (233446, 2, 233443, 4.5, 2);
INSERT INTO price VALUES (233448, 1, 233443, 35, 1);
INSERT INTO price VALUES (233449, 2, 233443, 35, 1);
INSERT INTO price VALUES (233452, 1, 233450, 10.3, 2);
INSERT INTO price VALUES (233453, 2, 233450, 10.3, 2);
INSERT INTO price VALUES (233455, 1, 233450, 80, 1);
INSERT INTO price VALUES (233456, 2, 233450, 80, 1);
INSERT INTO price VALUES (233459, 1, 233457, 7.7, 2);
INSERT INTO price VALUES (233460, 2, 233457, 7.7, 2);
INSERT INTO price VALUES (233462, 1, 233457, 60, 1);
INSERT INTO price VALUES (233463, 2, 233457, 60, 1);
INSERT INTO price VALUES (233466, 1, 233464, 12.8, 2);
INSERT INTO price VALUES (233467, 2, 233464, 12.8, 2);
INSERT INTO price VALUES (233469, 1, 233464, 100, 1);
INSERT INTO price VALUES (233470, 2, 233464, 100, 1);
INSERT INTO price VALUES (233473, 1, 233471, 7.7, 2);
INSERT INTO price VALUES (233474, 2, 233471, 7.7, 2);
INSERT INTO price VALUES (233476, 1, 233471, 60, 1);
INSERT INTO price VALUES (233477, 2, 233471, 60, 1);
INSERT INTO price VALUES (233480, 1, 233478, 7.7, 2);
INSERT INTO price VALUES (233481, 2, 233478, 7.7, 2);
INSERT INTO price VALUES (233483, 1, 233478, 60, 1);
INSERT INTO price VALUES (233484, 2, 233478, 60, 1);
INSERT INTO price VALUES (233487, 1, 233485, 10.3, 2);
INSERT INTO price VALUES (233488, 2, 233485, 10.3, 2);
INSERT INTO price VALUES (233490, 1, 233485, 80, 1);
INSERT INTO price VALUES (233491, 2, 233485, 80, 1);
INSERT INTO price VALUES (233494, 1, 233492, 10.3, 2);
INSERT INTO price VALUES (233495, 2, 233492, 10.3, 2);
INSERT INTO price VALUES (233497, 1, 233492, 80, 1);
INSERT INTO price VALUES (233498, 2, 233492, 80, 1);
INSERT INTO price VALUES (233501, 1, 233499, 12.8, 2);
INSERT INTO price VALUES (233502, 2, 233499, 12.8, 2);
INSERT INTO price VALUES (233504, 1, 233499, 100, 1);
INSERT INTO price VALUES (233505, 2, 233499, 100, 1);
INSERT INTO price VALUES (233508, 1, 233506, 6.4, 2);
INSERT INTO price VALUES (233509, 2, 233506, 6.4, 2);
INSERT INTO price VALUES (233511, 1, 233506, 50, 1);
INSERT INTO price VALUES (233512, 2, 233506, 50, 1);
INSERT INTO price VALUES (233515, 1, 233513, 6.4, 2);
INSERT INTO price VALUES (233516, 2, 233513, 6.4, 2);
INSERT INTO price VALUES (233518, 1, 233513, 50, 1);
INSERT INTO price VALUES (233519, 2, 233513, 50, 1);
INSERT INTO price VALUES (233522, 1, 233520, 6.4, 2);
INSERT INTO price VALUES (233523, 2, 233520, 6.4, 2);
INSERT INTO price VALUES (233525, 1, 233520, 50, 1);
INSERT INTO price VALUES (233526, 2, 233520, 50, 1);
INSERT INTO price VALUES (233529, 1, 233527, 6.4, 2);
INSERT INTO price VALUES (233530, 2, 233527, 6.4, 2);
INSERT INTO price VALUES (233532, 1, 233527, 50, 1);
INSERT INTO price VALUES (233533, 2, 233527, 50, 1);
INSERT INTO price VALUES (233536, 1, 233534, 3.8, 2);
INSERT INTO price VALUES (233537, 2, 233534, 3.8, 2);
INSERT INTO price VALUES (233539, 1, 233534, 30, 1);
INSERT INTO price VALUES (233540, 2, 233534, 30, 1);
INSERT INTO price VALUES (233543, 1, 233541, 1.5, 2);
INSERT INTO price VALUES (233544, 2, 233541, 1.5, 2);
INSERT INTO price VALUES (233546, 1, 233541, 12, 1);
INSERT INTO price VALUES (233547, 2, 233541, 12, 1);
INSERT INTO price VALUES (233550, 1, 233548, 1.5, 2);
INSERT INTO price VALUES (233551, 2, 233548, 1.5, 2);
INSERT INTO price VALUES (233553, 1, 233548, 12, 1);
INSERT INTO price VALUES (233554, 2, 233548, 12, 1);
INSERT INTO price VALUES (233557, 1, 233555, 1.5, 2);
INSERT INTO price VALUES (233558, 2, 233555, 1.5, 2);
INSERT INTO price VALUES (233560, 1, 233555, 12, 1);
INSERT INTO price VALUES (233561, 2, 233555, 12, 1);
INSERT INTO price VALUES (233564, 1, 233562, 1.3, 2);
INSERT INTO price VALUES (233565, 2, 233562, 1.3, 2);
INSERT INTO price VALUES (233567, 1, 233562, 10, 1);
INSERT INTO price VALUES (233568, 2, 233562, 10, 1);
INSERT INTO price VALUES (233571, 1, 233569, 1.3, 2);
INSERT INTO price VALUES (233572, 2, 233569, 1.3, 2);
INSERT INTO price VALUES (233574, 1, 233569, 10, 1);
INSERT INTO price VALUES (233575, 2, 233569, 10, 1);
INSERT INTO price VALUES (233578, 1, 233576, 6.4, 2);
INSERT INTO price VALUES (233579, 2, 233576, 6.4, 2);
INSERT INTO price VALUES (233581, 1, 233576, 50, 1);
INSERT INTO price VALUES (233582, 2, 233576, 50, 1);
INSERT INTO price VALUES (233585, 1, 233583, 12.8, 2);
INSERT INTO price VALUES (233586, 2, 233583, 12.8, 2);
INSERT INTO price VALUES (233588, 1, 233583, 100, 1);
INSERT INTO price VALUES (233589, 2, 233583, 100, 1);
INSERT INTO price VALUES (233592, 1, 233590, 12.8, 2);
INSERT INTO price VALUES (233593, 2, 233590, 12.8, 2);
INSERT INTO price VALUES (233595, 1, 233590, 100, 1);
INSERT INTO price VALUES (233596, 2, 233590, 100, 1);
INSERT INTO price VALUES (233599, 1, 233597, 6.4, 2);
INSERT INTO price VALUES (233600, 2, 233597, 6.4, 2);
INSERT INTO price VALUES (233602, 1, 233597, 50, 1);
INSERT INTO price VALUES (233603, 2, 233597, 50, 1);
INSERT INTO price VALUES (233606, 1, 233604, 10.3, 2);
INSERT INTO price VALUES (233607, 2, 233604, 10.3, 2);
INSERT INTO price VALUES (233609, 1, 233604, 80, 1);
INSERT INTO price VALUES (233610, 2, 233604, 80, 1);
INSERT INTO price VALUES (233613, 1, 233611, 6.4, 2);
INSERT INTO price VALUES (233614, 2, 233611, 6.4, 2);
INSERT INTO price VALUES (233616, 1, 233611, 50, 1);
INSERT INTO price VALUES (233617, 2, 233611, 50, 1);
INSERT INTO price VALUES (233620, 1, 233618, 3.8, 2);
INSERT INTO price VALUES (233621, 2, 233618, 3.8, 2);
INSERT INTO price VALUES (233623, 1, 233618, 30, 1);
INSERT INTO price VALUES (233624, 2, 233618, 30, 1);
INSERT INTO price VALUES (233627, 1, 233625, 3.2, 2);
INSERT INTO price VALUES (233628, 2, 233625, 3.2, 2);
INSERT INTO price VALUES (233630, 1, 233625, 25, 1);
INSERT INTO price VALUES (233631, 2, 233625, 25, 1);
INSERT INTO price VALUES (233634, 1, 233632, 3.8, 2);
INSERT INTO price VALUES (233635, 2, 233632, 3.8, 2);
INSERT INTO price VALUES (233637, 1, 233632, 30, 1);
INSERT INTO price VALUES (233638, 2, 233632, 30, 1);
INSERT INTO price VALUES (233641, 1, 233639, 5.1, 2);
INSERT INTO price VALUES (233642, 2, 233639, 5.1, 2);
INSERT INTO price VALUES (233644, 1, 233639, 40, 1);
INSERT INTO price VALUES (233645, 2, 233639, 40, 1);
INSERT INTO price VALUES (233648, 1, 233646, 3.8, 2);
INSERT INTO price VALUES (233649, 2, 233646, 3.8, 2);
INSERT INTO price VALUES (233651, 1, 233646, 30, 1);
INSERT INTO price VALUES (233652, 2, 233646, 30, 1);
INSERT INTO price VALUES (233655, 1, 233653, 3.8, 2);
INSERT INTO price VALUES (233656, 2, 233653, 3.8, 2);
INSERT INTO price VALUES (233658, 1, 233653, 30, 1);
INSERT INTO price VALUES (233659, 2, 233653, 30, 1);
INSERT INTO price VALUES (233662, 1, 233660, 6.4, 2);
INSERT INTO price VALUES (233663, 2, 233660, 6.4, 2);
INSERT INTO price VALUES (233665, 1, 233660, 50, 1);
INSERT INTO price VALUES (233666, 2, 233660, 50, 1);
INSERT INTO price VALUES (233669, 1, 233667, 6.4, 2);
INSERT INTO price VALUES (233670, 2, 233667, 6.4, 2);
INSERT INTO price VALUES (233672, 1, 233667, 50, 1);
INSERT INTO price VALUES (233673, 2, 233667, 50, 1);
INSERT INTO price VALUES (233676, 1, 233674, 10.3, 2);
INSERT INTO price VALUES (233677, 2, 233674, 10.3, 2);
INSERT INTO price VALUES (233679, 1, 233674, 80, 1);
INSERT INTO price VALUES (233680, 2, 233674, 80, 1);
INSERT INTO price VALUES (233683, 1, 233681, 10.3, 2);
INSERT INTO price VALUES (233684, 2, 233681, 10.3, 2);
INSERT INTO price VALUES (233686, 1, 233681, 80, 1);
INSERT INTO price VALUES (233687, 2, 233681, 80, 1);
INSERT INTO price VALUES (233690, 1, 233688, 10.3, 2);
INSERT INTO price VALUES (233691, 2, 233688, 10.3, 2);
INSERT INTO price VALUES (233693, 1, 233688, 80, 1);
INSERT INTO price VALUES (233694, 2, 233688, 80, 1);
INSERT INTO price VALUES (233697, 1, 233695, 3.8, 2);
INSERT INTO price VALUES (233698, 2, 233695, 3.8, 2);
INSERT INTO price VALUES (233700, 1, 233695, 30, 1);
INSERT INTO price VALUES (233701, 2, 233695, 30, 1);
INSERT INTO price VALUES (233704, 1, 233702, 7.7, 2);
INSERT INTO price VALUES (233705, 2, 233702, 7.7, 2);
INSERT INTO price VALUES (233707, 1, 233702, 60, 1);
INSERT INTO price VALUES (233708, 2, 233702, 60, 1);
INSERT INTO price VALUES (233711, 1, 233709, 10.3, 2);
INSERT INTO price VALUES (233712, 2, 233709, 12.8, 2);
INSERT INTO price VALUES (233714, 1, 233709, 80, 1);
INSERT INTO price VALUES (233715, 2, 233709, 100, 1);
INSERT INTO price VALUES (233718, 1, 233716, 3.8, 2);
INSERT INTO price VALUES (233719, 2, 233716, 5.1, 2);
INSERT INTO price VALUES (233721, 1, 233716, 30, 1);
INSERT INTO price VALUES (233722, 2, 233716, 40, 1);
INSERT INTO price VALUES (233725, 1, 233723, 6.4, 2);
INSERT INTO price VALUES (233726, 2, 233723, 7.7, 2);
INSERT INTO price VALUES (233728, 1, 233723, 50, 1);
INSERT INTO price VALUES (233729, 2, 233723, 60, 1);
INSERT INTO price VALUES (233732, 1, 233730, 10.3, 2);
INSERT INTO price VALUES (233733, 2, 233730, 12.8, 2);
INSERT INTO price VALUES (233735, 1, 233730, 80, 1);
INSERT INTO price VALUES (233736, 2, 233730, 100, 1);
INSERT INTO price VALUES (56, 1, 26, 25.6, 2);
INSERT INTO price VALUES (58, 1, 28, 121.8, 2);
INSERT INTO price VALUES (43, 1, 13, 2.8, 2);
INSERT INTO price VALUES (34, 1, 4, 7.7, 2);
INSERT INTO price VALUES (37, 1, 7, 7.2, 2);
INSERT INTO price VALUES (39, 1, 9, 10.5, 2);
INSERT INTO price VALUES (60, 1, 30, 21.8, 2);
INSERT INTO price VALUES (50, 1, 20, 4.6, 2);
INSERT INTO price VALUES (51, 1, 21, 8.8, 2);
INSERT INTO price VALUES (42, 1, 12, 7.7, 2);
INSERT INTO price VALUES (49, 1, 19, 7.7, 2);
INSERT INTO price VALUES (59, 1, 29, 83.3, 2);
INSERT INTO price VALUES (48, 1, 18, 5.8, 2);
INSERT INTO price VALUES (31, 1, 1, 9.6, 2);
INSERT INTO price VALUES (46, 1, 16, 2.1, 2);
INSERT INTO price VALUES (47, 1, 17, 9.2, 2);
INSERT INTO price VALUES (40, 1, 10, 4.5, 2);
INSERT INTO price VALUES (45, 1, 15, 9.6, 2);
INSERT INTO price VALUES (38, 1, 8, 2.1, 2);
INSERT INTO price VALUES (52, 1, 22, 12.2, 2);
INSERT INTO price VALUES (44, 1, 14, 6.2, 2);
INSERT INTO price VALUES (36, 1, 6, 4.1, 2);
INSERT INTO price VALUES (53, 1, 23, 20.5, 2);
INSERT INTO price VALUES (54, 1, 24, 23.1, 2);
INSERT INTO price VALUES (57, 1, 27, 24.4, 2);
INSERT INTO price VALUES (32, 1, 2, 9.6, 2);
INSERT INTO price VALUES (33, 1, 3, 15.4, 2);
INSERT INTO price VALUES (35, 1, 5, 6.2, 2);
INSERT INTO price VALUES (55, 1, 25, 3.6, 2);
INSERT INTO price VALUES (74, 2, 14, 5.5, 2);
INSERT INTO price VALUES (80, 2, 20, 4.2, 2);
INSERT INTO price VALUES (77, 2, 17, 8.3, 2);
INSERT INTO price VALUES (67, 2, 7, 6.5, 2);
INSERT INTO price VALUES (72, 2, 12, 6.9, 2);
INSERT INTO price VALUES (89, 2, 29, 75, 2);
INSERT INTO price VALUES (90, 2, 30, 19.6, 2);
INSERT INTO price VALUES (88, 2, 28, 109.6, 2);
INSERT INTO price VALUES (62, 2, 2, 8.7, 2);
INSERT INTO price VALUES (75, 2, 15, 8.7, 2);
INSERT INTO price VALUES (70, 2, 10, 4, 2);
INSERT INTO price VALUES (87, 2, 27, 21.9, 2);
INSERT INTO price VALUES (81, 2, 21, 8, 2);
INSERT INTO price VALUES (86, 2, 26, 23.1, 2);
INSERT INTO price VALUES (84, 2, 24, 20.8, 2);
INSERT INTO price VALUES (85, 2, 25, 3.2, 2);
INSERT INTO price VALUES (76, 2, 16, 1.8, 2);
INSERT INTO price VALUES (66, 2, 6, 3.7, 2);
INSERT INTO price VALUES (82, 2, 22, 11, 2);
INSERT INTO price VALUES (83, 2, 23, 18.5, 2);
INSERT INTO price VALUES (63, 2, 3, 13.8, 2);
INSERT INTO price VALUES (69, 2, 9, 9.5, 2);
INSERT INTO price VALUES (64, 2, 4, 6.9, 2);
INSERT INTO price VALUES (73, 2, 13, 2.5, 2);
INSERT INTO price VALUES (78, 2, 18, 5.2, 2);
INSERT INTO price VALUES (79, 2, 19, 6.9, 2);
INSERT INTO price VALUES (68, 2, 8, 1.8, 2);
INSERT INTO price VALUES (61, 2, 1, 8.7, 2);
INSERT INTO price VALUES (65, 2, 5, 5.5, 2);


--
-- Name: price_prc_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('price_prc_id_seq', 120, true);


--
-- Data for Name: price_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO price_type VALUES (1, 'retail', 'RET01');
INSERT INTO price_type VALUES (2, 'markdown', 'MKD01');


--
-- Data for Name: product_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO product_attr_lcl VALUES (22, 11, '辣椒', 'capsicum.jpg', 'zh-HK', NULL);
INSERT INTO product_attr_lcl VALUES (21, 11, 'capsicum', 'capsicum.jpg', 'en-GB', NULL);
INSERT INTO product_attr_lcl VALUES (233276, 233275, 'High Class See through Rose Packaging box (Leather paper)', '30833030_1.jpg', 'en-GB', 'High Class See through Rose Packaging box, with 1 layer draw for earrings. Great for gifts presentation ');
INSERT INTO product_attr_lcl VALUES (233279, 233275, '高檔花盒透明玫瑰花禮盒 ( 高質皮紙) ', '30833030_1.jpg', 'zh-HK', '高檔透明玫瑰花禮盒 有一層櫃桶裝耳環送禮一流');
INSERT INTO product_attr_lcl VALUES (233283, 233282, 'High Class See through Rose Packaging box ( Plastic)', '30833031_1.jpg', 'en-GB', 'High Class See through Rose Packaging box, with 1 layer draw for earrings. Great for gifts presentation ');
INSERT INTO product_attr_lcl VALUES (233286, 233282, '高檔花盒透明玫瑰花禮盒 (高質膠)', '30833031_1.jpg', 'zh-HK', '高檔透明玫瑰花禮盒 有一層櫃桶裝耳環送禮一流');
INSERT INTO product_attr_lcl VALUES (233290, 233289, 'Korean Big White Flowers pretty Earrings ', '30833032_1.jpg', 'en-GB', 'Elegant, Pretty, Sweet Big White Flower earrings  ');
INSERT INTO product_attr_lcl VALUES (233293, 233289, '韓國大白茶花精緻耳環', '30833032_1.jpg', 'zh-HK', '高氣質,時尚,甜美大白茶花耳環');
INSERT INTO product_attr_lcl VALUES (233297, 233296, 'Korean Fashion Stripey Hairband', '30833033_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233300, 233296, '韓國時尚條紋頭箍', '30833033_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233304, 233303, 'Heart Shaped Earrings ', '30833034_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233307, 233303, '心形耳環', '30833034_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233311, 233310, 'Vintage Beige and Black Hair Clip with gold colour logo ', '30833035_1.jpg', 'en-GB', '8.8 x 1.8 cm');
INSERT INTO product_attr_lcl VALUES (233314, 233310, '古代小香奶茶色髮夾', '30833035_1.jpg', 'zh-HK', '8.8 x 1.8 cm');
INSERT INTO product_attr_lcl VALUES (233318, 233317, 'S925 needle Korean Big Pearly dingle Earrings ', '30833036_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233321, 233317, 'S925銀針韓國優雅復古珍珠耳環', '30833036_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233325, 233324, 'S925 needle Korean Spaceman Big Moon Earrings ', '30833037_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233328, 233324, 'S925銀針宇宙大空人月亮耳環', '30833037_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233332, 233331, 'Korea Cute Fried Egg Necklace ', '30833038_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233335, 233331, '愛心大煎蛋項鏈(如圖包鏈) ', '30833038_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233339, 233338, 'Waterproof Outer Blue Makeup Wallet pouch 18x12cm', '30833039_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233342, 233338, '防水藍色18x12cm化妝袋,銀包仔', '30833039_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233346, 233345, 'Single Flower Clover Cosmos Necklace', '30833040_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233349, 233345, '靚靚斯文小小四葉花氣質鎖骨項鏈', '30833040_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233353, 233352, 'Rainbow colour smiley face flower Earrings ', '30833041_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233356, 233352, '彩虹花花開心笑耳環', '30833041_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233360, 233359, 'Korean Smiley face on pearly white shell Earrings ', '30833042_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233363, 233359, '韓國開心笑珍珠貝耳環', '30833042_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233367, 233366, 'Happy Yellow Flowers Clip On Earrings', '30833043_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233370, 233366, '開心大向日葵花花耳夾', '30833043_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233374, 233373, 'Happy Green Flowers Clip On Earrings', '30833044_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233377, 233373, '開心大綠花耳夾', '30833044_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233381, 233380, 'Korean white tea flower elegant pearls and crystals Earrings ', '30833045_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233384, 233380, '韓國白茶花配水晶珍珠氣質仙女耳環', '30833045_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233388, 233387, 'Korean sweet pink ribbon bunny rabbit Earrings', '30833046_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233391, 233387, '韓國甜美花朵蝴蝶可愛兔仔耳環', '30833046_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233395, 233394, 'Pretty Girl Hair Sticker ', '30833047_1.jpg', 'en-GB', '10 x 6 cm ');
INSERT INTO product_attr_lcl VALUES (233398, 233394, '可愛美少女頭髮貼可愛', '30833047_1.jpg', 'zh-HK', '10 x 6 cm ');
INSERT INTO product_attr_lcl VALUES (233402, 233401, 'Multicolour flower Hair Bobbles ', '30833048_1.jpg', 'en-GB', 'Each pack contains 6 flowery Hair bobbles in multiple colours');
INSERT INTO product_attr_lcl VALUES (233405, 233401, '韓國可愛花花頭髮橡筋', '30833048_1.jpg', 'zh-HK', '每pack 6條不同顔色的橡筋');
INSERT INTO product_attr_lcl VALUES (233409, 233408, 'Scottish Hendoz Multi purpose Navy Blue with white dots Crossbody Bag', '30833049_1.jpg', 'en-GB', '19 x 7 x 26 cm , zipped outer pocket, hidden pocket inside. Great for café, supermarket, restaurnat outings. Bag is roomy can easily fit in wallet, umbrella, masks, tissues, handphones and keys. ');
INSERT INTO product_attr_lcl VALUES (233412, 233408, '新款蘇格蘭亨多斯深藍色多用途斜孭包 ', '30833049_1.jpg', 'zh-HK', '19 x 7 x 26 cm ,拉鏈外袋, 內有暗袋買餸食飯休閒可以百搭用可以放好多嘢㗎');
INSERT INTO product_attr_lcl VALUES (233416, 233415, 'Scottish Hendoz Multi purpose Black with pink dots Crossbody Bag', '30833050_1.jpg', 'en-GB', '19 x 7 x 26 cm , zipped outer pocket, hidden pocket inside. Great for café, supermarket, restaurnat outings. Bag is roomy can easily fit in wallet, umbrella, masks, tissues, handphones and keys. ');
INSERT INTO product_attr_lcl VALUES (233419, 233415, '新款蘇格蘭亨多斯黑色多用途斜孭包 ', '30833050_1.jpg', 'zh-HK', '19 x 7 x 26 cm ,拉鏈外袋, 內有暗袋買餸食飯休閒可以百搭用可以放好多嘢㗎');
INSERT INTO product_attr_lcl VALUES (233423, 233422, 'Scottish Hendoz Multi purpose Pink with green dots Crossbody Bag', '30833051_1.jpg', 'en-GB', '19 x 7 x 26 cm , zipped outer pocket, hidden pocket inside. Great for café, supermarket, restaurnat outings. Bag is roomy can easily fit in wallet, umbrella, masks, tissues, handphones and keys. ');
INSERT INTO product_attr_lcl VALUES (233426, 233422, '新款蘇格蘭亨多斯粉紅色多用途斜孭包 ', '30833051_1.jpg', 'zh-HK', '19 x 7 x 26 cm ,拉鏈外袋, 內有暗袋買餸食飯休閒可以百搭用可以放好多嘢㗎');
INSERT INTO product_attr_lcl VALUES (233430, 233429, 'LBS Brown small Crossbody Bag', '30833052_1.jpg', 'en-GB', '18 x 5 x 13 cm, High quality PU material Bag Weight: 160g  ');
INSERT INTO product_attr_lcl VALUES (233433, 233429, '新款啡色LBS版休閒斜孭女包', '30833052_1.jpg', 'zh-HK', '18 x 5 x 13 cm, 高質PU 重量:160g');
INSERT INTO product_attr_lcl VALUES (233437, 233436, 'LBS Brown wallet Clip Bag', '30833029_1.jpg', 'en-GB', '18.5 x 5.5 x 11 cm , High quality PU material Bag Weight: 130g ');
INSERT INTO product_attr_lcl VALUES (233440, 233436, '新款啡色LBS金扣休閒斜孭女包', '30833029_1.jpg', 'zh-HK', '18.5 x 5.5 x 11 cm, 高質PU 重量:130g');
INSERT INTO product_attr_lcl VALUES (233444, 233443, 'Korean Cute White Rabbit FlipFlops (EU37) ', '30833053_1.jpg', 'en-GB', 'Can wear outdoor or indoor ');
INSERT INTO product_attr_lcl VALUES (233447, 233443, '韓國可愛兔兔人字拖女室內室外(EU37)', '30833053_1.jpg', 'zh-HK', '室內室外也可以');
INSERT INTO product_attr_lcl VALUES (233451, 233450, 'Korean Slim Waist Bag Fanny Pack (White) ', '30833054_1.jpg', 'en-GB', '31 x 18.5 x 8.5 cm, Adjustable body strap. Secure clasp fastening. Zip Closure  Material: Polyester ');
INSERT INTO product_attr_lcl VALUES (233454, 233450, '韓國多功能胸包', '30833054_1.jpg', 'zh-HK', '31 x 18.5 x 8.5 cm, ');
INSERT INTO product_attr_lcl VALUES (233458, 233457, 'White 4cm Platform Slipons', '30833055_1.jpg', 'en-GB', '4cm platform, Soft sole ');
INSERT INTO product_attr_lcl VALUES (233461, 233457, '韓國厚底4cm白色女拖鞋 (EU38)', '30833055_1.jpg', 'zh-HK', '厚底4cm');
INSERT INTO product_attr_lcl VALUES (233465, 233464, 'Unisex Korean Black Sport BackPack', '30833056_1.jpg', 'en-GB', '40 x 14 x 31 cm Can fit A4 size, laptops, water bottles. Multi-purpose bag,  great for Man and Woman ');
INSERT INTO product_attr_lcl VALUES (233468, 233464, '中性韓版黑色運動背包', '30833056_1.jpg', 'zh-HK', '40 x 14 x 31 cm. 可以容納A4尺寸書，手提電腦，水壺。非常適合男女的多功能袋');
INSERT INTO product_attr_lcl VALUES (233472, 233471, 'Unisex Purple Sporty Student Postman Crossbody Bag', '30833057_1.jpg', 'en-GB', '16 x 10 x 25 cm, straps max length :130cm ');
INSERT INTO product_attr_lcl VALUES (233475, 233471, '中性紫色運動學生郵差斜孭包', '30833057_1.jpg', 'zh-HK', '16 x 10 x 25 cm, 帶最大長度: 130 cm');
INSERT INTO product_attr_lcl VALUES (233479, 233478, 'Unisex Black Sporty Student Postman Crossbody Bag', '30833058_1.jpg', 'en-GB', '16 x 10 x 25 cm, straps max length :130cm ');
INSERT INTO product_attr_lcl VALUES (233482, 233478, '中性黑色運動學生郵差斜孭包', '30833058_1.jpg', 'zh-HK', '16 x 10 x 25 cm, 帶最大長度: 130 cm');
INSERT INTO product_attr_lcl VALUES (233486, 233485, 'Black with White Text Bump Bag (Mens) ', '30833059_1.jpg', 'en-GB', '40 x 20 x 19 cm, Material: Nylon');
INSERT INTO product_attr_lcl VALUES (233489, 233485, '黑底白字男多功能胸包', '30833059_1.jpg', 'zh-HK', '40 x 20 x 19 cm, 尼龍');
INSERT INTO product_attr_lcl VALUES (233493, 233492, 'Black with Gold Text Bump Bag (Mens) ', '30833060_1.jpg', 'en-GB', '40 x 20 x 19 cm, Material: Nylon ');
INSERT INTO product_attr_lcl VALUES (233496, 233492, '黑底金字男多功能胸包', '30833060_1.jpg', 'zh-HK', '40 x 20 x 19 cm, 尼龍');
INSERT INTO product_attr_lcl VALUES (233500, 233499, 'Multi purpose Black checkered patten BackPack', '30833061_1.jpg', 'en-GB', '36 x 14 x 26 cm, Material: Oxford');
INSERT INTO product_attr_lcl VALUES (233503, 233499, '多用途黑色格紋背囊', '30833061_1.jpg', 'zh-HK', '36 x 14 x 26 cm, 牛津布');
INSERT INTO product_attr_lcl VALUES (233507, 233506, 'Japanese Sukura Pretty Mama Blue background Crossbody Bag', '30833062_1.jpg', 'en-GB', '23 x 15 x 13 cm, Material:  Nylon ');
INSERT INTO product_attr_lcl VALUES (233510, 233506, '日式花紋靚靚媽咪斜孭袋(藍色)', '30833062_1.jpg', 'zh-HK', '23 x 15 x 13 cm,  尼龍');
INSERT INTO product_attr_lcl VALUES (233514, 233513, 'Japanese Sukura Pretty Mama Purple background Crossbody Bag', '30833063_1.jpg', 'en-GB', '23 x 15 x 13 cm, Material:  Nylon ');
INSERT INTO product_attr_lcl VALUES (233517, 233513, '日式花紋靚靚媽咪斜孭袋(紫色)', '30833063_1.jpg', 'zh-HK', '23 x 15 x 13 cm,  尼龍');
INSERT INTO product_attr_lcl VALUES (233521, 233520, 'Oxford Bag Pretty Mama Crossbody Bag (Black) ', '30833064_1.jpg', 'en-GB', '20 x 29 x 10 cm, Material: Oxford');
INSERT INTO product_attr_lcl VALUES (233524, 233520, '牛津布靚靚媽咪防水尼龍帆布多用途斜孭袋(黑色)', '30833064_1.jpg', 'zh-HK', '20 x 29 x 10 cm, 牛津布');
INSERT INTO product_attr_lcl VALUES (233528, 233527, 'Oxford Bag Pretty Mama Crossbody Bag (Navy Blue)', '30833065_1.jpg', 'en-GB', '20 x 29 x 10 cm, Material: Oxford');
INSERT INTO product_attr_lcl VALUES (233531, 233527, '牛津布靚靚媽咪防水尼龍帆布多用途斜孭袋(寶藍色)', '30833065_1.jpg', 'zh-HK', '20 x 29 x 10 cm, 牛津布');
INSERT INTO product_attr_lcl VALUES (233535, 233534, 'Pink Hearts Pretty Wallet ', '30833066_1.jpg', 'en-GB', '18 x 6 x 12 cm ');
INSERT INTO product_attr_lcl VALUES (233538, 233534, '粉紅心心長銀包', '30833066_1.jpg', 'zh-HK', '18 x 6 x 12 cm ');
INSERT INTO product_attr_lcl VALUES (233542, 233541, 'A4 Sized zipped with handle File Pocket (Farting bear) ', '30833067_1.jpg', 'en-GB', 'A4');
INSERT INTO product_attr_lcl VALUES (233545, 233541, 'A4放屁大能拉鍊文件袋', '30833067_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233549, 233548, 'A4 Sized zipped with handle File Pocket (Blue pigs) ', '30833068_1.jpg', 'en-GB', 'A4');
INSERT INTO product_attr_lcl VALUES (233552, 233548, 'A4飛天豬仔拉鍊文件袋', '30833068_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233556, 233555, 'A4 Sized zipped with handle File Pocket (Pink pig) ', '30833069_1.jpg', 'en-GB', 'A4');
INSERT INTO product_attr_lcl VALUES (233559, 233555, 'A4粉紅為食豬拉鍊文件袋', '30833069_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233563, 233562, 'A6 sized zipped pink bunny Pen Bag ', '30833070_1.jpg', 'en-GB', 'A6 , Material: PU ');
INSERT INTO product_attr_lcl VALUES (233566, 233562, 'A6粉紅小兔筆袋', '30833070_1.jpg', 'zh-HK', 'A6 , Material: PU ');
INSERT INTO product_attr_lcl VALUES (233570, 233569, 'A6 sized zipped yellow duckling Pen Bag ', '30833071_1.jpg', 'en-GB', 'A6 , Material: PU ');
INSERT INTO product_attr_lcl VALUES (233573, 233569, 'A6黃色小鴨筆袋', '30833071_1.jpg', 'zh-HK', 'A6 , Material: PU ');
INSERT INTO product_attr_lcl VALUES (233577, 233576, 'Chic Sporty Hobo underarm Bag (Black) ', '30833072_1.jpg', 'en-GB', '18 x 22 x 7 cm, 裝到好多嘢嘅潮物! ');
INSERT INTO product_attr_lcl VALUES (233580, 233576, '少女運動腋下小手袋 (黑色)', '30833072_1.jpg', 'zh-HK', '18 x 22 x 7 cm, looks small but it can fit a lot! ');
INSERT INTO product_attr_lcl VALUES (233584, 233583, 'Circle Carpet (Black) ', '30833073_1.jpg', 'en-GB', 'Diameter: 60cm High quality manmade velvet Suitable for bedroom,kitchen,toilet,front door!');
INSERT INTO product_attr_lcl VALUES (233587, 233583, '圓圈地毯 (黑色)', '30833073_1.jpg', 'zh-HK', '直徑: 60cm 優質水晶絨 房間,化妝間,廁所,廚房門口,度度都擺得! ');
INSERT INTO product_attr_lcl VALUES (233591, 233590, 'Circle Carpet (Pink) ', '30833074_1.jpg', 'en-GB', 'Diameter: 60cm High quality manmade velvet');
INSERT INTO product_attr_lcl VALUES (233594, 233590, '圓圈地毯 (粉紅色)', '30833074_1.jpg', 'zh-HK', '直徑: 60cm 優質水晶絨');
INSERT INTO product_attr_lcl VALUES (233598, 233597, 'Little Mouse Waterproof PU material Bump Bag (White) ', '30833075_1.jpg', 'en-GB', '13 x 30 x 8 cm , PU  Perfect to wipe with disinfecting agents');
INSERT INTO product_attr_lcl VALUES (233601, 233597, '小老鼠防水PU腰包袋白色', '30833075_1.jpg', 'zh-HK', '13 x 30 x 8 cm, 超靚, 防疫一流, 消毒濕紙巾,抹一萬次都唔怕');
INSERT INTO product_attr_lcl VALUES (233605, 233604, 'Little Mouse Waterproof PU material Cylinder Bag (Brown) ', '30833076_1.jpg', 'en-GB', '21 x 9 x 9 cm');
INSERT INTO product_attr_lcl VALUES (233608, 233604, '小老鼠防水PU圓筒包包袋啡色', '30833076_1.jpg', 'zh-HK', '21 x 9 x 9 cm');
INSERT INTO product_attr_lcl VALUES (233612, 233611, 'Black mobile pouch with gold square twist lock ', '30833077_1.jpg', 'en-GB', '18 x 4.5 x 13 cm ');
INSERT INTO product_attr_lcl VALUES (233615, 233611, '方形金扣PU手機袋(黑色)', '30833077_1.jpg', 'zh-HK', '18 x 4.5 x 13 cm ');
INSERT INTO product_attr_lcl VALUES (233619, 233618, 'Black Phone Cover ', '30833078_1.jpg', 'en-GB', 'Suitable Apple 11 Pro');
INSERT INTO product_attr_lcl VALUES (233622, 233618, '黑色手機套', '30833078_1.jpg', 'zh-HK', '適合 Apple 11 Pro');
INSERT INTO product_attr_lcl VALUES (233626, 233625, 'Coin Wallet With Zips ( can fit cards and coins) ', '30833079_1.jpg', 'en-GB', '10.5 x 2 x 7.5 cm');
INSERT INTO product_attr_lcl VALUES (233629, 233625, '拉鏈錢Coin包', '30833079_1.jpg', 'zh-HK', '10.5 x 2 x 7.5 cm');
INSERT INTO product_attr_lcl VALUES (233633, 233632, 'Small D Simple Wallet ', '30833080_1.jpg', 'en-GB', '9 x 1.5 x 11.5 cm');
INSERT INTO product_attr_lcl VALUES (233636, 233632, '小號D簡約錢包', '30833080_1.jpg', 'zh-HK', '9 x 1.5 x 11.5 cm');
INSERT INTO product_attr_lcl VALUES (233640, 233639, 'VC Lucky Flower Earrings ( Silver with white pearly shells) ', '30833081_1.jpg', 'en-GB', '');
INSERT INTO product_attr_lcl VALUES (233643, 233639, 'VC 銀色配白色珍珠貝幸運花花耳環', '30833081_1.jpg', 'zh-HK', '');
INSERT INTO product_attr_lcl VALUES (233647, 233646, 'Busy Bee Red Wallet ', '30833082_1.jpg', 'en-GB', '9 x 12 x 1.5 cm');
INSERT INTO product_attr_lcl VALUES (233650, 233646, '忙碌小的蜜蜂高貴紅色銀包', '30833082_1.jpg', 'zh-HK', '9 x 12 x 1.5 cm');
INSERT INTO product_attr_lcl VALUES (233654, 233653, 'Busy Bee Green Wallet ', '30833083_1.jpg', 'en-GB', '9 x 12 x 1.5 cm');
INSERT INTO product_attr_lcl VALUES (233657, 233653, '忙碌小的蜜蜂高貴綠色銀包', '30833083_1.jpg', 'zh-HK', '9 x 12 x 1.5 cm');
INSERT INTO product_attr_lcl VALUES (233661, 233660, 'Brown mobile pouch with gold square twist lock ', '30833084_1.jpg', 'en-GB', '18 x 4.5 x 13 cm , Brown');
INSERT INTO product_attr_lcl VALUES (233664, 233660, '方形金扣PU手機袋(啡色)', '30833084_1.jpg', 'zh-HK', '18 x 4.5 x 13 cm ,啡色');
INSERT INTO product_attr_lcl VALUES (233668, 233667, 'Red mobile pouch with gold square twist lock ', '30833085_1.jpg', 'en-GB', '18 x 4.5 x 13 cm , Brown');
INSERT INTO product_attr_lcl VALUES (233671, 233667, '方形金扣PU手機袋(红色)', '30833085_1.jpg', 'zh-HK', '18 x 4.5 x 13 cm ,红色');
INSERT INTO product_attr_lcl VALUES (233675, 233674, 'HAPPYLEAF Unisex multi colour pure cotton sport socks (gift pack of 5 pairs) ', '30833086_1.jpg', 'en-GB', 'Cotton Material, Anti slippery, it is a gift box set of 5. One size fit all ( EU36-43) ');
INSERT INTO product_attr_lcl VALUES (233678, 233674, 'HAPPYLEAF男女潮流襪純棉中筒運動襪 (禮盒5對裝)', '30833086_1.jpg', 'zh-HK', '純棉, 吸汗防滑, 禮盒5對裝, 均碼36至43');
INSERT INTO product_attr_lcl VALUES (233682, 233681, 'HAPPYLEAF F* OFF Slippers (White) ', '30833087_1.jpg', 'en-GB', 'Size EU40');
INSERT INTO product_attr_lcl VALUES (233685, 233681, 'HAPPYLEAF男女潮流白色運動拖鞋', '30833087_1.jpg', 'zh-HK', 'EU40碼');
INSERT INTO product_attr_lcl VALUES (233689, 233688, 'HAPPYLEAF F* OFF Slippers (Black) ', '30833088_1.jpg', 'en-GB', 'Size EU40');
INSERT INTO product_attr_lcl VALUES (233692, 233688, 'HAPPYLEAF男女潮流黑色運動拖鞋', '30833088_1.jpg', 'zh-HK', 'EU40碼');
INSERT INTO product_attr_lcl VALUES (233696, 233695, 'Korean Style Pink Flat Slippers ', '30833089_1.jpg', 'en-GB', 'Pink, Size EU39 ');
INSERT INTO product_attr_lcl VALUES (233699, 233695, '粉紅色平底韓版女拖鞋', '30833089_1.jpg', 'zh-HK', '粉紅色, Size EU39');
INSERT INTO product_attr_lcl VALUES (233703, 233702, 'Hurry Up "On my way" Red and Black Crossbody Bag', '30833090_1.jpg', 'en-GB', 'Bag: 14 x 20 x 1 cm , Straps : 120cm , Material:  Oxford Cloth ');
INSERT INTO product_attr_lcl VALUES (233706, 233702, '快D 快 D "On my way" 红黑斜孭袋', '30833090_1.jpg', 'zh-HK', '袋: 14 x 20 x 1 cm , 帶: 120cm , 布料: 牛津布');
INSERT INTO product_attr_lcl VALUES (233710, 233709, 'HAPPYLEAR Unisex White with happy green leaf BackPack  ', '30833091_1.jpg', 'en-GB', 'Bag: 38 x 28 x 11 cm ');
INSERT INTO product_attr_lcl VALUES (233713, 233709, 'HAPPYLEAF中性男女運動背包囊', '30833091_1.jpg', 'zh-HK', '38 x 28 x 11 cm ');
INSERT INTO product_attr_lcl VALUES (233717, 233716, 'Elegant Beach Off white flowers Flipflop ', '30833092_1.jpg', 'en-GB', 'Size EU39 (24cm)');
INSERT INTO product_attr_lcl VALUES (233720, 233716, '高雅花花米白沙灘人字拖', '30833092_1.jpg', 'zh-HK', 'Size EU39 (24cm)');
INSERT INTO product_attr_lcl VALUES (233724, 233723, 'Little Mouse Waterproof PU material LIttle Tote Bag', '30833093_1.jpg', 'en-GB', 'Bag: 26 x 26 x 11 cm,  Material:  PU');
INSERT INTO product_attr_lcl VALUES (233727, 233723, '小老鼠防水PU小手袋', '30833093_1.jpg', 'zh-HK', '袋:26 x 26 x 11 cm,  料:  PU');
INSERT INTO product_attr_lcl VALUES (233731, 233730, 'HAPPYLEAF Unisex White with happy green leaf Crossbody Bag', '30833094_1.jpg', 'en-GB', 'Bag: 30 x 30 x 13 cm');
INSERT INTO product_attr_lcl VALUES (233734, 233730, 'HAPPYLEAF中性男女運動斜孭袋', '30833094_1.jpg', 'zh-HK', '袋: 30 x 30 x 13 cm');
INSERT INTO product_attr_lcl VALUES (27, 14, 'Pumpkin', 'pumpkin.jpg', 'en-GB', 'Pumpkin');
INSERT INTO product_attr_lcl VALUES (28, 14, '南瓜', 'pumpkin.jpg', 'zh-HK', '南瓜');
INSERT INTO product_attr_lcl VALUES (39, 20, 'Musk Melon', 'musk-melon.jpg', 'en-GB', 'Musk Melon');
INSERT INTO product_attr_lcl VALUES (40, 20, '麝香甜瓜', 'musk-melon.jpg', 'zh-HK', '麝香甜瓜');
INSERT INTO product_attr_lcl VALUES (33, 17, 'Apple', 'apple.jpg', 'en-GB', 'Apple');
INSERT INTO product_attr_lcl VALUES (34, 17, '蘋果', 'apple.jpg', 'zh-HK', '蘋果');
INSERT INTO product_attr_lcl VALUES (13, 7, 'carrot', 'carrots.jpg', 'en-GB', 'carrot');
INSERT INTO product_attr_lcl VALUES (14, 7, '胡萝卜', 'carrots.jpg', 'zh-HK', '胡萝卜');
INSERT INTO product_attr_lcl VALUES (23, 12, 'mushroom', 'button-mushroom.jpg', 'en-GB', 'mushroom');
INSERT INTO product_attr_lcl VALUES (24, 12, '蘑菇', 'button-mushroom.jpg', 'zh-HK', '蘑菇');
INSERT INTO product_attr_lcl VALUES (57, 29, 'Cashews', 'cashews.jpg', 'en-GB', 'Cashews');
INSERT INTO product_attr_lcl VALUES (58, 29, '腰果', 'cashews.jpg', 'zh-HK', '腰果');
INSERT INTO product_attr_lcl VALUES (59, 30, 'Walnuts', 'walnuts.jpg', 'en-GB', 'Walnuts');
INSERT INTO product_attr_lcl VALUES (60, 30, '核桃', 'walnuts.jpg', 'zh-HK', '核桃');
INSERT INTO product_attr_lcl VALUES (55, 28, 'Nuts Mixture', 'nuts-mixture.jpg', 'en-GB', 'Nuts Mixture');
INSERT INTO product_attr_lcl VALUES (56, 28, '堅果混合物', 'nuts-mixture.jpg', 'zh-HK', '堅果混合物');
INSERT INTO product_attr_lcl VALUES (3, 2, 'orange', 'orange.jpg', 'en-GB', 'orange');
INSERT INTO product_attr_lcl VALUES (4, 2, '橙子', 'orange.jpg', 'zh-HK', '橙子');
INSERT INTO product_attr_lcl VALUES (29, 15, 'Corn', 'corn.jpg', 'en-GB', 'Corn');
INSERT INTO product_attr_lcl VALUES (30, 15, '玉米', 'corn.jpg', 'zh-HK', '玉米');
INSERT INTO product_attr_lcl VALUES (19, 10, 'brinjal', 'brinjal.jpg', 'en-GB', 'brinjal');
INSERT INTO product_attr_lcl VALUES (20, 10, '茄子', 'brinjal.jpg', 'zh-HK', '茄子');
INSERT INTO product_attr_lcl VALUES (53, 27, 'Pistachio', 'pistachio.jpg', 'en-GB', 'Pistachio');
INSERT INTO product_attr_lcl VALUES (54, 27, '開心果', 'pistachio.jpg', 'zh-HK', '開心果');
INSERT INTO product_attr_lcl VALUES (41, 21, 'Pears', 'pears.jpg', 'en-GB', 'Pears');
INSERT INTO product_attr_lcl VALUES (42, 21, '梨', 'pears.jpg', 'zh-HK', '梨');
INSERT INTO product_attr_lcl VALUES (51, 26, 'Almonds', 'almonds.jpg', 'en-GB', 'Almonds');
INSERT INTO product_attr_lcl VALUES (52, 26, '杏仁', 'almonds.jpg', 'zh-HK', '杏仁');
INSERT INTO product_attr_lcl VALUES (47, 24, 'Strawberry', 'strawberry.jpg', 'en-GB', 'Strawberry');
INSERT INTO product_attr_lcl VALUES (48, 24, '草莓', 'strawberry.jpg', 'zh-HK', '草莓');
INSERT INTO product_attr_lcl VALUES (49, 25, 'Water Melon', 'water-melon.jpg', 'en-GB', 'Water Melon');
INSERT INTO product_attr_lcl VALUES (50, 25, '西瓜', 'water-melon.jpg', 'zh-HK', '西瓜');
INSERT INTO product_attr_lcl VALUES (31, 16, 'Onion', 'onion.jpg', 'en-GB', 'Onion');
INSERT INTO product_attr_lcl VALUES (32, 16, '洋蔥', 'onion.jpg', 'zh-HK', '洋蔥');
INSERT INTO product_attr_lcl VALUES (11, 6, 'beetroot', 'beetroot.jpg', 'en-GB', 'beetroot');
INSERT INTO product_attr_lcl VALUES (12, 6, '红菜头', 'beetroot.jpg', 'zh-HK', '红菜头');
INSERT INTO product_attr_lcl VALUES (43, 22, 'Pomegranate', 'pomegranate.jpg', 'en-GB', 'Pomegranate');
INSERT INTO product_attr_lcl VALUES (44, 22, '石榴', 'pomegranate.jpg', 'zh-HK', '石榴');
INSERT INTO product_attr_lcl VALUES (45, 23, 'RaspberryDan', 'raspberry.jpg', 'en-GB', 'RaspberryDan');
INSERT INTO product_attr_lcl VALUES (46, 23, '覆盆子', 'raspberry.jpg', 'zh-HK', '覆盆子');
INSERT INTO product_attr_lcl VALUES (5, 3, 'brocolli', 'broccoli.jpg', 'en-GB', 'brocolli');
INSERT INTO product_attr_lcl VALUES (6, 3, '西兰花', 'broccoli.jpg', 'zh-HK', '西兰花');
INSERT INTO product_attr_lcl VALUES (17, 9, 'beans', 'beans.jpg', 'en-GB', 'beans');
INSERT INTO product_attr_lcl VALUES (18, 9, '豆子', 'beans.jpg', 'zh-HK', '豆子');
INSERT INTO product_attr_lcl VALUES (7, 4, 'cauliflower', 'cauliflower.jpg', 'en-GB', 'cauliflower');
INSERT INTO product_attr_lcl VALUES (8, 4, '菜花', 'cauliflower.jpg', 'zh-HK', '菜花');
INSERT INTO product_attr_lcl VALUES (25, 13, 'potato', 'potato.jpg', 'en-GB', 'potato');
INSERT INTO product_attr_lcl VALUES (26, 13, '土豆', 'potato.jpg', 'zh-HK', '土豆');
INSERT INTO product_attr_lcl VALUES (35, 18, 'Banana', 'banana.jpg', 'en-GB', 'Banana');
INSERT INTO product_attr_lcl VALUES (36, 18, '香蕉', 'banana.jpg', 'zh-HK', '香蕉');
INSERT INTO product_attr_lcl VALUES (37, 19, 'Grapes', 'grapes.jpg', 'en-GB', 'Grapes');
INSERT INTO product_attr_lcl VALUES (38, 19, '葡萄', 'grapes.jpg', 'zh-HK', '葡萄');
INSERT INTO product_attr_lcl VALUES (15, 8, 'tomato', 'tomato.jpg', 'en-GB', 'tomato');
INSERT INTO product_attr_lcl VALUES (16, 8, '番茄', 'tomato.jpg', 'zh-HK', '番茄');
INSERT INTO product_attr_lcl VALUES (1, 1, 'mango', 'mango.jpg', 'en-GB', 'mango');
INSERT INTO product_attr_lcl VALUES (2, 1, '芒果', 'mango.jpg', 'zh-HK', '芒果');
INSERT INTO product_attr_lcl VALUES (9, 5, 'cucumber', 'cucumber.jpg', 'en-GB', 'cucumber');
INSERT INTO product_attr_lcl VALUES (10, 5, '黄瓜', 'cucumber.jpg', 'zh-HK', '黄瓜');


--
-- Data for Name: product_category; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO product_category VALUES (39, 1, 15);
INSERT INTO product_category VALUES (40, 2, 14);
INSERT INTO product_category VALUES (41, 3, 5);
INSERT INTO product_category VALUES (42, 4, 8);
INSERT INTO product_category VALUES (43, 5, 5);
INSERT INTO product_category VALUES (44, 6, 6);
INSERT INTO product_category VALUES (45, 7, 8);
INSERT INTO product_category VALUES (46, 8, 14);
INSERT INTO product_category VALUES (47, 9, 7);
INSERT INTO product_category VALUES (48, 10, 9);
INSERT INTO product_category VALUES (50, 12, 9);
INSERT INTO product_category VALUES (51, 13, 8);
INSERT INTO product_category VALUES (52, 14, 8);
INSERT INTO product_category VALUES (53, 15, 9);
INSERT INTO product_category VALUES (54, 16, 9);
INSERT INTO product_category VALUES (55, 17, 10);
INSERT INTO product_category VALUES (56, 18, 15);
INSERT INTO product_category VALUES (57, 19, 14);
INSERT INTO product_category VALUES (58, 20, 13);
INSERT INTO product_category VALUES (59, 21, 10);
INSERT INTO product_category VALUES (60, 22, 10);
INSERT INTO product_category VALUES (61, 23, 12);
INSERT INTO product_category VALUES (62, 24, 12);
INSERT INTO product_category VALUES (63, 25, 13);
INSERT INTO product_category VALUES (64, 26, 20);
INSERT INTO product_category VALUES (65, 27, 17);
INSERT INTO product_category VALUES (66, 28, 26);
INSERT INTO product_category VALUES (67, 29, 19);
INSERT INTO product_category VALUES (68, 30, 26);
INSERT INTO product_category VALUES (69, 1, 38);
INSERT INTO product_category VALUES (70, 10, 38);
INSERT INTO product_category VALUES (71, 11, 38);
INSERT INTO product_category VALUES (72, 14, 38);
INSERT INTO product_category VALUES (73, 15, 38);
INSERT INTO product_category VALUES (74, 18, 38);
INSERT INTO product_category VALUES (75, 19, 38);
INSERT INTO product_category VALUES (76, 20, 38);
INSERT INTO product_category VALUES (77, 22, 38);
INSERT INTO product_category VALUES (78, 24, 38);
INSERT INTO product_category VALUES (79, 27, 38);
INSERT INTO product_category VALUES (80, 29, 38);
INSERT INTO product_category VALUES (81, 2, 43);
INSERT INTO product_category VALUES (82, 14, 43);
INSERT INTO product_category VALUES (83, 18, 43);
INSERT INTO product_category VALUES (84, 22, 43);
INSERT INTO product_category VALUES (85, 12, 43);
INSERT INTO product_category VALUES (86, 16, 43);
INSERT INTO product_category VALUES (87, 26, 43);
INSERT INTO product_category VALUES (88, 6, 43);
INSERT INTO product_category VALUES (89, 20, 43);
INSERT INTO product_category VALUES (90, 5, 43);
INSERT INTO product_category VALUES (123, 233275, 233101);
INSERT INTO product_category VALUES (124, 233282, 233101);
INSERT INTO product_category VALUES (125, 233289, 233080);
INSERT INTO product_category VALUES (126, 233296, 233068);
INSERT INTO product_category VALUES (127, 233303, 233080);
INSERT INTO product_category VALUES (128, 233310, 233071);
INSERT INTO product_category VALUES (129, 233317, 233080);
INSERT INTO product_category VALUES (130, 233324, 233080);
INSERT INTO product_category VALUES (131, 233331, 233083);
INSERT INTO product_category VALUES (132, 233338, 233170);
INSERT INTO product_category VALUES (133, 233345, 233083);
INSERT INTO product_category VALUES (134, 233352, 233080);
INSERT INTO product_category VALUES (135, 233359, 233080);
INSERT INTO product_category VALUES (136, 233366, 233080);
INSERT INTO product_category VALUES (137, 233373, 233080);
INSERT INTO product_category VALUES (138, 233380, 233080);
INSERT INTO product_category VALUES (139, 233387, 233080);
INSERT INTO product_category VALUES (140, 233394, 233068);
INSERT INTO product_category VALUES (141, 233401, 233074);
INSERT INTO product_category VALUES (142, 233408, 233167);
INSERT INTO product_category VALUES (143, 233415, 233167);
INSERT INTO product_category VALUES (144, 233422, 233167);
INSERT INTO product_category VALUES (145, 233429, 233167);
INSERT INTO product_category VALUES (146, 233436, 233167);
INSERT INTO product_category VALUES (147, 233443, 233134);
INSERT INTO product_category VALUES (148, 233450, 233167);
INSERT INTO product_category VALUES (149, 233457, 233134);
INSERT INTO product_category VALUES (150, 233464, 233164);
INSERT INTO product_category VALUES (151, 233471, 233167);
INSERT INTO product_category VALUES (152, 233478, 233167);
INSERT INTO product_category VALUES (153, 233485, 233167);
INSERT INTO product_category VALUES (154, 233492, 233167);
INSERT INTO product_category VALUES (155, 233499, 233164);
INSERT INTO product_category VALUES (156, 233506, 233167);
INSERT INTO product_category VALUES (157, 233513, 233167);
INSERT INTO product_category VALUES (158, 233520, 233167);
INSERT INTO product_category VALUES (159, 233527, 233167);
INSERT INTO product_category VALUES (160, 233534, 233170);
INSERT INTO product_category VALUES (161, 233541, 233173);
INSERT INTO product_category VALUES (162, 233548, 233173);
INSERT INTO product_category VALUES (163, 233555, 233173);
INSERT INTO product_category VALUES (164, 233562, 233173);
INSERT INTO product_category VALUES (165, 233569, 233173);
INSERT INTO product_category VALUES (166, 233576, 233167);
INSERT INTO product_category VALUES (167, 233583, 233185);
INSERT INTO product_category VALUES (168, 233590, 233185);
INSERT INTO product_category VALUES (169, 233597, 233167);
INSERT INTO product_category VALUES (170, 233604, 233167);
INSERT INTO product_category VALUES (171, 233611, 233167);
INSERT INTO product_category VALUES (172, 233618, 233176);
INSERT INTO product_category VALUES (173, 233625, 233170);
INSERT INTO product_category VALUES (174, 233632, 233170);
INSERT INTO product_category VALUES (175, 233639, 233080);
INSERT INTO product_category VALUES (176, 233646, 233170);
INSERT INTO product_category VALUES (177, 233653, 233170);
INSERT INTO product_category VALUES (178, 233660, 233167);
INSERT INTO product_category VALUES (179, 233667, 233167);
INSERT INTO product_category VALUES (180, 233674, 233137);
INSERT INTO product_category VALUES (181, 233681, 233134);
INSERT INTO product_category VALUES (182, 233688, 233134);
INSERT INTO product_category VALUES (183, 233695, 233134);
INSERT INTO product_category VALUES (184, 233702, 233167);
INSERT INTO product_category VALUES (185, 233709, 233164);
INSERT INTO product_category VALUES (186, 233716, 233134);
INSERT INTO product_category VALUES (187, 233723, 233167);
INSERT INTO product_category VALUES (188, 233730, 233167);


--
-- Name: product_category_prd_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('product_category_prd_cat_id_seq', 190, true);


--
-- Data for Name: product_promotion; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: product_rating; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO product_rating VALUES (1, 11, 6, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (2, 5, 1, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (3, 7, 5, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (4, 9, 5, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (5, 13, 2, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (6, 21, 2, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (7, 23, 4, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (8, 25, 3, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (9, 26, 5, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (10, 28, 3, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (11, 30, 3, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (12, 2, 5, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (13, 8, 4, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (14, 17, 2, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (15, 3, 3, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (16, 4, 2, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (17, 6, 5, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (18, 12, 5, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (19, 16, 5, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (20, 10, 4, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (21, 14, 5, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (22, 15, 2, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (23, 19, 3, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (24, 20, 4, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (25, 22, 2, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (26, 24, 4, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (27, 27, 4, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (28, 29, 3, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (29, 1, 5, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (30, 18, 4, 1, '2020-04-21 12:24:49.524028+08');
INSERT INTO product_rating VALUES (31, 11, 1, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (32, 5, 5, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (33, 7, 4, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (34, 9, 5, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (35, 13, 4, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (36, 21, 6, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (37, 23, 2, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (38, 25, 2, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (39, 26, 2, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (40, 28, 6, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (41, 30, 4, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (42, 2, 4, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (43, 8, 5, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (44, 17, 3, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (45, 3, 5, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (46, 4, 2, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (47, 6, 4, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (48, 12, 4, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (49, 16, 6, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (50, 10, 3, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (51, 14, 2, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (52, 15, 5, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (53, 19, 5, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (54, 20, 4, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (55, 22, 3, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (56, 24, 6, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (57, 27, 2, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (58, 29, 6, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (59, 1, 2, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (60, 18, 1, 232230, '2020-04-21 12:25:18.024842+08');
INSERT INTO product_rating VALUES (61, 11, 4, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (62, 5, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (63, 7, 5, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (64, 9, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (65, 13, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (66, 21, 3, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (67, 23, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (68, 25, 3, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (69, 26, 4, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (70, 28, 3, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (71, 30, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (72, 2, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (73, 8, 1, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (74, 17, 1, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (75, 3, 4, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (76, 4, 6, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (77, 6, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (78, 12, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (79, 16, 4, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (80, 10, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (81, 14, 4, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (82, 15, 5, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (83, 19, 5, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (84, 20, 3, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (85, 22, 3, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (86, 24, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (87, 27, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (88, 29, 4, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (89, 1, 2, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (90, 18, 3, 232250, '2020-04-21 12:25:36.451586+08');
INSERT INTO product_rating VALUES (91, 11, 4, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (92, 5, 5, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (93, 7, 5, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (94, 9, 3, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (95, 13, 2, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (96, 21, 6, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (97, 23, 5, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (98, 25, 2, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (99, 26, 3, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (100, 28, 3, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (101, 30, 5, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (102, 2, 4, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (103, 8, 5, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (104, 17, 5, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (105, 3, 4, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (106, 4, 3, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (107, 6, 5, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (108, 12, 6, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (109, 16, 3, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (110, 10, 2, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (111, 14, 1, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (112, 15, 1, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (113, 19, 1, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (114, 20, 6, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (115, 22, 3, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (116, 24, 3, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (117, 27, 2, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (118, 29, 4, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (119, 1, 1, 232254, '2020-04-21 12:26:01.053376+08');
INSERT INTO product_rating VALUES (120, 18, 3, 232254, '2020-04-21 12:26:01.053376+08');


--
-- Name: product_rating_prd_rat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('product_rating_prd_rat_id_seq', 120, true);


--
-- Data for Name: product_shipping; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Name: product_status_prd_sts_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('product_status_prd_sts_id_seq', 1, false);


--
-- Data for Name: product_supplier; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: tag; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO tag VALUES (15, 'ORG01');
INSERT INTO tag VALUES (17, 'GFR01');
INSERT INTO tag VALUES (233218, 'TAG01');
INSERT INTO tag VALUES (233221, 'TAG02');
INSERT INTO tag VALUES (233224, 'TAG03');
INSERT INTO tag VALUES (233227, 'TAG04');
INSERT INTO tag VALUES (233230, 'TAG05');
INSERT INTO tag VALUES (233233, 'TAG06');
INSERT INTO tag VALUES (233236, 'TAG07');
INSERT INTO tag VALUES (233239, 'TAG08');
INSERT INTO tag VALUES (233242, 'TAG09');
INSERT INTO tag VALUES (233245, 'TAG10');
INSERT INTO tag VALUES (233248, 'TAG11');
INSERT INTO tag VALUES (233251, 'TAG12');
INSERT INTO tag VALUES (233254, 'TAG13');
INSERT INTO tag VALUES (233257, 'TAG14');
INSERT INTO tag VALUES (233260, 'TAG15');
INSERT INTO tag VALUES (233263, 'TAG16');
INSERT INTO tag VALUES (233266, 'TAG17');
INSERT INTO tag VALUES (233269, 'TAG18');
INSERT INTO tag VALUES (233272, 'TAG19');


--
-- Data for Name: product_tag; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO product_tag VALUES (25, 10, 17);
INSERT INTO product_tag VALUES (26, 11, 15);
INSERT INTO product_tag VALUES (27, 22, 17);
INSERT INTO product_tag VALUES (30, 7, 17);
INSERT INTO product_tag VALUES (31, 2, 15);
INSERT INTO product_tag VALUES (32, 5, 17);
INSERT INTO product_tag VALUES (34, 13, 17);
INSERT INTO product_tag VALUES (35, 233275, 233269);
INSERT INTO product_tag VALUES (36, 233282, 233269);
INSERT INTO product_tag VALUES (37, 233289, 233230);
INSERT INTO product_tag VALUES (38, 233296, 233230);
INSERT INTO product_tag VALUES (39, 233296, 233266);
INSERT INTO product_tag VALUES (40, 233303, 233230);
INSERT INTO product_tag VALUES (41, 233310, 233230);
INSERT INTO product_tag VALUES (42, 233310, 233266);
INSERT INTO product_tag VALUES (43, 233317, 233221);
INSERT INTO product_tag VALUES (44, 233324, 233230);
INSERT INTO product_tag VALUES (45, 233331, 233230);
INSERT INTO product_tag VALUES (46, 233338, 233230);
INSERT INTO product_tag VALUES (47, 233345, 233230);
INSERT INTO product_tag VALUES (48, 233352, 233221);
INSERT INTO product_tag VALUES (49, 233359, 233230);
INSERT INTO product_tag VALUES (50, 233366, 233230);
INSERT INTO product_tag VALUES (51, 233366, 233221);
INSERT INTO product_tag VALUES (52, 233373, 233230);
INSERT INTO product_tag VALUES (53, 233373, 233221);
INSERT INTO product_tag VALUES (54, 233380, 233230);
INSERT INTO product_tag VALUES (55, 233380, 233221);
INSERT INTO product_tag VALUES (56, 233387, 233230);
INSERT INTO product_tag VALUES (57, 233387, 233224);
INSERT INTO product_tag VALUES (58, 233394, 233266);
INSERT INTO product_tag VALUES (59, 233401, 233230);
INSERT INTO product_tag VALUES (60, 233401, 233266);
INSERT INTO product_tag VALUES (61, 233401, 233221);
INSERT INTO product_tag VALUES (62, 233408, 233251);
INSERT INTO product_tag VALUES (63, 233408, 233236);
INSERT INTO product_tag VALUES (64, 233415, 233251);
INSERT INTO product_tag VALUES (65, 233415, 233236);
INSERT INTO product_tag VALUES (66, 233422, 233251);
INSERT INTO product_tag VALUES (67, 233422, 233236);
INSERT INTO product_tag VALUES (68, 233429, 233251);
INSERT INTO product_tag VALUES (69, 233429, 233236);
INSERT INTO product_tag VALUES (70, 233436, 233251);
INSERT INTO product_tag VALUES (71, 233436, 233236);
INSERT INTO product_tag VALUES (72, 233443, 233230);
INSERT INTO product_tag VALUES (73, 233443, 233224);
INSERT INTO product_tag VALUES (74, 233450, 233230);
INSERT INTO product_tag VALUES (75, 233450, 233245);
INSERT INTO product_tag VALUES (76, 233450, 233248);
INSERT INTO product_tag VALUES (77, 233450, 233239);
INSERT INTO product_tag VALUES (78, 233457, 233230);
INSERT INTO product_tag VALUES (79, 233457, 233242);
INSERT INTO product_tag VALUES (80, 233464, 233230);
INSERT INTO product_tag VALUES (81, 233464, 233245);
INSERT INTO product_tag VALUES (82, 233464, 233239);
INSERT INTO product_tag VALUES (83, 233471, 233245);
INSERT INTO product_tag VALUES (84, 233471, 233239);
INSERT INTO product_tag VALUES (85, 233478, 233245);
INSERT INTO product_tag VALUES (86, 233478, 233239);
INSERT INTO product_tag VALUES (87, 233485, 233245);
INSERT INTO product_tag VALUES (88, 233485, 233248);
INSERT INTO product_tag VALUES (89, 233485, 233239);
INSERT INTO product_tag VALUES (90, 233492, 233245);
INSERT INTO product_tag VALUES (91, 233492, 233248);
INSERT INTO product_tag VALUES (92, 233492, 233239);
INSERT INTO product_tag VALUES (93, 233499, 233251);
INSERT INTO product_tag VALUES (94, 233506, 233251);
INSERT INTO product_tag VALUES (95, 233513, 233251);
INSERT INTO product_tag VALUES (96, 233520, 233251);
INSERT INTO product_tag VALUES (97, 233527, 233251);
INSERT INTO product_tag VALUES (98, 233534, 233251);
INSERT INTO product_tag VALUES (99, 233541, 233254);
INSERT INTO product_tag VALUES (100, 233548, 233254);
INSERT INTO product_tag VALUES (101, 233555, 233254);
INSERT INTO product_tag VALUES (102, 233562, 233254);
INSERT INTO product_tag VALUES (103, 233569, 233254);
INSERT INTO product_tag VALUES (104, 233576, 233257);
INSERT INTO product_tag VALUES (105, 233576, 233239);
INSERT INTO product_tag VALUES (106, 233583, 233230);
INSERT INTO product_tag VALUES (107, 233583, 233260);
INSERT INTO product_tag VALUES (108, 233590, 233230);
INSERT INTO product_tag VALUES (109, 233590, 233260);
INSERT INTO product_tag VALUES (110, 233597, 233248);
INSERT INTO product_tag VALUES (111, 233597, 233242);
INSERT INTO product_tag VALUES (112, 233604, 233251);
INSERT INTO product_tag VALUES (113, 233611, 233251);
INSERT INTO product_tag VALUES (114, 233618, 233251);
INSERT INTO product_tag VALUES (115, 233618, 233245);
INSERT INTO product_tag VALUES (116, 233625, 233251);
INSERT INTO product_tag VALUES (117, 233632, 233251);
INSERT INTO product_tag VALUES (118, 233639, 233230);
INSERT INTO product_tag VALUES (119, 233639, 233221);
INSERT INTO product_tag VALUES (120, 233646, 233251);
INSERT INTO product_tag VALUES (121, 233653, 233251);
INSERT INTO product_tag VALUES (122, 233660, 233251);
INSERT INTO product_tag VALUES (123, 233667, 233251);
INSERT INTO product_tag VALUES (124, 233674, 233272);
INSERT INTO product_tag VALUES (125, 233681, 233272);
INSERT INTO product_tag VALUES (126, 233688, 233272);
INSERT INTO product_tag VALUES (127, 233695, 233230);
INSERT INTO product_tag VALUES (128, 233695, 233251);
INSERT INTO product_tag VALUES (129, 233702, 233272);
INSERT INTO product_tag VALUES (130, 233709, 233245);
INSERT INTO product_tag VALUES (131, 233709, 233272);
INSERT INTO product_tag VALUES (132, 233716, 233221);
INSERT INTO product_tag VALUES (133, 233723, 233230);
INSERT INTO product_tag VALUES (134, 233730, 233272);


--
-- Name: product_tag_attr_lcl_tag_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('product_tag_attr_lcl_tag_id_seq', 1, false);


--
-- Name: product_tag_prd_tag_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('product_tag_prd_tag_id_seq', 176, true);


--
-- Name: product_type_prd_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('product_type_prd_typ_id_seq', 1, true);


--
-- Data for Name: promotion; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: promotion_bngnf; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Name: promotion_category_prm_cat_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('promotion_category_prm_cat_id_seq', 1, false);


--
-- Data for Name: promotion_mechanic; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--



--
-- Data for Name: promotion_type; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO promotion_type VALUES (1, 'product');
INSERT INTO promotion_type VALUES (2, 'order');
INSERT INTO promotion_type VALUES (3, 'category');
INSERT INTO promotion_type VALUES (4, 'brand');


--
-- Data for Name: rating; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO rating VALUES (1, 'One Star', 1);
INSERT INTO rating VALUES (2, 'Two Star', 2);
INSERT INTO rating VALUES (3, 'Three Star', 3);
INSERT INTO rating VALUES (4, 'Four Star', 4);
INSERT INTO rating VALUES (5, 'Five Star', 5);


--
-- Name: role_rle_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('role_rle_id_seq', 1, false);


--
-- Name: role_type_rle_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('role_type_rle_typ_id_seq', 1, false);


--
-- Name: role_type_role_typ_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('role_type_role_typ_id_seq', 1, false);


--
-- Data for Name: stock_on_hand; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO stock_on_hand VALUES (234239, 11, 10);
INSERT INTO stock_on_hand VALUES (234241, 21, 10);
INSERT INTO stock_on_hand VALUES (234243, 23, 10);
INSERT INTO stock_on_hand VALUES (234245, 25, 10);
INSERT INTO stock_on_hand VALUES (234247, 26, 10);
INSERT INTO stock_on_hand VALUES (234249, 28, 10);
INSERT INTO stock_on_hand VALUES (234251, 30, 10);
INSERT INTO stock_on_hand VALUES (234253, 17, 10);
INSERT INTO stock_on_hand VALUES (234255, 16, 10);
INSERT INTO stock_on_hand VALUES (234257, 14, 10);
INSERT INTO stock_on_hand VALUES (234259, 15, 10);
INSERT INTO stock_on_hand VALUES (234261, 19, 10);
INSERT INTO stock_on_hand VALUES (234263, 20, 10);
INSERT INTO stock_on_hand VALUES (234265, 22, 10);
INSERT INTO stock_on_hand VALUES (234267, 24, 10);
INSERT INTO stock_on_hand VALUES (234269, 27, 10);
INSERT INTO stock_on_hand VALUES (234271, 29, 10);
INSERT INTO stock_on_hand VALUES (234273, 18, 10);
INSERT INTO stock_on_hand VALUES (234275, 233275, 10);
INSERT INTO stock_on_hand VALUES (234277, 233282, 10);
INSERT INTO stock_on_hand VALUES (234279, 233289, 10);
INSERT INTO stock_on_hand VALUES (234281, 233296, 10);
INSERT INTO stock_on_hand VALUES (234283, 233303, 10);
INSERT INTO stock_on_hand VALUES (234285, 233310, 10);
INSERT INTO stock_on_hand VALUES (234287, 233317, 10);
INSERT INTO stock_on_hand VALUES (234289, 233324, 10);
INSERT INTO stock_on_hand VALUES (234291, 233331, 10);
INSERT INTO stock_on_hand VALUES (234293, 233338, 10);
INSERT INTO stock_on_hand VALUES (234295, 233345, 10);
INSERT INTO stock_on_hand VALUES (234297, 233352, 10);
INSERT INTO stock_on_hand VALUES (234299, 233359, 10);
INSERT INTO stock_on_hand VALUES (234301, 233366, 10);
INSERT INTO stock_on_hand VALUES (234305, 233380, 10);
INSERT INTO stock_on_hand VALUES (234307, 233387, 10);
INSERT INTO stock_on_hand VALUES (234309, 233394, 10);
INSERT INTO stock_on_hand VALUES (234311, 233401, 10);
INSERT INTO stock_on_hand VALUES (234313, 233408, 10);
INSERT INTO stock_on_hand VALUES (234315, 233415, 10);
INSERT INTO stock_on_hand VALUES (234317, 233422, 10);
INSERT INTO stock_on_hand VALUES (234319, 233429, 10);
INSERT INTO stock_on_hand VALUES (234321, 233436, 10);
INSERT INTO stock_on_hand VALUES (234323, 233443, 10);
INSERT INTO stock_on_hand VALUES (234325, 233450, 10);
INSERT INTO stock_on_hand VALUES (234327, 233457, 10);
INSERT INTO stock_on_hand VALUES (234329, 233464, 10);
INSERT INTO stock_on_hand VALUES (234331, 233471, 10);
INSERT INTO stock_on_hand VALUES (234333, 233478, 10);
INSERT INTO stock_on_hand VALUES (234335, 233485, 10);
INSERT INTO stock_on_hand VALUES (234337, 233492, 10);
INSERT INTO stock_on_hand VALUES (234339, 233499, 10);
INSERT INTO stock_on_hand VALUES (234341, 233506, 10);
INSERT INTO stock_on_hand VALUES (234343, 233513, 10);
INSERT INTO stock_on_hand VALUES (234345, 233520, 10);
INSERT INTO stock_on_hand VALUES (234347, 233527, 10);
INSERT INTO stock_on_hand VALUES (234349, 233534, 10);
INSERT INTO stock_on_hand VALUES (234351, 233541, 10);
INSERT INTO stock_on_hand VALUES (234353, 233548, 10);
INSERT INTO stock_on_hand VALUES (234355, 233555, 10);
INSERT INTO stock_on_hand VALUES (234357, 233562, 10);
INSERT INTO stock_on_hand VALUES (234359, 233569, 10);
INSERT INTO stock_on_hand VALUES (234361, 233576, 10);
INSERT INTO stock_on_hand VALUES (234363, 233583, 10);
INSERT INTO stock_on_hand VALUES (234365, 233590, 10);
INSERT INTO stock_on_hand VALUES (234367, 233597, 10);
INSERT INTO stock_on_hand VALUES (234369, 233604, 10);
INSERT INTO stock_on_hand VALUES (234371, 233611, 10);
INSERT INTO stock_on_hand VALUES (234373, 233618, 10);
INSERT INTO stock_on_hand VALUES (234375, 233625, 10);
INSERT INTO stock_on_hand VALUES (234377, 233632, 10);
INSERT INTO stock_on_hand VALUES (234379, 233639, 10);
INSERT INTO stock_on_hand VALUES (234381, 233646, 10);
INSERT INTO stock_on_hand VALUES (234383, 233653, 10);
INSERT INTO stock_on_hand VALUES (234385, 233660, 10);
INSERT INTO stock_on_hand VALUES (234387, 233667, 10);
INSERT INTO stock_on_hand VALUES (234389, 233674, 10);
INSERT INTO stock_on_hand VALUES (234391, 233681, 10);
INSERT INTO stock_on_hand VALUES (234393, 233688, 10);
INSERT INTO stock_on_hand VALUES (234395, 233695, 10);
INSERT INTO stock_on_hand VALUES (234397, 233702, 10);
INSERT INTO stock_on_hand VALUES (234399, 233709, 10);
INSERT INTO stock_on_hand VALUES (234401, 233716, 10);
INSERT INTO stock_on_hand VALUES (234403, 233723, 10);
INSERT INTO stock_on_hand VALUES (234405, 233730, 10);
INSERT INTO stock_on_hand VALUES (234407, 13, 10);
INSERT INTO stock_on_hand VALUES (234409, 4, 10);
INSERT INTO stock_on_hand VALUES (234411, 7, 10);
INSERT INTO stock_on_hand VALUES (234413, 9, 10);
INSERT INTO stock_on_hand VALUES (234415, 12, 10);
INSERT INTO stock_on_hand VALUES (234417, 1, 10);
INSERT INTO stock_on_hand VALUES (234419, 10, 10);
INSERT INTO stock_on_hand VALUES (234421, 8, 10);
INSERT INTO stock_on_hand VALUES (234423, 6, 10);
INSERT INTO stock_on_hand VALUES (234425, 2, 10);
INSERT INTO stock_on_hand VALUES (234427, 3, 10);
INSERT INTO stock_on_hand VALUES (234429, 5, 10);
INSERT INTO stock_on_hand VALUES (234303, 233373, 0);


--
-- Data for Name: supplier; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO supplier VALUES (233056, '1000000002');


--
-- Name: supplier_sup_num_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('supplier_sup_num_seq', 1000000002, true);


--
-- Data for Name: tag_attr_lcl; Type: TABLE DATA; Schema: mochi; Owner: mochidb_owner
--

INSERT INTO tag_attr_lcl VALUES (2, 15, '有機', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (4, 17, '不含麩質', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233219, 233218, 'S925', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233220, 233218, 'S925', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233222, 233221, 'FLOWER', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233223, 233221, '花', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233225, 233224, 'BUNNY ', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233226, 233224, '兔仔', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233228, 233227, 'STARS', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233229, 233227, '星', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233231, 233230, 'KOREA', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233232, 233230, '韓國', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233234, 233233, 'CAT ', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233235, 233233, '小貓', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233237, 233236, 'CROSSBODY BAG ', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233238, 233236, '斜咩袋', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233240, 233239, 'SPORTS', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233241, 233239, '運動', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233243, 233242, 'WHITE', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233244, 233242, '白色', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233246, 233245, 'UNISEX', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233247, 233245, '男女適合', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233249, 233248, 'BUMBAG', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233250, 233248, '腰包', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233252, 233251, 'MUM ', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233253, 233251, '靚媽媽', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233255, 233254, 'KIDS', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233256, 233254, '小童', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233258, 233257, 'HOBO BAG', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233259, 233257, '水餃包包', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233261, 233260, 'HOME', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233262, 233260, '家居', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233264, 233263, 'PET', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233265, 233263, '寵物', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233267, 233266, 'HAIR', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233268, 233266, '髮飾', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233270, 233269, 'PACKAGING', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233271, 233269, ' 禮品包裝', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (233273, 233272, 'SKATEBOARD', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (233274, 233272, '滑板風', NULL, 'zh-HK');
INSERT INTO tag_attr_lcl VALUES (3, 17, 'GLUTEN FREE', NULL, 'en-GB');
INSERT INTO tag_attr_lcl VALUES (1, 15, 'ORGANIC', NULL, 'en-GB');


--
-- Name: tag_attr_lcl_tag_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('tag_attr_lcl_tag_id_seq', 4, true);


--
-- Name: tag_tag_id_seq; Type: SEQUENCE SET; Schema: mochi; Owner: mochidb_owner
--

SELECT pg_catalog.setval('tag_tag_id_seq', 18, true);


--
-- PostgreSQL database dump complete
--

