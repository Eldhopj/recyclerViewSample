package com.example.eldhopjames.recyclercardviewsample.modelClass;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelClass implements Parcelable {
    private String head;
    private String desc;
    private int type;

    public ModelClass() {
    }

    protected ModelClass(Parcel in) {
        head = in.readString();
        desc = in.readString();
        type = in.readInt();
    }

    public ModelClass(String head, String desc, int type) {
        this.head = head;
        this.desc = desc;
        this.type = type;
    }

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

    public int getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(head);
        parcel.writeString(desc);
        parcel.writeInt(type);
    }
}
