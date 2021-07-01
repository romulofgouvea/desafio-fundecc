CREATE DATABASE desafio_fundecc
	WITH OWNER = postgres
	ENCODING = 'UTF8'
	TABLESPACE = pg_default
	CONNECTION LIMIT = -1;

CREATE TABLE public.cargo (
	id serial NOT NULL,
	nome varchar NOT NULL,
	CONSTRAINT cargo_pk PRIMARY KEY (id),
	CONSTRAINT cargo_un UNIQUE (nome)
);
CREATE INDEX "cargo_id_IDX" ON public.cargo (id);