<?php
global $conn;
include "Connexio.php";
$desc = $_GET["desc"];
$user = $_GET["user"];
$resultat = [
    'estat' => 'KO',
    'error' => '',
    'usuari_app' => $user
];
try{
    $queryS = $conn->prepare("SELECT * FROM partides WHERE descripcio LIKE :desc");
    $queryS->bindParam(":desc", $desc, PDO::PARAM_STR);
    $queryS->execute();
    $queryI = $conn->prepare("INSERT INTO partides (descripcio) VALUES (:desc)");
    $queryI->bindParam(":desc", $desc, PDO::PARAM_STR);
    if ($queryI->execute()) {
        $query = $conn->prepare("SELECT id FROM usuaris_app WHERE nom LIKE :user");
        $query->bindParam(":user", $user, PDO::PARAM_STR);
        $query->execute();
        $result = $query->fetch(PDO::FETCH_ASSOC);
        $queryS->execute();
        $result2 = $queryS->fetch(PDO::FETCH_ASSOC);
        $idusuari = $result['id'];
        $idpartida = $result2['idpartides'];
        $formatges = json_encode([1 => false, 2 => false, 3 => false, 4 => false, 5 => false, 6 => false]);
        $queryI = $conn->prepare("INSERT INTO partides_usuaris (idusuari, idpartida, formatges) VALUES (:idusuari,:idpartida, :formatges)");
        $queryI->bindParam(":idusuari", $idusuari, PDO::PARAM_INT);
        $queryI->bindParam(":idpartida", $idpartida, PDO::PARAM_INT);
        $queryI->bindParam(":formatges", $formatges, PDO::PARAM_STR);
        if ($queryI->execute()) {
            $resultat['estat'] = 'OK';
        } else {
            $resultat['error'] = 'Error al crear partida usuari';
        }
    } else {
        $resultat['error'] = 'Error al crear partida';
    }
} catch (Exception $exception) {
    $resultat['error'] = $exception->getMessage();
}
echo json_encode($resultat);