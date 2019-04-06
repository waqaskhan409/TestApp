package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wizy.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenThree extends BaseActivity {

    @BindView(R.id.tvAcademic)
    TextView tvAcademic;
    @BindView(R.id.tvHobby)
    TextView tvHobby;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screenthree);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvAcademic)
    public void onAcademicClick()
    {
        getAppPreferenceHelper().setUserScreenSignUpThree(tvAcademic.getText().toString());
        startActivity(new Intent(this,SignupScreenFour.class));
    }
    @OnClick(R.id.tvHobby)
    public void onHobbyClick() {
        getAppPreferenceHelper().setUserScreenSignUpThree(tvHobby.getText().toString());
        startActivity(new Intent(this,SignUpScreenFourForHobby.class));
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
