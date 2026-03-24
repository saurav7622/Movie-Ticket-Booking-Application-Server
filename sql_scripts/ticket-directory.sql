CREATE DATABASE  IF NOT EXISTS `movie_ticket_booking_directory`;
USE `movie_ticket_booking_directory`;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `seat_id` INT NOT NULL,
  `price` INT NOT NULL,
  `duration` TINYINT NOT NULL,
  `start_time` TIME NOT NULL,
  `movie_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movie`(`id`),
  FOREIGN KEY (`seat_id`) REFERENCES `seat`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;




