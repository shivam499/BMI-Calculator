package com.pathantalabs.android.bmicalculator.lbmFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pathantalabs.android.bmicalculator.R;

public class LbmInfoFragment extends Fragment{
    private TextView QuestionText;
    private TextView AnswerText;
    public LbmInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_info,container,false);
        QuestionText = (TextView) root.findViewById(R.id.textQuestion);
        AnswerText = (TextView) root.findViewById(R.id.textData);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //noinspection deprecation
        QuestionText.setText(Html.fromHtml(getString(R.string.lbm_ques)));
        //noinspection deprecation
        AnswerText.setText(Html.fromHtml(getString(R.string.lbm_ans)));
    }
}
