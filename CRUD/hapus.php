<?php
 $id = $_GET['id'];
 
 require_once('koneksi.php');
 
 $sql = "DELETE FROM tb_cafe WHERE id=$id;";
 
 if(mysqli_query($con,$sql)){
   echo json_encode(array('status' => 'OK', 'message' => 'Berhasil Hapus Data'));
 } else {
   echo json_encode(array('status' => 'KO', 'message' => 'Gagal Hapus Data'));
 }
 
 mysqli_close($con);
 ?>