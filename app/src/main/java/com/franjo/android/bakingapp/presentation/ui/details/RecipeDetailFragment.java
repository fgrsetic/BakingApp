//package com.franjo.android.bakingapp.presentation;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Parcelable;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.franjo.android.bakingapp.R;
//import com.franjo.android.bakingapp.presentation.adapter.RecipeDetailAdapter;
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
///**
// * Created by Franjo on 10.11.2017..
// */
//
//public class RecipeDetailFragment extends Fragment implements OnStepItemClickListener {
//
//    private static final String TAG = RecipeDetailFragment.class.getSimpleName();
//
//    @BindView(R.id.fragment_detail_recycler_view)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.tvIngredientDetails)
//    TextView tvIngredientDetails;
//
//    @BindView(R.id.tvIngredientTitle)
//    TextView tvIngredientTitle;
//
//    @BindView(R.id.tv_steps_title)
//    TextView tvStepTitle;
//
//    List<RecipeUI> mListRecipes;
//    List<Steps> mListSteps;
//
//    // Variables to store the index of the recipe that this fragment displays
//    private int mListIndex;
//    Bundle bundle;
//
//    // Define a new interface OnStepDetailListener that triggers a callback in the host activity
//    OnStepDetailListener mCallback;
//
//
//    // Mandatory empty constructor
//    public RecipeDetailFragment() {
//
//    }
//
//    // OnStepDetailListener interface, calls a method in the host activity named onStepSelected
//    public interface OnStepDetailListener {
//        void onStepSelected(List<Steps> stepsList, int position);
//    }
//
//
//
//    // Override onAttach to make sure that the container activity has implemented the callback
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        // This makes sure that the host activity has implemented the callback interface
//        // If not, it throws an exception
//        try {
//            mCallback = (OnStepDetailListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement OnStepDetailListener");
//        }
//    }
//
//
//    @Nullable
//    @Override
//    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        final Bundle bundle = getArguments();
//
//        View fragmentView = inflater.inflate(R.layout.fragment_recipes_detail_layout, container, false);
//        ButterKnife.bind(this, fragmentView);
//
//        if (savedInstanceState != null) {
//            savedInstanceState.getParcelableArrayList(Constants.CLICKED_RECIPE);
//        } else {
//            mListRecipes = bundle.getParcelableArrayList(Constants.CLICKED_RECIPE);
//        }
//
//        tvIngredientTitle.setText(R.string.ingredients);
//        tvIngredientDetails.setText("\u25BA");
//        tvStepTitle.setText(R.string.steps);
//
//        mListRecipes = bundle.getParcelableArrayList(Constants.CLICKED_RECIPE);
//
//        if (mListRecipes != null) {
//            mListSteps = mListRecipes.get(mListIndex).getSteps();
//
//        } else {
//            Log.v(TAG, "This fragment has a null list of steps id's");
//
//        }
//
//        tvIngredientTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                IngredientFragment ingredientFragment = new IngredientFragment();
//                ingredientFragment.setArguments(bundle);
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, ingredientFragment)
//                        .addToBackStack(null)
//                        .commit();
//
//
//            }
//
//        });
//
//        RecipeDetailAdapter mAdapter = new RecipeDetailAdapter(getActivity(), mListSteps);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mAdapter.setOnStepFragmentClickListener(this);
//
//
//
//        return fragmentView;
//    }
//
//
//    @Override
//    public void itemListClicked(List<Steps> stepsList, int position) {
//        mCallback.onStepSelected(stepsList, position);
//
//    }
//
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList(Constants.CLICKED_RECIPE, (ArrayList<? extends Parcelable>) mListRecipes);
//    }
//
//
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mCallback = null;
//    }
//}
