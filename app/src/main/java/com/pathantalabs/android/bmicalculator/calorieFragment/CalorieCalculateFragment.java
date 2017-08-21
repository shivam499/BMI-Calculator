package com.pathantalabs.android.bmicalculator.calorieFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pathantalabs.android.bmicalculator.R;
import com.pathantalabs.android.bmicalculator.app.AppUtils;
import com.pathantalabs.android.bmicalculator.app.CalculationUtils;

public class CalorieCalculateFragment extends Fragment{

    private EditText heightCal;
    private EditText weightCal;
    private EditText ageCal;
    private RadioButton maleCal;
    private RadioButton femaleCal;
    private Spinner activityCal;
    private Button CalculateCal;
    private TextView CalorieInfo;

    public CalorieCalculateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calorie_calculate,container,false);

        heightCal = (EditText) root.findViewById(R.id.editHeightCal);
        weightCal = (EditText) root.findViewById(R.id.editWeightCal);
        ageCal = (EditText) root.findViewById(R.id.editAgeCal);
        CalculateCal = (Button) root.findViewById(R.id.CalCalculateButton);
        CalorieInfo = (TextView) root.findViewById(R.id.CalTextShow);
        activityCal = (Spinner) root.findViewById(R.id.SpinnerCal);
        maleCal = (RadioButton) root.findViewById(R.id.maleCal);
        femaleCal = (RadioButton) root.findViewById(R.id.femaleCal);
        CalorieInfo.setText("");

        String[] arrayActivity = getResources().getStringArray(R.array.activity_unit);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayActivity);
        activityCal.setAdapter(adapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CalculateCal.setOnClickListener(new View.OnClickListener() {
            double heightCalD = 0;
            double weightCalD = 0;
            int ageCalD = 0;
            double extraCalorieValue = 0;
            boolean condition = true;
            // --Commented out by Inspection (07-08-2017 12:22 AM):boolean genderStatus;
            @Override
            public void onClick(View view) {
                if (heightCal.getText().toString().equals("")&&weightCal.getText().toString().equals("")
                        &&ageCal.getText().toString().equals("")&&
                        !(maleCal.isChecked()||femaleCal.isChecked())){
                    condition = false;
                    Toast.makeText(getContext(), "Enter details Please!", Toast.LENGTH_SHORT).show();
                }else if (heightCal.getText().toString().equals("")||weightCal.getText().toString().equals("")
                        ||ageCal.getText().toString().equals("")||
                        !(maleCal.isChecked()||femaleCal.isChecked())){
                    CalorieInfo.setText("");
                    if (weightCal.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Weight Please!", Toast.LENGTH_SHORT).show();
                    } else if (heightCal.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Height Please!", Toast.LENGTH_SHORT).show();
                    } else if (ageCal.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Age Please!", Toast.LENGTH_SHORT).show();
                    }else if (!(maleCal.isChecked()||femaleCal.isChecked())){
                        condition = false;
                        Toast.makeText(getContext(), "Select Gender Please!", Toast.LENGTH_SHORT).show();
                    }
                }
                if (!heightCal.getText().toString().equals("")&&!weightCal.getText().toString().equals("")
                        &&!ageCal.getText().toString().equals("")&&
                        (maleCal.isChecked()||femaleCal.isChecked())){
                    heightCalD = Double.parseDouble(heightCal.getText().toString().trim());
                    weightCalD = Double.parseDouble(weightCal.getText().toString().trim());
                    ageCalD = Integer.parseInt(ageCal.getText().toString().trim());
                    //boolean genderStatus = maleCal.isChecked() || femaleCal.isChecked();
                    condition = true;
                }

                extraCalorieValue = AppUtils.getExtraCalorie(activityCal.getSelectedItemPosition());

                if (condition){
                    if (maleCal.isChecked()){
                        double answer = CalculationUtils.getCalorieCalculate(1,weightCalD,heightCalD,ageCalD, extraCalorieValue);
                        //noinspection deprecation
                        CalorieInfo.setText(Html.fromHtml("You need <b>"+answer+"</b> Calorie/day to Actively Work."));
                    }else if (femaleCal.isChecked()){
                        double answer = CalculationUtils.getCalorieCalculate(2,weightCalD,heightCalD,ageCalD,extraCalorieValue);
                        //noinspection deprecation
                        CalorieInfo.setText(Html.fromHtml("You need <b>"+answer+"</b> Calorie/day to Actively Work."));
                    }
                }
            }
        });
    }
}
