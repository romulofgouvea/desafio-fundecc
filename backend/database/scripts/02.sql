CREATE TABLE public.perfil (
	id serial NOT NULL,
	nome varchar NOT NULL,
	CONSTRAINT perfil_pk PRIMARY KEY (id),
	CONSTRAINT perfil_un UNIQUE (nome)
);
CREATE INDEX "perfil_id_IDX" ON public.perfil (id);