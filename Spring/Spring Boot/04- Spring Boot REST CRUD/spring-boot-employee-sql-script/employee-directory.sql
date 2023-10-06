CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `employee` VALUES 
	(1,'Leslie','Andrews','leslie@luv2code.com'),
	(2,'Emma','Baumgarten','emma@luv2code.com'),
	(3,'Avani','Gupta','avani@luv2code.com'),
	(4,'Yuri','Petrov','yuri@luv2code.com'),
	(5,'Juan','Vega','juan@luv2code.com');
    
    INSERT INTO employee (id, first_name, last_name, email)
VALUES
(6, 'Charlie', 'Lee', 'charlie.lee@example.com'),
(7, 'Frank', 'Davis', 'frank.davis@example.com'),
(8, 'Ivy', 'Hall', 'ivy.hall@example.com'),
(9, 'David', 'Clark', 'david.clark@example.com'),
(10, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(11, 'Grace', 'Smith', 'grace.smith@example.com'),
(12, 'David', 'Taylor', 'david.taylor@example.com'),
(13, 'Frank', 'Wang', 'frank.wang@example.com'),
(14, 'Alice', 'Hall', 'alice.hall@example.com'),
(15, 'Ivy', 'Johnson', 'ivy.johnson@example.com'),
(16, 'Jack', 'Clark', 'jack.clark@example.com'),
(17, 'David', 'Lee', 'david.lee@example.com'),
(18, 'Frank', 'Hall', 'frank.hall@example.com'),
(19, 'Alice', 'Smith', 'alice.smith@example.com'),
(20, 'Emma', 'Clark', 'emma.clark@example.com'),
(21, 'Ivy', 'Taylor', 'ivy.taylor@example.com'),
(22, 'David', 'Lee', 'david.lee@example.com'),
(23, 'Frank', 'Clark', 'frank.clark@example.com'),
(24, 'Alice', 'Smith', 'alice.smith@example.com'),
(25, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(26, 'David', 'Hall', 'david.hall@example.com'),
(27, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(28, 'Alice', 'Clark', 'alice.clark@example.com'),
(29, 'Ivy', 'Smith', 'ivy.smith@example.com'),
(30, 'David', 'Lee', 'david.lee@example.com'),
(31, 'Frank', 'Clark', 'frank.clark@example.com'),
(32, 'Alice', 'Smith', 'alice.smith@example.com'),
(33, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(34, 'David', 'Hall', 'david.hall@example.com'),
(35, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(36, 'Alice', 'Clark', 'alice.clark@example.com'),
(37, 'Ivy', 'Smith', 'ivy.smith@example.com'),
(38, 'David', 'Lee', 'david.lee@example.com'),
(39, 'Frank', 'Clark', 'frank.clark@example.com'),
(40, 'Alice', 'Smith', 'alice.smith@example.com'),
(41, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(42, 'David', 'Hall', 'david.hall@example.com'),
(43, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(44, 'Alice', 'Clark', 'alice.clark@example.com'),
(45, 'Ivy', 'Smith', 'ivy.smith@example.com'),
(46, 'David', 'Lee', 'david.lee@example.com'),
(47, 'Frank', 'Clark', 'frank.clark@example.com'),
(48, 'Alice', 'Smith', 'alice.smith@example.com'),
(49, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(50, 'David', 'Hall', 'david.hall@example.com'),
(51, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(52, 'Alice', 'Clark', 'alice.clark@example.com'),
(53, 'Ivy', 'Smith', 'ivy.smith@example.com'),
(54, 'David', 'Lee', 'david.lee@example.com'),
(55, 'Frank', 'Clark', 'frank.clark@example.com'),
(56, 'Alice', 'Smith', 'alice.smith@example.com'),
(57, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(58, 'David', 'Hall', 'david.hall@example.com'),
(59, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(60, 'Alice', 'Clark', 'alice.clark@example.com'),
(61, 'Ivy', 'Smith', 'ivy.smith@example.com'),
(62, 'David', 'Lee', 'david.lee@example.com'),
(63, 'Frank', 'Clark', 'frank.clark@example.com'),
(64, 'Alice', 'Smith', 'alice.smith@example.com'),
(65, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(66, 'David', 'Hall', 'david.hall@example.com'),
(67, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(68, 'Alice', 'Clark', 'alice.clark@example.com'),
(69, 'Ivy', 'Smith', 'ivy.smith@example.com'),
(70, 'David', 'Lee', 'david.lee@example.com'),
(71, 'Frank', 'Clark', 'frank.clark@example.com'),
(72, 'Alice', 'Smith', 'alice.smith@example.com'),
(73, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(74, 'David', 'Hall', 'david.hall@example.com'),
(75, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(76, 'Alice', 'Clark', 'alice.clark@example.com'),
(77, 'Ivy', 'Smith', 'ivy.smith@example.com'),
(78, 'David', 'Lee', 'david.lee@example.com'),
(79, 'Frank', 'Clark', 'frank.clark@example.com'),
(80, 'Alice', 'Smith', 'alice.smith@example.com'),
(81, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(82, 'David', 'Hall', 'david.hall@example.com'),
(83, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(84, 'Alice', 'Clark', 'alice.clark@example.com'),
(85, 'Ivy', 'Smith', 'ivy.smith@example.com'),
(86, 'David', 'Lee', 'david.lee@example.com'),
(87, 'Frank', 'Clark', 'frank.clark@example.com'),
(88, 'Alice', 'Smith', 'alice.smith@example.com'),
(89, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(90, 'David', 'Hall', 'david.hall@example.com'),
(91, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(92, 'Alice', 'Clark', 'alice.clark@example.com'),
(93, 'Ivy', 'Smith', 'ivy.smith@example.com'),
(94, 'David', 'Lee', 'david.lee@example.com'),
(95, 'Frank', 'Clark', 'frank.clark@example.com'),
(96, 'Alice', 'Smith', 'alice.smith@example.com'),
(97, 'Ivy', 'Lee', 'ivy.lee@example.com'),
(98, 'David', 'Hall', 'david.hall@example.com'),
(99, 'Frank', 'Taylor', 'frank.taylor@example.com'),
(100, 'Alice', 'Clark', 'alice.clark@example.com');


desc employee;

select * from employee;