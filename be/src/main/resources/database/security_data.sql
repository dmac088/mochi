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

SET search_path = security, pg_catalog;

--
-- Name: authority_id_seq; Type: SEQUENCE SET; Schema: security; Owner: security_owner
--

SELECT pg_catalog.setval('authority_id_seq', 1, false);


--
-- Data for Name: clientdetails; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: security; Owner: security_owner
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- Data for Name: oauth_access_token; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Data for Name: oauth_approvals; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Data for Name: oauth_client_details; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Data for Name: oauth_client_token; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Data for Name: oauth_code; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Data for Name: oauth_refresh_token; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Data for Name: permission; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Data for Name: role; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: security; Owner: security_owner
--

SELECT pg_catalog.setval('role_id_seq', 1, false);


--
-- Data for Name: role_permission; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Name: role_permission_id_seq; Type: SEQUENCE SET; Schema: security; Owner: security_owner
--

SELECT pg_catalog.setval('role_permission_id_seq', 1, false);


--
-- Data for Name: user_; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Data for Name: user_role; Type: TABLE DATA; Schema: security; Owner: security_owner
--



--
-- Name: user_role_id_seq; Type: SEQUENCE SET; Schema: security; Owner: security_owner
--

SELECT pg_catalog.setval('user_role_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

