create table countries (
	id int primary key auto_increment,
    `name` varchar(100) not null
);

create table towns (
	id int primary key auto_increment,
    `name` varchar(100) not null,
    country_id int not null,
    CONSTRAINT fk_towns_countries FOREIGN KEY (country_id) REFERENCES countries(id)
);

create table minions(
	id int primary key AUTO_INCREMENT,
    `name` varchar(50) not null,
    age int not null,
    town_id int,
    CONSTRAINT fk_minions_towns FOREIGN KEY (town_id) REFERENCES towns(id)
);

create table villains (
	id int primary key auto_increment,
    `name` varchar(100) not null,
    evilness_factor enum ('good', 'bad', 'evil', 'super evil')
);

create table minions_villains (
	minion_id int not null,
    villain_id int not null,
    PRIMARY KEY (minion_id, villain_id),
    CONSTRAINT fk_minions_villains_minions FOREIGN KEY (minion_id) REFERENCES minions(id),
    CONSTRAINT fk_minions_villains_villains FOREIGN KEY (villain_id) REFERENCES villains(id)
);

insert into countries(id, `name`)
values (1, 'Bulgaria'), (2, 'Serbia'), (3, 'Romania'), (4, 'Croatia'), (5, 'Greece');

insert into towns(id, `name`, country_id)
values (1, 'Pazardzhik', 1), (2, 'Beograd', 2), (3, 'Sinaia', 3), (4, 'Vraca', 1), (5, 'Athens', 5);

insert into minions(id, age, `name`,  town_id)
values (1, 21, 'Gogo piinqka', 1), (2, 22, 'Malinkata', 4), 
(3, 66, 'Mile Kitic', 2), (4, 64, 'Vasilis Karras', 5), (5, 38, 'Florin Salam', 3);

insert into villains(`name`, evilness_factor)
values ('Petko', 'bad'), ('Mitko', 'good'), ('Pepi', 'super evil'), ('Hitler', 'good'), ('Karadzhic', 'bad');

insert into minions_villains(minion_id, villain_id)
values (1, 1), (2, 2),(3, 3),(4, 4),(5, 5);