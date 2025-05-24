<?php
global $conn;
include "Connexio.php";
$desc = $_GET["desc"];
$tema = $_GET["tema"];
$r1 = $_GET["r1"];
$r2 = $_GET["r2"];
$r3 = $_GET["r3"];
$r4 = $_GET["r4"];
$solu = $_GET["solu"];
$resultat = "";
try{
    $queryI = $conn->prepare("INSERT INTO preguntes (descripcio, tema, resposta1, resposta2, resposta3, resposta4, solucio) VALUES (:desc, :tema, :r1, :r2, :r3, :r4, :solu)");
    $queryI->bindParam(":desc", $desc, PDO::PARAM_STR);
    $queryI->bindParam(":tema", $tema, PDO::PARAM_INT);
    $queryI->bindParam(":r1", $r1, PDO::PARAM_STR);
    $queryI->bindParam(":r2", $r2, PDO::PARAM_STR);
    $queryI->bindParam(":r3", $r3, PDO::PARAM_STR);
    $queryI->bindParam(":r4", $r4, PDO::PARAM_STR);
    $queryI->bindParam(":solu", $solu, PDO::PARAM_INT);
    if ($queryI->execute()) {
        $resultat = 'Pregunta creada correctament';
    } else {
        $resultat = 'Error al crear pregunta';
    }
} catch (Exception $exception) {
    $resultat = $exception->getMessage();
}
echo json_encode($resultat);