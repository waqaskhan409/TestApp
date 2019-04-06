package com.wizy.testapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wizy.testapp.R;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.wizy.testapp.activity.SignupScreenOne.encrypt;

public class LoginForteacher extends AppCompatActivity {
    private static final String TAG = "LoginForteacher";
    private TextView loginName, testApp, countryCode;
    private EditText mContactNo, mPassword;
    private Button mLogin;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forteacher);


        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.otf");
        loginName = findViewById(R.id.login);
        testApp = findViewById(R.id.testApp);
        mContactNo = findViewById(R.id.edtNumber);
        mPassword = findViewById(R.id.passwordForLogin);
        mLogin = findViewById(R.id.btnLogin);
        countryCode = findViewById(R.id.tvNoText);
        mDatabase = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        final ProgressDialog dialog = ProgressDialog.show(LoginForteacher.this, "Loging in User",
                "Loading. Please wait...", true);
        dialog.show();
                if(mContactNo.getText().toString().equals("")){
                    dialog.dismiss();
                    Toast.makeText(LoginForteacher.this, R.string.enter_phone, Toast.LENGTH_SHORT).show();

                }else if(mPassword.getText().toString().equals("")){

                    dialog.dismiss();
                    Toast.makeText(LoginForteacher.this, R.string.enter_password, Toast.LENGTH_SHORT).show();

                }else {
                    validateUser();
                    dialog.dismiss();
                    /*mDatabase.collection(getString(R.string.users)).document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot snapshot = task.getResult();
                                String encryptpassword = snapshot.getString("password");
//                                validateUser(encryptpassword);
                                dialog.dismiss();
                            }
                        }
                    });*/
                }
            }

        });





    }

    private void validateUser() {


        try {
            String pass = encrypt(mPassword.getText().toString());
            String cont = mContactNo.getText().toString();
            Intent intent = new Intent(this, LoginVerification.class);
            intent.putExtra("contact_number", cont);
            intent.putExtra("password", pass);
            startActivity(intent);

        } catch (NoSuchPaddingException e) {
                e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
