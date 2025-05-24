<?php
$servidor = "localhost";
$usuari = "root";
$contrasenya = "super3";
try{
    $conn=new PDO("mysql:host=$servidor;dbname=trivial;port=3302",$usuari,$contrasenya);
} catch (Exception $exception) {
    print $exception->getMessage();
}