<?php

namespace App\Middleware;


use App\Entity\User;
use App\Model\Profile;
use Cavesman\Http\JsonResponse;
use Cavesman\JWT;
use Cavesman\Request;
use Exception;

class Base {

    public static function auth(): JsonResponse|\Cavesman\Db\Doctrine\Model\Base
    {

        $token = substr(Request::header('Authorization', ''), 7);

        try {
            $decode = JWT::decode($token);
            $user = User::findOneBy(['id' => $decode->id, 'deletedOn' => null]);

            if(!$user)
                return new JsonResponse(['message' => 'User not found'], 404);

            return $user->model(Profile::class);

        }catch(Exception $exception){
            return new JsonResponse(['message' => $exception->getMessage(), 'token' => $token], 403);
        }

    }

}