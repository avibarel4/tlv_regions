package com.avi.tlvregions;

import com.avi.tlvregions.network.objects.Country;
import com.avi.tlvregions.network.objects.Currency;

import java.util.List;

/**
 * Created by avibarel on 21/10/2017.
 */

public class Utils {

    private Utils() {
        // private constructor
    }

    public static String getCurrenciesStringForCountry(Country country, String currencyStringFormat) {

        if (country == null) {
            return "";
        }

        List<Currency> currencies = country.getCurrencies();
        StringBuilder currenciesStringBuilder = new StringBuilder();
        if (currencies != null) {
            for (int i = 0; i < currencies.size(); i++) {
                Currency currency = currencies.get(i);
                currenciesStringBuilder.append(String.format(currencyStringFormat, currency.getName(), currency.getSymbol()));
                if (i < currencies.size() - 1) {
                    currenciesStringBuilder.append(", ");
                }
            }
        }

        return currenciesStringBuilder.toString();
    }

    public static String getBorderCountriesStringFromList(List<String> codes) {
        StringBuilder codesStringBuilder = new StringBuilder();
        for (int i = 0; i < codes.size(); i++) {
            codesStringBuilder.append(codes.get(i));
            if (i < codes.size() - 1) {
                codesStringBuilder.append(";");
            }
        }

        return codesStringBuilder.toString();
    }

}
