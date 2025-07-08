<?php
use App\Model\Connexion;

$conn = Connexion::getInstance()->getConnection();
try {
    Cavesman\Router::run();
} catch (\Soanix\Router\RouterException $exception) {
    print $exception->getMessage();
}