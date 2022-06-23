--useage psql -U postgres -f /path/to/file

CREATE DATABASE precisely_user;
\c precisely_user;
CREATE TABLE user_account
(
        user_id serial,
	firstname varchar(255) NOT NULL,
	lastname varchar(255) NOT NULL,
        email varchar(319) NOT NULL UNIQUE,
        date_registered DATE,

        constraint pk_User primary key (user_id)
);
INSERT INTO user_account (firstname, lastname, email) VALUES('john','doe','abc@efg.com');
INSERT INTO user_account (firstname, lastname, email) VALUES('jane','doe','jane@test.com');
INSERT INTO user_account (firstname, lastname, email) VALUES('sam','cobbett','ilikejava@programming.com');
INSERT INTO user_account (firstname, lastname, email) VALUES('thanks','forLooking','atmytakehome@precisely.com');