package com.ozgurtas.newsapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ozgur on 12.03.2018.
 */

public class Multimedia implements Parcelable {
    private String url;
    private String format;
    private Integer height;
    private Integer width;
    private String type;
    private String subtype;
    private String caption;
    private String copyright;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.format);
        dest.writeValue(this.height);
        dest.writeValue(this.width);
        dest.writeString(this.type);
        dest.writeString(this.subtype);
        dest.writeString(this.caption);
        dest.writeString(this.copyright);
    }

    public Multimedia() {
    }

    protected Multimedia(Parcel in) {
        this.url = in.readString();
        this.format = in.readString();
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = in.readString();
        this.subtype = in.readString();
        this.caption = in.readString();
        this.copyright = in.readString();
    }

    public static final Parcelable.Creator<Multimedia> CREATOR = new Parcelable.Creator<Multimedia>() {
        @Override
        public Multimedia createFromParcel(Parcel source) {
            return new Multimedia(source);
        }

        @Override
        public Multimedia[] newArray(int size) {
            return new Multimedia[size];
        }
    };
}
