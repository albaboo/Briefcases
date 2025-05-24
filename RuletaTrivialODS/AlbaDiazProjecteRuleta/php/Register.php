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
    if (!str_ends_with($user, '@ies-sabadell.cat')) {
        $resultat['error'] = 'Correu incorrecte';
    } elseif(strlen($pwd) < 5) {
        $resultat['error'] = 'Minim 5 caràcters al password';
    } else {
        $query = $conn->prepare("SELECT id FROM usuaris_app WHERE nom LIKE :user");
        $query->bindParam(":user", $user, PDO::PARAM_STR);
        $query->execute();
        if ($query->rowCount() > 0) {
            $resultat['error'] = 'L\'usuari existeix';
        } else {
            $queryI = $conn->prepare("INSERT INTO usuaris_app (nom, password) VALUES (:user, :pwd)");
            $queryI->bindParam(":user", $user, PDO::PARAM_STR);
            $queryI->bindParam(":pwd", $pwd, PDO::PARAM_STR);
            if ($queryI->execute()) {
                $resultat['estat'] = 'OK';
            } else {
                $resultat['error'] = 'Error al registrar';
            }
        }
    }
} catch (Exception $exception) {
    $resultat['error'] = $exception->getMessage();
}
echo json_encode($resultat);