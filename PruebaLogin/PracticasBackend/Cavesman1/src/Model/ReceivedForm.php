<?php

namespace App\Model;

use App\Entity\Form;
use Cavesman\Db\Doctrine\Entity\Base;
use Cavesman\Model\Time;
use DateTime;

class ReceivedForm extends StandardForm
{
    const string|Base ENTITY = Form::class;
    public string $budgetType = '';
    public array $status = [];
    public string $practice = '';
    public string $account = '';
    public DateTime|string|null $startDate = null;
    public Time|string|null $startTime = null;
    public DateTime|string|null $endDate = null;
    public Time|string|null $endTime = null;
    public string $sourceType = '';
    public string $msaSigner = '';
    public string $billingEntity = '';
    public string $industry = '';
    public string $region = '';
}