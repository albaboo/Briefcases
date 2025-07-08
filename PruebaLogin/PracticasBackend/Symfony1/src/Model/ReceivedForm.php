<?php

namespace App\Model;

use DateTime;

class ReceivedForm extends StandardForm
{
    public string $budgetType = '';
    public array $status = [];
    public string $practice = '';
    public array $account = [];
    public DateTime|null $startDate = null;
    public DateTime|null $startTime = null;
    public DateTime|null $endDate = null;
    public DateTime|null $endTime = null;
    public string $sourceType = '';
    public string $msaSigner = '';
    public string $billingEntity = '';
    public string $industry = '';
    public string $region = '';
}
