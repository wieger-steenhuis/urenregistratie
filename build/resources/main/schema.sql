DROP SCHEMA IF EXISTS `data`;
CREATE SCHEMA  IF NOT EXISTS `data` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `data`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


CREATE TABLE IF NOT EXISTS `trainer` (
  `id`         INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(200)     DEFAULT NULL,
  `last_name`  VARCHAR(200)     DEFAULT NULL,
  `phone_nr`   VARCHAR(200)     DEFAULT NULL,
  `e_mail`     VARCHAR(200)     DEFAULT NULL,
  `pass_word`  VARCHAR(200)     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 6;

  CREATE TABLE IF NOT EXISTS `customer` (
  `id`         INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(200)     DEFAULT NULL,
  `last_name`  VARCHAR(200)     DEFAULT NULL,
  `phone_nr`   VARCHAR(200)     DEFAULT NULL,
  `e_mail`     VARCHAR(200)     DEFAULT NULL,
  `pin`        VARCHAR(200)     DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 9;

CREATE TABLE IF NOT EXISTS `subscription` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `customer_id` INT(11) NOT NULL,
  `trainer_id`  INT(11)          DEFAULT NULL,
  `subscr_type` VARCHAR(200)     DEFAULT NULL,
  `start_date`  DATE             DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cust_id` (`customer_id`),
  KEY `trainer_id` (`trainer_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 2;

  CREATE TABLE IF NOT EXISTS `sport_session` (
  `id`              INT(11) NOT NULL AUTO_INCREMENT,
  `subscription_id` INT(11) NOT NULL,
  `customer_id` INT(11) NOT NULL,
  `trainer_id` INT(11) NOT NULL,
  `date`            VARCHAR(200)     DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `subscription_id` (`subscription_id`),
  KEY `customer_id` (`customer_id`),
  KEY `trainer_id` (`trainer_id`)

  )
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 13;

ALTER TABLE `sport_session`
  ADD CONSTRAINT `sport_session_ibfk_1` FOREIGN KEY (`subscription_id`) REFERENCES `subscription` (`id`)ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `sport_session_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `sport_session_ibfk_3` FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `subscription`
  ADD CONSTRAINT `subscription_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `subscription_ibfk_2` FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
