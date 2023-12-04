USE check_if;

INSERT INTO administrador VALUES 
(null, 'ApoioAcademico', 'apoio@checkif.com', '1A2B3C', '$2a$10$Qn5zoRMMhzVPkyosKrBoLev4uvhRTbFy3WLAyyuLaHAwSMpKAlhdS', true, null, null, null);

INSERT INTO portaria VALUES (null, 'portaria@checkif.com', '$2a$10$Qn5zoRMMhzVPkyosKrBoLev4uvhRTbFy3WLAyyuLaHAwSMpKAlhdS', true);

INSERT INTO permissao VALUES 
(null, 'ADMINISTRADOR', null, null, 1),
(null, 'PORTARIA', null, 1, null);

INSERT INTO professor VALUES 
(null, 'Fábio Lemes', '1A2B3C', 'fabio@checkif.com', 51999999999, true, true),
(null, 'Priscila Ligoski', '3324089', 'priscila@checkif.com', 51999999998, true, true),
(null, 'Lourenço Basso', '4D5E6F', 'lourenco@checkif.com', 51999999997, true, true),
(null, 'Alex Orozco', '7G8H9J', 'alex@checkif.com', 51999999996, true, true),
(null, 'Rodrigo Remor', '10k11l', 'rodrigo@checkif.com', 51999999995, true, true),
(null, 'Vitor Hugo', '12ç13z', 'vitor@checkif.com', 51999999994, true, true),
(null, 'Marcelo Haas', '13x14c', 'marcelo@checkif.com', 51999999993, true, true),
(null, 'Janaina Jaeger', '14v15b', 'janaina@checkif.com', 51999999992, true, true),
(null, 'Roberto Bowowski', '7G8H9I', 'roberto@checkif.com', 51999999991, true, true);

INSERT INTO aluno VALUES
(null, 'Nicolle', '087654INFQ', '4K', '2008-10-23', true);