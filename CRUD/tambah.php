<?php
	require_once('koneksi.php');
	
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$id 				 = $_POST['id'];
		$nama				 = $_POST['nama'];
		$pesanan 			 = $_POST['pesanan'];
		$tanggal_pemesanan	 = $_POST['tanggal_pemesanan'];
		$keterangan 		 = $_POST['keterangan'];
		$sql 				 = "INSERT INTO tb_cafe (id,nama,pesanan,tanggal_pemesanan,keterangan) VALUES ('$id','$nama','$pesanan','$tanggal_pemesanan','$keterangan')";
		
		if(mysqli_query($con,$sql)){
			echo json_encode(array('status' => 'OK', 'message' => 'Berhasil Tambah Data'));
		}else{
			echo json_encode(array('status' => 'KO', 'message' => 'Gagal Tambah Data'));
		}
		
		mysqli_close($con);
	}
?>