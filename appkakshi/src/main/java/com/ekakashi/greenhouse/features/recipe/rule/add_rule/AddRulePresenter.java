package com.ekakashi.greenhouse.features.recipe.rule.add_rule;

import android.support.annotation.NonNull;

import com.ekakashi.greenhouse.api.APIManager;
import com.ekakashi.greenhouse.common.BasePresenter;
import com.ekakashi.greenhouse.common.Utils;
import com.ekakashi.greenhouse.database.model_server_request.EditRecipeRequest;
import com.ekakashi.greenhouse.database.model_server_response.ObjectRecipe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ptloc on 2/7/2018.
 */

public class AddRulePresenter extends BasePresenter implements AddRuleInterface.presenter {

    private AddRuleInterface.View view;

    public AddRulePresenter(AddRuleInterface.View view) {
        this.view = view;
    }

    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void editRecipeById(int recipeId, EditRecipeRequest recipeRequest) {
        APIManager.editRecipeById(recipeId, recipeRequest, new Callback<ObjectRecipe>() {
            @Override
            public void onResponse(@NonNull Call<ObjectRecipe> call, @NonNull Response<ObjectRecipe> response) {
                if (view != null) {
                    if (response.code() == Utils.ErrorCode.SUCCESS_200) {
                        view.editSuccess(response.body());
                    } else {
                        if (response.code() == Utils.ErrorCode.ERROR_401) {
                            view.tokenExpired();
                        } else {
                            String errorMessage = getErrorMessage(response.errorBody());
                            if (errorMessage != null && !errorMessage.isEmpty()) {
                                view.failed(errorMessage);
                            } else {
                                view.failed(com.ekakashi.greenhouse.common.BaseResponse.messageResponse(response.code()));
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ObjectRecipe> call, @NonNull Throwable t) {
                if (view != null) {
                    view.failed(t.getMessage());
                }
            }
        });
    }
}
