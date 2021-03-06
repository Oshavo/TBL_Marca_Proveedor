-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TAREA1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TAREA1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TAREA1` DEFAULT CHARACTER SET utf8 ;
USE `TAREA1` ;

-- -----------------------------------------------------
-- Table `TAREA1`.`TBLMarca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAREA1`.`TBLMarca` (
  `idMarca` INT NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idMarca`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAREA1`.`TBLProvedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAREA1`.`TBLProveedor` (
  `IdProveedor` INT NOT NULL AUTO_INCREMENT,
  `proveedor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`IdProveedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAREA1`.`TBLProducto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAREA1`.`TBLProducto` (
  `IdProducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `url` VARCHAR(45) NOT NULL,
  `PrecioPub` INT NOT NULL,
  `costo` INT NOT NULL,
  `TBLMarca_idMarca` INT NOT NULL,
  `TBLProveedor_IdProveedor` INT NOT NULL,
  PRIMARY KEY (`IdProducto`),
  CONSTRAINT `fk_TBLProducto_TBLMarca1`
    FOREIGN KEY (`TBLMarca_idMarca`)
    REFERENCES `TAREA1`.`TBLMarca` (`idMarca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TBLProducto_TBLProveedor1`
    FOREIGN KEY (`TBLProveedor_IdProveedor`)
    REFERENCES `TAREA1`.`TBLProveedor` (`IdProveedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_TBLProducto_TBLMarca1_idx` ON `TAREA1`.`TBLProducto` (`TBLMarca_idMarca` ASC) VISIBLE;

CREATE INDEX `fk_TBLProducto_TBLProveedor1_idx` ON `TAREA1`.`TBLProducto` (`TBLProveedor_IdProveedor` ASC) VISIBLE;

CREATE UNIQUE INDEX `UX_TBLMarca_TBLProveedor` ON `TAREA1`.`TBLProducto` (`TBLMarca_idMarca` ASC, `TBLProveedor_IdProveedor` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
