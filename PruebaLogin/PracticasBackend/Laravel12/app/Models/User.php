<?php

namespace App\Models;

use Database\Factories\UserFactory;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 *
 * @method static Builder|static find($id)
 * @method static Builder|static where(string $column, mixed $value)
 */
class User extends Model
{
    /** @use HasFactory<UserFactory> */
    use HasFactory;

    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'user';

    const CREATED_AT = 'created_on';
    const UPDATED_AT = 'updated_on';

    protected $casts = [
        'id' => 'integer',
        'birthday' => 'datetime:Y-m-d',
        'admin' => 'boolean',
        'deleted_on' => 'datetime',
    ];
}
