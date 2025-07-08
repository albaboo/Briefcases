<?php

use App\Controller\Form;
use App\Controller\User;
use App\Middleware\Base;

Cavesman\Router::mount('/api', function() {

    Cavesman\Router::get('/', User::class . '@login');

    /** @see User::login() */
    Cavesman\Router::post('/login', User::class . '@login');

    /** @see User::check() */
    Cavesman\Router::get('/check', User::class.'@check');

});

Cavesman\Router::mount('/api/user', function() {
    /** @see User::register() */
    Cavesman\Router::post('/', User::class . '@register');

    /** @see User::list() */
    Cavesman\Router::get('/', User::class . '@list', Base::class.'@auth');

    /** @see User::get() */
    Cavesman\Router::get('/(\d+)', User::class . '@get', Base::class.'@auth');

    /** @see User::delete() */
    Cavesman\Router::delete('/(\d+)', User::class . '@delete', Base::class.'@auth');

    /** @see User::update() */
    Cavesman\Router::put('/(\d+)', User::class . '@update', Base::class.'@auth');

});

Cavesman\Router::mount('/api/form', function() {
    /** @see Form::create() */
    Cavesman\Router::post('/', Form::class . '@create', Base::class.'@auth');

    /** @see Form::list() */
    Cavesman\Router::get('/(\d+)', Form::class . '@list', Base::class.'@auth');

    /** @see Form::get() */
    Cavesman\Router::get('/project/(\d+)', Form::class . '@get', Base::class.'@auth');
});