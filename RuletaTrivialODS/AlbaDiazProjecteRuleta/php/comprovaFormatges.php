<?php
global $conn;
include "Connexio.php";
$partida = $_GET["partida"];
$resultat = [
    'estat' => 'KO',
    'error' => '',
    'complet' => false
];
try{
    $query = $conn->prepare("SELECT * FROM partides_usuaris WHERE idpartida = :partida");
    $query->bindParam(":partida", $partida, PDO::PARAM_INT);
    $query->execute();
    if ($query->rowCount() === 0) {
        $resultat['error'] = 'Partida no trobada';
    } else {
        $resultat['estat'] = 'OK';
        $result = $query->fetch(PDO::FETCH_ASSOC);
        $formatges = json_decode($result['formatges'], true);
        if (!in_array(false,$formatges)){
            $resultat['complet'] = true;
        }
    }
} catch (Exception $exception) {
    $resultat['error'] = $exception->getMessage();
}
echo json_encode($resultat);