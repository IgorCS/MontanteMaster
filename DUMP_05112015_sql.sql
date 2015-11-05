/*
SQLyog Ultimate v8.55 
MySQL - 5.5.45 : Database - financeiro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`financeiro` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `financeiro`;

/*Table structure for table `autorizacao` */

DROP TABLE IF EXISTS `autorizacao`;

CREATE TABLE `autorizacao` (
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `autorizacao` */

insert  into `autorizacao`(`nome`) values ('ROLE_ADMIN'),('ROLE_USER');

/*Table structure for table `lancamento` */

DROP TABLE IF EXISTS `lancamento`;

CREATE TABLE `lancamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_pagamento` date DEFAULT NULL,
  `data_vencimento` date NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `pessoa_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ax5wcm3fcn1xss5lkmlbt68pu` (`pessoa_id`),
  KEY `FK_leujf0uilgwr9k59thfsqs7fq` (`usuario_id`),
  CONSTRAINT `FK_ax5wcm3fcn1xss5lkmlbt68pu` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `FK_leujf0uilgwr9k59thfsqs7fq` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `lancamento` */

insert  into `lancamento`(`id`,`data_pagamento`,`data_vencimento`,`descricao`,`tipo`,`valor`,`pessoa_id`,`usuario_id`) values (1,NULL,'2015-10-22','testes_1','RECEITA','10.00',2,1),(2,NULL,'2015-10-22','testes_2','DESPESA','19000.00',2,1),(3,NULL,'2015-10-22','testes_3','DESPESA','590.00',4,3),(4,NULL,'2015-10-22','testes_4','RECEITA','300.00',4,3),(6,NULL,'2015-10-22','testes_5','RECEITA','4002.00',4,1),(7,NULL,'2015-10-22','testes_6','RECEITA','2800.00',5,3),(8,NULL,'2015-10-22','testes_7','DESPESA','600.00',4,3),(9,NULL,'2015-10-23','testes_8','RECEITA','1000.00',2,4);

/*Table structure for table `pessoa` */

DROP TABLE IF EXISTS `pessoa`;

CREATE TABLE `pessoa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `pessoa` */

insert  into `pessoa`(`id`,`nome`) values (1,'WWW Indústria de Alimentos'),(2,'SoftBRAX Treinamentos'),(3,'Mingau  de Milho'),(4,'BV Financeira'),(5,'katarina nogueira'),(6,'igorcs');

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_p2crt32hudkn5ovjqo5txf7g` (`role_description`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

insert  into `roles`(`role_id`,`role_description`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK_5q4rc4fh1on6567qk69uesvyf` (`role_id`),
  KEY `FK_g1uebn6mqk9qiaw45vnacmyo2` (`user_id`),
  CONSTRAINT `FK_5q4rc4fh1on6567qk69uesvyf` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FK_g1uebn6mqk9qiaw45vnacmyo2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_roles` */

insert  into `user_roles`(`user_id`,`role_id`) values (1,1),(2,2);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_password` varchar(255) DEFAULT NULL,
  `user_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_bcy4eb602iyorjjc2cklf53cf` (`user_username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`user_id`,`user_password`,`user_username`) values (1,'admin','admin'),(2,'user','user');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `username` varchar(255) NOT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`username`,`id`),
  KEY `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(`username`,`enable`,`password`,`id`) values ('admin',1,'admin',1),('carol',1,'carol',2),('igor',1,'igor',3),('katarina',1,'katarina',4);

/*Table structure for table `usuario_autorizacao` */

DROP TABLE IF EXISTS `usuario_autorizacao`;

CREATE TABLE `usuario_autorizacao` (
  `Usuario_username` varchar(255) NOT NULL,
  `autorizacoes_nome` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FKD04AA31379DDB250` (`autorizacoes_nome`),
  KEY `FKD04AA3139FEF07F0` (`Usuario_username`),
  KEY `UK_cymsxar9x2quhdvudex2mwfen` (`autorizacoes_nome`,`Usuario_username`),
  KEY `autorizacoes_nome` (`autorizacoes_nome`,`Usuario_username`),
  CONSTRAINT `FKD04AA31379DDB250` FOREIGN KEY (`autorizacoes_nome`) REFERENCES `autorizacao` (`nome`),
  CONSTRAINT `FKD04AA3139FEF07F0` FOREIGN KEY (`Usuario_username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `usuario_autorizacao` */

insert  into `usuario_autorizacao`(`Usuario_username`,`autorizacoes_nome`,`id`) values ('admin','ROLE_ADMIN',1),('igor','ROLE_ADMIN',3),('carol','ROLE_USER',2),('katarina','ROLE_USER',4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
