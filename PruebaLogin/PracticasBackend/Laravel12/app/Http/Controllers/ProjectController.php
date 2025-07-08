<?php

namespace App\Http\Controllers;

use App\Models\Project;
use App\Models\User;
use Database\Factories\ProjectFactory;
use DateTime;
use Exception;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;

class ProjectController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(int $id): JsonResponse
    {
        try {
             $allProjects = Project::all();

             if (!$allProjects)
                 return response()->json(['message' => 'Projects not found'], 404);


             $projects = [];

             foreach ($allProjects as $project) {

                 if (in_array($id, $project->users))
                     $projects[] = $project;

             }

             foreach ($projects as $project) {

                 $usernames = [];

                 foreach ($project->users as $user) {
                     $u = User::where('id', $user)->first();
                     $usernames[] = $u->username;
                 }

                 $project->users = $usernames;

                 if ($project->description == '')
                     $project->description = 'No description';

             }

             return response()->json($projects);

        } catch (Exception $exception) {
            return response()->json(['message' => $exception->getMessage()], 500);
        }
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request): JsonResponse
    {
        try {
            $data = json_decode($request->getContent(), true);
            $projectFactory = new ProjectFactory();
            if (!$data['project_name'] || !$data['short_name'] || !$data['users'])
                return response()->json(['message' => 'not required data'], 422);

            if (!empty($data['account'])) {
                foreach ($data['account'] as $user) {
                    if ($user != 0) {
                        $data['users'][] = $user;
                    }
                }
            }

            foreach ($data['users'] as &$user) {
                $u = User::where('username', $user)->whereNull('deleted_on')->first();

                if (!$u)
                    return response()->json(['message' => 'User not found'], 404);

                $user = $u->id;
            }

            $sD = (new DateTime($data['start_date']))->format('Y-m-d');
            $sT = (new DateTime($data['start_time']))->format('H:i:s');
            $eD = (new DateTime($data['end_date']))->format('Y-m-d');
            $eT = (new DateTime($data['end_time']))->format('H:i:s');

            $info = [
                'users' => $data['users'],
                'project_name' => $data['project_name'],
                'short_name' => $data['short_name'],
                'description' => $data['description'],
                'budget_type' => $data['budget_type'],
                'status' => $data['status'],
                'practice' => $data['practice'],
                'start_date' => new DateTime($sD . ' ' . $sT),
                'end_date' =>  new DateTime($eD. ' ' . $eT),
                'source_type' => $data['source_type'],
                'msa_signer' => $data['msa_signer'],
                'billing_entity' => $data['billing_entity'],
                'industry' =>  $data['industry'],
                'region' =>  $data['region'],

            ];

            $project = $projectFactory->create($info);
            return response()->json(['message' => 'Project created', 'project' => $project]);

        } catch (Exception $exception) {
            return response()->json(['message' => $exception->getMessage()], 500);
        }

    }

    /**
     * Display the specified resource.
     */
    public function show(int $id): JsonResponse
    {
        try {
            $project = Project::where('id', $id)->first();

            if (!$project)
                return response()->json(['message' => 'Project not found'], 404);

            $usernames = [];
            foreach ($project->users as $user) {
                $u = User::where('id', $user)->first();
                $usernames[] = $u->username;
            }
            $project->users = $usernames;

            if ($project->description == '')
                $project->description = 'No description';

            return response()->json($project);

        } catch (Exception $exception) {
            return response()->json(['message' => $exception->getMessage()], 500);
        }
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Project $project)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Project $project)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Project $project)
    {
        //
    }
}
