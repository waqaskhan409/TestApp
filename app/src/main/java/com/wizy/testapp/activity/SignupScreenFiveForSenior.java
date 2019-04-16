package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

public class SignupScreenFiveForSenior extends BaseActivity{
    /*Referencing the widgets through the bind view API*/
    /*Start*/
        @BindView(R.id.LLBiology)
        LinearLayout LLBiology;

        @BindView(R.id.LLEnglish)
        LinearLayout LLEnglish;

        @BindView(R.id.LLMaths)
        LinearLayout LLMaths;

        @BindView(R.id.LLPhysics)
        LinearLayout LLPhysics;

        @BindView(R.id.LLChemistry)
        LinearLayout LLLChemistry;

        @BindView(R.id.LLComputerScience)
        LinearLayout LLComputerScience;

        @BindView(R.id.LLAccountacy)
        LinearLayout LLAccountacy;
        @BindView(R.id.LLBusiness)
        LinearLayout LLBusiness;
        @BindView(R.id.LLEconomics)
        LinearLayout LLEconomics;

        @BindView(R.id.ivBiology)
        ImageView ivBliology;
        @BindView(R.id.ivAccountacy)
        ImageView ivAccountacy;
        @BindView(R.id.ivBusiness)
        ImageView ivBusiness;
        @BindView(R.id.ivEconomics)
        ImageView ivEconomics;

        @BindView(R.id.ivEnglish)
        ImageView ivEnglish;

        @BindView(R.id.ivMaths)
        ImageView ivMaths;

        @BindView(R.id.ivPhysics)
        ImageView ivPhysics;

        @BindView(R.id.ivChemistry)
        ImageView ivChemistry;

        @BindView(R.id.ivComputerScience)
        ImageView ivComputerScience;
    /*end*/

    /*Declaration of Variables*/
        private ArrayList<String> stringArrayList;
        private StringBuilder stringBuilder;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signup_screenfive_for_senior);
            ButterKnife.bind(this);
            /*Initialization*/
            stringArrayList = new ArrayList<>();
        }

    /*The below method is for to validate the user to select the Subjects or subject*/
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
        /*The below method is for select the subject Biology*/
    @OnClick(R.id.LLBiology)
        public void onBiologyCLick() {
            if (!ivBliology.isShown()) {
                if (!stringArrayList.contains("biology")) {
                    stringArrayList.add("biology");
                }
                ivBliology.setVisibility(View.VISIBLE);
            } else {
                stringArrayList.remove("biology");
                ivBliology.setVisibility(View.GONE);
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
    /*The below method is for select the subject Maths*/
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
    /*The below method is for select the subject Physics*/
    @OnClick(R.id.LLPhysics)
        public void onSocialClick() {
            if (!ivPhysics.isShown()) {
                if (!stringArrayList.contains("physics")) {
                    stringArrayList.add("physics");
                }
                ivPhysics.setVisibility(View.VISIBLE);
            } else {
                stringArrayList.remove("physics");
                ivPhysics.setVisibility(View.GONE);
            }
        }
    /*The below method is for select the subject Chemistry*/
    @OnClick(R.id.LLChemistry)
        public void onChemistryClick() {
            if (!ivChemistry.isShown()) {
                if (!stringArrayList.contains("chemistry")) {
                    stringArrayList.add("chemistry");
                }
                ivChemistry.setVisibility(View.VISIBLE);
            } else {
                stringArrayList.remove("chemistry");
                ivChemistry.setVisibility(View.GONE);
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
                stringArrayList.remove("computer science");
                ivComputerScience.setVisibility(View.GONE);
            }

        }
    /*The below method is for select the subject Accountacy*/

    @OnClick(R.id.LLAccountacy)
    public void onAccountacyClick() {
        if (!ivAccountacy.isShown()) {
            if (!stringArrayList.contains("accountancy")) {
                stringArrayList.add("accountancy");
            }
            ivAccountacy.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("accountancy");
            ivAccountacy.setVisibility(View.GONE);
        }

    }
    /*The below method is for select the subject Economics*/
    @OnClick(R.id.LLEconomics)
    public void onEconomicsClick() {
        if (!ivEconomics.isShown()) {
            if (!stringArrayList.contains("economics")) {
                stringArrayList.add("economics");
            }
            ivEconomics.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("economics");
            ivEconomics.setVisibility(View.GONE);
        }

    }
    /*The below method is for select the subject Business*/
    @OnClick(R.id.LLBusiness)
    public void onBusinessClick() {
        if (!ivBusiness.isShown()) {
            if (!stringArrayList.contains("business studies")) {
                stringArrayList.add("business studies");
            }
            ivBusiness.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("business studies");
            ivBusiness.setVisibility(View.GONE);
        }

    }
    /*The below function is for Same font to the whole Activity*/

    @Override
        protected void attachBaseContext(Context newBase) {
            super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }


}
