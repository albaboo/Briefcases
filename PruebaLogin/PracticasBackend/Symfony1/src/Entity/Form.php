<?php

namespace App\Entity;

use DateTime;
use Doctrine\ORM\Mapping\Column;
use Doctrine\ORM\Mapping\Entity;
use Doctrine\ORM\Mapping AS ORM;

#[Entity]
class Form
{

    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "id", type: "integer")]
    private ?int $id;

    #[ORM\Column(name: "created_on", type: "datetime", nullable: true)]
    private ?DateTime $createdOn = null;

    #[ORM\Column(name: "updated_on", type: "datetime", nullable: true)]
    private ?DateTime $updatedOn = null;


    #[ORM\Column(name: "deleted_on", type: "datetime", nullable: true)]
    private ?DateTime $deletedOn = null;

    #[Column(name: "users", type: "json")]
    private array $users;

    #[Column(name: "project_name", type: "string")]
    private string $projectName;

    #[Column(name: "short_name", type: "string")]
    private string $shortName;

    #[Column(name: "description", type: "text", nullable: true)]
    private string $description;

    #[Column(name: "budget_type", type: "string", nullable: true)]
    private string $budgetType;

    #[Column(name: "status", type: "json", nullable: true)]
    private array $status;

    #[Column(name: "practice", type: "string", nullable: true)]
    private string $practice;

    #[Column(name: "start_date", type: "datetime", nullable: true)]
    private ?DateTime $startDate = null;

    #[Column(name: "end_date", type: "datetime", nullable: true)]
    private ?DateTime $endDate = null;

    #[Column(name: "source_type", type: "string", nullable: true)]
    private string $sourceType;

    #[Column(name: "msa_signer", type: "string", nullable: true)]
    private string $msaSigner;

    #[Column(name: "billing_entity", type: "string", nullable: true)]
    private string $billingEntity;

    #[Column(name: "industry", type: "string", nullable: true)]
    private string $industry;

    #[Column(name: "region", type: "string", nullable: true)]
    private string $region;

    public function onDelete(): static
    {
        $this->deletedOn = new DateTime();

        return $this;
    }

    #[ORM\PrePersist]
    public function onCreate(): static {
        $this->createdOn = new DateTime();

        return $this;
    }
    #[ORM\PreUpdate]
    public function onUpdate(): static {
        $this->updatedOn = new DateTime();

        return $this;
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUsers(): array
    {
        return $this->users;
    }

    public function setUsers(array $users): static
    {
        $this->users = $users;

        return $this;
    }

    public function getProjectName(): ?string
    {
        return $this->projectName;
    }

    public function setProjectName(string $projectName): static
    {
        $this->projectName = $projectName;

        return $this;
    }

    public function getShortName(): ?string
    {
        return $this->shortName;
    }

    public function setShortName(string $shortName): static
    {
        $this->shortName = $shortName;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): static
    {
        $this->description = $description;

        return $this;
    }

    public function getBudgetType(): ?string
    {
        return $this->budgetType;
    }

    public function setBudgetType(?string $budgetType): static
    {
        $this->budgetType = $budgetType;

        return $this;
    }

    public function getStatus(): ?array
    {
        return $this->status;
    }

    public function setStatus(?array $status): static
    {
        $this->status = $status;

        return $this;
    }

    public function getPractice(): ?string
    {
        return $this->practice;
    }

    public function setPractice(?string $practice): static
    {
        $this->practice = $practice;

        return $this;
    }

    public function getStartDate(): ?DateTime
    {
        return $this->startDate;
    }

    public function setStartDate(?DateTime $startDate): static
    {
        $this->startDate = $startDate;

        return $this;
    }

    public function getEndDate(): ?DateTime
    {
        return $this->endDate;
    }

    public function setEndDate(?DateTime $endDate): static
    {
        $this->endDate = $endDate;

        return $this;
    }

    public function getSourceType(): ?string
    {
        return $this->sourceType;
    }

    public function setSourceType(?string $sourceType): static
    {
        $this->sourceType = $sourceType;

        return $this;
    }

    public function getMsaSigner(): ?string
    {
        return $this->msaSigner;
    }

    public function setMsaSigner(?string $msaSigner): static
    {
        $this->msaSigner = $msaSigner;

        return $this;
    }

    public function getBillingEntity(): ?string
    {
        return $this->billingEntity;
    }

    public function setBillingEntity(?string $billingEntity): static
    {
        $this->billingEntity = $billingEntity;

        return $this;
    }

    public function getIndustry(): ?string
    {
        return $this->industry;
    }

    public function setIndustry(?string $industry): static
    {
        $this->industry = $industry;

        return $this;
    }

    public function getRegion(): ?string
    {
        return $this->region;
    }

    public function setRegion(?string $region): static
    {
        $this->region = $region;

        return $this;
    }
}
