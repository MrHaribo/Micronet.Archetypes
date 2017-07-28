SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
SET search_path = public, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;

-- Role: ${artifactId}_role
-- CHANGE PASSWORD AS SOON AS POSSIBLE
CREATE ROLE ${artifactId}_role LOGIN
  ENCRYPTED PASSWORD '${artifactId}_password_1234'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

CREATE DATABASE ${artifactId.toLowerCase()}_db OWNER postgres;
  
\connect ${artifactId.toLowerCase()}_db
  
------------- ADD YOUR DATABASE INITIALIZATION HERE ---------------


-------------------------------------------------------------------