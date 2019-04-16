package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wizy.testapp.R;
import com.wizy.testapp.utills.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenTen extends BaseActivity {
    private static final String TAG = "SignupScreenTen";
    /*Referencing the widgets through the bind view API*/
    /*Start*/

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
    /*end*/
    /*Declaration and initialization of Variables*/
    private String chargesType = "";
    private String chargeDoubt = "";
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> arrayList1 = new ArrayList<>();
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screenten);
        ButterKnife.bind(this);
        /*Initialization*/
        mStorage = FirebaseStorage.getInstance().getReference();
    }
    int count = 0;
    /*The below  method is to select and deselect the student to teach on the monthly bases*/
    @OnClick(R.id.tvMonthly)
    public void onMonthlyClick() {
        if(tvMonthly.getCurrentTextColor() == Color.parseColor("#ffffff")){
            arrayList.remove(tvMonthly.getText().toString());
            Log.d(TAG, "onMonthlyClick: "+ arrayList);
            tvMonthly.setBackgroundResource(R.drawable.reactangle_cert);
            tvMonthly.setTextColor(Color.parseColor("#ff707070"));
        }else {
            arrayList.add(tvMonthly.getText().toString());
            Log.d(TAG, "onMonthlyClick: "+ arrayList);
            tvMonthly.setBackgroundResource(R.drawable.reactangle_cert_black);
            tvMonthly.setTextColor(Color.parseColor("#ffffff"));
        }
    }
    /*The below  method to show text in the black background to show the hint*/
    @OnClick(R.id.information)
    public void onInformationClick(){
        if(rlBundleInfo.getVisibility() == View.VISIBLE){
            rlBundleInfo.setVisibility(View.GONE);
        }else {
            rlBundleInfo.setVisibility(View.VISIBLE);
        }

    }
    /*The below  method is to select and deselect the student to teach on the weekly bases*/
    @OnClick(R.id.tvWeekly)
    public void onWeeklyClick() {
//        chargesType = tvWeekly.getText().toString();
        if(tvWeekly.getCurrentTextColor() == Color.parseColor("#ffffff")){
            arrayList.remove(tvWeekly.getText().toString());
            Log.d(TAG, "tvWeekly: "+ arrayList);
            tvWeekly.setBackgroundResource(R.drawable.reactangle_cert);
            tvWeekly.setTextColor(Color.parseColor("#ff707070"));
        }else {
            arrayList.add(tvWeekly.getText().toString());
            Log.d(TAG, "tvWeekly: "+ arrayList);
            tvWeekly.setBackgroundResource(R.drawable.reactangle_cert_black);
            tvWeekly.setTextColor(Color.parseColor("#ffffff"));
        }

    }
    /*The below  method is to select and deselect the student to teach on the As per class bases*/

    @OnClick(R.id.tvAsPerClass)
    public void onPerClassClick() {
        if(tvAsPerClass.getCurrentTextColor() == Color.parseColor("#ffffff")){
            arrayList.remove(tvAsPerClass.getText().toString());
            Log.d(TAG, "tvAsPerClass: "+ arrayList);
            tvAsPerClass.setBackgroundResource(R.drawable.reactangle_cert);
            tvAsPerClass.setTextColor(Color.parseColor("#ff707070"));
        }else {
            arrayList.add(tvAsPerClass.getText().toString());
            Log.d(TAG, "tvAsPerClass: "+ arrayList);
            tvAsPerClass.setBackgroundResource(R.drawable.reactangle_cert_black);
            tvAsPerClass.setTextColor(Color.parseColor("#ffffff"));
        }


    }
    /*The below  method is to select and deselect the student to teach on the Chapter Wise*/
    @OnClick(R.id.tvChapterWise)
    public void onChapterClick() {
        if(tvChapterWise.getCurrentTextColor() == Color.parseColor("#ffffff")){
            arrayList1.remove(tvChapterWise.getText().toString());
            Log.d(TAG, "tvChapterWise: "+ arrayList1);
            tvChapterWise.setBackgroundResource(R.drawable.reactangle_cert);
            tvChapterWise.setTextColor(Color.parseColor("#ff707070"));
        }else {
            arrayList1.add(tvChapterWise.getText().toString());
            Log.d(TAG, "tvChapterWise: "+ arrayList1);
            tvChapterWise.setBackgroundResource(R.drawable.reactangle_cert_black);
            tvChapterWise.setTextColor(Color.parseColor("#ffffff"));
        }

    }
    /*The below  method is to select and deselect the student to teach on the Concept wise*/

    @OnClick(R.id.tvConceptWise)
    public void onConceptClick() {
        if(tvConceptWise.getCurrentTextColor() == Color.parseColor("#ffffff")){
            arrayList1.remove(tvConceptWise.getText().toString());
            Log.d(TAG, "tvConceptWise: "+ arrayList1);
            tvConceptWise.setBackgroundResource(R.drawable.reactangle_cert);
            tvConceptWise.setTextColor(Color.parseColor("#ff707070"));
        }else {
            arrayList1.add(tvConceptWise.getText().toString());
            Log.d(TAG, "tvConceptWise: "+ arrayList1);
            tvConceptWise.setBackgroundResource(R.drawable.reactangle_cert_black);
            tvConceptWise.setTextColor(Color.parseColor("#ffffff"));
        }

    }
    /*The below validate the class teaching expances either its empty or not and store to the shared preferences*/
    @OnClick(R.id.btnNext)
    public void onNextClick() {
        for (int i = 0; i < arrayList1.size(); i++) {
            chargeDoubt = chargeDoubt + arrayList1.get(i);
            Log.d(TAG, "onNextClick: " + chargeDoubt );
        }

        for (int i = 0; i < arrayList.size(); i++) {
            chargesType = chargesType + arrayList.get(i);
            Log.d(TAG, "onNextClick: " + chargesType );
        }

        if (chargesType.equals("")) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.select_chargestype), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        if (chargeDoubt.equals("")) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.select_chargesdoubt), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        Intent intent = new Intent(this, RquestAndMessages.class);
        startActivity(intent);
        sendData();
    }

    // Sending data to Firebase FireStore
    private void sendData() {

        mStorage = mStorage.child("users/profiles/proof_document/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "Original");
        UploadTask uploadTask =  mStorage.putFile(Uri.parse(getAppPreferenceHelper().getProofImage()));
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {

                    Snackbar snackbar;
                    snackbar = Snackbar.make((findViewById(android.R.id.content)), "Error in image loading", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    view.setBackgroundColor(Color.parseColor("#ba0505"));
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return mStorage.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    Log.d(TAG, "onComplete: "+ downloadUri.toString());
                    mStorage = FirebaseStorage.getInstance().getReference();
                } else {
                    // Handle failures
                    // ...
                }
            }

        });



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
    /*The below function is for Same font to the whole Activity*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
