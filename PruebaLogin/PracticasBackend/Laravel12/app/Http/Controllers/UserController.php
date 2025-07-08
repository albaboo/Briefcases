<?php

namespace App\Http\Controllers;

use App\Models\User;
use Database\Factories\UserFactory;
use Exception;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Hash;

class UserController extends Controller
{

    public function check(Request $request): JsonResponse | Response
    {
        try {
            $auth = $request->header('Authorization');

            $token = str_replace('Bearer ', '', $auth);

            $decode = json_decode($token, true);

            $user = User::where('id', $decode['id'])->whereNull('deleted_on')->first();

            if (!$user)
                return response()->json(['message' => 'User not found'], 404);

            return response($user);

        } catch (Exception $exception) {
            return response()->json(['message' => $exception->getMessage()], 500);
        }
    }
    public function login(Request $request): JsonResponse
    {
        try {
            if (!$request['username'] || !$request['password'])
                return response()->json(['message' => 'Username and password are required'], 422);


            $user = User::where('username', $request['username'])->whereNull('deleted_on')->first();

            if (!$user)
                return response()->json(['message' => 'User not found'], 404);
            elseif (!Hash::check($request['password'], $user->password))
                return response()->json(['message' => 'Wrong password'], 403);

            $token = json_encode(['id' => $user->id]);

            return response()->json(['message' => 'Login successfully','token' => $token, 'user' => $user]);
        } catch (Exception $exception) {
            return response()->json(['message' => $exception->getMessage()], 500);
        }

    }
    /**
     * Display a listing of the resource.
     */
    public function index(): JsonResponse
    {
        try {
            $users = User::where('deleted_on', null)->get();

            if (count($users) === 0)
                return response()->json(['message' => 'Users not found'], 404);

            return response()->json($users);
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
        try{

            $data = json_decode($request->getContent(), true);
            $userFactory = new UserFactory();

            if (!$data['username'] || !$data['password'])
                return response()->json(['message' => 'Username and password are required'], 422);


            $user =  User::where('username', $data['username'])->first();

            if($user)
                return response()->json(['message' => 'Username already exists'], 400);


            $data['password'] = Hash::make($request['password']);

            $user = $userFactory->create($data);
            $user->save();

            $token = json_encode(['id' => $user->id]);

            return response()->json(['message' => 'Register successfully', 'token' => $token, 'user' => $user]);
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

            $user = User::where('id', $id)->whereNull('deleted_on')->first();

            if (!$user)
                return response()->json(['message' => 'User not found'], 404);

            return response()->json($user);

        } catch (Exception $exception) {
            return response()->json(['message' => $exception->getMessage()], 500);
        }
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(User $user)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, int $id): JsonResponse
    {
        try {
            $data = json_decode($request->getContent(), true);

            $user = User::where('id', $id)->whereNull('deleted_on')->first();

            if (!$user)
                return response()->json(['message' => 'User not found'], 404);

            $anotherUser = User::where('username', $data['username'])->first();

            if ($anotherUser)
                if ($user->id != $anotherUser->id)
                    return response()->json(['message' => 'Username already exists'], 400);

            if ($user->password != $data['password'])
                $data['password'] = Hash::make($data['password']);

            $user->username = $data['username'];
            $user->password = $data['password'];
            $user->city = $data['city'];
            $user->birthday = $data['birthday'];

            $user->save();

            return response()->json(['message' => 'Update successfully', 'user' => $user]);

        } catch (Exception $exception) {
            return response()->json(['message' => $exception->getMessage()], 500);
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(int $id): JsonResponse
    {
        try {
            $user = User::where('id', $id)->first();

            if (!$user)
                return response()->json(['message' => 'User not found'], 404);

            $user->deleted_on = now();

            $user->save();

            return response()->json(['message' => 'User deleted successfully', 'user' => $user]);


        } catch (Exception $exception) {
            return response()->json(['message' => $exception->getMessage()], 500);
        }
    }
}
