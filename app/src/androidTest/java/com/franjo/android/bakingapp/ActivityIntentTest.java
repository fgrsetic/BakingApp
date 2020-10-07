//package com.franjo.android.bakingapp;
//
//import android.app.Activity;
//import android.app.Instrumentation;
//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.IdlingResource;
//import androidx.test.espresso.contrib.RecyclerViewActions;
//import androidx.test.espresso.intent.rule.IntentsTestRule;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import com.franjo.android.bakingapp.presentation.RecipeDetailActivity;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.intent.Intents.intended;
//import static androidx.test.espresso.intent.Intents.intending;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
//import static org.hamcrest.core.IsNot.not;
//
///**
// * Created by Franjo on 24.11.2017..
// */
//
//@RunWith(AndroidJUnit4.class)
//public class ActivityIntentTest {
//
//   /**
//     *
//     * This test demonstrates Espresso Intents using the IntentsTestRule, a class that extends
//     * ActivityTestRule. IntentsTestRule initializes Espresso-Intents before each test that is annotated
//     * with @Test and releases it once the test is complete. The designated Activity
//     * is also terminated after each test.
//     *
//     */
//
//   private IdlingResource mIdlingResource;
//
//
//    @Rule
//    public IntentsTestRule<RecipeMainActivity> mActivityTestRule = new IntentsTestRule<>(RecipeMainActivity.class);
//
//    @Before
//    public void registerIdlingResource() {
//        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
//        // To prove that the test fails, omit this call:
//        Espresso.registerIdlingResources(mIdlingResource);
//    }
//
//    // The @Before methods terminate prior to the execution of the @Test method
//    @Before
//    public void stubAllExternalIntents() {
//        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
//        // every test run. In this case all external Intents will be blocked.
//        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
//    }
//
//    @Test
//    public void clickItemOnRecyclerView_Match_RecipeDetailActivity() {
//        onView(ViewMatchers.withId(R.id.recipe_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//        // hasComponent() can match an intent by class name, package name or short class name
//        intended(hasComponent(RecipeDetailActivity.class.getName()));
//
//    }
//
//    // Unregister resources when not needed to avoid malfunction
//    @After
//    public void unregisterIdlingResource() {
//        if (mIdlingResource != null) {
//            Espresso.unregisterIdlingResources(mIdlingResource);
//        }
//    }
//}
