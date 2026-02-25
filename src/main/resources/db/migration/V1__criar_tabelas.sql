CREATE TABLE tb_local (
                          id SERIAL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          endereco VARCHAR(255) NOT NULL,
                          capacidade_maxima INTEGER NOT NULL
);

CREATE TABLE tb_organizador (
                                id SERIAL PRIMARY KEY,
                                nome VARCHAR(255) NOT NULL,
                                email VARCHAR(255) UNIQUE NOT NULL,
                                telefone VARCHAR(20) NOT NULL
);

CREATE TABLE tb_evento (
                           id SERIAL PRIMARY KEY,
                           titulo VARCHAR(255) NOT NULL,
                           descricao TEXT NOT NULL,
                           data_hora TIMESTAMP NOT NULL,
                           status VARCHAR(15) NOT NULL,
                           organizador_id INTEGER NOT NULL REFERENCES tb_organizador(id),
                           local_id INTEGER NOT NULL REFERENCES tb_local(id)
);

CREATE TABLE tb_participante (
                                 id SERIAL PRIMARY KEY,
                                 nome VARCHAR(255) NOT NULL,
                                 email VARCHAR(255) UNIQUE NOT NULL,
                                 documento VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE tb_ingresso (
                             id SERIAL PRIMARY KEY,
                             data_compra TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             valor_pago NUMERIC(19, 2) NOT NULL,
                             tipo VARCHAR(50) NOT NULL,
                             evento_id INTEGER NOT NULL REFERENCES tb_evento(id),
                             participante_id INTEGER NOT NULL REFERENCES tb_participante(id)
);