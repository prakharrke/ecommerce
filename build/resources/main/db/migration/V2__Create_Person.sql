CREATE TABLE person (

    username        character varying(50),
    firstName       character varying(100),
    lastName        character varying(100),
    email           character varying(100),
    passwordHash    character varying,
    salt            character varying(100)
)