package com.avi.tlvregions;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.avi.tlvregions", appContext.getPackageName());

        // make sure the response is not null even if the data passed is null
        assertTrue(Utils.getCurrenciesStringForCountry(null, appContext.getString(R.string.currency_format)) != null);

        // make sure the getBorderCountriesStringFromList method returns the correct amount of codes in the final String
        List<String> fiveCodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fiveCodes.add("Code: " + i);
        }
        assertTrue(Utils.getBorderCountriesStringFromList(fiveCodes).split(";").length == 5);
    }
}
