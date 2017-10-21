package com.avi.tlvregions.network.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by avibarel on 18/10/2017.
 */

public class Country implements Comparable<Country>, Parcelable {

    @SerializedName("name")
    private String mName;

    @SerializedName("area")
    private float mArea;

    @SerializedName("capital")
    private String mCapital;

    @SerializedName("currencies")
    private List<Currency> mCurrencies;

    @SerializedName("borders")
    private List<String> mBorders;

    public String getName() {
        return mName;
    }

    public int getArea() {
        return (int)mArea;
    }

    public String getCapital() {
        return mCapital;
    }

    public List<Currency> getCurrencies() {
        return mCurrencies;
    }

    public List<String> getBorders() {
        return mBorders;
    }

    @Override
    public int compareTo(Country other) {
        return (other == null ? 0 : other.getArea()) - getArea();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeFloat(mArea);
        dest.writeString(mCapital);
        dest.writeTypedList(mCurrencies);
        dest.writeStringList(mBorders);
    }

    protected Country(Parcel in) {
        mName = in.readString();
        mArea = in.readFloat();
        mCapital = in.readString();
        mCurrencies = in.createTypedArrayList(Currency.CREATOR);
        mBorders = in.createStringArrayList();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
