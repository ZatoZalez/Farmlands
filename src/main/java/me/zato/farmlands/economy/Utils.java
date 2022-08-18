package me.zato.farmlands.economy;

import java.text.DecimalFormat;

public class Utils {
    public static String convertToVisualValue(double value){
        DecimalFormat df = new DecimalFormat("###,###,###,###,###.##");
        String formattedBalance = df.format(value);
        formattedBalance = formattedBalance.replace(",", ".").replace(".", ",");
        return formattedBalance;
    }
}
