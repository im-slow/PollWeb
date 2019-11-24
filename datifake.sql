INSERT INTO `dbpollweb`.`utente` (`ID`, `nome`,`cognome`, `email`, `pwd`) VALUES ('1', 'Andrea', 'Amicosante','andrea@gmail.com', '123stella');
INSERT INTO `dbpollweb`.`utente` (`ID`, `nome`,`cognome`, `email`, `pwd`) VALUES ('2', 'Angelo', 'DAlfonso','angelo@gmil.com', '123stella');
INSERT INTO `dbpollweb`.`utente` (`ID`, `nome`,`cognome`, `email`, `pwd`) VALUES ('3', 'Stefano', 'Ravanetti','stefano@gmail.com', '123stella');

INSERT INTO `dbpollweb`.`poll` (`ID`, `title`, `openText`, `closeText`, `openPool`, `statePool`, `URLPool`, `IDuser`) VALUES (1, 'primo sondaggio', 'questo é un sondaggio di prova', 'grazie per aver Compilato il sondaggio!', 1, 1, 'sondaggioprova1', 1);

INSERT INTO `dbpollweb`.`question` (`ID`, `positionNumber`, `uniqueCode`, `questionText`, `note`, `mandatory`, `questionType`, `minimum`, `maximum`, `questionOption`, `IDpoll`) VALUES ('1', '1', '34671efd', 'Questa é la domanda numero 1', 'segna la risposta', '1', 'SINGLECHOISE', '1', '1', 'null', '1');
INSERT INTO `dbpollweb`.`question` (`ID`, `positionNumber`, `uniqueCode`, `questionText`, `note`, `mandatory`, `questionType`, `minimum`, `maximum`, `questionOption`, `IDpoll`) VALUES ('2', '2', '3456789', 'Questa é la risposta numero 2 foRMATTAta MalissiMO', 'non é obbligatoria', '0', 'MULTIPLECHOISE', '1', '4', 'null', '1');

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
