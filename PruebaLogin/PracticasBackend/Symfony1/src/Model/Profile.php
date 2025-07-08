<?php

namespace App\Model;

use DateTime;

class Profile extends User
{

    public string $username = '';
    public DateTime|string|null $birthday = null;
    public string $city = '';
    public int $admin = 0;
}
