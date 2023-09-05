USE check_if;

INSERT INTO administrador VALUES 
(null, 'administrador', 'administrador@checkif.com', '1A2B3C', '$2a$10$Qn5zoRMMhzVPkyosKrBoLev4uvhRTbFy3WLAyyuLaHAwSMpKAlhdS', true);

INSERT INTO portaria VALUES (null, 'portaria@checkif.com', '$2a$10$Qn5zoRMMhzVPkyosKrBoLev4uvhRTbFy3WLAyyuLaHAwSMpKAlhdS', true);

INSERT INTO responsavel VALUES 
(null, 'responsavel', 'responsavel@checkif.com', 51999999999, '$2a$10$Qn5zoRMMhzVPkyosKrBoLev4uvhRTbFy3WLAyyuLaHAwSMpKAlhdS', true, true, true);

INSERT INTO permissao VALUES (null, 'ADMINISTRADOR', null, null, 1);
INSERT INTO permissao VALUES (null, 'PORTARIA', null, 1, null);
INSERT INTO permissao VALUES (null, 'RESPONSAVEL', 1, null, null);

INSERT INTO aluno VALUES (null, 'Nathan de Souza Doile', '078790INFQ', '4K', '2004-07-29', true);
INSERT INTO aluno VALUES (null, 'Emily Eberhardt', '123456INFQ', '4I', '2004-12-30', true);