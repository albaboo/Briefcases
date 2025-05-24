<?php
global $conn;
include "Connexio.php";
$tema = $_GET['tema'];
$query = null;
try {
    if ($tema == 0) {
        $query =  $conn->prepare("SELECT * FROM preguntes ORDER BY tema");;
    } else {
        $query =  $conn->prepare("SELECT * FROM preguntes WHERE tema = :tema");;
        $query->bindParam(":tema", $tema, PDO::PARAM_INT);
    }
    $query->execute();
    $preguntes = $query->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($preguntes);
} catch (Exception $e) {
    echo json_encode(['error' => $e->getMessage()]);
}