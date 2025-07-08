<?php

namespace App\Model;

class StandardForm extends BasicForm
{
    public array $users = [];
    public string $projectName = '';
    public string $shortName = '';
    public string $description = '';
}