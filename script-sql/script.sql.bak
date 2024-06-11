CREATE TABLE Cliente (
    id BIGINT NOT NULL,
    nome VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO Cliente (id, nome) VALUES 
(1, 'Cliente 1'),
(2, 'Cliente 2'),
(3, 'Cliente 3'),	
(4, 'Cliente 4'),
(5, 'Cliente 5'),
(6, 'Cliente 6'),
(7, 'Cliente 7'),
(8, 'Cliente 8'),
(9, 'Cliente 9'),
(10, 'Cliente 10');

CREATE TABLE Pedido (
    id BIGINT NOT NULL AUTO_INCREMENT,
    numero_controle VARCHAR(255) NOT NULL UNIQUE,
    data_cadastro DATE DEFAULT (CURRENT_DATE),
    nome_produto VARCHAR(255) NOT NULL,
    valor_unitario DECIMAL(19, 2) NOT NULL,
    quantidade INTEGER DEFAULT 1,
    valor_total DECIMAL(19, 2),
    cliente_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
) AUTO_INCREMENT=11;
