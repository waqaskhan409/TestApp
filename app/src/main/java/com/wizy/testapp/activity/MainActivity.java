package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.wizy.testapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends BaseActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        /*
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, RquestAndMessages.class));
            finish();
        }
*/

    }

    /*Redirect to the login Activity*/
    @OnClick(R.id.btnLogin)
    public void onLoginClick() {
        startActivity(new Intent(this, LoginForteacher.class));
        finish();
    }

    /*Redirect to the Registration Activity*/
    @OnClick(R.id.btnSignup)
    public void onSignupClick() {
        startActivity(new Intent(this, SignupScreenOne.class));
        finish();
    }

    /*The below function is for Same font to the whole Activity*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
