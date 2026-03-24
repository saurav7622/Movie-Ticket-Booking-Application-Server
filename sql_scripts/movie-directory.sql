CREATE DATABASE  IF NOT EXISTS `movie_ticket_booking_directory`;
USE `movie_ticket_booking_directory`;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;

CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `rating` TINYINT DEFAULT NULL,
  `money_collection_INR` bigint DEFAULT NULL,
  `timings` VARCHAR(100),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `movie`
--

INSERT INTO `movie` VALUES 
	(1,'Bhool Bhulayiya','Horror',8,1200000000,"10:00, 13:00, 18:30"),
	(2,'Madagascar 6','Adventure',9,4000000000,"10:00, 13:00, 18:30");

