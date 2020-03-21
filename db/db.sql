SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `dbmdsysfolha` ;
CREATE SCHEMA IF NOT EXISTS `dbmdsysfolha` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `dbmdsysfolha` ;

-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbcargos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbcargos` (
  `carg_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `carg_descricao` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`carg_sequencial`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbfuncionarios`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbfuncionarios` (
  `func_cpf` VARCHAR(11) NOT NULL ,
  `func_nome` VARCHAR(100) NOT NULL ,
  `func_salario` FLOAT NOT NULL ,
  `func_ativo` VARCHAR(1) NULL DEFAULT 'S' ,
  `func_dtAdmissao` DATETIME NULL ,
  `func_dtDemissao` DATETIME NULL ,
  `func_cargo` INT NULL ,
  `func_loja` INT NOT NULL ,
  PRIMARY KEY (`func_cpf`) ,
  UNIQUE INDEX `func_cpf_UNIQUE` (`func_cpf` ASC) ,
  INDEX `fk_cargo_idx` (`func_cargo` ASC) ,
  CONSTRAINT `fk_cargo`
    FOREIGN KEY (`func_cargo` )
    REFERENCES `dbmdsysfolha`.`tbcargos` (`carg_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbbeneficios`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbbeneficios` (
  `benef_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `benef_descricao` VARCHAR(45) NOT NULL ,
  `benef_tipo_valor` VARCHAR(1) NOT NULL DEFAULT 'M' COMMENT 'M=Moeda; P=Porcentagem' ,
  `benef_ordem_calculo` INT NOT NULL ,
  `benef_base_calculo` VARCHAR(1) NULL DEFAULT 'B' COMMENT 'B=Bruto;L=Liquido' ,
  `benef_altera_base_calculo` VARCHAR(1) NULL DEFAULT 'N' COMMENT 'N=Não; S=Sim' ,
  PRIMARY KEY (`benef_sequencial`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbdescontos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbdescontos` (
  `desc_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `desc_descricao` VARCHAR(45) NOT NULL ,
  `desc_tipo_valor` VARCHAR(1) NOT NULL DEFAULT 'M' COMMENT 'M=Moeda; P=Porcentagem' ,
  `desc_ordem_calculo` INT NOT NULL ,
  `desc_base_calculo` VARCHAR(1) NULL DEFAULT 'B' COMMENT 'B=Bruto;L=Liquido' ,
  PRIMARY KEY (`desc_sequencial`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbcargobeneficio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbcargobeneficio` (
  `carg_sequencial` INT NOT NULL ,
  `benef_sequencial` INT NOT NULL ,
  `cgben_valor` FLOAT NOT NULL ,
  INDEX `fk_cargo_idx` (`carg_sequencial` ASC) ,
  INDEX `fk_beneficio_idx` (`benef_sequencial` ASC) ,
  PRIMARY KEY (`carg_sequencial`, `benef_sequencial`) ,
  CONSTRAINT `fk_cgben_cargo`
    FOREIGN KEY (`carg_sequencial` )
    REFERENCES `dbmdsysfolha`.`tbcargos` (`carg_sequencial` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cgben_beneficio`
    FOREIGN KEY (`benef_sequencial` )
    REFERENCES `dbmdsysfolha`.`tbbeneficios` (`benef_sequencial` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbcargodesconto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbcargodesconto` (
  `carg_sequencial` INT NOT NULL ,
  `desc_sequencial` INT NOT NULL ,
  `cgdes_valor` FLOAT NOT NULL ,
  INDEX `fk_cargo_desc_idx` (`carg_sequencial` ASC) ,
  INDEX `fk_cgdes_desconto_idx` (`desc_sequencial` ASC) ,
  PRIMARY KEY (`carg_sequencial`, `desc_sequencial`) ,
  CONSTRAINT `fk_cgdes_cargo`
    FOREIGN KEY (`carg_sequencial` )
    REFERENCES `dbmdsysfolha`.`tbcargos` (`carg_sequencial` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cgdes_desconto`
    FOREIGN KEY (`desc_sequencial` )
    REFERENCES `dbmdsysfolha`.`tbdescontos` (`desc_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbfolhapagamento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbfolhapagamento` (
  `fpgto_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `fpgto_ano` INT NOT NULL ,
  `fpgto_mes` INT NOT NULL ,
  `fpgto_data_criacao` DATETIME NOT NULL ,
  `fpgto_status` VARCHAR(1) NOT NULL DEFAULT 'A' COMMENT 'A=Aberta\nG=Gerada\nF=Finalizada' ,
  PRIMARY KEY (`fpgto_sequencial`) ,
  UNIQUE INDEX `UNIQ_FPGTO` (`fpgto_ano` ASC, `fpgto_mes` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tboutroslancamentos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tboutroslancamentos` (
  `olcto_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `olcto_descricao` VARCHAR(45) NOT NULL ,
  `olcto_tipo` VARCHAR(1) NOT NULL ,
  `olcto_valor` FLOAT NOT NULL ,
  `olcto_data` DATETIME NOT NULL ,
  `olcto_funcionario` VARCHAR(11) NULL ,
  `olcto_data_criacao` DATETIME NOT NULL ,
  `olcto_arquivo` VARCHAR(45) NULL ,
  PRIMARY KEY (`olcto_sequencial`) ,
  INDEX `fk_olcto_funcionario_idx` (`olcto_funcionario` ASC) ,
  UNIQUE INDEX `idx_olcto_unique` (`olcto_funcionario` ASC, `olcto_sequencial` ASC) ,
  CONSTRAINT `fk_olcto_funcionario`
    FOREIGN KEY (`olcto_funcionario` )
    REFERENCES `dbmdsysfolha`.`tbfuncionarios` (`func_cpf` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbparametros`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbparametros` (
  `pram_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `pram_descricao` VARCHAR(45) NOT NULL ,
  `pram_valor` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`pram_sequencial`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbusuariosistema`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbusuariosistema` (
  `user_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `user_nome` VARCHAR(45) NOT NULL ,
  `user_login` VARCHAR(45) NOT NULL ,
  `user_senha` VARCHAR(45) NOT NULL ,
  `user_ativo` TINYINT(1) NULL ,
  PRIMARY KEY (`user_sequencial`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbfolhapagamentobeneficio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbfolhapagamentobeneficio` (
  `fpgben_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `fpgben_folha` INT NOT NULL ,
  `fpgben_beneficio` INT NOT NULL ,
  `fpgben_valor_calculado` FLOAT NOT NULL ,
  `fpgben_funcionario` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`fpgben_sequencial`) ,
  INDEX `fk_fpgben_folha_idx` (`fpgben_folha` ASC) ,
  INDEX `fk_fpgben_beneficio_idx` (`fpgben_beneficio` ASC) ,
  UNIQUE INDEX `idx_fpgben_unique` (`fpgben_folha` ASC, `fpgben_funcionario` ASC, `fpgben_beneficio` ASC) ,
  INDEX `fk_fpgben_funcionario_idx` (`fpgben_funcionario` ASC) ,
  CONSTRAINT `fk_fpgben_folha`
    FOREIGN KEY (`fpgben_folha` )
    REFERENCES `dbmdsysfolha`.`tbfolhapagamento` (`fpgto_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fpgben_beneficio`
    FOREIGN KEY (`fpgben_beneficio` )
    REFERENCES `dbmdsysfolha`.`tbbeneficios` (`benef_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fpgben_funcionario`
    FOREIGN KEY (`fpgben_funcionario` )
    REFERENCES `dbmdsysfolha`.`tbfuncionarios` (`func_cpf` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbfolhapagamentodesconto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbfolhapagamentodesconto` (
  `fpgdesc_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `fpgdesc_folha` INT NOT NULL ,
  `fpgdesc_desconto` INT NOT NULL ,
  `fpgdesc_valor_calculado` FLOAT NOT NULL ,
  `fpgdesc_funcionario` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`fpgdesc_sequencial`) ,
  INDEX `fk_fpgdesc_folha_idx` (`fpgdesc_folha` ASC) ,
  INDEX `fk_fpgdesc_desconto_idx` (`fpgdesc_desconto` ASC) ,
  UNIQUE INDEX `idx_fpgdesc_unique` (`fpgdesc_folha` ASC, `fpgdesc_funcionario` ASC, `fpgdesc_desconto` ASC) ,
  INDEX `fk_fpgdesc_funcionario_idx` (`fpgdesc_funcionario` ASC) ,
  CONSTRAINT `fk_fpgdesc_folha`
    FOREIGN KEY (`fpgdesc_folha` )
    REFERENCES `dbmdsysfolha`.`tbfolhapagamento` (`fpgto_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fpgdesc_desconto`
    FOREIGN KEY (`fpgdesc_desconto` )
    REFERENCES `dbmdsysfolha`.`tbdescontos` (`desc_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fpgdesc_funcionario`
    FOREIGN KEY (`fpgdesc_funcionario` )
    REFERENCES `dbmdsysfolha`.`tbfuncionarios` (`func_cpf` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbfolhapagamentooutroslctos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbfolhapagamentooutroslctos` (
  `fpgolc_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `fpgolc_folha` INT NOT NULL ,
  `fpgolc_lancamento` INT NOT NULL ,
  PRIMARY KEY (`fpgolc_sequencial`) ,
  INDEX `fk_fpgolc_folha_idx` (`fpgolc_folha` ASC) ,
  INDEX `fk_fpgolc_lancamento_idx` (`fpgolc_lancamento` ASC) ,
  CONSTRAINT `fk_fpgolc_folha`
    FOREIGN KEY (`fpgolc_folha` )
    REFERENCES `dbmdsysfolha`.`tbfolhapagamento` (`fpgto_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fpgolc_lancamento`
    FOREIGN KEY (`fpgolc_lancamento` )
    REFERENCES `dbmdsysfolha`.`tboutroslancamentos` (`olcto_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbarquivooutroslctos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbarquivooutroslctos` (
  `arqlc_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `arqlc_nome` VARCHAR(45) NOT NULL ,
  `arqlc_qt_debitos` INT NOT NULL ,
  `arqlc_qt_creditos` INT NOT NULL ,
  `arqlc_qt_total` INT NOT NULL ,
  PRIMARY KEY (`arqlc_sequencial`) ,
  UNIQUE INDEX `arqlc_nome_UNIQUE` (`arqlc_nome` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbextras`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbextras` (
  `ext_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `ext_descricao` VARCHAR(45) NOT NULL ,
  `ext_tipo_valor` VARCHAR(1) NOT NULL ,
  `ext_tipo_lcto` VARCHAR(1) NOT NULL ,
  PRIMARY KEY (`ext_sequencial`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbfuncionarioextra`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbfuncionarioextra` (
  `ext_sequencial` INT NOT NULL ,
  `func_cpf` VARCHAR(11) NOT NULL ,
  `fnext_valor` FLOAT NOT NULL ,
  PRIMARY KEY (`ext_sequencial`, `func_cpf`) ,
  INDEX `fk_fnext_funcionario_idx` (`func_cpf` ASC) ,
  CONSTRAINT `fk_fnext_extra`
    FOREIGN KEY (`ext_sequencial` )
    REFERENCES `dbmdsysfolha`.`tbextras` (`ext_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fnext_funcionario`
    FOREIGN KEY (`func_cpf` )
    REFERENCES `dbmdsysfolha`.`tbfuncionarios` (`func_cpf` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbmdsysfolha`.`tbfolhapagamentofuncionarioextra`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbmdsysfolha`.`tbfolhapagamentofuncionarioextra` (
  `fpgext_sequencial` INT NOT NULL AUTO_INCREMENT ,
  `fpgext_valor_calculado` FLOAT NOT NULL ,
  `fpgext_funcionario` VARCHAR(11) NOT NULL ,
  `fpgext_folha` INT NOT NULL ,
  `fpgext_extra` INT NOT NULL ,
  PRIMARY KEY (`fpgext_sequencial`) ,
  INDEX `fk_fpgext_funcionario_idx` (`fpgext_funcionario` ASC) ,
  INDEX `fk_fpgext_lactoextra_idx` (`fpgext_extra` ASC) ,
  INDEX `fk_fpgext_folha_idx` (`fpgext_folha` ASC) ,
  UNIQUE INDEX `idx_fpgext_unique` (`fpgext_extra` ASC, `fpgext_folha` ASC, `fpgext_funcionario` ASC) ,
  CONSTRAINT `fk_fpgext_folha`
    FOREIGN KEY (`fpgext_folha` )
    REFERENCES `dbmdsysfolha`.`tbfolhapagamento` (`fpgto_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fpgext_funcionario`
    FOREIGN KEY (`fpgext_funcionario` )
    REFERENCES `dbmdsysfolha`.`tbfuncionarios` (`func_cpf` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fpgext_lactoextra`
    FOREIGN KEY (`fpgext_extra` )
    REFERENCES `dbmdsysfolha`.`tbextras` (`ext_sequencial` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;