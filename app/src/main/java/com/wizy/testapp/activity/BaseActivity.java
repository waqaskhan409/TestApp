package com.wizy.testapp.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wizy.testapp.R;
import com.wizy.testapp.pref.AppPreferencesHelper;
import com.wizy.testapp.utills.AppConstants;

public class BaseActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private AppPreferencesHelper appPreferencesHelper;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        appPreferencesHelper = new AppPreferencesHelper(this, AppConstants.PREF_NAME);
    }

    public FirebaseFirestore getFirebaseStore() {
        return firebaseFirestore;
    }

    public AppPreferencesHelper getAppPreferenceHelper() {
        return appPreferencesHelper;
    }

    // Show Progress Dialog
    public void showLoading() {
        try {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hide Progress Dialog
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
}
