CREATE DATABASE  IF NOT EXISTS `movie_ticket_booking_directory`;
USE `movie_ticket_booking_directory`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `age` TINYINT UNSIGNED CHECK (`age` >= 0 AND `age` <= 120),
  `gender` ENUM('Male', 'Female') NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  `phone_no` CHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT chk_phone_no CHECK (`phone_no` REGEXP '^[0-9]{10}$'),
  CONSTRAINT chk_email CHECK (`email` REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
  CONSTRAINT chk_password CHECK (
      `password` REGEXP '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$'
  )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



SET SESSION sql_mode = 'STRICT_TRANS_TABLES';
--
-- Data for table `user`
--

INSERT INTO `user`
(`name`, `email`, `age`, `gender`, `password`,`enabled`, `phone_no`)
VALUES
('Saurav Kumar', 'saurav.kumar@example.com', 28, 'Male', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', 1,'9876543210'),
('Ananya Sharma', 'ananya.sharma@example.com', 25, 'Female', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', 1,'9123456789'),
('Rohit Mehta', 'rohit.mehta@example.com', 32, 'Male', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1, '9988776655');

--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`), 
  UNIQUE KEY `authorities_idx_1` (`username`,`role`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

-- INSERT INTO `roles` 
-- VALUES 
-- (1,'hero2@example.com','ROLE_CUSTOMER');

