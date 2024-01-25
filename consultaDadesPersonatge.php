<?php

$nomPersonatge = $_GET["nomPersonatge"];
$servidor = "localhost";
$usuario = "root";
$password = "";
$dbname = "aev04";

// Conexió a la base de dades
$conexion = mysqli_connect($servidor, $usuario, $password, $dbname);

if (!$conexion) {
    echo "Error en la conexion a MySQL: " . mysqli_connect_error();
    exit();
}

$sql = "SELECT * FROM dades WHERE nomPersonatge = '".$nomPersonatge."'";
$resultado = mysqli_query($conexion, $sql);

if (!$resultado) {
    echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
}

// Crea un array per a emmagatzemar els resultats
$datos = array();

// Obte els resultats i s'afegix l'array
while ($fila = mysqli_fetch_assoc($resultado)) {
    $datos[] = $fila;
}

// Llibera el resultat i tanca la conexió
mysqli_free_result($resultado);
mysqli_close($conexion);

// Torna les dades en format JSON
echo json_encode($datos);
?>