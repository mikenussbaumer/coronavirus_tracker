-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Erstellungszeit: 15. Jun 2020 um 11:35
-- Server-Version: 8.0.20
-- PHP-Version: 7.3.15-3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `h31u271_coronatracker`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `countries`
--

CREATE TABLE `countries` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `countries`
--

INSERT INTO `countries` (`id`, `name`, `latitude`, `longitude`) VALUES
(1, 'Benin', 9.3077, 2.3158),
(2, 'Papua New Guinea', -6.315, 143.9555),
(3, 'Angola', -11.2027, 17.8739),
(4, 'Cambodia', 11.55, 104.9167),
(5, 'Sudan', 12.8628, 30.2176),
(6, 'Kazakhstan', 48.0196, 66.9237),
(7, 'Paraguay', -23.4425, -58.4438),
(8, 'Portugal', 39.3999, -8.2245),
(9, 'Syria', 34.802075, 38.99681500000001),
(10, 'Bahamas', 25.0343, -77.3963),
(11, 'Grenada', 12.1165, -61.679),
(12, 'Greece', 39.0742, 21.8243),
(13, 'Latvia', 56.8796, 24.6032),
(14, 'Mongolia', 46.8625, 103.8467),
(15, 'Iran', 32, 53),
(16, 'Morocco', 31.7917, -7.0926),
(17, 'Mali', 17.570692, -3.996166000000001),
(18, 'Panama', 8.538, -80.7821),
(19, 'Guatemala', 15.7835, -90.2308),
(20, 'Guyana', 5, -58.75),
(21, 'Iraq', 33, 44),
(22, 'Chile', -35.6751, -71.543),
(23, 'Laos', 19.85627, 102.495496),
(24, 'Nepal', 28.1667, 84.25),
(25, 'Argentina', -38.4161, -63.6167),
(26, 'Seychelles', -4.6796, 55.492),
(27, 'Tanzania', -6.369, 34.8888),
(28, 'Ukraine', 48.3794, 31.1656),
(29, 'Ghana', 7.9465, -1.0232),
(30, 'Zambia', -15.4167, 28.2833),
(31, 'Belize', 13.1939, -59.5432),
(32, 'Congo (Brazzaville)', -4.0383, 21.7587),
(33, 'Holy See', 41.9029, 12.4534),
(34, 'Bahrain', 26.0275, 50.55),
(35, 'India', 21, 78),
(36, 'Canada', 64.2823, -135),
(37, 'Maldives', 3.2028, 73.2207),
(38, 'Guinea-Bissau', 11.8037, -15.1804),
(39, 'Turkey', 38.9637, 35.2433),
(40, 'Belgium', 50.8333, 4),
(41, 'Namibia', -22.9576, 18.4904),
(42, 'Finland', 64, 26),
(43, 'Comoros', -11.6455, 43.3333),
(44, 'North Macedonia', 41.6086, 21.7453),
(45, 'South Africa', -30.5595, 22.9375),
(46, 'Trinidad and Tobago', 10.6918, -61.2225),
(47, 'Central African Republic', 6.6111, 20.9394),
(48, 'Georgia', 42.3154, 43.3569),
(49, 'Jamaica', 18.1096, -77.2975),
(50, 'Peru', -9.19, -75.0152),
(51, 'Saint Kitts and Nevis', 17.357822, -62.782998),
(52, 'Germany', 51, 9),
(53, 'Yemen', 15.552727, 48.516388),
(54, 'Eritrea', 15.1794, 39.7823),
(55, 'Fiji', -17.7134, 178.065),
(56, 'Guinea', 9.9456, -9.6966),
(57, 'Chad', 15.4542, 18.7322),
(58, 'Somalia', 5.1521, 46.1996),
(59, 'Sao Tome and Principe', 0.18636, 6.613081),
(60, 'Madagascar', -18.7669, 46.8691),
(61, 'Thailand', 15, 101),
(62, 'Equatorial Guinea', 1.5, 10),
(63, 'Libya', 26.3351, 17.228331),
(64, 'Costa Rica', 9.7489, -83.7534),
(65, 'Sweden', 63, 16),
(66, 'Taiwan*', 23.7, 121),
(67, 'Vietnam', 16, 108),
(68, 'West Bank and Gaza', 31.9522, 35.2332),
(69, 'Malawi', -13.254307999999998, 34.301525),
(70, 'Andorra', 42.5063, 1.5218),
(71, 'Liechtenstein', 47.14, 9.55),
(72, 'Poland', 51.9194, 19.1451),
(73, 'Bulgaria', 42.7339, 25.4858),
(74, 'Jordan', 31.24, 36.51),
(75, 'Kuwait', 29.5, 47.75),
(76, 'Nigeria', 9.082, 8.6753),
(77, 'Tunisia', 34, 9),
(78, 'Croatia', 45.1, 15.2),
(79, 'Sri Lanka', 7, 81),
(80, 'Uruguay', -32.5228, -55.7658),
(81, 'Timor-Leste', -8.874217, 125.727539),
(82, 'United Kingdom', -51.7963, -59.5236),
(83, 'United Arab Emirates', 24, 54),
(84, 'Kenya', -0.0236, 37.9062),
(85, 'Switzerland', 46.8182, 8.2275),
(86, 'Spain', 40, -4),
(87, 'Brunei', 4.5353, 114.7277),
(88, 'Djibouti', 11.8251, 42.5903),
(89, 'Lebanon', 33.8547, 35.8623),
(90, 'Azerbaijan', 40.1431, 47.5769),
(91, 'Cuba', 22, -80),
(92, 'Liberia', 6.4281, -9.4295),
(93, 'Venezuela', 6.4238, -66.5897),
(94, 'Burkina Faso', 12.2383, -1.5616),
(95, 'Mauritania', 21.0079, 10.9408),
(96, 'Saint Lucia', 13.9094, -60.9789),
(97, 'Israel', 31, 35),
(98, 'San Marino', 43.9424, 12.4578),
(99, 'Australia', -31.9505, 115.8605),
(100, 'Tajikistan', 38.861034, 71.276093),
(101, 'Estonia', 58.5953, 25.0136),
(102, 'Cameroon', 3.848, 11.5021),
(103, 'Cyprus', 35.1264, 33.4299),
(104, 'Malaysia', 2.5, 112.5),
(105, 'Iceland', 64.9631, -19.0208),
(106, 'Oman', 21, 57),
(107, 'MS Zaandam', 0, 0),
(108, 'Armenia', 40.0691, 45.0382),
(109, 'Gabon', -0.8037, 11.6094),
(110, 'Austria', 47.5162, 14.5501),
(111, 'Mozambique', -18.665695, 35.529562),
(112, 'El Salvador', 13.7942, -88.8965),
(113, 'Monaco', 43.7333, 7.4167),
(114, 'Luxembourg', 49.8153, 6.1296),
(115, 'Brazil', -14.235, -51.9253),
(116, 'Algeria', 28.0339, 1.6596),
(117, 'Cabo Verde', 16.5388, -23.0418),
(118, 'Slovenia', 46.1512, 14.9955),
(119, 'Diamond Princess', 0, 0),
(120, 'Lesotho', -29.609988, 28.233608),
(121, 'US', 37.0902, -95.7129),
(122, 'Antigua and Barbuda', 17.0608, -61.7964),
(123, 'Colombia', 4.5709, -74.2973),
(124, 'Ecuador', -1.8312, -78.1834),
(125, 'Western Sahara', 24.2155, -12.8858),
(126, 'Hungary', 47.1625, 19.5033),
(127, 'South Sudan', 6.877000000000002, 31.307),
(128, 'Japan', 36, 138),
(129, 'Moldova', 47.4116, 28.3699),
(130, 'Belarus', 53.7098, 27.9534),
(131, 'Mauritius', -20.2, 57.5),
(132, 'Albania', 41.1533, 20.1683),
(133, 'New Zealand', -40.9006, 174.886),
(134, 'Eswatini', -26.5225, 31.4659),
(135, 'Senegal', 14.4974, -14.4524),
(136, 'Honduras', 15.2, -86.2419),
(137, 'Italy', 43, 12),
(138, 'Ethiopia', 9.145, 40.4897),
(139, 'Haiti', 18.9712, -72.2852),
(140, 'Korea, South', 36, 128),
(141, 'Afghanistan', 33, 65),
(142, 'Burundi', -3.3731, 29.9189),
(143, 'Singapore', 1.2833, 103.8333),
(144, 'Czechia', 49.8175, 15.473),
(145, 'Egypt', 26, 30),
(146, 'Sierra Leone', 8.460555000000001, -11.779889),
(147, 'Bolivia', -16.2902, -63.5887),
(148, 'Malta', 35.9375, 14.3754),
(149, 'Russia', 60, 90),
(150, 'Saudi Arabia', 24, 45),
(151, 'Netherlands', 12.1784, -68.2385),
(152, 'Pakistan', 30.3753, 69.3451),
(153, 'Kosovo', 42.602636, 20.902977),
(154, 'Gambia', 13.4432, -15.3101),
(155, 'China', 29.1832, 120.0934),
(156, 'Ireland', 53.1424, -7.6921),
(157, 'Qatar', 25.3548, 51.1839),
(158, 'Slovakia', 48.669, 19.699),
(159, 'France', 46.8852, -56.3159),
(160, 'Lithuania', 55.1694, 23.8813),
(161, 'Serbia', 44.0165, 21.0059),
(162, 'Bosnia and Herzegovina', 43.9159, 17.6791),
(163, 'Kyrgyzstan', 41.2044, 74.7661),
(164, 'Bhutan', 27.5142, 90.4336),
(165, 'Romania', 45.9432, 24.9668),
(166, 'Togo', 8.6195, 0.8248),
(167, 'Niger', 17.6078, 8.0817),
(168, 'Philippines', 13, 122),
(169, 'Rwanda', -1.9403, 29.8739),
(170, 'Congo (Kinshasa)', -4.0383, 21.7587),
(171, 'Uzbekistan', 41.3775, 64.5853),
(172, 'Burma', 21.9162, 95.956),
(173, 'Bangladesh', 23.685, 90.3563),
(174, 'Barbados', 13.1939, -59.5432),
(175, 'Nicaragua', 12.8654, -85.2072),
(176, 'Norway', 60.472, 8.4689),
(177, 'Saint Vincent and the Grenadines', 12.9843, -61.2872),
(178, 'Botswana', -22.3285, 24.6849),
(179, 'Denmark', 56.2639, 9.5018),
(180, 'Dominican Republic', 18.7357, -70.1627),
(181, 'Mexico', 23.6345, -102.5528),
(182, 'Uganda', 1, 32),
(183, 'Zimbabwe', -20, 30),
(184, 'Suriname', 3.9193, -56.0278),
(185, 'Montenegro', 42.5, 19.3),
(186, 'Indonesia', -0.7893, 113.9213),
(187, 'Dominica', 15.415, -61.371);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `countries`
--
ALTER TABLE `countries`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=188;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
