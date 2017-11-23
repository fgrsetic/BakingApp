package com.franjo.android.bakingapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.franjo.android.bakingapp.utilities.Constants;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * Customize class - update intent actions, extra parameters and static helper methods.
 */
public class RecipesUpdateService extends IntentService {

    static final String TAG = RecipesUpdateService.class.getSimpleName();


    public RecipesUpdateService() {
        super("RecipesUpdateService");
    }

    /**
     * Starts this service to perform action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    public static void startActionFetchIngredientsList(Context context, String ingredientList) {
        Intent intent = new Intent(context, RecipesUpdateService.class);
        intent.setAction(Constants.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(Constants.EXTRA_INGREDIENT_LIST, ingredientList);
        context.startService(intent);
        Log.i(TAG, "startService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (Constants.ACTION_APPWIDGET_UPDATE.equals(action)) {
                String ingredientList = intent.getStringExtra(Constants.EXTRA_INGREDIENT_LIST);
                handleActionFetchIngredientsList(ingredientList);

            }
        }
    }

    /**
     * Handle action in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFetchIngredientsList(String ingredientList) {
        Intent intent = new Intent(Constants.ACTION_APPWIDGET_UPDATE);
        intent.setAction(Constants.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(Constants.EXTRA_INGREDIENT_LIST, ingredientList);
        sendBroadcast(intent);


    }

}
