//package com.franjo.android.bakingapp.presentation;
//
//import android.os.Bundle;
//import android.os.Parcelable;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.franjo.android.bakingapp.R;
//import com.franjo.android.bakingapp.presentation.adapter.IngredientsFragmentAdapter;
//import com.franjo.android.bakingapp.data.network.model.Ingredients;
//import com.franjo.android.bakingapp.presentation.model.RecipeUI;
//import com.franjo.android.bakingapp.utilities.Constants;
//import com.franjo.android.bakingapp.widget.RecipesUpdateService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
///**
// * Created by Franjo on 1.11.2017..
// */
//
//public class IngredientFragment extends Fragment {
//
//    private static final String TAG = IngredientFragment.class.getSimpleName();
//
//    @BindView(R.id.ingredient_detail_layout)
//    RecyclerView recyclerView;
//
//    private List<RecipeUI> mRecipeUIList;
//    private List<Ingredients> mIngredientsList;
//    int mListIndex;
//
//    List<Ingredients> mWidgetArrayList;
//
//
//    // Mandatory empty constructor
//    public IngredientFragment() {
//
//    }
//
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        mRecipeUIList = new ArrayList<>();
//        Bundle bundle = getArguments();
//
//        if(savedInstanceState != null) {
//            mRecipeUIList = savedInstanceState.getParcelableArrayList(Constants.CLICKED_RECIPE);
//        } else {
//            mRecipeUIList = bundle.getParcelableArrayList(Constants.CLICKED_RECIPE);
//        }
//
//        View rootView = inflater.inflate(R.layout.fragment_ingredient_detail_layout, container, false);
//        ButterKnife.bind(this, rootView);
//
//        if (mRecipeUIList != null) {
//            mIngredientsList = mRecipeUIList.get(mListIndex).getIngredients();
//
//        } else {
//            Log.v(TAG, getString(R.string.log_message_ingredient_id_null));
//
//        }
//
//        mWidgetArrayList = new ArrayList<>();
//
//        IngredientsFragmentAdapter mAdapter = new IngredientsFragmentAdapter(getActivity(), mIngredientsList);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        updateWidgetScreen(mIngredientsList);
//
//        return rootView;
//    }
//
//    public void updateWidgetScreen(List<Ingredients> widgetArrayList) {
//
//        StringBuilder ingredients = new StringBuilder(getString(R.string.ingredient_list_widget_title) + "\n");
//        int lineNumber = 0;
//        for (Ingredients ingredient : widgetArrayList) {
//            Double quantity = ingredient.getQuantity();
//                    lineNumber++;
//            ingredients.append(lineNumber).append(". ")
//                    .append(ingredient.getIngredient()).append(": " + "\n")
//                    .append(Constants.format(quantity)).append(" x ").append(ingredient.getMeasure()).append("\n");
//        }
//
//        RecipesUpdateService.startActionFetchIngredientsList(getContext(), ingredients.toString());
//    }
//
//
//    @Override
//    public void onSaveInstanceState(Bundle currentState) {
//        super.onSaveInstanceState(currentState);
//        currentState.putParcelableArrayList(Constants.CLICKED_RECIPE, (ArrayList<? extends Parcelable>) mRecipeUIList);
//
//
//    }
//
//
//}
//
