package com.pathantalabs.android.bmicalculator.app;

import com.pathantalabs.android.bmicalculator.BuildConfig;

public class AppUtils {

    public static String getBMICategory(double bmi) {
        if (15.0d > bmi) {
            return "Very severely underweight";
        }
        if (15.0d <= bmi && 16.0d >= bmi) {
            return "Severely underweight";
        }
        if (16.0d <= bmi && 18.5d >= bmi) {
            return "Underweight";
        }
        if (18.5d <= bmi && 25.0d >= bmi) {
            return "Normal";
        }
        if (25.0d <= bmi && 30.0d >= bmi) {
            return "Overweight";
        }
        if (30.0d <= bmi && 35.0d >= bmi) {
            return "Moderately obese";
        }
        if (35.0d <= bmi && 40.0d >= bmi) {
            return "Severely obese";
        }
        if (40.0d < bmi) {
            return "Very severely obese";
        }
        return BuildConfig.FLAVOR;
    }

    public static double getExtraCalorie(int pos){
        if (pos == 0){
            return 1.2;
        }else if (pos == 1){
            return 1.375;
        }else if (pos == 2){
            return 1.55;
        }else if (pos == 3){
            return 1.725;
        }else if (pos == 4){
            return 1.9;
        }
        return 1;
    }
}
