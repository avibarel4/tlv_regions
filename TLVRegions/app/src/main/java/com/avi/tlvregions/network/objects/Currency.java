package com.avi.tlvregions.network.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by avibarel on 21/10/2017.
 */

public class Currency implements Parcelable {

    @SerializedName("code")
    private String mCode;

    @SerializedName("name")
    private String mName;

    @SerializedName("symbol")
    private String mSymbol;

    public String getCode() {
        return mCode;
    }

    public String getName() {
        return mName;
    }

    public String getSymbol() {
        return mSymbol;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Currency(Parcel in) {
        mCode = in.readString();
        mName = in.readString();
        mSymbol = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCode);
        dest.writeString(mName);
        dest.writeString(mSymbol);
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel in) {
            return new Currency(in);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };
}
