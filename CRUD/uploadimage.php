<?php
require_once ('koneksi.php');
$target_path = dirname(__FILE__).'/uploads/';
$_BASE_URL = 'http://192.168.0.104/datacafe/uploads/';

if(isset($_FILES['image']['name'])) {

    $target_path = $target_path . basename($_FILES['image']['name']);

    try{

        if(!move_uploaded_file($_FILES['image']['tmp_name'], $target_path)){
            echo json_encode(array('status'=>'KO', 'message'=> 'image gagal diupload'));
        }else {
            $id = $_POST['id'];

            $sql = "UPDATE tb_cafe SET image = '".$_BASE_URL.basename($_FILES['image']['name']) ."' WHERE id = $id;";

            if (mysqli_query($con, $sql)) {
                echo json_encode(array('status'=>'OK', 'message'=>  'image berhasil diupload'));
            } else {
                echo json_encode(array('status'=>'KO', 'message'=> 'image gagal diupload'));
            }
        
            mysqli_close($con);
        } 

    }catch(Exception $e){
        echo json_encode(array('status'=>'KO', 'message'=> $e->getMessage()));
    }
}else {
    echo json_encode(array('status'=>'KO', 'message'=> 'Mohon Masukan image'));
}

?>