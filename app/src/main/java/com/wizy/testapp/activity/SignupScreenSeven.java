package com.wizy.testapp.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wizy.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenSeven extends BaseActivity {
    /*Referencing the widgets through the bind view API*/
    /*Start*/
    @BindView(R.id.tvSelectExperince)
    TextView tvSelectExperince;

    @BindView(R.id.tvSelectWorkingHours)
    TextView tvSelectWorkingHours;

    @BindView(R.id.tvStartingOut)
    TextView tvStartingOut;

    @BindView(R.id.tvIntermediate)
    TextView tvIntermediate;

    @BindView(R.id.tvExpert)
    TextView tvExpert;

    @BindView(R.id.tvmoreThan30Hour)
    TextView tvmoreThan30Hour;

    @BindView(R.id.tvlessThan30Hour)
    TextView tvlessThan30Hour;

    @BindView(R.id.tvasWhenNeeded)
    TextView tvasWhenNeeded;

    @BindView(R.id.tvCBSE)
    TextView tvCBSE;

    @BindView(R.id.tvICSE)
    TextView tvICSE;

    @BindView(R.id.tvStateBoard)
    TextView tvStateBoard;

    @BindView(R.id.LLExpDropdown)
    LinearLayout LLExpDropdown;

    @BindView(R.id.LLWorkingHours)
    LinearLayout LLWorkingHours;

    @BindView(R.id.LLStartingOut)
    LinearLayout LLStartingOut;

    @BindView(R.id.LLIntermediate)
    LinearLayout LLIntermediate;

    @BindView(R.id.LLExpert)
    LinearLayout LLExpert;
    /*end*/
    /*Declaration and initialization of Variables*/
    private String board = "";
    private String experince = "";
    private String workingHour = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screenseven);
        ButterKnife.bind(this);
    }

    /*The below method is for to validate the user to select the board, experience and working hour then store the data
    * in the shared preferences
    * */
    @OnClick(R.id.btnNext)
    public void onNextClick() {
        if (board.equals("")) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.select_board_message), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        if (experince.equals("")) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.select_exp_message), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        if (workingHour.equals("")) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.select_hour_message), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        getAppPreferenceHelper().setUserScreenSeven(board, experince, workingHour);

        startActivity(new Intent(this, SignupScreenEight.class));
    }
    /*The below method is to select the board and deselect the another ones*/
    @OnClick(R.id.tvCBSE)
    public void onCBSEClick() {
        board = "CBSE";
        tvCBSE.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvCBSE.setTextColor(Color.parseColor("#ffffff"));
        tvICSE.setBackgroundResource(R.drawable.reactangle_cert);
        tvICSE.setTextColor(Color.parseColor("#000000"));
        tvStateBoard.setBackgroundResource(R.drawable.reactangle_cert);
        tvStateBoard.setTextColor(Color.parseColor("#000000"));
    }
    /*The below method is to select the board and deselect the another ones*/
    @OnClick(R.id.tvICSE)
    public void onICSEClick() {
        board = "ICSE";
        tvCBSE.setBackgroundResource(R.drawable.reactangle_cert);
        tvCBSE.setTextColor(Color.parseColor("#000000"));
        tvICSE.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvICSE.setTextColor(Color.parseColor("#ffffff"));
        tvStateBoard.setBackgroundResource(R.drawable.reactangle_cert);
        tvStateBoard.setTextColor(Color.parseColor("#000000"));
    }
    /*The below method is to select the board and deselect the another ones*/
    @OnClick(R.id.tvStateBoard)
    public void onStateBoardClick() {
        board = "State Board";
        tvCBSE.setBackgroundResource(R.drawable.reactangle_cert);
        tvCBSE.setTextColor(Color.parseColor("#000000"));
        tvICSE.setBackgroundResource(R.drawable.reactangle_cert);
        tvICSE.setTextColor(Color.parseColor("#000000"));
        tvStateBoard.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvStateBoard.setTextColor(Color.parseColor("#ffffff"));
    }
    /*Show drop down menu*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectExperince)
    public void onExpClick() {
        if (!LLExpDropdown.isShown()) {
            /*Setting responsive icon*/
            tvSelectExperince.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_upxhdpi, 0);

            LLExpDropdown.setVisibility(View.VISIBLE);
        } else {
            LLExpDropdown.setVisibility(View.GONE);
            /*Setting responsive icon*/
            tvSelectExperince.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

        }

    }
    /*Show drop down menu*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectWorkingHours)
    public void onHoursClick() {
        if (!LLWorkingHours.isShown()) {
            LLWorkingHours.setVisibility(View.VISIBLE);
            /*Setting responsive icon*/
            tvSelectWorkingHours.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_upxhdpi, 0);

        } else {
            LLWorkingHours.setVisibility(View.GONE);
            /*Setting responsive icon*/
            tvSelectWorkingHours.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.LLStartingOut)
    public void onStartingOutClick() {
        experince = tvStartingOut.getText().toString();
        /*Setting responsive icon*/

        tvSelectExperince.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

        tvSelectExperince.setText(tvStartingOut.getText().toString());
        LLExpDropdown.setVisibility(View.GONE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.LLIntermediate)
    public void onIntermediateClick() {
        experince = tvIntermediate.getText().toString();
        /*Setting responsive icon*/

        tvSelectExperince.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

        tvSelectExperince.setText(tvIntermediate.getText().toString());
        LLExpDropdown.setVisibility(View.GONE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.LLExpert)
    public void onExpertClick() {
        experince = tvExpert.getText().toString();
        /*Setting responsive icon*/
        tvSelectExperince.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

        tvSelectExperince.setText(tvExpert.getText().toString());
        LLExpDropdown.setVisibility(View.GONE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvmoreThan30Hour)
    public void onMoreClick() {
        workingHour = tvmoreThan30Hour.getText().toString();
                   /*Setting responsive icon*/

        tvSelectWorkingHours.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

        tvSelectWorkingHours.setText(tvmoreThan30Hour.getText().toString());
        LLWorkingHours.setVisibility(View.GONE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvlessThan30Hour)
    public void onLessClick() {
        workingHour = tvlessThan30Hour.getText().toString();
        /*Setting responsive icon*/
        tvSelectWorkingHours.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

        tvSelectWorkingHours.setText(tvlessThan30Hour.getText().toString());
        LLWorkingHours.setVisibility(View.GONE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvasWhenNeeded)
    public void onNeededClick() {
        workingHour = tvasWhenNeeded.getText().toString();
        /*Setting responsive icon*/
        tvSelectWorkingHours.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

        tvSelectWorkingHours.setText(tvasWhenNeeded.getText().toString());
        LLWorkingHours.setVisibility(View.GONE);
    }
    /*The below function is for Same font to the whole Activity*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
