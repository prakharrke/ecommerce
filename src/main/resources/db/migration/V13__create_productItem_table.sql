CREATE TABLE productitem (

    id              BIGINT PRIMARY KEY,
    productid       BIGINT references product,
    serialnumber    text,

    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    modified        TIMESTAMP WITH TIME ZONE NOT NULL

);

CREATE UNIQUE INDEX ON productitem(productid, serialNumber);