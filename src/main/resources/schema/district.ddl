CREATE SEQUENCE IF NOT EXISTS district_id_seq START 1;

CREATE TABLE IF NOT EXISTS public.district
(
    id bigint NOT NULL DEFAULT nextval('district_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    ortsteil character varying(255) COLLATE pg_catalog."default",
    external_id uuid NOT NULL,
    the_geom geometry,
    CONSTRAINT district_pkey PRIMARY KEY (id)
);

ALTER TABLE public.district
    OWNER to pumper;
