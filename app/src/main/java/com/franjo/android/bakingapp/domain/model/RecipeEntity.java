package com.franjo.android.bakingapp.domain.model;

import androidx.annotation.NonNull;

import com.franjo.android.bakingapp.data.network.model.Ingredients;
import com.franjo.android.bakingapp.data.network.model.Steps;

import java.util.List;
import java.util.Objects;

/**
 * Created by Franjo on 30.10.2017..
 */

public class RecipeEntity {

    private Integer id;
    private String name;
    private Integer servings;
    private String image;
    private List<Ingredients> ingredients;
    private List<Steps> steps;

    public RecipeEntity(Integer id, String name, Integer servings, String image, List<Ingredients> ingredients, List<Steps> steps) {
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

    public Integer getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeEntity that = (RecipeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ingredients);
    }

    @NonNull
    @Override
    public String toString() {
        return "RecipeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
