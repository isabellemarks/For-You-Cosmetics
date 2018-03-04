-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema INTEGRADOR_VC
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema INTEGRADOR_VC
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `INTEGRADOR_VC` DEFAULT CHARACTER SET utf8 ;
USE `INTEGRADOR_VC` ;

-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Endereco` (
  `idEndereco` INT(11) NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cep` INT(11) NOT NULL,
  `complemento` VARCHAR(45) NULL DEFAULT NULL,
  `numero` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEndereco`))
ENGINE = InnoDB
AUTO_INCREMENT = 50
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Cliente` (
  `idCliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `dataNascimento` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `status` INT(1) NOT NULL,
  `idEndereco` INT(10) NOT NULL,
  PRIMARY KEY (`idCliente`),
  INDEX `Cliente_ibfk_1` (`idEndereco` ASC),
  CONSTRAINT `Cliente_ibfk_1`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `INTEGRADOR_VC`.`Endereco` (`idEndereco`))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Fornecedor` (
  `idFornecedor` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `status` INT(1) NOT NULL,
  `idEndereco` INT(11) NOT NULL,
  PRIMARY KEY (`idFornecedor`),
  INDEX `fk_Fornecedor_Endereco1_idx` (`idEndereco` ASC),
  CONSTRAINT `fk_Fornecedor_Endereco1`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `INTEGRADOR_VC`.`Endereco` (`idEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Telefone` (
  `idTelefone` INT(11) NOT NULL AUTO_INCREMENT,
  `telefone` INT(11) NOT NULL,
  PRIMARY KEY (`idTelefone`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Fornecedor_Telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Fornecedor_Telefone` (
  `idFornecedor` INT(11) NOT NULL,
  `idTelefone` INT(11) NOT NULL,
  PRIMARY KEY (`idFornecedor`, `idTelefone`),
  INDEX `fk_Fornecedor_has_Telefone_Telefone1_idx` (`idTelefone` ASC),
  INDEX `fk_Fornecedor_has_Telefone_Fornecedor1_idx` (`idFornecedor` ASC),
  CONSTRAINT `fk_Fornecedor_has_Telefone_Fornecedor1`
    FOREIGN KEY (`idFornecedor`)
    REFERENCES `INTEGRADOR_VC`.`Fornecedor` (`idFornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Fornecedor_has_Telefone_Telefone1`
    FOREIGN KEY (`idTelefone`)
    REFERENCES `INTEGRADOR_VC`.`Telefone` (`idTelefone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Produto` (
  `idProduto` INT(11) NOT NULL AUTO_INCREMENT,
  `nomeProduto` VARCHAR(11) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `precoUnitario` FLOAT(11,2) NOT NULL,
  `status` INT(1) NOT NULL,
  `idFornecedor` INT(11) NOT NULL,
  PRIMARY KEY (`idProduto`),
  INDEX `fk_Produto_Fornecedor1_idx` (`idFornecedor` ASC),
  CONSTRAINT `fk_Produto_Fornecedor1`
    FOREIGN KEY (`idFornecedor`)
    REFERENCES `INTEGRADOR_VC`.`Fornecedor` (`idFornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Pedido` (
  `idPedido` INT(11) NOT NULL AUTO_INCREMENT,
  `valorPedidoTotal` DECIMAL(10,0) NOT NULL,
  `dataPedido` DATE NOT NULL,
  `idCliente` INT(11) NOT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `Pedido_ibfk_1` (`idCliente` ASC),
  CONSTRAINT `Pedido_ibfk_1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `INTEGRADOR_VC`.`Cliente` (`idCliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`ItensDoPedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`ItensDoPedido` (
  `idItensDoPedido` INT(11) NOT NULL AUTO_INCREMENT,
  `quantidade` VARCHAR(45) NOT NULL,
  `idProduto` INT(11) NOT NULL,
  `idPedido` INT(11) NOT NULL,
  PRIMARY KEY (`idItensdoPedido`),
  INDEX `ItensdoPedido_ibfk_1` (`idProduto` ASC),
  INDEX `ItensdoPedido_ibfk_2` (`idPedido` ASC),
  CONSTRAINT `ItensdoPedido_ibfk_1`
    FOREIGN KEY (`idProduto`)
    REFERENCES `INTEGRADOR_VC`.`Produto` (`idProduto`),
  CONSTRAINT `ItensdoPedido_ibfk_2`
    FOREIGN KEY (`idPedido`)
    REFERENCES `INTEGRADOR_VC`.`Pedido` (`idPedido`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Telefone_Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Telefone_Cliente` (
  `idCliente` INT(11) NOT NULL,
  `idTelefone` INT(11) NOT NULL,
  PRIMARY KEY (`idCliente`, `idTelefone`),
  INDEX `fk_Cliente_has_Telefone_Telefone1_idx` (`idTelefone` ASC),
  INDEX `fk_Cliente_has_Telefone_Cliente1_idx` (`idCliente` ASC),
  CONSTRAINT `fk_Cliente_has_Telefone_Cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `INTEGRADOR_VC`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_has_Telefone_Telefone1`
    FOREIGN KEY (`idTelefone`)
    REFERENCES `INTEGRADOR_VC`.`Telefone` (`idTelefone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Usuario` (
  `idUsuario` INT(45) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `status` INT(1) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`UsuarioCliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`UsuarioCliente` (
  `idUsuarioCliente` INT NOT NULL AUTO_INCREMENT,
  `loginCliente` VARCHAR(45) NOT NULL,
  `senhaCliente` VARCHAR(45) NOT NULL,
  `statusCliente` INT NOT NULL,
  PRIMARY KEY (`idUsuarioCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `INTEGRADOR_VC`.`Contato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `INTEGRADOR_VC`.`Contato` (
  `idContato` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `voceENosso` VARCHAR(45) NOT NULL,
  `assunto` VARCHAR(45) NOT NULL,
  `mensagem` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`idContato`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
