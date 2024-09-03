CREATE TABLE clientes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    telefone VARCHAR(11),
    correntista BOOLEAN NOT NULL,
    score_credito FLOAT NOT NULL,
    saldo FLOAT NOT NULL
);