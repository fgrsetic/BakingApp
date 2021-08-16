//package com.franjo.android.bakingapp.presentation;
//
//import android.os.Bundle;
//import android.os.Parcelable;
//import androidx.fragment.app.FragmentManager;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import android.view.MenuItem;
//import android.widget.LinearLayout;
//
//import com.franjo.android.bakingapp.R;
//import com.franjo.android.bakingapp.presentation.model.RecipeUI;
//import com.franjo.android.bakingapp.data.network.model.Steps;
//import com.franjo.android.bakingapp.utilities.Constants;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
//public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepDetailListener, StepDetailFragment.OnStepDetailListener {
//
//    private static final String TAG = RecipeDetailActivity.class.getSimpleName();
//
//    public String mRecipeName;
//    private FragmentManager fragmentManager;
//
//    @BindView(R.id.layout_fragment_container)
//    LinearLayout linearLayout;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recipe_detail);
//
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//
//        ButterKnife.bind(this);
//
//        fragmentManager = getSupportFragmentManager();
//
//            // Only create new fragments when there is no previously saved state
//            if (savedInstanceState == null) {
//
//                Bundle data = getIntent().getExtras();
//                List<RecipeUI> mRecipes = new ArrayList<>();
//
//                if (data != null) {
//                    mRecipes = data.getParcelableArrayList(Constants.CLICKED_RECIPE);
//                }
//                if (mRecipes != null) {
//                    mRecipeName = mRecipes.get(0).getName();
//                }
//
//                RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
//                recipeDetailFragment.setArguments(data);
//                fragmentManager.beginTransaction()
//                        .add(R.id.fragment_container, recipeDetailFragment)
//                        .addToBackStack(Constants.RECIPE_DETAILS_STACK)
//                        .commit();
//                // This LinearLayout will only initially exist in the two-pane tablet case
//                if (linearLayout.getTag() != null && linearLayout.getTag().equals("tablet-land") ) {
//                    StepDetailFragment stepDetailFragment = new StepDetailFragment();
//                    stepDetailFragment.setArguments(data);
//                    fragmentManager.beginTransaction()
//                            .add(R.id.fragment_container_land, stepDetailFragment)
//                            .addToBackStack(Constants.STEP_DETAILS_STACK)
//                            .commit();
//
//                }
//
//            } else {
//
//                mRecipeName = savedInstanceState.getString("Title");
//
//        }
//
//        if (actionBar != null) {
//            actionBar.setTitle(mRecipeName);
//        }
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            if (findViewById(R.id.fragment_container_land) == null) {
//                if (fragmentManager.getBackStackEntryCount() > 1) {
//                    fragmentManager.popBackStack(Constants.RECIPE_DETAILS_STACK, 0);
//                } else if (fragmentManager.getBackStackEntryCount() > 0) {
//
//                    finish();
//                }
//            } else {
//                finish();
//            }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onStepSelected(List<Steps> stepsList, int position) {
//        StepDetailFragment stepDetailFragment = new StepDetailFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList(Constants.CLICKED_STEP, (ArrayList<? extends Parcelable>) stepsList);
//        bundle.putInt("Index", position);
//        stepDetailFragment.setArguments(bundle);
//
//
//        if (linearLayout.getTag() != null && linearLayout.getTag().equals("tablet-land"))  {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container_land, stepDetailFragment)
//                    .addToBackStack(Constants.STEP_DETAILS_STACK)
//                    .commit();
//
//        } else {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, stepDetailFragment)
//                    .addToBackStack(Constants.STEP_DETAILS_STACK)
//                    .commit();
//        }
//
//    }
//
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("Title", mRecipeName);
//
//
//    }
//
//    @Override
//    public void onBackPressed() {
//         if (fragmentManager.getBackStackEntryCount() > 1) {
//            getSupportFragmentManager().popBackStack();
//        } else if (fragmentManager.getBackStackEntryCount() > 0) {
//            finish();
//        }
//
//    }
//
//
//}
