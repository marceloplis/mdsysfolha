SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

ALTER TABLE `dbmdsysfolha`.`tbfolhapagamento` ADD COLUMN `fpgto_tipo` VARCHAR(1) NULL DEFAULT 'M' COMMENT 'M-Mensal; D-Décimo Terceiro'  AFTER `fpgto_status` 
, DROP INDEX `UNIQ_FPGTO` 
, ADD UNIQUE INDEX `UNIQ_FPGTO` (`fpgto_ano` ASC, `fpgto_mes` ASC, `fpgto_tipo` ASC) ;

ALTER TABLE `dbmdsysfolha`.`tboutroslancamentos` ADD COLUMN `olcto_tipo_folha` VARCHAR(1) NULL DEFAULT 'M' COMMENT 'M-Mensal; D-Décimo Terceiro'  AFTER `olcto_arquivo` , CHANGE COLUMN `olcto_tipo` `olcto_tipo` VARCHAR(1) NOT NULL COMMENT 'C-Crédito; D-Débito'  ;

CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbfolhapagamentofuncionario` (
  `fpgfunc_sequencial` INT(11) NOT NULL AUTO_INCREMENT ,
  `fpgfunc_folha` INT(11) NOT NULL ,
  `fpgfunc_funcionario` VARCHAR(11) NOT NULL ,
  `fpgfunc_cargo` VARCHAR(45) NOT NULL ,
  `fpgfunc_salario` FLOAT(11) NOT NULL ,
  `fpgfunc_loja` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`fpgfunc_sequencial`) ,
  INDEX `fk_fpgfunc_folha` (`fpgfunc_folha` ASC) ,
  INDEX `fk_fpgfunc_funcionario` (`fpgfunc_funcionario` ASC) ,
  CONSTRAINT `fk_fpgfunc_folha`
    FOREIGN KEY (`fpgfunc_folha` )
    REFERENCES `dbmdsysfolha`.`tbfolhapagamento` (`fpgto_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fpgfunc_funcionario`
    FOREIGN KEY (`fpgfunc_funcionario` )
    REFERENCES `dbmdsysfolha`.`tbfuncionarios` (`func_cpf` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
