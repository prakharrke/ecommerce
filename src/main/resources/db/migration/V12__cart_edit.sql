DROP TABLE cart;

CREATE TABLE cart (
    id              BIGINT PRIMARY KEY,
    personId        BIGINT  REFERENCES person,

    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    modified        TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE UNIQUE INDEX ON cart(personId);

CREATE TABLE cartitem(
    id              BIGINT PRIMARY KEY,

    cartId          BIGINT REFERENCES cart,
    productId       BIGINT REFERENCES product,
    quantity        INTEGER not null,

    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    modified        TIMESTAMP WITH TIME ZONE NOT NULL

);

CREATE UNIQUE INDEX  ON cartitem(cartId, productId);