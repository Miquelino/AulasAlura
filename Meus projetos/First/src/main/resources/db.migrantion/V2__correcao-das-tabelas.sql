ALTER TABLE banco_rm.tipo_conta CHANGE numero `Tipo da conda` VARCHAR(100) NOT NULL;
ALTER TABLE banco_rm.tipo_conta MODIFY COLUMN `Tipo da conda` VARCHAR(100) NOT NULL;
ALTER TABLE banco_rm.tipo_conta DROP COLUMN saldo;
ALTER TABLE banco_rm.clientes DROP COLUMN deposito;
ALTER TABLE banco_rm.clientes ADD id_tipo_conta INT NOT NULL;
