CREATE TABLE product (

    id              BIGINT PRIMARY KEY,
    price           float,
    quantity        integer,
    modelseries     character varying(50),
    modelnumber     character varying(50),
    manufacturer    character varying(50),
    producttype     character varying(50),

    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    modified        TIMESTAMP WITH TIME ZONE NOT NULL

);

CREATE UNIQUE INDEX ON product(modelseries, modelnumber, producttype);