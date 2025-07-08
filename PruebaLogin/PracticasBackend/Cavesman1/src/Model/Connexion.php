<?php

namespace App\Model;

use Cavesman\Config;
use Exception;
use PDO;

class Connexion
{
    private static ?Connexion $instance = null;
    private ?PDO $conn;

    private function __construct()
    {
        $servidor = Config::get('database.host', "localhost");
        $user = Config::get('database.user', "root");
        $pass = Config::get('database.password', "1234");
        $dbname = Config::get('database.dbname', "pruebalogin");
        $port = Config::get('database.port', "3306");
        try {
            $this->conn = new PDO("mysql:host=$servidor;dbname=$dbname;port=$port", $user, $pass);
        } catch (Exception $exception) {
            print $exception->getMessage();
        }
    }

    public static function getInstance(): Connexion
    {
        if (self::$instance === null) {
            self::$instance = new Connexion();
        }
        return self::$instance;
    }

    public function getConnection(): PDO
    {
        return $this->conn;
    }
}