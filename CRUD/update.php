<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		$nama				 = $_POST['nama'];
		$pesanan 			 = $_POST['pesanan'];
		$tanggal_pemesanan	 = $_POST['tanggal_pemesanan'];
		$keterangan 		 = $_POST['keterangan'];
		require_once('koneksi.php');
		
		$sql = "UPDATE tb_cafe SET nama = '$nama', pesanan = '$pesanan', tanggal_pemesanan = '$tanggal_pemesanan', keterangan = '$keterangan' WHERE id = $id;";
		
		if (mysqli_query($con, $sql)) {
		echo json_encode(array('status' => 'OK', 'message' => 'Berhasil Update Data'));
		}else{
		echo json_encode(array('status' => 'KO', 'message' => 'Gagal Update Data'));
		}
		
		mysqli_close($con);
	}
?>