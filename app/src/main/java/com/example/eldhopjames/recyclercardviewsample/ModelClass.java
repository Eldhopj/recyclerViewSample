package com.example.eldhopjames.recyclercardviewsample;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelClass implements Parcelable {
    private String head;
    private String desc;

    public ModelClass() {
    }

    public ModelClass(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    protected ModelClass(Parcel in) {
        head = in.readString();
        desc = in.readString();
    }

    //This creator uses the constructor above to read the values from the parcel
    public static final Creator<ModelClass> CREATOR = new Creator<ModelClass>() {
        @Override
        public ModelClass createFromParcel(Parcel in) {
            return new ModelClass(in);
        }

        @Override
        public ModelClass[] newArray(int size) {
            return new ModelClass[size];
        }
    };

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //This method adds the elements to the parcel
    //NOTE : The order of reading items must be equal to order in the protected Parcel constructor
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(head);
        dest.writeString(desc);
    }
}
