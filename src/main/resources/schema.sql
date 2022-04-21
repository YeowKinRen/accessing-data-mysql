DROP TABLE user;

CREATE TABLE user (
	id bigint not null, 
	email varchar(255),
	name varchar(255),
	primary key (id)
	);