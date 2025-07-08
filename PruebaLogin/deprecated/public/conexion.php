<?php
$servidor = "localhost";
$user= "root";
$pass = "1234";
try{
  return new PDO("mysql:host=$servidor;dbname=pruebalogin;port=3306",$user,$pass);
} catch (Exception $exception) {
  print $exception->getMessage();
}

//$servername = "localhost";
//$username = "username";
//$password = "password";
//
//// Create connection
//$conn = new mysqli($servername, $username, $password);
//
//// Check connection
//if ($conn->connect_error) {
//  die("Connection failed: " . $conn->connect_error);
//}
//echo "Connected successfully";

//$servername = "localhost";
//$username = "username";
//$password = "password";
//
//// Create connection
//$conn = mysqli_connect($servername, $username, $password);
//
//// Check connection
//if (!$conn) {
//  die("Connection failed: " . mysqli_connect_error());
//}
//echo "Connected successfully";
