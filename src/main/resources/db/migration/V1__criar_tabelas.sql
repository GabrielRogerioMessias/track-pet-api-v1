CREATE TABLE usuario(
    id BINARY(16) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(150) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    numero VARCHAR(12) NOT NULL,
    telefone VARCHAR(15)
);

CREATE TABLE animal(
    id BINARY(16) PRIMARY KEY,
    usuario_id BINARY(16) NOT NULL,
    nome VARCHAR(100),
    data_nascimento DATE,
    peso DOUBLE,
    situacao ENUM('VIVO','MORTO','PERDIDO'),
    foto_url VARCHAR(250),
    raca VARCHAR(100),
    sexo ENUM('MACHO','FEMEA'),
    cor VARCHAR(100),
    CONSTRAINT FK_ANIMAL_USUARIO FOREIGN KEY(usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE leitura_qr(
    id BINARY(16) PRIMARY KEY,
    animal_id BINARY(16) NOT NULL,
    data_hora DATETIME,
    latitude VARCHAR(45),
    longitude VARCHAR(45),
    mensagem VARCHAR(1000),
    CONSTRAINT FK_LEITURA_QR_ANIMAL FOREIGN KEY(animal_id) REFERENCES animal(id) ON DELETE CASCADE
);