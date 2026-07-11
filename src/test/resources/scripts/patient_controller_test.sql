INSERT INTO patients
(id, name, cpf, birth_date) VALUES
('f5b45923-0749-48ec-9db8-e2941db149ac', 'Fulano Salvo', '20798907002', NOW()),
('d31ac41b-2e35-45e6-a142-3729d8d31361', 'Cicrano Salvo', '94479488049', NOW() - INTERVAL '15 YEAR'),
('877d5612-a6a7-4e64-bccb-130d0f84af6e', 'Beltrano Salvo', '98053710000', NOW() - INTERVAL '60 YEAR');
