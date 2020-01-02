
CREATE SEQUENCE public.quotation_id_seq;

CREATE TABLE public.quotation (
                id INTEGER NOT NULL DEFAULT nextval('public.quotation_id_seq'),
                name VARCHAR(2) NOT NULL,
                CONSTRAINT quotation_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.quotation_id_seq OWNED BY public.quotation.id;

CREATE SEQUENCE public.voie_id_seq;

CREATE TABLE public.voie (
                id INTEGER NOT NULL DEFAULT nextval('public.voie_id_seq'),
                name VARCHAR(200) NOT NULL,
                secteur_id INTEGER,
                description VARCHAR,
                latitude VARCHAR,
                longitude VARCHAR,
                height INTEGER NOT NULL,
                quotation INTEGER NOT NULL,
                type VARCHAR(20) NOT NULL,
                points_num INTEGER,
                user_id INTEGER,
                parent_id INTEGER,
                CONSTRAINT voie_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.voie_id_seq OWNED BY public.voie.id;

CREATE SEQUENCE public.spot_id_seq;

CREATE TABLE public.spot (
                id INTEGER NOT NULL DEFAULT nextval('public.spot_id_seq'),
                image VARCHAR,
                user_id INTEGER,
                name VARCHAR NOT NULL,
                description VARCHAR,
                topo_id INTEGER,
                CONSTRAINT spot_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.spot_id_seq OWNED BY public.spot.id;

CREATE SEQUENCE public.secteur_id_seq;

CREATE TABLE public.secteur (
                id INTEGER NOT NULL DEFAULT nextval('public.secteur_id_seq'),
                spot_id INTEGER NOT NULL,
                name VARCHAR(200) NOT NULL,
                description VARCHAR,
                user_id INTEGER NOT NULL,
                CONSTRAINT secteur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.secteur_id_seq OWNED BY public.secteur.id;

CREATE SEQUENCE public.user_account_id_seq;

CREATE TABLE public.user_account (
                id INTEGER NOT NULL DEFAULT nextval('public.user_account_id_seq'),
                pseudo VARCHAR(100) NOT NULL,
                password VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL,
                role VARCHAR(10) DEFAULT user NOT NULL,
                date TIMESTAMP,
                CONSTRAINT user_account_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;

CREATE SEQUENCE public.topo_id_seq;

CREATE TABLE public.topo (
                id INTEGER NOT NULL DEFAULT nextval('public.topo_id_seq'),
                user_id INTEGER NOT NULL,
                name VARCHAR NOT NULL,
                description VARCHAR,
                image VARCHAR,
                date_publication TIMESTAMP NOT NULL,
                CONSTRAINT topo_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.topo_id_seq OWNED BY public.topo.id;

CREATE TABLE public.user_topo_reservation (
                user_id INTEGER NOT NULL,
                topo_id INTEGER NOT NULL,
                reserved BOOLEAN NOT NULL,
                date_reservation TIMESTAMP,
                user_reserved_id INTEGER,
                date_fin_reservation TIMESTAMP,
                CONSTRAINT user_topo_reservation_pk PRIMARY KEY (user_id, topo_id)
);


CREATE SEQUENCE public.comment_id_seq;

CREATE TABLE public.comment (
                id INTEGER NOT NULL DEFAULT nextval('public.comment_id_seq'),
                user_id INTEGER NOT NULL,
                text VARCHAR NOT NULL,
                topo_id INTEGER,
                date TIMESTAMP NOT NULL,
                parent_id INTEGER,
                CONSTRAINT comment_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;

ALTER TABLE public.voie ADD CONSTRAINT quotation_voie_fk
FOREIGN KEY (quotation)
REFERENCES public.quotation (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.secteur ADD CONSTRAINT spot_secteur_fk
FOREIGN KEY (spot_id)
REFERENCES public.spot (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comment ADD CONSTRAINT user_comment_fk
FOREIGN KEY (user_id)
REFERENCES public.user_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_topo_reservation ADD CONSTRAINT user_user_topo_fk
FOREIGN KEY (user_id)
REFERENCES public.user_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT user_topo_fk
FOREIGN KEY (user_id)
REFERENCES public.user_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_topo_reservation ADD CONSTRAINT topo_reservation_user_topo_fk
FOREIGN KEY (topo_id)
REFERENCES public.topo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
