CREATE DATABASE  IF NOT EXISTS `movie_ticket_booking_directory`;
USE `movie_ticket_booking_directory`;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;

CREATE TABLE `seat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `seat_no` VARCHAR(5) NOT NULL,
  `type` ENUM('CLASSIC', 'PRIME') NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT chk_seat_no CHECK (`seat_no` REGEXP '^[A-Z]-[0-9]{3}$'),
  UNIQUE (`seat_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Data for table `seat`
--

INSERT INTO `seat` (`seat_no`, `type`) VALUES
('A-001', 'CLASSIC'),
('A-002', 'PRIME'),
('B-010', 'CLASSIC'),
('C-015', 'PRIME'),
('Z-020', 'CLASSIC');


