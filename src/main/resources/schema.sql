DROP DATABASE IF EXISTS ssm;
CREATE DATABASE ssm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ssm;

CREATE TABLE Todo
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `content`   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `ssm`.`Weibo`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `ct` INT NOT NULL ,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ssm`.`Comment`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `content` TEXT NOT NULL,
    `weibo_id` INT NOT NULL ,
    PRIMARY KEY (`id`)
);

CREATE TABLE `ssm`.`Topic`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `title`   VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    `userId` INT NOT NULL ,
    `createdTime` DATE NOT NULL ,
    `updatedTime` DATE NOT NULL ,
    PRIMARY KEY (`id`)
);

CREATE TABLE `ssm`.`TopicComment`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `content` TEXT NOT NULL,
    `userId` INT NOT NULL ,
    `topicId` INT NOT NULL ,
    `createdTime` DATE NOT NULL ,
    `updatedTime` DATE NOT NULL ,
    PRIMARY KEY (`id`)
);


CREATE TABLE `ssm`.`User`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(500) NOT NULL,
  `mail` VARCHAR(500) NOT NULL,
  `createdTime` DATE NOT NULL ,
  `updatedTime` DATE NOT NULL ,
  `role` ENUM('admin', 'guest', 'normal') NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO ssm.User
    (username, password, avatar, mail, createdTime, updatedTime, role)
     VALUES ('Gaov', 'abc123', '/avatar.png', 'gaov331@163.com', '2019-11-11', '2019-11-11', 'admin');
