package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.wizy.testapp.R;
import com.wizy.testapp.utills.AppConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenTen extends BaseActivity {

    @BindView(R.id.tvMonthly)
    TextView tvMonthly;

    @BindView(R.id.tvWeekly)
    TextView tvWeekly;

    @BindView(R.id.tvAsPerClass)
    TextView tvAsPerClass;

    @BindView(R.id.tvChapterWise)
    TextView tvChapterWise;

    @BindView(R.id.tvConceptWise)
    TextView tvConceptWise;

    @BindView(R.id.bundleInformation)
    RelativeLayout rlBundleInfo;

    @BindView(R.id.hintText)
    TextView hintText;

    private String chargesType = "";
    private String chargeDoubt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screenten);
        ButterKnife.bind(this);
    }
    int count = 0;

    @OnClick(R.id.tvMonthly)
    public void onMonthlyClick() {
        chargesType = tvMonthly.getText().toString();
        tvMonthly.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvMonthly.setTextColor(Color.parseColor("#ffffff"));
        tvWeekly.setBackgroundResource(R.drawable.reactangle_cert);
        tvWeekly.setTextColor(Color.parseColor("#ff707070"));
        tvAsPerClass.setBackgroundResource(R.drawable.reactangle_cert);
        tvAsPerClass.setTextColor(Color.parseColor("#ff707070"));
    }

    @OnClick(R.id.information)
    public void onInformationClick(){
        if(rlBundleInfo.getVisibility() == View.VISIBLE){
            rlBundleInfo.setVisibility(View.GONE);
        }else {
            rlBundleInfo.setVisibility(View.VISIBLE);
        }

    }
    @OnClick(R.id.tvWeekly)
    public void onWeeklyClick() {
        chargesType = tvWeekly.getText().toString();
        tvMonthly.setBackgroundResource(R.drawable.reactangle_cert);
        tvMonthly.setTextColor(Color.parseColor("#ff707070"));
        tvWeekly.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvWeekly.setTextColor(Color.parseColor("#ffffff"));
        tvAsPerClass.setBackgroundResource(R.drawable.reactangle_cert);
        tvAsPerClass.setTextColor(Color.parseColor("#ff707070"));
    }

    @OnClick(R.id.tvAsPerClass)
    public void onPerClassClick() {
        chargesType = tvAsPerClass.getText().toString();
        tvWeekly.setBackgroundResource(R.drawable.reactangle_cert);
        tvWeekly.setTextColor(Color.parseColor("#ff707070"));
        tvMonthly.setBackgroundResource(R.drawable.reactangle_cert);
        tvMonthly.setTextColor(Color.parseColor("#ff707070"));
        tvAsPerClass.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvAsPerClass.setTextColor(Color.parseColor("#ffffff"));
    }

    @OnClick(R.id.tvChapterWise)
    public void onChapterClick() {
        chargeDoubt = tvChapterWise.getText().toString();
        tvChapterWise.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvChapterWise.setTextColor(Color.parseColor("#ffffff"));
        tvConceptWise.setBackgroundResource(R.drawable.reactangle_cert);
        tvConceptWise.setTextColor(Color.parseColor("#ff707070"));
    }

    @OnClick(R.id.tvConceptWise)
    public void onConceptClick() {
        chargeDoubt = tvConceptWise.getText().toString();
        tvChapterWise.setBackgroundResource(R.drawable.reactangle_cert);
        tvChapterWise.setTextColor(Color.parseColor("#ff707070"));
        tvConceptWise.setBackgroundResource(R.drawable.reactangle_cert_black);
        tvConceptWise.setTextColor(Color.parseColor("#ffffff"));
    }

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        if (chargesType.equals("")) {
            Toast.makeText(this, R.string.select_chargestype, Toast.LENGTH_SHORT).show();
            return;
        }
        if (chargeDoubt.equals("")) {
            Toast.makeText(this, R.string.select_chargesdoubt, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, RquestAndMessages.class);
        startActivity(intent);
        sendData();
    }

    // Sending data to Firebase FireStore
    private void sendData() {
        showLoading();
        Map<String, Object> user = new HashMap<>();
        user.put(AppConstants.KEY_TITLE, getAppPreferenceHelper().getUserTitle());
        user.put(AppConstants.KEY_FIRST_NAME, getAppPreferenceHelper().getUserFirstName());
        user.put(AppConstants.KEY_LAST_NAME, getAppPreferenceHelper().getUserLastName());
        user.put(AppConstants.PROFILE_IMAGE, getAppPreferenceHelper().getProfileImage());
        user.put(AppConstants.CERTIFICATE_IMAGE, getAppPreferenceHelper().getCertificatesImage());
        user.put(AppConstants.PROOF_DOCUMENT_IMAGE, getAppPreferenceHelper().getProofImage());
        user.put(AppConstants.KEY_CONTACT_NUMBER, getAppPreferenceHelper().getUserPhone());
        user.put(AppConstants.KEY_PASSWORD, getAppPreferenceHelper().getUserPassword());
        user.put(AppConstants.KEY_CATEGORY, getAppPreferenceHelper().getPrefKeyCategory());
        user.put(AppConstants.KEY_CLASSES, getAppPreferenceHelper().getClasses());
        user.put(AppConstants.KEY_SUBJECTS, getAppPreferenceHelper().getSubjects());
        user.put(AppConstants.KEY_CURRENT_OCCUPATION, getAppPreferenceHelper().getOccupation());
        user.put(AppConstants.KEY_TEACHING_EXPERINCE, getAppPreferenceHelper().getExperince());
        user.put(AppConstants.KEY_QUALIFICATION, getAppPreferenceHelper().getQualification());
        user.put(AppConstants.KEY_AREA_QUALIFICATION, getAppPreferenceHelper().getArea());
        user.put(AppConstants.KEY_UNIVERSITY, getAppPreferenceHelper().getUniversity());
        user.put(AppConstants.KEY_SPECIALISATION, getAppPreferenceHelper().getSpecialisation());
        user.put(AppConstants.KEY_QUALIFICATION_YEAR, getAppPreferenceHelper().getYear());
        user.put(AppConstants.KEY_BOARD, getAppPreferenceHelper().getboard());
        user.put(AppConstants.KEY_ACADEMIC_EXPERINCE, getAppPreferenceHelper().getexperinceLevel());
        user.put(AppConstants.KEY_HOURS_AVAILABLE, getAppPreferenceHelper().getworkingHour());
        user.put(AppConstants.KEY_OVERVIEW, getAppPreferenceHelper().getOverview());
        user.put(AppConstants.KEY_CLASSES_PLACE, getAppPreferenceHelper().getClassesPlace());
        user.put(AppConstants.KEY_CHARGES_TYPE, chargesType);
        user.put(AppConstants.KEY_CHARGES_DOUBT, chargeDoubt);

        getFirebaseStore().collection("users").document(getAppPreferenceHelper().getUserId()).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        hideLoading();
                        Toast.makeText(SignupScreenTen.this, R.string.sign_up_message, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignupScreenTen.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        hideLoading();
                        Toast.makeText(SignupScreenTen.this, R.string.error, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
