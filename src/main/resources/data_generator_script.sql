INSERT INTO "tricast"."sports" ("id", "description") VALUES
	(1, 'Football'),
	(2, 'Horse Racing');

INSERT INTO "tricast"."competitors" ("id", "description") VALUES
	(1, 'FTC'),
	(2, 'ETO'),
	(3, 'Arsenal'),
	(4, 'Milan'),
	(5, 'Real Madrid'),
	(6, 'Lord Clenaghcastle'),
	(7, 'Poetic Force'),
	(8, 'Mamillius'),
	(9, 'Dourado'),
	(10, 'Zeyzoun'),
	(11, 'Walk On Walter'),
	(12, 'De Vegas Kid'),
	(13, 'Glenn Coco'),
	(14, 'Suzis Connoisseur');

INSERT INTO "tricast"."eventtypes" ("id", "description") VALUES
	(1, 'GAMEEVENT'),
	(2, 'RANKEVENT');

INSERT INTO "tricast"."periodtypes" ("id", "description") VALUES
	(1, '90 minutes'),
	(2, 'First half'),
	(3, 'Second half'),
	(4, 'Rac');
	
INSERT INTO "tricast"."bettypes"("id", "description") VALUES 
	(1, 'Single'),
	(2, 'Double'),
	(3, 'Treble');
	
INSERT INTO "tricast"."markettypes" ("id", "description", "sportid") VALUES
	(1, 'WDW', 1),
	(2, 'Correct score', 1),
	(3, 'Over/Under Goals 1.5', 1),
	(4, 'Over/Under Goals 2.5', 1),
	(5, 'Over/Under Corners 8.5', 1),
	(6, 'Half time / Full time', 1),
	(7, 'Outright', 1),
	(8, 'Head to head', 1),
	(9, 'Outright', 2),
	(10, 'Head to head', 2);

INSERT INTO "tricast"."resulttypes" ("id", "description") VALUES
	(1, 'Goals'),
	(2, 'Corners'),
	(3, 'Positions');
	
INSERT INTO "tricast"."accounts"("id", "username", "password", "firstname", "lastname", "dob", "address", "emailaddress", "phonenumber", "pin", "bankaccountnumber", "bankcardnumber", "createddate", "accounttype") VALUES 
	(1, 'Ferike12', 'password123', 'Ferenc', 'Nagy', '19890216', '9012, Győr Hegyalja u. 116','ferike12@gmail.com', '+36302658945', '234523AA', '1234567879985421', '5454545454848484', current_timestamp,'PLAYER'),
	(2, 'BlackAdder', 'password123', 'Edmund', 'Blackadder', '19750312', '9999, Edinburgh Edinburgh Castl','blackadder@gmail.com', '+36202354955', '443521A', '1413564879975421', '545466774848484', current_timestamp,'PLAYER'),
	(3, 'RunningMan', 'password123', 'Ben', 'Richards', '19810119', '1111, Co-op City, Cullum Street 12','benrichards12@gmail.com', '+36702458945', '666677EA', '1234467879995929', '6654545454848484', current_timestamp,'PLAYER'),
	(4, 'admin', 'admin', 'Adminus', 'Admin', '00000101', '0000, ?','deity@gmail.com', '-', '-', '-', '-', current_timestamp,'ADMIN');
	
	
INSERT INTO "tricast"."leagues" ("id", "description", "sportid") VALUES
	(1, 'EB 2018', 1),
	(2, 'EB 2017', 1),
	(3, 'VB 2018', 1),
	(4, 'VB 2017', 1),
	(5, 'EPSOM DOWNS 2018-08-17', 2),
	(6, 'MUSSELBURGH 2018-11-01', 2);

INSERT INTO "tricast"."leaguecompetitormap" ("id", "competitorid", "leagueid") VALUES
	(1, 1, 1),
	(2, 3, 1),
	(3, 5, 1),
	(4, 10, 5),
	(5, 11, 5),
	(6, 13, 5);

INSERT INTO "tricast"."events" ("id", "eventtypeid", "leagueid", "description", "status", "starttime") VALUES
	(1, 1, 1, E'Szuper EB döntő', E'OPEN', E'2018-09-30 17:45:22+02'),
	(2, 2, 5, E'A nagy lóverseny', E'OPEN', E'2018-09-30 17:45:35+02'),
	(3, 1, 1, E'EB elődöntő', E'OPEN', E'2018-09-30 17:45:52+02');

INSERT INTO "tricast"."eventcompetitormap" ("id", "competitorid", "eventid") VALUES
	(1, 1, 1),
	(2, 3, 1),
	(3, 6, 2),
	(4, 7, 2),
	(5, 8, 2),
	(6, 9, 2),
	(7, 10, 2),
	(8, 11, 2),
	(9, 12, 2),
	(10, 13, 2),
	(11, 14, 2),
	(12, 2, 3),
	(13, 4, 3);
	
INSERT INTO "tricast"."markets" ("id", "eventid", "markettypeid", "description", "periodtypeid") VALUES
	(1, 1, 4, E'Szuper EB döntő - FTC - ARSENAL - 2018.09.30. 18:30 - 1x2', 1),
	(2, 3, 3, E'EB elődöntő - ETO - MILAN - 2018.09.30. 18:30 - 1x2', 2),
	(3, 2, 8, E'A nagy lóverseny - Lord Clenaghcastle - Poetic Force', 4),
	(4, 2, 8, E'A nagy lóverseny - Mamillius - Poetic Force', 4);
	
INSERT INTO tricast.outcomes(id, marketid, outcomecode, description, odds) VALUES 
	(1, 1, '1', 'FTC', 3.10),
	(2, 1, 'X', 'Döntetlen', 2.10),
	(3, 1, '2', 'ARSENAL', 1.15),
	(4, 2, '1', 'ETO', 4),
	(5, 2, 'X', 'Döntetlen', 2.15),
	(6, 2, '2', 'MILAN', 1.2),
	(7, 3, '1', 'Lord Clenaghcastle', 1.4),
	(8, 3, '2', 'Poetic Force', 3.15),
	(9, 4, '1', 'Mamillius', 1.15),
	(10, 4, '2', 'Poetic Force', 2.15);
	
INSERT INTO tricast.results(id, resulttypeid, result, eventcompetitormapid, periodtypeid) VALUES 
	(1, 1, 1, 1, 1),
	(2, 1, 0, 2, 1),
	(3, 3, 2, 3, 4),
	(4, 3, 4, 4, 4);
	
INSERT INTO tricast.bets(id, bettypeid, accountid) VALUES 
	(1, 2, 1),
	(2, 1, 2);
	
INSERT INTO tricast.betoutcomemap(id, betid, outcomeid, odds) VALUES 
	(1, 1, 1, 3.10),
	(2, 1, 4, 4),
	(3, 2, 8, 3.15);
	
INSERT INTO tricast.transactions(id, betid, createddate, description, amount, accountid, type) VALUES 
	(1, null, CURRENT_TIMESTAMP, 'Bankkártya feltöltés', 1000, 1, 'TRANSFER'),
	(2, 1, CURRENT_TIMESTAMP, 'Double Bet FTC @ 3.10, ETO @ 4.00', -10, 1, 'BET'),
	(3, null, CURRENT_TIMESTAMP, 'Bankkártya feltöltés', 500, 2, 'TRANSFER'),
	(4, 2, CURRENT_TIMESTAMP, 'Single Bet Poetic Force @ 3.15', -20, 2, 'BET');