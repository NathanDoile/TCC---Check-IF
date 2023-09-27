USE check_if;

INSERT INTO administrador VALUES 
(null, 'administrador', 'administrador@checkif.com', '1A2B3C', '$2a$10$Qn5zoRMMhzVPkyosKrBoLev4uvhRTbFy3WLAyyuLaHAwSMpKAlhdS', true);

INSERT INTO portaria VALUES (null, 'portaria@checkif.com', '$2a$10$Qn5zoRMMhzVPkyosKrBoLev4uvhRTbFy3WLAyyuLaHAwSMpKAlhdS', true);

INSERT INTO responsavel VALUES 
(null, 'responsavel', 'responsavel@checkif.com', 51999999999, '$2a$10$Qn5zoRMMhzVPkyosKrBoLev4uvhRTbFy3WLAyyuLaHAwSMpKAlhdS', true);

INSERT INTO permissao VALUES 
(null, 'ADMINISTRADOR', null, null, 1),
(null, 'PORTARIA', null, 1, null),
(null, 'RESPONSAVEL', 1, null, null);

INSERT INTO aluno VALUES 
(null, 'Nathan de Souza Doile', '078790INFQ', '4K', '2004-07-29', true),
(null, 'Emily Aparecida da Silveira Eberhardt', '078630INFQ', '4I', '2004-12-30', true),
(null, 'Bianca Ramos Alves', '078510INFQ', '4I', '2005-03-07', true);

INSERT INTO professor VALUES (null, 'Fábio Lemes', '1A2B3C', 'fabiolemes@ifsul.edu.br', 51999999999, true, true, true);

INSERT INTO chegada_atrasada VALUES 
(null, '2023-09-05 11:02:04', 'Não queria vir para a escola', 'Empreendedorismo', 1, 1, null), 
(null, '2023-09-04 11:02:04', 'Despertador não tocou', 'Empreendedorismo', 2, 1, null);

INSERT INTO saida_antecipada VALUES
(null, null, "2023-09-20 12:00:00", "Simone de Souza Doile", "PENDENTE", null, "Tem que trabalhar.", 1, 1, null),
(null, null, "2023-09-20 11:45:00", "Simone de Souza Doile", "PENDENTE", null, "Tem que trabalhar.", 1, 1, null),
(null, null, "2023-09-20 12:00:00", "Simone de Souza Doile", "PENDENTE", null, "Tem que trabalhar.", 1, 1, null),
(null, null, "2023-09-20 11:45:00", "Simone de Souza Doile", "PENDENTE", null, "Tem que trabalhar.", 1, 1, null),
(null, null, "2023-09-20 11:45:00", "Simone de Souza Doile", "SAIU", null, "Tem que trabalhar.", 1, 1, null);