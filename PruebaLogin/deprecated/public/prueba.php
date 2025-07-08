<?php

//use App\Model\Login;
//use App\Model\Message;
//
//require_once '../vendor/autoload.php';

//
//header('Access-Control-Allow-Origin: *');
//header("Access-Control-Allow-Headers: X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method");
//header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
//header("Allow: GET, POST, OPTIONS, PUT, DELETE");
//$method = $_SERVER['REQUEST_METHOD'];
//if ($method == "OPTIONS") {
//  die();
//}
//
//// Simulacion de una BBDD con datos
//$users = array();
//$u = new Login();
//
///** @var Login $loginRequest */
//$loginRequest = json_decode(file_get_contents('php://input'));

//$login = new Login();
//$login->user = $loginRequest->user ?? '';
//$login->password = $loginRequest->password ?? '';
//$login->birthday = $loginRequest->birthday ?? '01/01/2000';
//$login->city = $loginRequest->city ?? 'Barcelona';
//$login->id = $loginRequest->id ?? 0;
//// RESPUESTA
//$message = new Message();
//$message->status = 'OK';

////Direccion del json
//$fileDb = __DIR__ . '/../db.json';
//
//// Verificamos si la base de datos existe
//if (!file_exists($fileDb)) {
//
//  $fp = fopen($fileDb, 'w+');
//  fwrite($fp, json_encode([]));
//  fclose($fp);
//
//}
//
//// Descargamos la base de datos
//$users = json_decode(file_get_contents($fileDb));
//
//// Validamos si el usuario existe en la base de datos
///** @var Login $user */
//if ($loginRequest->action != 'update') {
//  $key = array_search($login->user, array_column($users, 'user'));
//
//} else {
//  $key = array_search($login->id, array_column($users, 'id'));
//
//}
//
//$user = false;
//
//if($key !== FALSE) {
//
//  $user = $users[$key];
//
//}
//
//// En caso contrario lo añadimos
//if (!$user) {
//
//  if ($loginRequest->action == 'login') {
//
//    $lastId = end($users) ? end($users)->id : 0;
//    $login ->id = $lastId + 1;
//
//  } else {
//
//    $message->status = 'KO';
//    $message->message = 'User not found';
//
//    $u->user = $loginRequest->user;
//    $u->password = $loginRequest->password;
//    $u->birthday = $loginRequest->birthday;
//    $u->city = $loginRequest->city;
//    $u->id = $loginRequest->id;
//    $message->user = $u;
//
//  }
//
//} elseif ($loginRequest->action == 'update') {
//
//  //Validar que el nombre de usuario no exista en la base de datos
//  /** @var Login $user */
//
//  $anotherKey = array_search($login->user, array_column($users, 'user'));
//
//  //Si el nombre existe, devuelve fallo, si no, actualiza los datos
//  if($anotherKey !== FALSE) {
//
//    if ($users[$anotherKey]->id != $login->id) {
//
//      $message->status = 'KO';
//      $message->message = 'Username already exists';
//
//    } else {
//      if ($users[$anotherKey]->password != $login->password) {
//        $login->password = password_hash($login->password, PASSWORD_BCRYPT);
//      }
//      $users[$key] = $login;
//      file_put_contents($fileDb, json_encode($users));
//      $message->message = 'Your profile was updated';
//
//    }
//
//  } else {
//
//    $users[$key] = $login;
//    file_put_contents($fileDb, json_encode($users));
//    $message->message = 'Your profile was updated';
//
//  }
//
//} else {
//
//  //Validar password
//  if ((!password_verify($login->password, $user->password) && $loginRequest->action == 'login') || ($login->password !== $user->password && $loginRequest->action == 'delete')) {
//
//    $message->status = 'KO';
//    $message->message = 'Incorrect password';
//
//  } else {
//
//    if ($loginRequest->action == 'login') {
//
//      $u->user = $user->user;
//      $u->password = $user->password;
//      $u->birthday = $user->birthday;
//      $u->city = $user->city;
//      $u->id = $user->id;
//      $message->user = $u;
//      $message->message = 'You\'re now logged';
//
//    } else {
//
//      array_splice($users, $key, 1);
//      file_put_contents($fileDb, json_encode($users));
//      $message->message = 'User deleted';
//
//    }
//
//  }
//
//}
//
//print_r(json_encode($message));
//
exit;
