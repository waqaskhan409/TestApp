package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wizy.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenNine extends BaseActivity {
    /*Referencing the widgets through the bind view API*/
    /*Start*/
    @BindView(R.id.tvHome)
    TextView tvHome;

    @BindView(R.id.tvStudentHome)
    TextView tvStudentHome;
    /*end*/
    /*Declaration and initialization of Variables*/
    private String classesPlace = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screennine);
        ButterKnife.bind(this);
    }
    /*The below validate the class places either its empty or not and store to the shared preferences*/
    @OnClick(R.id.btnNext)
    public void onNextClick() {
        if (classesPlace.equals("")) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.select_place), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        getAppPreferenceHelper().setScreenNine(classesPlace);
        startActivity(new Intent(this, SignupScreenTen.class));
    }
    /*The below  method is to select the student to teach at teacher's home*/
    @OnClick(R.id.tvHome)
    public void onHomeClick() {
        classesPlace = tvHome.getText().toString();
        tvHome.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvHome.setTextColor(Color.parseColor("#ffffff"));
        tvStudentHome.setBackgroundResource(R.drawable.reactangle_cert);
        tvStudentHome.setTextColor(Color.parseColor("#ff707070"));
    }
/*The below  method is to select the student to teach at student's home*/
    @OnClick(R.id.tvStudentHome)
    public void onStudentClick() {
        classesPlace = tvStudentHome.getText().toString();
        tvStudentHome.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvStudentHome.setTextColor(Color.parseColor("#ffffff"));
        tvHome.setBackgroundResource(R.drawable.reactangle_cert);
        tvHome.setTextColor(Color.parseColor("#ff707070"));
    }
    /*The below function is for Same font to the whole Activity*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
