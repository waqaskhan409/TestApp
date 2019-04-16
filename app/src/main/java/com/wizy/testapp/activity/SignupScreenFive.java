package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wizy.testapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenFive extends BaseActivity {

    /*Referencing the widgets through the bind view API*/
    /*Start*/
    @BindView(R.id.LLScience)
    LinearLayout LLScience;

    @BindView(R.id.LLEnglish)
    LinearLayout LLEnglish;

    @BindView(R.id.LLMaths)
    LinearLayout LLMaths;

    @BindView(R.id.LLSocialStudies)
    LinearLayout LLSocialStudies;

    @BindView(R.id.LLLanguages)
    LinearLayout LLLanguages;

    @BindView(R.id.LLComputerScience)
    LinearLayout LLComputerScience;

    @BindView(R.id.ivScience)
    ImageView ivScience;

    @BindView(R.id.ivEnglish)
    ImageView ivEnglish;

    @BindView(R.id.ivMaths)
    ImageView ivMaths;

    @BindView(R.id.ivSocialStudies)
    ImageView ivSocialStudies;

    @BindView(R.id.ivLanguages)
    ImageView ivLanguages;

    @BindView(R.id.ivComputerScience)
    ImageView ivComputerScience;
    /*end*/
    /*Declaration of Variables*/
    private ArrayList<String> stringArrayList;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screenfive);
        ButterKnife.bind(this);
        /*Initialization*/
        stringArrayList = new ArrayList<>();
    }
    /*The below method is for to validate the user to select the subjects or subject*/
    @OnClick(R.id.btnNext)
    public void onNextClick() {
        stringBuilder = new StringBuilder();
        int size = stringArrayList.size();
        if (size > 0) {
            stringBuilder.append(stringArrayList.get(0));
            for (int i = 1; i < stringArrayList.size(); i++) {
                stringBuilder.append(", ").append(stringArrayList.get(i));
            }

        }

        if (stringBuilder.length() == 0) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.choose_subjects), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        getAppPreferenceHelper().setScreenFive(stringBuilder.toString());
        Intent intent = new Intent(this, SignupScreenSixForHobbies.class);
        intent.putExtra("category", getString(R.string.academics));

        startActivity(intent);
    }
    /*The below method is for select the subject Science*/
    @OnClick(R.id.LLScience)
    public void onScienceClick() {
        if (!ivScience.isShown()) {
            if (!stringArrayList.contains("science")) {
                stringArrayList.add("science");
            }
            ivScience.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("science");
            ivScience.setVisibility(View.GONE);
        }
    }
    /*The below method is for select the subject English*/
    @OnClick(R.id.LLEnglish)
    public void onEnglishClick() {
        if (!ivEnglish.isShown()) {
            if (!stringArrayList.contains("english")) {
                stringArrayList.add("english");
            }
            ivEnglish.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("english");
            ivEnglish.setVisibility(View.GONE);
        }
    }
    /*The below method is for select the subject Math*/
    @OnClick(R.id.LLMaths)
    public void onMathsClick() {
        if (!ivMaths.isShown()) {
            if (!stringArrayList.contains("maths")) {
                stringArrayList.add("maths");
            }
            ivMaths.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("maths");
            ivMaths.setVisibility(View.GONE);
        }
    }

    /*The below method is for select the subject SocailStudies*/
    @OnClick(R.id.LLSocialStudies)
    public void onSocialClick() {
        if (!ivSocialStudies.isShown()) {
            if (!stringArrayList.contains("socialstudies")) {
                stringArrayList.add("socialstudies");
            }
            ivSocialStudies.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("socialstudies");
            ivSocialStudies.setVisibility(View.GONE);
        }
    }
    /*The below method is for select the subject Lanuages*/
    @OnClick(R.id.LLLanguages)
    public void onLanguagesClick() {
        if (!ivLanguages.isShown()) {
            if (!stringArrayList.contains("languages")) {
                stringArrayList.add("languages");
            }
            ivLanguages.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("languages");
            ivLanguages.setVisibility(View.GONE);
        }
    }
    /*The below method is for select the subject Computer Science*/
    @OnClick(R.id.LLComputerScience)
    public void onComputerClick() {
        if (!ivComputerScience.isShown()) {
            if (!stringArrayList.contains("computer science")) {
                stringArrayList.add("computer science");
            }
            ivComputerScience.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("computerscience");
            ivComputerScience.setVisibility(View.GONE);
        }

    }
    /*The below function is for Same font to the whole Activity*/

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
