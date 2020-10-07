//package com.franjo.android.bakingapp;
//
//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.IdlingResource;
//import androidx.test.espresso.contrib.RecyclerViewActions;
//import androidx.test.rule.ActivityTestRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.core.AllOf.allOf;
//
///**
// * Created by Franjo on 23.11.2017..
// */
//
//@RunWith(AndroidJUnit4.class)
//public class ActivityBasicTest {
//
//    /**
//     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
//     * activity. The activity that will be tested will be launched before each test that's annotated
//     * with @Test and before methods annotated with @before. The activity will be terminated after
//     * the test and methods annotated with @After are complete. This rule allows you to directly
//     * access the activity during the test.
//     */
//
//    private IdlingResource mIdlingResource;
//    private static final String RECIPE_TITLE = "Cheesecake";
//
//    @Rule
//    public ActivityTestRule<RecipeMainActivity> mActivityTestRule
//            = new ActivityTestRule<>(RecipeMainActivity.class);
//
//
//    // Registers any resource that needs to be synchronized with Espresso before
//    // the test is run.
//    @Before
//    public void registerIdlingResource() {
//        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
//        Espresso.registerIdlingResources(mIdlingResource);
//    }
//
//
//    // The espresso-contrib library provides a RecyclerViewActions class that offers a way to click on a specific position in a RecyclerView
//    @Test
//    public void clickCardViewItem_checkTextTitle() {
//
//        int recyclerview_position = 3;
//
//        onView(withId(R.id.recipe_recycler_view)) // Find the view (ViewMatcher)
//                .perform(RecyclerViewActions.scrollToPosition(recyclerview_position)); // Perform action on the view (ViewAction)
//        onView(withText(RECIPE_TITLE))
//                .check(matches(isDisplayed())); // Check if view does what is expected (matches(isDisplayed()) is a ViewAssertion)
//
//    }
//
//
//    @Test
//    public void click_FragmentDetailItem_ForPlayerView_RecipeDetailActivity() {
//
//        onView(withId(R.id.recipe_recycler_view))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click())); //Clicks on an item from the list by position
//        onView(withId(R.id.fragment_detail_recycler_view)) // Find the view
//                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click())); // Perform action on the view
//        onView(allOf(withId(R.id.mPlayerView), isDisplayed())); // Check if view does what is expected
//
//    }
//
//
//    @Test
//    public void clickNextButton_goToDescription() {
//        onView(withId(R.id.recipe_recycler_view))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click())); //Clicks on an item from the list by position
//        onView(withId(R.id.fragment_detail_recycler_view)) // Find the view
//                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click())); // Perform action on the view
//        onView(withId(R.id.btnNext)).perform(click());
//        onView(withId(R.id.tvDescription)).check(matches(withText(containsString("4"))));
//    }
//
//    // Unregister resources when not needed to avoid malfunction
//    @After
//    public void unregisterIdlingResource() {
//        if (mIdlingResource != null) {
//            Espresso.unregisterIdlingResources(mIdlingResource);
//        }
//
//
//    }
//}
//
//
