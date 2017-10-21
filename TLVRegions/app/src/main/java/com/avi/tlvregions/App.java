package com.avi.tlvregions;

import android.app.Application;
import android.content.Context;

/**
 * Created by avibarel on 21/10/2017.
 */

public class App extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
