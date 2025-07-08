<?php

use App\Http\Controllers\ProjectController;
use App\Http\Controllers\UserController;
use Illuminate\Support\Facades\Route;

Route::get('/', [UserController::class, 'login']);


Route::get('/check', [UserController::class, 'check']);


Route::post('/login', [UserController::class, 'login']);


Route::post('/user', [UserController::class, 'store']);


Route::get('/user', [UserController::class, 'index']);


Route::get('/user/{id}', [UserController::class, 'show']);


Route::delete('/user/{id}', [UserController::class, 'destroy']);


Route::put('/user/{id}', [UserController::class, 'update']);


Route::post('/project', [ProjectController::class, 'store']);


Route::get('/projects/{id}', [ProjectController::class, 'index']);


Route::get('/projects/project/{id}', [ProjectController::class, 'show']);
