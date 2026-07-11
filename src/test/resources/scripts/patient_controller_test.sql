INSERT INTO patients
(name, cpf, birth_date) VALUES
('Fulano Salvo', '20798907002', NOW()),
('Cicrano Salvo', '94479488049', NOW() - INTERVAL '15 YEAR'),
('Beltrano Salvo', '98053710000', NOW() - INTERVAL '60 YEAR');