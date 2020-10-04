ALTER TABLE `dbmdsysfolha`.`tbfolhapagamento` 
CHANGE COLUMN `fpgto_tipo` `fpgto_tipo` VARCHAR(1) NULL DEFAULT 'M' COMMENT 'M-Mensal; D-Décimo Terceiro; R-Rescisão' ;


ALTER TABLE `dbmdsysfolha`.`tboutroslancamentos` 
CHANGE COLUMN `olcto_tipo_folha` `olcto_tipo_folha` VARCHAR(1) NOT NULL DEFAULT 'M' COMMENT 'M-Mensal; D-Décimo Terceiro; R-Rescisão\'\n' ,
DROP INDEX `idx_olcto_unique` ;
;

ALTER TABLE `dbmdsysfolha`.`tboutroslancamentos` 
ADD UNIQUE INDEX `idx_olcto_unique` (`olcto_tipo_folha` ASC, `olcto_sequencial` ASC, `olcto_funcionario` ASC);
;

