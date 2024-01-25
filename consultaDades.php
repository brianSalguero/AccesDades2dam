<?php
$servidor = "localhost";
$usuario = "root";
$password = "";
$dbname = "aev04";

// Conexió a la base de dades
$conexion = mysqli_connect($servidor, $usuario, $password, $dbname);

if (!$conexion) {
    die("Error en la conexión a MySQL: " . mysqli_connect_error());
}

$sql = "SELECT * FROM dades";
$resultado = mysqli_query($conexion, $sql);

if (!$resultado) {
    die("Error al ejecutar la consulta: " . mysqli_error($conexion));
}

// Crea un array per a emmagatzemar els resultats
$datos = array();

// Obte els resultats y afegir l'array
while ($fila = mysqli_fetch_assoc($resultado)) {
    $datos[] = $fila;
}

// Llibera el resultat y tanca la conexió
mysqli_free_result($resultado);
mysqli_close($conexion);

// Torna les dades en format JSON
echo json_encode($datos);
?>