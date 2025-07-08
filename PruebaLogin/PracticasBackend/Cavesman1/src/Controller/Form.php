<?php

namespace App\Controller;

use App\Model\CompleteForm;
use App\Model\ReceivedForm;
use App\Model\StandardForm;
use Cavesman\Db;
use Cavesman\Http\JsonResponse;
use DateTime;
use Doctrine\ORM\Exception\ORMException;
use Exception;

class Form
{
    public static function create(): ?JsonResponse
    {
        try {
            $formRequest = ReceivedForm::fromRequest();

            if (!$formRequest->users || !$formRequest->projectName || !$formRequest->shortName)
                return new JsonResponse(['message' => 'not required data'], 400);

            foreach ($formRequest->users as &$user) {

                $u = \App\Entity\User::findOneBy(['username' => $user, 'deletedOn' => null]);

                if (!$u)
                    return new JsonResponse(['message' => 'User not found', $user], 404);


                $user = $u->id;
            }

            if ($formRequest->startDate && $formRequest->endDate) {
                $start = $formRequest->startDate->format('Y-m-d');
                $end = $formRequest->endDate->format('Y-m-d');

                $formRequest->startDate = new DateTime("$start $formRequest->startTime");
                $formRequest->endDate = new DateTime("$end $formRequest->endTime");
            }


            $entity = $formRequest->entity();

            $em = Db::getManager();
            $em->persist($entity);
            $em->flush();

            return new JsonResponse(['message' => 'New project created', 'form' => $entity->model(CompleteForm::class)->json()]);

        } catch (Exception|ORMException $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }

    }

    public static function list(int $id): ?JsonResponse
    {
        try {
            $allProjects = \App\Entity\Form::findBy(['deletedOn' => null]);

            if (!$allProjects)
                return new JsonResponse(['message' => 'Projects not found'], 404);

            $projects = [];


            foreach ($allProjects as $project) {

                if (in_array($id, $project->users))
                    $projects[] = $project;

            }

            foreach ($projects as &$project) {

                foreach ($project->users as &$user) {
                    $u = \App\Entity\User::findOneBy(['id' => $user]);
                    $user = $u->username;
                }

                if ($project->description == '')
                    $project->description = 'No description';

                $project = $project->model(StandardForm::class)->json();
            }

            return new JsonResponse($projects);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }

    public function get(int $id): ?JsonResponse
    {
        try {
            $project = \App\Entity\Form::findOneBy(['id' => $id, 'deletedOn' => null]);

            if (!$project)
                return new JsonResponse(['message' => 'Project not found'], 404);

            foreach ($project->users as &$user) {
                $u = \App\Entity\User::findOneBy(['id' => $user]);
                $user = $u->username;
            }

            if ($project->description == '')
                $project->description = 'No description';

            return new JsonResponse($project->model(CompleteForm::class)->json());

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }

    }
}