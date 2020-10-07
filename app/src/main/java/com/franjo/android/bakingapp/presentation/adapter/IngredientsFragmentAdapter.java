//package com.franjo.android.bakingapp.presentation.adapter;
//
//import android.content.Context;
//import androidx.recyclerview.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.franjo.android.bakingapp.R;
//import com.franjo.android.bakingapp.data.network.model.Ingredients;
//import com.franjo.android.bakingapp.utilities.Constants;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by Franjo on 2.11.2017..
// */
//
//public class IngredientsFragmentAdapter extends RecyclerView.Adapter<IngredientsFragmentAdapter.IngredientsViewHolder> {
//
//    private Context mContext;
//    private List<Ingredients> mListIngredients = new ArrayList<>();;
//    private LayoutInflater mInflater;
//
//
//    public IngredientsFragmentAdapter(Context context, List<Ingredients> ingredientsList) {
//        mContext = context;
//        mListIngredients = ingredientsList;
//        mInflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        int layoutForListItem = R.layout.fragment_ingredients_item;
//
//        View rootView = mInflater.inflate(layoutForListItem, parent, false);
//
//        return new IngredientsViewHolder(rootView);
//    }
//
//    @Override
//    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
//        Ingredients data = mListIngredients.get(position);
//
//        Double quantity = data.getQuantitiy();
//        String measure = data.getMeasure();
//        String ingredient = data.getIngredient();
//
//        holder.tvQuantity.setText(String.format(mContext.getString(R.string.quantity), Constants.format(quantity)));
//        holder.tvMeasure.setText(String.format(mContext.getString(R.string.measure_text), measure));
//        holder.tvIngredient.setText(ingredient);
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return (mListIngredients == null) ? 0 : mListIngredients.size();
//    }
//
//
//
//    class IngredientsViewHolder extends RecyclerView.ViewHolder {
//
//        @BindView(R.id.quantity)
//        TextView tvQuantity;
//        @BindView(R.id.measure)
//        TextView tvMeasure;
//        @BindView(R.id.ingredient)
//        TextView tvIngredient;
//
//
//        IngredientsViewHolder(View itemView) {
//            super(itemView);
//
//            ButterKnife.bind(this, itemView);
//        }
//    }
//}
