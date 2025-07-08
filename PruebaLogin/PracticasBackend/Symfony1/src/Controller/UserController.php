<?php

namespace App\Controller;

use App\Entity\User;
use App\Model\Login;
use App\Model\Profile;
use App\Model\Register;
use DateTime;
use Doctrine\ORM\EntityManagerInterface;
use Exception;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Attribute\Route;

final class UserController extends AbstractController
{
    #[Route('/api', name: 'app_home', methods: ['GET'])]
    #[Route('/api/login', name: 'app_login', methods: ['POST'])]
    public static function login(Request $request, EntityManagerInterface $em): JsonResponse
    {
        try {
            $data = json_decode($request->getContent(), true);

            $userRequest = new Login();
            $userRequest->username = $data['username'];
            $userRequest->password = $data['password'];

            if (!$userRequest->username || !$userRequest->password)
                return new JsonResponse(['message' => 'not username or password'], 400);


            $entity = $em->getRepository(User::class)->findOneBy(['username' => $userRequest->username, 'deletedOn' => null]);


            if (!$entity)
                return new JsonResponse(['message' => 'User not found'], 404);
            elseif (!password_verify($userRequest->password, $entity->getPassword()))
                return new JsonResponse(['message' => 'Wrong password'], 403);


            $token = json_encode(['id' => $entity->getId()]);

            $profile = new Profile();
            $profile->id = $entity->getId();
            $profile->username = $entity->getUsername();
            $profile->birthday = $entity->getBirthday()->format('Y-m-d');
            $profile->city = $entity->getCity();
            $profile->admin = $entity->isAdmin();

            return new JsonResponse(['message' => 'You\'re now logged', 'token' => $token, 'user' => $profile]);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }

    #[Route('/api/user', name: 'app_register', methods: ['POST'])]
    public function register(Request $request, EntityManagerInterface $em): JsonResponse
    {
        try {

            $data = json_decode($request->getContent(), true);
            $userRequest = new Register();
            $userRequest->username = $data['username'];
            $userRequest->password = $data['password'];
            $userRequest->birthday = $data['birthday'];
            $userRequest->city = $data['city'];
            $userRequest->admin = false;

            if (!$userRequest->username || !$userRequest->password)
                return new JsonResponse(['message' => 'not username or password'], 400);


            $user = $em->getRepository(User::class)->findOneBy(['username' => $userRequest->username]);

            if ($user)
                return new JsonResponse(['message' => 'Username already exists'], 400);

            $userRequest->password = password_hash($userRequest->password, PASSWORD_BCRYPT);

            $entity = new User();

            $entity->setUsername($userRequest->username);
            $entity->setPassword($userRequest->password);
            $entity->setBirthday(new DateTime($userRequest->birthday));
            $entity->setCity($userRequest->city);
            $entity->setAdmin($userRequest->admin);
            $entity->onCreate();

            $em->persist($entity);
            $em->flush();
            $token = json_encode(['id' => $entity->getId()]);

            $profile = new Profile();
            $profile->id = $entity->getId();
            $profile->username = $entity->getUsername();
            $profile->birthday = $entity->getBirthday()->format('Y-m-d');
            $profile->city = $entity->getCity();
            $profile->admin = $entity->isAdmin();

            return new JsonResponse(['message' => 'You\'re now registered', 'token' => $token, 'user' => $profile]);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }

    #[Route('/api/check', name: 'app_check', methods: ['GET'])]
    public function check(Request $request, EntityManagerInterface $em): JsonResponse
    {
        try {
            $auth = $request->headers->get('Authorization');

            // Para quitar 'Bearer '
            $token = substr($auth, 7);

            $decode = json_decode($token);

            $user = $em->getRepository(User::class)->findOneBy(['id' => $decode->id, 'deletedOn' => null]);

            if(!$user)
                return new JsonResponse(['message' => 'User not found'], 404);

            $profile = new Profile();
            $profile->id = $user->getId();
            $profile->username = $user->getUsername();
            $profile->birthday = $user->getBirthday()->format('Y-m-d');
            $profile->city = $user->getCity();
            $profile->admin = $user->isAdmin();

            return new JsonResponse($profile);
        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }

    #[Route('/api/user/{id}', name: 'app_get', methods: ['GET'])]
    public function get(int $id, EntityManagerInterface $em): JsonResponse
    {
        try {

            $user = $em->getRepository(User::class)->findOneBy(['id' => $id, 'deletedOn' => null]);

            if(!$user)
                return new JsonResponse(['message' => 'User not found'], 404);

            $profile = new Profile();
            $profile->id = $user->getId();
            $profile->username = $user->getUsername();
            $profile->birthday = $user->getBirthday()->format('Y-m-d');
            $profile->city = $user->getCity();
            $profile->admin = $user->isAdmin();

            return new JsonResponse($profile);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }

    #[Route('/api/user', name: 'app_list', methods: ['GET'])]
    public function list(EntityManagerInterface $em): JsonResponse
    {
        try {

            $users =  $em->getRepository(User::class)->findBy([ 'deletedOn' => null]);

            if(!$users)
                return new JsonResponse(['message' => 'Users not found'], 404);

            foreach ($users as &$user) {

                $profile = new Profile();
                $profile->id = $user->getId();
                $profile->username = $user->getUsername();
                $profile->birthday = $user->getBirthday()->format('Y-m-d');
                $profile->city = $user->getCity();
                $profile->admin = $user->isAdmin();

                $user = $profile;
            }

            return new JsonResponse($users);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }

    #[Route('/api/user/{id}', name: 'app_delete', methods: ['DELETE'])]
    public function delete(int $id, EntityManagerInterface $em): JsonResponse
    {
        try {

            $entity =  $em->getRepository(User::class)->findOneBy(['id' => $id]);

            if (!$entity)
                return new JsonResponse(['message' => 'User not found'], 404);

            $entity->onDelete();
            $em->persist($entity);
            $em->flush();

            $profile = new Profile();
            $profile->id = $entity->getId();
            $profile->username = $entity->getUsername();
            $profile->birthday = $entity->getBirthday()->format('Y-m-d');
            $profile->city = $entity->getCity();
            $profile->admin = $entity->isAdmin();

            return new JsonResponse(['message' => 'User deleted', 'user' => $profile]);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }

    #[Route('/api/user/{id}', name: 'app_update', methods: ['PUT'])]
    public function update(Request $request, int $id, EntityManagerInterface $em): JsonResponse
    {
        try {

            $data = json_decode($request->getContent(), true);

            // Recibe los datos del perfil
            $userRequest = new Profile();
            $userRequest->id = $data['id'];
            $userRequest->username = $data['username'];
            $userRequest->birthday = $data['birthday'];
            $userRequest->city = $data['city'];
            $userRequest->admin = $data['admin'];

            // Busca el usuario con ID recibido
            $entity = $em->getRepository(User::class)->findOneBy(['id' => $id, 'deletedOn' => null]);

            // No hay usuario
            if (!$entity)
                return new JsonResponse(['message' => 'User not found'], 404);

            // No puede actualizar porque el nuevo nombre pertenece a otro a usuario
            $anotherUser = $em->getRepository(User::class)->findOneBy(['username' => $userRequest->username]);
            if($anotherUser) {
                if($entity->getId() != $anotherUser->id)
                    return new JsonResponse(['message' => 'Username already exists'], 400);
            }

            // Ya se ha verificado el usuario y sus nuevos datos asi que se puede cambiar en la base
            $entity->setUsername($userRequest->username);
            $entity->setBirthday(new DateTime($userRequest->birthday));
            $entity->setCity($userRequest->city);
            $entity->onUpdate();
            $em->flush();

            $profile = new Profile();
            $profile->id = $entity->getId();
            $profile->username = $entity->getUsername();
            $profile->birthday = $entity->getBirthday()->format('Y-m-d');
            $profile->city = $entity->getCity();

            return new JsonResponse(['message' => 'Your profile was updated', 'user'=> $profile]);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getTraceAsString()], 500);
        }
    }

}
