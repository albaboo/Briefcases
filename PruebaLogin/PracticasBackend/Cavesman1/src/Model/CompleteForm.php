<?php

namespace App\Model;

use DateTime;

class CompleteForm extends StandardForm
{
    public string $budgetType = '';
    public array $status = [];
    public string $practice = '';
    public string $account = '';
    public DateTime|string|null $startDate = null;
    public DateTime|string|null $endDate = null;
    public string $sourceType = '';
    public string $msaSigner = '';
    public string $billingEntity = '';
    public string $industry = '';
    public string $region = '';
}