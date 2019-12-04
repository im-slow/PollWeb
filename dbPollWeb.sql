drop database if exists dbPollWeb;
create database dbpollweb;
use dbpollweb;

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
    cognome varchar(32) not null unique,
    email varchar(255) not null,
    pwd varchar(255) not null
);

#sondaggio
create table poll(
	ID integer unsigned not null primary key auto_increment,
    idNum integer not null unique,
    title varchar(32) not null,
    openText varchar(255) not null,
    closeText varchar(255) not null,
    openPoll boolean not null,
    statePoll boolean not null,
    URLPoll varchar(255) not null unique,
    IDuser integer unsigned not null,
    constraint poll_user foreign key (IDuser) references utente(ID) on update cascade on delete cascade
);

#domande
create table question(
	ID integer unsigned not null primary key auto_increment,
    positionNumber integer unsigned not null,
    uniqueCode varchar(32) not null unique,
    questionText text not null,
    note varchar(32) not null,
    mandatory bool not null,
    questionType enum('SHORTTEXT','LONGTEXT','NUMBER','DATE','SINGLECHOISE','MULTIPLECHOISE'),
    minimum varchar(32),
    maximum varchar(32),
    qAnswer varchar(255),
    IDpoll integer unsigned not null,
    constraint question_poll foreign key (IDpoll) references poll(ID) on update cascade on delete cascade
);

#le risposte alle domande fanno riferimento a questa istanza,
#in modo da essere associate allutente che ha compilato un dato sondaggio
create table instance(
	ID integer unsigned not null primary key auto_increment,
	userStatus boolean not null,
    submission timestamp,
	IDutente integer unsigned not null,
    IDpoll integer unsigned not null,
    constraint instance_utente foreign key (IDutente) references utente(ID) on update cascade on delete cascade,
    constraint instance_poll foreign key (IDpoll) references poll(ID) on update cascade on delete cascade
);

#risposte
create table answer(
	ID integer unsigned not null primary key auto_increment,
    answer varchar(255) not null,
    IDquestion integer unsigned not null,
    constraint answer_question foreign key (IDquestion) references question(ID) on update cascade on delete cascade
);

#associazione (n,m) risposta<-->instanza
create table submittedBy(
	IDanswer integer unsigned not null,
    IDinstance integer unsigned not null,
    primary key(IDanswer, IDinstance),
    constraint instance_answer foreign key (IDanswer) references answer(ID) on update cascade on delete cascade,
	constraint answer_instance foreign key (IDinstance) references instance(ID) on update cascade on delete cascade
);

#associazione (n,m) servizio <---> gruppo
create table utilizza(
	IDservizio integer unsigned not null,
    IDgruppo integer unsigned not null,
    primary key(IDservizio, IDgruppo),
    constraint utilizza_servizio foreign key (IDservizio) references servizio(ID) on update cascade on delete cascade,
    constraint utilizza_gruppo foreign key (IDgruppo) references gruppo(ID) on update cascade on delete cascade
);

#associazione (n,m) utente <---> gruppo
create table appartiene(
	IDutente integer unsigned not null,
    IDgruppo integer unsigned not null,
    primary key(IDutente, IDgruppo),
    constraint appartiene_utente foreign key (IDutente) references utente(ID) on update cascade on delete cascade,
    constraint appartiene_gruppo foreign key (IDgruppo) references gruppo(ID) on update cascade on delete cascade
);
