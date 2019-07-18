-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema academicSystemDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema academicSystemDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `academicSystemDB` DEFAULT CHARACTER SET utf8 ;
USE `academicSystemDB` ;

CREATE TABLE IF NOT EXISTS `academicSystemDB`.`alumno` (
  `cui` INT(8) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `edad` TINYINT(4) NOT NULL,
  `sexo` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`cui`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `academicSystemDB`.`asignatura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`asignatura` (
  `casi` INT(8) NOT NULL,
  `nombre` VARCHAR(128) NOT NULL,
  `semestre` INT(1) NOT NULL,
  `caracteristicas` VARCHAR(256) NOT NULL,
  `duracion` TINYINT(4) NOT NULL,
  `horas_teoricas` TINYINT(4) NOT NULL,
  `horas_practicas` TINYINT(4) NOT NULL,
  `horas_teorico_practicas` TINYINT(4) NOT NULL,
  `horas_laboratorio` TINYINT(4) NOT NULL,
  `creditos` TINYINT(4) NOT NULL,
  `fundamentacion` VARCHAR(1024) NOT NULL,
  `sumilla` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`casi`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`prerequisito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`prerequisito` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_pre` INT(8) NULL,
  `id_post` INT(8) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_pre_idx` (`id_pre` ASC),
  INDEX `id_post_idx` (`id_post` ASC),
  CONSTRAINT `id_pre`
    FOREIGN KEY (`id_pre`)
    REFERENCES `academicSystemDB`.`asignatura` (`casi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_post`
    FOREIGN KEY (`id_post`)
    REFERENCES `academicSystemDB`.`asignatura` (`casi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`silabo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`silabo` (
  `id` INT(8) NOT NULL,
  `id_asignatura` INT(8) NOT NULL,
  `semestre` VARCHAR(8) NOT NULL,
  `contenido` VARCHAR(2056) NOT NULL,
  `tipos_evaluacion` VARCHAR(45) NULL,
  `evaluacion_continua` VARCHAR(45) NULL,
  `evaluacion_periodica` VARCHAR(45) NULL,
  `instrumentos` VARCHAR(45) NULL,
  `requisitos_evaluacion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `id_asignatura_idx` (`id_asignatura` ASC),
  CONSTRAINT `id_asignatura`
    FOREIGN KEY (`id_asignatura`)
    REFERENCES `academicSystemDB`.`asignatura` (`casi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`cronograma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`cronograma` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idSilabo` INT(8) NOT NULL,
  `semana` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idSilabo_idx` (`idSilabo` ASC),
  CONSTRAINT `idSilabo`
    FOREIGN KEY (`idSilabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`resultados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`resultados` (
  `id` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`asig_res`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`asig_res` (
  `id` INT NOT NULL,
  `nivel` INT NOT NULL,
  `id_asignatura` INT(8) NOT NULL,
  `idresultados` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asig_res_asignatura1_idx` (`id_asignatura` ASC),
  INDEX `fk_asig_res_resultados1_idx` (`idresultados` ASC),
  CONSTRAINT `fk_asig_res_asignatura1`
    FOREIGN KEY (`id_asignatura`)
    REFERENCES `academicSystemDB`.`asignatura` (`casi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_asig_res_resultados1`
    FOREIGN KEY (`idresultados`)
    REFERENCES `academicSystemDB`.`resultados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`competencias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`competencias` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `nivel` INT NULL,
  `idresultados` INT NOT NULL,
  `idsilabo` INT(8) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_competencias_resultados1_idx` (`idresultados` ASC),
  INDEX `fk_competencias_silabo1_idx` (`idsilabo` ASC),
  CONSTRAINT `fk_competencias_resultados1`
    FOREIGN KEY (`idresultados`)
    REFERENCES `academicSystemDB`.`resultados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_competencias_silabo1`
    FOREIGN KEY (`idsilabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`estrategia-enseñanza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`estrategia-enseñanza` (
  `id` INT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`bibliografia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`bibliografia` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `autor` VARCHAR(45) NULL,
  `fecha` VARCHAR(45) NULL,
  `editorial` VARCHAR(45) NULL,
  `edicion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`silabo-estrategia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`silabo-estrategia` (
  `id` INT NOT NULL,
  `idsilabo` INT(8) NOT NULL,
  `idestrategia` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_silabo-estrategia_silabo1_idx` (`idsilabo` ASC),
  INDEX `fk_silabo-estrategia_estrategia-enseñanza1_idx` (`idestrategia` ASC),
  CONSTRAINT `fk_silabo-estrategia_silabo1`
    FOREIGN KEY (`idsilabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_silabo-estrategia_estrategia-enseñanza1`
    FOREIGN KEY (`idestrategia`)
    REFERENCES `academicSystemDB`.`estrategia-enseñanza` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`silabo-bibliografia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`silabo-bibliografia` (
  `id` INT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `idsilabo` INT(8) NOT NULL,
  `idbibliografia` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_silabo-bibliografia_silabo1_idx` (`idsilabo` ASC),
  INDEX `fk_silabo-bibliografia_bibliografia1_idx` (`idbibliografia` ASC),
  CONSTRAINT `fk_silabo-bibliografia_silabo1`
    FOREIGN KEY (`idsilabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_silabo-bibliografia_bibliografia1`
    FOREIGN KEY (`idbibliografia`)
    REFERENCES `academicSystemDB`.`bibliografia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`horario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`horario` (
  `id` INT NOT NULL,
  `dia_semana` VARCHAR(45) NULL,
  `tipo_clase` VARCHAR(45) NULL,
  `grupo` VARCHAR(1) NULL,
  `idsilabo` INT(8) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_horario_silabo1_idx` (`idsilabo` ASC),
  CONSTRAINT `fk_horario_silabo1`
    FOREIGN KEY (`idsilabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`evaluacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`evaluacion` (
  `id` INT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `evaluacion` VARCHAR(45) NULL,
  `peso` INT(2) NULL,
  `fecha` VARCHAR(45) NULL,
  `silabo_id` INT(8) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_evaluacion_silabo1_idx` (`silabo_id` ASC),
  CONSTRAINT `fk_evaluacion_silabo1`
    FOREIGN KEY (`silabo_id`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`preguntas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`preguntas` (
  `id` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `respuesta` VARCHAR(45) NULL,
  `idevaluacion` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_preguntas_evaluacion1_idx` (`idevaluacion` ASC),
  CONSTRAINT `fk_preguntas_evaluacion1`
    FOREIGN KEY (`idevaluacion`)
    REFERENCES `academicSystemDB`.`evaluacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`res-preguntas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`res-preguntas` (
  `id` INT NOT NULL,
  `nivel` INT NULL,
  `idresultados` INT NOT NULL,
  `id_preguntas` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_resp-preguntas_resultados1_idx` (`idresultados` ASC),
  INDEX `fk_resp-preguntas_preguntas1_idx` (`id_preguntas` ASC),
  CONSTRAINT `fk_resp-preguntas_resultados1`
    FOREIGN KEY (`idresultados`)
    REFERENCES `academicSystemDB`.`resultados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resp-preguntas_preguntas1`
    FOREIGN KEY (`id_preguntas`)
    REFERENCES `academicSystemDB`.`preguntas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`departamento-academico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`departamento-academico` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `departamento-academico` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`docentes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`docentes` (
  `id` INT NOT NULL,
  `dni` INT(8) NULL,
  `nombre` VARCHAR(45) NULL,
  `grado_academico` VARCHAR(45) NULL,
  `docentescol` VARCHAR(45) NULL,
  `iddepartamentoacademico` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_docentes_departamento-academico1_idx` (`iddepartamentoacademico` ASC),
  CONSTRAINT `fk_docentes_departamento-academico1`
    FOREIGN KEY (`iddepartamentoacademico`)
    REFERENCES `academicSystemDB`.`departamento-academico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`silabo-doc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`silabo-doc` (
  `id` INT NOT NULL,
  `silabo_id` INT(8) NOT NULL,
  `iddocente` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_silabo-doc_silabo1_idx` (`silabo_id` ASC),
  INDEX `fk_silabo-doc_docentes1_idx` (`iddocente` ASC),
  CONSTRAINT `fk_silabo-doc_silabo1`
    FOREIGN KEY (`silabo_id`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_silabo-doc_docentes1`
    FOREIGN KEY (`iddocente`)
    REFERENCES `academicSystemDB`.`docentes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

