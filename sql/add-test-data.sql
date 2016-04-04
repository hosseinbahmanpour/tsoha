INSERT INTO Auto (id, rekkari, merkki, malli) VALUES (1, ABC-123, Lada, 100);

INSERT INTO Kuljettaja (id, etunimi, sukunimi) VALUES (1, Arto, Vihavainen);

INSERT INTO Ajovuoro (id, kuljettaja_id, auto_id, km, aika) VALUES (1, 1, 1, 11.2, 0.40);

INSERT INTO Kyyti (id, ajovuoro_id, hinta, km, aika) VALUES (1, 1, 25.2, 9.63, 0.15);
