<?php
global $conn;
include "Connexio.php";
$valor = $_GET["valor"];
$user = $_GET["user"];
$resultat = [
    "estat" => "KO",
    "error" => "",
    "punts" => 0,
];
try{
    $query = $conn->prepare("SELECT * FROM usuaris_app WHERE nom LIKE :user");
    $query->bindParam(":user", $user, PDO::PARAM_STR);
    $query->execute();
    $usuari = $query->fetch(PDO::FETCH_ASSOC);
    $nouPunts = $usuari["punts"] + $valor;
    $queryU = $conn->prepare("UPDATE usuaris_app SET punts = :nouPunts WHERE nom LIKE :user");
    $queryU->bindParam(":nouPunts", $nouPunts, PDO::PARAM_INT);
    $queryU->bindParam(":user", $user, PDO::PARAM_STR);
    if ($queryU->execute()) {
        $resultat["estat"] = "OK";
        $resultat["punts"] = $nouPunts;
    } else {
        $resultat = "Error al modifcar punts";
    }
} catch (Exception $exception) {
    $resultat['error'] = $exception->getMessage();
}
echo json_encode($resultat);