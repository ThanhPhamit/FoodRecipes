package com.example.foodrecipes;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.foodrecipes.Listeners.FoodTriviaListener;
import com.example.foodrecipes.Listeners.InstructionsListener;
import com.example.foodrecipes.Listeners.RandomRecipeResponseListener;
import com.example.foodrecipes.Listeners.RecipeCardResponeListener;
import com.example.foodrecipes.Listeners.RecipeDetailsListener;
import com.example.foodrecipes.Listeners.SimilarRecipesListener;
import com.example.foodrecipes.Models.FoodTrivia;
import com.example.foodrecipes.Models.Instruction;
import com.example.foodrecipes.Models.RandomRecipeApiResponse;
import com.example.foodrecipes.Models.Recipe;
import com.example.foodrecipes.Models.RecipeCardRespone;
import com.example.foodrecipes.Models.RecipeDetailsResponse;
import com.example.foodrecipes.Models.SimilarRecipe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    //    Get Random Recipes
    public void getRandomRecipes(RandomRecipeResponseListener listener, ArrayList<String> tags) {
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse<Recipe>> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "15", tags);
        call.enqueue(new Callback<RandomRecipeApiResponse<Recipe>>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse<Recipe>> call, Response<RandomRecipeApiResponse<Recipe>> response) {
                if (!response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        listener.didError(object.getString("message"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse<Recipe>> call, Throwable t) {
                listener.didError((t.getMessage()));
            }
        });
    }

    //    Get Recipe details
    public void getRecipeDetails(RecipeDetailsListener listener, int id) {
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        listener.didError(object.getString("message"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    //    Get similar recipes
    public void getSimilarRecipes(SimilarRecipesListener listener, int id) {
        CallSimilarRecipes callSimilarRecipes = retrofit.create(CallSimilarRecipes.class);
        Call<ArrayList<SimilarRecipe>> call = callSimilarRecipes.callSimilarRecipes(id, "10", context.getString(R.string.api_key));
        call.enqueue(new Callback<ArrayList<SimilarRecipe>>() {
            @Override
            public void onResponse(Call<ArrayList<SimilarRecipe>> call, Response<ArrayList<SimilarRecipe>> response) {
                if (!response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        listener.didError(object.getString("message"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<ArrayList<SimilarRecipe>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    //    Get instructions
    public void getInstructions(InstructionsListener listener, int id) {
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<ArrayList<Instruction>> call = callInstructions.callInstruction(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<ArrayList<Instruction>>() {
            @Override
            public void onResponse(Call<ArrayList<Instruction>> call, Response<ArrayList<Instruction>> response) {
                if (!response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        listener.didError(object.getString("message"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<ArrayList<Instruction>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    //   Get food trivia
    public void getFoodTrivia(FoodTriviaListener listener) {
        CallFoodTrivia callFoodTrivia = retrofit.create(CallFoodTrivia.class);
        Call<FoodTrivia> call = callFoodTrivia.callFoodTrivia(context.getString(R.string.api_key));
        call.enqueue(new Callback<FoodTrivia>() {
            @Override
            public void onResponse(Call<FoodTrivia> call, Response<FoodTrivia> response) {
                if (!response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        listener.didError(object.getString("message"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<FoodTrivia> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    //Get recipe card
    public void getRecipeCard(RecipeCardResponeListener listener, int id) {
        CallRecipeCard callRecipeCard = retrofit.create(CallRecipeCard.class);
        Call<RecipeCardRespone> call = callRecipeCard.callRecipeCard(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeCardRespone>() {
            @Override
            public void onResponse(Call<RecipeCardRespone> call, Response<RecipeCardRespone> response) {
                if (!response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        listener.didError(object.getString("message"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeCardRespone> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }


    //INTERFACE
    private interface CallRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipeApiResponse<Recipe>> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") ArrayList<String> tags
        );
    }

    private interface CallRecipeDetails {
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallSimilarRecipes {
        @GET("recipes/{id}/similar")
        Call<ArrayList<SimilarRecipe>> callSimilarRecipes(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallInstructions {
        @GET("recipes/{id}/analyzedInstructions")
        Call<ArrayList<Instruction>> callInstruction(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallFoodTrivia {
        @GET("food/trivia/random")
        Call<FoodTrivia> callFoodTrivia(
                @Query("apiKey") String apiKey
        );
    }

    private interface CallRecipeCard {
        @GET("recipes/{id}/card")
        Call<RecipeCardRespone> callRecipeCard(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
