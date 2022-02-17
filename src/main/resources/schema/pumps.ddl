CREATE SEQUENCE IF NOT EXISTS pump_id_seq START 1;

CREATE TABLE IF NOT EXISTS public.pump
(
    id bigint NOT NULL DEFAULT nextval('pump_id_seq'::regclass),
    address character varying(255) COLLATE pg_catalog."default",
    controls_description character varying(255) COLLATE pg_catalog."default",
    date character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    detailed_physical_state character varying(255) COLLATE pg_catalog."default",
    district character varying(255) COLLATE pg_catalog."default",
    external_id uuid NOT NULL,
    feeding_description character varying(255) COLLATE pg_catalog."default",
    the_geom geometry,
    last_control character varying(255) COLLATE pg_catalog."default",
    last_feeding character varying(255) COLLATE pg_catalog."default",
    lat double precision NOT NULL,
    lon double precision NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    number_anke character varying(255) COLLATE pg_catalog."default" NOT NULL,
    number_official character varying(255) COLLATE pg_catalog."default",
    operating_state character varying(255) COLLATE pg_catalog."default",
    osm_id character varying(255) COLLATE pg_catalog."default",
    physical_state character varying(255) COLLATE pg_catalog."default",
    state_description character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    wikipedia_id character varying(255) COLLATE pg_catalog."default",
    wikipedia_page character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT pump_pkey PRIMARY KEY (id)
);

ALTER TABLE public.pump
    OWNER to pumper;