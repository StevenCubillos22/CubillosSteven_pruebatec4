-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 19, 2025 at 03:45 AM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nagencia`
--

-- --------------------------------------------------------

--
-- Table structure for table `hoteles`
--

DROP TABLE IF EXISTS `hoteles`;
CREATE TABLE IF NOT EXISTS `hoteles` (
  `id` int NOT NULL,
  `esta_reservado` bit(1) NOT NULL,
  `fecha_desde` date DEFAULT NULL,
  `fecha_hasta` date DEFAULT NULL,
  `lugar` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio_habitacion` double DEFAULT NULL,
  `tipo_habitacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hoteles`
--

INSERT INTO `hoteles` (`id`, `esta_reservado`, `fecha_desde`, `fecha_hasta`, `lugar`, `nombre`, `precio_habitacion`, `tipo_habitacion`) VALUES
(1, b'0', '2025-02-18', '2025-02-25', 'Madrid', 'Hotel Ejemplo', 100, 'Doble'),
(2, b'0', '2022-04-20', '2022-04-25', 'Valencia', 'Hotel NH', 110.5, 'Doble'),
(102, b'0', '2011-04-18', '2011-04-26', 'Cordoba', 'El romeral', 117.5, 'Doble');

-- --------------------------------------------------------

--
-- Table structure for table `hoteles_seq`
--

DROP TABLE IF EXISTS `hoteles_seq`;
CREATE TABLE IF NOT EXISTS `hoteles_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hoteles_seq`
--

INSERT INTO `hoteles_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
CREATE TABLE IF NOT EXISTS `personas` (
  `id` int NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `personas`
--

INSERT INTO `personas` (`id`, `apellido`, `nombre`) VALUES
(1, 'Remolina', 'Alvaro'),
(3, 'string', 'string'),
(5, 'Banderas', 'Sergio'),
(52, 'string', 'patata'),
(152, 'string', 'zaca'),
(202, 'Arroyo', 'Pablo'),
(252, 'Arroyo', 'Pablo');

-- --------------------------------------------------------

--
-- Table structure for table `personas_seq`
--

DROP TABLE IF EXISTS `personas_seq`;
CREATE TABLE IF NOT EXISTS `personas_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `personas_seq`
--

INSERT INTO `personas_seq` (`next_val`) VALUES
(351);

-- --------------------------------------------------------

--
-- Table structure for table `reservas_hoteles`
--

DROP TABLE IF EXISTS `reservas_hoteles`;
CREATE TABLE IF NOT EXISTS `reservas_hoteles` (
  `id` int NOT NULL,
  `cant_personas` int DEFAULT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `noches` int DEFAULT NULL,
  `tipo_habitacion` varchar(255) DEFAULT NULL,
  `hotel_id` int DEFAULT NULL,
  `persona_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdhflhkg0csc30su3vbsilsgny` (`hotel_id`),
  KEY `FK37m9uh9g102dcqkcnfl1uu3yi` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reservas_hoteles`
--

INSERT INTO `reservas_hoteles` (`id`, `cant_personas`, `check_in`, `check_out`, `ciudad`, `noches`, `tipo_habitacion`, `hotel_id`, `persona_id`) VALUES
(52, 2, '2025-02-20', '2025-02-25', 'Madrid', 5, 'Doble', 1, 1),
(53, 2, '2025-02-20', '2025-02-25', 'Madrid', 5, 'Doble', 1, 1),
(54, 2, '2027-02-20', '2027-02-25', 'Fuengirola', 5, 'Doble', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `reservas_hoteles_seq`
--

DROP TABLE IF EXISTS `reservas_hoteles_seq`;
CREATE TABLE IF NOT EXISTS `reservas_hoteles_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reservas_hoteles_seq`
--

INSERT INTO `reservas_hoteles_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Table structure for table `reservas_vuelos`
--

DROP TABLE IF EXISTS `reservas_vuelos`;
CREATE TABLE IF NOT EXISTS `reservas_vuelos` (
  `id` int NOT NULL,
  `cantidad_personas` int DEFAULT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `fecha_reserva` date DEFAULT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `tipo_asiento` varchar(255) DEFAULT NULL,
  `persona_id` int DEFAULT NULL,
  `vuelo_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlmujgr6hb1gqeuwkq4mlvr0j9` (`persona_id`),
  KEY `FK5va9qkbp50c49fvtvarp42y1n` (`vuelo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reservas_vuelos`
--

INSERT INTO `reservas_vuelos` (`id`, `cantidad_personas`, `destino`, `fecha_reserva`, `origen`, `tipo_asiento`, `persona_id`, `vuelo_id`) VALUES
(2, 2, 'París', '2025-02-20', 'Madrid', 'Económico', 5, 2),
(52, 0, 'string', '2025-02-18', 'string', 'string', 5, 2),
(102, 0, 'string', '2025-02-18', 'string', 'string', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `reservas_vuelos_seq`
--

DROP TABLE IF EXISTS `reservas_vuelos_seq`;
CREATE TABLE IF NOT EXISTS `reservas_vuelos_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reservas_vuelos_seq`
--

INSERT INTO `reservas_vuelos_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Table structure for table `vuelos`
--

DROP TABLE IF EXISTS `vuelos`;
CREATE TABLE IF NOT EXISTS `vuelos` (
  `id` int NOT NULL,
  `aerolinea` varchar(255) DEFAULT NULL,
  `asientos` int NOT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `fecha_vuelta` date DEFAULT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `precio_por_persona` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `vuelos`
--

INSERT INTO `vuelos` (`id`, `aerolinea`, `asientos`, `destino`, `fecha_salida`, `fecha_vuelta`, `origen`, `precio_por_persona`) VALUES
(1, 'Iberia', 100, 'París', '2025-06-10', '2025-06-20', 'Madrid', 250.5),
(2, 'AEuropaNueva', 98, 'Oviedo', '2024-03-10', '2024-06-20', 'Barcelona', 250.5),
(52, 'Aerolínea Ejemplo', 150, 'Madrid', '2025-02-18', '2025-02-25', 'Buenos Aires', 500),
(102, 'Azetaio', 87, 'Caracas', '2024-01-10', '2024-01-25', 'Santa Cruz', 250.5);

-- --------------------------------------------------------

--
-- Table structure for table `vuelos_seq`
--

DROP TABLE IF EXISTS `vuelos_seq`;
CREATE TABLE IF NOT EXISTS `vuelos_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `vuelos_seq`
--

INSERT INTO `vuelos_seq` (`next_val`) VALUES
(201);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservas_hoteles`
--
ALTER TABLE `reservas_hoteles`
  ADD CONSTRAINT `FK37m9uh9g102dcqkcnfl1uu3yi` FOREIGN KEY (`persona_id`) REFERENCES `personas` (`id`),
  ADD CONSTRAINT `FKdhflhkg0csc30su3vbsilsgny` FOREIGN KEY (`hotel_id`) REFERENCES `hoteles` (`id`);

--
-- Constraints for table `reservas_vuelos`
--
ALTER TABLE `reservas_vuelos`
  ADD CONSTRAINT `FK5va9qkbp50c49fvtvarp42y1n` FOREIGN KEY (`vuelo_id`) REFERENCES `vuelos` (`id`),
  ADD CONSTRAINT `FKlmujgr6hb1gqeuwkq4mlvr0j9` FOREIGN KEY (`persona_id`) REFERENCES `personas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
