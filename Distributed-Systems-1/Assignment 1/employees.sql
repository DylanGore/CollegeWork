-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 10.0.90.200:3306
-- Generation Time: Oct 25, 2020 at 03:24 PM
-- Server version: 10.4.15-MariaDB-1:10.4.15+maria~bionic-log
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `firstName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `lastName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ssn` int(9) NOT NULL,
  `salary` int(11) DEFAULT 0,
  `gender` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`firstName`, `lastName`, `ssn`, `salary`, `gender`) VALUES
('Bender', 'Rodríguez', 2716057, 42000, 'Other'),
('Hubert', 'Farnsworth', 4092841, 60000, 'Male'),
('John', 'Zoidberg', 5052914, 15000, 'Male'),
('Hermes', 'Conrad', 7152959, 52000, 'Male'),
('Turanga', 'Leela', 7292975, 45000, 'Female'),
('Philip', 'Fry', 8141974, 31000, 'Male'),
('Scruffy', 'Scruffington', 10312945, 20000, 'Male'),
('Amy', 'Wong', 12032978, 45000, 'Female');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`ssn`),
  ADD UNIQUE KEY `ssn` (`ssn`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
