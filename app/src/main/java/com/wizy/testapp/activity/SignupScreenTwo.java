package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.wizy.testapp.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenTwo extends BaseActivity {
    /*Referencing the widgets through the bind view API*/
    /*Start*/
    @BindView(R.id.edtNumber)
    EditText edtNumber;

    @BindView(R.id.edtNumberOne)
    EditText edtNumberOne;

    @BindView(R.id.edtNumberTwo)
    EditText edtNumberTwo;

    @BindView(R.id.edtNumberThree)
    EditText edtNumberThree;

    @BindView(R.id.edtNumberFour)
    EditText edtNumberFour;

    @BindView(R.id.edtNumberFive)
    EditText edtNumberFive;

    @BindView(R.id.edtNumberSix)
    EditText edtNumberSix;
    /*end*/

    /*Declaration of Variables*/

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth firebaseAuth;
    private String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screentwo);
        ButterKnife.bind(this);
        /*Initialization the Variables start*/

        edtNumber.setText(getAppPreferenceHelper().getUserPhone());
        firebaseAuth = FirebaseAuth.getInstance();
        /*end*/
        setCallback();
        /*The below function is for sending the sms to the Contact number */
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                getString(R.string.code) + edtNumber.getText().toString(),
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
        /*First EditText for the manual otp varification*/
        edtNumberOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edtNumberTwo.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /*Two EditText for the manual otp varification*/
        edtNumberTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edtNumberThree.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /*Three EditText for the manual otp varification*/
        edtNumberThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edtNumberFour.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /*Four EditText for the manual otp varification*/

        edtNumberFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edtNumberFive.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /*Five EditText for the manual otp varification*/
        edtNumberFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edtNumberSix.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    // PhoneAuthProvider callback
    private void setCallback() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                } else if (e instanceof FirebaseTooManyRequestsException) {

                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;


            }
        };

    }
    /*The below method is for to validate the user the contact number is either valid or not*/

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        /*The below method is for to validate the user from the client*/

        if (edtNumberOne.getText().length() == 0 && edtNumberTwo.getText().length() == 0 && edtNumberThree.getText().length() == 0
                && edtNumberFour.getText().length() == 0 && edtNumberFive.getText().length() == 0 && edtNumberSix.getText().length() == 0) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.enter_correct_otp), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        showLoading();
        /*The below Code is for to validate the user from the server side*/
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, edtNumberOne.getText().toString() + edtNumberTwo.getText().toString() +
                edtNumberThree.getText().toString() + edtNumberFour.getText().toString() + edtNumberFive.getText().toString() + edtNumberSix.getText().toString());
        signInWithPhoneAuthCredential(credential);
    }

    /*This is resend function is for sending the sms to the Contact number */
    @OnClick(R.id.tvResendCode)
    public void onResendClick() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                getString(R.string.code) + edtNumber.getText().toString(),
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
        Toast.makeText(this,R.string.sending,Toast.LENGTH_SHORT).show();
    }


    // Verifying OTP
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            hideLoading();
                            getAppPreferenceHelper().setUserId(task.getResult().getUser().getUid());
                            getAppPreferenceHelper().setUserPhone(edtNumber.getText().toString());
                            startActivity(new Intent(SignupScreenTwo.this, SignupScreenThree.class));
                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                hideLoading();
                            }
                        }
                    }
                });
    }
    /*The below function is for Same font to the whole Activity*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
