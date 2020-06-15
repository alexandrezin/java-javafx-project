-- MySQL Workbench Synchronization
-- Generated: 2020-06-15 11:48
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Alexandre Zin da Silva

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `javafxproject` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `javafxproject`.`tb_Report` (
  `id_Report` INT(11) NOT NULL AUTO_INCREMENT,
  `title_Report` VARCHAR(50) NOT NULL,
  `description_Report` VARCHAR(1000) NOT NULL,
  `date_Report` DATE NOT NULL,
  `id_Collaborator_Report` INT(11) NOT NULL,
  PRIMARY KEY (`id_Report`),
  UNIQUE INDEX `id_Report_UNIQUE` (`id_Report` ASC) VISIBLE,
  INDEX `fk_tb_Report_tb_Collaborator_idx` (`id_Collaborator_Report` ASC) VISIBLE,
  CONSTRAINT `fk_tb_Report_tb_Collaborator`
    FOREIGN KEY (`id_Collaborator_Report`)
    REFERENCES `javafxproject`.`tb_Collaborator` (`id_Collaborator`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `javafxproject`.`tb_Collaborator` (
  `id_Collaborator` INT(11) NOT NULL AUTO_INCREMENT,
  `name_Collaborator` VARCHAR(50) NOT NULL,
  `email_Collaborator` VARCHAR(50) NOT NULL,
  `registerDate_Collaborator` DATE NOT NULL,
  `id_Department_Collaborator` INT(11) NOT NULL,
  PRIMARY KEY (`id_Collaborator`),
  UNIQUE INDEX `id_Collaborator_UNIQUE` (`id_Collaborator` ASC) VISIBLE,
  INDEX `fk_tb_Collaborator_tb_Department1_idx` (`id_Department_Collaborator` ASC) VISIBLE,
  CONSTRAINT `fk_tb_Collaborator_tb_Department1`
    FOREIGN KEY (`id_Department_Collaborator`)
    REFERENCES `javafxproject`.`tb_Department` (`id_Department`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `javafxproject`.`tb_Department` (
  `id_Department` INT(11) NOT NULL AUTO_INCREMENT,
  `name_Department` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_Department`),
  UNIQUE INDEX `id_Department_UNIQUE` (`id_Department` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
