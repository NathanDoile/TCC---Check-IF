DROP DATABASE IF EXISTS check_if;

CREATE DATABASE IF NOT EXISTS check_if;

USE check_if;

CREATE TABLE portaria (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
email VARCHAR(100) NOT NULL,
senha VARCHAR(255) NOT NULL,
is_ativo BOOLEAN NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE aluno (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(255) NOT NULL,
matricula VARCHAR(100) NOT NULL,
turma VARCHAR(10) NOT NULL,
data_nascimento DATE NOT NULL,
is_ativo BOOLEAN NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE professor (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(255) NOT NULL,
siape VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL,
celular BIGINT UNSIGNED NULL,
notificacao_email BOOLEAN NOT NULL,
is_ativo BOOLEAN NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE responsavel (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(255) NOT NULL,
email VARCHAR(100) NOT NULL,
celular BIGINT UNSIGNED NULL,
senha VARCHAR(255) NOT NULL,
is_ativo BOOLEAN NOT NULL,
token_resetar_senha VARCHAR(255) NULL,
data_envio_token TIMESTAMP NULL,
tentativas_resetar_senha INT NULL,
PRIMARY KEY (id)
);

CREATE TABLE administrador (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(255) NOT NULL,
email VARCHAR(100) NOT NULL,
siape VARCHAR(100) NOT NULL,
senha VARCHAR(255) NOT NULL,
is_ativo BOOLEAN NOT NULL,
token_resetar_senha VARCHAR(255) NULL,
data_envio_token TIMESTAMP NULL,
tentativas_resetar_senha INT NULL,
PRIMARY KEY (id)
);

CREATE TABLE responsavel_aluno (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
id_aluno BIGINT UNSIGNED NOT NULL,
id_responsavel BIGINT UNSIGNED NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (id_aluno) REFERENCES aluno (id),
FOREIGN KEY (id_responsavel) REFERENCES responsavel (id)
);

CREATE TABLE chegada_atrasada (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
data_hora TIMESTAMP NOT NULL,
motivo VARCHAR(500) NOT NULL,
disciplina VARCHAR(255) NULL,
id_aluno BIGINT UNSIGNED NOT NULL,
id_professor BIGINT UNSIGNED NOT NULL,
id_administrador BIGINT UNSIGNED NULL,
PRIMARY KEY (id),
FOREIGN KEY (id_aluno) REFERENCES aluno (id),
FOREIGN KEY (id_professor) REFERENCES professor (id),
FOREIGN KEY (id_administrador) REFERENCES administrador (id)
);

CREATE TABLE saida_antecipada (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
data_hora_saida TIMESTAMP NULL,
data_hora_autorizada TIMESTAMP NULL,
nome_responsavel VARCHAR(255) NULL,
situacao_saida VARCHAR(14) NOT NULL,
grau_parentesco VARCHAR(100) NULL,
motivo VARCHAR(500) NOT NULL,
id_responsavel BIGINT UNSIGNED NULL,
id_aluno BIGINT UNSIGNED NOT NULL,
id_administrador BIGINT UNSIGNED NULL,
PRIMARY KEY (id),
FOREIGN KEY (id_aluno) REFERENCES aluno (id),
FOREIGN KEY (id_responsavel) REFERENCES responsavel (id),
FOREIGN KEY (id_administrador) REFERENCES administrador (id)
);

CREATE TABLE permissao (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
funcao VARCHAR(20) NOT NULL,
id_responsavel BIGINT UNSIGNED  NULL,
id_portaria BIGINT UNSIGNED  NULL,
id_administrador BIGINT UNSIGNED NULL,
PRIMARY KEY (id),
FOREIGN KEY (id_responsavel) REFERENCES responsavel (id),
FOREIGN KEY (id_administrador) REFERENCES administrador (id),
FOREIGN KEY (id_portaria) REFERENCES portaria (id)
);

CREATE TABLE notificacao_email(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    receber BOOLEAN NOT NULL,
    id_aluno BIGINT UNSIGNED NOT NULL,
    id_responsavel BIGINT UNSIGNED NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_aluno) REFERENCES aluno (id),
    FOREIGN KEY(id_responsavel) REFERENCES responsavel (id)
);

CREATE TABLE email(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    remetente VARCHAR(255) NOT NULL,
    email_de VARCHAR(255) NOT NULL,
    email_para VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    mensagem VARCHAR(1000) NOT NULL,
    enviado_em TIMESTAMP NOT NULL,
    status_email VARCHAR(7) NOT NULL,
    PRIMARY KEY (id)
);