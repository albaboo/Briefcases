<?php

use App\Model\Login;
use App\Model\Message;

require_once '../vendor/autoload.php';
$conn = require 'conexion.php';

header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Headers: X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
header("Allow: GET, POST, OPTIONS, PUT, DELETE");
$method = $_SERVER['REQUEST_METHOD'];
if ($method == "OPTIONS") {
  die();
}

//Recibe usuario
/** @var Login $loginRequest */
$loginRequest = json_decode(file_get_contents('php://input'));

$login = new Login();
$login->user = $loginRequest->user ?? '';
$login->password = $loginRequest->password ?? '';
$login->birthday = $loginRequest->birthday ?? '2000-01-01';
$login->city = $loginRequest->city ?? 'Barcelona';
$login->id = $loginRequest->id ?? 0;

// RESPUESTA
$message = new Message();
$message->status = 'OK';

try{
  // Validamos si el usuario existe en la base de datos
  /** @var Login $user */
  if ($loginRequest->action == 'update') {
    $queryUser = $conn->prepare("SELECT * FROM users WHERE id LIKE :id");
    $queryUser->bindParam(":id", $login->id, PDO::PARAM_INT);

  } else {
    $queryUser = $conn->prepare("SELECT * FROM users WHERE username LIKE :username");
    $queryUser->bindParam(":username", $login->user, PDO::PARAM_STR);

  }
  $queryUser->execute();
  $user = $queryUser->fetch(PDO::FETCH_ASSOC);

  if ($queryUser->rowCount() === 0 && $loginRequest->action != 'login') {
    // Un usuario ha intentado borrar o actualizar su perfil, pero, no se ha encontrado en la base de datos
    $message->status = 'KO';
    $message->message = 'User not found';

  } elseif ($queryUser->rowCount() === 0 && $loginRequest->action == 'login') {
    // Un usuario sin cuenta ha intentado iniciar sesión, asi que, se le ha registrado en la base de datos para que pueda
    $login->password = password_hash($login->password, PASSWORD_BCRYPT);
    $queryRegister = $conn->prepare("INSERT INTO users (username, password, birthday, city) VALUES (:username, :password, :birthday, :city)");
    $queryRegister->bindParam(":username", $login->user, PDO::PARAM_STR);
    $queryRegister->bindParam(":password", $login->password, PDO::PARAM_STR);
    $queryRegister->bindParam(":birthday", $login->birthday, PDO::PARAM_STR);
    $queryRegister->bindParam(":city", $login->city, PDO::PARAM_STR);
    $queryRegister->execute();
    $queryNew = $conn->prepare("SELECT * FROM users WHERE username LIKE :username");
    $queryNew->bindParam(":username", $login->user, PDO::PARAM_STR);
    $queryNew->execute();
    $userNew = $queryNew->fetch(PDO::FETCH_ASSOC);
    $login->id = $userNew['id'];
    $message->user = $login;
    $message->message = 'You\'re now registered';

  } else {

    if ($loginRequest->action == 'update') {
      // Verifica que si el usuario intenta cambiar su nombre de usuario, este nombre no este ya registrado
      $queryAnother = $conn->prepare("SELECT * FROM users WHERE username LIKE :username");
      $queryAnother->bindParam(":username", $login->user, PDO::PARAM_STR);
      $queryAnother->execute();
      $anotherUser = $queryAnother->fetch(PDO::FETCH_ASSOC);
      // Si el usuario ha cambiado la contraseña se vuelve a encriptar
      if($user['password'] != $login->password) {
        $login->password = password_hash($login->password, PASSWORD_BCRYPT);
      }

      //Preparar la consulta que se ejecutará solo si el cambio es valido
      $queryUpdate = $conn->prepare("UPDATE users SET username = :username, password = :password, birthday = :birthday, city = :city WHERE id = :id");
      $queryUpdate->bindParam(":username", $login->user, PDO::PARAM_STR);
      $queryUpdate->bindParam(":password", $login->password, PDO::PARAM_STR);
      $queryUpdate->bindParam(":birthday", $login->birthday, PDO::PARAM_STR);
      $queryUpdate->bindParam(":city", $login->city, PDO::PARAM_STR);
      $queryUpdate->bindParam(":id", $login->id, PDO::PARAM_INT);

      // Si el nombre está registrado, puede que sea del usuario (porque no ha sido ese el cambio y, por lo tanto, puede actualizar su perfil,
      // o porque el nombre de usuario ya está registrado en otra cuenta y no puedo cambiarlo a ese
      if ($queryAnother ->rowCount() != 0 ) {

        if ($user['id'] != $anotherUser['id']) {
          $message->status = 'KO';
          $message->message = 'Username already exists';
        } else {
          $queryUpdate->execute();
          $message->message = 'Your profile was updated';
        }
      } else {
        $queryUpdate->execute();
        $message->message = 'Your profile was updated';
      }


    } elseif($loginRequest->action == 'delete') {
      // Un usuario ha borrado su perfil con éxito
      $queryDelete = $conn->prepare("DELETE FROM users WHERE id LIKE :id");
      $queryDelete->bindParam(":id", $user['id'], PDO::PARAM_INT);
      $queryDelete->execute();
      $message->message = 'User deleted';

    } else {
      if (!password_verify($login->password, $user['password'])) {
        // Un usuario ha intentado iniciar sesión, pero, la contraseña no coincide con la que hay registrada en la base de datos
        $message->status = 'KO';
        $message->message = 'Incorrect password';
      } else {
        // Un usuario ha conseguido iniciar sesión con éxito
        $login->city = $user['city'];
        $login->birthday = $user['birthday'];
        $login->id = $user['id'];
        $message->message = 'You\'re logged now';
        $message->user = $login;
      }
    }
  }

} catch (Exception $exception) {
  $message->status = 'KO';
  $message->message = $exception->getMessage();
}

print_r(json_encode($message));

exit;
