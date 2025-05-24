<?php
global $conn;
include "Connexio.php";
$id = $_GET["id"];
$desc = $_GET["desc"];
$tema = $_GET["tema"];
$r1 = $_GET["r1"];
$r2 = $_GET["r2"];
$r3 = $_GET["r3"];
$r4 = $_GET["r4"];
$solu = $_GET["solu"];
$resultat = "";
try{
    $queryS = $conn->prepare("SELECT * FROM preguntes WHERE idpreguntes = :id");
    $queryS->bindParam(":id", $id, PDO::PARAM_INT);
    $queryS->execute();
    $pregunta = $queryS->fetch(PDO::FETCH_ASSOC);
    if ($queryS->rowCount() === 0) {
        $resultat = 'ID de pregunta no existeix';
    } else {
        $modifica = [];
        $params = [":id" => $id];
        if ($desc != $pregunta['descripcio'] && !empty($desc)) {
            $modifica[] = "descripcio = :desc";
            $params[":desc"] = $desc;
        }
        if ($tema != $pregunta['tema'] && !empty($tema)) {
            $modifica[] = "tema = :tema";
            $params[":tema"] = $tema;
        }
        if ($r1 != $pregunta['resposta1'] && !empty($r1)) {
            $modifica[] = "resposta1 = :r1";
            $params[":r1"] = $r1;
        }
        if ($r2 != $pregunta['resposta2'] && !empty($r2)) {
            $modifica[] = "resposta2 = :r2";
            $params[":r2"] = $r2;
        }
        if ($r3 != $pregunta['resposta3'] && !empty($r3)) {
            $modifica[] = "resposta3 = :r3";
            $params[":r3"] = $r3;
        }
        if ($r4 != $pregunta['resposta4'] && !empty($r4)) {
            $modifica[] = "resposta4 = :r4";
            $params[":r4"] = $r4;
        }
        if ($solu != $pregunta['solucio'] && !empty($solu)) {
            $modifica[] = "solucio = :solu";
            $params[":solu"] = $solu;
        }
        if (count($modifica) > 0) {
            $query = $conn->prepare("UPDATE preguntes SET ". implode(", ", $modifica) ." WHERE idpreguntes = :id");
            foreach ($params as $key => &$value) {
                $query->bindParam($key, $value, is_int($value) ? PDO::PARAM_INT : PDO::PARAM_STR);
            }
            if ($query->execute()) {
                $resultat = 'Pregunta modificada correctament';
            } else {
                $resultat = 'Error al modifcar pregunta';
            }
        } else {
            $resultat = 'No hi ha camps distints per modificar';
        }
    }
} catch (Exception $exception) {
    $resultat = $exception->getMessage();
}
echo json_encode($resultat);