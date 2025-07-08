<?php

namespace App\Entity;

use DateTime;
use Doctrine\ORM\Mapping\Column;
use Doctrine\ORM\Mapping\Entity;
use Doctrine\ORM\Mapping AS ORM;

#[Entity]
class User
{

    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "id", type: "integer")]
    public ?int $id;

    #[ORM\Column(name: "created_on", type: "datetime", nullable: true)]
    public ?DateTime $createdOn = null;

    #[ORM\Column(name: "updated_on", type: "datetime", nullable: true)]
    public ?DateTime $updatedOn = null;


    #[ORM\Column(name: "deleted_on", type: "datetime", nullable: true)]
    public ?DateTime $deletedOn = null;

    #[Column(name: "username", type: "string", unique: true)]
    private string $username;

    #[Column(name: "password", type: "string", nullable: true)]
    private ?string $password = null;

    #[Column(name: "birthday", type: "date", nullable: true)]
    private DateTime $birthday;

    #[Column(name: "city", type: "string", nullable: true)]
    private  string $city;

    #[Column(name: "admin", type: "boolean", nullable: true)]
    private  string $admin;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(string $username): static
    {
        $this->username = $username;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(?string $password): static
    {
        $this->password = $password;

        return $this;
    }

    public function getBirthday(): ?DateTime
    {
        return $this->birthday;
    }

    public function setBirthday(?DateTime $birthday): static
    {
        $this->birthday = $birthday;

        return $this;
    }

    public function getCity(): ?string
    {
        return $this->city;
    }

    public function setCity(?string $city): static
    {
        $this->city = $city;

        return $this;
    }

    public function isAdmin(): ?bool
    {
        return $this->admin;
    }

    public function setAdmin(?bool $admin): static
    {
        $this->admin = $admin;

        return $this;
    }

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

}
