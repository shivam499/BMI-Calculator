package com.pathantalabs.android.bmicalculator.ibwFragment;

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

public class IbwCalculateFragment extends Fragment{

    private EditText heightIBW;
    private EditText ageIBW;
    private RadioButton maleIBW;
    private RadioButton femaleIBW;
    private TextView IBWInfo;
    private Button IBWCalculate;
    public IbwCalculateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ibw_calculate,container,false);

        heightIBW = (EditText) root.findViewById(R.id.editHeightIbw);
        ageIBW = (EditText) root.findViewById(R.id.editAgeIbw);
        IBWInfo = (TextView) root.findViewById(R.id.IbwTextShow);
        IBWCalculate = (Button) root.findViewById(R.id.IbwCalculateButton);
        maleIBW = (RadioButton) root.findViewById(R.id.maleIbw);
        femaleIBW = (RadioButton) root.findViewById(R.id.femaleIbw);
        IBWInfo.setText("");

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        IBWCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double heightIbwD = 0;
                @SuppressWarnings("UnusedAssignment") int ageIbwD = 0;
                boolean condition = true;
                boolean genderStatus;
                if (heightIBW.getText().toString().equals("")&&ageIBW.getText().toString().equals("")
                        &&!(maleIBW.isChecked()||femaleIBW.isChecked())){
                    Toast.makeText(getContext(), "Enter Details Please!", Toast.LENGTH_SHORT).show();
                    condition = false;
                    IBWInfo.setText("");
                }else if (heightIBW.getText().toString().equals("")||ageIBW.getText().toString().equals("")
                        ||!(maleIBW.isChecked()||femaleIBW.isChecked())){
                    IBWInfo.setText("");
                    if (heightIBW.getText().toString().equals("")){
                        Toast.makeText(getContext(), "Enter Height Please!", Toast.LENGTH_SHORT).show();
                        condition = false;
                    }else if (ageIBW.getText().toString().equals("")){
                        Toast.makeText(getContext(), "Enter Age Please!", Toast.LENGTH_SHORT).show();
                        condition = false;
                    }else if (!(maleIBW.isChecked()||femaleIBW.isChecked())){
                        Toast.makeText(getContext(), "Select Gender Please!", Toast.LENGTH_SHORT).show();
                        condition = false;
                    }
                }
                if (!heightIBW.getText().toString().equals("")&&!ageIBW.getText().toString().equals("")
                        &&(maleIBW.isChecked()||femaleIBW.isChecked())){
                    heightIbwD = Double.parseDouble(heightIBW.getText().toString().trim());
                    //noinspection UnusedAssignment
                    ageIbwD = Integer.parseInt(ageIBW.getText().toString().trim());
                    //noinspection UnusedAssignment
                    genderStatus = maleIBW.isChecked()||femaleIBW.isChecked();
                    condition = true;
                }
                if (condition){
                    if (maleIBW.isChecked()){
                        double answer = CalculationUtils.getIBWCalculate(1, heightIbwD);
                        //noinspection deprecation
                        IBWInfo.setText(Html.fromHtml("Your Ideal Body Weight should be <b>"+answer+"</b> Kgs."));
                    }else if (femaleIBW.isChecked()){
                        double answer = CalculationUtils.getIBWCalculate(2, heightIbwD);
                        //noinspection deprecation
                        IBWInfo.setText(Html.fromHtml("Your Ideal Body Weight should be <b>"+answer+"</b> Kgs."));
                    }
                }
            }
        });
    }
}
