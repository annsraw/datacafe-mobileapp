<?php
require_once 'koneksi.php';

$sql = "SELECT * FROM tb_cafe";

$r = mysqli_query($con, $sql);

$result = array();

while ($row = mysqli_fetch_array($r)) {
	array_push($result, array(
		"id" 					=> $row['id'],
		"nama" 					=> $row['nama'],
		"pesanan" 				=> $row['pesanan'],
		"tanggal_pemesanan" 	=> $row['tanggal_pemesanan'],
		"keterangan" 			=> $row['keterangan'],
		"image" 				=> $row['image']	
	));
}

echo json_encode(array('cafe' => $result));



mysqli_close($con);
?>