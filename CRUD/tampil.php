<?php
	$id = $_GET['id'];
	
	require_once('koneksi.php');
	
	$sql = "SELECT * FROM tb_cafe WHERE id=$id";
	
	$r = mysqli_query($con,$sql);
	
	$result = array();
	
	$row = mysqli_fetch_array($r);
	
	array_push($result,array(
		"id"			 		=>$row['id'],
		"nama"		 			=>$row['nama'],
		"pesanan"			 	=>$row['pesanan'],
		"tanggal_pemesanan"		=>$row['tanggal_pemesanan'],
		"keterangan"	 		=>$row['keterangan'],
		"image"			 		=>$row['image']
		));
	
	if ($result[0]['id'] !=""){
		echo json_encode($result[0]);
	}else{
		echo json_encode(array('status' => 'KO', 'message' => 'Data Tidak Ditemukan'));
	}
	
	mysqli_close($con);
?>