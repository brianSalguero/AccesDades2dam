<?php
if (isset($_POST["id"])) {
    $id = $_POST["id"];
    $nomPersonatge = $_POST["nomPersonatge"];
    $imatgeUrl = $_POST["imatgeUrl"];
    $pelicules = $_POST["pelicules"];
    $series = $_POST["series"];
    $videoJocs = $_POST["videoJocs"];
    $webUrl = $_POST["webUrl"];

    $servidor = "localhost";
    $usuario = "root";
    $password = "";
    $dbname = "aev04";
    $conexion = mysqli_connect($servidor, $usuario, $password, $dbname);

    // Si te una cadena com ' , al fer el INSERT no lo agafarà
    $id = mysqli_real_escape_string($conexion, $id);
    $nomPersonatge = mysqli_real_escape_string($conexion, $nomPersonatge);
    $imatgeUrl = mysqli_real_escape_string($conexion, $imatgeUrl);
    $pelicules = mysqli_real_escape_string($conexion, $pelicules);
    $series = mysqli_real_escape_string($conexion, $series);
    $videoJocs = mysqli_real_escape_string($conexion, $videoJocs);
    $webUrl = mysqli_real_escape_string($conexion, $webUrl);

    if (!$conexion) {
        echo "Error en la conexion a MySQL: " . mysqli_connect_error();
        exit();
    }
    $sql = "INSERT INTO dades (id, nomPersonatge, imatgeUrl, pelicules, series, videoJocs, webUrl) VALUES ('" . $id . "','" . $nomPersonatge . "','" . $imatgeUrl . "','" . $pelicules . "','" . $series . "','" . $videoJocs . "','" . $webUrl . "')";
    if (mysqli_query($conexion, $sql)) {
        echo "Registro insertado correctamente.";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
    }
}
?>