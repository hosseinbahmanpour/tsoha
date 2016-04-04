INSERT INTO Auto (rekkari, asemapaikka, merkki, malli) VALUES ('ABC-123', 'Helsinki', 'Lada', '100');
INSERT INTO Kuljettaja (etunimi, sukunimi) VALUES ('Arto', 'Vihavainen');
INSERT INTO Ajovuoro (kuljettaja_id, auto_id, km, aika) VALUES (1, 1, 11.2, 0.40);
INSERT INTO Kyyti (ajovuoro_id, hinta, km, aika) VALUES (1, 25.2, 9.63, 0.15);
INSERT INTO Kayttaja (tunnus, salasana) VALUES ('a', 'b');
