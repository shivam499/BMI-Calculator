package com.pathantalabs.android.bmicalculator.bsaFragment;

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
import com.pathantalabs.android.bmicalculator.app.CalculationUtils;

public class BsaCalculateFragment extends Fragment{

    private EditText weightBSA;
    private EditText heightBSA;
    private Button CalculateBSA;
    private TextView BSAInfo;
    public BsaCalculateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bsa_calculate,container,false);

        weightBSA = (EditText) root.findViewById(R.id.editWeightBsa);
        heightBSA = (EditText) root.findViewById(R.id.editHeightBsa);
        CalculateBSA = (Button) root.findViewById(R.id.BsaCalculateButton);
        BSAInfo = (TextView) root.findViewById(R.id.BsaTextShow);
        BSAInfo.setText("");

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CalculateBSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double weightBsaD = 0;
                double heightBsaD = 0;
                boolean condition = true;
                if (weightBSA.getText().toString().equals("")&&heightBSA.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Enter Details Please!", Toast.LENGTH_SHORT).show();
                    condition = false;
                    BSAInfo.setText("");
                }else if(weightBSA.getText().toString().equals("")||heightBSA.getText().toString().equals("")){
                    BSAInfo.setText("");
                    if (weightBSA.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Weight Please!", Toast.LENGTH_SHORT).show();
                    }else if (heightBSA.getText().toString().equals("")){
                        condition = false;
                        Toast.makeText(getContext(), "Enter Height Please!", Toast.LENGTH_SHORT).show();
                    }
                }
                if (!weightBSA.getText().toString().equals("")&&!heightBSA.getText().toString().equals("")){
                    condition = true;
                    weightBsaD = Double.parseDouble(weightBSA.getText().toString().trim());
                    heightBsaD = Double.parseDouble(heightBSA.getText().toString().trim());
                }

                if (condition){
                    double answer = CalculationUtils.getBSACalculate(weightBsaD,heightBsaD);
                    //noinspection deprecation
                    BSAInfo.setText(Html.fromHtml("Your BSA is <b>"+answer+"</b> m²."));
                }
            }
        });
    }
}
