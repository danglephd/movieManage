CREATE DATABASE `movies_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `main_act` (
  `idmain_act` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `gene` int(2) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idmain_act`),
  UNIQUE KEY `idmain_act_UNIQUE` (`idmain_act`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `main_genre` (
  `idmain_genre` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`idmain_genre`),
  UNIQUE KEY `idmain_genre_UNIQUE` (`idmain_genre`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `main_movie` (
  `idmain_movie` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `ivote` int(10) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `genres` varchar(255) DEFAULT NULL,
  `posterUrl` varchar(255) DEFAULT NULL,
  `isWatched` bit(1) DEFAULT b'0',
  `subtitle` bit(1) DEFAULT b'0',
  `releaseYear` int(4) DEFAULT NULL,
  `itotalScore` int(11) DEFAULT NULL,
  PRIMARY KEY (`idmain_movie`),
  UNIQUE KEY `idmain_movie_UNIQUE` (`idmain_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;

CREATE TABLE `smt_learning` (
  `idsmt_learning` int(11) NOT NULL AUTO_INCREMENT,
  `smt_action` varchar(45) NOT NULL,
  `smt_value1` mediumtext,
  `smt_value2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idsmt_learning`),
  UNIQUE KEY `idsmt_learning_UNIQUE` (`idsmt_learning`)
) ENGINE=InnoDB AUTO_INCREMENT=536 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_config` (
  `idsys_app` int(11) NOT NULL AUTO_INCREMENT,
  `keyCfg` varchar(45) NOT NULL,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idsys_app`),
  UNIQUE KEY `idsys_app_UNIQUE` (`idsys_app`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_user` (
  `idsys_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idsys_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
