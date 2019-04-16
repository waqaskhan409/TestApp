package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.wizy.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenEight extends BaseActivity {
    /*Referencing the widgets through the bind view API*/
    /*Start*/
    @BindView(R.id.etOverview)
    EditText etOverview;
    /*end*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screeneight);
        ButterKnife.bind(this);
    }

    /*The below is to get Data and store to the shared preferences*/
    @OnClick(R.id.btnNext)
    public void onNextClick() {
        getAppPreferenceHelper().setScreenEight(etOverview.getText().toString());
        startActivity(new Intent(this, SignupScreenNine.class));
    }
    /*The below function is for Same font to the whole Activity*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
