DROP DATABASE IF EXISTS `testdb_test`;

CREATE DATABASE `testdb_test`;
USE `testdb_test`;

DROP TABLE IF EXISTS `team_user`;
DROP TABLE IF EXISTS `team`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `team` (
`team_id` int(11) NOT NULL,
`teamname` varchar(45),
PRIMARY KEY (`team_id`)
);

CREATE TABLE `user` (
`user_id` int(11) NOT NULL AUTO_INCREMENT,
`username` varchar(45),
`password` varchar(45),
`admin` int(11) DEFAULT NULL,
PRIMARY KEY (`user_id`)
);

CREATE TABLE `team_user` (
`team_id` int(11) NOT NULL,
`user_id` int(11) NOT NULL,
PRIMARY KEY (`team_id`,`user_id`),
CONSTRAINT `fk_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`),
CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

INSERT INTO `team` (`team_id`, `teamname`) VALUES (1,'A');
INSERT INTO `team` (`team_id`, `teamname`) VALUES (2,'B');
INSERT INTO `team` (`team_id`, `teamname`) VALUES (3,'C');
INSERT INTO `user` (`user_id`, `username`, `password`, `admin`) VALUES (1,'Anders And','1234',1);
INSERT INTO `user` (`user_id`, `username`, `password`, `admin`) VALUES (2,'Mickey Mouse','5678',1);
INSERT INTO `user` (`user_id`, `username`, `password`, `admin`) VALUES (3,'Fedtmule','1234',0);
INSERT INTO `user` (`user_id`, `username`, `password`, `admin`) VALUES (4,'George Gearløs','1234',0);
INSERT INTO `user` (`user_id`, `username`, `password`, `admin`) VALUES (5,'Bedstemor And','1234',0);
INSERT INTO `user` (`user_id`, `username`, `password`, `admin`) VALUES (6,'Onkel Joakim','1234',0);
INSERT INTO `user` (`user_id`, `username`, `password`, `admin`) VALUES (7,'Pluto','1234',0);
INSERT INTO `user` (`user_id`, `username`, `password`, `admin`) VALUES (8,'Fætter Guf','1234',0);

INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (1,2);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (1,3);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (1,7);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (2,1);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (2,4);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (2,5);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (2,6);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (2,8);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (3,1);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (3,2);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (3,3);
INSERT INTO `team_user` (`team_id`, `user_id`) VALUES (3,7);