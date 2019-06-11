drop database if exists dbPollWeb;
create database dbPollWeb;
use dbPollWeb;

#servizi
create table servizio(
	ID integer unsigned not null primary key auto_increment,
    nome varchar(45) not null,
    description varchar(255) not null
);

#gruppo
create table gruppo(
	ID integer unsigned not null primary key auto_increment,
    nome varchar(45) not null,
    description varchar(255) not null
);

#utente
create table utente(
	ID integer unsigned not null primary key auto_increment,
    nome varchar(32) not null unique,
    email varchar(255) not null,
    pwd varchar(255) not null
);

#sondaggio
create table poll(
	ID integer unsigned not null primary key auto_increment,
    title varchar(32) not null unique,
    openText varchar(255) not null unique,
    closeText varchar(255) not null unique,
    openPool boolean not null unique,
    statePool boolean not null unique,
    URLPool varchar(255) not null,
    IDuser integer unsigned not null,
    constraint poll_user foreign key (IDuser) references utente(ID) on update cascade on delete cascade
);

#domande
create table question(
	ID integer unsigned not null primary key auto_increment,
    positionNumber integer unsigned not null unique,
    uniqueCode varchar(32) not null unique,
    questionText text not null,
    note varchar(32) not null,
    mandatory bool not null,
    questionType enum('SHORTTEXT','LONGTEXT','NUMBER','DATE','SINGLECHOISE','MULTIPLECHOISE'),
    minimum varchar(32),
    maximum varchar(32),
    questionOption varchar(255),
    IDpoll integer unsigned not null,
    constraint question_poll foreign key (IDpoll) references poll(ID) on update cascade on delete cascade
);

#risposte
create table answer(
	ID integer unsigned not null primary key auto_increment,
    answer varchar(255) not null,
    IDquestion integer unsigned not null,
    constraint answer_question foreign key (IDquestion) references question(ID) on update cascade on delete cascade
);

#associazione(n,m) utente <--> sondaggio
create table signIn(
	IDuser integer unsigned not null,
    IDpoll integer unsigned not null,
    userStatus boolean not null,
    primary key(IDuser, IDpoll),
    constraint signIn_user foreign key (IDuser) references utente(ID) on update cascade on delete cascade,
    constraint signIn_poll foreign key (IDpoll) references poll(ID) on update cascade on delete cascade
);

#associazione(n,m) servizio <---> gruppo
create table utilizza(
	IDservizio integer unsigned not null,
    IDgruppo integer unsigned not null,
    primary key(IDservizio, IDgruppo),
    constraint utilizza_servizio foreign key (IDservizio) references servizio(ID) on update cascade on delete cascade,
    constraint utilizza_gruppo foreign key (IDgruppo) references gruppo(ID) on update cascade on delete cascade
);

#associazione(n,m) utente <---> gruppo
create table appartiene(
	IDutente integer unsigned not null,
    IDgruppo integer unsigned not null,
    primary key(IDutente, IDgruppo),
    constraint appartiene_utente foreign key (IDutente) references utente(ID) on update cascade on delete cascade,
    constraint appartiene_gruppo foreign key (IDgruppo) references gruppo(ID) on update cascade on delete cascade
);

