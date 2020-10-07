//package com.franjo.android.bakingapp.widget;
//
//import android.annotation.TargetApi;
//import android.app.PendingIntent;
//import android.appwidget.AppWidgetManager;
//import android.appwidget.AppWidgetProvider;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.util.Log;
//import android.widget.RemoteViews;
//
//import com.franjo.android.bakingapp.R;
//import com.franjo.android.bakingapp.utilities.Constants;
//
///**
// * Implementation of App Widget functionality.
// */
//public class RecipesWidgetProvider extends AppWidgetProvider {
//
//    static final String TAG =RecipesWidgetProvider.class.getSimpleName();
//    String ingredientList;
//
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, String ingredientList, int appWidgetId) {
//
//        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//
//        // Create intent to launch RecipeDetailActivity when clicked
//        Intent appIntent = new Intent(context, RecipeMainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
//
//        views.setTextViewText(R.id.tv_widget_ingredient, ingredientList);
//
//        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, views);
//        Log.i(TAG, "update App Widget in WidgetProvider");
//
//    }
//
//    // Called when new widget is created, as well as every update interval which we set to 30 minutes
//    @Override
//    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, context.getString(R.string.widget_text), appWidgetId);
//
//        }
//
//
//    }
//
//    public static void updateRecipeAppWidgets(Context context, AppWidgetManager appWidgetManager, String ingredientList, int[] appWidgetIds) {
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, ingredientList, appWidgetId);
//        }
//    }
//
//    @Override
//    public void onEnabled(Context context) {
//        // Enter relevant functionality for when the first widget is created
//    }
//
//    @Override
//    public void onDisabled(Context context) {
//        // Enter relevant functionality for when the last widget is disabled
//    }
//
//    // Called for every broadcast and before each of the above callback methods
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        final String action = intent.getAction();
//        if (action != null && action.equals(Constants.ACTION_APPWIDGET_UPDATE)) {
//            // Refresh all widgets
//            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecipesWidgetProvider.class));
//            ingredientList = intent.getStringExtra(Constants.EXTRA_INGREDIENT_LIST);
//            // Update all widgets
//            RecipesWidgetProvider.updateRecipeAppWidgets(context, appWidgetManager, ingredientList, appWidgetIds);
//
//            super.onReceive(context, intent);
//        }
//    }
//
//}
//
