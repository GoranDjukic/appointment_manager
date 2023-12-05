START TRANSACTION;

CREATE TABLE IF NOT EXISTS `appointment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `start_time` DATETIME,
    `end_time` DATETIME,
    `confirmed` BIT(1) DEFAULT false,
    `client_id` BIGINT
);

CREATE TABLE IF NOT EXISTS `client` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255)
);

ALTER TABLE `appointment`
ADD CONSTRAINT FK_client_id
FOREIGN KEY (client_id) REFERENCES `client`(id);

ALTER TABLE `client` ADD CONSTRAINT UK_email UNIQUE (`email`);

COMMIT;