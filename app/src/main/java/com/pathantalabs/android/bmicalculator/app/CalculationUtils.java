package com.pathantalabs.android.bmicalculator.app;


import android.util.Log;

import java.text.DecimalFormat;

public class CalculationUtils {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.0");
    private static final double ONE_CMS_IN_FOOT =0.0328084;
    private static final double FOOT_INCHES = 12;


    public static double getBMICalculate(double weight,double height){
        double formula = (weight*10000 )/ (height * height);
        Log.v("Formula","value "+formula);

        return Double.parseDouble(decimalFormat.format(formula));
    }

    public static double getBMRCalculate(int tag,double weight,double height,int age){
        if (tag == 1){
            double formula = 10*weight+6.25*height-5*age+5;
            return Double.parseDouble(decimalFormat.format(formula));
        }else if(tag == 2){
            double formula = 10*weight+6.25*height-5*age-161;
            return Double.parseDouble(decimalFormat.format(formula));
        }
        return Double.parseDouble(null);
    }

    public static double getCalorieCalculate(int tag,double weight,double height,int age,double extraValue){
        if (tag == 1){
            double formula = (10*weight+6.25*height-5*age+5)*extraValue;
            return Double.parseDouble(decimalFormat.format(formula));
        }else if (tag == 2){
            double formula = (10*weight+6.25*height-5*age-161)*extraValue;
            return Double.parseDouble(decimalFormat.format(formula));
        }

        return 0.0;
    }

    public static double getBSACalculate(double weight, double height){
        double formula = Math.sqrt(weight*height/3600);
        return Double.parseDouble(decimalFormat.format(formula));
    }

    public static double getIBWCalculate(int tag, double height){
        double heightInFeet = (height*ONE_CMS_IN_FOOT - 5)*FOOT_INCHES;
        if (tag == 1){
            double formula  = 52 + 1.9*heightInFeet;
            return Double.parseDouble(decimalFormat.format(formula));
        }else if (tag ==2){
            double formula = 49 + 1.7*heightInFeet;
            return Double.parseDouble(decimalFormat.format(formula));
        }
        return 0;
    }
    public static double getLBMCalculate(int tag, double weight, double height){
        if (tag == 1){
            double formula = 0.407*weight + 0.267*height - 19.2;
            return Double.parseDouble(decimalFormat.format(formula));
        }else if (tag == 2){
            double formula = 0.252*weight + 0.473*height - 48.3;
            return Double.parseDouble(decimalFormat.format(formula));
        }
        return 0;
    }
}
