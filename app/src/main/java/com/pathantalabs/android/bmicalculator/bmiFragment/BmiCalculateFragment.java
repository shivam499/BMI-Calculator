package com.pathantalabs.android.bmicalculator.bmiFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pathantalabs.android.bmicalculator.R;
import com.pathantalabs.android.bmicalculator.app.AppUtils;
import com.pathantalabs.android.bmicalculator.app.CalculationUtils;

public class BmiCalculateFragment extends Fragment{

    private EditText weight;
    private EditText height;
    private Button CalculateBMI;
    private TextView BMIInfo;
    public BmiCalculateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bmi_calculate,container,false);

        weight = (EditText) root.findViewById(R.id.editWeight);
        height = (EditText) root.findViewById(R.id.editHeight);
        CalculateBMI = (Button) root.findViewById(R.id.bmiCalculateButton);
        BMIInfo = (TextView) root.findViewById(R.id.bmiTextShow);
        BMIInfo.setText("");

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double weightD = 0;
                double heightD = 0;
                boolean condition = true;
                if (weight.getText().toString().isEmpty()&&height.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Enter Details Please!", Toast.LENGTH_SHORT).show();
                    condition = false;
                    BMIInfo.setText("");
                }else if(weight.getText().toString().equals("")||height.getText().toString().equals("")){
                    BMIInfo.setText("");
                    if (weight.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Weight Please!", Toast.LENGTH_SHORT).show();
                    }else if (height.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Height Please!", Toast.LENGTH_SHORT).show();
                    }
                }
                if (!weight.getText().toString().equals("")&&!height.getText().toString().equals("")){
                    condition = true;
                    weightD = Double.parseDouble(weight.getText().toString().trim());
                    heightD = Double.parseDouble(height.getText().toString().trim());
                }

                if (condition){
                    double answer = CalculationUtils.getBMICalculate(weightD,heightD);
                    String bmiCategory = AppUtils.getBMICategory(answer);

                    //noinspection deprecation
                    BMIInfo.setText(Html.fromHtml("Your BMI Score is <b>"+answer+"</b>.<br/>You are in <b>"+bmiCategory+"</b>.<br/>For Ideal Weight Check <u>IBW Calculator</u>."));
                }
            }
        });
    }
}
