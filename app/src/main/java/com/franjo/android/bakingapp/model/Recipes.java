package com.franjo.android.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Franjo on 30.10.2017..
 */

public class Recipes implements Parcelable{

    private String title;
    private List<Ingredients> ingredients;
    private List<Steps> steps;
    private int servings;
    private ImageView recipeImage;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public ImageView getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(ImageView recipeImage) {
        this.recipeImage = recipeImage;
    }


    protected Recipes(Parcel in) {
        title = in.readString();
        servings = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeInt(servings);
        dest.writeList(ingredients);
        dest.writeList(steps);

    }



    public static final Creator<Recipes> CREATOR = new Creator<Recipes>() {
        @Override
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        @Override
        public Recipes[] newArray(int size) {
            return new Recipes[size];
        }
    };


}
