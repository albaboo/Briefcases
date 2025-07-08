<?php

namespace App\Model;

use Cavesman\Db\Doctrine\Entity\Base;
use DateTime;

class Profile extends User
{
    const string|Base ENTITY = \App\Entity\User::class;

    public string $username = '';
    public DateTime|string|null $birthday = null;
    public string $city = '';
    public int $admin = 0;
    public function json(): static
    {
        $birthday = $this->birthday;
        $json =  parent::json();
        $json->birthday = $birthday->format('Y-m-d');
        return $json;
    }
}