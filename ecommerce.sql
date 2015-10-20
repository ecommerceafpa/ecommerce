-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 20 Octobre 2015 à 10:28
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `ecommerce`
--

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_procedure`(OUT nb INT)
BEGIN
	SELECT COUNT(*) INTO nb FROM event;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `addressee`
--

CREATE TABLE IF NOT EXISTS `addressee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `firstname` varchar(60) NOT NULL,
  `lastname` varchar(60) NOT NULL,
  `address` varchar(120) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_addressee_customer1_idx` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `author`
--

CREATE TABLE IF NOT EXISTS `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(60) NOT NULL,
  `lastname` varchar(60) NOT NULL,
  `portrait` text NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `author`
--

INSERT INTO `author` (`id`, `firstname`, `lastname`, `portrait`, `created`, `updated`, `deleted`) VALUES
(5, 'DRAILLARD', 'Francis', 'Ingénieur EFREI, Francis Draillard a exercé dans l''industrie, la recherche, la formation continue et l''enseignement supérieur. Il intervient comme professeur associé à  l''EIGSI, école d''ingénieurs à  La Rochelle. Concepteur web indépendant et conseil en entreprise, il contribue au site Framasoft dédié aux logiciels libres et à  l''évolution de PluXml, système de gestion de contenu libre pour créer des sites modifiables en ligne.', '2015-10-17 12:34:33', '2015-10-18 13:03:56', 0),
(7, 'GRANET', 'Vincent', 'Vincent Granet est Metre de conférences à  l''université de Nice-Sophia Antipolis et à  l''ESINSA.', '2015-10-17 12:39:11', '2015-10-17 18:03:46', 0),
(8, 'REGOURD', 'Jean-Pierre', 'Jean-Pierre Regourd est docteur en informatique de l''universite Pierre et Marie Curie (Paris-VI). Ancien ingenieur de recherche aux Laboratoires de Marcoussis d''Alcatel-Alsthom, il est actuellement maitre de conferences a l''universite de Nice-Sophia Antipolis.', '2015-10-17 12:42:44', '2015-10-17 18:00:00', 0),
(9, 'PAÏOLA', 'Philippe', 'Philippe Païola est Ingénieur Système et Sécurité. Responsable Sécurité Opérationnel dans une grande entreprise, il forme également des professionnels sur les technologies Microsoft (Windows Server, Windows 7 et 8...) et enseigne en IUT et école d''Ingénieurs. Il possède plus d''une dizaine de certifications Microsoft et a acquis le statut Microsoft Certified Trainer. Ainsi, ses compétences techniques s''allient à son expérience pédagogique pour fournir aux lecteurs des livres opérationnels sur l''administration des postes de travail Windows 7 dans un environnement d''entreprise.', '2015-10-18 13:04:48', '2015-10-18 13:06:30', 0),
(10, 'VALETTE', 'Kevin', 'Analyste programmeur pendant plusieurs années, Kevin Valette est aujourd''hui analyste et expert sur les technologies Java. En lien étroit avec la MOA il propose des solutions de développement innovantes et créatives particulièrement sur l''implémentation d''IHM sur des applications web JEE. À travers les pages de ce livre il partage toute son expérience pour fournir aux lecteurs un ouvrage réellement opérationnel pour tirer le meilleur parti possible des dernières évolutions de la plateforme JEE.', '2015-10-18 22:49:19', '2015-10-18 22:49:19', 0);

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `editor_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `tax_id` int(11) NOT NULL,
  `isbn` bigint(19) unsigned NOT NULL,
  `image` blob,
  `title` varchar(255) NOT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `summary` text NOT NULL,
  `nb_page` int(11) NOT NULL,
  `release_date` date NOT NULL,
  `edition` int(11) NOT NULL DEFAULT '1',
  `price` decimal(6,2) NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ISBN_UNIQUE` (`isbn`),
  KEY `fk_book_editor1_idx` (`editor_id`),
  KEY `fk_book_language1_idx` (`language_id`),
  KEY `fk_book_tax1_idx` (`tax_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `book`
--

INSERT INTO `book` (`id`, `editor_id`, `language_id`, `tax_id`, `isbn`, `image`, `title`, `subtitle`, `summary`, `nb_page`, `release_date`, `edition`, `price`, `created`, `updated`, `deleted`) VALUES
(1, 1, 1, 1, 9782746096486, NULL, 'Java et jQuery', 'Intégrer un framework JavaScript dans l''écosystème JEE', 'Ce livre s''adresse aux développeurs Java souhaitant dynamiser leurs applications web. Tout au long de ces pages l''auteur explique de façon pragmatique comment ajouter de la fluidité et du dynamisme au sein des pages mises à disposition des utilisateurs et comment les solutions clientes Javascript permettent d''apporter de la logique de présentation sur les écrans. C''est dans ce cadre que la bibliothèque jQuery intervient. Avec un concept simple "write less, do more", la bibliothèque propose des fonctionnalités performantes et concises afin d''améliorer les vues de l''application tout en uniformisant les différents comportements des navigateurs.\r\nAprès les premiers chapitres du livre expliquant les différents concepts à maîtriser pour créer une solution JEE performante, ce livre donne les clés pour maîtriser les différents aspects de la solution jQuery et son intégration au sein de l''application. Au travers d''exemples, l''auteur détaille de nombreuses fonctionnalités concrètes provenant de problématiques du terrain. L''intégration du comportement dynamique au sein des JSP permettra de mettre en relief les possibilités offertes au travers de la bibliothèque cliente JavaScript. Les techniques AJAX sont présentées avec leurs problématiques : création d''un service REST se reposant sur les normes JEE7 et sa consommation par la vue au travers des mécanismes de jQuery. Ces nouvelles solutions proposent des outils performants (Brackets.io, Atom.io,...) permettant de construire, d''analyser et de consolider des solutions clientes. Cet outillage sera décrit dans un chapitre avec les différents points forts de chacune des briques.\r\nPour aller encore plus loin, le livre propose un chapitre sur les possibilités des composants jQueryUI afin d''apporter des éléments réutilisables sur les applications.', 400, '2015-10-10', 1, '36.00', '2015-10-18 23:15:03', '2015-10-19 00:02:06', 0),
(2, 1, 1, 1, 9782746055674, NULL, 'Les EJB 3 (avec Struts 2, JSF 2, JasperReports 3, Flex 3)', 'Développez pour le web par l''exemple : 3 applications détaillées', 'Ce livre sur les EJB 3 s''adresse aux développeurs Java d''applications web travaillant sur les frameworks Struts 2, JSF 2 ou Flex 3. Le débutant comme l''expert trouveront les informations qui leur conviennent sur l''utilisation des EJB (Enterprise JavaBeans) de manière générale et les gains de productivité apportés par la version 3.\r\nL''auteur propose le développement avec les EJB de trois applications web de vente en ligne aux fonctionnalités quasi identiques et qui sont basées sur des couches métier et persistance communes. A l''aide de l''IDE Eclipse et du serveur d''application JBoss 6, il exploite les fonctionnalités d''un container EJB pour :\r\nmettre en place une couche de persistance basée sur les Entity beans, le langage JPQL et la Java Persistence API,\r\ncréer des objets métiers à l''aide des Session beans et des Message-driven beans,\r\ndéfinir une politique de sécurité avec une gestion des rôles et des permissions définie dans un fichier de propriétés, une base ou un annuaire LDAP,\r\nexposer des EJB 3 en tant que web services,\r\nmettre en place des traitements différés et ponctuels à l''aide des EJB Timers,\r\nfaire de la programmation par aspect grâce aux Interceptors.\r\nTout au long des chapitres, l''auteur :\r\ndécrit et met en place les nouveautés incluses dans les dernières versions des frameworks Struts 2 et JSF 2.\r\ndétaille l''utilisation du framework GraniteDS pour réaliser la communication entre les objets Java et Flex 3 et créer une interface RIA.\r\nmet en avant le framework open-source de reporting JasperReports et montre son utilisation avec les EJB, Struts 2 et JSF 2 pour créer des rapports graphiques.\r\nEnfin, l''auteur décrit les apports de la toute dernière version des EJB, la version 3.1, qui a été finalisée en décembre 2009.\r\nLes sources des applications sont en téléchargement sur le site www.editions-eni.fr et l''auteur continuera de les faire évoluer sur son site.', 370, '2015-07-15', 1, '39.00', '2015-10-19 00:01:21', '2015-10-19 00:01:21', 0),
(3, 2, 1, 1, 9782212114843, NULL, 'J2EE 1.4', '', 'Mieux maîtriser les technologies J2EE...\r\nAvec la version 1.4, J2EE a atteint un niveau de maturité entraînant un développement considérable du nombre des applications développées et mises en production. Pourtant, de nombreux développeurs ont encore une connaissance limitée de cet environnement. Pour y remédier, cet ouvrage complet traite de façon didactique de tous les aspects de la programmation côté serveur avec le J2EE SDK.\r\npour répondre au mieux aux problématiques des entreprises\r\nLes applications d''entreprise deviennent plus complexes à mesure que les problématiques des entreprises et les échanges se multiplient et se trouvent plus étroitement imbriqués : stockage, traitements complexes, synchronisation, interfaces multiples... Cet ouvrage est conçu pour donner aux développeurs les connaissances en Java et la maîtrise nécessaire pour créer des composants J2EE réutilisables - JavaServer Pages, Entreprise JavaBeans, services Web - en mesure d''apporter des solutions fiables aux besoins des entreprises.\r\nÀ qui s''adresse l''ouvrage ?\r\nAux développeurs Java intermédiaires et confirmés\r\nAux développeurs C++ et C# souhaitant développer des applications en Java\r\nAux développeurs d''applications d''entreprise, de services Web, d''intranets, de sites de commerce électronique\r\nCode source téléchargeable sur www.editions-eyrolles.com.', 662, '2004-09-02', 1, '42.50', '2015-10-19 11:06:11', '2015-10-19 11:07:28', 0);

-- --------------------------------------------------------

--
-- Structure de la table `book_author`
--

CREATE TABLE IF NOT EXISTS `book_author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_author_author1_idx` (`author_id`),
  KEY `fk_book_author_book_idx` (`book_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Contenu de la table `book_author`
--

INSERT INTO `book_author` (`id`, `book_id`, `author_id`) VALUES
(4, 2, 9),
(5, 2, 10),
(6, 1, 10),
(13, 3, 8);

-- --------------------------------------------------------

--
-- Structure de la table `book_category`
--

CREATE TABLE IF NOT EXISTS `book_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_category_category1_idx` (`category_id`),
  KEY `fk_book_category_book1_idx` (`book_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `book_category`
--

INSERT INTO `book_category` (`id`, `book_id`, `category_id`) VALUES
(1, 2, 3),
(2, 1, 1),
(9, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `book_event`
--

CREATE TABLE IF NOT EXISTS `book_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_event_event1_idx` (`event_id`),
  KEY `fk_book_event_book1_idx` (`book_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=27 ;

--
-- Contenu de la table `book_event`
--

INSERT INTO `book_event` (`id`, `book_id`, `event_id`) VALUES
(20, 1, 2),
(21, 2, 2),
(26, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(180) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `addressee_id` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_cart_customer1_idx` (`customer_id`),
  KEY `fk_cart_addressee1_idx` (`addressee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `cart_book`
--

CREATE TABLE IF NOT EXISTS `cart_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `date_add` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_cart_book_book1_idx` (`book_id`),
  KEY `fk_cart_book_cart1_idx` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_category_id` int(11) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_category_category1_idx` (`parent_category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Contenu de la table `category`
--

INSERT INTO `category` (`id`, `parent_category_id`, `name`, `created`, `updated`, `deleted`) VALUES
(1, NULL, 'Informatique', '2015-10-16 20:45:55', '2015-10-18 10:20:11', 0),
(2, 16, 'Java', '2015-10-16 20:45:55', '2015-10-18 10:21:41', 0),
(3, 17, 'Graphisme', '2015-10-16 20:45:55', '2015-10-18 10:24:29', 0),
(14, 17, 'Photo', '2015-10-16 20:45:55', '2015-10-18 10:25:58', 1),
(15, 1, 'Développement d''applications', '2015-10-18 10:20:49', '2015-10-18 10:20:49', 0),
(16, 15, 'Langages', '2015-10-18 10:21:30', '2015-10-18 10:21:30', 0),
(17, NULL, 'Audiovisuel', '2015-10-18 10:24:17', '2015-10-18 10:24:17', 0);

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `title` varchar(60) NOT NULL,
  `text` varchar(45) NOT NULL,
  `rate` tinyint(4) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_comment_customer1_idx` (`customer_id`),
  KEY `fk_comment_book1_idx` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(60) NOT NULL,
  `lastname` varchar(60) NOT NULL,
  `username` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `disabled` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `customer`
--

INSERT INTO `customer` (`id`, `firstname`, `lastname`, `username`, `password`, `created`, `updated`, `deleted`, `disabled`) VALUES
(1, 'toot3', 'titi3', 'tic', 'pm', '2015-10-16 20:45:55', '2015-10-16 20:45:55', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `editor`
--

CREATE TABLE IF NOT EXISTS `editor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `editor`
--

INSERT INTO `editor` (`id`, `name`, `created`, `updated`, `deleted`) VALUES
(1, 'ENI', '2015-10-16 20:45:55', '2015-10-18 11:04:10', 0),
(2, 'Eyrolles', '2015-10-18 11:05:56', '2015-10-18 11:05:56', 0),
(3, 'Dunod', '2015-10-18 11:06:44', '2015-10-18 11:06:44', 0);

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `event`
--

INSERT INTO `event` (`id`, `name`, `start_date`, `end_date`, `created`, `updated`, `deleted`) VALUES
(1, 'RENTRÉE LITTÉRAIRE 2014', '2014-09-01', '2014-09-30', '2015-10-18 12:34:59', '2015-10-18 12:38:45', 1),
(2, 'LA SÉLECTION DU JURY PRIX DU ROMAN 2015', '2015-09-01', '2015-12-31', '2015-10-18 12:37:44', '2015-10-18 12:37:44', 0),
(3, 'RENTRÉE LITTÉRAIRE 2015', '2015-09-01', '2015-10-30', '2015-10-18 12:38:34', '2015-10-19 17:36:21', 0);

-- --------------------------------------------------------

--
-- Structure de la table `language`
--

CREATE TABLE IF NOT EXISTS `language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `language`
--

INSERT INTO `language` (`id`, `name`, `created`, `updated`, `deleted`) VALUES
(1, 'Français', '2015-10-18 22:50:27', '2015-10-18 22:50:27', 0),
(2, 'Anglais', '2015-10-18 22:50:27', '2015-10-18 22:50:27', 0);

-- --------------------------------------------------------

--
-- Structure de la table `tax`
--

CREATE TABLE IF NOT EXISTS `tax` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `value` decimal(4,2) NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `tax`
--

INSERT INTO `tax` (`id`, `name`, `value`, `created`, `updated`, `deleted`) VALUES
(1, 'TVA 2014', '20.00', '2015-10-16 20:45:55', '2015-10-18 11:28:49', 0),
(2, 'TVA', '19.60', '2015-10-18 11:29:02', '2015-10-18 11:29:02', 0),
(3, 'TVA restauration', '5.50', '2015-10-18 11:29:27', '2015-10-18 11:29:31', 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `addressee`
--
ALTER TABLE `addressee`
  ADD CONSTRAINT `fk_addressee_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `fk_book_editor1` FOREIGN KEY (`editor_id`) REFERENCES `editor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_book_language1` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_book_tax1` FOREIGN KEY (`tax_id`) REFERENCES `tax` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `book_author`
--
ALTER TABLE `book_author`
  ADD CONSTRAINT `fk_book_author_author1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_book_author_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `book_category`
--
ALTER TABLE `book_category`
  ADD CONSTRAINT `fk_book_category_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_book_category_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `book_event`
--
ALTER TABLE `book_event`
  ADD CONSTRAINT `fk_book_Event_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_book_event_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart_addressee1` FOREIGN KEY (`addressee_id`) REFERENCES `addressee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cart_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `cart_book`
--
ALTER TABLE `cart_book`
  ADD CONSTRAINT `fk_cart_book_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cart_book_cart1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `fk_category_category1` FOREIGN KEY (`parent_category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_comment_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_comment_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
