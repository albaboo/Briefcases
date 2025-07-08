<?php

namespace App\Controller;

use App\Middleware\Base;
use App\Model\Login;
use App\Model\Profile;
use App\Model\Register;
use Cavesman\Db;
use Cavesman\Http\JsonResponse;
use Cavesman\JWT;
use DateTime;
use Doctrine\ORM\Exception\ORMException;
use Exception;

class User {
    public static function login(): ?JsonResponse
    {
        try {
            $userRequest = Login::fromRequest();

            if (!$userRequest->username || !$userRequest->password)
                return new JsonResponse(['message' => 'not username or password'], 400);

            $entity = \App\Entity\User::findOneBy(['username' => $userRequest->username, 'deletedOn' => null]);


            if (!$entity)
                return new JsonResponse(['message' => 'User not found'], 404);
            elseif (!password_verify($userRequest->password, $entity->password))
                return new JsonResponse(['message' => 'Wrong password'], 403);


            $token = JWT::encode(['id' => $entity->id]);

            return new JsonResponse(['message' => 'You\'re now logged', 'token' => $token, 'user' => $entity->model(Profile::class)->json()]);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }
    public static function register(): ?JsonResponse
    {
        try {

            $userRequest = Register::fromRequest();

            if (!$userRequest->username || !$userRequest->password)
                return new JsonResponse(['message' => 'not username or password'], 400);

            $user = \App\Entity\User::findOneBy(['username' => $userRequest->username]);

            if ($user)
                return new JsonResponse(['message' => 'Username already exists'], 400);

            $userRequest->password = password_hash($userRequest->password, PASSWORD_BCRYPT);

            $entity = $userRequest->entity();

            $em = Db::getManager();
            $em->persist($entity);
            $em->flush();
            
            $token = JWT::encode(['id' => $entity->id]);

            return new JsonResponse(['message' => 'You\'re now registered', 'token' => $token, 'user' => $entity->model(Profile::class)->json()]);

        } catch (Exception | ORMException $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }

    }

    public function check(): ?JsonResponse
    {
        try {

            /** @var \App\Model\User $user */
            $user = Base::auth();
            return new JsonResponse($user->json());

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }


    }

    public function get(int $id): ?JsonResponse
    {
        try {
            $user = \App\Entity\User::findOneBy(['id' => $id, 'deletedOn' => null]);

            if (!$user)
                return new JsonResponse(['message' => 'User not found'], 404);

            return new JsonResponse($user->model(Profile::class)->json());

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }

    }

    public function list(): ?JsonResponse
    {
        try {
            $users = \App\Entity\User::findBy(['deletedOn' => null]);

            if (!$users)
                return new JsonResponse(['message' => 'Users not found'], 404);

            foreach ($users as &$user) {
                $user = $user->model(Profile::class)->json();
            }

            return new JsonResponse($users);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }

    }

    public function delete(int $id): ?JsonResponse
    {
        try{

            $entity = \App\Entity\User::findOneBy(['id' => $id]);

            if (!$entity)
                return new JsonResponse(['message' => 'User not found'], 404);

            $entity->deletedOn = new DateTime();

            $em = Db::getManager();
            $em->persist($entity);
            $em->flush();

            return new JsonResponse(['message' => 'User deleted', 'user' => $entity->model(Profile::class)->json()]);

        } catch (Exception | ORMException $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }

    }

    public function update(int $id): ?JsonResponse
    {
        try {
            $userRequest = Profile::fromRequest();

            $em = Db::getManager();
            $entity = $em->getRepository(\App\Entity\User::class)->findOneBy(['id' => $id]);

            if (!$entity)
                return new JsonResponse(['message' => 'User not found'], 404);

            if ($entity->id != $userRequest->id)
                return new JsonResponse(['message' => 'Inconsistent request ID'], 400);

            $entity = $userRequest->entity();

            $anotherUser = \App\Entity\User::findOneBy(['username' => $userRequest->username]);

            if ($anotherUser) {
                if ($entity->id != $anotherUser->id)
                    return new JsonResponse(['message' => 'Username already exists'], 400);
            }
            $em->persist($entity);
            $em->flush();

            return new JsonResponse(['message' => 'Your profile was updated', 'user'=> $entity->model(Profile::class)->json()]);

        } catch (Exception | ORMException $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }
}