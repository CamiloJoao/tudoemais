CREATE TABLE IF NOT EXISTS categoria (
    id serial PRIMARY KEY,
    nome varchar (20)
);

CREATE TABLE IF NOT EXISTS produto (
    id serial PRIMARY KEY,
    nome varchar(50),
    descricao TEXT,
    preco decimal(10, 2),
    categoria INT,
    FOREIGN KEY (categoria) REFERENCES categoria(id)
);

CREATE TABLE IF NOT EXISTS produtoA (
    id serial PRIMARY KEY,
    nome varchar(50),
    descricao TEXT,
    preco decimal(10, 2),
    categoria INT,
    FOREIGN KEY (categoria) REFERENCES categoria(id)
);


