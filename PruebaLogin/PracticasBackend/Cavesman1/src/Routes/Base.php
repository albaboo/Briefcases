<?php

use App\Controller\User;
use Cavesman\Http\JsonResponse;
use Cavesman\Router;
use App\Middleware\Base as BaseMiddleware;

Router::set404(fn() => new JsonResponse(['message' => 'Route Not Found'], 404));