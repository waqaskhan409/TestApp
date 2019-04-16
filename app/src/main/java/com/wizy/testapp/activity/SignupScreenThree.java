package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wizy.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenThree extends BaseActivity {

    @BindView(R.id.rlAcademics)
    RelativeLayout tvAcademic;
    @BindView(R.id.rlHobby)
    RelativeLayout tvHobby;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screenthree);
        ButterKnife.bind(this);
    }

    /*The below method is for select the user's Academics area */
    @OnClick(R.id.rlAcademics)
    public void onAcademicClick()
    {
        getAppPreferenceHelper().setUserScreenSignUpThree(getString(R.string.academics));
        startActivity(new Intent(this,SignupScreenFour.class));
    }
    /*The below method is for select the user's Hobby area */

    @OnClick(R.id.rlHobby)
    public void onHobbyClick() {
        getAppPreferenceHelper().setUserScreenSignUpThree(getString(R.string.hobby));
        startActivity(new Intent(this,SignUpScreenFourForHobby.class));
    }
    /*The below function is for Same font to the whole Activity*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
