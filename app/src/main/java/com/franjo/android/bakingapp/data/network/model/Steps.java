package com.franjo.android.bakingapp.data.network.model;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Created by Franjo on 30.10.2017..
 */

public class Steps {

    private Integer id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailUrl) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Steps steps = (Steps) o;
        return Objects.equals(id, steps.id) &&
                Objects.equals(shortDescription, steps.shortDescription) &&
                Objects.equals(description, steps.description) &&
                Objects.equals(videoURL, steps.videoURL) &&
                Objects.equals(thumbnailURL, steps.thumbnailURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortDescription, description, videoURL, thumbnailURL);
    }

    @NonNull
    @Override
    public String toString() {
        return "Steps{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", thumbnailURL='" + thumbnailURL + '\'' +
                '}';
    }
}
