-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema shared_carpool
-- -----------------------------------------------------
-- Banco de dados utilizado no projeo rede social caronetiro. UEPB campina grande PB.

-- -----------------------------------------------------
-- Schema shared_carpool
--
-- Banco de dados utilizado no projeo rede social caronetiro. UEPB campina grande PB.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shared_carpool` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `shared_carpool` ;

-- -----------------------------------------------------
-- Table `shared_carpool`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`usuario` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC),
  UNIQUE INDEX `nomeUsuario_UNIQUE` (`nome` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `senha_UNIQUE` (`senha` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shared_carpool`.`perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`perfil` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`perfil` (
  `idPerfil` INT NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `estadoSessao` TINYINT(1) NOT NULL,
  `usuario_idUsuario` INT NULL,
  `login` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPerfil`),
  UNIQUE INDEX `idPerfil_UNIQUE` (`idPerfil` ASC),
  INDEX `fk_perfil_usuario1_idx` (`usuario_idUsuario` ASC),
  UNIQUE INDEX `idSessao_UNIQUE` (`idSessao` ASC),
  CONSTRAINT `fk_perfil_usuario1`
    FOREIGN KEY (`usuario_idUsuario`)
    REFERENCES `shared_carpool`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `shared_carpool`.`caronas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`caronas` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`caronas` (
  `idCaronas` INT NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `localOrigem` VARCHAR(45) NOT NULL,
  `localDestino` VARCHAR(45) NOT NULL,
  `data` VARCHAR(45) NOT NULL,
  `hora` VARCHAR(45) NOT NULL,
  `vagas` VARCHAR(45) NOT NULL,
  `perfil_idPerfil` INT NULL,
  PRIMARY KEY (`idCaronas`),
  UNIQUE INDEX `idcaronas_UNIQUE` (`idCaronas` ASC),
  INDEX `fk_caronas_perfil1_idx` (`perfil_idPerfil` ASC),
  CONSTRAINT `fk_caronas_perfil1`
    FOREIGN KEY (`perfil_idPerfil`)
    REFERENCES `shared_carpool`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shared_carpool`.`solicitacoes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`solicitacoes` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`solicitacoes` (
  `idSugestao` INT NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` VARCHAR(45) NOT NULL,
  `pontos` VARCHAR(45) NOT NULL,
  `solicitante` VARCHAR(45) NOT NULL,
  `perfil_idPerfil` INT NULL,
  INDEX `fk_solicitacoes_perfil1_idx` (`perfil_idPerfil` ASC),
  UNIQUE INDEX `idSugestao_UNIQUE` (`idSugestao` ASC),
  PRIMARY KEY (`idSugestao`),
  CONSTRAINT `fk_solicitacoes_perfil1`
    FOREIGN KEY (`perfil_idPerfil`)
    REFERENCES `shared_carpool`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `shared_carpool`.`respostaSugestaoCarona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`respostaSugestaoCarona` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`respostaSugestaoCarona` (
  `idrespostaSugestaoCarona` INT NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` VARCHAR(45) NOT NULL,
  `idSugestao` VARCHAR(45) NOT NULL,
  `pontos` VARCHAR(45) NOT NULL,
  `perfil_idPerfil` INT NULL,
  PRIMARY KEY (`idrespostaSugestaoCarona`),
  UNIQUE INDEX `idrespostaSugestaoCarona_UNIQUE` (`idrespostaSugestaoCarona` ASC),
  INDEX `fk_respostaSugestaoCarona_perfil1_idx` (`perfil_idPerfil` ASC),
  CONSTRAINT `fk_respostaSugestaoCarona_perfil1`
    FOREIGN KEY (`perfil_idPerfil`)
    REFERENCES `shared_carpool`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shared_carpool`.`solicitacaoVagas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`solicitacaoVagas` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`solicitacaoVagas` (
  `idsolicitacaoVagas` INT NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` VARCHAR(45) NOT NULL,
  `ponto` VARCHAR(45) NOT NULL,
  `solicitante` VARCHAR(45) NOT NULL,
  `situacao` VARCHAR(45) NULL DEFAULT 'ATIVADA',
  `estado` VARCHAR(45) NULL DEFAULT 'NOT',
  PRIMARY KEY (`idsolicitacaoVagas`),
  UNIQUE INDEX `idsolicitacaoVagas_UNIQUE` (`idsolicitacaoVagas` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shared_carpool`.`Solicitacao_Vaga_Sem_Sugestao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`Solicitacao_Vaga_Sem_Sugestao` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`Solicitacao_Vaga_Sem_Sugestao` (
  `idSolicitacao_Vaga_Vem_Sugestao` INT NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` VARCHAR(45) NOT NULL,
  `solicitante` VARCHAR(45) NOT NULL,
  `situacaoVagaSolicitada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idSolicitacao_Vaga_Vem_Sugestao`),
  UNIQUE INDEX `idSolicitacao_Vaga_Vem_Sugestao_UNIQUE` (`idSolicitacao_Vaga_Vem_Sugestao` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shared_carpool`.`review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`review` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`review` (
  `idreview` INT NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` INT NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `presenca` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `idreview_UNIQUE` (`idreview` ASC),
  PRIMARY KEY (`idreview`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shared_carpool`.`review_Motorista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`review_Motorista` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`review_Motorista` (
  `id_review_Motorista` INT NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` INT NOT NULL,
  `informacao` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `idreview_UNIQUE` (`id_review_Motorista` ASC),
  PRIMARY KEY (`id_review_Motorista`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
