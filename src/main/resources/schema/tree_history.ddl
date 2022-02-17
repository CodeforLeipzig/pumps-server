CREATE SEQUENCE IF NOT EXISTS tree_history_id_seq START 1;

CREATE TABLE IF NOT EXISTS public.tree_history
(
    id bigint NOT NULL DEFAULT nextval('tree_history_id_seq'::regclass),
    alter integer,
    baumart_de character varying(255) COLLATE pg_catalog."default",
    baumart_wi character varying(255) COLLATE pg_catalog."default",
    dat_abgabe character varying(255) COLLATE pg_catalog."default",
    external_id uuid NOT NULL,
    gattung character varying(255) COLLATE pg_catalog."default",
    the_geom geometry,
    ortsteil character varying(255) COLLATE pg_catalog."default",
    pflanzjahr integer,
    standortnr character varying(255) COLLATE pg_catalog."default",
    strasse character varying(255) COLLATE pg_catalog."default",
    "timestamp" bigint NOT NULL,
    xcoord double precision,
    ycoord double precision,
    CONSTRAINT tree_history_pkey PRIMARY KEY (id)
);

ALTER TABLE public.tree_history
    OWNER to pumper;
