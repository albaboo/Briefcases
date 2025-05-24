<?php
global $conn;
include "Connexio.php";
$user = $_GET["user"];
$pwd = $_GET["pwd"];
$resultat = [
    'estat' => 'KO',
    'error' => '',
    'usuari_app' => $user
];
try{
    $query = $conn->prepare("SELECT * FROM usuaris_app WHERE nom LIKE :user");
    $query->bindParam(":user", $user, PDO::PARAM_STR);
    $query->execute();
    $usuari = $query->fetch(PDO::FETCH_ASSOC);
    if ($query->rowCount() === 0) {
        $resultat['error'] = 'Usuari no existeix';
    } elseif ($pwd != $usuari['password']) {
        $resultat['error'] = 'Credencial incorrecte';
    } else {
        $resultat['estat'] = 'OK';
    }
} catch (Exception $exception) {
    $resultat['error'] = $exception->getMessage();
}
echo json_encode($resultat);