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

    @BindView(R.id.etOverview)
    EditText etOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screeneight);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        getAppPreferenceHelper().setScreenEight(etOverview.getText().toString());
        startActivity(new Intent(this, SignupScreenNine.class));
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
