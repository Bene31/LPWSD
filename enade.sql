SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE `mydb`;

CREATE SCHEMA IF NOT EXISTS `enade` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

USE `enade`;

CREATE  TABLE IF NOT EXISTS `enade`.`curso` (
  `id_curso` INT(11) NOT NULL AUTO_INCREMENT ,
  `nm_curso` VARCHAR(45) NOT NULL ,
  `sigla` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id_curso`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE  TABLE IF NOT EXISTS `enade`.`usuario` (
  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT primary key,
  `nm_usuario` VARCHAR(255) NOT NULL ,
  `cpf` VARCHAR(15) NOT NULL UNIQUE,
  `email` VARCHAR(45) NOT NULL ,
  `tipo_usuario` ENUM('A', 'P', 'M') NOT NULL ,
  `id_curso` int,
 -- `reset` TINYINT(4) NOT NULL ,*/
  `senha` VARCHAR(45) NOT NULL ,
  CONSTRAINT `fk_usuario_curso` FOREIGN KEY (`id_curso` ) 
  REFERENCES `enade`.`curso` (`id_curso` ) 
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

INSERT INTO usuario (nm_usuario, cpf, email, tipo_usuario, senha) VALUES
('ADMIN', '00000000000','admin@gmail.com', 'M', 'admin'),
('PROFESSOR', '11111111111','prof1@gmail.com', 'P', 'prof1');

CREATE  TABLE IF NOT EXISTS `enade`.`prova` (
  `id_prova` INT(11) NOT NULL AUTO_INCREMENT ,
  `ano` varchar(4) NOT NULL ,
  `curso_id_curso` INT(11) NULL ,
  PRIMARY KEY (`id_prova`) ,
  INDEX `fk_prova_curso1_idx` (`curso_id_curso` ASC) ,
  CONSTRAINT `fk_prova_curso1`
    FOREIGN KEY (`curso_id_curso` )
    REFERENCES `enade`.`curso` (`id_curso` )
   )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE  TABLE IF NOT EXISTS `enade`.`questao` (
  `id_questao` INT(11) NOT NULL AUTO_INCREMENT ,
  `descricao_questao` VARCHAR(255) NOT NULL ,
  `tipo_questao` ENUM('discursiva geral', 'objetiva geral', 'discursiva especifica', 'objetiva especifica') not null ,
  `prova_id_prova` INT(11)  NULL ,
  PRIMARY KEY (`id_questao`) ,
    FOREIGN KEY (`prova_id_prova` )
    REFERENCES `enade`.`prova` (`id_prova` )
   )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE  TABLE IF NOT EXISTS `enade`.`resposta` (
  `id_resposta` INT(11) NOT NULL AUTO_INCREMENT ,
  `resposta_correta` VARCHAR(45) NOT NULL ,
  `questao_id_questao` INT(11) NULL ,
  `justificativa` VARCHAR(255) NOT NULL ,
  `opcao` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id_resposta`) ,
  INDEX `fk_resposta_questao1_idx` (`questao_id_questao` ASC) ,
  CONSTRAINT `fk_resposta_questao1`
    FOREIGN KEY (`questao_id_questao` )
    REFERENCES `enade`.`questao` (`id_questao` )
    )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

/*CREATE  TABLE IF NOT EXISTS `enade`.`curso_has_usuario` (
  `curso_id_curso` INT(11) NOT NULL ,
  `usuario_id_usuario` INT(11) NOT NULL ,
  PRIMARY KEY (`curso_id_curso`, `usuario_id_usuario`) ,
  INDEX `fk_curso_has_usuario_usuario1_idx` (`usuario_id_usuario` ASC) ,
  INDEX `fk_curso_has_usuario_curso1_idx` (`curso_id_curso` ASC) ,
  CONSTRAINT `fk_curso_has_usuario_curso1`
    FOREIGN KEY (`curso_id_curso` )
    REFERENCES `enade`.`curso` (`id_curso` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_curso_has_usuario_usuario1`
    FOREIGN KEY (`usuario_id_usuario` )
    REFERENCES `enade`.`usuario` (`id_usuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;*/

CREATE  TABLE IF NOT EXISTS `enade`.`usuario_has_questao` (
  `usuario_id_usuario` INT(11) NOT NULL ,
  `questao_id_questao` INT(11) NOT NULL ,
  `questao_prova_id_prova` INT(11) NOT NULL ,
  `resposta_id_resposta` INT(11) NOT NULL ,
  PRIMARY KEY (`usuario_id_usuario`, `questao_id_questao`, `questao_prova_id_prova`) ,
  INDEX `fk_usuario_has_questao_questao1_idx` (`questao_id_questao` ASC, `questao_prova_id_prova` ASC) ,
  INDEX `fk_usuario_has_questao_usuario1_idx` (`usuario_id_usuario` ASC) ,
  INDEX `fk_usuario_has_questao_resposta1_idx` (`resposta_id_resposta` ASC) ,
  CONSTRAINT `fk_usuario_has_questao_usuario1`
    FOREIGN KEY (`usuario_id_usuario` )
    REFERENCES `enade`.`usuario` (`id_usuario` )
    ,
  CONSTRAINT `fk_usuario_has_questao_questao1`
    FOREIGN KEY (`questao_id_questao`)
    REFERENCES `enade`.`questao` (`id_questao`)
    ,
  CONSTRAINT `fk_usuario_has_questao_resposta1`
    FOREIGN KEY (`resposta_id_resposta` )
    REFERENCES `enade`.`resposta` (`id_resposta` )
    )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
