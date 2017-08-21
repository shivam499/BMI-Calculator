package com.pathantalabs.android.bmicalculator.lbmFragments;

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

public class LbmCalculateFragment extends Fragment{

    private EditText weightLBM;
    private EditText heightLBM;
    private EditText AgeLBM;
    private RadioButton maleLBM;
    private RadioButton femaleLBM;
    private Button CalculateLBM;
    private TextView LBMInfo;
    public LbmCalculateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lbm_calculate,container,false);

        weightLBM = (EditText) root.findViewById(R.id.editWeightLbm);
        heightLBM = (EditText) root.findViewById(R.id.editHeightLbm);
        AgeLBM = (EditText) root.findViewById(R.id.editAgeLbm);
        CalculateLBM = (Button) root.findViewById(R.id.LbmCalculateButton);
        LBMInfo = (TextView) root.findViewById(R.id.LbmTextShow);
        maleLBM = (RadioButton) root.findViewById(R.id.maleLbm);
        femaleLBM = (RadioButton) root.findViewById(R.id.femaleLbm);
        LBMInfo.setText("");

        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CalculateLBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double weightLbmD = 0;
                double heightLbmD = 0;
                @SuppressWarnings("UnusedAssignment") int ageLbm = 0;
                boolean genderStatus;
                boolean condition = true;

                if (weightLBM.getText().toString().equals("")&&heightLBM.getText().toString().equals("")
                        &&AgeLBM.getText().toString().equals("")&&!(maleLBM.isChecked()||femaleLBM.isChecked())){
                    Toast.makeText(getContext(), "Enter Details Please!", Toast.LENGTH_SHORT).show();
                    condition = false;
                    LBMInfo.setText("");
                }else if (weightLBM.getText().toString().equals("")||heightLBM.getText().toString().equals("")
                        ||AgeLBM.getText().toString().equals("")||!(maleLBM.isChecked()||femaleLBM.isChecked())){
                    LBMInfo.setText("");
                    if (weightLBM.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Weight Please!", Toast.LENGTH_SHORT).show();
                    }else if (heightLBM.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Height Please!", Toast.LENGTH_SHORT).show();
                    }else if (AgeLBM.getText().toString().equals("")) {
                        condition = false;
                        Toast.makeText(getContext(), "Enter Age Please!", Toast.LENGTH_SHORT).show();
                    }else if (!(maleLBM.isChecked()||femaleLBM.isChecked())){
                        condition = false;
                        Toast.makeText(getContext(), "Select Gender Please!", Toast.LENGTH_SHORT).show();
                    }
                }
                if (!weightLBM.getText().toString().equals("")&&!heightLBM.getText().toString().equals("")
                        &&!AgeLBM.getText().toString().equals("")&&(maleLBM.isChecked()||femaleLBM.isChecked())){
                    weightLbmD = Double.parseDouble(weightLBM.getText().toString().trim());
                    heightLbmD = Double.parseDouble(heightLBM.getText().toString().trim());
                    //noinspection UnusedAssignment
                    ageLbm = Integer.parseInt(AgeLBM.getText().toString().trim());
                    //noinspection UnusedAssignment
                    genderStatus = maleLBM.isChecked()||femaleLBM.isChecked();
                    condition = true;
                }

                if (condition){
                    if (maleLBM.isChecked()){
                        double answer = CalculationUtils.getLBMCalculate(1,weightLbmD,heightLbmD);
                        //noinspection deprecation
                        LBMInfo.setText(Html.fromHtml("You Lean Body mass is <b>"+answer+"</b> Kgs."));
                    }else if (femaleLBM.isChecked()){
                        double answer = CalculationUtils.getLBMCalculate(2,weightLbmD,heightLbmD);
                        //noinspection deprecation
                        LBMInfo.setText(Html.fromHtml("You Lean Body mass is <b>"+answer+"</b> Kgs."));
                    }
                }
            }
        });
    }
}
