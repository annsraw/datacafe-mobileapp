-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2020 at 11:27 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_cafee`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_cafe`
--

CREATE TABLE `tb_cafe` (
  `id` int(10) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `pesanan` varchar(255) NOT NULL,
  `tanggal_pemesanan` varchar(255) NOT NULL,
  `keterangan` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_cafe`
--

INSERT INTO `tb_cafe` (`id`, `nama`, `pesanan`, `tanggal_pemesanan`, `keterangan`, `image`) VALUES
(42, 'abel', 'kopi', '11 July 2020', 'gapake gula', 'http://192.168.0.104/datacafe/uploads/image1594458880419.jpeg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_cafe`
--
ALTER TABLE `tb_cafe`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_cafe`
--
ALTER TABLE `tb_cafe`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
