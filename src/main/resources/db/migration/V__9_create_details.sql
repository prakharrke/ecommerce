create TABLE operatingsystem (

    id          BIGINT PRIMARY KEY,
    type        character varying(50),
    version     character varying(50),
    productId   BIGINT REFERENCES product,

    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    modified        TIMESTAMP WITH TIME ZONE NOT NULL
);

create UNIQUE INDEX ON operatingsystem(productId);

create TABLE screenspecifications (

    id          BIGINT PRIMARY KEY,
    size        character varying(50),
    type        character varying(50),
    resolution  character varying(50),
    ratio       character varying(50),
    productId   BIGINT REFERENCES product,

    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    modified        TIMESTAMP WITH TIME ZONE NOT NULL
);

create UNIQUE INDEX ON screenspecifications (productId);

create TABLE processordetails (
    id                  BIGINT PRIMARY KEY,
    brand               character varying(50),
    name                character varying(50),
    variant             character varying(50),
    generation          character varying(50),
    numberofcores       integer,
    processorspeed      float,
    cache               float,
    productId           BIGINT REFERENCES product,

    created             TIMESTAMP WITH TIME ZONE NOT NULL,
    modified            TIMESTAMP WITH TIME ZONE NOT NULL

);

create UNIQUE INDEX ON processordetails (productId);

create TABLE internalmemory (
    id                  BIGINT primary key,
    ram                 character varying(50),
    type                character varying(50),
    productId           BIGINT REFERENCES product,

    created             TIMESTAMP WITH TIME ZONE NOT NULL,
    modified            TIMESTAMP WITH TIME ZONE NOT NULL

);

CREATE UNIQUE INDEX ON internalmemory (productId);

CREATE TABLE graphicdetails (

    id                  BIGINT primary key,
    brand               character varying(50),
    model               character varying(50),
    type                character varying(50),
    memory              character varying(50),
    productId           BIGINT REFERENCES product,

    created             TIMESTAMP WITH TIME ZONE NOT NULL,
    modified            TIMESTAMP WITH TIME ZONE NOT NULL

);

CREATE UNIQUE INDEX ON graphicdetails (productId);
















