INSERT INTO `dbpollweb`.`utente` (`ID`, `nome`,`cognome`, `email`, `pwd`) VALUES ('1', 'Andrea', 'Amicosante','andrea@gmail.com', 'fbefd8749ff346f9037fe650810f8dfef336c');
INSERT INTO `dbpollweb`.`utente` (`ID`, `nome`,`cognome`, `email`, `pwd`) VALUES ('2', 'Angelo', 'DAlfonso','angelo@gmil.com', 'fbefd8749ff346f9037fe650810f8dfef336c'); #123stella
INSERT INTO `dbpollweb`.`utente` (`ID`, `nome`,`cognome`, `email`, `pwd`) VALUES ('3', 'Stefano', 'Ravanetti','stefano@gmail.com', 'fbefd8749ff346f9037fe650810f8dfef336c');

INSERT INTO `dbpollweb`.`poll` (`ID`, `title`, `openText`, `closeText`, `openPoll`, `statePoll`, `URLPoll`, `IDuser`) VALUES (1, 'primo sondaggio', 'questo é un sondaggio di prova', 'grazie per aver Compilato il sondaggio!', 1, 1, 'sondaggioprova1', 1);

INSERT INTO `dbpollweb`.`question` (`ID`, `positionNumber`, `uniqueCode`, `questionText`, `note`, `mandatory`, `questionType`, `minimum`, `maximum`, `qAnswer`, `IDpoll`) VALUES (1, 1, '34671345', 'Questa é la domanda numero 1', 'segna la risposta', '1', 'SINGLECHOISE', '1', '1', 'risposta 1#; risposta 2#; risposta 3#;', '1');
INSERT INTO `dbpollweb`.`question` (`ID`, `positionNumber`, `uniqueCode`, `questionText`, `note`, `mandatory`, `questionType`, `minimum`, `maximum`, `qAnswer`, `IDpoll`) VALUES (2, 2, '3456789', 'Questa é la risposta numero 2 foRMATTAta MalissiMO', 'non é obbligatoria', '0', 'MULTIPLECHOISE', '1', '4', 'risposta 1#; risposta 2#; risposta 3#;', '1');

INSERT INTO `dbpollweb`.`answer` (`ID`, `answer`, `IDquestion`) VALUES ('1', 'a1', '1');
INSERT INTO `dbpollweb`.`answer` (`ID`, `answer`, `IDquestion`) VALUES ('2', 'a2,a3', '2');
INSERT INTO `dbpollweb`.`answer` (`ID`, `answer`, `IDquestion`) VALUES ('3', 'a2', '1');
INSERT INTO `dbpollweb`.`answer` (`ID`, `answer`, `IDquestion`) VALUES ('4', 'a1', '2');

INSERT INTO `dbpollweb`.`instance` (`ID`, `userStatus`, `IDutente`, `IDpoll`) VALUES ('1', '1', '1', '1');
INSERT INTO `dbpollweb`.`instance` (`ID`, `userStatus`, `IDutente`, `IDpoll`) VALUES ('2', '1', '1', '1');
INSERT INTO `dbpollweb`.`instance` (`ID`, `userStatus`, `IDutente`, `IDpoll`) VALUES ('3', '1', '2', '1');
INSERT INTO `dbpollweb`.`instance` (`ID`, `userStatus`, `IDutente`, `IDpoll`) VALUES ('4', '1', '2', '1');

INSERT INTO `dbpollweb`.`submittedby` (`IDanswer`, `IDinstance`) VALUES ('1', '1');
INSERT INTO `dbpollweb`.`submittedby` (`IDanswer`, `IDinstance`) VALUES ('2', '2');
INSERT INTO `dbpollweb`.`submittedby` (`IDanswer`, `IDinstance`) VALUES ('3', '3');
INSERT INTO `dbpollweb`.`submittedby` (`IDanswer`, `IDinstance`) VALUES ('4', '4');

#UPDATE `dbpollweb`.`question` SET `positionNumber`='2' AND positionNumber='1' WHERE `ID`='1' AND ;
#tanti sondaggi
INSERT INTO `dbpollweb`.`poll` (`ID`, `title`, `openText`, `closeText`, `openPoll`, `statePoll`, `URLPoll`, `IDuser`) VALUES ('4', 'Prova', 'questo è un sondaggio', 'chiusura', '1', '1', 'url4', '2');
INSERT INTO `dbpollweb`.`poll` (`ID`, `title`, `openText`, `closeText`, `openPoll`, `statePoll`, `URLPoll`, `IDuser`) VALUES ('5', 'Bellissima', 'Lorem ipsum', 'sondaggio completato', '1', '1', 'url5', '2');
INSERT INTO `dbpollweb`.`poll` (`ID`, `title`, `openText`, `closeText`, `openPoll`, `statePoll`, `URLPoll`, `IDuser`) VALUES ('6', 'I', 'ma le mettiamo le', 'grazie per la partecipazione', '1', '1', 'url6', '2');
INSERT INTO `dbpollweb`.`poll` (`ID`, `title`, `openText`, `closeText`, `openPoll`, `statePoll`, `URLPoll`, `IDuser`) VALUES ('7', 'Sondaggi', 'maiuscole', 'sempre un piacere', '1', '1', 'url7', '2');
INSERT INTO `dbpollweb`.`poll` (`ID`, `title`, `openText`, `closeText`, `openPoll`, `statePoll`, `URLPoll`, `IDuser`) VALUES ('8', 'Sono', 'alla prima', 'bela ciao', '1', '0', 'url8', '3');
INSERT INTO `dbpollweb`.`poll` (`ID`, `title`, `openText`, `closeText`, `openPoll`, `statePoll`, `URLPoll`, `IDuser`) VALUES ('9', '9', 'lettera. ci metto tanto così la card diventa più grande lorem ipsum?!', 'una matina', '0', '0', 'url9', '3');
UPDATE `dbpollweb`.`poll` SET `URLPoll`='url1' WHERE `ID`='1';
UPDATE `dbpollweb`.`poll` SET `URLPoll`='url2' WHERE `ID`='2';
UPDATE `dbpollweb`.`poll` SET `URLPoll`='url3' WHERE `ID`='3';

INSERT INTO `dbpollweb`.`question` (`ID`, `positionNumber`, `uniqueCode`, `questionText`, `note`, `mandatory`, `questionType`, `minimum`, `maximum`, `qAnswer`, `IDpoll`) VALUES ('3', '3', '2345632', 'Scrivi un grande testo', 'NULL','1', 'LONGTEXT', '12', '400', 'NULL', '1');
INSERT INTO `dbpollweb`.`question` (`ID`, `positionNumber`, `uniqueCode`, `questionText`, `note`, `mandatory`, `questionType`, `minimum`, `maximum`, `qAnswer`, `IDpoll`) VALUES ('4', '4', '231315632', 'Scrivi un piccolo testo', 'la precedente domanda non aveva note','1', 'SHORTTEXT', '12', '35', 'NULL', '1');
INSERT INTO `dbpollweb`.`question` (`ID`, `positionNumber`, `uniqueCode`, `questionText`, `note`, `mandatory`, `questionType`, `minimum`, `maximum`, `qAnswer`, `IDpoll`) VALUES ('5', '5', '12345', 'Inserisci una data', 'quella che ti pare', '1', 'DATE', '12/11/1970', '04/12/2019', 'NULL', '1');
INSERT INTO `dbpollweb`.`question` (`ID`, `positionNumber`, `uniqueCode`, `questionText`, `note`, `mandatory`, `questionType`, `minimum`, `maximum`, `qAnswer`, `IDpoll`) VALUES ('6', '6', '87654', 'Inserisci un numero', 'inserisci un numero tra 69 e 90 per teresa', '1', 'NUMBER', '69', '90', 'NULL', '1');

INSERT INTO `dbpollweb`.`roles` (`ID`, `nome`, `description`) VALUES ('1', 'ADMIN', 'Hai i permessi di admin');
INSERT INTO `dbpollweb`.`roles` (`ID`, `nome`, `description`) VALUES ('2', 'RES', 'Sei un responsabile, puoi creare sondaggi');
INSERT INTO `dbpollweb`.`roles` (`ID`, `nome`, `description`) VALUES ('3', 'USER', 'Sei un utente, puoi rispondere ai sondaggi a cui sei stato registrato');

INSERT INTO `dbpollweb`.`users_roles` (`IDusers`, `IDroles`) VALUES ('1', '1');
INSERT INTO `dbpollweb`.`users_roles` (`IDusers`, `IDroles`) VALUES ('2', '2');
INSERT INTO `dbpollweb`.`users_roles` (`IDusers`, `IDroles`) VALUES ('3', '3');

