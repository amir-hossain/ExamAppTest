package com.example.amir.examtestapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Persion implements Parcelable {
    private String firstName;

    public Persion(String firstName) {
        this.firstName = firstName;
    }


    protected Persion(Parcel in) {
        firstName = in.readString();
    }

    public static final Creator<Persion> CREATOR = new Creator<Persion>() {
        @Override
        public Persion createFromParcel(Parcel in) {
            return new Persion(in);
        }

        @Override
        public Persion[] newArray(int size) {
            return new Persion[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
    }
}
