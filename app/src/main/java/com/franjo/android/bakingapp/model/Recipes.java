package com.franjo.android.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Franjo on 30.10.2017..
 */

public class Recipes implements Parcelable{

    private Integer id;
    private String name;
    private List<Ingredients> ingredients;
    private List<Steps> steps;
    private Integer servings;
    private String image;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(servings);
        dest.writeString(image);
        dest.writeList(ingredients);
        dest.writeList(steps);
    }


    private Recipes(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
        image = in.readString();

        ingredients = new ArrayList<>();
                in.readList(ingredients, Ingredients.class.getClassLoader());
        steps = new ArrayList<>();
                in.readList(steps, Ingredients.class.getClassLoader());
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
