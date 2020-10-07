package com.franjo.android.bakingapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.franjo.android.bakingapp.data.network.model.Ingredients;
import com.franjo.android.bakingapp.data.network.model.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Franjo on 30.10.2017..
 */

public class RecipeUI implements Parcelable{

    private Integer id;
    private String name;
    private Integer servings;
    private String image;
    private List<Ingredients> ingredients;
    private List<Steps> steps;

    public RecipeUI(Integer id, String name, Integer servings, String image, List<Ingredients> ingredients, List<Steps> steps) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

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


    private RecipeUI(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
        image = in.readString();

        ingredients = new ArrayList<>();
                in.readList(ingredients, Ingredients.class.getClassLoader());
        steps = new ArrayList<>();
                in.readList(steps, Ingredients.class.getClassLoader());
    }

    public static final Creator<RecipeUI> CREATOR = new Creator<RecipeUI>() {
        @Override
        public RecipeUI createFromParcel(Parcel in) {
            return new RecipeUI(in);
        }

        @Override
        public RecipeUI[] newArray(int size) {
            return new RecipeUI[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeUI recipeUI = (RecipeUI) o;
        return Objects.equals(id, recipeUI.id) &&
                Objects.equals(name, recipeUI.name) &&
                Objects.equals(servings, recipeUI.servings) &&
                Objects.equals(image, recipeUI.image) &&
                Objects.equals(ingredients, recipeUI.ingredients) &&
                Objects.equals(steps, recipeUI.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, servings, image, ingredients, steps);
    }

    @NonNull
    @Override
    public String toString() {
        return "RecipeUI{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
