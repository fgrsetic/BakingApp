package com.franjo.android.bakingapp.data.network.model;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

/**
 * Created by Franjo on 30.10.2017..
 */

public class ApiRecipe {
    private Integer id;
    private String name;
    private Integer servings;
    private String image;
    private List<Ingredients> ingredients;
    private List<Steps> steps;

    public ApiRecipe(Integer id, String name, Integer servings, String image, List<Ingredients> ingredients, List<Steps> steps) {
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

    public String getName() {
        return name;
    }

    public Integer getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiRecipe apiRecipe = (ApiRecipe) o;
        return Objects.equals(id, apiRecipe.id) &&
                Objects.equals(name, apiRecipe.name) &&
                Objects.equals(servings, apiRecipe.servings) &&
                Objects.equals(image, apiRecipe.image) &&
                Objects.equals(ingredients, apiRecipe.ingredients) &&
                Objects.equals(steps, apiRecipe.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, servings, image, ingredients, steps);
    }

    @NonNull
    @Override
    public String toString() {
        return "ApiRecipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
