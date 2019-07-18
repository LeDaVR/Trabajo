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
  `id_silabo` INT(8) NOT NULL,
  `semana` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_id_silabox` (`id_silabo` ASC),
  CONSTRAINT `id_silabo`
    FOREIGN KEY (`id_silabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`resultado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`resultado` (
  `id` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`asignatura-resultado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`asignatura-resultado` (
  `id` INT NOT NULL,
  `nivel` INT NOT NULL,
  `id_asignatura` INT(8) NOT NULL,
  `id_resultado` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asignatura-resultado_asignatura1_idx` (`id_asignatura` ASC),
  INDEX `fk_asignatura-resultado_resultado1_idx` (`id_resultado` ASC),
  CONSTRAINT `fk_asignatura-resultado_asignatura1`
    FOREIGN KEY (`id_asignatura`)
    REFERENCES `academicSystemDB`.`asignatura` (`casi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_asignatura-resultado_resultado1`
    FOREIGN KEY (`id_resultado`)
    REFERENCES `academicSystemDB`.`resultado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`competencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`competencia` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `nivel` INT NULL,
  `id_resultado` INT NOT NULL,
  `id_silabo` INT(8) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_competencia_resultado1_idx` (`id_resultado` ASC),
  INDEX `fk_competencia_silabo1_idx` (`id_silabo` ASC),
  CONSTRAINT `fk_competencia_resultado1`
    FOREIGN KEY (`id_resultado`)
    REFERENCES `academicSystemDB`.`resultado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_competencia_silabo1`
    FOREIGN KEY (`id_silabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`estrategia-ensenianza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`estrategia-ensenianza` (
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
  `id_silabo` INT(8) NOT NULL,
  `id_estrategia` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_silabo-estrategia_silabo1_idx` (`id_silabo` ASC),
  INDEX `fk_silabo-estrategia_estrategia-ensenianza1_idx` (`id_estrategia` ASC),
  CONSTRAINT `fk_silabo-estrategia_silabo1`
    FOREIGN KEY (`id_silabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_silabo-estrategia_estrategia-ensenianza1`
    FOREIGN KEY (`id_estrategia`)
    REFERENCES `academicSystemDB`.`estrategia-ensenianza` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`silabo-bibliografia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`silabo-bibliografia` (
  `id` INT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `id_silabo` INT(8) NOT NULL,
  `id_bibliografia` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_silabo-bibliografia_silabo1_idx` (`id_silabo` ASC),
  INDEX `fk_silabo-bibliografia_bibliografia1_idx` (`id_bibliografia` ASC),
  CONSTRAINT `fk_silabo-bibliografia_silabo1`
    FOREIGN KEY (`id_silabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_silabo-bibliografia_bibliografia1`
    FOREIGN KEY (`id_bibliografia`)
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
  `id_silabo` INT(8) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_horario_silabo1_idx` (`id_silabo` ASC),
  CONSTRAINT `fk_horario_silabo1`
    FOREIGN KEY (`id_silabo`)
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
  `id_silabo` INT(8) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_evaluacion_silabo1_idx` (`id_silabo` ASC),
  CONSTRAINT `fk_evaluacion_silabo1`
    FOREIGN KEY (`id_silabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`pregunta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`pregunta` (
  `id` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `respuesta` VARCHAR(45) NULL,
  `id_evaluacion` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pregunta_evaluacion1_idx` (`id_evaluacion` ASC),
  CONSTRAINT `fk_pregunta_evaluacion1`
    FOREIGN KEY (`id_evaluacion`)
    REFERENCES `academicSystemDB`.`evaluacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`resultado-pregunta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`resultado-pregunta` (
  `id` INT NOT NULL,
  `nivel` INT NULL,
  `id_resultado` INT NOT NULL,
  `id_pregunta` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_resp-pregunta_resultado1_idx` (`id_resultado` ASC),
  INDEX `fk_resp-pregunta_pregunta1_idx` (`id_pregunta` ASC),
  CONSTRAINT `fk_resp-pregunta_resultado1`
    FOREIGN KEY (`id_resultado`)
    REFERENCES `academicSystemDB`.`resultado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resp-pregunta_pregunta1`
    FOREIGN KEY (`id_pregunta`)
    REFERENCES `academicSystemDB`.`pregunta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`departamento_academico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`departamento_academico` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `departamento_academico` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`docente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`docente` (
  `id` INT NOT NULL,
  `dni` INT(8) NULL,
  `nombre` VARCHAR(45) NULL,
  `grado_academico` VARCHAR(45) NULL,
  `docente_col` VARCHAR(45) NULL,
  `id_departamento_academico` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_docente_departamento_academico1_idx` (`id_departamento_academico` ASC),
  CONSTRAINT `fk_docente_departamento_academico1`
    FOREIGN KEY (`id_departamento_academico`)
    REFERENCES `academicSystemDB`.`departamento_academico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academicSystemDB`.`silabo-docente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academicSystemDB`.`silabo-docente` (
  `id` INT NOT NULL,
  `id_silabo` INT(8) NOT NULL,
  `id_docente` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_silabo-docente_silabo1_idx` (`id_silabo` ASC),
  INDEX `fk_silabo-docente_docente1_idx` (`id_docente` ASC),
  CONSTRAINT `fk_silabo-docente_silabo1`
    FOREIGN KEY (`id_silabo`)
    REFERENCES `academicSystemDB`.`silabo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_silabo-docente_docente1`
    FOREIGN KEY (`id_docente`)
    REFERENCES `academicSystemDB`.`docente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Current Verion and Time Configuration
-- -----------------------------------------------------
SELECT version();
SET @@global.time_zone = ‘+00:00’;
SET @@session.time_zone = ‘+00:00’;
SELECT @@global.time_zone, @@session.time_zone;
