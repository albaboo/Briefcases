<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('projects', function (Blueprint $table) {
            $table->id();
            $table->json('users');
            $table->string('project_name');
            $table->string('short_name');
            $table->text('description');
            $table->string('budget_type');
            $table->json('status');
            $table->string('practice');
            $table->dateTime('start_date');
            $table->dateTime('end_date');
            $table->string('source_type');
            $table->string('msa_signer');
            $table->string('billing_entity');
            $table->string('industry');
            $table->string('region');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('projects');
    }
};
