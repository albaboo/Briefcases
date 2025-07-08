<?php

namespace App\Entity;

use Doctrine\ORM\Mapping\Column;
use Doctrine\ORM\Mapping\Entity;

#[Entity]
class Form extends \Cavesman\Db\Doctrine\Entity\Entity
{

    #[Column(name: "users", type: "json")]
    public array $users;

    #[Column(name: "project_name", type: "string")]
    public string $projectName;

    #[Column(name: "short_name", type: "string")]
    public string $shortName;

    #[Column(name: "description", type: "text", nullable: true)]
    public string $description;

    #[Column(name: "budget_type", type: "string", nullable: true)]
    public string $budgetType;

    #[Column(name: "status", type: "json", nullable: true)]
    public array $status;

    #[Column(name: "practice", type: "string", nullable: true)]
    public string $practice;

    #[Column(name: "account", type: "string", nullable: true)]
    public string $account;

    #[Column(name: "start_date", type: "datetime", nullable: true)]
    public string $startDate;

    #[Column(name: "end_date", type: "datetime", nullable: true)]
    public string $endDate;

    #[Column(name: "source_type", type: "string", nullable: true)]
    public string $sourceType;

    #[Column(name: "msa_signer", type: "string", nullable: true)]
    public string $msaSigner;

    #[Column(name: "billing_entity", type: "string", nullable: true)]
    public string $billingEntity;

    #[Column(name: "industry", type: "string", nullable: true)]
    public string $industry;

    #[Column(name: "region", type: "string", nullable: true)]
    public string $region;

}