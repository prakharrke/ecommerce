ALTER TABLE person add PRIMARY KEY (id);

CREATE TABLE billingaddress (

    id              bigint not null,
    country         TEXT,
    addressline1    TEXT,
    addressline2    TEXT,
    city            TEXT,
    state           TEXT,
    pincode         VARCHAR(6),
    phone           VARCHAR(12),
    firstname       TEXT,
    lastname        TEXT,
    personId       bigint REFERENCES person,

    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    modified        TIMESTAMP WITH TIME ZONE NOT NULL


);

CREATE UNIQUE INDEX ON billingaddress(personId);