package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.wizy.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenNine extends BaseActivity {

    @BindView(R.id.tvHome)
    TextView tvHome;

    @BindView(R.id.tvStudentHome)
    TextView tvStudentHome;

    private String classesPlace = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screennine);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        if (classesPlace.equals("")) {
            Toast.makeText(this, R.string.select_place, Toast.LENGTH_SHORT).show();
            return;
        }
        getAppPreferenceHelper().setScreenNine(classesPlace);
        startActivity(new Intent(this, SignupScreenTen.class));
    }

    @OnClick(R.id.tvHome)
    public void onHomeClick() {
        classesPlace = tvHome.getText().toString();
        tvHome.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvHome.setTextColor(Color.parseColor("#ffffff"));
        tvStudentHome.setBackgroundResource(R.drawable.reactangle_cert);
        tvStudentHome.setTextColor(Color.parseColor("#ff707070"));
    }

    @OnClick(R.id.tvStudentHome)
    public void onStudentClick() {
        classesPlace = tvStudentHome.getText().toString();
        tvStudentHome.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvStudentHome.setTextColor(Color.parseColor("#ffffff"));
        tvHome.setBackgroundResource(R.drawable.reactangle_cert);
        tvHome.setTextColor(Color.parseColor("#ff707070"));
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
