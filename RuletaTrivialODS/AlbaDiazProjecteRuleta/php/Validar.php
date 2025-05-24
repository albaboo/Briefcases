<?php
global $conn;
include "Connexio.php";
$partida = $_GET["partida"];
$user = $_GET["user"];
$resultat = [
    'estat' => 'KO',
    'error' => '',
    'partida' => $partida
];
try{
    $query = $conn->prepare("SELECT * FROM partides WHERE idpartides = :partida");
    $query->bindParam(":partida", $partida, PDO::PARAM_INT);
    $query->execute();
    if ($query->rowCount() === 0) {
        $resultat['error'] = 'Partida no existeix';
    } else {
        $query2 = $conn->prepare("SELECT * FROM usuaris_app WHERE nom LIKE :user");
        $query2->bindParam(":user", $user, PDO::PARAM_STR);
        $query2->execute();
        $result2= $query2->fetch(PDO::FETCH_ASSOC);
        $idusuari = $result2['id'];
        $query3 = $conn->prepare("SELECT * FROM partides_usuaris WHERE idpartida = :partida AND idusuari = :idusuari");
        $query3->bindParam(":partida", $partida, PDO::PARAM_INT);
        $query3->bindParam(":idusuari", $idusuari, PDO::PARAM_STR);
        $query3->execute();
        if ($query3->rowCount() === 0) {
            $resultat['error'] = 'Partida no perteneix a aquest usuari';
        } else {
            $result3 = $query3->fetch(PDO::FETCH_ASSOC);
            $formatges = json_decode($result3['formatges'], true);
            if (in_array(false,$formatges)){
                $resultat['estat'] = 'OK';
            } else {
                $resultat['error'] = 'Tots els formatges conseguits';
            }
        }

    }
} catch (Exception $exception) {
    $resultat['error'] = $exception->getMessage();
}
echo json_encode($resultat);