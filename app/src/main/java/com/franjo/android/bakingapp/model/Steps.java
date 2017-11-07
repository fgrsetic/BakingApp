package com.franjo.android.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Franjo on 30.10.2017..
 */

public class Steps implements Parcelable{

    private Integer id;
    private String shortDescription;
    private String videoUrl;
    private String thumbnailUrl;
    private String description;



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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(shortDescription);
        dest.writeString(videoUrl);
        dest.writeString(thumbnailUrl);
        dest.writeString(description);
    }

    private Steps(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        shortDescription = in.readString();
        videoUrl = in.readString();
        thumbnailUrl = in.readString();
        description = in.readString();
    }


    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

}
