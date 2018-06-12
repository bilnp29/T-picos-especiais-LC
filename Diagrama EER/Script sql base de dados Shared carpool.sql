-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema shared_carpool
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema shared_carpool
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shared_carpool` DEFAULT CHARACTER SET utf8 ;
USE `shared_carpool` ;

-- -----------------------------------------------------
-- Table `shared_carpool`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`usuario` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`usuario` (
  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `nomeUsuario_UNIQUE` (`nome` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `senha_UNIQUE` (`senha` ASC),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 58
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`perfil` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`perfil` (
  `idPerfil` INT(11) NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `estadoSessao` TINYINT(1) NOT NULL,
  `usuario_idUsuario` INT(11) NULL DEFAULT NULL,
  `login` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPerfil`),
  UNIQUE INDEX `idPerfil_UNIQUE` (`idPerfil` ASC),
  UNIQUE INDEX `idSessao_UNIQUE` (`idSessao` ASC),
  INDEX `fk_perfil_usuario1_idx` (`usuario_idUsuario` ASC),
  CONSTRAINT `fk_perfil_usuario1`
    FOREIGN KEY (`usuario_idUsuario`)
    REFERENCES `shared_carpool`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`caronas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`caronas` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`caronas` (
  `idCaronas` INT(11) NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `localOrigem` VARCHAR(45) NOT NULL,
  `localDestino` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(60) NULL DEFAULT NULL,
  `data` VARCHAR(45) NOT NULL,
  `hora` VARCHAR(45) NOT NULL,
  `vagas` VARCHAR(45) NOT NULL,
  `tipoCarona` ENUM('Municipal','Intermunicipal') NOT NULL,
  `perfil_idPerfil` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idCaronas`),
  UNIQUE INDEX `idcaronas_UNIQUE` (`idCaronas` ASC),
  INDEX `fk_caronas_perfil1_idx` (`perfil_idPerfil` ASC),
  CONSTRAINT `fk_caronas_perfil1`
    FOREIGN KEY (`perfil_idPerfil`)
    REFERENCES `shared_carpool`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`respostasugestaocarona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`respostasugestaocarona` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`respostasugestaocarona` (
  `idrespostaSugestaoCarona` INT(11) NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` VARCHAR(45) NOT NULL,
  `idSugestao` VARCHAR(45) NOT NULL,
  `pontos` VARCHAR(45) NOT NULL,
  `perfil_idPerfil` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idrespostaSugestaoCarona`),
  UNIQUE INDEX `idrespostaSugestaoCarona_UNIQUE` (`idrespostaSugestaoCarona` ASC),
  INDEX `fk_respostaSugestaoCarona_perfil1_idx` (`perfil_idPerfil` ASC),
  CONSTRAINT `fk_respostaSugestaoCarona_perfil1`
    FOREIGN KEY (`perfil_idPerfil`)
    REFERENCES `shared_carpool`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`review` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`review` (
  `idreview` INT(11) NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` INT(11) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `presenca` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idreview`),
  UNIQUE INDEX `idreview_UNIQUE` (`idreview` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`review_motorista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`review_motorista` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`review_motorista` (
  `id_review_Motorista` INT(11) NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` INT(11) NOT NULL,
  `informacao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_review_Motorista`),
  UNIQUE INDEX `idreview_UNIQUE` (`id_review_Motorista` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`solicitacao_vaga_sem_sugestao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`solicitacao_vaga_sem_sugestao` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`solicitacao_vaga_sem_sugestao` (
  `idSolicitacao_Vaga_Vem_Sugestao` INT(11) NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` VARCHAR(45) NOT NULL,
  `solicitante` VARCHAR(45) NOT NULL,
  `situacaoVagaSolicitada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idSolicitacao_Vaga_Vem_Sugestao`),
  UNIQUE INDEX `idSolicitacao_Vaga_Vem_Sugestao_UNIQUE` (`idSolicitacao_Vaga_Vem_Sugestao` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`solicitacaovagas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`solicitacaovagas` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`solicitacaovagas` (
  `idsolicitacaoVagas` INT(11) NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` VARCHAR(45) NOT NULL,
  `ponto` VARCHAR(45) NOT NULL,
  `solicitante` VARCHAR(45) NOT NULL,
  `situacao` VARCHAR(45) NULL DEFAULT 'ATIVADA',
  `estado` VARCHAR(45) NULL DEFAULT 'NOT',
  PRIMARY KEY (`idsolicitacaoVagas`),
  UNIQUE INDEX `idsolicitacaoVagas_UNIQUE` (`idsolicitacaoVagas` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`sugestao_enconto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`sugestao_enconto` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`sugestao_enconto` (
  `idSugestao` INT(11) NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `idCarona` VARCHAR(45) NOT NULL,
  `pontos` VARCHAR(45) NOT NULL,
  `solicitante` VARCHAR(45) NOT NULL,
  `perfil_idPerfil` INT(11) NOT NULL,
  PRIMARY KEY (`idSugestao`),
  UNIQUE INDEX `idSugestao_UNIQUE` (`idSugestao` ASC),
  INDEX `fk_sugestao_enconto_perfil1_idx` (`perfil_idPerfil` ASC),
  CONSTRAINT `fk_sugestao_enconto_perfil1`
    FOREIGN KEY (`perfil_idPerfil`)
    REFERENCES `shared_carpool`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`interessecaronas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`interessecaronas` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`interessecaronas` (
  `idSessao` VARCHAR(45) NOT NULL,
  `origem` VARCHAR(45) NOT NULL,
  `destino` VARCHAR(45) NOT NULL,
  `data` VARCHAR(45) NULL DEFAULT NULL,
  `horaInicial` VARCHAR(45) NULL DEFAULT NULL,
  `horaFinal` VARCHAR(45) NULL DEFAULT NULL,
  `idInteresse` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idInteresse`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shared_carpool`.`caronaRelampago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shared_carpool`.`caronaRelampago` ;

CREATE TABLE IF NOT EXISTS `shared_carpool`.`caronaRelampago` (
  `idcaronaRelampago` INT NOT NULL AUTO_INCREMENT,
  `idSessao` VARCHAR(45) NOT NULL,
  `origem` VARCHAR(45) NOT NULL,
  `destino` VARCHAR(45) NOT NULL,
  `dataIda` VARCHAR(45) NOT NULL,
  `dataVolta` VARCHAR(45) NOT NULL,
  `hora` VARCHAR(45) NOT NULL,
  `minimoCaroneiros` VARCHAR(45) NOT NULL,
  `perfil_idPerfil` INT(11) NOT NULL,
  PRIMARY KEY (`idcaronaRelampago`),
  INDEX `fk_caronaRelampago_perfil1_idx` (`perfil_idPerfil` ASC),
  CONSTRAINT `fk_caronaRelampago_perfil1`
    FOREIGN KEY (`perfil_idPerfil`)
    REFERENCES `shared_carpool`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
