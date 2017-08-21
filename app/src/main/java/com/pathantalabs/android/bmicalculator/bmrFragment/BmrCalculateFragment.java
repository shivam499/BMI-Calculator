package com.pathantalabs.android.bmicalculator.bmrFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pathantalabs.android.bmicalculator.R;
import com.pathantalabs.android.bmicalculator.app.CalculationUtils;

public class BmrCalculateFragment extends Fragment{

    private EditText weightBMR;
    private EditText heightBMR;
    private EditText AgeBMR;
    private RadioButton maleBMR;
    private RadioButton FemaleBMR;
    private Button CalculateBMR;
    private TextView BMRInfo;
    public BmrCalculateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bmr_calculate,container,false);

        weightBMR = (EditText) root.findViewById(R.id.editWeightBmr);
        heightBMR = (EditText) root.findViewById(R.id.editHeightBmr);
        AgeBMR = (EditText) root.findViewById(R.id.editAgeBmr);
        CalculateBMR = (Button) root.findViewById(R.id.bmrCalculateButton);
        BMRInfo = (TextView) root.findViewById(R.id.bmrTextShow);
        maleBMR = (RadioButton) root.findViewById(R.id.maleBmr);
        FemaleBMR = (RadioButton) root.findViewById(R.id.femaleBmr);
        BMRInfo.setText("");

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CalculateBMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double weightBmrD = 0;
                double heightBmrD = 0;
                int ageBmr = 0;
                boolean genderStatus;
                boolean condition = true;

                if (weightBMR.getText().toString().equals("")&&heightBMR.getText().toString().equals("")
                        &&AgeBMR.getText().toString().equals("")&&!(maleBMR.isChecked()||FemaleBMR.isChecked())){
                    Toast.makeText(getContext(), "Enter Details Please!", Toast.LENGTH_SHORT).show();
                    condition = false;
                    BMRInfo.setText("");
                }else if (weightBMR.getText().toString().equals("")||heightBMR.getText().toString().equals("")
                        ||AgeBMR.getText().toString().equals("")||!(maleBMR.isChecked()||FemaleBMR.isChecked())){
                    BMRInfo.setText("");
                    if (weightBMR.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Weight Please!", Toast.LENGTH_SHORT).show();
                    }else if (heightBMR.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Height Please!", Toast.LENGTH_SHORT).show();
                    }else if (AgeBMR.getText().toString().equals("")) {
                        condition = false;
                        Toast.makeText(getContext(), "Enter Age Please!", Toast.LENGTH_SHORT).show();
                    }else if (!(maleBMR.isChecked()||FemaleBMR.isChecked())){
                        condition = false;
                        Toast.makeText(getContext(), "Select Gender Please!", Toast.LENGTH_SHORT).show();
                    }
                }
                if (!weightBMR.getText().toString().equals("")&&!heightBMR.getText().toString().equals("")
                        &&!AgeBMR.getText().toString().equals("")&&(maleBMR.isChecked()||FemaleBMR.isChecked())){
                    weightBmrD = Double.parseDouble(weightBMR.getText().toString().trim());
                    heightBmrD = Double.parseDouble(heightBMR.getText().toString().trim());
                    ageBmr = Integer.parseInt(AgeBMR.getText().toString().trim());
                    //noinspection UnusedAssignment
                    genderStatus = maleBMR.isChecked()||FemaleBMR.isChecked();
                    condition = true;
                }

                if (condition){
                    if (maleBMR.isChecked()){
                        double answer = CalculationUtils.getBMRCalculate(1,weightBmrD,heightBmrD,ageBmr);
                        //noinspection deprecation
                        BMRInfo.setText(Html.fromHtml("You need <b>"+answer+"</b> Calorie/day to Actively Work."));
                    }else if (FemaleBMR.isChecked()){
                        double answer = CalculationUtils.getBMRCalculate(2,weightBmrD,heightBmrD,ageBmr);
                        //noinspection deprecation
                        BMRInfo.setText(Html.fromHtml("You need <b>"+answer+"</b> Calorie/day to Actively Work."));
                    }
                }
            }
        });
    }
}
