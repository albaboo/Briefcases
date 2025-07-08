<?php

namespace App\Models;

use Database\Factories\ProjectFactory;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 *
 * @method static Builder|static find($id)
 * @method static Builder|static where(string $column, mixed $value)
 * @method static Model|static create(array $attributes = [])
 */
class Project extends Model
{
    /** @use HasFactory<ProjectFactory> */
    use HasFactory;
    protected $table = 'projects';

    protected $fillable = [
        'users',
        'project_name',
        'short_name',
        'description',
        'budget_type',
        'status',
        'practice',
        'start_date',
        'end_date',
        'source_type',
        'msa_signer',
        'billing_entity',
        'industry',
        'region',
    ];

    protected $casts = [
        'users' => 'array',
        'status' => 'array',
        'start_date' => 'datetime',
        'end_date' => 'datetime',
    ];
}
