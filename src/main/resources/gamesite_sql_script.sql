-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema gamesite_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gamesite_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gamesite_db` DEFAULT CHARACTER SET utf8 ;
USE `gamesite_db` ;

-- -----------------------------------------------------
-- Table `gamesite_db`.`developers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamesite_db`.`developers` (
  `developer_id` INT(11) NOT NULL,
  `developer_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`developer_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamesite_db`.`genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamesite_db`.`genres` (
  `genre_id` INT(11) NOT NULL,
  `genre_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`genre_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamesite_db`.`games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamesite_db`.`games` (
  `game_id` INT(11) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `release_date` DATE NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `image_url` VARCHAR(255) NULL DEFAULT NULL,
  `age_rating` VARCHAR(45) NULL DEFAULT NULL,
  `developer_id` INT(11) NOT NULL,
  `genre_id` INT(11) NOT NULL,
  PRIMARY KEY (`game_id`),
  INDEX `fk_games_developers1_idx` (`developer_id` ASC),
  INDEX `fk_games_genres1_idx` (`genre_id` ASC),
  CONSTRAINT `fk_games_developers1`
    FOREIGN KEY (`developer_id`)
    REFERENCES `gamesite_db`.`developers` (`developer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_games_genres1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `gamesite_db`.`genres` (`genre_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamesite_db`.`platforms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamesite_db`.`platforms` (
  `platform_id` INT(11) NOT NULL,
  `platform_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`platform_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamesite_db`.`platforms_has_games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamesite_db`.`platforms_has_games` (
  `platform_id` INT(11) NOT NULL,
  `game_id` INT(11) NOT NULL,
  PRIMARY KEY (`platform_id`, `game_id`),
  INDEX `fk_platforms_has_games1_games1_idx` (`game_id` ASC),
  INDEX `fk_platforms_has_games1_platforms1_idx` (`platform_id` ASC),
  CONSTRAINT `fk_platforms_has_games1_platforms1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `gamesite_db`.`platforms` (`platform_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_platforms_has_games1_games1`
    FOREIGN KEY (`game_id`)
    REFERENCES `gamesite_db`.`games` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamesite_db`.`games_in_stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamesite_db`.`games_in_stock` (
  `games_in_stock_id` INT(11) NOT NULL,
  `isReserved` TINYINT(4) NOT NULL,
  `platform_id` INT(11) NOT NULL,
  `game_id` INT(11) NOT NULL,
  PRIMARY KEY (`games_in_stock_id`),
  INDEX `fk_games_in_stock_platforms_has_games11_idx` (`platform_id` ASC, `game_id` ASC),
  CONSTRAINT `fk_games_in_stock_platforms_has_games11`
    FOREIGN KEY (`platform_id` , `game_id`)
    REFERENCES `gamesite_db`.`platforms_has_games` (`platform_id` , `game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamesite_db`.`games_platforms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamesite_db`.`games_platforms` (
  `platform_id` INT(11) NOT NULL,
  `game_id` INT(11) NOT NULL,
  PRIMARY KEY (`platform_id`, `game_id`),
  INDEX `fk_platforms_has_games_games1_idx` (`game_id` ASC),
  INDEX `fk_platforms_has_games_platforms1_idx` (`platform_id` ASC),
  CONSTRAINT `fk_platforms_has_games_games1`
    FOREIGN KEY (`game_id`)
    REFERENCES `gamesite_db`.`games` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_platforms_has_games_platforms1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `gamesite_db`.`platforms` (`platform_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamesite_db`.`platforms_has_games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamesite_db`.`platforms_has_games` (
  `platform_id` INT(11) NOT NULL,
  `game_id` INT(11) NOT NULL,
  PRIMARY KEY (`platform_id`, `game_id`),
  INDEX `fk_platforms_has_games1_games1_idx` (`game_id` ASC),
  INDEX `fk_platforms_has_games1_platforms1_idx` (`platform_id` ASC),
  CONSTRAINT `fk_platforms_has_games1_platforms1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `gamesite_db`.`platforms` (`platform_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_platforms_has_games1_games1`
    FOREIGN KEY (`game_id`)
    REFERENCES `gamesite_db`.`games` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `gamesite_db` ;

-- -----------------------------------------------------
-- Placeholder table for view `gamesite_db`.`games_complete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamesite_db`.`games_complete` (`game_id` INT, `title` INT, `release_date` INT, `description` INT, `image_url` INT, `age_rating` INT, `developer_name` INT, `genre_name` INT);

-- -----------------------------------------------------
-- View `gamesite_db`.`games_complete`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gamesite_db`.`games_complete`;
USE `gamesite_db`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `gamesite_db`.`games_complete` AS select `j`.`game_id` AS `game_id`,`j`.`title` AS `title`,`j`.`release_date` AS `release_date`,`j`.`description` AS `description`,`j`.`image_url` AS `image_url`,`j`.`age_rating` AS `age_rating`,`j`.`developer_name` AS `developer_name`,`gen`.`genre_name` AS `genre_name` from (((select `g`.`game_id` AS `game_id`,`g`.`title` AS `title`,`g`.`release_date` AS `release_date`,`g`.`description` AS `description`,`g`.`image_url` AS `image_url`,`g`.`age_rating` AS `age_rating`,`g`.`genre_id` AS `genre_id`,`d`.`developer_name` AS `developer_name` from (`gamesite_db`.`games` `g` join `gamesite_db`.`developers` `d`) where (`g`.`developer_id` = `d`.`developer_id`))) `j` join `gamesite_db`.`genres` `gen`) where (`j`.`genre_id` = `gen`.`genre_id`);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `gamesite_db`.`developers`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamesite_db`;
INSERT INTO `gamesite_db`.`developers` (`developer_id`, `developer_name`) VALUES (1, 'Square Enix');
INSERT INTO `gamesite_db`.`developers` (`developer_id`, `developer_name`) VALUES (2, 'Nintendo');

COMMIT;


-- -----------------------------------------------------
-- Data for table `gamesite_db`.`genres`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamesite_db`;
INSERT INTO `gamesite_db`.`genres` (`genre_id`, `genre_name`) VALUES (1, 'RPG');
INSERT INTO `gamesite_db`.`genres` (`genre_id`, `genre_name`) VALUES (2, 'Platformer');

COMMIT;


-- -----------------------------------------------------
-- Data for table `gamesite_db`.`games`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamesite_db`;
INSERT INTO `gamesite_db`.`games` (`game_id`, `title`, `release_date`, `description`, `image_url`, `age_rating`, `developer_id`, `genre_id`) VALUES (1, 'Final Fantasy XV', '2016-11-29', 'Tabatas magnum opus', NULL, '16+', 1, 1);
INSERT INTO `gamesite_db`.`games` (`game_id`, `title`, `release_date`, `description`, `image_url`, `age_rating`, `developer_id`, `genre_id`) VALUES (2, 'Super Mario World', '1992-05-20', 'Put that rock over there', NULL, '3+', 2, 2);
INSERT INTO `gamesite_db`.`games` (`game_id`, `title`, `release_date`, `description`, `image_url`, `age_rating`, `developer_id`, `genre_id`) VALUES (3, 'Kingdom Hearts 3', '2018-08-08', 'A fantasy based on a fantasy', NULL, '12+', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gamesite_db`.`platforms`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamesite_db`;
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (1, 'PlayStation 4');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (2, 'Xbox One');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (3, 'Super Nintendo');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (4, 'PC');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (5, 'Nintendo Switch');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (6, 'Nintendo 64');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (7, 'SEGA Megadrive');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (8, 'Playstation');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (9, 'Playstation 2');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (10, 'Nintendo Wii');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (11, 'Playstation 3');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (12, 'Nintendo Wii U');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (13, 'Nintendo 3DS');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (14, 'Nintendo DS');
INSERT INTO `gamesite_db`.`platforms` (`platform_id`, `platform_name`) VALUES (15, 'Playstation Portable');

COMMIT;

