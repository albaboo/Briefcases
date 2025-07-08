<?php

namespace App\Controller;

use App\Entity\Form;
use App\Entity\User;
use App\Model\CompleteForm;
use App\Model\ReceivedForm;
use App\Model\StandardForm;
use DateTime;
use Doctrine\ORM\EntityManagerInterface;
use Exception;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Attribute\Route;

final class FormController extends AbstractController
{
    #[Route('/api/form', name: 'app_create', methods: ['POST'])]
    public function create(Request $request, EntityManagerInterface $em): JsonResponse
    {
        try{
            $data = json_decode($request->getContent(), true);
            $formRequest = new ReceivedForm();
            $formRequest->projectName = $data['projectName'];
            $formRequest->shortName = $data['shortName'];
            $formRequest->description = $data['description'];
            $formRequest->users = $data['users'];
            $formRequest->sourceType = $data['sourceType'];
            $formRequest->msaSigner = $data['msaSigner'];
            $formRequest->endDate = new DateTime($data['endDate']);
            $formRequest->endTime =  new DateTime($data['endTime']);
            $formRequest->budgetType = $data['budgetType'];
            $formRequest->status = $data['status'];
            $formRequest->practice = $data['practice'];
            $formRequest->account = $data['account'];
            $formRequest->startDate =  new DateTime($data['startDate']);
            $formRequest->startTime = new DateTime($data['startTime']);
            $formRequest->billingEntity = $data['billingEntity'];
            $formRequest->industry = $data['industry'];
            $formRequest->region = $data['region'];

            if(!$formRequest->projectName || !$formRequest->shortName|| !$formRequest->users)
                return new JsonResponse(['message' => 'not required data'], 400);


            foreach ($formRequest->account as $user) {
                if ($user != 0)
                    $formRequest->users[] = $user;
            }

             foreach ($formRequest->users as &$user) {
                 $u = $em->getRepository(User::class)->findOneBy(['username' => $user, 'deletedOn' => null]);

                 if(!$u)
                     return new JsonResponse(['message' => 'User not found'], 400);

                 $user = $u->getId();
             }

            $sD = $formRequest->startDate->format('Y-m-d');
            $eD = $formRequest->endDate->format('Y-m-d');
            $sT = $formRequest->startTime->format('H:i');
            $eT = $formRequest->endTime->format('H:i');

            $formRequest->startDate = new DateTime("$sD $sT");
            $formRequest->endDate = new DateTime("$eD $eT");

            $entity = new Form();
            $entity->setProjectName($formRequest->projectName);
            $entity->setShortName($formRequest->shortName);
            $entity->setDescription($formRequest->description);
            $entity->setUsers($formRequest->users);
            $entity->setSourceType($formRequest->sourceType);
            $entity->setMsaSigner($formRequest->msaSigner);
            $entity->setStartDate($formRequest->startDate);
            $entity->setEndDate($formRequest->endDate);
            $entity->setBudgetType($formRequest->budgetType);
            $entity->setStatus($formRequest->status);
            $entity->setPractice($formRequest->practice);
            $entity->setBillingEntity($formRequest->billingEntity);
            $entity->setIndustry($formRequest->industry);
            $entity->setRegion($formRequest->region);
            $entity->onCreate();
            $em->persist($entity);
            $em->flush();

            $complete = new CompleteForm();
            $complete->projectName = $entity->getProjectName();
            $complete->shortName = $entity->getShortName();
            $complete->description = $entity->getDescription();
            $complete->users = $entity->getUsers();
            $complete->budgetType = $entity->getBudgetType();
            $complete->status = $entity->getStatus();
            $complete->practice = $entity->getPractice();
            $complete->startDate = $entity->getStartDate()->format('Y-m-d');
            $complete->endDate = $entity->getEndDate()->format('Y-m-d');
            $complete->sourceType = $entity->getSourceType();
            $complete->msaSigner = $entity->getMsaSigner();
            $complete->billingEntity = $entity->getBillingEntity();
            $complete->industry = $entity->getIndustry();
            $complete->region = $entity->getRegion();

            return new JsonResponse(['message'=> 'New project created', 'form' => $complete]);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }

    #[Route('api/form/{id}', name: 'app_list_form', methods: ['GET'])]
    public static function list(int $id, EntityManagerInterface $em): JsonResponse
    {
        try {
            $allProjects = $em->getRepository(Form::class)->findBy(['deletedOn' => null]);

            if(!$allProjects)
                return new JsonResponse(['message' => 'Projects not found'], 404);

            $projects = [];

            foreach ($allProjects as $project) {

                if (in_array($id, $project->getUsers()))
                    $projects[] = $project;

            }

            foreach ($projects as &$project) {

                for ($j = 0; $j< count($project->getUsers()); $j++) {
                    $u = $em->getRepository(User::class)->findOneBy(['id' => $project->getUsers()[$j]]);
                    $users = $project->getUsers();
                    $users[$j] = $u->getUsername();
                    $project->setUsers($users);
                }

                if ($project->getDescription() == '')
                    $project->setDescription('No description');

                $standard = new StandardForm();
                $standard->projectName = $project->getProjectName();
                $standard->shortName = $project->getShortName();
                $standard->description = $project->getDescription();
                $standard->users = $project->getUsers();
                $standard->id = $project->getId();
                $project = $standard;
            }

            return new JsonResponse($projects);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }

    #[Route('api/form/project/{id}', name: 'app_get_form', methods: ['GET'])]
    public function get(int $id, EntityManagerInterface $em): JsonResponse
    {
        try {
            $project = $em->getRepository(Form::class)->findOneBy(['id' => $id, 'deletedOn' => null]);

            if(!$project)
                return new JsonResponse(['message' => 'Project not found'], 404);

            for ($j = 0; $j< count($project->getUsers()); $j++) {
                $u = $em->getRepository(User::class)->findOneBy(['id' => $project->getUsers()[$j]]);
                $users = $project->getUsers();
                $users[$j] = $u->getUsername();
                $project->setUsers($users);
            }

            if ($project->getDescription() == '')
                $project->setDescription('No description');

            $complete = new CompleteForm();
            $complete->projectName = $project->getProjectName();
            $complete->shortName = $project->getShortName();
            $complete->description = $project->getDescription();
            $complete->users = $project->getUsers();
            $complete->budgetType = $project->getBudgetType();
            $complete->status = $project->getStatus();
            $complete->practice = $project->getPractice();
            if ($project->getStartDate() && $project->getEndDate()) {
                $complete->startDate = $project->getStartDate()->format('Y-m-d H:s');
                $complete->endDate = $project->getEndDate()->format('Y-m-d H:s');
            }
            $complete->sourceType = $project->getSourceType();
            $complete->msaSigner = $project->getMsaSigner();
            $complete->billingEntity = $project->getBillingEntity();
            $complete->industry = $project->getIndustry();
            $complete->region = $project->getRegion();

            return new JsonResponse($complete);

        } catch (Exception $exception) {
            return new JsonResponse(['message' => $exception->getMessage()], 500);
        }
    }
}
