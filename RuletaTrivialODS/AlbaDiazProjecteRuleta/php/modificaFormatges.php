<?php
global $conn;
include "Connexio.php";
$formatge = $_GET['formatge'];
$partida = $_GET['partida'];
$resultat = [
    'estat' => "KO",
    'error' => "",
    'num' => 0
];
if ($formatge == "Hotpink") {
    $resultat['num'] = 6;
} else if ($formatge == "Vermell") {
    $resultat['num'] = 5;
} else if ($formatge == "Groc") {
    $resultat['num'] = 4;
} else if ($formatge == "Lime") {
    $resultat['num'] = 3;
} else if ($formatge == "Cyan") {
    $resultat['num'] = 2;
} else if ($formatge == "Fuchsia") {
    $resultat['num'] = 1;
}
try{
    $query = $conn->prepare("SELECT * FROM partides_usuaris WHERE idpartida = :partida");
    $query->bindParam(":partida", $partida, PDO::PARAM_INT);
    $query->execute();
    if ($query->rowCount() === 0) {
        $resultat['error'] = 'Partida no existeix';
    } else {
        $result = $query->fetch(PDO::FETCH_ASSOC);
        $formatges = json_decode($result['formatges'], true);
        if ($formatges[$resultat['num']] === false) {
            $formatges[$resultat['num']] = true;
            $f = json_encode($formatges);
            $query2 = $conn->prepare("UPDATE partides_usuaris SET formatges = :f WHERE idpartida = :partida");
            $query2->bindParam(":f", $f, PDO::PARAM_STR);
            $query2->bindParam(":partida", $partida, PDO::PARAM_INT);
            if ($query2->execute()) {
                $resultat['estat'] = "OK";
            } else {
                $resultat['error'] = 'Error al actulitzar formatge nou';
            }
        } else {
            $resultat['error'] = 'Formatge conseguit abans, cap canvi fet :(';
        }

    }
} catch (Exception $exception) {
    $resultat['error'] = $exception->getMessage();
}
echo json_encode($resultat);