<?php
global $conn;
include "Connexio.php";
$tema = $_GET['tema'];
$resultat = [
    'estat' => "KO",
    'error' => "",
    'pregunta' => "",
    'resposta1' => "",
    'resposta2' => "",
    'resposta3' => "",
    'resposta4' => "",
    'solucio' => ""
];
try{
    $query = $conn->prepare("SELECT * FROM preguntes WHERE tema = :tema ORDER BY RAND()");
    $query->bindParam(":tema", $tema, PDO::PARAM_INT);
    $query->execute();
    $pregunta =  $query->fetch(PDO::FETCH_ASSOC);
    if ($query->rowCount() === 0) {
        $resultat['error'] = 'No hi ha preguntes per aquest tema';
    } else {
        $resultat['estat'] = 'OK';
        $resultat['pregunta'] = $pregunta["descripcio"];
        $resultat['resposta1'] = $pregunta["resposta1"];
        $resultat['resposta2'] = $pregunta["resposta2"];
        $resultat['resposta3'] = $pregunta["resposta3"];
        $resultat['resposta4'] = $pregunta["resposta4"];
        $resultat['solucio'] = $pregunta["solucio"];
    }
} catch (Exception $exception) {
    $resultat['error'] = $exception->getMessage();
}
echo json_encode($resultat);