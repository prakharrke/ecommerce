CREATE TABLE cart (
    id              BIGINT  PRIMARY KEY,
    productId       BIGINT  REFERENCES product,
    personId        BIGINT  REFERENCES person,
    quantity        INTEGER NOT NULL,

    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    modified        TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE UNIQUE INDEX ON cart(personId, productId);



