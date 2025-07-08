<?php

namespace App\Entity;

use DateTime;
use Doctrine\ORM\Mapping\Column;
use Doctrine\ORM\Mapping\Entity;

#[Entity]
class User extends \Cavesman\Db\Doctrine\Entity\Entity
{

    #[Column(name: "username", type: "string", unique: true)]
    public string $username;

    #[Column(name: "password", type: "string", nullable: true)]
    public ?string $password = null;

    #[Column(name: "birthday", type: "date", nullable: true)]
    public DateTime $birthday;

    #[Column(name: "city", type: "string", nullable: true)]
    public  string $city;

    #[Column(name: "admin", type: "boolean", nullable: true)]
    public  string $admin;

}