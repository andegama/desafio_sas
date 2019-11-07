DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS cargo;
DROP TABLE IF EXISTS perfil;
 
CREATE TABLE cargo (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL
);

CREATE TABLE perfil (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL
);

CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  sexo VARCHAR(1) NOT NULL,
  data_nascimento DATE NOT NULL,
  fk_cargo INT NOT NULL,
  fk_perfil INT NOT NULL
);

ALTER TABLE usuario
    ADD FOREIGN KEY (fk_cargo) 
    REFERENCES cargo(id);

ALTER TABLE usuario
    ADD FOREIGN KEY (fk_perfil) 
    REFERENCES perfil(id);