-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.28-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para aev04
CREATE DATABASE IF NOT EXISTS `aev04` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `aev04`;

-- Volcando estructura para tabla aev04.dades
CREATE TABLE IF NOT EXISTS `dades` (
  `id` int(11) NOT NULL,
  `nomPersonatge` varchar(1000) NOT NULL DEFAULT '',
  `imatgeUrl` varchar(1000) NOT NULL DEFAULT '',
  `pelicules` varchar(1000) NOT NULL DEFAULT '',
  `series` varchar(1000) NOT NULL DEFAULT '',
  `videoJocs` varchar(1000) NOT NULL DEFAULT '',
  `webUrl` varchar(1000) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla aev04.dades: ~8 rows (aproximadamente)
INSERT INTO `dades` (`id`, `nomPersonatge`, `imatgeUrl`, `pelicules`, `series`, `videoJocs`, `webUrl`) VALUES
	(16, 'Abdullah', 'https://static.wikia.nocookie.net/disney/images/c/cb/1087603-44532-clp-950.jpg', 'Cheetah', 'Sense series', 'Sense video jocs', 'https://disney.fandom.com/wiki/Abdullah"'),
	(18, 'Abigail the Cow', 'https://static.wikia.nocookie.net/disney/images/0/05/Fox-disneyscreencaps_com-901.jpg', 'The Fox and the Hound,The Fox and the Hound 2', 'Sense series', 'Sense video jocs', 'https://disney.fandom.com/wiki/Abigail_the_Cow"'),
	(45, 'Admiral Boom and Mr. Binnacle', 'https://static.wikia.nocookie.net/disney/images/b/be/Marypoppins-disneyscreencaps_com-1086.jpg', 'Mary Poppins (film),Mary Poppins Returns', 'Sense series', 'Sense video jocs', 'https://disney.fandom.com/wiki/Admiral_Boom_and_Mr._Binnacle"'),
	(47, 'Adonis', 'https://static.wikia.nocookie.net/disney/images/c/ca/Adonis.jpg', 'Sense películes', 'Hercules (TV series)', 'Sense video jocs', 'https://disney.fandom.com/wiki/Adonis"'),
	(112, 'Achilles', 'https://static.wikia.nocookie.net/disney/images/d/d3/Vlcsnap-2015-05-06-23h04m15s601.png', 'Hercules (film)', 'Hercules (TV series)', 'Kingdom Hearts III', 'https://disney.fandom.com/wiki/Achilles_(Hercules)"'),
	(323, 'Artemis', 'https://static.wikia.nocookie.net/disney/images/8/80/ARTEMIS12.jpg', 'Hercules', 'Hercules (TV series)', 'Sense video jocs', 'https://disney.fandom.com/wiki/Artemis"'),
	(450, 'Baloo', 'https://static.wikia.nocookie.net/disney/images/3/31/Profile_-_Baloo.jpeg', 'The Jungle Book,The Jungle Book 2,Rudyard Kipling\'s The Jungle Book', 'The Mouse Factory,TaleSpin,Raw Toonage', 'TaleSpin (NES video game),TaleSpin (Sega Genesis Video Game),The Jungle Book (video game)', 'https://disney.fandom.com/wiki/Baloo"'),
	(3082, 'Hercules', 'https://static.wikia.nocookie.net/disney/images/7/70/Profile_-_Hercules.jpeg', 'Hercules (film),Hercules: Zero to Hero,Mickey\'s Magical Christmas: Snowed in at the House of Mouse', 'Hercules (TV series),House of Mouse,A Poem Is...', 'Hercules (video game),Kingdom Hearts (series),Disney Universe', 'https://disney.fandom.com/wiki/Hercules_(character)"');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
