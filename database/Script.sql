CREATE DATABASE `coffeeShop` DEFAULT CHARACTER SET utf8 ;
USE `CoffeeShop` ;

/* SHOW ALL FK IN THE DATABASE */
SELECT RefCons.constraint_schema, RefCons.table_name, RefCons.referenced_table_name, RefCons.constraint_name, KeyCol.column_name
FROM information_schema.referential_constraints RefCons
JOIN information_schema.key_column_usage KeyCol ON RefCons.constraint_schema = KeyCol.table_schema
     AND RefCons.table_name = KeyCol.table_name
     AND RefCons.constraint_name = KeyCol.constraint_name
WHERE RefCons.constraint_schema = 'coffeeShop';

drop database coffeeshop;


-- -----------------------------------------------------
-- Table `T_nr_ddd`
-- -----------------------------------------------------

CREATE TABLE T_nr_ddd (
    id_nr_ddd INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nr_dd INT(2) NOT NULL
);

-- -----------------------------------------------------
-- Table `T_type_phone`
-- -----------------------------------------------------


CREATE TABLE T_type_phone (
  `id_type_phone` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ty_phone` TEXT(10) NOT NULL
);


-- -----------------------------------------------------
-- Table `T_Phonenumber`
-- -----------------------------------------------------

CREATE TABLE T_Phonenumber (
    `id_phonenumber` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `nm_Phone` VARCHAR(11) NOT NULL,
    T_id_nr_ddd INT NOT NULL,
    T_id_ty_phone INT NOT NULL,
    
    CONSTRAINT fk_T_nr_ddd FOREIGN KEY (T_id_nr_ddd)
        REFERENCES t_nr_ddd (id_nr_ddd),
        
    CONSTRAINT fk_T_type_phone FOREIGN KEY (T_id_ty_phone)
        REFERENCES T_type_phone (`id_type_phone`)
);


-- -----------------------------------------------------
-- Table `T_Contact_Phonenumber`
-- -----------------------------------------------------

CREATE TABLE T_Contact_Phonenumber (
  `id_Contact_Phonenumber` INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  `T_Phonenumber_id_phonenumber` INT NOT NULL,
  CONSTRAINT `fk_T_Phonenumber`
    FOREIGN KEY (`T_Phonenumber_id_phonenumber`)
    REFERENCES `T_Phonenumber` (`id_phonenumber`));

-- -----------------------------------------------------
-- Table `T_estado`
-- -----------------------------------------------------

CREATE TABLE `T_estado` (
  `id_estado` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nm_estado` VARCHAR(45) NOT NULL);
  
-- -----------------------------------------------------
-- Table `T_cidade`
-- -----------------------------------------------------
  
CREATE TABLE T_cidade (
  `id_cidade` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nm_cidade` VARCHAR(45) NOT NULL,
  `T_estado_id_estado` INT NOT NULL,
 
 CONSTRAINT fk_T_estado FOREIGN KEY (T_estado_id_estado)
 REFERENCES T_estado(id_estado)
 );

-- -----------------------------------------------------
-- Table `T_bairro`
-- -----------------------------------------------------
CREATE TABLE  `T_bairro` (
  `id_bairro` INT NOT NULL primary KEY AUTO_INCREMENT,
  `nm_bairro` VARCHAR(45) NOT NULL,
  `T_cidade_id_cidade` INT NOT NULL,
  CONSTRAINT `fk_T_cidade`
    FOREIGN KEY (`T_cidade_id_cidade`)
    REFERENCES `T_cidade` (`id_cidade`));
    

-- -----------------------------------------------------
-- Table `T_logradouro`
-- -----------------------------------------------------
CREATE TABLE `T_logradouro` (
  `id_logradouro` INT NOT NULL PRIMARY KEY auto_increment,
  `ds_logradouro` VARCHAR(45) NOT NULL,
  `T_bairro_id_bairro` INT NOT NULL,
  CONSTRAINT `fk_T_bairro`
    FOREIGN KEY (`T_bairro_id_bairro`)
    REFERENCES `T_bairro` (`id_bairro`));

-- -----------------------------------------------------
-- Table `mydb`.`T_endereco`
-- -----------------------------------------------------
CREATE TABLE `T_endereco` (
  `id_endereco` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ds_endereco` VARCHAR(45) NOT NULL,
  `nm_cep` INT NOT NULL,
  `T_logradouro_id_logradouro` INT NOT NULL,
  CONSTRAINT `fk_T_endereco_T_logradouro`
    FOREIGN KEY (`T_logradouro_id_logradouro`)
    REFERENCES `T_logradouro` (`id_logradouro`));

-- -----------------------------------------------------
-- Table `mydb`.`T_endereco_contato`
-- -----------------------------------------------------

CREATE TABLE `T_endereco_contato` (
  `id_enderco_contato` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ds_endereco` VARCHAR(45) NOT NULL,
  `T_endereco_id_endereco` INT NOT NULL,
  CONSTRAINT `fk_T_endereco`
    FOREIGN KEY (`T_endereco_id_endereco`)
    REFERENCES `T_endereco` (`id_endereco`));


-- -----------------------------------------------------
-- Table `mydb`.`T_Contato`
-- -----------------------------------------------------
CREATE TABLE `T_Contato` (
  `id_Contato` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nm_nome` VARCHAR(30) NOT NULL,
  `em_email` VARCHAR(50) NOT NULL,
  `ty_type_cliente` VARCHAR(10) NOT NULL,
  `T_Contact_Phonenumber_id_Contact_Phonenumber` INT NOT NULL,
  `T_endereco_contato_id_endereco_contato` INT NOT NULL,
  CONSTRAINT `fk_T_Contact_Phonenumber`
    FOREIGN KEY (`T_Contact_Phonenumber_id_Contact_Phonenumber`)
    REFERENCES `T_Contact_Phonenumber` (`id_Contact_Phonenumber`),
    
  CONSTRAINT `fk_Contato_T_endereco_contato`
    FOREIGN KEY (`T_endereco_contato_id_endereco_contato`)
    REFERENCES `T_endereco_contato` (`id_enderco_contato`));
    
-- -----------------------------------------------------
-- Table `T_equipe`
-- -----------------------------------------------------
    
CREATE TABLE T_equipe (
  `id_equipe` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ds_descricao` VARCHAR(45) NOT NULL);
  
  select * from T_equipe;
     drop table T_equipe;

-- -----------------------------------------------------
-- Table `T_funcionario`
-- -----------------------------------------------------

CREATE TABLE `T_funcionario` (
  `id_funcionario` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nm_nome` TEXT(40) NOT NULL,
  `dt_admissao` varchar(30) NOT NULL,
  `vl_salario` VARCHAR(45) NOT NULL,
  `ds_funcao` VARCHAR(45) NOT NULL,
   `T_equipe_id_equipe` INT NOT NULL ,
   
     CONSTRAINT `fk_T_funcionario_T_equipe`
    FOREIGN KEY (`T_equipe_id_equipe`)
    REFERENCES `T_equipe` (`id_equipe`));
  
   
     select * from T_funcionario;

   drop table T_funcionario ;
   
   /*
Insert into T_funcionario(id_funcionario, nm_nome, dt_admissao, vl_salario, ds_funcao, 

  `T_Contato_T_Contact_Phonenumber_id_Contact_Phonenumber` INT NOT NULL,
 

  CONSTRAINT `fk_T_funcionario_T_Contato`
    FOREIGN KEY (`T_Contato_id_Contato`)
    REFERENCES `T_Contato` (`id_Contato`),
    
    CONSTRAINT `fk_T_funcionario_T_Contact_Phonenumber`
    FOREIGN KEY (`T_Contato_T_Contact_Phonenumber_id_Contact_Phonenumber`)
    REFERENCES `T_Contato` (`T_Contact_Phonenumber_id_Contact_Phonenumber`)
  */

    select * from T_funcionario;
	drop table T_funcionario;
-- -----------------------------------------------------
-- Table `T_cliente_fisico`
-- -----------------------------------------------------
CREATE TABLE T_cliente_fisico(
  `id_cliente_fisico` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nr_cpf` INT(11) NOT NULL);
  
  
-- -----------------------------------------------------
-- Table `T_cliente_juridico`
-- -----------------------------------------------------
  
CREATE TABLE T_cliente_juridico (
  `id_cliente_juridico` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nr_cnpj` VARCHAR(45) NOT NULL);
  
-- -----------------------------------------------------
-- Table `T_cliente`
-- -----------------------------------------------------

CREATE TABLE `T_cliente` (
  `id_cliente` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nm_cliente` VARCHAR(45)  NOT NULL
  /*
  `T_cliente_fisico_id_cliente_fisico` INT NOT NULL,
  `T_cliente_juridico_id_cliente_juridico` INT NOT NULL,
  `T_cliente_T_Contato_id_Contato` INT NOT NULL,
  `T_cliente_T_Contato_Phonenumber_id_Contact_Phonenumber` INT NOT NULL,
  
  CONSTRAINT `fk_T_cliente_T_cliente_fisico`
    FOREIGN KEY (`T_cliente_fisico_id_cliente_fisico`)
    REFERENCES `T_cliente_fisico` (`id_cliente_fisico`),
    
   
  CONSTRAINT `fk_T_cliente_T_cliente_juridico`
    FOREIGN KEY (`T_cliente_juridico_id_cliente_juridico`)
    REFERENCES `T_cliente_juridico` (`id_cliente_juridico`),
   
  CONSTRAINT `fk_T_Contato`
    FOREIGN KEY (`T_cliente_T_Contato_id_Contato`)
    REFERENCES `T_Contato` (`id_Contato`),
    
    CONSTRAINT `fk_T_Contato_phonenumber`
    FOREIGN KEY (`T_cliente_T_Contato_Phonenumber_id_Contact_Phonenumber`)
    REFERENCES `T_Contato` (`T_Contact_Phonenumber_id_Contact_Phonenumber`)
    */
    );
    
    select * from T_cliente;
    drop table T_cliente;
    
-- -----------------------------------------------------
-- Table `T_notaFiscal`
-- -----------------------------------------------------
use coffeeshop;

CREATE TABLE T_Notafiscal (
  `id_Notafiscal` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nr_notafiscal` INT NOT NULL  ,
  `T_Pedido_id_pedido` INT  );
    
    drop table T_notaFiscal;
    
    select * from T_notaFiscal;
  
-- -----------------------------------------------------
-- Table `T_Pedido`
-- -----------------------------------------------------

CREATE TABLE `T_Pedido` (
    `id_Pedido` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `ds_pedido` TEXT(80) NOT NULL,
    `dt_pedido` VARCHAR(45) NOT NULL,
    `vl_pedido` VARCHAR(45) NOT NULL,
    `T_cliente_id_cliente` INT NOT NULL,
    `T_notafiscal_id_notafiscal` INT NOT NULL,
    CONSTRAINT `fk_T_Pedido_T_cliente` FOREIGN KEY (`T_cliente_id_cliente`)
        REFERENCES `T_cliente` (`id_cliente`),
    CONSTRAINT `FK_T_Pedido_T_NotaFiscal` FOREIGN KEY (`T_notafiscal_id_notafiscal`)
        REFERENCES `T_Notafiscal` (`id_Notafiscal`)
);
    
    
    drop table T_Pedido;
    
    select * from T_Pedido;
    
    CREATE TABLE T_Users (
	id_user int not null primary key auto_increment,
    login varchar(60) not null unique,
    ds_password varchar(60) not null,
    ds_role varchar(20) not null);
    
    select * from t_users;
    
    
    drop table t_users;
    
    /*

    CONSTRAINT `fk_T_Pedido_T_cliente_fisico`
    FOREIGN KEY (`T_cliente_T_cliente_fisico_id_cliente_fisico`)
    REFERENCES `T_cliente` (`T_cliente_fisico_id_cliente_fisico`),
    
    CONSTRAINT `fk_T_Pedido_T_cliente_juridico`
    FOREIGN KEY (`T_cliente_T_cliente_juridico_id_cliente_juridico`)
    REFERENCES `T_cliente` (`T_cliente_juridico_id_cliente_juridico`));
    
    
    */
     
    
-- -----------------------------------------------------
-- Table `T_estoque`
-- -----------------------------------------------------
    CREATE TABLE T_estoque (
  `id_estoque` INT NOT NULL  PRIMARY KEY AUTO_INCREMENT,
  `nr_quantidade_min` INT NOT NULL,
  `nr_quantidade_max` INT NOT NULL,
  `nr_quantidade_atual_items` INT NOT NULL);
  
-- -----------------------------------------------------
-- Table `T_produto`
-- -----------------------------------------------------

CREATE TABLE `T_produto` (
  `id_produto` INT NOT NULL  PRIMARY KEY AUTO_INCREMENT,
  `nm_produto` VARCHAR(45) NOT NULL,
  `vl_produto` VARCHAR(10) NOT NULL,
  `qt_produto` INT NOT NULL,
  `T_pedido_id_pedido` INT NOT NULL,
  `T_estoque_id_estoque` INT NOT NULL,

  
  CONSTRAINT `fk_T_produto_T_Pedido` FOREIGN KEY (`T_pedido_id_pedido`)
        REFERENCES `T_Pedido` (`id_Pedido`),
        
        
  CONSTRAINT `fk_T_produto_T_Estoque` FOREIGN KEY (`T_estoque_id_estoque`)
        REFERENCES `T_estoque` (`id_estoque`)
  );
  
  select *  from T_produto;
  
  drop table T_produto;

/*  CONSTRAINT `fk_T_produto_T_estoque`
    FOREIGN KEY (`T_estoque_id_estoque`)
    REFERENCES `T_estoque` (`id_estoque`));
    `T_estoque_id_estoque` INT NOT NULL)*/  


-- -----------------------------------------------------
-- Table `mydb`.`T_produto_vendido`
-- -----------------------------------------------------

CREATE TABLE `T_produto_vendido` (
    `id_produto_vendido` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `qt_vendida` INT NOT NULL,
    `T_notaFiscal_id_notaFiscal` INT NOT NULL,
    `T_produto_id_produto` INT NOT NULL,
    
    CONSTRAINT `fk_T_produto_vendido_T_notaFiscal` FOREIGN KEY (`T_notaFiscal_id_notaFiscal`)
        REFERENCES `T_notaFiscal` (`id_notaFiscal`),
        
    CONSTRAINT `fk_T_produto_vendido_T_produto` FOREIGN KEY (`T_produto_id_produto`)
        REFERENCES `T_produto` (`id_produto`)
);

CREATE TABLE T_fornecedor (
id_fornecedor INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
nm_fornecedor varchar(45),
nm_email varchar(70)
/*T_contato_id_contato_fornecedor int,


Constraint fk_T_Contato_id foreign key(`T_contato_id_contato_fornecedor`)
REFERENCES `T_Contato` (`id_contato`)*/

);

select * from T_fornecedor;
select * from T_cliente;
select * from T_funcionario

