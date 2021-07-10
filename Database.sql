CREATE DATABASE IF NOT EXISTS `exam`;
USE `exam`;
DROP TABLE IF EXISTS `receive`;
CREATE TABLE `receive`
(
    `id`       int(11)     NOT NULL AUTO_INCREMENT,
    `name`     varchar(55) NOT NULL,
    `unit`     int         NOT NULL,
    `quantity` int         NOT NULL,
    `price`    int         NOT NULL,
    PRIMARY KEY (`id`)
);