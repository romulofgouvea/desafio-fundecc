CREATE TABLE public.usuario (
	id serial NOT NULL,
	nome varchar NOT NULL,
	cpf varchar NOT NULL,
	data_nascimento DATE NOT NULL,
	data_cadastro DATE NOT NULL,
	sexo varchar NOT NULL,
	id_cargo integer NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY (id),
	CONSTRAINT usuario_cargo_un UNIQUE (nome),
	CONSTRAINT usuario_cargo_fk
        FOREIGN KEY ("id_cargo")
            REFERENCES cargo (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE INDEX "usuario_id_IDX" ON public.usuario (id);