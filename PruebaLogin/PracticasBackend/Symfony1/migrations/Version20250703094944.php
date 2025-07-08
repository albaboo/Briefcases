<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250703094944 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE form DROP created_on, DROP updated_on, DROP deleted_on');
        $this->addSql('ALTER TABLE user DROP created_on, DROP updated_on, DROP deleted_on');
        $this->addSql('ALTER TABLE user RENAME INDEX uniq_2da17977f85e0677 TO UNIQ_8D93D649F85E0677');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE form ADD created_on DATETIME DEFAULT NULL, ADD updated_on DATETIME DEFAULT NULL, ADD deleted_on DATETIME DEFAULT NULL');
        $this->addSql('ALTER TABLE user ADD created_on DATETIME DEFAULT NULL, ADD updated_on DATETIME DEFAULT NULL, ADD deleted_on DATETIME DEFAULT NULL');
        $this->addSql('ALTER TABLE user RENAME INDEX uniq_8d93d649f85e0677 TO UNIQ_2DA17977F85E0677');
    }
}
