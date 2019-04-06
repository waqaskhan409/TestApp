package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wizy.testapp.R;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.wizy.testapp.activity.SignupScreenOne.encrypt;

public class LoginVerification extends BaseActivity {
    private static final String TAG = "LoginVerification";
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

        private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
        private FirebaseAuth firebaseAuth;
        private String mVerificationId;
        String contactNo, password;
        FirebaseFirestore mDatabase;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_verification);
            ButterKnife.bind(this);

            Bundle data = getIntent().getExtras();
            contactNo = data.getString("contact_number");
            password = data.getString("password");
            edtNumber.setText(contactNo);
            firebaseAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseFirestore.getInstance();
            setCallback();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    getString(R.string.code) + edtNumber.getText().toString(),
                    60,
                    TimeUnit.SECONDS,
                    this,
                    mCallbacks);

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

        @OnClick(R.id.btnNext)
        public void onNextClick() {
            if (edtNumberOne.getText().length() == 0 && edtNumberTwo.getText().length() == 0 && edtNumberThree.getText().length() == 0
                    && edtNumberFour.getText().length() == 0 && edtNumberFive.getText().length() == 0 && edtNumberSix.getText().length() == 0) {
                Toast.makeText(this, getString(R.string.enter_correct_otp), Toast.LENGTH_SHORT).show();
                return;
            }
            showLoading();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, edtNumberOne.getText().toString() + edtNumberTwo.getText().toString() +
                    edtNumberThree.getText().toString() + edtNumberFour.getText().toString() + edtNumberFive.getText().toString() + edtNumberSix.getText().toString());
            signInWithPhoneAuthCredential(credential);
        }


        @OnClick(R.id.tvResendCode)
        public void onResendClick() {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    R.string.code + edtNumber.getText().toString(),
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
                                showLoading();
                                mDatabase.collection(getString(R.string.users)).document(FirebaseAuth.getInstance()
                                        .getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot snapshot = task.getResult();
                                            String encryptpassword = snapshot.getString("password");
                                            validateUser(encryptpassword);
                                            hideLoading();
                                        }else {
                                            Snackbar.make((findViewById(android.R.id.content)), "You Need to register your self", Snackbar.LENGTH_LONG)
                                                    .setAction("Sign Up", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            Intent intent = new Intent(LoginVerification.this, SignupScreenOne.class);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                            startActivity(intent);


                                                        }
                                                    })
                                                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                                                    .show();
                                        }
                                    }
                                });
                            } else {
                                if (task.getException() instanceof
                                        FirebaseAuthInvalidCredentialsException) {
                                    hideLoading();
                                }
                            }
                        }
                    });
        }
    private void validateUser(String encryptPassword) {


            String pass = password;

        Log.d(TAG, "validateUser: " + password + " = " + encryptPassword);
            if(pass.equals(encryptPassword)){
                Toast.makeText(this, "User Verified!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginVerification.this, RquestAndMessages.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else if(encryptPassword == null){
                Snackbar.make((findViewById(android.R.id.content)), "You Need to register your self", Snackbar.LENGTH_LONG)
                        .setAction("Sign Up", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(LoginVerification.this, SignupScreenOne.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);


                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                        .show();
//                Toast.makeText(this, "Password not Matched!", Toast.LENGTH_SHORT).show();Intent
                /*Intent intent = new Intent(LoginVerification.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/

            }else {
                Toast.makeText(this, "Password not Matched!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginVerification.this, LoginForteacher.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
    }

        @Override
        protected void attachBaseContext(Context newBase) {
            super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }
    }
