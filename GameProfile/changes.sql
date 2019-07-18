ALTER TABLE `academicsystemdb`.`silabo` 
ADD COLUMN `periodo_academico` VARCHAR(45) NULL AFTER `requisitos_evaluacion`;

ALTER TABLE `academicsystemdb`.`horario` 
ADD COLUMN `horario` VARCHAR(45) NULL AFTER `idsilabo`;

ALTER TABLE `academicsystemdb`.`horario` 
CHANGE COLUMN `grupo` `grupo` VARCHAR(30) NULL DEFAULT NULL ,
CHANGE COLUMN `horario` `horario` VARCHAR(150) NULL DEFAULT NULL ;

ALTER TABLE `academicsystemdb`.`competencias` 
DROP FOREIGN KEY `fk_competencias_resultados1`;
ALTER TABLE `academicsystemdb`.`competencias` 
DROP COLUMN `idresultados`,
DROP INDEX `fk_competencias_resultados1_idx` ;

CREATE TABLE `academicsystemdb`.`competencias-resultados` (
  `idcompetencias-resultados` INT NOT NULL,
  `competencias_id` INT NULL,
  `resultados_id` INT NULL,
  PRIMARY KEY (`idcompetencias-resultados`),
  INDEX `fk_competencias-resultados_competencias1_idx1` (`competencias_id` ASC),
  INDEX `fk_competencias-resultados_resultados1_idx1` (`resultados_id` ASC),
  CONSTRAINT `fk_competencias-resultados_competencias1`
    FOREIGN KEY (`competencias_id`)
    REFERENCES `academicsystemdb`.`competencias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_competencias-resultados_resultados1`
    FOREIGN KEY (`resultados_id`)
    REFERENCES `academicsystemdb`.`resultados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
ALTER TABLE `academicsystemdb`.`competencias` 
DROP COLUMN `nivel`;

ALTER TABLE `academicsystemdb`.`competencias-resultados` 
ADD COLUMN `nivel` INT(2) NULL AFTER `resultados_id`;


ALTER TABLE `academicsystemdb`.`silabo` 
ADD COLUMN `PORCENTAJE_EP1` INT(3) NULL AFTER `periodo_academico`,
ADD COLUMN `PORCENTAJE_EP2` INT(3) NULL AFTER `PORCENTAJE_EP1`,
ADD COLUMN `PORCENTAJE_EP3` INT(3) NULL AFTER `PORCENTAJE_EP2`,
ADD COLUMN `PORCENTAJE_EC1` INT(3) NULL AFTER `PORCENTAJE_EP3`,
ADD COLUMN `PORCENTAJE_EC2` INT(3) NULL AFTER `PORCENTAJE_EC1`,
ADD COLUMN `PORCENTAJE_EC3` INT(3) NULL AFTER `PORCENTAJE_EC2`,
ADD COLUMN `FECHA_EP1` VARCHAR(45) NULL AFTER `PORCENTAJE_EC3`,
ADD COLUMN `FECHA_EP2` VARCHAR(45) NULL AFTER `FECHA_EP1`,
ADD COLUMN `FECHA_EP3` VARCHAR(45) NULL AFTER `FECHA_EP2`;


ALTER TABLE `academicsystemdb`.`estrategia-enseñanza` 
DROP COLUMN `descripcion`;

ALTER TABLE `academicsystemdb`.`silabo-estrategia` 
ADD COLUMN `descripcion` VARCHAR(90) NULL AFTER `idestrategia`;

ALTER TABLE `academicsystemdb`.`cronograma` 
ADD COLUMN `tema_evaluacion` VARCHAR(45) NULL AFTER `semana`,
ADD COLUMN `avance` INT(3) NULL AFTER `tema_evaluacion`;

ALTER TABLE `academicsystemdb`.`competencias` 
CHANGE COLUMN `nombre` `nombre` VARCHAR(500) NULL DEFAULT NULL ;

ALTER TABLE `academicsystemdb`.`silabo-estrategia` 
CHANGE COLUMN `descripcion` `descripcion` VARCHAR(150) NULL DEFAULT NULL ;

ALTER TABLE `academicsystemdb`.`silabo` 
CHANGE COLUMN `evaluacion_continua` `evaluacion_continua` VARCHAR(150) NULL DEFAULT NULL ,
CHANGE COLUMN `evaluacion_periodica` `evaluacion_periodica` VARCHAR(150) NULL DEFAULT NULL ;

ALTER TABLE `academicsystemdb`.`silabo` 
CHANGE COLUMN `tipos_evaluacion` `tipos_evaluacion` VARCHAR(512) NULL DEFAULT NULL ,
CHANGE COLUMN `instrumentos` `instrumentos` VARCHAR(256) NULL DEFAULT NULL ;

ALTER TABLE `academicsystemdb`.`silabo` 
CHANGE COLUMN `requisitos_evaluacion` `requisitos_evaluacion` VARCHAR(1024) NULL DEFAULT NULL ;

ALTER TABLE `academicsystemdb`.`silabo-bibliografia` 
CHANGE COLUMN `tipo` `tipo` VARCHAR(124) NULL DEFAULT NULL ;

ALTER TABLE `academicsystemdb`.`bibliografia` 
CHANGE COLUMN `nombre` `nombre` VARCHAR(256) NULL DEFAULT NULL ,
CHANGE COLUMN `autor` `autor` VARCHAR(256) NULL DEFAULT NULL ,
CHANGE COLUMN `fecha` `fecha` VARCHAR(256) NULL DEFAULT NULL ,
CHANGE COLUMN `editorial` `editorial` VARCHAR(256) NULL DEFAULT NULL ,
CHANGE COLUMN `edicion` `edicion` VARCHAR(256) NULL DEFAULT NULL ;

ALTER TABLE `academicsystemdb`.`docentes` 
DROP COLUMN `docentescol`;

ALTER TABLE `academicsystemdb`.`docentes` 
ADD COLUMN `apellido_materno` VARCHAR(45) NULL AFTER `iddepartamentoacademico`,
ADD COLUMN `apellido_paterno` VARCHAR(45) NULL AFTER `apellido_materno`;

