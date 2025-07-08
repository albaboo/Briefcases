<?php

namespace Database\Factories;

use App\Models\Project;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Database\Eloquent\Model;

/**
 * @extends Factory<Project>
 */
class ProjectFactory extends Factory
{
    /**
     * The name of the factory's corresponding model.
     *
     * @var class-string<Model>
     */
    protected $model = Project::class;

    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return  [
            'users' => [$this->faker->numberBetween(1,10)],
            'project_name' => $this->faker->words(),
            'short_name' => $this->faker->word(),
            'description' => $this->faker->paragraph(),
            'budget_type' => $this->faker->word,
            'status' => [$this->faker->word()],
            'practice' => $this->faker->word,
            'start_date' => $this->faker->dateTime(),
            'end_date' => $this->faker->dateTime(),
            'source_type' => $this->faker->word(),
            'msa_signer' => $this->faker->name,
            'billing_entity' => $this->faker->company(),
            'industry' => $this->faker->word(),
            'region' => $this->faker->country(),
        ];
    }
}
