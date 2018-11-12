SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;
DROP SCHEMA IF EXISTS tricast CASCADE;
CREATE SCHEMA tricast;
ALTER SCHEMA tricast OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE tricast.accounts (
    id integer NOT NULL,
    username character varying(30) NOT NULL,
    password character varying(60) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    dob character varying(8) NOT NULL,
    address character varying(300) NOT NULL,
    emailaddress character varying(100),
    phonenumber character varying(50),
    pin character varying(20),
    bankaccountnumber character varying(40),
    bankcardnumber character varying(40),
    createddate timestamp(6) with time zone NOT NULL,
    accounttype character varying(8) NOT NULL
);

CREATE SEQUENCE tricast.accounts_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.accounts_id_seq OWNED BY tricast.accounts.id;

CREATE TABLE tricast.betoutcomemap (
    id integer NOT NULL,
	betid integer NOT NULL,
    outcomeid integer NOT NULL,
    odds numeric(18,2) NOT NULL
);

CREATE SEQUENCE tricast.betoutcomemap_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.betoutcomemap_id_seq OWNED BY tricast.betoutcomemap.id;

CREATE TABLE tricast.bets (
    id integer NOT NULL,
    bettypeid integer NOT NULL,
    accountid integer NOT NULL
);

CREATE SEQUENCE tricast.bets_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.bets_id_seq OWNED BY tricast.bets.id;

CREATE TABLE tricast.bettypes (
    id integer NOT NULL,
    description character varying(50) NOT NULL
);

CREATE TABLE tricast.competitors (
    id integer NOT NULL,
    description character varying(50) NOT NULL
);

CREATE SEQUENCE tricast.competitors_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.competitors_id_seq OWNED BY tricast.competitors.id;

CREATE TABLE tricast.eventcompetitormap (
    id integer NOT NULL,
    competitorid integer NOT NULL,
    eventid integer NOT NULL
);

CREATE SEQUENCE tricast.eventcompetitormap_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.eventcompetitormap_id_seq OWNED BY tricast.eventcompetitormap.id;

CREATE TABLE tricast.events (
    id integer NOT NULL,
    eventtypeid integer NOT NULL,
    leagueid integer NOT NULL,
    description character varying(100) NOT NULL,
    status character varying(30) NOT NULL,
    starttime timestamp(6) with time zone
);

CREATE SEQUENCE tricast.events_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.events_id_seq OWNED BY tricast.events.id;

CREATE TABLE tricast.eventtypes (
    id integer NOT NULL,
    description character varying(50) NOT NULL
);

CREATE TABLE tricast.leaguecompetitormap (
    id integer NOT NULL,
	competitorid integer NOT NULL,
    leagueid integer NOT NULL
);

CREATE SEQUENCE tricast.leaguecompetitormap_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.leaguecompetitormap_id_seq OWNED BY tricast.leaguecompetitormap.id;

CREATE TABLE tricast.leagues (
    id integer NOT NULL,
    description character varying(50) NOT NULL,
    sportid integer NOT NULL
);

CREATE SEQUENCE tricast.leagues_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.leagues_id_seq OWNED BY tricast.leagues.id;

CREATE TABLE tricast.markets (
    id integer NOT NULL,
    eventid integer NOT NULL,
    markettypeid integer NOT NULL,
    description character varying(200) NOT NULL,
    periodtypeid integer NOT NULL
);

CREATE SEQUENCE tricast.markets_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.markets_id_seq OWNED BY tricast.markets.id;

CREATE TABLE tricast.markettypes (
    id integer NOT NULL,
    description character varying(50) NOT NULL,
    sportid integer NOT NULL
);

CREATE SEQUENCE tricast.markettypes_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.markettypes_id_seq OWNED BY tricast.markettypes.id;

CREATE TABLE tricast.outcomes (
    id integer NOT NULL,
    marketid integer NOT NULL,
    outcomecode character varying(5) NOT NULL,
    description character varying(50) NOT NULL,
    odds numeric(18,2),
    winyn numeric(1,0)
);

CREATE SEQUENCE tricast.outcomes_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.outcomes_id_seq OWNED BY tricast.outcomes.id;

CREATE TABLE tricast.periodtypes (
    id integer NOT NULL,
    description character varying(50) NOT NULL
);

CREATE TABLE tricast.results (
    id integer NOT NULL,
    resulttypeid integer NOT NULL,
    result numeric(18,0) NOT NULL,
    periodtypeid integer NOT NULL,
	eventcompetitormapid bigint 
);

CREATE SEQUENCE tricast.results_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.results_id_seq OWNED BY tricast.results.id;

CREATE TABLE tricast.resulttypes (
    id integer NOT NULL,
    description character varying(50) NOT NULL
);

CREATE TABLE tricast.transactions (
    id integer NOT NULL,
    betid integer,
    createddate timestamp(6) with time zone NOT NULL,
    description character varying(300) NOT NULL,
    amount numeric(18,2) NOT NULL,
    accountid integer NOT NULL,
    type character varying(13) NOT NULL
);

CREATE SEQUENCE tricast.transactions_id_seq
    AS integer
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tricast.transactions_id_seq OWNED BY tricast.transactions.id;

CREATE TABLE tricast.sports (
    id integer NOT NULL,
    description character varying(50) NOT NULL
);

ALTER TABLE ONLY tricast.accounts ALTER COLUMN id SET DEFAULT nextval('tricast.accounts_id_seq'::regclass);

ALTER TABLE ONLY tricast.betoutcomemap ALTER COLUMN id SET DEFAULT nextval('tricast.betoutcomemap_id_seq'::regclass);

ALTER TABLE ONLY tricast.bets ALTER COLUMN id SET DEFAULT nextval('tricast.bets_id_seq'::regclass);

ALTER TABLE ONLY tricast.competitors ALTER COLUMN id SET DEFAULT nextval('tricast.competitors_id_seq'::regclass);

ALTER TABLE ONLY tricast.events ALTER COLUMN id SET DEFAULT nextval('tricast.events_id_seq'::regclass);

ALTER TABLE ONLY tricast.eventcompetitormap ALTER COLUMN id SET DEFAULT nextval('tricast.eventcompetitormap_id_seq'::regclass);

ALTER TABLE ONLY tricast.leaguecompetitormap ALTER COLUMN id SET DEFAULT nextval('tricast.leaguecompetitormap_id_seq'::regclass);

ALTER TABLE ONLY tricast.leagues ALTER COLUMN id SET DEFAULT nextval('tricast.leagues_id_seq'::regclass);

ALTER TABLE ONLY tricast.markets ALTER COLUMN id SET DEFAULT nextval('tricast.markets_id_seq'::regclass);

ALTER TABLE ONLY tricast.outcomes ALTER COLUMN id SET DEFAULT nextval('tricast.outcomes_id_seq'::regclass);

ALTER TABLE ONLY tricast.results ALTER COLUMN id SET DEFAULT nextval('tricast.results_id_seq'::regclass);

ALTER TABLE ONLY tricast.transactions ALTER COLUMN id SET DEFAULT nextval('tricast.transactions_id_seq'::regclass);

ALTER TABLE ONLY tricast.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.betoutcomemap
    ADD CONSTRAINT betoutcomemap_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.bets
    ADD CONSTRAINT bets_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.bettypes
    ADD CONSTRAINT bettypes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.competitors
    ADD CONSTRAINT competitors_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.eventcompetitormap
    ADD CONSTRAINT eventcompetitormap_pkey PRIMARY KEY (id);
	
ALTER TABLE ONLY tricast.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.eventtypes
    ADD CONSTRAINT eventtypes_pkey PRIMARY KEY (id);
	
ALTER TABLE ONLY tricast.leaguecompetitormap
    ADD CONSTRAINT leaguecompetitormap_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.leagues
    ADD CONSTRAINT leagues_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.markets
    ADD CONSTRAINT markets_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.markettypes
    ADD CONSTRAINT markettypes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.outcomes
    ADD CONSTRAINT outcomes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.periodtypes
    ADD CONSTRAINT periodtypes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.results
    ADD CONSTRAINT results_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.resulttypes
    ADD CONSTRAINT resulttypes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tricast.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);
	
ALTER TABLE ONLY tricast.sports
    ADD CONSTRAINT sports_pkey PRIMARY KEY (id);

CREATE INDEX fki_fk_betoutcomemap_bets ON tricast.betoutcomemap USING btree (betid);

CREATE INDEX fki_fk_betoutcomemap_outcomes ON tricast.betoutcomemap USING btree (outcomeid);

CREATE INDEX fki_fk_bets_accounts ON tricast.bets USING btree (accountid);

CREATE INDEX fki_fk_bets_bettypes ON tricast.bets USING btree (bettypeid);

CREATE INDEX fki_fk_eventcompetitormap_competitors ON tricast.eventcompetitormap USING btree (competitorid);

CREATE INDEX fki_fk_eventcompetitormap_events ON tricast.eventcompetitormap USING btree (eventid);

CREATE INDEX fki_fk_leaguecompetitormap_competitors ON tricast.leaguecompetitormap USING btree (competitorid);

CREATE INDEX fki_fk_leaguecompetitormap_leagues ON tricast.leaguecompetitormap USING btree (leagueid);

CREATE INDEX fki_fk_leagues_sports ON tricast.leagues USING btree (sportid);

CREATE INDEX fki_fk_markets_events ON tricast.markets USING btree (eventid);

CREATE INDEX fki_fk_markets_markettypes ON tricast.markets USING btree (markettypeid);

CREATE INDEX fki_fk_markets_periodtypes ON tricast.markets USING btree (periodtypeid);

CREATE INDEX fki_fk_markettypes_sports ON tricast.markettypes USING btree (sportid);

CREATE INDEX fki_fk_outcomes_markets ON tricast.outcomes USING btree (marketid);

CREATE INDEX fki_fk_events_eventtypes ON tricast.events USING btree (eventtypeid);

CREATE INDEX fki_fk_events_leagues ON tricast.events USING btree (leagueid);

CREATE INDEX fki_fk_results_eventcompetitormap ON tricast.results USING btree (eventcompetitormapid);

CREATE INDEX fki_fk_results_periodtypes ON tricast.results USING btree (periodtypeid);

CREATE INDEX fki_fk_results_resulttypes ON tricast.results USING btree (resulttypeid);

CREATE INDEX fki_fk_transactions_accounts ON tricast.transactions USING btree (accountid);

CREATE INDEX fki_fk_transactions_bets ON tricast.transactions USING btree (betid);

ALTER TABLE ONLY tricast.betoutcomemap
    ADD CONSTRAINT fk_betoutcomemap_bets FOREIGN KEY (betid) REFERENCES tricast.bets(id);

ALTER TABLE ONLY tricast.betoutcomemap
    ADD CONSTRAINT fk_betoutcomemap_outcomes FOREIGN KEY (outcomeid) REFERENCES tricast.outcomes(id);

ALTER TABLE ONLY tricast.bets
    ADD CONSTRAINT fk_bets_accounts FOREIGN KEY (accountid) REFERENCES tricast.accounts(id);

ALTER TABLE ONLY tricast.bets
    ADD CONSTRAINT fk_bets_bettypes FOREIGN KEY (bettypeid) REFERENCES tricast.bettypes(id);

ALTER TABLE ONLY tricast.eventcompetitormap
    ADD CONSTRAINT fk_eventcompetitormap_competitors FOREIGN KEY (competitorid) REFERENCES tricast.competitors(id);

ALTER TABLE ONLY tricast.eventcompetitormap
    ADD CONSTRAINT fk_eventcompetitormap_events FOREIGN KEY (eventid) REFERENCES tricast.events(id);

ALTER TABLE ONLY tricast.events
    ADD CONSTRAINT fk_events_eventtypes FOREIGN KEY (eventtypeid) REFERENCES tricast.eventtypes(id);

ALTER TABLE ONLY tricast.events
    ADD CONSTRAINT fk_events_leagues FOREIGN KEY (leagueid) REFERENCES tricast.leagues(id);

ALTER TABLE ONLY tricast.leaguecompetitormap
    ADD CONSTRAINT fk_leaguecompetitormap_competitors FOREIGN KEY (competitorid) REFERENCES tricast.competitors(id);

ALTER TABLE ONLY tricast.leaguecompetitormap
    ADD CONSTRAINT fk_leaguecompetitormap_leagues FOREIGN KEY (leagueid) REFERENCES tricast.leagues(id);

ALTER TABLE ONLY tricast.leagues
    ADD CONSTRAINT fk_leagues_sports FOREIGN KEY (sportid) REFERENCES tricast.sports(id);
	
ALTER TABLE ONLY tricast.markets
    ADD CONSTRAINT fk_markets_events FOREIGN KEY (eventid) REFERENCES tricast.events(id);

ALTER TABLE ONLY tricast.markets
    ADD CONSTRAINT fk_markets_markettypes FOREIGN KEY (markettypeid) REFERENCES tricast.markettypes(id);

ALTER TABLE ONLY tricast.markets
    ADD CONSTRAINT fk_markets_periodtypes FOREIGN KEY (periodtypeid) REFERENCES tricast.periodtypes(id);
	
ALTER TABLE ONLY tricast.markettypes
    ADD CONSTRAINT fk_markettypes_sports FOREIGN KEY (sportid) REFERENCES tricast.sports(id);

ALTER TABLE ONLY tricast.outcomes
    ADD CONSTRAINT fk_outcomes_markets FOREIGN KEY (marketid) REFERENCES tricast.markets(id);

ALTER TABLE ONLY tricast.results
    ADD CONSTRAINT fk_results_eventcompetitormap FOREIGN KEY (eventcompetitormapid) REFERENCES tricast.eventcompetitormap(id);
	
ALTER TABLE ONLY tricast.results
    ADD CONSTRAINT fk_results_periodtypes FOREIGN KEY (periodtypeid) REFERENCES tricast.periodtypes(id);

ALTER TABLE ONLY tricast.results
    ADD CONSTRAINT fk_results_resulttypes FOREIGN KEY (resulttypeid) REFERENCES tricast.resulttypes(id);

ALTER TABLE ONLY tricast.transactions
    ADD CONSTRAINT fk_transactions_accounts FOREIGN KEY (accountid) REFERENCES tricast.accounts(id);

ALTER TABLE ONLY tricast.transactions
    ADD CONSTRAINT fk_transactions_bets FOREIGN KEY (betid) REFERENCES tricast.bets(id);